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
public class PayTypeDVO {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) long id;
    private String type;

    public PayTypeDVO() {

    }

    public PayTypeDVO(String payType) {
        type = payType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

}   //PayTypeDVO
