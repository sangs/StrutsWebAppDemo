<%-- 
    Document   : AdminWSDSView
    Created on : Jul 17, 2010, 7:10:51 PM
    Author     : Owner
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
        <title>WSDS Admin View Page</title>

        <script type="text/javascript">
            function submitForm()
            {
                document.forms[0].action = "AdminWSDSAction.do?operation=PlaceOrder"
                document.forms[0].submit();
            }
            function submitShowOrder()
            {
                document.forms[0].action = "AdminWSDSAction.do?operation=showOrdersForCustomer"
                document.forms[0].submit();
            }
            function submitNewCustomer()
            {
                document.forms[0].action = "AdminWSDSAction.do?operation=addNewCustomer"
                document.forms[0].submit();
            }
            function submitUpdateOrderStatus()
            {
                document.forms[0].action="AdminWSDSAction.do?operation=updateOrderStatus"
                document.forms[0].submit();
            }
        </script>

    </head>
    <body id ="mainbody">
        <div style="text-align: left; font-size: 1.6em; font-weight: bold">
            <img src="WSDS.gif" hspace="0" vspace="0" border="0" alt=""/>
        </div>
        <div id="heightspacer">&nbsp;</div>
        <p></p>

        <table border="0" cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td align="left" style="font-weight:bold; text-align:center; padding:4px; width: 120px">
                    <h3 style="color:black; text-transform:capitalize;"> Welcome <%= session.getAttribute(WSDSConstants.USERFIRSTNAME)%>! </h3>
                </td>
            </tr>
            <tr> </tr>
            <tr>
                <td align="left" valign="top" class="trpadleft0">
                    <div id="leftColumn"><%@include file="AdminLeftNavPage.jsp"%></div>
                </td>
                <td>
                    <div id="content">
                        <div id="content_container">

                            <table>
                                <tr> <td align="center"> <h2 style="color:captiontext; text-align:center; left:3px; position:relative;">Customer & Order administration and maintenance </h2></td></tr>
                                <tr></tr>
                            </table>

                            <html:form action="/AdminWSDSAction">
                                <% if (WSDSConstants.SHOW_ALL_WSDSSERVICES.equals(request.getAttribute(WSDSConstants.OPERATION))) {%>
                                <html:form action="AdminWSDSAction.do?operation=showAllWSDSServices">
                                    <display:table name="orderTypeList" id="row"
                                                   class="dataTable" defaultsort="1"
                                                   defaultorder="ascending" requestURI="/AdminWSDSAction">
                                        <display:caption title="Types of Services Offered" />
                                        <display:column property="type" title="Categories of WebSite" />
                                        <display:column property="cost" title="Price" />
                                    </display:table>
                                    <display:table name="searchkeyList" id="row"
                                                   class="dataTable" defaultsort="1"
                                                   defaultorder="ascending" requestURI="/AdminWSDSAction">
                                        <display:caption title="Search Keys Offered" />
                                        <display:column property="searchkey" title="Search Key" />
                                        <display:column property="cost" title="Price" />
                                    </display:table>
                                </html:form>
                                <% }%>

                                <% if (WSDSConstants.DISPLAY_NEW_CUSTFORM.equals(request.getAttribute(WSDSConstants.OPERATION))) {%>
                                <html:form action="AdminWSDSAction.do?operation=displayNewCustForm">
                                    <table class="dataTable">
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
                                            <td align="center" colspan="2">
                                                <html:submit value="Add new customer" onclick="submitNewCustomer()" />
                                            </td>
                                        </tr>
                                    </table>
                                </html:form>
                                <% }%>


                                <% if (WSDSConstants.DISPLAY_NEWORDER_FORM.equals(request.getAttribute(WSDSConstants.OPERATION))) {%>

                                <html:form action="AdminWSDSAction.do?operation=displayNewOrderForm">
                                    <table class="dataTable">
                                        <%-- <table border="=1" align="center" cellpadding="1" cellspacing="1"
                                           bgcolor=#FFFFFF class="tblwidth700 tblborder"> --%>
                                        <tr>
                                            <th colspan="2" align="center">WSDS Order Form</th>
                                        </tr>
                                        <tr>
                                            <td>* Last Name:</td>
                                            <td><html:text property="orderForLastName" /></td>
                                        </tr>
                                        <tr>
                                            <td>* First Name:</td>
                                            <td><html:text property="orderForFirstName" /></td>
                                        </tr>
                                        <tr>
                                            <td>* Email address:</td>
                                            <td><html:text property="orderForEmail" /></td>
                                        </tr>
                                        <tr>
                                            <td>* Select Order Type</td>
                                            <td>
                                                <logic:iterate id="choice" name="choices">
                                                    <html:radio property="orderTypeChoice" idName="choice" value="value"/>
                                                    <bean:write name="choice" property="label"/>
                                                    <br>
                                                </logic:iterate>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>* Order Amount:</td>
                                            <td><html:text property="orderAmount" /></td>
                                        </tr>
                                        <tr>
                                            <td>* Order Item:</td>
                                            <td><html:text property="orderItem" /> (The actual URL)</td>
                                        </tr>
                                        <tr>
                                            <td>* Order Description:</td>
                                            <td><html:text property="orderDesc" /> This is the description of the (functionality of the site) order being placed</td>
                                        </tr>
                                        <tr>
                                            <td>Select Searchkeys to Purchase</td>
                                            <td>
                                                <logic:iterate id="keyitem" property="keyitems" name="AdminWSDSForm">
                                                    <html:multibox property="selectedKeyItems">
                                                        <bean:write name="keyitem"/>
                                                    </html:multibox>
                                                    <bean:write name="keyitem"/>
                                                </logic:iterate>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td>* Select PayType</td>
                                            <td>
                                                <logic:iterate id="item" name="items">
                                                    <html:radio property="payTypeChoice" idName="item" value="value"/>
                                                    <bean:write name="item" property="label"/>
                                                    <br>
                                                </logic:iterate>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Order Notes:</td>
                                            <td><html:text property="orderNotes" /> (Notes by employee) </td>
                                        </tr>
                                        <tr>
                                            <td align="center" colspan="2">
                                                <html:submit value="PlaceOrder" onclick="submitForm()" />
                                            </td>
                                        </tr>
                                    </table>

                                </html:form>
                                <% }%>

                                <% if (WSDSConstants.DISP_CUST_ORDERS.equals(request.getAttribute(WSDSConstants.OPERATION))) {%>

                                <html:form action="AdminWSDSAction.do?operation=dispOrdersForCustomer">
                                    <table class="dataTable">
                                   <%-- <table border="=1" align="left" cellpadding="1" cellspacing="1"
                                                bgcolor=#FFFFFF class="tblwidth700 tblborder"> --%>
                                        <tr>
                                            <th colspan="2" align="center">Customer and Orders</th>
                                        </tr>
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
                                            <td align="center" colspan="2">
                                                <html:submit value="ShowOrders" onclick="submitShowOrder()" />
                                            </td>
                                        </tr>
                                    </table>
                                </html:form>
                                <% }%>

                                <% if (WSDSConstants.SHOW_CUST_ORDERS.equals(request.getAttribute(WSDSConstants.OPERATION))
                                       || WSDSConstants.CANCEL_CUST_ORDER.equals(request.getAttribute(WSDSConstants.OPERATION))
                                       || WSDSConstants.UPDATE_ORDER_STATUS.equals(request.getAttribute(WSDSConstants.OPERATION)) ) {%>
                                <html:form action="AdminWSDSAction.do?operation=showOrdersForCustomer">
                                    <b>Customer Name: <bean:write name="AdminWSDSForm" property="custOfInterestFullName"/> </b>

                                    <logic:notEmpty name="AdminWSDSForm" property="<%=WSDSConstants.ORDER_LIST%>">
                                        <table width="50%" class="dataTable">
                                            <tr class="dataTable th.r">
                                                <th class="dataTable th">Order Number</th>
                                                <th class="dataTable th">Item</th>
                                                <th class="dataTable th">Description</th>
                                                <th class="dataTable th">Quantity</th>
                                                <th class="dataTable th">Status</th>
                                                <th class="dataTable th">Cancel Order</th>
                                            </tr>
                                            <logic:iterate id="ordersObj" property="orderList" name="AdminWSDSForm" indexId="currOrderId">

                                                <tr class="dataTable th.r">
                                                    <td class="dataTable th.c dataTable tr.odd">
                                                        <input type="hidden" id="orderId<bean:write name="currOrderId"/>"
                                                         name="ordersObj[<bean:write name="currOrderId" />].customerID"
                                                         value="<bean:write name="ordersObj" property="customerID"/>" >

                                                        <html:text name="ordersObj" property="orderID" indexed="true" readonly="true"/>
                                                    </td>
                                                    <td class="dataTable th.c dataTable tr.even">
                                                        <html:text name="ordersObj" property="orderItem" indexed="true" readonly="true"/>
                                                    </td>
                                                    <td class="dataTable th.c dataTable tr.odd">
                                                        <html:text name="ordersObj" property="orderDesc" indexed="true" readonly="true"/>
                                                    </td>
                                                    <td class="dataTable th.c dataTable tr.even">
                                                        <html:text name="ordersObj" property="orderAmount" indexed="true" readonly="true"/>
                                                    </td>
                                                    <td class="dataTable th.c dataTable tr.odd">
                                                       <select id="sel<bean:write name="currOrderId"/>" name="ordersObj[<bean:write name="currOrderId" />].orderStatus">
                                                            <OPTION value="1" <logic:match value="1" name="ordersObj" property="orderStatus"> selected </logic:match> ><%=WSDSConstants.ORDER_PLACED_STR%></OPTION>
                                                            <OPTION value="2" <logic:match value="2" name="ordersObj" property="orderStatus"> selected </logic:match> ><%=WSDSConstants.ORDER_DELIVERED_STR%></OPTION>
                                                       </select> 
                                                    </td>
                                                    <td class="dataTable th.c dataTable tr.even">
                                                        <html:link forward="cancelThisOrderForCustomer" paramId="orderID" paramName="ordersObj" paramProperty="orderID"
                                                                   onclick="return isCancel('Order')">
                                                            Cancel Order
                                                        </html:link>
                                                    </td>
                                                </tr>
                                            </logic:iterate>
                                            <tr>
                                              <td align="center" colspan="6">
                                               
                                                <html:submit value="SaveStatus" onclick="submitUpdateOrderStatus()" />
                                              </td>
                                            </tr>
                                        </table>
                                    </logic:notEmpty>

                                    <logic:empty name="AdminWSDSForm" property="<%=WSDSConstants.ORDER_LIST%>">
                                        <br>
                                        <b>No Active orders for this customer</b>
                                        <br>
                                    </logic:empty>

                                    <logic:notEmpty name="AdminWSDSForm" property="<%=WSDSConstants.D_ORDER_LIST%>">
                                       <h3 style="color:black; text-transform:capitalize; position: relative; left: -20px;">Completed Orders</h3>
                                        <display:table name="dorderList" id="row"
                                                   class="dataTable" defaultsort="1"
                                                   defaultorder="ascending" requestURI="/AdminWSDSAction">
                                        <display:caption title="Completed Orders" />
                                        <display:column property="orderID" title="Order Number" />
                                        <display:column property="orderItem" title="Item" />
                                        <display:column property="orderDesc" title="Description" />
                                        <display:column property="orderAmount" title="Quantity" />
                                        <display:column property="OUT_Date" title="Delivered Date" format="{0,date,short}" />
                                        </display:table>
                                    </logic:notEmpty>


                                </html:form>
                                <% }%>

                            </html:form>

                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </body>
</html>
