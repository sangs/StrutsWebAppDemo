/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.DVO;

import javax.persistence.*;


/**
 *
 * @author Owner
 */

@Entity
public class PaymentDVO {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) long ID;
    private long orderID;
    private long payTypeID;
    private double amount;
    private String status;

    public PaymentDVO() {

    }

    public PaymentDVO(long orderID, long payTypeID, double amount, String status) {
        this.orderID = orderID;
        this.payTypeID = payTypeID;
        this.amount = amount;
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getPayTypeID() {
        return payTypeID;
    }

    public void setPayTypeID(long payTypeID) {
        this.payTypeID = payTypeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getID() {
        return ID;
    }

    

} //class PaymentDVO
