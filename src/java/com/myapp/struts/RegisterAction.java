/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.myapp.utilities.*;
import com.myapp.DVO.*;

/**
 *
 * @author Owner
 */
public class RegisterAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        RegisterForm regForm = (RegisterForm)form;
        boolean ret = false;

        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);
        if (regForm.getFirstName() == null || regForm.getFirstName().length() < 1 ||
                regForm.getLastName() == null || regForm.getLastName().length() < 1 ||
                regForm.getEmail() == null || regForm.getEmail().indexOf("@") == -1 ||
                regForm.getAddress() == null || regForm.getAddress().length() < 1 ||
                regForm.getPhoneNumber() == null || regForm.getPhoneNumber().length() < 1) {
            
                return mapping.findForward(FAILURE);
        }

        ret = registerNewCustomer (form);
        if (!ret)
            return mapping.findForward(FAILURE);
        else
            return mapping.findForward(SUCCESS);
    }

    boolean registerNewCustomer (ActionForm form) {
        boolean ret = false;
        RegisterForm regForm = (RegisterForm)form;
        WSDS myWSDS = new WSDS();

        CustomerDVO data = new CustomerDVO(regForm.getFirstName(), regForm.getLastName(),
                                           regForm.getOrganization(), regForm.getPhoneNumber(),
                                           regForm.getEmail(), regForm.getAddress());

        ret = myWSDS.registerNewCustomer(data);

        return ret;
    }
}
