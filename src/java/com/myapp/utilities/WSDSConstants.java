/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.utilities;


/**
 *
 * @author Owner
 */
public interface WSDSConstants {

    public final static String OPERATION="operation";
    public final static String USERLOGIN="userlogin";
    public final static String USERFIRSTNAME="userfirst_name";
    public final static String DEFAULT_DATE_STR="01/01/8098";
    public final static long ADMIN_USER=8098;
    public final static int CUSTOMER_LOGIN=2;
    public final static int EMP_LOGIN=1;
    public final static int INVALID_LOGIN=0;
    public final static String SPECIFIC_ORDER="orderID";

    //JSP Foward constants
    public final static String SHOW_SERVICE_ORDER_PAGE="customerServiceOrder";
    public final static String SHOW_WSDS_SERVICES="showWSDSServices";
    public final static String SHOW_ORDER="showOrder";

    public final static String SHOW_ADMIN_WSDSVIEW="WSDSAdminView";
    public final static String SHOW_ALL_WSDSSERVICES="showAllWSDSServices";
    public final static String DISPLAY_NEW_CUSTFORM="displayNewCustForm";
    public final static String ADD_NEW_CUSTOMER="addNewCustomer";
    public final static String DISPLAY_NEWORDER_FORM="displayNewOrderForm";
    public final static String PLACE_NEW_ORDERFORCUST="PlaceOrder";   //placeNewOrderForCustomer";
    public final static String DISP_CUST_ORDERS="dispOrdersForCustomer";
    public final static String SHOW_CUST_ORDERS="showOrdersForCustomer";
    public final static String CANCEL_CUST_ORDER="cancelThisOrderForCustomer";
    public final static String DISP_CUSTORDER_STATUS="dispCustomerOrderStatus";
    public final static String UPDATE_ORDER_STATUS="updateOrderStatus";
    
    public final static String REGISTER_NEWCUSTOMER="registerNewCustomer";
    public final static String LOGIN_OPERATION="logIn";

    //PaymentDVO Constants
    public final static String PAYSTATUS_PAID="1";
    public final static String PAYSTATUS_NOTPAID="0";
    public final static String PAYSTATUS_REFUNDED="2";

    //PayTypeDVO constants
    public final static String PAYTYPE_CHQ="CHEQUE";
    public final static String PAYTYPE_CASH="CASH";
    public final static String PAYTYPE_CREDITCARD="CREDIT CARD";
    public final static String PAYTYPE_PAYPAL="PAYPAL";

    //OrderDVO Constants
    public final static String ORDER_STATUS_PLACED="1"; //Already paid for and payment status is "ORDER_STATUS_PAID"
    public final static String ORDER_STATUS_UPDATED="3"; //Only search keys can be updated in the already placed order
    public final static String ORDER_STATUS_DELIVERED="2";
    public final static String ORDER_STATUS_CANCELLED="4";
    public final static String ORDER_PLACED_STR="ACTIVE";
    public final static String ORDER_DELIVERED_STR="DELIVERED";
    public final static String ORDER_CANCELLED_STR="CANCELLED";

    //EmployeeDVO constants
    public final static String EMP_STATUS_ACTIVE="1";
    public final static String EMP_STATUS_INACTIVE="0";
    public final static String EMP_LOGINID_PREFIX="001";

    //Database connection constants
    public final static String DBName="WSDS";
    public final static String DBPath="C://Sangeetha//edu//ObjectDB//WSDSdb//";
    public final static String DBExtn=".odb";
    public final static String DBFullPath="C://Sangeetha//edu//ObjectDB//WSDSdb//WSDS.odb";

    //Database constants
    public final static String CUSTOMER_TB="CustomerDVO";
    public final static String EMPLOYEE_TB="EmployeeDVO";
    public final static String ORDER_TB="OrderDVO";
    public final static String PAYMENT_TB="PaymentDVO";
    public final static String PAYTYPE_TB="PayTypeDVO";
    public final static String SEARCHKEY_TB="SearchkeyDVO";
    public final static String ORDERTYPE_TB="OrderTypeDVO";
    public final static String SEARCHKEYORDERTYPEXREF_TB="SearchkeyOrderTypeXRefDVO";
    public final static String ORDERTYPEXREF_TB="OrderTypeXRefDVO";
    public final static String ACCESSMANAGER_TB="AccessManagerDVO";

    //OrderTypes available
    public final static String ORDER_TYPES_LIST="orderTypeList";
    //Searkeys available
    public final static String SEARCH_KEYS_LIST="searchkeyList";

    //Customer constants

    //Employee constants

    //AdminWSDSForm and ViewCustomerOrderForm order constants
    public final static String ORDER_LIST="orderList";
    public final static String D_ORDER_LIST="dorderList";
    
}
