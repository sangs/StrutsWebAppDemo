/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import java.util.*;
import com.myapp.DVO.*;

/**
 *
 * @author Owner
 */
public class AdminWSDSForm extends org.apache.struts.action.ActionForm {
    
   //Variables for ADD_NEW_CUSTOMER
    private String firstName;
    private String lastName;
    private String email;
    private String organization;
    private String phoneNumber;
    private String address;

    //Variables for SHOW_ALL_WSDSSERVICES
    private ArrayList<SearchkeyDVO> searchkeyList;
    private ArrayList<OrderTypeDVO> orderTypeList = null;

    //Variables for SHOW_CUST_ORDERS
    private Customer custOfInterest;
    private ArrayList<OrderDVO> orderList = new ArrayList<OrderDVO>();
    private ArrayList<OrderDVO> dorderList = new ArrayList<OrderDVO>();

    //Variables for DISPLAY_NEWORDER_FORM
    private String orderForFirstName;
    private String orderForLastName;
    private String orderForEmail;
    private int orderAmount;
    private String orderItem;
    private String orderDesc;
    private String orderNotes;

    private String orderTypeChoice;
    private String payTypeChoice;
    private String[] selectedKeyItems = {};
    private String[] keyitems = {"website", "culinary", "chef",
                                  "gardening", "landscape", "lawn maintenance",
                                    "cleaning", "cleaning company"};
    /**
     *
     */
    public AdminWSDSForm() {
        super();

        orderTypeChoice = "Gardening";


        // TODO Auto-generated constructor stub
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustOfInterest() {
        return custOfInterest;
    }

    public void setCustOfInterest(Customer custOfInterest) {
        this.custOfInterest = custOfInterest;
    }

    public String getCustOfInterestFullName () {
        String fullName = null;
        fullName = this.custOfInterest.getFullName();
        return fullName;
    }

    public void setOrdersObj(int index, OrderDVO obj) {
        orderList.set(index, obj);
    }

    public OrderDVO getOrdersObj(int index) {
        checkValid(index);
        return orderList.get(index);
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

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderForEmail() {
        return orderForEmail;
    }

    public void setOrderForEmail(String orderForEmail) {
        this.orderForEmail = orderForEmail;
    }

    public String getOrderForFirstName() {
        return orderForFirstName;
    }

    public void setOrderForFirstName(String orderForFirstName) {
        this.orderForFirstName = orderForFirstName;
    }

    public String getOrderForLastName() {
        return orderForLastName;
    }

    public void setOrderForLastName(String orderForLastName) {
        this.orderForLastName = orderForLastName;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }

    public ArrayList<OrderDVO> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<OrderDVO> orderList) {
        this.orderList = orderList;
    }

    public ArrayList<OrderDVO> getDorderList() {
        return dorderList;
    }

    public void setDorderList(ArrayList<OrderDVO> dorderList) {
        this.dorderList = dorderList;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrderTypeChoice() {
        return orderTypeChoice;
    }

    public void setOrderTypeChoice(String orderTypeChoice) {
        this.orderTypeChoice = orderTypeChoice;
    }

    public String[] getSelectedKeyItems() {
        return selectedKeyItems;
    }

    public void setSelectedKeyItems(String[] selectedKeyItems) {
        this.selectedKeyItems = selectedKeyItems;
    }

    public String[] getKeyitems() {
        return keyitems;
    }

    public void setKeyitems(String[] keyitems) {
        this.keyitems = keyitems;
    }

    public String getPayTypeChoice() {
        return payTypeChoice;
    }

    public void setPayTypeChoice(String payTypeChoice) {
        this.payTypeChoice = payTypeChoice;
    }

    public ArrayList<OrderTypeDVO> getOrderTypeList() {
        return orderTypeList;
    }

    public void setOrderTypeList(ArrayList<OrderTypeDVO> orderTypeList) {
        this.orderTypeList = orderTypeList;
    }

    public ArrayList<SearchkeyDVO> getSearchkeyList() {
        return searchkeyList;
    }

    public void setSearchkeyList(ArrayList<SearchkeyDVO> searchkeyList) {
        this.searchkeyList = searchkeyList;
    }

    private void checkValid (int index) {
        if (orderList == null) {
            orderList = new ArrayList<OrderDVO>();
        }
        int sz = orderList.size();
        if (sz < index + 1) {
            int lastIdx = index+1-sz;
            for (int i=0; i < lastIdx; i++)
                orderList.add(null);
        }
        if (orderList.get(index) == null) {
            orderList.set(index, new OrderDVO());
        }
    }
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    /*public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getName() == null || getName().length() < 1) {
            errors.add("name", new ActionMessage("error.name.required"));
            // TODO: add 'error.name.required' key to your resources
        }
        return errors;
    }*/

    
}
