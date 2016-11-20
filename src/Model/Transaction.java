/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.security.Timestamp;

/**
 *
 * @author omarsabbagh
 */
public class Transaction {
    
    private int transaction_id;
    private int account_from;
    private int account_to;
    private double trans_ammount;
    private Timestamp trans_created_at;

    public int getTransaction_id() {
        return transaction_id;
    }

    public int getAccount_from() {
        return account_from;
    }

    public int getAccount_to() {
        return account_to;
    }

    public double getTrans_ammount() {
        return trans_ammount;
    }

    public Timestamp getTrans_created_at() {
        return trans_created_at;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public void setAccount_from(int account_from) {
        this.account_from = account_from;
    }

    public void setAccount_to(int account_to) {
        this.account_to = account_to;
    }

    public void setTrans_ammount(double trans_ammount) {
        this.trans_ammount = trans_ammount;
    }

    public void setTrans_created_at(Timestamp trans_created_at) {
        this.trans_created_at = trans_created_at;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transaction_id=" + transaction_id + ", account_from=" + account_from + ", account_to=" + account_to + ", trans_ammount=" + trans_ammount + ", trans_created_at=" + trans_created_at + '}';
    }
    
    
    
    
}
