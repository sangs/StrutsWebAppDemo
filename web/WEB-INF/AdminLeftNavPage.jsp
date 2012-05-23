<%-- 
    Document   : AdminLeftNavPage
    Created on : Oct 1, 2010, 6:41:18 AM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/verticalnavbox.css" rel="stylesheet" type="text/css" />
        <title>Admin Tools</title>
    </head>
    <body>
        <h3> Admin Tools </h3>
        <ul>
            <li><html:link forward="showAllWSDSServices">WSDS Services</html:link></li>
            <li><html:link forward="displayNewCustForm">Add New Customer</html:link></li>
            <li><html:link forward="displayNewOrderForm">Create New Order</html:link></li>
            <li><html:link forward="dispOrdersForCustomer">View/Update Customer Orders</html:link></li>
        </ul>

    </body>
</html>
