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
import Model.Account;
import Model.Client;
import com.sun.rowset.JdbcRowSetImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.rowset.JdbcRowSet;

/**
 *
 * @author omarsabbagh
 */
public class ClientMySql {

    private JdbcRowSet rowSet = null;

    public ClientMySql() {
        try {
            Class.forName(JDBC_DRIVER);
            rowSet = new JdbcRowSetImpl();
            rowSet.setUrl(DB_URL);
            rowSet.setUsername(DB_USER);
            rowSet.setPassword(DB_PASS);
            rowSet.setCommand("SELECT * FROM Client");
            rowSet.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Client create(Client client) {
        try {
            rowSet.moveToInsertRow();
            rowSet.updateString("client_ip", client.getClient_ip());
            rowSet.updateInt("client_port", client.getClient_port());
            rowSet.updateString("client_domain", client.getClient_domain());
            rowSet.insertRow();
            rowSet.moveToCurrentRow();

            rowSet.last();
            client.setClient_id(rowSet.getInt("client_id"));
            rowSet.moveToCurrentRow();

        } catch (SQLException ex) {
            try {
                rowSet.rollback();
                client = null;
            } catch (SQLException e) {

            }
            ex.printStackTrace();
        }
        return client;
    }

    public Client moveFirst() {
        Client client = new Client();
        try {
            rowSet.first();
            client.setClient_ip(rowSet.getString("client_ip"));
            client.setClient_port(rowSet.getInt("client_port"));
            client.setClient_domain(rowSet.getString("client_domain"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return client;
    }

    public Client moveLast() {
        Client client = new Client();
        try {
            rowSet.last();
            client.setClient_ip(rowSet.getString("client_ip"));
            client.setClient_port(rowSet.getInt("client_port"));
            client.setClient_domain(rowSet.getString("client_domain"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return client;
    }

    public Client moveNext() {
        Client client = new Client();
        try {
            if (rowSet.next() == false) {
                rowSet.previous();
            }
            client.setClient_ip(rowSet.getString("client_ip"));
            client.setClient_port(rowSet.getInt("client_port"));
            client.setClient_domain(rowSet.getString("client_domain"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return client;
    }

    public Client movePrevious() {
        Client client = new Client();
        try {
            if (rowSet.previous() == false) {
                rowSet.next();
            }
            client.setClient_ip(rowSet.getString("client_ip"));
            client.setClient_port(rowSet.getInt("client_port"));
            client.setClient_domain(rowSet.getString("client_domain"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return client;
    }

    public Client getCurrent() {
        Client client = new Client();
        try {
            rowSet.moveToCurrentRow();
            client.setClient_ip(rowSet.getString("client_ip"));
            client.setClient_port(rowSet.getInt("client_port"));
            client.setClient_domain(rowSet.getString("client_domain"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return client;
    }

    public ArrayList<Client> getAllClients() throws SQLException {
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

    public Client checkLogin(Client client) throws SQLException {
        System.out.println("heeere 2");
        rowSet.setCommand("select * from Client where Client.client_port = ? and Client.client_ip = ?");
        rowSet.setInt(1, client.getClient_port());
        rowSet.setString(2, client.getClient_ip());
        rowSet.execute();
        while (rowSet.next()) {
            Client returedClient = new Client();
            returedClient.setClient_id(rowSet.getInt("client_id"));
            returedClient.setClient_ip(rowSet.getString("client_ip"));
            returedClient.setClient_port(rowSet.getInt("client_port"));
            returedClient.setClient_domain(rowSet.getString("client_domain"));
            return returedClient;
        }
        return null;
    }
}
