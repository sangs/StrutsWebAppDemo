/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.DVO;

import java.util.*;
import javax.persistence.*;
import com.myapp.utilities.*;


/**
 *
 * @author Sangeetha R
 */


public class Customer {
   private CustomerDVO data;
   private String fullName;
   private ArrayList<OrderDVO> ordersList;
   private ArrayList<OrderDVO> dordersList;
   private WSDSUtilities wsdsUtil;

    public Customer() {
        wsdsUtil = new WSDSUtilities();
        data = null;
        ordersList = null;
    }

    public boolean validateCustomer (String name, String cred) {
        boolean valid = false;

        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();
        String matchedPwd = null;

        try {
            TypedQuery<String> cQuery = eMgr.createQuery("SELECT c.pwd FROM CustomerDVO AS c"
                                                    + " where c.loginID= :loginName", String.class);
            cQuery.setParameter("loginName", name);
            matchedPwd = cQuery.getSingleResult();
            if( matchedPwd.compareTo(cred) == 0 )
                valid = true;
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return valid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ArrayList<OrderDVO> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(ArrayList<OrderDVO> ordersList) {
        this.ordersList = ordersList;
    }

    public ArrayList<OrderDVO> getDordersList() {
        return dordersList;
    }

    public void setDordersList(ArrayList<OrderDVO> dordersList) {
        this.dordersList = dordersList;
    }

    public CustomerDVO getData() {
        return data;
    }

    public void setData(CustomerDVO data) {
        this.data = data;
    }

    public String getCustomerFirstName(String loginName) {
        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();
        String firstName = null;

        try {
            TypedQuery<String> cQuery = eMgr.createQuery("SELECT c.firstName FROM CustomerDVO AS c"
                                                    + " where c.loginID= :loginName", String.class);
            cQuery.setParameter("loginName", loginName);
            firstName = cQuery.getSingleResult();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }
         return firstName;
    }

}
