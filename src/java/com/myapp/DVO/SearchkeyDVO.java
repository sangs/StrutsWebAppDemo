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
public class SearchkeyDVO {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) long searchkeyID;
    private String searchkey;
    private double cost;

    public SearchkeyDVO() {

    }

    public SearchkeyDVO(String key, double cost_of_keypurchase) {
        searchkey=key;
        cost=cost_of_keypurchase;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getSearchkey() {
        return searchkey;
    }

    public void setSearchkey(String searchkey) {
        this.searchkey = searchkey;
    }

    public long getSearchkeyID() {
        return searchkeyID;
    }

    

}   //SearchkeyDVO
