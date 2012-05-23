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

import com.myapp.utilities.*;
import com.myapp.DVO.*;
import com.myapp.utilities.WSDSConstants.*;

/**
 *
 * @author Owner
 */
public class LoginAction extends org.apache.struts.action.Action {
    
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

        int valid = WSDSConstants.INVALID_LOGIN;
        LoginForm lgnForm = (LoginForm)form;
        String name = lgnForm.getLoginID();
        String pwd = lgnForm.getPwd();
        String userfirst_name = null;

        String currOperation = request.getParameter(WSDSConstants.OPERATION);
        request.setAttribute(WSDSConstants.OPERATION, currOperation);
        if (name != null && name.length() > 1 && pwd != null && pwd.length() > 1)
            valid = logIn (name, pwd);

        if (valid == WSDSConstants.CUSTOMER_LOGIN) {//DONE: Show services and ViewOrder options on services page for specific customer with welcome msg
            userfirst_name = getUserFirstName(WSDSConstants.CUSTOMER_LOGIN, name);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            session = request.getSession(true);
            session.setAttribute(WSDSConstants.USERLOGIN, name);
            session.setAttribute(WSDSConstants.USERFIRSTNAME, userfirst_name);
            lgnForm.setFirstName(userfirst_name);
            
            return mapping.findForward(WSDSConstants.SHOW_SERVICE_ORDER_PAGE);
        }
        else if (valid == WSDSConstants.EMP_LOGIN) {
            userfirst_name = getUserFirstName(WSDSConstants.EMP_LOGIN, name);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            session = request.getSession(true);
            session.setAttribute(WSDSConstants.USERLOGIN, name);
            session.setAttribute(WSDSConstants.USERFIRSTNAME, userfirst_name);
            lgnForm.setFirstName(userfirst_name);

            return mapping.findForward(WSDSConstants.SHOW_ADMIN_WSDSVIEW);
        }
        else
             return mapping.findForward(FAILURE);
    }

    private int logIn (String name, String credential) {
        int ret = WSDSConstants.INVALID_LOGIN;
        WSDS myWSDS = new WSDS();
        ret = myWSDS.logIn (name, credential);
        return ret;
    }

    private String getUserFirstName(int loginType, String loginName) {
        String fName = null;
        WSDS myWSDS = new WSDS();
        fName = myWSDS.getUserFirstName(loginType, loginName);
        return fName;
    }


}   //class LoginAction
