<%-- 
    Document   : CustomerLeftNavPage
    Created on : Nov 9, 2010, 10:50:46 AM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/verticalnavbox.css" rel="stylesheet" type="text/css" />
        <title>User Tools</title>
    </head>
    <body>
        <h3> User Tools </h3>
        <ul>
          <li><html:link forward="showWSDSServices">WSDS Services</html:link></li>
          <li><html:link forward="showOrder">View MyOrders</html:link></li>
        </ul>
    </body>
</html>
