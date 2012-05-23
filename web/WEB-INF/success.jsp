<%-- 
    Document   : newjsp
    Created on : Jul 12, 2010, 2:41:40 PM
    Author     : Sangeetha R
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registered Successfully</title>
    </head>
    <body>

        <div style="text-align: left; font-size: 1.6em; font-weight: bold">
            <img src="WSDS.gif" hspace="0" vspace="0" border="0" alt=""/>
        </div>

        <h1>Congratulations!</h1>

        <p>You have successfully registered </p>

        <p>Your name is: <bean:write name="RegisterForm" property="name" /></p>
        
        <p>Your LoginID is: <bean:write name="RegisterForm" property="email" /></p>

        <p>Your password is: available upon contacting customer service representative. Available 24/7 </p>

    </body>
</html>
