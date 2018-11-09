package utils;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.dbcp.dbcp.DelegatingConnection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Locale;

public class dbFunction {
    protected final Log logger = LogFactory.getLog(getClass());

    public Connection connectDB() throws Exception {
        Locale oldLocale =  Locale.getDefault();
        boolean is = oldLocale.equals(Locale.ENGLISH);
        if (!is){
            Locale.setDefault(Locale.ENGLISH);
        }
        Locale.setDefault(Locale.ENGLISH);
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/UltEmp");
        Connection connection = ds.getConnection();
        if (connection != null) {
        //logger.info("DB CONNECTED");
            connection.setAutoCommit(false);
        } else {
//            logger.info("NOT CONNECTED");
            throw new Exception("getConnection() Connection is null ");
        }

        return connection;
    }//ConnectDB

    public String encodePass(String orig){
    byte[] encoded = Base64.encodeBase64(orig.getBytes());
    String res = new String(encoded);
    return res;
  }
    public String decodePass(String encVal){
    byte[] decoded = Base64.decodeBase64(encVal.getBytes());
    String res = new String(decoded);
    return res;
  }
    
    public static void close(ResultSet rs, PreparedStatement ps, Connection c) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (c != null) {
            c.close();
        }
    }
    public static void close(PreparedStatement ps) throws SQLException {
        if (ps != null) {
            ps.close();
        }
    }
    public static void close(ResultSet rs, CallableStatement cs, Connection c) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (cs != null) {
            cs.close();
        }
        if (c != null) {
            c.close();
        }
    }
    public static void close(ResultSet rs, PreparedStatement ps) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
    }   
    public static void close(Connection c) throws SQLException {
        if (c != null) {
            c.close();
        }
    }   
    public static void close(PreparedStatement ps, Connection c) throws SQLException {
        if (ps != null) {
            ps.close();
        }
        if (c != null) {
            c.close();
        }
    }
    public static void close(CallableStatement callableStatement, Connection connection) throws SQLException {
        if (callableStatement != null) {
            callableStatement.close();
        }

        if (connection != null) {
            connection.close();
        }
    }
    public static void close(CallableStatement callableStatement) throws SQLException {
        if (callableStatement != null) {
            callableStatement.close();
        }
    }

    public Long makeInsertDB(Connection con,String tableName,String[] fields,String[] param,int [] typ) throws Exception {
        Long res= Long.parseLong("-1");
        CallableStatement pst =null;
        OracleConnection ocon = null;
        try{
            ocon = (OracleConnection) ((DelegatingConnection) con).getInnermostDelegate();
            ArrayDescriptor descr= ArrayDescriptor.createDescriptor("CULTTREASREGISTER_COMMON.ARRAY_1D", ocon);
            pst =  con.prepareCall(" { ?= call CULTTREASREGISTER_COMMON.MAIN_FUNCTIONS.INSERT_TO_DB(?,?,?,?) }");
            pst.registerOutParameter(1, OracleTypes.NUMBER);
            ARRAY aa = new ARRAY(descr, ocon, fields);
            ARRAY a1 = new ARRAY(descr, ocon, param);
            ARRAY a2 = new ARRAY(descr, ocon, typ);
            pst.setString(2, tableName);
            pst.setArray(3, aa);
            pst.setArray(4, a1);
            pst.setArray(5, a2);
            pst.execute();
            res = pst.getLong(1);
            con.commit();
        }catch(Exception ex){
            ex.printStackTrace();
            logger.info("errorrrrrrrrrrrrrrrrr = "+ex.toString());
            if(pst !=null){
                pst.close();
            }
            if(ocon !=null){
                ocon.close();
            }

        }finally{
            if(pst !=null){
                pst.close();
            }
        }
        return res;
    }
    public Long makeUpdateDB(Connection con,String tableName,String[] fields,String[] param,int [] typ,String whFieldValue) throws Exception {
        Long res= Long.parseLong("-1");
        CallableStatement pst =null;
        OracleConnection ocon = null;
        try{
            ocon = (OracleConnection) ((DelegatingConnection) con).getInnermostDelegate();
            ArrayDescriptor descr= ArrayDescriptor.createDescriptor("CULTTREASREGISTER_COMMON.ARRAY_1D", ocon);
            pst =  con.prepareCall(" { ?= call CULTTREASREGISTER_COMMON.MAIN_FUNCTIONS.UPDATE_IN_DB(?,?,?,?,?) }");
            pst.registerOutParameter(1, OracleTypes.NUMBER);
            ARRAY aa = new ARRAY(descr, ocon, fields);
            ARRAY a1 = new ARRAY(descr, ocon, param);
            ARRAY a2 = new ARRAY(descr, ocon, typ);
            pst.setString(2, tableName);
            pst.setArray(3, aa);
            pst.setArray(4, a1);
            pst.setArray(5, a2);
            pst.setString(6, whFieldValue);
            pst.execute();
            res = pst.getLong(1);
            con.commit();
        }catch(Exception ex){    ex.printStackTrace();
            logger.info(" Update Infos ex = "+ex.toString());
            if(pst !=null){
                pst.close();
            }
            if(ocon !=null){
                ocon.close();
            }
        }finally{
            if(pst !=null){
                pst.close();
            }
        }
        return res;
    }


}
