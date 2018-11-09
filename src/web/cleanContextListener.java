
package web;

import dao.*;
import domain.*;
import service.*;

import javax.naming.Context;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import domain.Operation;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class cleanContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        userDao uDao = new userDaoImpl();
        UserService uSer = new UserServiceImpl();
        Map<Long , Operation> operationMap = new HashMap<Long, Operation>();
        Map<Long , Module> moduleMap = new HashMap<Long, Module>();
        Map<Long , Resource> langMap = new HashMap<Long, Resource>();
        Map<Long , String> constantsMap = new HashMap<Long, String>();

        //Map<Long , User> activeUserMap= new HashMap<Long, User>();

        try {

       //     operationMap = uSer.getOperationMap();
          //  moduleMap = uSer.getModuleMap();
            langMap = uSer.getResources();
            constantsMap = uSer.getConstants();
        } catch (Exception e) {
            e.printStackTrace();
        }

     //   sce.getServletContext().setAttribute( constant.OPERATION_BUTTONS , operationMap );
      //  sce.getServletContext().setAttribute( constant.MODULS , moduleMap );
        sce.getServletContext().setAttribute( constant.RESOURCES_LOCALE , langMap );
        sce.getServletContext().setAttribute( constant.CONSTANTS , constantsMap );
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Logger logger = Logger.getLogger("CleanupContextListener");
        // On Application Shutdown, please…

        // 1. Go fetch that DataSource
        Context initContext;
//        try {
//            initContext = new InitialContext();
//            Context envContext  = (Context)initContext.lookup("java:/comp/env/jdbc/PENT");
////            DataSource datasource = (DataSource)envContext.lookup("jdbc/database");
//
//            // 2. Deregister Driver
//            try {
//                java.sql.Driver mySqlDriver = DriverManager.getDriver("jdbc:mysql://localhost:3306/");
//                DriverManager.deregisterDriver(mySqlDriver);
//            } catch (SQLException ex) {
//                logger.info("Could not deregister driver:".concat(ex.getMessage()));
//            }
//
//            // 3. For added safety, remove the reference to dataSource for GC to enjoy.
////            datasource = null;
//            logger.info("deregister driver:");
//        } catch (NamingException ex) {
//            Logger.getLogger(cleanContextListener.class.getName()).log(Level.SEVERE, null, ex);
//        }

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            ClassLoader driverclassLoader = driver.getClass().getClassLoader();
            ClassLoader thisClassLoader = this.getClass().getClassLoader();
            if (driverclassLoader != null && thisClassLoader != null &&  driverclassLoader.equals(thisClassLoader)) {
                try {
                    logger.log(Level.INFO, "Deregistering: {0}", driver);
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
