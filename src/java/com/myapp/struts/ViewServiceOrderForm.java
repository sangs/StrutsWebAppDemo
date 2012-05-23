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
public class ViewServiceOrderForm extends org.apache.struts.action.ActionForm {

   //Variables to receive the customers Orders and their status
   private Customer requestingCustomer;
   private ArrayList<OrderDVO> orderList;
   private ArrayList<OrderDVO> dorderList;
   //TODO: Keep two seperate lists: one for active orders and another for delivered orders. To be shown on showorders

   //Variables to retrieve services provided
   private String orderType = null;
   private OrderTypeDVO orderTypeObj = null;
   private ArrayList<OrderTypeDVO> orderTypeList = null;

    /**
     *
     */
    public ViewServiceOrderForm() {
        super();
        // TODO Auto-generated constructor stub
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

    public Customer getRequestingCustomer() {
        return requestingCustomer;
    }

    public void setRequestingCustomer(Customer requestingCustomer) {
        this.requestingCustomer = requestingCustomer;
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
  /*  public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
       
        return errors;
    } */

    

     public ArrayList<OrderTypeDVO> getOrderTypeList() {
        return orderTypeList;
    }

    public void setOrderTypeList(ArrayList<OrderTypeDVO> orderTypeList) {
        this.orderTypeList = orderTypeList;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeAt(String idx) {
        int i = (Integer.valueOf(idx)).intValue();
        return getOrderTypeAt(i);
    }

    public String getOrderTypeAt(int index) {
        checkValid(index);
        return ((OrderTypeDVO)orderTypeList.get(index)).getType();
    }

    public void setOrderTypeAt(int index, String orderType) {
        checkValid(index);
        ((OrderTypeDVO)orderTypeList.get(index)).setType(orderType);
    }

    public OrderTypeDVO getOrderTypeObj() {
        return orderTypeObj;
    }

    public void setOrderTypeObj(OrderTypeDVO orderTypeObj) {
        this.orderTypeObj = orderTypeObj;
    }

    public OrderTypeDVO getOrderTypeObj(int i) {
        checkValid(i);
        return ((OrderTypeDVO)orderTypeList.get(i));
    }

    public void setOrderTypeObj(int i, OrderTypeDVO otDVO) {
        checkValid (i);
        orderTypeList.set(i, otDVO);
    }

    private void checkValid (int index) {
        if (orderTypeList == null) {
            orderTypeList = new ArrayList<OrderTypeDVO>();
        }
        int sz = orderTypeList.size();
        if (sz < index + 1) {
            int lastIdx = index+1-sz;
            for (int i=0; i < lastIdx; i++)
                orderTypeList.add(null);
        }
        if (orderTypeList.get(index) == null) {
            orderTypeList.set(index, new OrderTypeDVO());
        }
    }
}
