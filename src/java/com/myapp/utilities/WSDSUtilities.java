/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.myapp.utilities;

import javax.persistence.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;


/**
 *
 * @author Owner
 */
public class WSDSUtilities {
    
    private EntityManagerFactory emf;
    public static String REQUESTED_CURR_OPERATION="CURRENT_OPERATION";
    public static Date DEFAULT_DATE;
    private static Random randomGenerator = null;
 
    public WSDSUtilities () {
        emf = null;
    }

    public EntityManagerFactory getEMF(String dbName) {

      //  if (em == null)
        {
            String entityDBPath = (WSDSConstants.DBPath).concat(dbName);
            String entityDB = entityDBPath.concat(WSDSConstants.DBExtn);

            emf = Persistence.createEntityManagerFactory(entityDB);
            //em = emf.createEntityManager();
        }
        return emf;
    }

    public EntityManagerFactory getEMF() {
        //if (em == null)
        {
            emf = Persistence.createEntityManagerFactory(WSDSConstants.DBFullPath);
            //em = emf.createEntityManager();
        }
        return emf;
    }

    public static void setCurrOperation(String currOperation) {
        REQUESTED_CURR_OPERATION=currOperation;
    }

    public static Date getDefaultDate() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
            DEFAULT_DATE = df.parse(WSDSConstants.DEFAULT_DATE_STR);
        }
        catch (ParseException e) {
            System.out.println (e.getMessage());
        }

        return DEFAULT_DATE;
    }

    public static Random getRandomGenerator() {
         if (randomGenerator == null)
            randomGenerator = new Random();

         return randomGenerator;
    }

} //class WSDSUtilities
