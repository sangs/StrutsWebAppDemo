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
public class OrderTypeDVO {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) long orderTypeID;
    private String type;
    private double cost;

    public OrderTypeDVO() {

    }

    public OrderTypeDVO(String orderType, double orderTypeCost) {
        type = orderType;
        cost = orderTypeCost;
    }

    public String getType() {
        return type;
    }

    public void setType(String Type) {
        this.type = Type;
    }

    public long getOrderTypeID() {
        return orderTypeID;
    }

    public double getCost() {
        return cost;
    }



}
