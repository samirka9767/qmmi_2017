package dao;

import mondrian.util.Base64;
import oracle.jdbc.OracleTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import utils.*;
import web.*;
import domain.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class saveDataDBImpl implements saveDataDB {

    private Log logger = LogFactory.getLog(getClass());
    protected Connection conn = null;
    protected dbFunction df = new dbFunction();
    protected SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");


    @Override
    public void saveExamplesInfo(examples pp, String opType, List<subjElement> piList, List<contact> pcList, List<docList> dsl, List<docList> qrDsl, String userID) throws SQLException {
        try {
            conn = df.connectDB();
            long exmID = 0;
            Date dt = new Date();


            String[] efield = {"EXAMPLE_ID", "EX_NAME", "TEXT", "NOTE", "LANG_ID", "KEY_WORD"};
            int[] etype = {0, 1,  1, 1, 0, 1};

            String[] eMfield = {"EXAMPLE_ID", "RELATED_ID", "RELATED_TYPE"};
            int[] eMtype = {0, 0, 0};

            String[] STfield = {"EXAMPLE_ID", "DICT_EVENT_TYPE_ID", "EVENT_DATE", "EVENT_COMMENT", "USER_ID"};
            int[] STtype = {0, 0, 2, 1, 0};


           /* if (pp.geteDate() == null) {
                pp.seteDate(dt);
            }
*/
            String eDate = "";
            if(pp.geteDate() != null){
                eDate = format.format(pp.geteDate());
            }
            if ("1".equals(opType)) {
        //         TODO: // register code
                String categoryCount = findCountForCode(conn, pp.getCategoryID());
                String groupCount = findCountForCode(conn, pp.getGroupID());
                String classCount = findCountForCode(conn, pp.getExmClassID());
                String typeCount = findCountForCode(conn, pp.getExmTypeID());
                String genreCount = findCountForCode(conn, pp.getExmGenreID());

                String code = "";
                if (!"".equals(categoryCount) && !"0".equals(categoryCount)) {
                    code = code + categoryCount;
                }
                if ( !"0".equals(groupCount) &&  !"".equals(groupCount) ) {
                    code = code + groupCount;
                } else {
                    code = code + "0";
                }

                    if (!"0".equals(classCount) && !"".equals(classCount)) {
                        code = code + classCount;
                    } else {
                        code = code + "00";
                    }

                    if (!"0".equals(typeCount) && !"".equals(typeCount)){
                        code = code + typeCount;
                    } else {
                         code = code + "00";
                    }

                    if (!"0".equals(genreCount) && !"".equals(genreCount)) {
                        code = code + genreCount;
                    }  else {
                        code = code + "000";
                    }

                String[] pfield = {"REYESTER_CODE_1", "EDATE", "OCCUPATION_PLACE", "CREATE_DATE","METE"};
                int[] ptype = {1, 2, 0, 2,0};

                String[] pval = {code,eDate , String.valueOf(pp.getOvertakenRegionID()), format.format(dt),String.valueOf(pp.getMete())};
                exmID = df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLES, pfield, pval, ptype);

                String[] STval = {String.valueOf(exmID), "1025", format.format(dt), " ", userID};
                df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_STATUS, STfield, STval, STtype);
                //         TODO: //  carriers  AZE
                String[] evalAz = {String.valueOf(exmID), pp.getExmNameAz(), pp.getTextAz(), pp.getNoteAz(), String.valueOf(1000), pp.getExmKeyWordAz()};
                df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_INF, efield, evalAz, etype);
                //         TODO: //  carriers  RU
                String[] evalRu = {String.valueOf(exmID), pp.getExmNameRu(), pp.getTextRu(), pp.getNoteRu(), String.valueOf(1001), pp.getExmKeyWordRu()};
                df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_INF, efield, evalRu, etype);
                //         TODO: //  carriers  EN
                String[] evalEn = {String.valueOf(exmID), pp.getExmNameEn(),  pp.getTextEn(), pp.getNoteEn(), String.valueOf(1002), pp.getExmKeyWordEn()};
                df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_INF, efield, evalEn, etype);

            } else if ("2".equals(opType)) {
                long tableID = 0;
                exmID = pp.getID();
                if(pp.getMete() != 0 && pp.getOvertakenRegionID() != 0) {
                    String[] pfield = {"EDATE", "OCCUPATION_PLACE", "CREATE_DATE","METE"};
                    int[] ptype = {2, 0, 2,0};
                    String[] pval = {eDate, String.valueOf(pp.getOvertakenRegionID()), format.format(dt),String.valueOf(pp.getMete())};
                    df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLES, pfield, pval, ptype, String.valueOf(pp.getID()));
                }

                //         TODO: //  carriers  AZE
                tableID = findExampleDetailsID(conn, pp.getID(), 1000, 1);
                String[] evalAz = {String.valueOf(exmID), pp.getExmNameAz(),  pp.getTextAz(), pp.getNoteAz(), String.valueOf(1000), pp.getExmKeyWordAz()};
                if(tableID != 0){
                    df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLE_INF, efield, evalAz, etype, String.valueOf(tableID));
                }
                //         TODO: //  carriers  RU
                tableID = findExampleDetailsID(conn, pp.getID(), 1001, 1);
                String[] evalRu = {String.valueOf(exmID), pp.getExmNameRu(),  pp.getTextRu(), pp.getNoteRu(), String.valueOf(1001), pp.getExmKeyWordRu()};
                if(tableID != 0){
                    df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLE_INF, efield, evalRu, etype, String.valueOf(tableID));
                }
                //         TODO: //  carriers  EN
                tableID = findExampleDetailsID(conn, pp.getID(), 1002, 1);
                String[] evalEn = {String.valueOf(exmID), pp.getExmNameEn(), pp.getTextEn(), pp.getNoteEn(), String.valueOf(1002), pp.getExmKeyWordEn()};
                if(tableID != 0){
                    df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLE_INF, efield, evalEn, etype, String.valueOf(tableID));
                }

                //         TODO: //  MULTI-SELECT
                deleteMultiOptions(conn, pp.getID());
            }


            if (!"".equals(pp.getRegionID()) && pp.getRegionID() != null) {
                String[] regList = pp.getRegionID().split("/---/");
                for (int i = 0; i < regList.length; i++) {
                    String numberAsString = regList[i];
                    String[] eMvalCategory = {String.valueOf(exmID), numberAsString, "11"};
                    df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_MULTY_OPTION, eMfield, eMvalCategory, eMtype);
                }
            }
            if (!"".equals(pp.getCategoryID()) && pp.getCategoryID() != null) {
                String[] catList = pp.getCategoryID().split("/---/");
                for (int i = 0; i < catList.length; i++) {
                    String numberAsString = catList[i];
                    String[] eMvalCategory = {String.valueOf(exmID), numberAsString, "1012"};
                    df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_MULTY_OPTION, eMfield, eMvalCategory, eMtype);
                }
            }

            if (!"".equals(pp.getGroupID()) && pp.getGroupID() != null) {
                String[] grList = pp.getGroupID().split("/---/");
                for (int i = 0; i < grList.length; i++) {
                    String numberAsString = grList[i];
                    String[] eMvalGroup = {String.valueOf(exmID), numberAsString, "1013"};
                    df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_MULTY_OPTION, eMfield, eMvalGroup, eMtype);
                }
            }
            if (!"".equals(pp.getExmClassID()) && pp.getExmClassID() != null) {
                String[] clList = pp.getExmClassID().split("/---/");
                for (int i = 0; i < clList.length; i++) {
                    String numberAsString = clList[i];
                    String[] eMvalGroup = {String.valueOf(exmID), numberAsString, "1014"};
                    df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_MULTY_OPTION, eMfield, eMvalGroup, eMtype);
                }
            }

            if (!"".equals(pp.getExmTypeID()) && pp.getExmTypeID() != null) {
                String[] tpList = pp.getExmTypeID().split("/---/");
                for (int i = 0; i < tpList.length; i++) {
                    String numberAsString = tpList[i];
                    String[] eMvalGroup = {String.valueOf(exmID), numberAsString, "1017"};
                    df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_MULTY_OPTION, eMfield, eMvalGroup, eMtype);
                }
            }
            if (!"".equals(pp.getExmGenreID()) && pp.getExmGenreID() != null) {
                String[] genList = pp.getExmGenreID().split("/---/");
                for (int i = 0; i < genList.length; i++) {
                    String numberAsString = genList[i];
                    String[] eMvalGroup = {String.valueOf(exmID), numberAsString, "1015"};
                    df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_MULTY_OPTION, eMfield, eMvalGroup, eMtype);
                }
            }

            if (!"".equals(pp.getCarriersList())  && pp.getCarriersList() != null) {
                String[] carrypList = pp.getCarriersList().split("/---/");
                for (int i = 0; i < carrypList.length; i++) {
                    String numberAsString = carrypList[i];
                    String[] eMvalGroup = {String.valueOf(exmID), numberAsString, "1032"};
                    df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_MULTY_OPTION, eMfield, eMvalGroup, eMtype);
                }
            }

            if (!"".equals(pp.getOrgansList())  && pp.getOrgansList() != null) {
                String[] orgpList = pp.getOrgansList().split("/---/");
                for (int i = 0; i < orgpList.length; i++) {
                    String numberAsString = orgpList[i];
                    String[] eMvalGroup = {String.valueOf(exmID), numberAsString, "1033"};
                    df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_MULTY_OPTION, eMfield, eMvalGroup, eMtype);
                }
            }
            if (!"".equals(pp.getYuneskoList()) && pp.getYuneskoList() != null) {
                String[] yuneskoList = pp.getYuneskoList().split("/---/");
                for (int i = 0; i < yuneskoList.length; i++) {
                    String numberAsString = yuneskoList[i];
                    String[] eMvalGroup = {String.valueOf(exmID), numberAsString, "1076"};
                    df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_MULTY_OPTION, eMfield, eMvalGroup, eMtype);
                }
            }
            if(pp.getMete() != 0) {
                updateConnetcBetweenCatExamp(conn, exmID);
                if(pp.getMete() == 1020) {
                    long id = connectCategoryWithExample(conn, exmID);
                    if(id != 0) {
                        String[] omField = {"EXAMPLE_ID", "CATEGORY_ID", "ACTIVE"};
                        int[] omTyp = {0, 0, 0};
                        String[] omVal = {String.valueOf(exmID), String.valueOf(id), "1"};
                        df.makeInsertDB(conn, constant.TABLE_MAN_RELATED_CATEGORY_EXAMPLE, omField, omVal, omTyp);
                    }
                }
            }



            if (dsl != null) {
                for (docList ds : dsl) {
                    long mID = 0;
                    String[] tfield = {"EXAMPLE_ID", "FILEPATH", "LINK", "DICT_FILETYPE", "DOCNUMBER","DOCUMENTCONTAIN"};
                    String[] tval = {String.valueOf(exmID), ds.getFilePath(), ds.getLink(), String.valueOf(ds.getDocTypeID()), ds.getDocNumber(),String.valueOf(ds.getHasHTMLfile())};
                    int[] ttyp = {0, 1, 1, 0, 1,0};

                    String[] lfield = {"N_M_ID", "MEDIANAME", "AUTHORNAME", "CREATE_DATE", "LANG_ID","DOCYEAR"};
                    int[] ltyp = {0, 1, 1, 2, 0,1};

                    String[] exField = {"CHECKHTMLFILE"};
                    int[] exTyp = {0};
                    String eDocDate = "";
                    if(ds.getDocDate() != null && !ds.getDocDate().equals("")){
                        eDocDate = ds.getDocDate();
                    }
                    if ("0".equals(ds.getStatus())) {
                        if (ds.getFilePath() != null) {
                            mID = df.makeInsertDB(conn, constant.TABLE_EXAMPLE_PHOTO, tfield, tval, ttyp);
                            if (ds.getDocNameAz() != null && ds.getDocNameRu() != null && ds.getDocNameEn() != null) {
                                if(ds.getHasHTMLfile() == 1339) {
                                    String[] exVal = {"1"};
                                    df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLES, exField, exVal, exTyp, String.valueOf(exmID));
                                }

                                String[] lvalAz = {String.valueOf(mID), ds.getDocNameAz(), ds.getAuthorAz(), format.format(new Date()), "1000",eDocDate};
                                String[] lvalRu = {String.valueOf(mID), ds.getDocNameRu(), ds.getAuthorRu(),format.format(new Date()), "1001",eDocDate};
                                String[] lvalEn = {String.valueOf(mID), ds.getDocNameEn(), ds.getAuthorEn(), format.format(new Date()), "1002",eDocDate};

                                df.makeInsertDB(conn, constant.TABLE_EXAMPLE_MEDIA_LANG, lfield, lvalAz, ltyp);
                                df.makeInsertDB(conn, constant.TABLE_EXAMPLE_MEDIA_LANG, lfield, lvalRu, ltyp);
                                df.makeInsertDB(conn, constant.TABLE_EXAMPLE_MEDIA_LANG, lfield, lvalEn, ltyp);
                            }
                        }


                    } else if ("1".equals(ds.getStatus())) {
                        if (ds.getFilePath() != null) {
                            if (!"0".equals(ds.getRelID())) {
                                if (ds.getDocNameAz() != null && ds.getDocNameRu() != null && ds.getDocNameEn() != null) {
                                    String[] lf = {"MEDIANAME", "AUTHORNAME", "CREATE_DATE","DOCYEAR"};
                                    int[] lt = {1, 1, 2,1};
                                    mID = df.makeUpdateDB(conn, constant.TABLE_EXAMPLE_PHOTO, tfield, tval, ttyp, String.valueOf(ds.getRelID()));
                                    if(ds.getHasHTMLfile() == 1339) {
                                        String[] exVal = {"1"};
                                        df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLES, exField, exVal, exTyp, String.valueOf(exmID));
                                    }
                                    int[] langID = {1000, 1001, 1002};
                                    String[] docNames = {ds.getDocNameAz(), ds.getDocNameRu(), ds.getDocNameEn()};
                                    String[] authorName = {ds.getAuthorAz(), ds.getAuthorRu(), ds.getAuthorEn()};
                                    for (int i = 0; i < langID.length; i++) {
                                        long id = findExampleDetailsID(conn, ds.getRelID(), langID[i], 6);
                                        String[] lval = {docNames[i], authorName[i], format.format(new Date()),eDocDate};
                                        df.makeUpdateDB(conn, constant.TABLE_EXAMPLE_MEDIA_LANG, lf, lval, lt, String.valueOf(id));
                                    }
                                }
                            }
                        }
                    } else if ("3".equals(ds.getStatus())) {
                        String[] ff = {"ACTIVE"};
                        int[] tt = {0};
                        String[] vv = {"2"};
                        String[] exVal = {"0"};
                        if(ds.getHasHTMLfile() == 1339){
                            df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLES, exField, exVal, exTyp, String.valueOf(exmID));
                        }
                        int[] langID = {1000, 1001, 1002};
                        for (int i = 0; i < langID.length; i++) {
                            long id = findExampleDetailsID(conn, ds.getRelID(), langID[i], 6);
                            df.makeUpdateDB(conn, constant.TABLE_EXAMPLE_MEDIA_LANG, ff, vv, tt, String.valueOf(id));
                        }
                        df.makeUpdateDB(conn, constant.TABLE_EXAMPLE_PHOTO, ff, vv,
                                tt, String.valueOf(ds.getRelID()));
                    }
                }

            }
            if (qrDsl != null) {
                for (docList ds : qrDsl) {
                    long mID = 0;
                    String[] tfield = {"EXAMPLE_ID", "FILEPATH", "DICT_FILETYPE"};
                    String[] tval = {String.valueOf(exmID), ds.getQrCodePath(), String.valueOf(ds.getDocTypeID())};
                    int[] ttyp = {0, 1, 0};

                    if ("1".equals(ds.getStatus())) {
                        if (ds.getQrCodePath() != null) {
                            mID = df.makeInsertDB(conn, constant.TABLE_EXAMPLE_PHOTO, tfield, tval, ttyp);
                        }
                    } else if ("2".equals(ds.getStatus())) {
                        if (ds.getQrCodePath() != null) {

                            String[] ff = {"ACTIVE"};
                            int[] tt = {0};
                            String[] vv = {"2"};
                            long tableID = findqrCodePicID(conn, pp.getID(), 1034);
                            if(tableID != 0){
                                df.makeUpdateDB(conn, constant.TABLE_EXAMPLE_PHOTO, ff, vv,
                                        tt, String.valueOf(tableID));
                            }
                            mID = df.makeInsertDB(conn, constant.TABLE_EXAMPLE_PHOTO, tfield, tval, ttyp);
                        }
                    } else if ("3".equals(ds.getStatus())) {
                        String[] ff = {"ACTIVE"};
                        int[] tt = {0};
                        String[] vv = {"2"};
                        df.makeUpdateDB(conn, constant.TABLE_EXAMPLE_PHOTO, ff, vv,
                                tt, String.valueOf(ds.getId()));
                    }
                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            dbFunction.close(conn);
        }

    }


    @Override
    public void saveEmployeeInfo(person pp, String opType, List<subjElement> piList, List<contact> pcList, docList dd) throws SQLException {
        try {
            conn = df.connectDB();
            long pID = 0;
            long empID = 0;
            long contID = 0;
            String empStatus = "";

            String[] pfield = {"NAME", "SURNAME", "MIDDLE_NAME", "SEX", "BIRTH_DATE", "ACTIVE"};
            int[] ptype = {1, 1, 1, 0, 2, 0};
            String[] pval = {pp.getfName(), pp.getlName(), pp.getmName(), String.valueOf(pp.getGenderID()), format.format(pp.getBirthDate()), "1"};

            String[] cfield = {"PIN", "ACTIVE", "COM_PERSON_ID"};
            int[] ctype = {1, 0, 0};


            if ("1".equals(opType)) {
                pID = df.makeInsertDB(conn, constant.TABLE_COM_PERSONS, pfield, pval, ptype);

                String[] cval = {pp.getPinCode(), "1", String.valueOf(pID)};
                contID = df.makeInsertDB(conn, constant.TABLE_COM_PERSON_UNIQ, cfield, cval, ctype);

                if (pp.getFireDate() == null) {
                    empStatus = "1007";
                    String[] efield = {"COM_PERSON_ID", "HR_ORG_POSITION_ID", "START_DATE", "ACTIVE", "DIC_EMP_STATUS"};
                    int[] etype = {0, 0, 2, 0, 0};
                    String[] eval = {String.valueOf(pID), String.valueOf(pp.getPositionID()), format.format(pp.getHireDate()), "1", empStatus};
                    empID = df.makeInsertDB(conn, constant.TABLE_MAN_EMPLOYEES, efield, eval, etype);

                } else {
                    empStatus = "1008";
                    String[] efield = {"COM_PERSON_ID", "HR_ORG_POSITION_ID", "START_DATE", "END_DATE", "ACTIVE", "DIC_EMP_STATUS"};
                    int[] etype = {0, 0, 2, 2, 0, 0};
                    String[] eval = {String.valueOf(pID), String.valueOf(pp.getPositionID()), format.format(pp.getHireDate()), format.format(pp.getFireDate()), "1", empStatus};
                    empID = df.makeInsertDB(conn, constant.TABLE_MAN_EMPLOYEES, efield, eval, etype);

                }


            } else if ("2".equals(opType)) {
                pID = pp.getPersonID();
                String[] cval = {pp.getPinCode(), "1", String.valueOf(pID)};
                if (pp.getFireDate() == null) {
                    empStatus = "1007";
                    String[] efield = {"COM_PERSON_ID", "HR_ORG_POSITION_ID", "START_DATE", "ACTIVE", "DIC_EMP_STATUS"};
                    int[] etype = {0, 0, 2, 0, 0};
                    String[] eval = {String.valueOf(pID), String.valueOf(pp.getPositionID()), format.format(pp.getHireDate()), "1", empStatus};
                    df.makeUpdateDB(conn, constant.TABLE_MAN_EMPLOYEES, efield, eval, etype, String.valueOf(pp.getID()));
                } else {
                    empStatus = "1008";
                    String[] efield = {"COM_PERSON_ID", "HR_ORG_POSITION_ID", "START_DATE", "END_DATE", "ACTIVE", "DIC_EMP_STATUS"};
                    int[] etype = {0, 0, 2, 2, 0, 0};
                    String[] eval = {String.valueOf(pID), String.valueOf(pp.getPositionID()), format.format(pp.getHireDate()), format.format(pp.getFireDate()), "1", empStatus};
                    df.makeUpdateDB(conn, constant.TABLE_MAN_EMPLOYEES, efield, eval, etype, String.valueOf(pp.getID()));

                }
                df.makeUpdateDB(conn, constant.TABLE_COM_PERSONS, pfield, pval, ptype, String.valueOf(pp.getPersonID()));

                df.makeUpdateDB(conn, constant.TABLE_COM_PERSON_UNIQ, cfield, cval, ctype, String.valueOf(pp.getPincodeID()));
            }

            System.out.println("pID" + pID);
            //        saveEmpSecurity(conn, pp, empID);

            if ("1".equals(opType)) {


            } else if ("2".equals(opType)) {

            }
            if (piList != null) {
                for (subjElement ss : piList) {
                    if (ss.getPartID() == 10003) {
                        saveSubjList(conn, ss, pID, "1");
                    }
                }
            }
            if (pcList != null) {
                for (contact cc : pcList) {
                    String[] afield = {"COM_PERSON_ID", "DICT_CONTACT_TYPE_ID", "CONTACT_VALUE"};
                    String[] aval = {String.valueOf(pID), String.valueOf(cc.getContactTypeID()), cc.getContactVal()};
                    int[] atyp = {0, 0, 1};
                    if (cc.getState() == 0) {
                        df.makeInsertDB(conn, constant.TABLE_COM_PERSON_CONTACTS, afield, aval, atyp);
                    } else if (cc.getState() == 2) {
                        df.makeUpdateDB(conn, constant.TABLE_COM_PERSON_CONTACTS, afield, aval,
                                atyp, String.valueOf(cc.getPerContactID()));
                    } else if (cc.getState() == 3) {
                        String[] ff = {"ACTIVE"};
                        int[] tt = {0};
                        String[] vv = {"3"};
                        df.makeUpdateDB(conn, constant.TABLE_COM_PERSON_CONTACTS, ff, vv,
                                tt, String.valueOf(cc.getPerContactID()));
                    }
                }
            }


            if (dd != null) {
                String[] tfield = {"RELATED_ID", "PHOTO_URL", "ADD_DATE", "TYPID", "ACTIVE"};
                String[] tval = {String.valueOf(pID), dd.getFilePath(), format.format(new Date()),"1", "1"};
                int[] ttyp = {0, 1, 2, 0, 0};

                String[] ff = {"ACTIVE"};
                int[] tt = {0};
                String[] vv = {"2"};
                long id = findPicID(conn, pp.getID(), 1);
                df.makeUpdateDB(conn, constant.TABLE_COM_PERSON_PHOTO, ff, vv,
                        tt, String.valueOf(id));
                df.makeInsertDB(conn, constant.TABLE_COM_PERSON_PHOTO, tfield, tval, ttyp);

            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            dbFunction.close(conn);
        }

    }

    public void saveStrukturInfo(String opType, String orgID, String parentID, finalInfo pfList,
                                 List<address1> paList, List<contact> pcList) throws Exception {
        try {
            long orgID1 = 0;
            Date dt = new Date();
            conn = df.connectDB();
            String[] ofield = {"CREATE_DATE"};
            String[] oval = {format.format(pfList.getStDate())};
            int[] otyp = {2};
            if ("1".equals(opType)) {
                orgID1 = df.makeInsertDB(conn, constant.TABLE_ORG_TREE, ofield, oval, otyp);
                String[] ofield1 = {"FORMULA"};
                int[] otyp1 = {1};
                if ("-1".equals(parentID)) {
                    String[] oval1 = {String.valueOf(orgID1)};
                    df.makeUpdateDB(conn, constant.TABLE_ORG_TREE, ofield1, oval1, otyp1, String.valueOf(orgID1));
                } else {
//     TODO: // formula create
                    String parentFormula = findTreeFormula(conn, Long.parseLong(parentID), "1006");

                    parentFormula = parentFormula + "*" + String.valueOf(orgID1);
                    String[] oval1 = {parentFormula};
                    df.makeUpdateDB(conn, constant.TABLE_ORG_TREE, ofield1, oval1, otyp1, String.valueOf(orgID1));
                }
                String[] dfield = {"ORG_TREE_ID", "NAME", "DICT_ORG_TREE_TYPE", "START_DATE", "DESCRIPTION", "ORDER_BY"};
                String[] dval = {String.valueOf(orgID1), pfList.getoName(), String.valueOf(pfList.getFinTypeID()),
                        format.format(dt), pfList.getNotes(), String.valueOf(100)};
                int[] dtyp = {0, 1, 0, 2, 1, 0};
                df.makeInsertDB(conn, constant.TABLE_ORG_TREE_DETAILS, dfield, dval, dtyp);

                if (pfList.getAddress() != null && !("").equals(pfList.getAddress())) {
                    String[] dfield1 = {"ORG_TREE_ID", "CREATE_DATE", "ADDRESS"};
                    String[] dval1 = {String.valueOf(orgID1), format.format(dt), pfList.getAddress()};
                    int[] dtyp1 = {0, 2, 1};
                    df.makeInsertDB(conn, constant.ORG_TREE_INF_ADDRESS, dfield1, dval1, dtyp1);
                }
            } else if ("2".equals(opType)) {
                df.makeUpdateDB(conn, constant.TABLE_ORG_TREE, ofield, oval, otyp, orgID);
                orgID1 = Long.parseLong(orgID);
//     TODO: // update org_details
                if (pfList.getState() == 2) {
                    String[] ufield1 = {"END_DATE", "ACTIVE"};
                    int[] utyp1 = {2, 0};
                    String[] uval1 = {format.format(dt), "2"};
                    df.makeUpdateDB(conn, constant.TABLE_ORG_TREE_DETAILS, ufield1, uval1, utyp1, String.valueOf(pfList.getID()));
                    String[] dfield = {"ORG_TREE_ID", "NAME", "DICT_ORG_TREE_TYPE", "START_DATE", "DESCRIPTION", "ORDER_BY"};
                    String[] dval = {String.valueOf(orgID1), pfList.getoName(), String.valueOf(pfList.getFinTypeID()),
                            format.format(dt), pfList.getNotes(), String.valueOf(pfList.getOrder_by())};
                    int[] dtyp = {0, 1, 0, 2, 1, 0};
                    df.makeInsertDB(conn, constant.TABLE_ORG_TREE_DETAILS, dfield, dval, dtyp);

                    if (pfList.getAddrsID() != -1) {
                        String[] dfield1 = {"ORG_TREE_ID", "CREATE_DATE", "ADDRESS"};
                        String[] dval1 = {String.valueOf(orgID1), format.format(dt), pfList.getAddress()};
                        int[] dtyp1 = {0, 2, 1};
                        df.makeUpdateDB(conn, constant.ORG_TREE_INF_ADDRESS, dfield1, dval1, dtyp1, String.valueOf(pfList.getAddrsID()));
                    } else {
                        String[] dfield1 = {"ORG_TREE_ID", "CREATE_DATE", "ADDRESS"};
                        String[] dval1 = {String.valueOf(orgID1), format.format(dt), pfList.getAddress()};
                        int[] dtyp1 = {0, 2, 1};
                        df.makeInsertDB(conn, constant.ORG_TREE_INF_ADDRESS, dfield1, dval1, dtyp1);
                    }

                }
            }

            if (pcList != null) {
                for (contact cc : pcList) {
                    String[] afield = {"ORG_TREE_ID", "DICT_CONTACT_TYPE_ID", "CONTACT_VALUE"};
                    String[] aval = {String.valueOf(orgID1), String.valueOf(cc.getContactTypeID()), cc.getContactVal()};
                    int[] atyp = {0, 0, 1};
                    if (cc.getState() == 0) {
                        df.makeInsertDB(conn, constant.TABLE_ORG_TREE_CONTACT, afield, aval, atyp);
                    } else if (cc.getState() == 2) {
                        df.makeUpdateDB(conn, constant.TABLE_ORG_TREE_CONTACT, afield, aval,
                                atyp, String.valueOf(cc.getPerContactID()));
                    } else if (cc.getState() == 3) {
                        String[] afield1 = {"ACTIVE"};
                        String[] aval1 = {"3"};
                        int[] atyp1 = {0};
                        df.makeUpdateDB(conn, constant.TABLE_ORG_TREE_CONTACT, afield1, aval1,
                                atyp1, String.valueOf(cc.getPerContactID()));
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbFunction.close(conn);
        }
    }

    public void saveCategoryInfo(String opType, String orgID, String parentID, String partID, categoryFinalInfo pfList) throws Exception {
        try {
            long orgID1 = 0;
            Date dt = new Date();
            conn = df.connectDB();
            String[] ofield = {"CREATE_DATE", "CHANGE_DATE","CODE"};
            String[] oval = {format.format(dt), format.format(dt),pfList.getRegCodePart()};
            int[] otyp = {2, 2,1};
            if ("1".equals(opType)) {
                orgID1 = df.makeInsertDB(conn, constant.TABLE_CAT_TREE, ofield, oval, otyp);
                String[] ofield1 = {"FORMULA"};
                int[] otyp1 = {1};
                if ("-1".equals(parentID)) {
                    String[] oval1 = {String.valueOf(orgID1)};
                    df.makeUpdateDB(conn, constant.TABLE_CAT_TREE, ofield1, oval1, otyp1, String.valueOf(orgID1));
                } else {
//     TODO: // formula create
                    String parentFormula = findTreeFormula(conn, Long.parseLong(parentID), partID);
                    parentFormula = parentFormula + "*" + String.valueOf(orgID1);
                    String[] oval1 = {parentFormula};
                    df.makeUpdateDB(conn, constant.TABLE_CAT_TREE, ofield1, oval1, otyp1, String.valueOf(orgID1));
                }

//         TODO: //  CATEGORY  AZE
                String[] dfield = {"CATEGORY_ID", "CATEGORYNAME", "DICT_CATEGORYTYPE", "CREATE_DATE", "CHANGE_DATE", "ORDER_BY", "LANG_ID"};
                String[] dval = {String.valueOf(orgID1), pfList.getoName(), String.valueOf(pfList.getFinTypeID()),
                        format.format(dt), format.format(dt), String.valueOf(100), String.valueOf(1000)};
                int[] dtyp = {0, 1, 0, 2, 2, 0, 0};
                df.makeInsertDB(conn, constant.TABLE_CAT_TREE_DETAILS, dfield, dval, dtyp);
//         TODO: //  CATEGORY  RU
                String[] dvalRu = {String.valueOf(orgID1), pfList.getoNameRu(), String.valueOf(pfList.getFinTypeID()),
                        format.format(dt), format.format(dt), String.valueOf(100), String.valueOf(1001)};
                df.makeInsertDB(conn, constant.TABLE_CAT_TREE_DETAILS, dfield, dvalRu, dtyp);

//         TODO: //  CATEGORY  EN
                String[] dvalEn = {String.valueOf(orgID1), pfList.getoNameEn(), String.valueOf(pfList.getFinTypeID()),
                        format.format(dt), format.format(dt), String.valueOf(100), String.valueOf(1002)};
                df.makeInsertDB(conn, constant.TABLE_CAT_TREE_DETAILS, dfield, dvalEn, dtyp);
            } else if ("2".equals(opType)) {
                String[] ofield2 = {"CHANGE_DATE","CODE"};
                String[] oval2 = {format.format(dt),pfList.getRegCodePart()};
                int[] otyp2 = {2,1};
                df.makeUpdateDB(conn, constant.TABLE_CAT_TREE, ofield2, oval2, otyp2, orgID);
                orgID1 = Long.parseLong(orgID);
                //     TODO: // update org_details
                if (pfList.getState() == 2) {
                    //         TODO: //  CATEGORY  aze
                    String[] ufield1 = {"CATEGORYNAME", "DICT_CATEGORYTYPE", "CHANGE_DATE"};
                    int[] utyp1 = {1, 0, 2};
                    String[] uval1 = {pfList.getoName(), String.valueOf(pfList.getFinTypeID()), format.format(dt)};
                    df.makeUpdateDB(conn, constant.TABLE_CAT_TREE_DETAILS, ufield1, uval1, utyp1, String.valueOf(pfList.getLangIdAz()));
                    //         TODO: //  CATEGORY  ru

                    String[] uval1ru = {pfList.getoNameRu(), String.valueOf(pfList.getFinTypeID()), format.format(dt)};
                    df.makeUpdateDB(conn, constant.TABLE_CAT_TREE_DETAILS, ufield1, uval1ru, utyp1, String.valueOf(pfList.getLangIdRu()));
                    //         TODO: //  CATEGORY  en

                    String[] uval1en = {pfList.getoNameEn(), String.valueOf(pfList.getFinTypeID()), format.format(dt)};
                    df.makeUpdateDB(conn, constant.TABLE_CAT_TREE_DETAILS, ufield1, uval1en, utyp1, String.valueOf(pfList.getLangIdEn()));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbFunction.close(conn);
        }
    }

    @Override
    public void updatePassportPath(String exampleID, String filePath, long type) throws Exception {
        try {
            conn = df.connectDB();
            String[] ofield = {"FILEPATH"};
            String[] oval = {filePath};
            int[] otyp = {1};
            df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLES, ofield, oval, otyp, exampleID);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbFunction.close(conn);
        }
    }

    public void saveCarriersoryInfo(String opType, String carryID, String langID, carriersInfo pfList,docList dd) throws Exception {
        try {
            long carID = 0;
            Date dt = new Date();
            conn = df.connectDB();
            String[] ofield = {"CREATE_DATE", "CHANGE_DATE"};
            String[] oval = {format.format(dt), format.format(dt)};
            int[] otyp = {2, 2};
            if ("1".equals(opType)) {
                carID = df.makeInsertDB(conn, constant.TABLE_CAR, ofield, oval, otyp);

                if (pfList.getDesAz() == null) {
                    pfList.setDesAz("");
                }
                if (pfList.getDesRu() == null) {
                    pfList.setDesRu("");
                }
                if (pfList.getDesEn() == null) {
                    pfList.setDesEn("");
                }

//         TODO: //  carriers  AZE
                String[] dfield = {"CARRY_ID", "CARRYNAME", "CREATE_DATE", "CHANGE_DATE", "ORDER_BY", "LANG_ID", "DESCRIPTION"};
                String[] dval = {String.valueOf(carID), pfList.getCarryNameAz(), format.format(dt), format.format(dt), String.valueOf(100), String.valueOf(1000), pfList.getDesAz()};
                int[] dtyp = {0, 1, 2, 2, 0, 0, 1};
                df.makeInsertDB(conn, constant.TABLE_CAR_DETAILS, dfield, dval, dtyp);
//         TODO: //  carriers  RU
                String[] dvalRu = {String.valueOf(carID), pfList.getCarryNameRu(), format.format(dt), format.format(dt), String.valueOf(100), String.valueOf(1001), pfList.getDesRu()};
                df.makeInsertDB(conn, constant.TABLE_CAR_DETAILS, dfield, dvalRu, dtyp);
//         TODO: //  carriers  EN
                String[] dvalEn = {String.valueOf(carID), pfList.getCarryNameEn(), format.format(dt), format.format(dt), String.valueOf(100), String.valueOf(1002), pfList.getDesEn()};
                df.makeInsertDB(conn, constant.TABLE_CAR_DETAILS, dfield, dvalEn, dtyp);
            } else if ("2".equals(opType)) {
                String[] ofield2 = {"CHANGE_DATE"};
                String[] oval2 = {format.format(dt)};
                int[] otyp2 = {2};
                df.makeUpdateDB(conn, constant.TABLE_CAT_TREE, ofield2, oval2, otyp2, carryID);
                //  orgID1 = Long.parseLong(orgID);
                //     TODO: // update org_details
                if (pfList.getState() == 2) {
                    //         TODO: //  CATEGORY  aze
                    String[] ufield1 = {"CARRYNAME", "CREATE_DATE", "CHANGE_DATE", "DESCRIPTION"};
                    int[] utyp1 = {1, 2, 2, 1};
                    String[] uval1 = {pfList.getCarryNameAz(), format.format(dt), format.format(dt), pfList.getDesAz()};
                    df.makeUpdateDB(conn, constant.TABLE_CAR_DETAILS, ufield1, uval1, utyp1, String.valueOf(pfList.getLangIDaz()));
                    //         TODO: //  CATEGORY  ru

                    String[] uval1ru = {pfList.getCarryNameRu(), format.format(dt), format.format(dt), pfList.getDesRu()};
                    df.makeUpdateDB(conn, constant.TABLE_CAR_DETAILS, ufield1, uval1ru, utyp1, String.valueOf(pfList.getLangIDru()));
                    //         TODO: //  CATEGORY  en

                    String[] uval1en = {pfList.getCarryNameEn(), format.format(dt), format.format(dt), pfList.getDesEn()};
                    df.makeUpdateDB(conn, constant.TABLE_CAR_DETAILS, ufield1, uval1en, utyp1, String.valueOf(pfList.getLangIDen()));
                }
            }
            if (dd != null) {
                if(carID == 0){
                    carID = Long.parseLong(carryID);
                }
                String[] tfield = {"CHANGE_DATE", "FILEPATH"};
                String[] tval = {format.format(new Date()), dd.getFilePath()};
                int[] ttyp = {2, 1};
                df.makeUpdateDB(conn, constant.TABLE_CAR, tfield, tval,
                        ttyp, String.valueOf(carID));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbFunction.close(conn);
        }
    }

    public void saveOrganizationInfo(String opType, String carryID, String langID, carriersInfo pfList,docList dd) throws Exception {
        try {
            long carID = 0;
            Date dt = new Date();
            conn = df.connectDB();
            String[] ofield = {"CREATE_DATE", "CHANGE_DATE"};
            String[] oval = {format.format(dt), format.format(dt)};
            int[] otyp = {2, 2};
            if ("1".equals(opType)) {
                carID = df.makeInsertDB(conn, constant.TABLE_ORGAN, ofield, oval, otyp);

                if (pfList.getDesAz() == null) {
                    pfList.setDesAz("");
                }
                if (pfList.getDesRu() == null) {
                    pfList.setDesRu("");
                }
                if (pfList.getDesEn() == null) {
                    pfList.setDesEn("");
                }

//         TODO: //  carriers  AZE
                String[] dfield = {"ORGAN_ID", "ORGANNAME", "CREATE_DATE", "CHANGE_DATE", "ORDER_BY", "LANG_ID", "DESCRIPTION"};
                String[] dval = {String.valueOf(carID), pfList.getCarryNameAz(), format.format(dt), format.format(dt), String.valueOf(100), String.valueOf(1000), pfList.getDesAz()};
                int[] dtyp = {0, 1, 2, 2, 0, 0, 1};
                df.makeInsertDB(conn, constant.TABLE_ORGAN_DETAILS, dfield, dval, dtyp);
//         TODO: //  carriers  RU
                String[] dvalRu = {String.valueOf(carID), pfList.getCarryNameRu(), format.format(dt), format.format(dt), String.valueOf(100), String.valueOf(1001), pfList.getDesRu()};
                df.makeInsertDB(conn, constant.TABLE_ORGAN_DETAILS, dfield, dvalRu, dtyp);
//         TODO: //  carriers  EN
                String[] dvalEn = {String.valueOf(carID), pfList.getCarryNameEn(), format.format(dt), format.format(dt), String.valueOf(100), String.valueOf(1002), pfList.getDesRu()};
                df.makeInsertDB(conn, constant.TABLE_ORGAN_DETAILS, dfield, dvalEn, dtyp);
            } else if ("2".equals(opType)) {
                String[] ofield2 = {"CHANGE_DATE"};
                String[] oval2 = {format.format(dt)};
                int[] otyp2 = {2};
                df.makeUpdateDB(conn, constant.TABLE_ORGAN, ofield2, oval2, otyp2, carryID);
                //  orgID1 = Long.parseLong(orgID);
                //     TODO: // update org_details
                if (pfList.getState() == 2) {
                    //         TODO: //  CATEGORY  aze
                    String[] ufield1 = {"ORGANNAME", "CREATE_DATE", "CHANGE_DATE", "DESCRIPTION"};
                    int[] utyp1 = {1, 2, 2, 1};
                    String[] uval1 = {pfList.getCarryNameAz(), format.format(dt), format.format(dt), pfList.getDesAz()};
                    df.makeUpdateDB(conn, constant.TABLE_ORGAN_DETAILS, ufield1, uval1, utyp1, String.valueOf(pfList.getLangIDaz()));
                    //         TODO: //  CATEGORY  ru

                    String[] uval1ru = {pfList.getCarryNameRu(), format.format(dt), format.format(dt), pfList.getDesRu()};
                    df.makeUpdateDB(conn, constant.TABLE_ORGAN_DETAILS, ufield1, uval1ru, utyp1, String.valueOf(pfList.getLangIDru()));
                    //         TODO: //  CATEGORY  en

                    String[] uval1en = {pfList.getCarryNameEn(), format.format(dt), format.format(dt), pfList.getDesEn()};
                    df.makeUpdateDB(conn, constant.TABLE_ORGAN_DETAILS, ufield1, uval1en, utyp1, String.valueOf(pfList.getLangIDen()));
                }
            }
            if (dd != null) {
                if(carID == 0){
                    carID = Long.parseLong(carryID);
                }
                String[] tfield = {"CHANGE_DATE", "FILEPATH"};
                String[] tval = {format.format(new Date()), dd.getFilePath()};
                int[] ttyp = {2, 1};
                df.makeUpdateDB(conn, constant.TABLE_ORGAN, tfield, tval,
                        ttyp, String.valueOf(carID));

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbFunction.close(conn);
        }
    }


    public void updateExamplesStatus(String exampleID, String statusID, String reson, String userid) throws Exception {
        try {
            long carID = 0;
            Date dt = new Date();
            conn = df.connectDB();
            String[] ofield = {"ACTIVE"};
            String[] oval = {"2"};
            int[] otyp = {0};

            String eventTypeID = "";
            String[] of = {"FILEPATH"};
            String[] ov = {""};
            int[] ot = {1};
            df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLES, of, ov, ot, exampleID);

            if ("1037".equals(statusID)) {
                eventTypeID = "1018";
                String code =  getRegisterCode1(conn,exampleID);
                if(!"0".equals(code)){
                    long codeCount = GenerateRegisterCode(conn,code);
                    codeCount = codeCount + 1;
                    String codeCountString = "";
                    if(codeCount < 10) {
                        codeCountString = "000" + codeCount;
                    }else if(codeCount >= 10 && codeCount < 100){
                        codeCountString = "00"+codeCount;
                    }else if(codeCount >= 100 && codeCount < 1000){
                        codeCountString = "0"+codeCount;
                    }else{
                        codeCountString = String.valueOf(codeCount);
                    }
                    code = code + codeCountString;
                    String[] of_1 = {"REYESTER_CODE"};
                    String[] ov_1 = {code};
                    int[] ot_1 = {1};
                    df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLES, of_1, ov_1, ot_1, exampleID);
                }
            } else if ("1038".equals(statusID)) {
                eventTypeID = "1019";
            } else if ("1041".equals(statusID)) {
                eventTypeID = "1039";
            }
            String[] f1 = {"EXAMPLE_ID", "DICT_EVENT_TYPE_ID", "EVENT_DATE", "EVENT_COMMENT", "ACTIVE", "USER_ID"};
            String[] v1 = {exampleID, eventTypeID, format.format(dt), reson, "1", userid};
            int[] t1 = {0, 0, 2, 1, 0, 0};

            long id = findExampleStatusID(conn, Long.parseLong(exampleID));
            df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLE_STATUS, ofield, oval, otyp, String.valueOf(id));
            df.makeInsertDB(conn, constant.TABLE_MAN_EXAMPLE_STATUS, f1, v1, t1);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbFunction.close(conn);
        }
    }

    @Override
    public void saveUserInfo(String opType, usersInfo uInfo, long uID) throws Exception {
        CallableStatement cst = null;
        ResultSet rs = null;
        try {
            conn = df.connectDB();
            Date dt = new Date();
            String pass = org.apache.commons.codec.digest.DigestUtils.sha256Hex(uInfo.getPswd());
            int[] utyp1 = {0, 1, 0, 0, 1, 1, 1, 4, 0, 0, 0, 1, 1};

            long userID = -1;
            String rights = uInfo.getRights();
            if ("1".equals(opType)) {

                cst = conn.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.GET_DATA_FUNCTIONS.CONVERTPASSW(?) }");
                cst.registerOutParameter(1, OracleTypes.CURSOR);
                cst.setString(2, uInfo.getPswd());
                cst.execute();

                rs = (ResultSet) cst.getObject(1);
                while (rs.next()) {
                    uInfo.setPswd(rs.getString("PASW"));
                }


                String[] ufield = {"USER_NAME", "PASSWORD", "HR_EMP_ID", "ACTIVE", "USER_TYPE", "SYSTEM_TYPE"};
                String[] uval = {uInfo.getUserName(), uInfo.getPswd(),
                        String.valueOf(uInfo.getEmpid()), String.valueOf(uInfo.getUserAct()),
                        String.valueOf(uInfo.getUserType()), "1"};
                int[] utyp = {1, 1, 0, 0, 0, 0};
                userID = df.makeInsertDB(conn, constant.TABLE_SEC_USERS, ufield, uval, utyp);

            } else if ("2".equals(opType)) {
                String[] ufield = {"HR_EMP_ID", "ACTIVE", "USER_TYPE", "SYSTEM_TYPE"};
                String[] uval = {String.valueOf(uInfo.getEmpid()), String.valueOf(uInfo.getUserAct()),
                        String.valueOf(uInfo.getUserType()), "1"};
                int[] utyp = {0, 0, 0, 0};
                userID = uInfo.getId();
                df.makeUpdateDB(conn, constant.TABLE_SEC_USERS, ufield, uval, utyp, String.valueOf(userID));
                cst = conn.prepareCall("{ call CULTTREASREGISTER_COMMON.LOAD_MAIN_DATA_FUNCTIONS.DELETEUSERACCESS(?) }");
                cst.setLong(1, userID);
                cst.execute();
            }
            logger.info("opType= " + opType + " // userID = " + userID + " // rights =" + rights);
            String[] field3 = {"USER_ID", "MODUL_ID", "OPER_ID", "NOTE", "ACTIVE"};
            String[] value3 = null;
            int[] ptype3 = {0, 0, 0, 1, 0};
            int pos = -1;
            String menuID = "";
            String rr = "";
            String tt = "";
            while (rights.length() > 1) {
                value3 = new String[5];
                value3[0] = String.valueOf(userID);

                pos = rights.indexOf(":");
                menuID = rights.substring(0, pos);
                value3[1] = menuID;

                rights = rights.substring(pos + 1, rights.length());
                pos = rights.indexOf("/");
                rr = rights.substring(0, pos);
                rights = rights.substring(pos + 1, rights.length());
                while (rr.length() > 0) {
                    pos = rr.indexOf("*");
                    tt = rr.substring(0, pos);
                    value3[2] = tt;
                    value3[3] = "1";
                    value3[4] = "1";
                    rr = rr.substring(pos + 1, rr.length());
                    df.makeInsertDB(conn, constant.TABLE_SEC_USERS_ACCESS, field3, value3, ptype3);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error while save User error=" + ex.toString());
        } finally {
            dbFunction.close(cst, conn);
        }
    }

    private String findTreeFormula(Connection con, long parentID, String partID) throws Exception {
        String formula = "";
        CallableStatement cst = null;
        try {
            if ("1010".equals(partID)) {
                cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDTREEFORMULACAT(?) }");
            } else if ("1006".equals(partID)) {
                cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDTREEFORMULA(?) }");
            }
            cst.registerOutParameter(1, OracleTypes.VARCHAR);
            cst.setLong(2, parentID);
            cst.execute();
            formula = cst.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
        return formula;
    }


    private long findExampleDetailsID(Connection con, long exmlID, long parametrID, long number) throws Exception {
        long id = 0;
        CallableStatement cst = null;
        try {
            if (number == 1) {
                cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDEXAMPLEDETAILSID(?,?) }");
            } else if (number == 2) {
                cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDCATEGORYDETAILSID(?,?) }");
            } else if (number == 3) {
                cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDORGANIZATIONDETAILSID(?,?) }");
            } else if (number == 4) {
                cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDCARRIERSDETAILSID(?,?) }");
            } else if (number == 5) {
                cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDPERSONID(?,?) }");
            } else if (number == 6) {
                cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDEXAMPLEFILEDSID(?,?) }");
            }

            cst.registerOutParameter(1, OracleTypes.VARCHAR);
            cst.setLong(2, exmlID);
            cst.setLong(3, parametrID);
            cst.execute();
            id = cst.getLong(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
        return id;
    }

    private long findExampleStatusID(Connection con, long exmlID) throws Exception {
        long id = 0;
        CallableStatement cst = null;
        try {
            cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDEXAMPLESTATUSID(?) }");

            cst.registerOutParameter(1, OracleTypes.VARCHAR);
            cst.setLong(2, exmlID);
            cst.execute();
            id = cst.getLong(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
        return id;
    }


    private void deleteMultiOptions(Connection con, long exmlID) throws Exception {
        CallableStatement cst = null;
        try {
            cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.DELETEMULTIOPTIONS(?) }");
            cst.registerOutParameter(1, OracleTypes.VARCHAR);
            cst.setLong(2, exmlID);
            cst.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
    }


    private void updateConnetcBetweenCatExamp(Connection con, long exmlID) throws Exception {
        CallableStatement cst = null;
        try {
            cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.DELETECONNECTCATEXAMP(?) }");
            cst.registerOutParameter(1, OracleTypes.VARCHAR);
            cst.setLong(2, exmlID);
            cst.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
    }

    private long connectCategoryWithExample(Connection con, long exmlID) throws Exception {
        long id = 0;
        CallableStatement cst = null;
        try {
            cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.CONNECTCATEGORYEXAMPLE(?) }");
            cst.registerOutParameter(1, OracleTypes.VARCHAR);
            cst.setLong(2, exmlID);
            cst.execute();
            id = cst.getLong(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
        return id;
    }

    @Override
    public void saveSubj(String subjTypeID, String cVal0AZ, String cVal0RU, String cVal0EN, String cValAZ, String cValRU, String cValEN, String newEditmp, String realID) throws Exception {
        try {
            conn = df.connectDB();
            if ("1".equals(newEditmp)) {
                if ("10014".equals(subjTypeID)) {
                    String[] mf1_1 = {"ORG_TREE_ID", "DICT_POSITION_ID", "ACTIVE"};
                    int[] mt1_1 = {0, 0, 0};
                    String[] mv1_1 = {cValAZ, cVal0AZ, "1"};
                    df.makeInsertDB(conn, constant.TABLE_HR_ORG_POSITION, mf1_1, mv1_1, mt1_1);
                } else {
                    long id = 1;
                    String[] mf1 = {"DIC_TYPE_ID", "FULL_NAME", "SHORT_NAME"};
                    int[] mt1 = {0, 1, 1};
                    String[] mv1 = {subjTypeID, cVal0AZ, cValAZ};
                    id = df.makeInsertDB(conn, constant.TABLE_COM_DICTIONARY, mf1, mv1, mt1);

                    //             TODO: //  Diller lazim olarsa
                    String[] mf2 = {"RESOURCE_ID", "LANG_ID", "VALUE", "TIP"};
                    int[] mt2 = {0, 0, 1, 0};
                    String[] mvAZ = {String.valueOf(id), "1000", cValAZ, "3"};
                    df.makeInsertDB(conn, constant.TABLE_COM_LOCALE_RESOURCE, mf2, mvAZ, mt2);

                    String[] mvRU = {String.valueOf(id), "1001", cValRU, "3"};
                    df.makeInsertDB(conn, constant.TABLE_COM_LOCALE_RESOURCE, mf2, mvRU, mt2);

                    String[] mvEN = {String.valueOf(id), "1002", cValEN, "3"};
                    df.makeInsertDB(conn, constant.TABLE_COM_LOCALE_RESOURCE, mf2, mvEN, mt2);

                    String[] dicAZ = {String.valueOf(id), "1000", cVal0AZ, "2"};
                    df.makeInsertDB(conn, constant.TABLE_COM_LOCALE_RESOURCE, mf2, dicAZ, mt2);

                    String[] dicRU = {String.valueOf(id), "1001", cVal0RU, "2"};
                    df.makeInsertDB(conn, constant.TABLE_COM_LOCALE_RESOURCE, mf2, dicRU, mt2);

                    String[] dicEN = {String.valueOf(id), "1002", cVal0EN, "2"};
                    df.makeInsertDB(conn, constant.TABLE_COM_LOCALE_RESOURCE, mf2, dicEN, mt2);
                }

            } else if ("2".equals(newEditmp)) {
                if ("10014".equals(subjTypeID)) {
                    String[] mf1_1 = {"ORG_TREE_ID", "DICT_POSITION_ID"};
                    int[] mt1_1 = {0, 0};
                    String[] mv1_1 = {cValAZ, cVal0AZ};
                    df.makeUpdateDB(conn, constant.TABLE_HR_ORG_POSITION, mf1_1, mv1_1, mt1_1, realID);
                } else {
                    String[] mf1 = {"FULL_NAME", "SHORT_NAME"};
                    int[] mt1 = {1, 1};
                    String[] mv1 = {cVal0AZ, cValAZ};

                    df.makeUpdateDB(conn, constant.TABLE_COM_DICTIONARY, mf1, mv1, mt1, realID);
                }
            } else if ("3".equals(newEditmp)) {
                String[] mf1 = {"ACTIVE"};
                int[] mt1 = {0};
                String[] mv1 = {"0"};
                if ("10014".equals(subjTypeID)) {
                    df.makeUpdateDB(conn, constant.TABLE_HR_ORG_POSITION, mf1, mv1, mt1, realID);
                } else {
                    df.makeUpdateDB(conn, constant.TABLE_COM_DICTIONARY, mf1, mv1, mt1, realID);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error while save User error=" + ex.toString());
        } finally {
            dbFunction.close(conn);
        }
    }

    private long saveSubjList(Connection con, subjElement ss, long perID, String typ) throws Exception {
        long res = -1;
        Date dt = new Date();
        try {
            String[] ifield = {"RELATED_ID", "SUB_ELEMENT_ID", "SUB_ELEM_STR_VAL", "SUB_ELEM_NUMBER_VAL",
                    "SUB_ELEM_DATE_VAL", "SUB_ELEM_DICT_ID", "SUB_ELEM_ADD_ID", "SUB_ELEM_PERSON_ID",
                    "SUB_ELEM_ADD_DICT_ID", // bu ishlenmeyecek
                    "SUB_ELEM_LIST_ID", "SUB_ELEM_YEAR_MONTH_DAY", "TABLE_TYPE_ID"};
            int[] itype = {0, 0, 1, 0, 2, 0, 0, 0, 0, 1, 1, 0};
            String[] ivalue = {};
            String stat = "";
            ivalue = new String[12];
            ivalue[0] = String.valueOf(perID);
            ivalue[1] = String.valueOf(ss.getSubjectID());
            ivalue[2] = "";
            ivalue[3] = "";
            ivalue[4] = "";
            ivalue[5] = "";
            ivalue[6] = "";
            ivalue[7] = "";
            ivalue[8] = "";
            ivalue[9] = "";
            ivalue[10] = "";
            ivalue[11] = typ;
            switch (Math.round(ss.getSubjectTypeID())) {
                case 1000: {
                    ivalue[2] = ss.getSubjectValue();
                    break;
                } // str
                case 1001: {
                    ivalue[3] = ss.getSubjectValue();
                    break;
                } // int
                case 1002: {
                    dt = format.parse(ss.getSubjectValue());  // date
                    String t2 = format.format(dt);
                    ivalue[4] = t2;
                    break;
                }
                case 1003: {
                    ivalue[5] = String.valueOf(ss.getListID());
                    break;
                }   //combo
                case 1004: {
                    ivalue[6] = String.valueOf(ss.getListID());
                    break;
                }   //address
                case 1005: {
                    ivalue[7] = String.valueOf(ss.getListID());
                    break;
                }   //person
                case 1006: {
                    ivalue[5] = String.valueOf(ss.getListID());
                    break;
                }   //add combo 8
                case 1007: {
                    ivalue[9] = ss.getSubjList();
                    break;
                } // list info
                case 1008: {
                    ivalue[10] = ss.getSubjParseDate();
                    break;
                } // parse date
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error while saving Subj Info = " + ex.toString());
        }
        return res;
    }

    @Override
    public void deleteData(String dic_thema_id, String partID, String realID) throws Exception {
        CallableStatement cst = null;
        try {

            conn = df.connectDB();
            Date dt = new Date();
            long regID = 0;

            String[] mf1 = {"ACTIVE"};
            int[] mt1 = {0};
            String[] mv1 = {"3"};
            String[] eval = {"0"};

            if ("1002".equals(partID)) {
                updateMultiOptionalTables(conn, realID, constant.TABLE_ORGAN_DETAILS, 3);
                df.makeUpdateDB(conn, constant.TABLE_ORGAN, mf1, mv1, mt1, realID);
            } else if ("1012".equals(partID)) {
                updateMultiOptionalTables(conn, realID, constant.TABLE_CAR_DETAILS, 4);
                df.makeUpdateDB(conn, constant.TABLE_CAR, mf1, mv1, mt1, realID);
            } else if ("1010".equals(partID)) {
                updateMultiOptionalTables(conn, realID, constant.TABLE_CAT_TREE_DETAILS, 2);
                df.makeUpdateDB(conn, constant.TABLE_CAT_TREE, mf1, mv1, mt1, realID);
            } else if ("1008".equals(partID) || "1009".equals(partID) || "1011".equals(partID)) {
                updateMultiOptionalTables(conn, realID, constant.TABLE_MAN_EXAMPLE_INF, 1);
                df.makeUpdateDB(conn, constant.TABLE_MAN_EXAMPLES, mf1, eval, mt1, realID);
            } else if ("1006".equals(partID)) {
                df.makeUpdateDB(conn, constant.TABLE_ORG_TREE, mf1, eval, mt1, realID);
            } else if ("1007".equals(partID)) {
                df.makeUpdateDB(conn, constant.TABLE_SEC_USERS, mf1, eval, mt1, realID);
            } else if ("1001".equals(partID)) {
                long id = findExampleDetailsID(conn, Long.parseLong(realID), 1000, 5);
                df.makeUpdateDB(conn, constant.TABLE_COM_PERSONS, mf1, eval, mt1, String.valueOf(id));
                df.makeUpdateDB(conn, constant.TABLE_MAN_EMPLOYEES, mf1, eval, mt1, realID);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dbFunction.close(cst);
            dbFunction.close(conn);
        }
    }

    private String findCountForCode(Connection con, String typeID) throws Exception {
        String mID = "";
        String Count = "";
        CallableStatement cst = null;
        try {
            if (!"".equals(typeID) && typeID != null) {
                String[] regList = typeID.split("/---/");
                for (int i = 0; i < regList.length; i++) {
                    String numberAsString = regList[i];

                    cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDREGISTERCODE(?) }");
                    cst.registerOutParameter(1, OracleTypes.VARCHAR);
                    cst.setLong(2, Long.parseLong(numberAsString));
                    cst.execute();
                    mID = cst.getString(1);
                   // Count = Count + mID;
                }

            }


        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl find findCountEmpSecurity = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
        return mID;
    }

    private String getRegisterCode1(Connection con,String exmpID) throws Exception {
        String Code = "";
        CallableStatement cst = null;
        try {
                    cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.GETREGISTERCODE1(?) }");
                    cst.registerOutParameter(1, OracleTypes.VARCHAR);
                    cst.setLong(2, Long.parseLong(exmpID));
                    cst.execute();
                    Code = cst.getString(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl find findCountEmpSecurity = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
        return Code;
    }

    private long GenerateRegisterCode(Connection con, String code) throws Exception {
        long mID = 0;
        CallableStatement cst = null;
        try {
                    cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.CREATEREGISTERCODE(?) }");
                    cst.registerOutParameter(1, OracleTypes.VARCHAR);
                    cst.setString(2, code);
                    cst.execute();
                    mID = cst.getLong(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl find findCountEmpSecurity = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
        return mID;
    }

    private long findqrCodePicID(Connection con, long exmplID, long typeID) throws Exception {
        long mID = 0;
        CallableStatement cst = null;
        try {
            cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDQRCODETBID(?,?) }");
            cst.registerOutParameter(1, OracleTypes.VARCHAR);
            cst.setLong(2, exmplID);
            cst.setLong(3, typeID);
            cst.execute();
            mID = cst.getLong(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl find findCountEmpSecurity = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
        return mID;
    }

    private void updateMultiOptionalTables(Connection conn, String realID, String tableName, long number) throws Exception {
        String[] mf1 = {"ACTIVE"};
        int[] mt1 = {0};
        String[] mv1 = {"3"};
        String[] eval = {"0"};

        int[] langID = {1000, 1001, 1002};
        for (int i = 0; i < langID.length; i++) {
            long id = findExampleDetailsID(conn, Long.parseLong(realID), langID[i], number);
            df.makeUpdateDB(conn, tableName, mf1, eval, mt1, String.valueOf(id));
        }
    }

    private long findPicID(Connection con, long id, long typeID) throws Exception {
        long mID = 0;
        CallableStatement cst = null;
        try {
            cst = con.prepareCall("{ ?= call CULTTREASREGISTER_COMMON.FIND_DATA_FUNCTIONS.FINDPICTUREID(?,?) }");
            cst.registerOutParameter(1, OracleTypes.VARCHAR);
            cst.setLong(2, id);
            cst.setLong(3, typeID);
            cst.execute();
            mID = cst.getLong(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Error daoImpl find findCountEmpSecurity = " + ex.toString());
        } finally {
            dbFunction.close(cst);
        }
        return mID;
    }

}
