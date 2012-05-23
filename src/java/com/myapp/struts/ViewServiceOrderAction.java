/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.*;
import java.security.Principal;

import java.util.*;
import com.myapp.utilities.*;
import com.myapp.DVO.*;


/**
 *
 * @author Owner
 */
public class ViewServiceOrderAction extends org.apache.struts.actions.DispatchAction {
    
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
 
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
    
    @Override
     public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         ViewServiceOrderForm soForm = (ViewServiceOrderForm)form;
         String currOperation = request.getParameter(WSDSConstants.OPERATION);
         request.setAttribute(WSDSConstants.OPERATION, currOperation);
         //WSDSUtilities.setCurrOperation(currOperation);

         return mapping.findForward(currOperation);
     } */


    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
     public ActionForward showWSDSServices(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ArrayList<OrderTypeDVO> orderTypeList = null;
        ViewServiceOrderForm soForm = (ViewServiceOrderForm)form;
        WSDS myWSDS = new WSDS();

        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);
        orderTypeList = myWSDS.getServicesOffered();
        soForm.setOrderTypeList(orderTypeList);
        request.setAttribute(WSDSConstants.ORDER_TYPES_LIST, orderTypeList);

        return mapping.findForward(WSDSConstants.SHOW_SERVICE_ORDER_PAGE);
    }

    public ActionForward showOrder(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        boolean ret = false;
        ViewServiceOrderForm soForm = (ViewServiceOrderForm)form;
       
        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);

        ret = getMyOrders(request, form);
        if (!ret)
          return mapping.findForward(FAILURE);
        return mapping.findForward(WSDSConstants.SHOW_SERVICE_ORDER_PAGE);
    }


    private boolean getMyOrders(HttpServletRequest request, ActionForm form) {
        boolean ret = false;
        String currentUserLogIn = null;
        WSDS myWSDS = new WSDS();
        Customer requestingCust = null;
        HttpSession session;
        ViewServiceOrderForm vsoForm = (ViewServiceOrderForm)form;

        session = request.getSession();
        if (session != null) {
            currentUserLogIn = (String)session.getAttribute(WSDSConstants.USERLOGIN);
            if (currentUserLogIn != null) {
                requestingCust = myWSDS.getCustomerOrders(currentUserLogIn);
                vsoForm.setRequestingCustomer(requestingCust);
                //TODO: remove this and instead access ordersList inside Customer
                if (requestingCust != null) {
                    ret = true;
                    vsoForm.setOrderList(requestingCust.getOrdersList());
                    vsoForm.setDorderList(requestingCust.getDordersList());
                    request.setAttribute(WSDSConstants.ORDER_LIST, requestingCust.getOrdersList());
                    request.setAttribute(WSDSConstants.D_ORDER_LIST, requestingCust.getDordersList());
                }
            }
        }
        
        return ret;
    }

    
}   //class ViewServiceOrderAction
