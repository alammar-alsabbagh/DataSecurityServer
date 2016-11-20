/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import static Connection.MySqlConstants.DB_PASS;
import static Connection.MySqlConstants.DB_URL;
import static Connection.MySqlConstants.DB_USER;
import static Connection.MySqlConstants.JDBC_DRIVER;
import Model.Transaction;
import Model.Client;
import com.sun.rowset.JdbcRowSetImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.JdbcRowSet;

/**
 *
 * @author omarsabbagh
 */
public class TransactionMySql {
    private JdbcRowSet rowSet = null;
   
   public TransactionMySql() {
      try {
         Class.forName(JDBC_DRIVER);
         rowSet = new JdbcRowSetImpl();
         rowSet.setUrl(DB_URL);
         rowSet.setUsername(DB_USER);
         rowSet.setPassword(DB_PASS);
         rowSet.setCommand("SELECT * FROM Transaction");
         rowSet.execute();
      }catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
   
   
   
   public Transaction create(Transaction transaction) {
      try {
         rowSet.moveToInsertRow();
         rowSet.updateInt("account_from",transaction.getAccount_from() );
         rowSet.updateInt("account_to",transaction.getAccount_to());
         rowSet.updateDouble("trans_ammount",transaction.getTrans_ammount());
         rowSet.insertRow();
         rowSet.moveToCurrentRow();
      } catch (SQLException ex) {
         try {
            rowSet.rollback();
            transaction = null;
         } catch (SQLException e) {

         }
         ex.printStackTrace();
      }
      return transaction;
   }
   
     public ArrayList<Client> getAllClients() throws SQLException{
            ArrayList<Client> accounts = new ArrayList<Client>();
            rowSet.setCommand("select * from Client");
            rowSet.execute();
            while (rowSet.next()) {
                Client client = new Client();
                client.setClient_id(rowSet.getInt("client_id"));
                client.setClient_ip(rowSet.getString("client_ip"));
                client.setClient_port(rowSet.getInt("client_port"));
                client.setClient_domain(rowSet.getString("client_domain"));
                accounts.add(client);
            }
            return accounts;   
      }
}