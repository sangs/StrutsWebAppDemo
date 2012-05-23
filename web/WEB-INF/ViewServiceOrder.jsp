<%-- 
    Document   : ViewServiceOrder
    Created on : Jul 15, 2010, 12:36:32 PM
    Author     : Sangeetha R
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<%@page import="com.myapp.utilities.*"%>
<%@page import="com.myapp.DVO.*" %>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/displaytag.css" rel="stylesheet" type="text/css" />
        <link href="css/ordertypesdisplaytag.css" rel="stylesheet" type="text/css" />
        <title>WSDS Services</title>
    </head>
    <body>

        <div style="text-align: left; font-size: 1.6em; font-weight: bold">
            <img src="WSDS.gif" hspace="0" vspace="0" border="0" alt=""/>
        </div>
        <div id="heightspacer">&nbsp;</div>

        <html:form action="/ViewServiceOrderAction">
            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                <tr>
                    <td align="left" style="font-weight:bold; text-align:center; padding:4px; width: 120px">
                        <h3 style="color:black; text-transform:capitalize;"> Welcome <%= session.getAttribute(WSDSConstants.USERFIRSTNAME) %>! </h3>
                    </td>
                </tr>
                <tr> </tr>
                <tr>
                    <td align="left" valign="top" class="trpadleft0">
                        <div id="leftColumn"><%@include file="CustomerLeftNavPage.jsp"%></div>
                    </td>
                    <td align="left" valign="top" class="trpadleft0">
                                <% String str1 = WSDSConstants.SHOW_WSDS_SERVICES;%>
                                <% String str2 = WSDSConstants.OPERATION;%>
                                <% String str3 = (String) request.getAttribute(str2);%>
                                <% if (str1.equals(str3)) {%>

                                <% }%>

                                <% if (WSDSConstants.SHOW_WSDS_SERVICES.equals(request.getAttribute(WSDSConstants.OPERATION))) {%>

                                <html:form action="ViewServiceOrderAction.do?operation=showWSDSServices">

                                    <display:table name="orderTypeList" id="row"
                                                   class="otdataTable" defaultsort="1"
                                                   defaultorder="ascending" requestURI="/ViewServiceOrderAction">
                                        <display:caption title="Types of Services Offered" />
                                        <display:column property="type" title="Categories of WebSite" />
                                    </display:table>
                                   
                                </html:form>
                                <% }%>

                                <% if (WSDSConstants.SHOW_ORDER.equals(request.getAttribute(WSDSConstants.OPERATION))) {%>
                                <logic:notEmpty name="ViewServiceOrderForm" property="<%=WSDSConstants.ORDER_LIST%>">
                                 <html:form action="ViewServiceOrderAction.do?operation=showOrder">

                                    <h3 style="color:black; text-transform:capitalize; position: relative; left: -20px;">Active Orders</h3>
                                    <display:table name="orderList" id="row"
                                                   class="dataTable" defaultsort="1" 
                                                   defaultorder="ascending" requestURI="/ViewServiceOrderAction">
                                        <display:caption title="Active Orders" />
                                        <display:column property="orderItem" title="Item" />
                                        <display:column property="orderDesc" title="Description" />
                                        <display:column property="orderAmount" title="Quantity" />
                                        <display:column property="IN_Date" title="Order Date" format="{0,date,short}" />
                                    </display:table>
                                 </html:form>
                                </logic:notEmpty>
                                 <logic:notEmpty name="ViewServiceOrderForm" property="<%=WSDSConstants.D_ORDER_LIST%>">
                                 <html:form action="ViewServiceOrderAction.do?operation=showOrder">

                                    <h3 style="color:black; text-transform:capitalize; position: relative; left: -20px;">Completed Orders</h3>
                                    <display:table name="dorderList" id="row"
                                                   class="dataTable" defaultsort="1"
                                                   defaultorder="ascending" requestURI="/ViewServiceOrderAction">
                                        <display:caption title="Completed Orders" />
                                        <display:column property="orderItem" title="Item" />
                                        <display:column property="orderDesc" title="Description" />
                                        <display:column property="orderAmount" title="Quantity" />
                                        <display:column property="OUT_Date" title="Delivered Date" format="{0,date,short}" />
                                    </display:table>
                                 </html:form>
                                </logic:notEmpty>
                                <% }%>
                    </td>
                </tr>
            </table>
        </html:form>
    </body>
</html>



