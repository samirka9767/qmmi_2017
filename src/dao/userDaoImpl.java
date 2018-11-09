package dao;

import domain.*;
import utils.*;
import oracle.jdbc.OracleTypes;
import utils.dbFunction;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by a.ulviyya on 30.06.2016.
 */
public class userDaoImpl implements userDao {
    protected Connection conn=null;
    protected dbFunction df= new dbFunction();

    ResultSet rs=null;

    @Override
    public usersInfo login(String username,String password) throws Exception {
        usersInfo uInfo =null;
        CallableStatement cst=null;
        try {
            conn = df.connectDB();

            cst = conn.prepareCall("{?= call CULTTREASREGISTER_COMMON.SECURITY_FUNCTIONS.LOGIN(?,?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setString(2, username.toLowerCase());
            cst.setString(3, password.toLowerCase());
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            if (rs.next()) {
                String pass = rs.getString("PASSW");
                String decPas = df.decodePass(pass);
               // if (password == null ? decPas == null : password.equals(decPas)) {
                if (password == null ? pass == null : password.equals(pass)) {
                    uInfo = new usersInfo();
                    uInfo.setId(rs.getInt("USERID"));
                    uInfo.setFullName(rs.getString("FULLNAME"));
                    uInfo.setOrgID(rs.getLong("ORGID"));
                    uInfo.setUserName(username);
                    uInfo.setPswd(rs.getString("PASSW"));
                    uInfo.setUserType(rs.getLong("USER_TYPE"));
                }


            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.print("Error while login dao "+ex.toString());
        }finally{
            dbFunction.close(rs, cst, conn);
        }
        return uInfo;


    }
    @Override
    public List<roles> userAccess(long uID) throws Exception {
        List<roles> rols= new ArrayList<roles>();
        CallableStatement cst = null;
        ResultSet rs = null;
        try{ // encode decode
            conn= df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.SECURITY_FUNCTIONS.USERACCESSMODULOPERATIONS(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, uID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            String menuID="";
            roles rol = new roles();
            List<String> btnl= new ArrayList<String>();
            List<String> btnlID= new ArrayList<String>();
            while(rs.next()){
                if("".equals(menuID)){
                    rol = new roles();
                    menuID=rs.getString("MENUID").trim();
                }
                if(!menuID.equals(rs.getString("MENUID").trim())){
                    rol.setButtons(btnl);
                    rol.setButtonID(btnlID);
                    rols.add(rol);
//                    logger.info("menuID = "+menuID+" // "+rs.getString("MENUID").trim()+" // brnl = "+btnl+" // rols = "+rols);
                    rol = new roles();
                    btnl= new ArrayList<String>();
                    btnlID= new ArrayList<String>();
                    menuID=rs.getString("MENUID").trim();
                }
                btnl.add(rs.getString("BUTTONID"));
                btnlID.add(rs.getString("BID"));
                rol.setMenuID(menuID);
                rol.setLevelID(rs.getString("LEVELID"));
            }
            rol.setButtons(btnl); rol.setButtonID(btnlID);
            rols.add(rol);
//            logger.info("menuID = "+menuID+" // "+rs.getString("MENUID").trim()+" // brnl = "+btnl+" // rols = "+rols);
        }catch(Exception ex){
            System.out.print("Error while gettin userAccessInfo dao "+ex.toString());
        }finally{
            dbFunction.close(rs, cst, conn);
        }
        return rols;
    }


    @Override
    public Map<Long , Resource> getResources() throws Exception {
        Map<Long, Resource> map = new HashMap<Long, Resource>();
        Connection c = null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call CULTTREASREGISTER_COMMON.SECURITY_FUNCTIONS.GET_RESOURCES( ? )}";
        String sql1 = "{call CULTTREASREGISTER_COMMON.SECURITY_FUNCTIONS.GET_RESOURCES_LOCALE( ?, ?,? )}";

        try{
            c = df.connectDB();
            if (c!= null){
                call = c.prepareCall(sql);
                call.registerOutParameter(1, OracleTypes.CURSOR);
                call.execute();
                rs = (ResultSet) call.getObject(1);
                List<Long> resIds = new ArrayList<Long>();
                while (rs.next()){
                    resIds.add( rs.getLong("ID") );
                }
                rs.close();
                call.close();
                for ( long r : resIds ) {
                    call = c.prepareCall(sql1);
                    call.setLong(1, 1);
                    call.setLong(2, r);
                    call.registerOutParameter(3, OracleTypes.CURSOR);

                    call.execute();
                    rs = (ResultSet) call.getObject(3);
                    Resource resource = new Resource();
                    Map<Long,String> stringMap = new HashMap<Long, String>();
                    while (rs.next()){
                        stringMap.put( rs.getLong("LANG_ID") , rs.getString("VALUE"));
                    }
                    resource.setLocaleValues(stringMap);
                    map.put(r,resource);
                    rs.close();
                    call.close();
                }
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            dbFunction.close(rs, call, c);
        }
        return map;
    }

    @Override
    public Map<Long, String> getConstants() throws Exception {
        Map<Long, String> map = new HashMap<Long, String>();
        Connection c = null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql = "{call  CULTTREASREGISTER_COMMON.SECURITY_FUNCTIONS.GET_CONSTANTS(?)}";
        try{
            c = df.connectDB();
            if (c!= null){
                call = c.prepareCall(sql);
                call.registerOutParameter(1, OracleTypes.CURSOR);
                call.execute();
                rs = (ResultSet) call.getObject(1);
                while (rs.next()){
                    map.put( rs.getLong("ID") , rs.getString("VALUE") );
                }
            }else{
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            dbFunction.close(rs, call, c);
        }
        return map;
    }

    @Override
    public usersInfo getUser(Long userId, String userName, String password, Long currLang) throws Exception {
        usersInfo user =null ;
        Connection c= null;
        CallableStatement call = null;
        ResultSet rs = null;
        String sql =   "{call CULTTREASREGISTER_COMMON.SECURITY_FUNCTIONS.GET_USER(?,?,?,?,?,?,?,? )}";
        //+ String sql_1 = "{call EDUMAN_EVALUATION.SECURITY_FUNCTIONS.GET_USER_PRIV(?,?,?)}";
       // String encPas = df.encodePass(password);
        try{
            c =  df.connectDB();
            System.gc();
            if(c!=null) {
                call = c.prepareCall(sql);
                call.setLong(1,userId);
                call.setString(2, userName);
                call.setString(3, password);
                call.setLong(4, currLang);
                call.registerOutParameter(5, OracleTypes.INTEGER);
                call.registerOutParameter(6, OracleTypes.CURSOR);
                call.registerOutParameter(7, OracleTypes.CURSOR);
                call.registerOutParameter(8, OracleTypes.CURSOR);
                call.execute();
                int login = (Integer) call.getObject(5);
                if (login == 1) {
                    rs = (ResultSet) call.getObject(6);
                    while (rs.next()){
                        user = new usersInfo();
                        user.setId(rs.getLong("USERID"));
                        user.setFullName(rs.getString("FULLNAME"));
                        user.setOrgID(rs.getLong("ORGID"));
                        user.setEmpid(rs.getLong("EMPID"));
                        user.setPerid(rs.getLong("PERID"));
                        user.setUserName(userName);
                        user.setPswd(rs.getString("PASSW"));
                        user.setUserType(rs.getLong("USER_TYPE"));
                        user.setCurrLangId(currLang);
                    }

                }else if(login == 0){
                    user.setId(-1L);
                    System.out.println("Access Denied !!");
                }
            }else {
                System.out.println("Connection is null");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            dbFunction.close(rs, call, c);
        }
        return user;
    }

}
