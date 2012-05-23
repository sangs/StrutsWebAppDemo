/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.DVO;

import com.myapp.utilities.*;
import javax.persistence.*;
import java.util.*;
import static com.myapp.utilities.WSDSConstants.*;

import java.util.*;

/**
 *
 * @author Owner
 */
public class WSDS {

private CustomerDVO data;
private ArrayList<OrderDVO> ordersList;
private Customer thisCustomer;
private WSDSUtilities wsdsUtil;
//private static Random randomGenerator = null;

    public WSDS() {
        wsdsUtil = new WSDSUtilities();
    }

    public boolean registerNewCustomer(CustomerDVO cData) {
        boolean ret = false;

        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        ret = generateLoginPwd(cData);

        try {
            eMgr.getTransaction().begin();
            eMgr.persist(cData);
            eMgr.getTransaction().commit();
            ret = true;
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
        finally {
            //Close the PersistenceManager
            eMgr.close();
            emf.close();
        }
        return ret;
    }

    public int logIn(String name, String cred) {
        boolean ret = false;
        int valid = WSDSConstants.INVALID_LOGIN;

        if( isCustomer(name) ) {
            thisCustomer = new Customer();
            if ( (ret = thisCustomer.validateCustomer(name, cred)) == true )
                valid = WSDSConstants.CUSTOMER_LOGIN;
        }
        else if ( isEmployee(name) ) {
            if ( (ret = validateEmployee(name, cred)) == true )
                valid = WSDSConstants.EMP_LOGIN;
        }
        return valid;
    }

    public String getUserFirstName(int loginType, String loginName) {
        String firstName = null;
        if( loginType == CUSTOMER_LOGIN ) {
            thisCustomer = new Customer();
            firstName = thisCustomer.getCustomerFirstName(loginName);
        }
        else if ( loginType == EMP_LOGIN ) {
            firstName = getEmployeeFirstName(loginName);
        }

        return firstName;
    }

    public ArrayList<OrderTypeDVO> getServicesOffered() {
        ArrayList<OrderTypeDVO> orderTypeLst = null;
        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        // Retrieve all the OrderTypeDVO objects from the database:
        try {
            TypedQuery<OrderTypeDVO> query =
                                    eMgr.createQuery("SELECT ordT FROM OrderTypeDVO ordT", OrderTypeDVO.class);
            orderTypeLst = (ArrayList<OrderTypeDVO>)query.getResultList();
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
        finally {
             eMgr.close();
             emf.close();
        }

        return orderTypeLst;
    }

    public ArrayList<SearchkeyDVO> getSearchKeyInfo() {
        ArrayList<SearchkeyDVO> skList = null;

        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        // Retrieve all the SearchKeyDVO objects from the database:
        try {
            TypedQuery<SearchkeyDVO> query =
                                    eMgr.createQuery("SELECT sk FROM SearchkeyDVO sk", SearchkeyDVO.class);
            skList = (ArrayList<SearchkeyDVO>)query.getResultList();
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
        finally {
             eMgr.close();
             emf.close();
        }

        return skList;
    }

    public long getCustomerID(String fName, String lName, String email) {
        long id = 0;
        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        try {
             TypedQuery<CustomerDVO> cQuery = eMgr.createQuery("SELECT c FROM CustomerDVO AS c"
                                                    + " where c.firstName= :firstName AND"
                                                    + " c.lastName = :lastName AND"
                                                    + " c.email= :email", CustomerDVO.class);
            cQuery.setParameter("firstName", fName);
            cQuery.setParameter("lastName", lName);
            cQuery.setParameter("email", email);
            CustomerDVO cust = cQuery.getSingleResult();
            id = cust.getCustomerID();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return id;
    }

    public long getCustomerIDOfOrder(long orderID) {
        long id = 0;
        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        try {
            TypedQuery<OrderDVO> cQuery = eMgr.createQuery("SELECT od FROM OrderDVO AS od"
                                                    + " where od.orderID= :orderID", OrderDVO.class);
            cQuery.setParameter("orderID", orderID);
            OrderDVO order = cQuery.getSingleResult();
            id = order.getCustomerID();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }
        return id;
    }

    public Customer placeOrder (long customerID, OrderDVO orderToPlace, String orderType, String payType, List<String> keysPurchased) {
        thisCustomer = null;
        boolean ret = false;
        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();
    
        try {
            //Place order (includes make payment and xref tables update)
            if ( (ret = placeOrder(eMgr, orderType, orderToPlace, payType, keysPurchased)) == true ) {

                if ( (data = getCustomerData(customerID)) != null ) {
                    thisCustomer = new Customer();
                    thisCustomer.setData(data);
                    //TODO: Add this order to the orderlist for this customer before returning
                }
            }
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
        finally {
             eMgr.close();
             emf.close();
        }
        
        //TODO: In case of any failure rollback the transactions
      
        return thisCustomer;
    }

    public Customer getCustomerOrders (String fName, String lName, String email) {
        //Creates Customer object that has both customer data and all orders placed by the customer
        //Get the particular customer
        data = getCustomerData(fName, lName, email);
        thisCustomer = getThisCustomerWithOrders(data);

        return thisCustomer;
    }

    public Customer getCustomerOrders (String userLogIn) {
        //String customerName = null;
        data = getCustomerData(userLogIn);
        thisCustomer = getThisCustomerWithOrders(data);
        return thisCustomer;
    }

    public Customer getCustomerOrders(long customerID) {
      data = getCustomerData(customerID);
      thisCustomer = getThisCustomerWithOrders(data);
      return thisCustomer;
    }

    public boolean cancelThisOrderForCustomer(long orderToCancel) {
        boolean ret = false;
        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        try {
            TypedQuery<OrderDVO> cQuery = eMgr.createQuery("SELECT od FROM OrderDVO AS od"
                                                    + " where od.orderID= :orderIDToCancel", OrderDVO.class);
            cQuery.setParameter("orderIDToCancel", orderToCancel);
            OrderDVO orDVO = cQuery.getSingleResult();
            eMgr.getTransaction().begin();
            eMgr.remove(orDVO);
            eMgr.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
        finally {
            eMgr.close();
            emf.close();
        }
        ret = true;
        return ret;
    }

    public boolean updateOrderStatus (OrderDVO orderToUpdate) {
        boolean ret = false;
        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();
        long orderID = orderToUpdate.getOrderID();
        String ordStatus = orderToUpdate.getOrderStatus();
        Date out_date = Calendar.getInstance().getTime();
        
        try {
            TypedQuery<OrderDVO> cQuery = eMgr.createQuery("SELECT od FROM OrderDVO AS od"
                                                    + " where od.orderID= :orderID", OrderDVO.class);
            cQuery.setParameter("orderID", orderID);
            OrderDVO ordDVO = cQuery.getSingleResult();
            eMgr.getTransaction().begin();
            ordDVO.setOrderStatus(ordStatus);
            ordDVO.setOUT_Date(out_date);
            eMgr.getTransaction().commit();
        }
        catch (Exception e) {
            System.out.println (e.getMessage());
        }
        finally {
            eMgr.close();
            emf.close();
        }
        ret = true;
        
        
        return ret;
    }


    ////// Private Methods /////////

    private boolean isCustomer (String name) {
        boolean res = false;

        String str = name.substring(0, 3);
        if( str.compareTo(WSDSConstants.EMP_LOGINID_PREFIX) != 0 )
            res = true;
        return res;
    }

    private boolean isEmployee (String name) {
        boolean res = false;

        String str = name.substring(0, 3);
        if( str.compareTo(WSDSConstants.EMP_LOGINID_PREFIX) == 0 )
            res = true;
        return res;
    }

     private boolean validateEmployee (String name, String cred) {
        boolean valid = false;

        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();
        String matchedPwd = null;

        try {
            TypedQuery<String> cQuery = eMgr.createQuery("SELECT e.pwd FROM EmployeeDVO AS e"
                                                    + " where e.loginID= :loginName", String.class);
            cQuery.setParameter("loginName", name);
            matchedPwd = cQuery.getSingleResult();
            if( matchedPwd.compareTo(cred) == 0 )
                valid = true;
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return valid;
    }

    private Customer getThisCustomerWithOrders(CustomerDVO data) {
      String customerName = null;
      ArrayList<OrderDVO> dordersList = null;
      ArrayList<OrderDVO> aordersList = null;

      if (data != null) {
        //Get orders placed by this customer
        aordersList = getActiveOrdersForCustomer(data);
        dordersList = getDeliveredOrdersForCustomer(data);
       
        customerName = (data.getLastName()).concat(" ").concat(data.getFirstName());
        thisCustomer = new Customer();
        thisCustomer.setData(data);
        thisCustomer.setOrdersList(aordersList);
        thisCustomer.setDordersList(dordersList);
        thisCustomer.setFullName(customerName);
      
      }

      return thisCustomer;
    }

     private boolean generateLoginPwd(CustomerDVO data) {
        boolean ret = true;
        int idx = -1;
        String lpwd = null;
        String part1 = null;
        int intPart = (WSDSUtilities.getRandomGenerator()).nextInt();
        String part2 = Integer.toString(intPart);
        String part = data.getEmail();

        if ( (part2 != null) && (part != null) && ( (idx = part.indexOf("@")) != -1 ) ) {
            part1 = (idx > 4) ? (part.substring(0, 4)) : (part.substring(0, idx));
            lpwd = (part1 != null) ? part1.concat(part2) : null;
            if (lpwd != null) {
                data.setLoginID(part);
                data.setPwd(lpwd);
            }
        }
        ret = (lpwd != null) ? true : false;
        return ret;
    }


    private long getOrderTypeID(String orderType) {
        long id = 0;
        Long idObject = null;

        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        try {
            TypedQuery<OrderTypeDVO> cQuery = eMgr.createQuery("SELECT odT FROM OrderTypeDVO AS odT"
                                                    + " where odT.type= :ordertype", OrderTypeDVO.class);
            cQuery.setParameter("ordertype", orderType);
            OrderTypeDVO otDVO = cQuery.getSingleResult();
            id = otDVO.getOrderTypeID();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return id;
    }

    public CustomerDVO getCustomerData(long id) {
        CustomerDVO cdata = null;

        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        try {
            TypedQuery<CustomerDVO> cQuery = eMgr.createQuery("SELECT c FROM CustomerDVO as c"
                                                                + " where c.customerID= :id", CustomerDVO.class);
            cQuery.setParameter("id", id);
            cdata = cQuery.getSingleResult();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return cdata;
    }

    private CustomerDVO getCustomerData(String fName, String lName, String email) {
        CustomerDVO cdata = null;

        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        try {
            TypedQuery<CustomerDVO> cQuery = eMgr.createQuery("SELECT c FROM CustomerDVO AS c"
                                                    + " where c.firstName= :firstName AND"
                                                    + " c.lastName = :lastName AND"
                                                    + " c.email= :email", CustomerDVO.class);
            cQuery.setParameter("firstName", fName);
            cQuery.setParameter("lastName", lName);
            cQuery.setParameter("email", email);
            cdata = cQuery.getSingleResult();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return cdata;
    }

    private CustomerDVO getCustomerData (String userLogin) {
        CustomerDVO cdata = null;

        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        try {
            TypedQuery<CustomerDVO> cQuery = eMgr.createQuery("SELECT c FROM CustomerDVO AS c"
                                                    + " where c.loginID= :userLogin", CustomerDVO.class);
            cQuery.setParameter("userLogin", userLogin);
            cdata = cQuery.getSingleResult();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return cdata;
    }

    private ArrayList<OrderDVO> getOrdersForCustomer(CustomerDVO cust) {
        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();
        long custID = cust.getCustomerID();

        try {
            TypedQuery<OrderDVO> cQuery = eMgr.createQuery("SELECT ord FROM OrderDVO AS ord"
                                                    + " where ord.customerID = :custID", OrderDVO.class);
            cQuery.setParameter("custID", custID);
            ordersList = (ArrayList<OrderDVO>)cQuery.getResultList();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return ordersList;
    }

    private ArrayList<OrderDVO> getActiveOrdersForCustomer(CustomerDVO cust) {
        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();
        long custID = cust.getCustomerID();
        String status = WSDSConstants.ORDER_STATUS_PLACED;

        try {
            TypedQuery<OrderDVO> cQuery = eMgr.createQuery("SELECT ord FROM OrderDVO AS ord"
                                                    + " where ord.customerID = :custID AND"
                                                    + " ord.orderStatus = :status", OrderDVO.class);
            cQuery.setParameter("custID", custID);
            cQuery.setParameter("status", status);
            ordersList = (ArrayList<OrderDVO>)cQuery.getResultList();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return ordersList;
    }

    private ArrayList<OrderDVO> getDeliveredOrdersForCustomer(CustomerDVO cust) {
        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();
        long custID = cust.getCustomerID();
        String status = WSDSConstants.ORDER_STATUS_DELIVERED;

        try {
            TypedQuery<OrderDVO> cQuery = eMgr.createQuery("SELECT ord FROM OrderDVO AS ord"
                                                    + " where ord.customerID = :custID AND"
                                                    + " ord.orderStatus = :status", OrderDVO.class);
            cQuery.setParameter("custID", custID);
            cQuery.setParameter("status", status);
            ordersList = (ArrayList<OrderDVO>)cQuery.getResultList();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return ordersList;
    }

    private boolean placeOrder(EntityManager em, String orderType, OrderDVO order, 
                                String payType, List<String> keysPurchased) throws Exception {
        boolean ret = false;
        long orderId = order.getOrderID();
        double amount = 0;
        double kAmount = 0;
        long orderTypeID = 0;
        ArrayList<Long> skeyIdList;
        SearchkeyOrderTypeXRefDVO skeyOrderTypeXRef = null;

        //Place Order
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();

        //Get OrderTypeID this order belongs to
        orderTypeID = getOrderTypeID(orderType);

        //Get SearchkeyID's for the keysPurchased
        skeyIdList = getSearchkeyIDS(keysPurchased);

        //Get totalcost of the order placed = cost of keys purchased + cost of order
        kAmount = getCostOfSKeys(em, skeyIdList);
        amount = getCostOfOrder(em, orderType);
        amount += kAmount;

        //Make payment for this order
        ret = makePayment(em, orderId, payType, amount);
        
        if (ret) {
            //Entry for OrderTypeXRef with the now placed order's ID
            OrderTypeXRefDVO otXRef = new OrderTypeXRefDVO(orderId, orderTypeID);
            em.getTransaction().begin();
            em.persist(otXRef);
            em.getTransaction().commit();

             //Entry for SearchkeyOrderTypeXRef with the now placed order's ID
            for (Long skId : skeyIdList) {
                long id = skId.longValue();
                skeyOrderTypeXRef = new SearchkeyOrderTypeXRefDVO(id, orderTypeID, orderId);

                em.getTransaction().begin();
                em.persist(skeyOrderTypeXRef);
                em.getTransaction().commit();
            }
        }
        return ret;
    }

     private ArrayList<Long> getSearchkeyIDS(List<String> keys) {
        ArrayList<Long> ids = new ArrayList<Long>();
        Long idObject = null;

        EntityManagerFactory emf = wsdsUtil.getEMF();
        EntityManager eMgr = emf.createEntityManager();

        try {
            for (String str : keys) {
                TypedQuery<SearchkeyDVO> cQuery = eMgr.createQuery("SELECT sk FROM SearchkeyDVO AS sk"
                                                    + " where sk.searchkey= :searchkey", SearchkeyDVO.class);
                cQuery.setParameter("searchkey", str);
                SearchkeyDVO sdvo = cQuery.getSingleResult();
                long skid = sdvo.getSearchkeyID();
                idObject = Long.valueOf(skid);
                ids.add(idObject);
            }
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }

        return ids;
    }

    private double getCostOfSKeys(EntityManager em, ArrayList<Long> skIdentifiers) {
        ArrayList<Long> skIds = skIdentifiers;
        double amount = 0;
        double skCost = 0;

        for (Long skId : skIds) {
          TypedQuery<SearchkeyDVO> cQuery = em.createQuery("SELECT sk FROM SearchkeyDVO AS sk"
                                                    + " where sk.searchkeyID= :searchkeyID", SearchkeyDVO.class);
          cQuery.setParameter("searchkeyID", skId);
          SearchkeyDVO sdvo = cQuery.getSingleResult();
          skCost = sdvo.getCost();
          amount += skCost;
       }
      
       return amount;
    }

    private double getCostOfOrder(EntityManager em, String orderType) throws Exception {
        double id = 0;

        TypedQuery<OrderTypeDVO> cQuery = em.createQuery("SELECT ot FROM OrderTypeDVO AS ot"
                                                    + " where ot.type= :ordertype", OrderTypeDVO.class);
        cQuery.setParameter("ordertype", orderType);
        OrderTypeDVO otDVO = cQuery.getSingleResult();
        id = otDVO.getCost();

        return id;
    }

    private boolean makePayment(EntityManager em, long orderId, String payType, double amount) throws Exception {
        boolean ret = false;
        long payTypeID = getPayTypeID(em, payType);

        PaymentDVO payment = new PaymentDVO(orderId, payTypeID, amount, WSDSConstants.PAYSTATUS_PAID);
        em.getTransaction().begin();
        em.persist(payment);
        em.getTransaction().commit();

        ret = true;
        return ret;
    }

    private long getPayTypeID(EntityManager em, String payType) throws Exception {
        long id = 0;

        TypedQuery<PayTypeDVO> cQuery = em.createQuery("SELECT pt FROM PayTypeDVO AS pt"
                                                    + " where pt.type= :paytype", PayTypeDVO.class);
        cQuery.setParameter("paytype", payType);
        PayTypeDVO ptDVO = cQuery.getSingleResult();
        id = ptDVO.getId();

        return id;
    }

    private String getEmployeeFirstName(String loginName) {
      EntityManagerFactory emf = wsdsUtil.getEMF();
      EntityManager eMgr = emf.createEntityManager();
      String firstName = null;

        try {
            TypedQuery<String> cQuery = eMgr.createQuery("SELECT e.firstName FROM EmployeeDVO AS e"
                                                    + " where e.loginID= :loginName", String.class);
            cQuery.setParameter("loginName", loginName);
            firstName = cQuery.getSingleResult();
         }
         catch (Exception e) {
            System.out.println (e.getMessage());
         }
         finally {
             eMgr.close();
             emf.close();
         }
         return firstName;
    }

    


} //class WSDS
