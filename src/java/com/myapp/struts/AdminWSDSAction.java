/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;

import org.apache.struts.util.LabelValueBean;
import java.util.*;
import com.myapp.DVO.*;
import com.myapp.utilities.*;

/**
 *
 * @author Owner
 */
public class AdminWSDSAction extends DispatchAction {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";

    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=myAction1,
     * where "method" is the value specified in <action> element : 
     * ( <action parameter="method" .../> )
     *
     *
     * //TODO: TO be able to view/update the order status from ordered to delivered
     */
    public ActionForward displayNewCustForm(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        boolean ret = false;

        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);
        return mapping.findForward(WSDSConstants.SHOW_ADMIN_WSDSVIEW);
    }

    public ActionForward addNewCustomer(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        boolean ret = false;

        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);

        if( validateThisForm(form, currOperation) == false ) {
                return mapping.findForward(FAILURE);
        }
        ret = addNewCustomer (form);
        if (!ret)
            return mapping.findForward(FAILURE);
    
        return mapping.findForward(WSDSConstants.SHOW_ADMIN_WSDSVIEW);
    }

    public ActionForward showAllWSDSServices(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ArrayList<OrderTypeDVO> orderTypeList = null;
        ArrayList<SearchkeyDVO> skList = null;
        AdminWSDSForm adWSDSForm = (AdminWSDSForm)form;
        WSDS myWSDS = new WSDS();

        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);

        orderTypeList = myWSDS.getServicesOffered();
        adWSDSForm.setOrderTypeList(orderTypeList);
        request.setAttribute(WSDSConstants.ORDER_TYPES_LIST, orderTypeList);

        skList = myWSDS.getSearchKeyInfo();
        adWSDSForm.setSearchkeyList(skList);
        request.setAttribute(WSDSConstants.SEARCH_KEYS_LIST, skList);
        
        return mapping.findForward(WSDSConstants.SHOW_ADMIN_WSDSVIEW);
    }


    public ActionForward displayNewOrderForm(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);

        //TODO: dynamically populate from DB in future
        Collection odTypes = new ArrayList();
        odTypes.add(new LabelValueBean("Gardening", "Gardening")); //"GD"
        odTypes.add(new LabelValueBean("Landscaping", "Landscaping"));   //"LS"
        odTypes.add(new LabelValueBean("Cleaning Service", "Cleaning Service"));  //"CS"
        odTypes.add(new LabelValueBean("Chef Website", "Chef Website"));  //"CF"
        request.setAttribute("choices", odTypes);

        Collection pTypes = new ArrayList();
        pTypes.add(new LabelValueBean("CHEQUE", "CHEQUE")); //"CQ"
        pTypes.add(new LabelValueBean("CREDIT CARD", "CREDIT CARD"));   //"CH"
        pTypes.add(new LabelValueBean("PAYPAL", "PAYPAL")); //"PP"
        request.setAttribute("items", pTypes);

        return mapping.findForward(WSDSConstants.SHOW_ADMIN_WSDSVIEW);
    }


    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=myAction2,
     * where "method" is the value specified in <action> element : 
     * ( <action parameter="method" .../> ) placeNewOrderForCustomer
     */
    public ActionForward PlaceOrder(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        boolean ret = false;
        AdminWSDSForm adminWSDS = (AdminWSDSForm)form;
        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);

        ret = placeOrderForCustomer (form);

        if (!ret)
            return mapping.findForward(FAILURE);
        else
            return mapping.findForward(WSDSConstants.SHOW_ADMIN_WSDSVIEW);
    }

    public ActionForward dispOrdersForCustomer(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);

        return mapping.findForward(WSDSConstants.SHOW_ADMIN_WSDSVIEW);
    }

    public ActionForward showOrdersForCustomer(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        boolean ret = false;
        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);

        if( (ret = validateThisForm(form, currOperation)) == true ) {
          ret = getCustomerOrders(form, request);
        }
        if (!ret)
            return mapping.findForward(FAILURE);
        else
            return mapping.findForward(WSDSConstants.SHOW_ADMIN_WSDSVIEW);
    }

    public ActionForward updateOrderStatus(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        boolean ret = false;
        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);

        ret = updateOrderStatus(form);
        if (!ret)
            return mapping.findForward(FAILURE);
        else
            return showOrdersForCustomer(mapping, form, request, response);
    }

    boolean updateOrderStatus (ActionForm form) {
        boolean ret = false;
        AdminWSDSForm acForm = (AdminWSDSForm)form;
        ArrayList<OrderDVO> olist = acForm.getOrderList();
        WSDS myWSDS = new WSDS();
        int sz = olist.size();
        long customerID = 0;
        CustomerDVO custData = null;

        for(int ix = 0; ix < sz; ix++) {
            if( (olist.get(ix)).getOrderStatus().equals(WSDSConstants.ORDER_STATUS_DELIVERED) ) {
              customerID = (olist.get(ix)).getCustomerID();
              ret = myWSDS.updateOrderStatus(olist.get(ix)) ;
            }
        }

        custData = myWSDS.getCustomerData(customerID);
        if (custData != null) {
          acForm.setFirstName(custData.getFirstName());
          acForm.setLastName(custData.getLastName());
          acForm.setEmail(custData.getEmail());
        }

        return ret;
    }

     boolean addNewCustomer (ActionForm form) {
        boolean ret = false;
        AdminWSDSForm acForm = (AdminWSDSForm)form;
        WSDS myWSDS = new WSDS();

        CustomerDVO data = new CustomerDVO(acForm.getFirstName(), acForm.getLastName(),
                                           acForm.getOrganization(), acForm.getPhoneNumber(),
                                           acForm.getEmail(), acForm.getAddress());

        ret = myWSDS.registerNewCustomer(data);
        return ret;
    }

    boolean placeOrderForCustomer(ActionForm form) {
        boolean ret = false;
        long custID = -1;
        String fName = null;
        String lName = null;
        String email = null;
        int orderAmount = 0;
        String orderItem = null;
        String orderDesc = null;
        String orderNotes = null;
        AdminWSDSForm adminWSDS = (AdminWSDSForm)form;
        OrderDVO orderToPlace = null;
        List<String> keysPurchased = null;
        String thisOrderType = null;
        String payTypeSelected = null;
        WSDS myWSDS = new WSDS();

        //Get OrderType from form (selection from a set of radio buttons in the form)
        thisOrderType = adminWSDS.getOrderTypeChoice();

        //Get payTypeSelected from form (selection from a set of radio buttons in the form)
        payTypeSelected = adminWSDS.getPayTypeChoice();

        //Populate keysPurchased (selection from a set of check boxes in the form) from the form
        String[] keys = adminWSDS.getKeyitems();
        keysPurchased = Arrays.asList( keys );

        if( (fName= adminWSDS.getOrderForFirstName()) != null && fName.length() > 1
                     && (lName = adminWSDS.getOrderForLastName()) != null && lName.length() > 1
                     && (email = adminWSDS.getOrderForEmail()) != null && email.length() > 1
                     && (orderAmount = adminWSDS.getOrderAmount()) > 0
                     && (orderItem = adminWSDS.getOrderItem()) != null && orderItem.length() > 1
                     && (orderDesc = adminWSDS.getOrderDesc()) != null && orderDesc.length() > 1
                     && thisOrderType != null && payTypeSelected != null) {

            //Get customer ID
            if ( ( custID = myWSDS.getCustomerID(fName, lName, email) ) != 0 ) {
                orderToPlace = new OrderDVO(orderAmount, custID, orderItem, orderDesc, orderNotes, 0, 0);
                Customer customerWithOrder = myWSDS.placeOrder(custID, orderToPlace, thisOrderType, payTypeSelected,
                                                                keysPurchased);
                if (customerWithOrder != null) {
                    //TODO: set customer object to form.
                    //TODO: set orderList object to form.
                    
                    ret = true;
                }
            }
        }

        return ret;
    }

    boolean getCustomerOrders(ActionForm form, HttpServletRequest request) {
        AdminWSDSForm acForm = (AdminWSDSForm)form;
        WSDS myWSDS = new WSDS();
        Customer cust = null;
        String fName = null;
        String lName = null;
        String email = null;
        boolean ret = false;

        fName = acForm.getFirstName();
        lName = acForm.getLastName();
        email = acForm.getEmail();

        //Customer and all orders placed by the customer with complete status
        cust = myWSDS.getCustomerOrders(fName, lName, email);
        acForm.setCustOfInterest(cust);
        //TODO: remove this and instead access ordersList inside Customer
        if (cust != null) {
          acForm.setOrderList(cust.getOrdersList());
          acForm.setDorderList(cust.getDordersList());
          request.setAttribute(WSDSConstants.ORDER_LIST, cust.getOrdersList());
          request.setAttribute(WSDSConstants.D_ORDER_LIST, cust.getDordersList());
        }
        ret = (cust == null) ? false : true;

        return ret;
    }

    public ActionForward cancelThisOrderForCustomer(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        boolean ret = false;
        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);

        request.setAttribute(WSDSConstants.OPERATION, currOperation);
        ret = retrieveAndCancelCustomerOrder(request, form);
        
        if(!ret)
          return mapping.findForward(FAILURE);
        else {
          //return mapping.findForward(WSDSConstants.SHOW_ADMIN_WSDSVIEW);
          return showOrdersForCustomer(mapping, form, request, response);
        }
    }


    private boolean retrieveAndCancelCustomerOrder(HttpServletRequest request, ActionForm form) {
      boolean ret = false;
      WSDS myWSDS = new WSDS();
      Customer cust = null;
      CustomerDVO custData = null;
      AdminWSDSForm acForm = (AdminWSDSForm)form;

      String rparam = request.getParameter(WSDSConstants.SPECIFIC_ORDER);
      long orderToCancel = Long.parseLong(rparam);
      long customerID = myWSDS.getCustomerIDOfOrder(orderToCancel);

      ret = myWSDS.cancelThisOrderForCustomer(orderToCancel);
      //cust = myWSDS.getCustomerOrders(customerID);
      //acForm.setCustOfInterest(cust);

      custData = myWSDS.getCustomerData(customerID);
      if (custData != null) {
          acForm.setFirstName(custData.getFirstName());
          acForm.setLastName(custData.getLastName());
          acForm.setEmail(custData.getEmail());
         // acForm.setOrderList(cust.getOrdersList());
      }
      ret = (custData == null) ? false : true;

      return ret;
    }


    boolean validateThisForm (ActionForm form, String currOperation) {
        boolean ret = false;
        AdminWSDSForm adminWSDS = (AdminWSDSForm)form;
        if (WSDSConstants.ADD_NEW_CUSTOMER.equals(currOperation)) {
                 if (adminWSDS.getFirstName() != null && adminWSDS.getFirstName().length() > 1 &&
                        adminWSDS.getLastName() != null && adminWSDS.getLastName().length() > 1 &&
                        adminWSDS.getEmail() != null && adminWSDS.getEmail().indexOf("@") != -1 &&
                        adminWSDS.getAddress() != null && adminWSDS.getAddress().length() > 1 &&
                        adminWSDS.getPhoneNumber() != null && adminWSDS.getPhoneNumber().length() > 1) {
                     ret = true;
                 }

        }
        else  if ( WSDSConstants.SHOW_CUST_ORDERS.equals(currOperation) ||
                   WSDSConstants.CANCEL_CUST_ORDER.equals(currOperation) || 
                   WSDSConstants.UPDATE_ORDER_STATUS.equals(currOperation) )
        {
                 if (adminWSDS.getFirstName() != null && adminWSDS.getFirstName().length() > 1 &&
                        adminWSDS.getLastName() != null && adminWSDS.getLastName().length() > 1 &&
                        adminWSDS.getEmail() != null && adminWSDS.getEmail().indexOf("@") != -1) {
                     ret = true;
                 }
        }

        return ret;

    }   //validateThisForm

}