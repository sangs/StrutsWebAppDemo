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
public class OrderTypeXRefDVO {

    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) long xrefID;
    private long orderID;
    private long orderTypeID;

    public OrderTypeXRefDVO() {

    }

    public OrderTypeXRefDVO(long orderID, long orderTypeID) {
        this.orderID = orderID;
        this.orderTypeID = orderTypeID;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getOrderTypeID() {
        return orderTypeID;
    }

    public void setOrderTypeID(long orderTypeID) {
        this.orderTypeID = orderTypeID;
    }

    public long getXrefID() {
        return xrefID;
    }

} //class OrderTypeXRefDVO
