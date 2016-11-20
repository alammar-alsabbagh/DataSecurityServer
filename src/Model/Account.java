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
public class Account implements Serializable{
    
    private int account_id;
    private int account_client_id;
    private double account_balance;

    public Account() {
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public int getAccount_client_id() {
        return account_client_id;
    }

    public void setAccount_client_id(int account_client_id) {
        this.account_client_id = account_client_id;
    }

    public double getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    @Override
    public String toString() {
        return "Account{" + "account_id=" + account_id + ", account_client_id=" + account_client_id + ", account_balance=" + account_balance + '}';
    }
    
    
    
}
