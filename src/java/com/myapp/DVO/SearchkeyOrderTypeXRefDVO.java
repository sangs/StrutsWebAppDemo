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
public class SearchkeyOrderTypeXRefDVO {
    private  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) long xrefID;
    private long searchkeyID;
    private long orderTypeID;
    private long orderID;

    public SearchkeyOrderTypeXRefDVO() {
        
    }

    public SearchkeyOrderTypeXRefDVO(long skID, long otID, long oID) {
        this.searchkeyID = skID;
        this.orderTypeID = otID;
        this.orderID = oID;
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

    public long getSearchkeyID() {
        return searchkeyID;
    }

    public void setSearchkeyID(long searchkeyID) {
        this.searchkeyID = searchkeyID;
    }

    public long getXrefID() {
        return xrefID;
    }

    

}   //class SearchkeyOrderTypeXRefDVO
