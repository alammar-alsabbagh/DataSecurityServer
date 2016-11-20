/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import DAL.AccountMySql;
import DAL.ClientMySql;
import Encryption.AESEncryption;
import Model.Account;
import Model.Client;
import View.ServerGUI;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author omarsabbagh
 */
public class ServerThread extends Thread {
   private ServerSocket serverSocket;
   
   public ServerThread(int port) throws IOException {
      serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(100000);
   }
   
   
   public void addTextToTextBox(String s){
       ServerGUI.server_text.setText(ServerGUI.server_text.getText() + s);
   }

   public void run() {
      while(true) {
         try {
      
            ServerGUI.server_text.setText(ServerGUI.server_text.getText() + "Waiting for client on port " + 
            serverSocket.getLocalPort() + "... \n");
            Socket server = serverSocket.accept();
            DataInputStream distream = new DataInputStream(new BufferedInputStream(server.getInputStream()));
            String choice = distream.readUTF();
            System.out.println(choice);
             
             switch(choice){
                 case "Get-Account":
                 {
                     AccountMySql accountMySql = new AccountMySql();
                     ArrayList<Account> accounts = accountMySql.getAllAccounts();    
                     AESEncryption.encrypt(accounts, server.getOutputStream());
                     
                     break;
                 }
                 case "Get-Clients":
                 {
                     ClientMySql clientMySql = new ClientMySql();
                     ArrayList<Client> clients = clientMySql.getAllClients();
                     ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
                     oos.writeObject(clients);    
                     break;
                 }
                 case "Check_login":
                 {
                     ClientMySql clientMySql = new ClientMySql();
                     System.out.println("here server");
                     ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
                     Client recieved  = (Client) ois.readObject();
                     DataOutputStream dostream = new DataOutputStream(new BufferedOutputStream(server.getOutputStream()));
                     Client signedClient = clientMySql.checkLogin(recieved);
                    try{
                     if(!signedClient.equals(null))
                     {
                        System.out.println("Logged in Successfully");
                        dostream.writeBoolean(true);
                        dostream.flush();
                        ObjectOutputStream oos = new ObjectOutputStream(server.getOutputStream());
                        oos.writeObject(signedClient);
                        oos.flush();
                     }
                     else
                     {
                        System.out.println(" Login Failed ");
                        dostream.writeBoolean(false);
                        dostream.flush();

                     }
                    }catch(NullPointerException e)
                    {
                        System.out.println(" Login Failed ");
                        dostream.writeBoolean(false);
                        dostream.flush();
                    }

                     break;
                 }
                 
             }
                        
         }catch(SocketTimeoutException s) {
            ServerGUI.server_text.setText(ServerGUI.server_text.getText() +  "Socket timed out!"+ "\n"); 
            break;
         }catch(IOException e) {
            e.printStackTrace();
            break;
         } catch (SQLException ex) {
              Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
          } catch (NoSuchAlgorithmException ex) {
              Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
          } catch (NoSuchPaddingException ex) {
              Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
          } catch (InvalidKeyException ex) {
              Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
   }
}
