<%-- 
    Document   : Login
    Created on : Jul 12, 2010, 11:35:53 AM
    Author     : Sangeetha R
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Form</title>
    </head>

    <body>

        <div style="text-align: left; font-size: 1.6em; font-weight: bold">
            <img src="WSDS.gif" hspace="0" vspace="0" border="0" alt=""/>
        </div>

        <html:form action="/Login">
            <div>
                <table border="0" align="right" cellpadding="3" cellspacing=0 bgcolor=#5CB3FF
                       width="100%">
                    <tbody>
                        <tr>
                            <td colspan="4" style="font-size: 1.4em; color: #FFFFFF; font-weight: bold; width: 450px">
                                WSDS
                            </td>
                            <td style="color:white"> Login </td>
                            <td style="color:white"> Password </td>
                        </tr>
                        <tr>
                            <td align="left" colspan="4" > </td>
                            <td align="left"> <html:text property="loginID" tabindex="1" /> </td>
                            <td align="left"> <html:password property="pwd" tabindex="2" /> </td>
                            <td align="left"> <html:submit value="Login" tabindex="3" /> </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <br>  <br>  <br>  <br>

            <div>
                <table border="0" align="right" cellpadding="0" cellspacing=0 bgcolor=#5CB3FF>
                    <tr>
                        <td align="left" colspan="4" > </td>
                        <td align="center" style="color: #be7429;font-weight: bold; width: 450px" >
                            New to WSDS? 
                            <html:link forward="showRegistration">
                                Register
                            </html:link> (It's Free)
                        </td>
                    </tr>
                </table>
            </div>

            <div style="text-align: left; align: center; font-size: 1.6em; font-weight: bold">
                <img src="buildinghighrise.gif" hspace="0" vspace="0" border="0" alt=""/>
            </div>

            <div id="heightspacer">&nbsp;</div>

        </html:form>
    </body>
</html>
