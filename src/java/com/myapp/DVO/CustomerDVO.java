/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.DVO;

import javax.persistence.*;
import java.util.*;

/**
 *
 * @author Sangeetha R
 */

@Entity
public class CustomerDVO {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) long customerID;
    private @Temporal(TemporalType.TIMESTAMP) java.util.Date IN_Date;
    private String loginID;
    private String pwd;

    private String firstName;
    private String lastName;
    private String organization;
    private String phoneNo;
    private String email;
    private String address;
 

    public CustomerDVO() {

    }

    public CustomerDVO(String fName, String lName, String org,
                        String pNo, String custEmail, String addrs) {
        this.firstName = fName;
        this.lastName = lName;
        this.organization = org;
        this.phoneNo = pNo;
        this.email = custEmail;
        this.address = addrs;
        this.IN_Date = Calendar.getInstance().getTime();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

     public String getLoginID() {
        return loginID;
    }

    public void setLoginID(String loginID) {
        this.loginID = loginID;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getIN_Date() {
        return IN_Date;
    }

    public long getCustomerID() {
        return customerID;
    }

}   //class CustomerDVO
