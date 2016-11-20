/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author omarsabbagh
 */
/**
 *
 * @author omarsabbagh
 */
import static Connection.MySqlConstants.DB_PASS;
import static Connection.MySqlConstants.DB_URL;
import static Connection.MySqlConstants.DB_USER;
import static Connection.MySqlConstants.JDBC_DRIVER;
import Model.Account;
import com.mysql.jdbc.Connection;

import com.sun.rowset.JdbcRowSetImpl;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.rowset.JdbcRowSet;
public class AccountMySql {
   private JdbcRowSet rowSet = null;
   
   public AccountMySql() {
      try {
         Class.forName(JDBC_DRIVER);
         rowSet = new JdbcRowSetImpl();
         rowSet.setUrl(DB_URL);
         rowSet.setUsername(DB_USER);
         rowSet.setPassword(DB_PASS);
         rowSet.setCommand("SELECT * FROM Account");
         rowSet.execute();
      }catch (Exception ex) {
         ex.printStackTrace();
      }
   }
   
   
   public Account create(Account account) {
      try {
         rowSet.moveToInsertRow();
         rowSet.updateInt("account_client_id", account.getAccount_client_id());
         rowSet.updateDouble("account_balance",account.getAccount_balance());
         rowSet.insertRow();
         rowSet.moveToCurrentRow();
      } catch (SQLException ex) {
         try {
            rowSet.rollback();
            account = null;
         } catch (SQLException e) {

         }
         ex.printStackTrace();
      }
      return account;
   }

   public Account update(Account account) {
      try {
         rowSet.updateDouble("account_balance",account.getAccount_balance());
         rowSet.updateRow();
         rowSet.moveToCurrentRow();
      } catch (SQLException ex) {
         try {
            rowSet.rollback();
         } catch (SQLException e) {

         }
         ex.printStackTrace();
      }
      return account;
   }

   public void delete() {
      try {
         rowSet.moveToCurrentRow();
         rowSet.deleteRow();
      } catch (SQLException ex) {
         try {
            rowSet.rollback();
         } catch (SQLException e) { }
         ex.printStackTrace();
      }

   }

   public Account moveFirst() {
      Account account = new Account();
      try {
         rowSet.first();
         account.setAccount_id(rowSet.getInt("account_id"));
         account.setAccount_client_id(rowSet.getInt("account_client_id"));
         account.setAccount_balance(rowSet.getDouble("account_balance"));

      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return account;
   }

   public Account moveLast() {
      Account account = new Account();
      try {
         rowSet.last();
         account.setAccount_id(rowSet.getInt("account_id"));
         account.setAccount_client_id(rowSet.getInt("account_client_id"));
         account.setAccount_balance(rowSet.getDouble("account_balance"));


      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return account;
   }

   public Account moveNext() {
      Account account = new Account();
      try {
         if (rowSet.next() == false)
            rowSet.previous();
          account.setAccount_id(rowSet.getInt("account_id"));
         account.setAccount_client_id(rowSet.getInt("account_client_id"));
         account.setAccount_balance(rowSet.getDouble("account_balance"));
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return account;
   }

   public Account movePrevious() {
      Account account = new Account();
      try {
         if (rowSet.previous() == false)
            rowSet.next();
           account.setAccount_id(rowSet.getInt("account_id"));
         account.setAccount_client_id(rowSet.getInt("account_client_id"));
         account.setAccount_balance(rowSet.getDouble("account_balance"));

      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return account;
   }

   public Account getCurrent() {
      Account account = new Account();
      try {
         rowSet.moveToCurrentRow();
        account.setAccount_id(rowSet.getInt("account_id"));
         account.setAccount_client_id(rowSet.getInt("account_client_id"));
         account.setAccount_balance(rowSet.getDouble("account_balance"));
      } catch (SQLException ex) {
         ex.printStackTrace();
      }
      return account;
   }
   
   public ArrayList<Account> getAllAccounts() throws SQLException{
            ArrayList<Account> accounts = new ArrayList<Account>();
            rowSet.setCommand("select * from Account");
            rowSet.execute();
            while (rowSet.next()) {
                Account account = new Account();
                account.setAccount_balance(rowSet.getDouble("account_balance"));
                account.setAccount_client_id(rowSet.getInt("account_client_id"));
                account.setAccount_id(rowSet.getInt("account_id"));
                accounts.add(account);
            }
            return accounts;
            
            
      }
}