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
public class EmployeeDVO {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) long employeeID;
    private String firstName;
    private String lastName;
    private String email;
    private String status;
    private String loginID;
    private String pwd;
    private String dept;
    private @Temporal(TemporalType.TIMESTAMP) java.util.Date IN_Date;
    private @Temporal(TemporalType.TIMESTAMP) java.util.Date OUT_Date;


    public EmployeeDVO() {

    }

    public EmployeeDVO (String fName, String lName, String email, String status, String dept,
                        String loginID, String pwd) {
        this.firstName = fName;
        this.lastName = lName;
        this.email = email;
        this.status = status;
        this.dept = dept;
        this.loginID = loginID;
        this.pwd = pwd;
        this.IN_Date = Calendar.getInstance().getTime();
        this.OUT_Date = WSDSUtilities.getDefaultDate();

    }

    public Date getIN_Date() {
        return IN_Date;
    }

    public void setIN_Date(Date IN_Date) {
        this.IN_Date = IN_Date;
    }

    public Date getOUT_Date() {
        return OUT_Date;
    }

    public void setOUT_Date(Date OUT_Date) {
        this.OUT_Date = OUT_Date;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getEmployeeID() {
        return employeeID;
    }


    
}   //class EmployeeDVO
