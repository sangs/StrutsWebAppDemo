<%-- 
    Document   : register
    Created on : Jul 10, 2010, 4:55:52 PM
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
        <title>Registration Form</title>
    </head>
    <body>
        <h1>Registration Form</h1>

        <html:form action="/Register">
            <table border="0">

                <tbody>
                    <tr>
                        <td>* Last Name:</td>
                        <td><html:text property="lastName" /></td>
                    </tr>
                    <tr>
                        <td>* First Name:</td>
                        <td><html:text property="firstName" /></td>
                    </tr>
                    <tr>
                        <td>* Email address:</td>
                        <td><html:text property="email" /></td>
                    </tr>
                    <tr>
                        <td>Organization Name:</td>
                        <td><html:text property="organization" /></td>
                    </tr>
                    <tr>
                        <td>* Phone Number:</td>
                        <td><html:text property="phoneNumber" /></td>
                    </tr>
                    <tr>
                        <td>* Address:</td>
                        <td><html:textarea property="address" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><html:submit value="Register" /></td>
                    </tr>
                </tbody>
            </table>

        </html:form>

    </body>
</html>
