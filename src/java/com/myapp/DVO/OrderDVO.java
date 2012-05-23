/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.DVO;

import javax.persistence.*;
import java.util.*;

import com.myapp.utilities.*;

/**
 *
 * @author Owner
 */
@Entity
public class OrderDVO {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) long orderID;
    private int orderAmount;
    private long customerID;
    private String orderItem;
    private String orderDesc;
    private String orderNotes;
    private String orderStatus;
    private long editedBy;
    private @Temporal(TemporalType.TIMESTAMP) java.util.Date IN_Date;
    private @Temporal(TemporalType.TIMESTAMP) java.util.Date OUT_Date;
    private @Temporal(TemporalType.TIMESTAMP) java.util.Date MOD_Date;

    public OrderDVO() {
        
    }

    public OrderDVO(int ordAmount, long cID, String ordItem, String ordDesc,
                 String ordNotes, int ordStatus, long editedBy) {

        this.orderAmount = ordAmount;
        this.customerID = cID;
        this.orderItem = ordItem;
        this.orderDesc = ordDesc;
        this.orderNotes = ordNotes;
        this.orderStatus =  WSDSConstants.ORDER_STATUS_PLACED; //ordStatus;
        this.editedBy = WSDSConstants.ADMIN_USER;    //editedBy; //TODO: acual employeeID to get in here
        this.IN_Date = Calendar.getInstance().getTime();
        this.OUT_Date = WSDSUtilities.getDefaultDate();
        this.MOD_Date = WSDSUtilities.getDefaultDate();

    }

    public Date getIN_Date() {
        return IN_Date;
    }

    public void setIN_Date(Date IN_Date) {
        this.IN_Date = IN_Date;
    }

    public void setIN_Date(String in_str) {
        this.IN_Date = Calendar.getInstance().getTime();
    }
    
    public Date getMOD_Date() {
        return MOD_Date;
    }

    public void setMOD_Date(Date MOD_Date) {
        this.MOD_Date = MOD_Date;
    }

    public Date getOUT_Date() {
        return OUT_Date;
    }

    public void setOUT_Date(Date OUT_Date) {
        this.OUT_Date = OUT_Date;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public long getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(long editedBy) {
        this.editedBy = editedBy;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

   

}   //class OrderDVO
