package dao;

import domain.*;
import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import utils.*;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class loadDataFromDBImpl implements loadDataFromDB {

    private Log logger = LogFactory.getLog(getClass());
    protected Connection conn = null;
    protected dbFunction df = new dbFunction();

    @Override
    public Result getGridContent(PagingObject po, String gridType, Map<String, String[]> parameterMap, String searchText, usersInfo loggedUser, String statusID, String from, String where) throws Exception {
        Result content = new Result();
        CallableStatement callableStatement = null;
        ResultSet resultSet = null;

        try {
            int rID1 = (po.getPageNumber() * po.getPageSize()) - po.getPageSize() + 1;
            int rID2 = po.getPageNumber() * po.getPageSize();
            String sIndex = po.getSortIndex();
            String sOrder = po.getSortOrder();
            conn = df.connectDB();
            BigDecimal rowCount = BigDecimal.valueOf(1);
            int page = 1;
            int currentpage = 1;


            String order = "";
            if (!"".equals(sIndex)) {
                order = "ORDER BY NLSSORT( " + sIndex + ", 'NLS_SORT = AZERBAIJANI') " + " " + sOrder;
            } else {
                order = "ORDER BY " + " NLSSORT( ID, 'NLS_SORT = AZERBAIJANI') " + " desc";
            }


            if (Long.parseLong(gridType) == 101 || Long.parseLong(gridType) == 102) {
                callableStatement = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADDICTGRID(?,?,?,?,?,?) }");
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                callableStatement.setLong(5, Long.parseLong(parameterMap.get("gridType")[0]));//GTYPE
                callableStatement.setLong(2, rID1);//FRR1
                callableStatement.setLong(3, rID2);//TOR2
                callableStatement.setString(4, order);//ORDBY
                callableStatement.setLong(6, Long.parseLong(parameterMap.get("lang_id")[0]));//LANGID
                callableStatement.setLong(7, Long.parseLong(parameterMap.get("dicPartId")[0]));//dict_type_id
                callableStatement.execute();
            } else {
                callableStatement = conn.prepareCall("{ call CULTTREASREGISTER_COMMON.LOAD_MAIN_DATA_FUNCTIONS.LOADMAINGRID(?,?,?,? ,?,?,?,?, ?,?,?,?, ?,?,?,?,?,?,?,?) }");
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);//RESULT
                callableStatement.registerOutParameter(2, OracleTypes.DECIMAL);//P_COUNT
                callableStatement.setLong(3, Long.parseLong(parameterMap.get("gridType")[0]));//GTYPE
                callableStatement.setLong(4, rID1);//FRR1
                callableStatement.setLong(5, rID2);//TOR2
                callableStatement.setString(6, order);//ORDBY
                callableStatement.setString(7, searchText);//STEXT
                callableStatement.setLong(8, loggedUser.getId());//USERID
                callableStatement.setLong(9, loggedUser.getOrgID());//USERORGID
                callableStatement.setLong(10, loggedUser.getUserType());//USERTYPE
                callableStatement.setLong(11, Long.parseLong(parameterMap.get("categoryList")[0]));//F_CATID
                callableStatement.setLong(12, Long.parseLong(parameterMap.get("groupList")[0]));//F_GROUP
                callableStatement.setLong(13, Long.parseLong(parameterMap.get("classList")[0]));//F_CLASS
                callableStatement.setLong(14, Long.parseLong(parameterMap.get("mStatusList")[0]));//F_STATUS
                callableStatement.setLong(15, Long.parseLong(parameterMap.get("typeList")[0]));//F_TYPE
                callableStatement.setLong(16, Long.parseLong(parameterMap.get("genreList")[0]));//GEN_ID
                callableStatement.setLong(17, Long.parseLong(parameterMap.get("lang_id")[0]));//LANG_ID
                callableStatement.setString(18, statusID);
                callableStatement.setString(19, from);
                callableStatement.setString(20, where);

                callableStatement.execute();

                rowCount = (BigDecimal) callableStatement.getObject(2);
                content.setPageNumber(Integer.parseInt(parameterMap.get("page")[0]));
                content.setRecordCount((int) Long.parseLong(rowCount.toString()));
                page = Integer.parseInt(parameterMap.get("rows")[0]);
                currentpage = Integer.parseInt(parameterMap.get("page")[0]);
                content.setTotalPageCount(Integer.parseInt(String.valueOf(rowCount)) / page);
            }
            if (Integer.parseInt(String.valueOf(rowCount)) % page > 0) {
                content.setTotalPageCount(1 + content.getTotalPageCount());
            }

            if (content.getTotalPageCount() == 0) {
                content.setTotalPageCount(1);
            }

            if (currentpage > content.getTotalPageCount()) {
                content.setPageNumber(content.getTotalPageCount());
            }
            resultSet = (ResultSet) callableStatement.getObject(1);


            if (resultSet != null)

            {
                ResultSetMetaData rsmd = resultSet.getMetaData();
                int columnCount = rsmd.getColumnCount();
                String[] cols = new String[columnCount];

                for (int i = 0; i < columnCount; i++) {
                    cols[i] = rsmd.getColumnName(i + 1);
                }

                while (resultSet.next()) {
                    Row gp = new Row();
                    List<Column> columnList = new ArrayList<Column>();

                    for (int i = 1; i <= columnCount; i++) {
                        Column column = new Column();
                        column.setKey(cols[i - 1]);
                        column.setValue(resultSet.getString(i));
                        columnList.add(column);
                    }
                    gp.setColumns(columnList);
                    content.add(gp);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            dbFunction.close(resultSet, callableStatement);
            dbFunction.close(conn);
        }
        return content;
    }


    @Override
    public List<listInfo> loadComboForAdvancedSearch(String prmID, long langid) throws Exception {
        List<listInfo> infL = new ArrayList<listInfo>();
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADDICTIONARYBYPRMID(?,?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);

            cst.setLong(2, Long.parseLong(prmID));
            cst.setLong(3, langid);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            while (rs.next()) {
                listInfo inf = new listInfo();
                inf.setId(rs.getLong("ID"));
                inf.setDescription1(rs.getString("FULL_NAME"));
                inf.setDescription2(rs.getString("SHORT_NAME"));
                infL.add(inf);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return infL;
    }


    @Override
    public List<Row> getComboContent(String sType, String comboType, Map<String, String[]> parameterMap, usersInfo
            loggedUser) throws Exception {
        List<Row> params = new ArrayList<Row>();
        String sql = "";
        CallableStatement cst = null;
        ResultSet rs = null;

        try {
            conn = df.connectDB();

            if ("18".equals(sType)) {

                if (comboType != null && comboType.length() > 0 && comboType.charAt(comboType.length() - 1) == ',') {
                    comboType = comboType.substring(0, comboType.length() - 1);
                    String code = "";
                    String[] parts = comboType.split(",");
                    int counter = 0;
                    for( int i=0; i<comboType.length(); i++ ) {
                        if( comboType.charAt(i) == ',' ) {
                            counter++;
                        }
                    }

                    for(int j =0; j <= counter; j++) {
                        String code1 = "";
                        code1 = "C.FORMULA LIKE '%"+parts[j]+"%'";
                        if(j < counter){
                            code = code + code1+" OR ";
                        }else {
                            code = code + code1;
                        }

                    }

                    cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.LOAD_MAIN_DATA_FUNCTIONS.LOADCOMBOCONTENT2(?,?,?,?,?) }");
                    cst.registerOutParameter(1, OracleTypes.CURSOR);
                    cst.setString(2, code);
                    cst.setLong(3, Long.parseLong(sType));
                    cst.setLong(4, Long.parseLong(parameterMap.get("param1")[0]));
                    cst.setLong(5, Long.parseLong(parameterMap.get("param2")[0]));
                    cst.setLong(6, Long.parseLong(parameterMap.get("param3")[0]));
                    cst.execute();
                    rs = (ResultSet) cst.getObject(1);
                }

            } else {
                if ("11".equals(sType)) {
                    cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.LOAD_MAIN_DATA_FUNCTIONS.LOADCOMBOCONTENT2(?,?,?,?,?)}");
                    cst.setString(2, comboType);
                } else if ("8".equals(sType)) {
                    cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.LOAD_MAIN_DATA_FUNCTIONS.LOADCOMBOCONTENT2(?,?,?,?,?)}");
                    String code = "C.FORMULA LIKE '%"+comboType+"%'";
                    cst.setString(2, code);
                }else {
                    cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.LOAD_MAIN_DATA_FUNCTIONS.LOADCOMBOCONTENT(?,?,?,?,?)}");
                    cst.setLong(2, Long.parseLong(comboType));
                }
                cst.registerOutParameter(1, OracleTypes.CURSOR);
                cst.setLong(3, Long.parseLong(sType));
                cst.setLong(4, Long.parseLong(parameterMap.get("param1")[0]));
                cst.setLong(5, Long.parseLong(parameterMap.get("param2")[0]));
                cst.setLong(6, Long.parseLong(parameterMap.get("param3")[0]));
                cst.execute();
                rs = (ResultSet) cst.getObject(1);
            }
            if (rs != null) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while (rs.next()) {
                    Row gp = new Row();
                    List<Column> columnList = new ArrayList<Column>();
                    for (int i = 1; i <= columnCount; i++) {
                        Column column = new Column();
                        column.setKey(rsmd.getColumnName(i));
                        column.setValue(rs.getString(rsmd.getColumnName(i)));
                        columnList.add(column);
                    }
                    gp.setColumns(columnList);
                    params.add(gp);
                }
            }

        } catch (Exception ex) {
            dbFunction.close(rs, cst);
            dbFunction.close(conn);
            ex.printStackTrace();
        } finally {
            dbFunction.close(rs, cst);
            dbFunction.close(conn);
        }
        return params;
    }

    @Override
    public List<listInfo> loadStrukturList(String partID, long langID) throws Exception {
        List<listInfo> infL = new ArrayList<listInfo>();
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            if (("1006").equals(partID)) {
                cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.LOAD_MAIN_DATA_FUNCTIONS.LOADSTRUKTURLIST() }");
                cst.registerOutParameter(1, OracleTypes.CURSOR);
                cst.execute();
            } else if (("1010").equals(partID)) {
                cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.LOAD_MAIN_DATA_FUNCTIONS.LOADCATEGORYLIST(?) }");
                cst.registerOutParameter(1, OracleTypes.CURSOR);
                cst.setLong(2, langID);
                cst.execute();
            }
            rs = (ResultSet) cst.getObject(1);
            while (rs.next()) {
                listInfo inf = new listInfo();
                inf.setId(rs.getLong("ID"));
                inf.setElemType(rs.getInt("TIP"));
                inf.setDescription1(rs.getString("FORMULA"));
                inf.setDescription2(rs.getString("NAME"));
                inf.setIndex(rs.getLong("INDEX1"));
                infL.add(inf);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return infL;
    }

    @Override
    public finalInfo getOrgInfo(long treeID) throws Exception {
        finalInfo orgInfo = new finalInfo();
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.GETORGINFO(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, treeID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            if (rs.next()) {
                orgInfo.setID(rs.getLong("ID"));
                orgInfo.setFinTypeID(rs.getLong("TYPID"));
                orgInfo.setFinTypeName(rs.getString("TYPNAME"));
                orgInfo.setStDate(rs.getDate("CREATE_DATE"));
                orgInfo.setNotes(rs.getString("DESCRIPTION"));
                orgInfo.setoName(rs.getString("NAME"));
                orgInfo.setAddress(rs.getString("ADDR"));
                orgInfo.setAddrsID(rs.getLong("ADDRID"));
                orgInfo.setState(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return orgInfo;
    }

    @Override
    public categoryFinalInfo getCategoryInfo(long treeID) throws Exception {
        categoryFinalInfo catInfo = new categoryFinalInfo();
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.GETCATINFO(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, treeID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            if (rs.next()) {
                catInfo.setID(rs.getLong("ID"));
                catInfo.setLangIdAz(rs.getLong("IDAZ"));
                catInfo.setLangIdRu(rs.getLong("IDRU"));
                catInfo.setLangIdEn(rs.getLong("IDEN"));
                catInfo.setFinTypeID(rs.getLong("TYPID"));
                catInfo.setFinTypeName(rs.getString("TYPNAME"));
                catInfo.setNotes(rs.getString("DESCRIPTION"));
                catInfo.setNotesRu(rs.getString("DESCRIPTIONRU"));
                catInfo.setNotesEn(rs.getString("DESCRIPTIONEN"));
                catInfo.setoName(rs.getString("NAME"));
                catInfo.setoNameRu(rs.getString("NAMERU"));
                catInfo.setoNameEn(rs.getString("NAMEEN"));
                catInfo.setRegCodePart(rs.getString("CODE"));
                catInfo.setState(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return catInfo;
    }

    @Override
    public carriersInfo getCariesInfo(long carryID) throws Exception {
        carriersInfo catInfo = new carriersInfo();
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.GETCARRYINFO(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, carryID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            if (rs.next()) {
                catInfo.setCarryID(rs.getLong("ID"));
                catInfo.setLangIDaz(rs.getLong("IDAZ"));
                catInfo.setLangIDru(rs.getLong("IDRU"));
                catInfo.setLangIDen(rs.getLong("IDEN"));
                catInfo.setDesAz(rs.getString("DESCRIPTION"));
                catInfo.setDesRu(rs.getString("DESCRIPTIONRU"));
                catInfo.setDesEn(rs.getString("DESCRIPTIONEN"));
                catInfo.setCarryNameAz(rs.getString("NAME"));
                catInfo.setCarryNameRu(rs.getString("NAMERU"));
                catInfo.setCarryNameEn(rs.getString("NAMEEN"));
                catInfo.setState(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return catInfo;
    }

    @Override
    public carriersInfo getOrganizationInfo(long carryID) throws Exception {
        carriersInfo catInfo = new carriersInfo();
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.GETORGANINFO(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, carryID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            if (rs.next()) {
                catInfo.setCarryID(rs.getLong("ID"));
                catInfo.setLangIDaz(rs.getLong("IDAZ"));
                catInfo.setLangIDru(rs.getLong("IDRU"));
                catInfo.setLangIDen(rs.getLong("IDEN"));
                catInfo.setDesAz(rs.getString("DESCRIPTION"));
                catInfo.setDesRu(rs.getString("DESCRIPTIONRU"));
                catInfo.setDesEn(rs.getString("DESCRIPTIONEN"));
                catInfo.setCarryNameAz(rs.getString("NAME"));
                catInfo.setCarryNameRu(rs.getString("NAMERU"));
                catInfo.setCarryNameEn(rs.getString("NAMEEN"));
                catInfo.setState(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return catInfo;
    }

    @Override
    public person getEmployeeInfo(long empId) throws Exception {
        CallableStatement cst = null;
        ResultSet rs = null;
        person p = null;
        try {
            p = new person();
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.GET_EMPLOYEE_INFO(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, empId);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            if (rs.next()) {
                p.setlName(rs.getString("LNAME"));
                p.setfName(rs.getString("FNAME"));
                p.setmName(rs.getString("MNAME"));
                p.setOrgID(rs.getLong("ORG_ID"));
                p.setPositionID(rs.getLong("POSITION_ID"));
                p.setHireDate(rs.getDate("HIREDATE"));
                p.setFireDate(rs.getDate("FIREDATE"));
                p.setBirthDate(rs.getDate("BDATE"));
                p.setGenderID(rs.getLong("GENID"));
                p.setPinCode(rs.getString("PIN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return p;
    }

    @Override
    public List<examples> getExamplesInfo(long exmpID, String langID) throws Exception {
        CallableStatement cst = null;
        List<examples> exmpL = null;
        ResultSet rs = null;
        examples exmp = null;
        try {
            exmpL = new ArrayList<examples>();

            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.GET_EXAMPLES_INFO(?,?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, exmpID);
            cst.setString(3, langID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            while (rs.next()) {
                exmp = new examples();
                exmp.setExmNameAz(rs.getString("NAMEAZ"));
                exmp.setExmNameRu(rs.getString("NAMERU"));
                exmp.setExmNameEn(rs.getString("NAMEEN"));
                exmp.setExmKeyWordAz(rs.getString("KEYAZ"));
                exmp.setExmKeyWordRu(rs.getString("KEYRU"));
                exmp.setExmKeyWordEn(rs.getString("KEYEN"));
                exmp.setTextAz(rs.getString("TEXTAZ"));
                exmp.setTextRu(rs.getString("TEXTRU"));
                exmp.setTextEn(rs.getString("TEXTE"));
                exmp.setReferenceAz(rs.getString("REFAZ"));
                exmp.setReferenceRu(rs.getString("REFRU"));
                exmp.setReferenceEn(rs.getString("REFEN"));
                exmp.setReferenceEn(rs.getString("REFEN"));
                exmp.setCategoryID(rs.getString("CNAME"));
                exmp.setGroupID(rs.getString("GNAME"));
                exmp.setExmClassID(rs.getString("CLNAME"));
                exmp.setExmTypeID(rs.getString("TNAME"));
                exmp.setExmGenreID(rs.getString("JNAME"));
                exmp.setRegCode(rs.getString("RCODE"));
                exmp.setNoteAz(rs.getString("NOTEAZ"));
                exmp.setNoteRu(rs.getString("NOTERU"));
                exmp.setNoteEn(rs.getString("NOTEEN"));
                exmp.seteDate(rs.getDate("EDATE"));
                exmp.setOvertakenRegionID(rs.getLong("OPLACE"));
                exmp.setMete(rs.getLong("METE"));
                exmp.setRegionID(rs.getString("REGID"));
                exmp.setCarriersList(rs.getString("CARRY_ID"));
                exmp.setOrgansList(rs.getString("ORGNAID"));
                exmp.setYuneskoList(rs.getString("YUNESKOID"));
                exmp.setCheckHtml(rs.getLong("CHECKHTMLFILE"));
                exmpL.add(exmp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return exmpL;
    }


    @Override
    public List<contact> getOrgContacts(long treeID) throws Exception {
        List<contact> cList = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            cList = new ArrayList<contact>();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.GETORGCONTACT(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, treeID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            while (rs.next()) {
                contact cc = new contact();
                cc.setRownum(rs.getLong("ID"));
                cc.setPerContactID(rs.getLong("REALID"));
                cc.setContactTypeID(rs.getLong("CONTACTYPEID"));
                cc.setContactTypeName(rs.getString("CONTACTTYPENAME"));
                cc.setContactVal(rs.getString("CONTACT_VALUE"));
                cc.setState(1);
                cList.add(cc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return cList;
    }

    @Override
    public List<contact> getPersonContact(long perID) throws Exception {
        List<contact> cList = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        int chk = 0;
        try {
            conn = df.connectDB();
            cList = new ArrayList<contact>();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.GETPERSONCONTACT(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, perID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            while (rs.next()) {
                contact cc1 = new contact();
                cc1.setPerContactID(rs.getLong("ID"));
                cc1.setContactTypeID(rs.getLong("CONTACTYPEID"));
                cc1.setContactTypeName(rs.getString("CONTACTTYPENAME"));
                cc1.setContactVal(rs.getString("CONTACT_VALUE"));
                cc1.setState(1);
                cList.add(cc1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst);
            dbFunction.close(conn);

        }
        return cList;
    }


    @Override
    public List<docList> loadQRphoto(Connection con, long exmplID, long picType) throws Exception {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<docList> dl = null;
        try {
            if (con == null) {
                con = df.connectDB();
            }
            cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADPHOTOURL(?,?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, exmplID);
            cst.setLong(3, picType);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            docList dd = null;
            if (rs.next()) {
                dl = new ArrayList<docList>();
                dd = new docList();
                Properties prop = new Properties();
                dd.setId(rs.getLong("ID"));
                InputStream inputStream = getClass().getResourceAsStream("/utils/FTPConn.properties");
                prop.load(inputStream);
                String url = "ftp://" + prop.getProperty("ftp.uname") + ":" + prop.getProperty("ftp.pass") + "@" + prop.getProperty("ftp.url") + ":21/";
                dd.setDocNumber("1");
                dd.setUrl(url);
                dd.setQrCodePath(rs.getString("PHOTO_URL"));
                dd.setStatus("1");
                dd.setDocCategory(1);
                dl.add(dd);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("load Photo ex=" + ex.toString());
        } finally {
            dbFunction.close(rs, cst);
            dbFunction.close(con);
        }
        return dl;
    }


    @Override
    public List<docList> loadPhoto(Connection con, long exmplID, long picType) throws Exception {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<docList> dl = null;
        try {
            if (con == null) {
                con = df.connectDB();
            }
            cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADPHOTOURL1(?,?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, exmplID);
            cst.setLong(3, picType);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            docList dd = null;
            if (rs.next()) {
                dl = new ArrayList<docList>();
                dd = new docList();
                Properties prop = new Properties();
                dd.setId(rs.getLong("ID"));
                InputStream inputStream = getClass().getResourceAsStream("/utils/FTPConn.properties");
                prop.load(inputStream);
                String url = "ftp://" + prop.getProperty("ftp.uname") + ":" + prop.getProperty("ftp.pass") + "@" + prop.getProperty("ftp.url") + ":21/";
                dd.setDocNumber("1");
                dd.setUrl(url);
                dd.setFilePath(rs.getString("PHOTO_URL"));
                dd.setStatus("1");
                dd.setDocCategory(1);
                dl.add(dd);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("load Photo ex=" + ex.toString());
        } finally {
            dbFunction.close(rs, cst);
            dbFunction.close(con);
        }
        return dl;
    }

    @Override
    public List<Row> getSelectContent(String type, Map<String, String[]> parameterMap, usersInfo loggedUser) throws Exception {
        List<Row> params = new ArrayList<Row>();
        String sql = "";
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            if (type.equals("mainGrid")) {
                long rID1 = (Long.parseLong(parameterMap.get("pNumber")[0]) * Long.parseLong(parameterMap.get("pSize")[0])) - Long.parseLong(parameterMap.get("pSize")[0]) + 1;
                long rID2 = Long.parseLong(parameterMap.get("pNumber")[0]) * Long.parseLong(parameterMap.get("pSize")[0]);
                String sIndex = parameterMap.get("gSortI")[0];
                String sOrder = parameterMap.get("gSort")[0];

                String order = "";
                cst = conn.prepareCall("{ call CULTTREASREGISTER_COMMON.LOAD_MAIN_DATA_FUNCTIONS.LOADMAINGRID(?,?,?,? ,?,?,?,?, ?,?,?,?, ?,?,?,?,?,?,?,?) }");
                cst.registerOutParameter(1, OracleTypes.CURSOR);//RESULT
                cst.registerOutParameter(2, OracleTypes.DECIMAL);//P_COUNT
                cst.setLong(3, Long.parseLong(parameterMap.get("gridType")[0]));//GTYPE
                cst.setLong(4, rID1);//FRR1
                cst.setLong(5, rID2);//TOR2
                cst.setString(6, order);//ORDBY
                cst.setString(7, " ");//STEXT
                cst.setLong(8, loggedUser.getId());//USERID
                cst.setLong(9, loggedUser.getOrgID());//USERORGID
                cst.setLong(10, loggedUser.getUserType());//USERTYPE
                cst.setLong(11, Long.parseLong(parameterMap.get("categoryList")[0]));//F_CATID
                cst.setLong(12, Long.parseLong(parameterMap.get("groupList")[0]));//F_GROUP
                cst.setLong(13, Long.parseLong(parameterMap.get("classList")[0]));//F_CLASS
                cst.setLong(14, Long.parseLong(parameterMap.get("mStatusList")[0]));//F_STATUS
                cst.setLong(15, Long.parseLong(parameterMap.get("typeList")[0]));//F_TYPE
                cst.setLong(16, Long.parseLong(parameterMap.get("genreList")[0]));//GEN_ID
                cst.setLong(17, Long.parseLong(parameterMap.get("lang_id")[0]));//LANG_ID
                cst.setString(18, parameterMap.get("statusID")[0]);
                cst.setString(19, "");
                cst.setString(20, "");
                cst.execute();
                rs = (ResultSet) cst.getObject(1);
            } else if (type.equals("createPassport")) {
                cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.GET_PASSPORT_INFO(?,?) }");
                cst.registerOutParameter(1, OracleTypes.CURSOR);
                cst.setLong(2, Long.parseLong(parameterMap.get("exampleID")[0]));
                cst.setLong(3, Long.parseLong(parameterMap.get("langID")[0]));
                cst.execute();
                rs = (ResultSet) cst.getObject(1);
            }else if (type.equals("FullMundericatReport")) {
                cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.LOAD_MAIN_DATA_FUNCTIONS.LOADCATEGORYLIST(?) }");
                cst.registerOutParameter(1, OracleTypes.CURSOR);
                cst.setLong(2, 1000);
                cst.execute();
                rs = (ResultSet) cst.getObject(1);
            }
            if (rs != null) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();
                while (rs.next()) {
                    Row gp = new Row();
                    List<Column> columnList = new ArrayList<Column>();
                    for (int i = 1; i <= columnCount; i++) {
                        Column column = new Column();
                        column.setKey(rsmd.getColumnName(i));
                        column.setValue(rs.getString(rsmd.getColumnName(i)));
                        columnList.add(column);
                    }
                    gp.setColumns(columnList);
                    params.add(gp);
                }
            }
            //}
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbFunction.close(rs, cst);
            dbFunction.close(conn);
        }
        return params;
    }

    @Override
    public List<docList> getExamplesPictures(Connection cc, long relID, long relType) throws Exception {
        List<docList> dList = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            dList = new ArrayList<docList>();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADEXAMPLEFILES(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, relID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            while (rs.next()) {
                docList dc = new docList();
                dc.setId(rs.getLong("EXMID"));
                dc.setRelID(rs.getLong("EMID"));
                dc.setDocTypeID(rs.getLong("DOCTYPEID"));
                dc.setDocNumber(rs.getString("DOCNUMBER"));
                dc.setHasHTMLfile(rs.getInt("DOCUMENTCONTAIN"));
                dc.setDocNameAz(rs.getString("NAMEAZ"));
                dc.setDocNameRu(rs.getString("NAMERU"));
                dc.setDocNameEn(rs.getString("NAMEEN"));
                dc.setAuthorAz(rs.getString("AUTHRAZ"));
                dc.setAuthorRu(rs.getString("AUTHRRU"));
                dc.setAuthorEn(rs.getString("AUTHREN"));
                dc.setLink(rs.getString("LINK"));
                dc.setDocDate(rs.getString("DOC_DATE"));
                dc.setFilePath(rs.getString("FILE_PATH"));
                dc.setStatus("1");
                dList.add(dc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return dList;
    }

    @Override
    public List<examples> getExamplesOperation(Connection cc, long relID, long langID) throws Exception {
        List<examples> dList = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            dList = new ArrayList<examples>();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADEXAMPLEOPERATION(?,?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, relID);
            cst.setLong(3, langID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            while (rs.next()) {
                examples dc = new examples();
                dc.setStatusID(rs.getLong("ID"));
                dc.setEventComment(rs.getString("REASON"));
                dc.setStatus(rs.getString("STATUS"));
                dc.setOperDate(rs.getDate("EVENTDATE"));
                dc.setEventTypeID(rs.getLong("DICT_EVENT_TYPE_ID"));
                dc.setUserName(rs.getString("UNAME"));
                dList.add(dc);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return dList;
    }

    @Override
    public int checkUser(String uName) throws Exception {
        int res = -1;
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.SECURITY_FUNCTIONS.LOGINUSER(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setString(2, uName.toLowerCase());
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            if (rs.next()) {
                res = 0;
            } else {
                res = 100;
            }
            // logger.info("res = "+res);
        } catch (Exception ex) {
            ex.printStackTrace();
            res = 10;
            logger.info("Error while check dao " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return res;
    }

    @Override
    public int checkDictRecord(String text, int dictType, String org, String pos) throws Exception {
        int res = -1;
        CallableStatement cst = null;
        conn = df.connectDB();
        try {
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.ACTIONCHECKRECORDCOUNTDICT(?,?,?,?) }");
            cst.registerOutParameter(1, OracleTypes.VARCHAR);
            cst.setString(2, text);
            cst.setInt(3, dictType);
            cst.setInt(4, Integer.parseInt(org));
            cst.setInt(5, Integer.parseInt(pos));
            cst.execute();
            res = cst.getInt(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl find findCountEmpSecurity = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
        return res;
    }
    @Override
    public List<categoryFinalInfo> getCategoryStructure(long catID, int id) throws Exception {
        conn = df.connectDB();
        CallableStatement cst = null;
        ResultSet rs = null;
        List<categoryFinalInfo> catList = new ArrayList<categoryFinalInfo>();
        try {
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.GET_CATEGORYSTRUCTURE_INFO(?,?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, 11);
            cst.setLong(3, 111);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);

            while (rs.next()) {
                categoryFinalInfo per = new categoryFinalInfo();
                per.setID(rs.getInt("ID"));
                per.setFormula(rs.getString("FORMULA"));
                per.setIndex1(rs.getLong("INDEX1"));
                per.setName(rs.getString("NAME"));

               // per.setParentID(rs.getLong("PARENTID"));
                per.setParentID1(rs.getString("PARENTID1"));
                per.setTip(rs.getLong("TIP"));
                catList.add(per);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return catList;
    }

    @Override
    public List<docList> loadRightPanelPhoto(long realID, int iType) throws Exception {
        CallableStatement cst = null;
        ResultSet rs = null;
        List<docList> dl = null;
        try {
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.RIGHTPANELPHOTOURL(?,?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, realID);
            cst.setLong(3, iType);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            docList dd = null;
            if (rs.next()) {
                dl = new ArrayList<docList>();
                dd = new docList();
                Properties prop = new Properties();
                dd.setId(rs.getLong("ID"));
                InputStream inputStream = getClass().getResourceAsStream("/utils/FTPConn.properties");
                prop.load(inputStream);
                String url = "ftp://" + prop.getProperty("ftp.uname") + ":" + prop.getProperty("ftp.pass") + "@" + prop.getProperty("ftp.url") + ":21/";
                dd.setDocNumber("1");
                dd.setUrl(url);
                dd.setFilePath(rs.getString("PHOTO_URL"));
                dd.setStatus("1");
                dd.setDocCategory(1);
                dl.add(dd);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("load Photo ex=" + ex.toString());
        } finally {
            dbFunction.close(rs, cst);
            dbFunction.close(conn);
        }
        return dl;
    }


    @Override
    public usersInfo loadUserInfo(String pID) throws Exception {
        usersInfo inf = null;
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADUSERINFO(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, Long.parseLong(pID));
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            if (rs.next()) {
                inf = new usersInfo();
                inf.setUserName(rs.getString("USER_NAME"));
                String pp = rs.getString("PASSWORD");
                inf.setPswd("*****");
                inf.setUserAct(rs.getLong("ACTIVE"));
                inf.setUserType(rs.getLong("USER_TYPE"));

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return inf;
    }

    @Override
    public List<person> loadEmpList(int OrgID) throws Exception {
        List<person> infL = new ArrayList<person>();
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call ULTEMP_COMMON.GET_DATA_FUNCTIONS.LOADEMPLOYEELIST(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, OrgID);
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            while (rs.next()) {
                person inf = new person();
                inf.setfName(rs.getString("FULLNAME"));
                inf.setID(rs.getLong("ID"));
                infL.add(inf);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return infL;
    }


    @Override
    public List<subjElement> getADVSearchMenuList(String partID) throws Exception {
        List<subjElement> subList = new ArrayList<subjElement>();
        ResultSet rs = null;
        CallableStatement cst = null;
        try {
            conn = df.connectDB();
            cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADSEARCHMENU(?) }");
            cst.registerOutParameter(1, OracleTypes.CURSOR);
            cst.setLong(2, Long.parseLong(partID));
            cst.execute();
            rs = (ResultSet) cst.getObject(1);
            while (rs.next()) {
                subjElement subj = new subjElement();
                subj.setID(rs.getInt("ID"));
                subj.setSubjectTypeID(rs.getLong("ELEM_TYPE_ID"));
                subj.setSubjectName(rs.getString("ELEMENT_NAME"));
                subj.setListID(rs.getLong("LIST_ID"));
                subList.add(subj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Error while getting searchMenu list Error = " + e.getMessage());
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return subList;
    }

    @Override
    public String getADVInfo(String paramID, String paramVal, String val, String cond, String typ) throws Exception {
        String res = "";
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            if ("1".equals(typ)) {
                cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADFROMPARVAL(?) }");
                cst.registerOutParameter(1, OracleTypes.CURSOR);
                cst.setInt(2, Integer.parseInt(paramID));
                cst.execute();
                rs = (ResultSet) cst.getObject(1);
                if (rs.next()) {
                    String fromText = rs.getString(1);
                    if ("1".equals(typ)) {
                        if (fromText != null) {
                            res = fromText;
                        }
                    }
                }
            } else if ("2".equals(typ)) {
                cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADWHEREPARVAL(?) }");
                cst.registerOutParameter(1, OracleTypes.CURSOR);
                cst.setInt(2, Integer.parseInt(paramID));
                cst.execute();
                rs = (ResultSet) cst.getObject(1);
                String whereText = "",
                        tmpWhr = "";//rs.getString(1);
                String value = "";
                while (rs.next()) {
                    tmpWhr = rs.getString(1);
                    //  logger.info(tmpWhr+" /// "+tmpWhr.indexOf("#")+" = tmpWhr.indexOf(#)");
                    whereText = whereText + rs.getString(1);
                    logger.info(" WHERE TEXTT===/// " + whereText);
                    if (tmpWhr.indexOf("#") != -1) {
                        if ("1000".equals(val)) {
                            String vall = paramVal.toLowerCase();
                            if ("1".equals(cond)) {
                                value = " like '" + vall + "%'";
                            } else if ("2".equals(cond)) {
                                value = " like '%" + vall + "%'";
                            } else if ("3".equals(cond)) {
                                value = " like '%" + vall + "'";
                            }
                        } else if ("1002".equals(val)) {
                            if ("1".equals(cond)) {
                                value = " > TO_DATE('" + paramVal + "','dd-mm-YYYY')";
                            } else if ("2".equals(cond)) {
                                value = " < TO_DATE('" + paramVal + "','dd-mm-YYYY')";
                            } else if ("3".equals(cond)) {
                                value = " = TO_DATE('" + paramVal + "','dd-mm-YYYY')";
                            } else if ("4".equals(cond)) {
                                value = " >= TO_DATE('" + paramVal + "','dd-mm-YYYY')";
                            } else if ("5".equals(cond)) {
                                value = " <= TO_DATE('" + paramVal + "','dd-mm-YYYY')";
                            }
                        } else if ("1001".equals(val)) {
                            if ("1".equals(cond)) {
                                value = " > " + paramVal + "";
                            } else if ("2".equals(cond)) {
                                value = " < " + paramVal + "";
                            } else if ("3".equals(cond)) {
                                value = " = " + paramVal + "";
                            } else if ("4".equals(cond)) {
                                value = " >= " + paramVal + "";
                            } else if ("5".equals(cond)) {
                                value = " <= " + paramVal + "";
                            }
                        } else if ("1003".equals(val)) {
                            value = cond + paramVal + "";
                        }
                    }
                    //  logger.info("valueeeee ===" +value);

                    whereText = whereText.replace("#", value);
                }

                res = whereText;
            } else if ("3".equals(typ)) {
                cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.LOADWHEREPARVAL(?) }");
                cst.registerOutParameter(1, OracleTypes.CURSOR);
                cst.setInt(2, Integer.parseInt(paramID));
                cst.execute();
                rs = (ResultSet) cst.getObject(1);
                String whereText = "", tmpWhr = "";//rs.getString(1);
                while (rs.next()) {
                    whereText = rs.getString(1);
                }
                res = whereText;
            }
        } catch (SQLException ex) {
            logger.info("Find element_type_id" + ex);
        } finally {
            dbFunction.close(rs, cst, conn);
        }
        return res;
    }


}





