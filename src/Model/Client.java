/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author omarsabbagh
 */
public class Client implements Serializable{
    private String client_ip;
    private int client_port;
    private String client_domain;
    private int client_id;

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getClient_id() {
        return client_id;
    }
    
    

   

    public String getClient_ip() {
        return client_ip;
    }

    public int getClient_port() {
        return client_port;
    }

    public String getClient_domain() {
        return client_domain;
    }

    public void setClient_ip(String client_ip) {
        this.client_ip = client_ip;
    }

    public void setClient_port(int client_port) {
        this.client_port = client_port;
    }

    public void setClient_domain(String client_domain) {
        this.client_domain = client_domain;
    }

    @Override
    public String toString() {
        return "Client{" + "client_ip=" + client_ip + ", client_port=" + client_port + ", client_domain=" + client_domain + '}';
    }
    
    
    
}
