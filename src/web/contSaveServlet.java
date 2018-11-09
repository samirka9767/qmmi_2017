package web;

import domain.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.tomcat.util.codec.binary.Base64;
import service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import utils.ftpUtility;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.acl.LastOwnerException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Created by a.ulviyya on 02.07.2016.
 */
@WebServlet(name = "contSaveServlet")
public class contSaveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Log logger = LogFactory.getLog(getClass());
        HttpSession session = request.getSession();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        saveDataService svService = new saveDataServiceImpl();
        String address = null;
        Boolean forward = false;
        usersInfo usersInfo = null;
        String ACTION = "action";
        loadDataService ldService = new loadDataServiceImpl();
        try {
            ACTION = request.getParameter("action");
            usersInfo = (usersInfo) session.getAttribute(constant.USER);
            if (ACTION.equalsIgnoreCase(constant.ACTION_SAVE_STRUKTUR)) {
                response.setContentType("text/html;charset=UTF-8");
                try {
                    String partID = request.getParameter("partID");
                    if ("1010".equals(partID)) {
                        String categoryType = request.getParameter("categoryType");
                        String regCodePart = request.getParameter("regCodePart");
                        String categorytName = request.getParameter("categorytName");
                        String categorytNameRu = request.getParameter("categorytNameRu");
                        String categorytNameEn = request.getParameter("categorytNameEn");
                        String parentID = request.getParameter("parentID");
                        String orgID = request.getParameter("orgID");
                        String opType = request.getParameter("operType");

                        categoryFinalInfo pfList = (categoryFinalInfo) session.getAttribute(constant.SES_CATEGORY_INFO);
                        if (pfList == null) {
                            pfList = new categoryFinalInfo();
                        }

                        if ("1".equals(opType)) {
                            pfList.setID(-1);
                            pfList.setState(0);
                            pfList.setFinTypeID(Long.parseLong(categoryType));
                            pfList.setRegCodePart(regCodePart);
                            pfList.setoName(categorytName);
                            pfList.setoNameRu(categorytNameRu);
                            pfList.setoNameEn(categorytNameEn);
                        } else {
                            long st = pfList.getState();
                            if (pfList.getFinTypeID() != Long.parseLong(categoryType)) {
                                pfList.setFinTypeID(Long.parseLong(categoryType));
                                st = 2;
                            }
                            if (pfList.getoName().trim() == null ? categorytName.trim() != null : !pfList.getoName().trim().equals(categorytName.trim())) {
                                pfList.setoName(categorytName);
                                st = 2;
                            }
                            //if (pfList.getRegCodePart().trim() == null ? regCodePart.trim() != null : !pfList.getRegCodePart().trim().equals(regCodePart.trim())) {
                                pfList.setRegCodePart(regCodePart);
                                st = 2;
                           //}

                        //    if (pfList.getoNameRu().trim() == null ? categorytNameRu.trim() != null : !pfList.getoNameRu().trim().equals(categorytNameRu.trim())) {
                                pfList.setoNameRu(categorytNameRu);
                                st = 2;
                        //    }
                           //  if (pfList.getoNameEn().trim() == null ? categorytNameEn.trim() != null : !pfList.getoNameEn().trim().equals(categorytNameEn.trim())) {
                                pfList.setoNameEn(categorytNameEn);
                                st = 2;
                             // }
                            if (st != pfList.getState()) {
                                pfList.setState(2);
                            }
                        }
                        svService.saveCategoryInfo(opType, orgID, parentID, partID, pfList);

                    } else if ("1006".equals(partID)) {
                        String oTypeID = request.getParameter("sOrgTypeID");
                        String oOrgName = request.getParameter("inOrgName");
                        String oOrgAddress = request.getParameter("inOrgAddress");
                        String oOrgDate = request.getParameter("inOrgDate");
                        String oOrgNote = request.getParameter("inOrgNote");
                        String parentID = request.getParameter("parentID");
                        String orgID = request.getParameter("orgID");
                        String opType = request.getParameter("operType");

                        List<address1> paList = (List<address1>) session.getAttribute(constant.SES_PADDRESS);
                        List<contact> pcList = (List<contact>) session.getAttribute(constant.SES_PCONTACTS);

                        finalInfo pfList = (finalInfo) session.getAttribute(constant.SES_ORG_INFO);
                        if (pfList == null) {
                            pfList = new finalInfo();
                        }

                        if ("1".equals(opType)) {
                            pfList.setID(-1);
                            pfList.setState(0);
                            pfList.setFinTypeID(Long.parseLong(oTypeID));
                            pfList.setStDate(format.parse(oOrgDate));
                            pfList.setoName(oOrgName);
                            pfList.setNotes(oOrgNote);
                            pfList.setAddress(oOrgAddress);
                        } else {
                            long st = pfList.getState();
                            if (pfList.getFinTypeID() != Long.parseLong(oTypeID)) {
                                pfList.setFinTypeID(Long.parseLong(oTypeID));
                                st = 2;
                            }
                            if (pfList.getStDate() != format.parse(oOrgDate)) {
                                pfList.setStDate(format.parse(oOrgDate));
                                st = 2;
                            }
                            if (pfList.getoName().trim() == null ? oOrgName.trim() != null : !pfList.getoName().trim().equals(oOrgName.trim())) {
                                pfList.setoName(oOrgName);
                                st = 2;
                            }
                            if (pfList.getNotes() == null ? oOrgNote.trim() != null : !pfList.getNotes().equals(oOrgNote.trim())) {
                                pfList.setNotes(oOrgNote);
                                st = 2;
                            }
                            if (pfList.getAddress() == null ? oOrgAddress.trim() != null : !pfList.getAddress().equals(oOrgAddress.trim())) {
                                pfList.setAddress(oOrgAddress);
                                st = 2;
                            }
                            if (st != pfList.getState()) {
                                pfList.setState(2);
                            }
                        }
                        svService.saveStrukturInfo(opType, orgID, parentID, pfList, paList, pcList);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    session.removeAttribute(constant.SES_PCONTACTS);
                    session.removeAttribute(constant.SES_ORG_INFO);
                }
                address = null;
                forward = false;
            } else if (ACTION.equalsIgnoreCase(constant.ACTION_SAVE_USERS)) {
                response.setContentType("text/html;charset=UTF-8");
                try {
                    String usID = "0";
                    String pID = "0";
                    String opType = request.getParameter("operType");
                    String act = request.getParameter("act");
                    String usr = request.getParameter("usr");
                    String rights = request.getParameter("rights");

                    String empID = request.getParameter("sEmpName");
                    String uName = request.getParameter("uName");
                    uName = uName.trim();
                    System.out.println("K" + uName + "P");
                    String pass = request.getParameter("passw");

                    if ("2".equals(opType)) {
                        usID = request.getParameter("usID");
                        pID = request.getParameter("pID");
                    }
                    usersInfo uInfo = new usersInfo();
                    long uID = usersInfo.getId();
                    uInfo.setId(Long.parseLong(usID));
                    uInfo.setEmpid(Long.parseLong(empID));
                    uInfo.setPerid(Long.parseLong(pID));
                    uInfo.setUserName(uName);
                    uInfo.setPswd(pass);
                    uInfo.setUserAct(Long.parseLong(act));
                    uInfo.setUserType(Long.parseLong(usr));
                    uInfo.setRights(rights);
                    svService.saveUserInfo(opType, uInfo, uID);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    logger.info("Error while change camera error = " + ex.toString());
                }
                address = null;
                forward = false;
            } else if (ACTION.equalsIgnoreCase(constant.ACTION_SAVE_EMPLOYEE_INFO)) {

                try {
                    person pp = new person();
                    String fname = request.getParameter("fname");
                    String mName = request.getParameter("mName");
                    String lname = request.getParameter("lname");
                    String orgID = request.getParameter("empOrganization");
                    String positionID = request.getParameter("empPosition");
                    String hireDate = request.getParameter("hireDate");
                    String fireDate = request.getParameter("fireDate");
                    String pinCode = request.getParameter("pinCode");
                    String genderID = request.getParameter("gender");
                    String empBirthday = request.getParameter("empBirthday");

                    String opType = request.getParameter("operType");

                    pp.setfName(fname);
                    pp.setmName(mName);
                    pp.setlName(lname);
                    pp.setOrgID(Long.parseLong(orgID));
                    pp.setPositionID(Long.parseLong(positionID));
                    pp.setHireDate(format.parse(hireDate));
                    if (!"".equals(fireDate)) {
                        pp.setFireDate(format.parse(fireDate));
                    }

                    if (!("").equals(pinCode)) {
                        pp.setPinCode(pinCode);
                    }
                    if (genderID != null) {
                        pp.setGenderID(Long.parseLong(genderID));
                    }
                    if (empBirthday != null) {
                        pp.setBirthDate(format.parse(empBirthday));
                    }


                    List<subjElement> piList = (List<subjElement>) session.getAttribute(constant.SES_PINFO);
                    List<contact> pcList = (List<contact>) session.getAttribute(constant.SES_PCONTACTS);
                    List<docList> dsL = (List<docList>) session.getAttribute(constant.SES_SPEMPLOYEEPH);
                    docList dd = null;
                    if (dsL != null) {
                        for (docList ds : dsL) {
                            if (ds.getDocCategory() == 3) {
                                dd = new docList();
                                dd = ds;
                            }
                        }
                    }
                    if ("2".equalsIgnoreCase(opType)) {
                        String id = request.getParameter("id");
                        String cid = request.getParameter("cid");
                        String perID = request.getParameter("perId");
                        pp.setID(Long.parseLong(id));
                        pp.setPersonID(Long.parseLong(perID));
                        pp.setPinCodeID(Long.parseLong(cid));
                    }
//                    else {
//                       pp.setID(0);
//                       pp.setPersonID(0);
//                   }
                    svService.saveEmployeeInfo(pp, opType, piList, pcList, dd);

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    session.removeAttribute(constant.SES_PINFO);
                    session.removeAttribute(constant.SES_PCONTACTS);
                    session.removeAttribute(constant.SES_PEDUCATION);
                    session.removeAttribute(constant.SES_SPEMPLOYEEPH);
                }
                address = null;
                forward = false;

            } else if (ACTION.equalsIgnoreCase(constant.ACTION_UPDATE_EXAMPLE_STATUS)) {

                try {
                    String exampleID = request.getParameter("exmpID22");
                    String statusID = request.getParameter("statusID");
                    String reson = request.getParameter("reson");
                    String partID = request.getParameter("partID");
                    long part = Long.parseLong(partID);
                    svService.updateExamplesStatus(exampleID, statusID, reson, String.valueOf(usersInfo.getId()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                }
                address = null;
                forward = false;

            } else if (ACTION.equalsIgnoreCase(constant.ACTION_SAVE_EXAMPLES_INFO)) {
                try {
                    examples pp = new examples();
                    String exmNameAz = request.getParameter("exmNameAz");
                    String exmNameRu = request.getParameter("exmNameRu");
                    String exmNameEn = request.getParameter("exmNameEn");
                    String eDate = request.getParameter("eDate");

                    String exmKeyWordAz = request.getParameter("exmKeyWordAz");
                    String exmKeyWordRu = request.getParameter("exmKeyWordRu");
                    String exmKeyWordEn = request.getParameter("exmKeyWordEn");

                    String textAz = request.getParameter("textAz");
                    String textRu = request.getParameter("textRu");
                    String textEn = request.getParameter("textEn");

                    String noteAz = request.getParameter("noteAz");
                    String noteRu = request.getParameter("noteRu");
                    String noteEn = request.getParameter("noteEn");

                    String categoryID = request.getParameter("arrayCategoryList");
                    String groupID = request.getParameter("arrayGroupList");
                    String exmClassID = request.getParameter("arrayClassList");
                    String exmTypeID = request.getParameter("arrayTypeList");
                    String exmGenreID = request.getParameter("arrayGenreList");
                    String regionID = request.getParameter("arrayRegionID");
                    String OvertakenRegionID = request.getParameter("OvertakenRegionID");
                    String mete = request.getParameter("mete");
                    String carriersList = request.getParameter("carriesListId");
                    String organsList = request.getParameter("organsListId");
                    String yuneskoListId = request.getParameter("yuneskoListId");

                    String opType = request.getParameter("operType");

                    pp.setExmNameAz(exmNameAz);
                    pp.setExmNameRu(exmNameRu);
                    pp.setExmNameEn(exmNameEn);
                    pp.setExmKeyWordAz(exmKeyWordAz);
                    pp.setExmKeyWordRu(exmKeyWordRu);
                    pp.setExmKeyWordEn(exmKeyWordEn);
                    pp.setTextAz(textAz);
                    pp.setTextRu(textRu);
                    pp.setTextEn(textEn);
                    pp.setNoteAz(noteAz);
                    pp.setNoteRu(noteRu);
                    pp.setNoteEn(noteEn);
                    if(mete != null){
                        pp.setMete(Long.parseLong(mete));
                    }
                    if(!"".equals(eDate) && eDate != null) {
                        pp.seteDate(format.parse(eDate));
                    }
                    pp.setCategoryID(categoryID);
                    pp.setGroupID(groupID);
                    pp.setExmClassID(exmClassID);
                    pp.setExmTypeID(exmTypeID);
                    pp.setExmGenreID(exmGenreID);
                    pp.setRegionID(regionID);
                    if(OvertakenRegionID != null){
                        pp.setOvertakenRegionID(Long.parseLong(OvertakenRegionID));
                    }
                    pp.setCarriersList(carriersList);
                    pp.setOrgansList(organsList);
                    pp.setYuneskoList(yuneskoListId);


                    List<subjElement> piList = (List<subjElement>) session.getAttribute(constant.SES_PINFO);
                    List<contact> pcList = (List<contact>) session.getAttribute(constant.SES_PCONTACTS);
                    List<docList> dsL = (List<docList>) session.getAttribute(constant.SES_PICTURES);
                    List<docList> qrDsl = (List<docList>) session.getAttribute(constant.SES_QRCODE);

                    if ("2".equalsIgnoreCase(opType)) {
                        String id = request.getParameter("id");
                        pp.setID(Long.parseLong(id));
                    }
                    svService.saveExamplesInfo(pp, opType, piList, pcList, dsL, qrDsl, String.valueOf(usersInfo.getId()));

                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    session.removeAttribute(constant.SES_PINFO);
                    session.removeAttribute(constant.SES_PICTURES);
                    session.removeAttribute(constant.SES_PCONTACTS);
                    session.removeAttribute(constant.SES_QRCODE);
                }
                address = null;
                forward = false;

            } else if (ACTION.equalsIgnoreCase(constant.ACTION_SAVE_CARRIERS_INFO)) {
                try {
                    carriersInfo pfList = null;
                    docList dd = null;
                    String partID = request.getParameter("partID");
                    String carryID = request.getParameter("id");
                    String langID = request.getParameter("langID");
                    String cNameAz = request.getParameter("cNameAz");
                    String cNameRu = request.getParameter("cNameRu");
                    String cNameEn = request.getParameter("cNameEn");
                    String CTextAz = request.getParameter("CTextAz");
                    String CTextRu = request.getParameter("CTextRu");
                    String CTextEn = request.getParameter("CTextEn");
                    String opType = request.getParameter("operType");
                    if ("1002".equals(partID)) {
                        pfList = (carriersInfo) session.getAttribute(constant.SES_ORGAN_INFO);
                        List<docList> dsL = (List<docList>) session.getAttribute(constant.SES_CAR_ORG);
                        if (dsL != null) {
                            for (docList ds : dsL) {
                                if (ds.getDocCategory() == 2) {
                                    dd = new docList();
                                    dd = ds;
                                }
                            }
                        }
                    } else if ("1012".equals(partID)) {
                        List<docList> dsL = (List<docList>) session.getAttribute(constant.SES_CAR_ORG1);
                        if (dsL != null) {
                            for (docList ds : dsL) {
                                if (ds.getDocCategory() == 2) {
                                    dd = new docList();
                                    dd = ds;
                                }
                            }
                        }
                        pfList = (carriersInfo) session.getAttribute(constant.SES_CARRY_INFO);
                    }
                    if (pfList == null) {
                        pfList = new carriersInfo();
                    }
                    if ("1".equals(opType)) {
                        pfList.setState(0);
                        pfList.setCarryNameAz(cNameAz);
                        pfList.setCarryNameRu(cNameRu);
                        pfList.setCarryNameEn(cNameEn);
                        pfList.setDesAz(CTextAz);
                        pfList.setDesRu(CTextRu);
                        pfList.setDesEn(CTextEn);
                        if ("1002".equals(partID)) {
                            svService.saveOrganizationInfo(opType, "", "", pfList, dd);
                        } else if ("1012".equals(partID)) {
                            svService.saveCarriersoryInfo(opType, "", "", pfList, dd);
                        }
                    } else if ("2".equals(opType)) {
                        long st = pfList.getState();
                        if (pfList.getCarryNameAz().trim() == null ? cNameAz.trim() != null : !pfList.getCarryNameAz().trim().equals(cNameAz.trim())) {
                            pfList.setCarryNameAz(cNameAz);
                            st = 2;
                        }
//                        if (pfList.getCarryNameRu().trim() == null ? cNameRu.trim() != null : !pfList.getCarryNameRu().trim().equals(cNameRu.trim())) {
                            pfList.setCarryNameRu(cNameRu);
                            st = 2;
//                        }
//                        if (pfList.getCarryNameEn().trim() == null ? cNameEn.trim() != null : !pfList.getCarryNameEn().trim().equals(cNameEn.trim())) {
                            pfList.setCarryNameEn(cNameEn);
                            st = 2;
//                        }
//                        if (pfList.getDesAz() == null ? CTextAz.trim() != null : !pfList.getDesAz().equals(CTextAz.trim())) {
                            pfList.setDesAz(CTextAz);
                            st = 2;
//                        }
//                        if (pfList.getDesRu() == null ? CTextRu.trim() != null : !pfList.getDesRu().equals(CTextRu.trim())) {
                            pfList.setDesRu(CTextRu);
                            st = 2;
//                        }
//                        if (pfList.getDesEn() == null ? CTextEn.trim() != null : !pfList.getDesEn().equals(CTextEn.trim())) {
                            pfList.setDesEn(CTextEn);
                            st = 2;
//                        }
                        if (st != pfList.getState()) {
                            pfList.setState(2);
                        }
                        if ("1002".equals(partID)) {
                            svService.saveOrganizationInfo(opType, carryID, "", pfList, dd);
                        } else if ("1012".equals(partID)) {
                            svService.saveCarriersoryInfo(opType, carryID, "", pfList, dd);
                        }
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                address = null;
                forward = false;

            } else if (ACTION.equalsIgnoreCase(constant.ACTION_DELETE_DATA)) {
                response.setContentType("text/html;charset=UTF-8");
                try {
                    String realID = request.getParameter("realID");
                    String partID = request.getParameter("partID");
                    String dic_thema_id = request.getParameter("dic_thema_id");
                    svService.deleteData(dic_thema_id, partID, realID);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                address = null;
                forward = false;
            } else if (ACTION.equalsIgnoreCase(constant.ACTION_SAVE_SUBJ1)) {
                response.setContentType("text/html;charset=UTF-8");
                try {
                    String subjTypeID = request.getParameter("dicPartID");
                    String cValAZ = request.getParameter("_nameAZ");
                    String cValRU = request.getParameter("_nameRU");
                    String cValEN = request.getParameter("_nameEN");
                    String cVal0AZ = request.getParameter("_descriptionAZ");
                    String cVal0RU = request.getParameter("_descriptionRU");
                    String cVal0EN = request.getParameter("_descriptionEN");
                    String newEditmp = request.getParameter("newEditmp");
                    String realID = "";
                    if ("3".equals(newEditmp) || "2".equals(newEditmp)) {
                        realID = request.getParameter("realID");
                    }
                    svService.saveSubj(subjTypeID, cVal0AZ, cVal0RU, cVal0EN, cValAZ, cValRU, cValEN, newEditmp, realID);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                address = null;
                forward = false;
            } else if (ACTION.equalsIgnoreCase(constant.ACTION_SET_SESSION)) {
                response.setContentType("text/html;charset=UTF-8");
                try {
                    String typ = request.getParameter("typ");
                    String newEdit = null;
                    String partID = null;

                    String rID = request.getParameter("realID");
                    String ID = request.getParameter("id");

                    List<String> catL = (List<String>) session.getAttribute(constant.SES_CATST);
                    if(catL == null){
                        catL = new ArrayList();
                    }

                    List<subjElement> piList = (List<subjElement>) session.getAttribute(constant.SES_PINFO);
                    if (piList == null) {
                        piList = new ArrayList<subjElement>();
                    }

                    List<contact> pcList = (List<contact>) session.getAttribute(constant.SES_PCONTACTS);
                    if (pcList == null) {
                        pcList = new ArrayList<contact>();
                    }
                    List<docList> pList = (List<docList>) session.getAttribute(constant.SES_PICTURES);
                    if (pList == null) {
                        pList = new ArrayList<docList>();
                    }
                    List<docList> qrList = (List<docList>) session.getAttribute(constant.SES_QRCODE);
                    if (qrList == null) {
                        qrList = new ArrayList<docList>();
                    }

                    newEdit = request.getParameter("newEdit");
                    partID = request.getParameter("partID");

                    if ("50000".equals(partID)) {
                        docList dl = new docList();
                        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                        int tip = 5;

                        if (!isMultipart) {
                            logger.info(" upload File not e multipart 1 ");
                        } else {
                            ftpUtility ftpU = new ftpUtility();
                            String srcPic = request.getParameter("qrCodePicture");

                            String imageString = srcPic.split(",")[1];
                            imageString = imageString.replace(" ", "+");
                            BufferedImage image = null;
                            byte[] imageByte;

                            imageByte = Base64.decodeBase64(imageString);
                            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                            image = ImageIO.read(bis);
                            bis.close();

                            File outputfile = new File("image.png");
                            ImageIO.write(image, "png", outputfile);

                            UploadForm uForm = ftpU.ftpUpload(outputfile, tip);
                            dl.setStatus(newEdit);
                            dl.setDocTypeID(Integer.parseInt("1034"));
                            dl.setQrCodePath(uForm.get("docFilePH"));
                            qrList.add(dl);
                            session.setAttribute(constant.SES_QRCODE, qrList);
                        }
                    }else if ("11111".equals(partID)) {
                       try{
                           String[] myJsonData = request.getParameterValues("json[]");

                        catL.add(myJsonData[0]);
                        session.setAttribute(constant.SES_CATST, catL);
                       }catch (Exception e){
                           e.printStackTrace();
                       }
                    } else if ("1".equals(partID)) {
                        List<docList> ds = (List<docList>) session.getAttribute(constant.SES_QUESTIONSPH);
                        if (ds == null) {
                            ds = new ArrayList<docList>();
                        }
                        docList dl = new docList();
                        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                        int tip = 0;

                        if (!isMultipart) {
                            logger.info(" upload File not e multipart 2 ");
                        } else {
                            String phType = request.getParameter("phType");
                            String foldID = request.getParameter("foldID");
                            ftpUtility ftpU = new ftpUtility();
                            tip = 8;
                            UploadForm uForm = ftpU.ftpUpload(request, tip);

                            docList dd = null;
                            dd = new docList();
                            long k = -1;
                            logger.info(phType + "=phType" + dd);
                            if ("3".equals(phType)) {
                                for (docList aa : ds) {
                                    if (aa.getDocCategory() == 1 || aa.getDocCategory() == 2 || aa.getDocCategory() == 3) {
                                        k = aa.getId();
                                    }
                                }
                                dd.setId(k);
                            }
                            dd.setFilePath(uForm.get("docFilePH"));
                            dd.setStatus("0");
                            dd.setFolderID(Long.parseLong(foldID));
                            dd.setDocCategory(Integer.parseInt(phType));
                            if ("3".equals(phType)) {
                                ds.add(dd);
                                session.setAttribute(constant.SES_SPEMPLOYEEPH, ds);
                            }
                        }

                    } else if ("2".equals(partID)) {
                        String ph = request.getParameter("ph");
                        List<docList> ds = null;
                        if ("1002".equals(ph)) {
                            ds = (List<docList>) session.getAttribute(constant.SES_CAR_ORG);
                        } else {
                            ds = (List<docList>) session.getAttribute(constant.SES_CAR_ORG1);
                        }
                        if (ds == null) {
                            ds = new ArrayList<docList>();
                        }
                        docList dl = new docList();
                        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                        int tip = 0;

                        if (!isMultipart) {
                            logger.info(" upload File not e multipart 3 ");
                        } else {
                            String phType = request.getParameter("phType");
                            String foldID = request.getParameter("foldID");

                            ftpUtility ftpU = new ftpUtility();
                            tip = 9;
                            UploadForm uForm = ftpU.ftpUpload(request, tip);

                            docList dd = null;
                            dd = new docList();
                            long k = -1;
                            logger.info(phType + "=phType" + dd);
                            if ("2".equals(phType)) {
                                for (docList aa : ds) {
                                    if (aa.getDocCategory() == 1 || aa.getDocCategory() == 2 || aa.getDocCategory() == 3) {
                                        k = aa.getId();
                                    }
                                }
                                dd.setId(k);
                            }
                            dd.setFilePath(uForm.get("docFilePH"));
                            dd.setStatus("0");
                            dd.setFolderID(Long.parseLong(foldID));
                            dd.setDocCategory(Integer.parseInt(phType));
                            if ("2".equals(phType)) {
                                ds.add(dd);
                                if ("1002".equals(ph)) {
                                    session.setAttribute(constant.SES_CAR_ORG, ds);
                                } else {
                                    session.setAttribute(constant.SES_CAR_ORG1, ds);
                                }

                            }
                        }

                    }
                    if ("1".equals(newEdit)) { // Insert Data

                        List<subjElement> spiList = (List<subjElement>) session.getAttribute(constant.SES_SPINFO);
                        if (spiList == null) {
                            spiList = new ArrayList<subjElement>();
                        }
                        if ("4001".equals(partID) || "4002".equals(partID)) {
                            List<person> inf = new ArrayList<person>();
                            String val = request.getParameter("val");
                            int pos = val.indexOf(",");
                            while (pos != -1) {
                                String rr = val.substring(0, pos);
                                person emp = new person();
                                emp.setID(Long.parseLong(rr));
                                emp.setState(0);
                                val = val.substring(pos + 1, val.length());
                                pos = val.indexOf(",");
                                inf.add(emp);
                            }
                            person emp = new person();
                            emp.setID(Long.parseLong(val));
                            inf.add(emp);
                            session.setAttribute(constant.SES_EMPLIST, inf);

                        } else if ("3012".equals(partID)) {
                            contact cc = new contact();
                            String cType = request.getParameter("contList");
                            cc.setContactTypeID(Long.parseLong(cType));
                            cc.setContactVal(request.getParameter("contVal"));
                            cc.setState(0);
                            cc.setPerContactID(pcList.size() + 1);
                            pcList.add(cc);
                            session.setAttribute(constant.SES_PCONTACTS, pcList);
                        } else if ("50001".equals(partID) || "50002".equals(partID) || "50003".equals(partID) || "50004".equals(partID)|| "50006".equals(partID)) {
                            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                            if (!isMultipart) {
                                logger.info(" upload File not e multipart 4 ");
                            } else {
                                int tip = 0;
                                int docTypeID1 = 1000;
                                String docType = request.getParameter("docTypeID");
                                if (!"".equals(docType) && docType != null) {
                                    docTypeID1 = Integer.parseInt(docType);
                                }
                                if ("50001".equals(partID)) {
                                    tip = 1;
                                } else if ("50002".equals(partID)) {
                                    tip = 2;
                                } else if ("50003".equals(partID)) {
                                    tip = 3;
                                } else if ("50004".equals(partID)) {
                                    if("1339".equals(docType)){
                                        tip = 10;
                                    }else {
                                        tip = 4;
                                    }
                                }else if ("50006".equals(partID)) {
                                    if("1339".equals(docType)){
                                        tip = 10;
                                    }else {
                                        tip = 4;
                                    }
                                }
                                ftpUtility ftpU = new ftpUtility();
                                UploadForm uForm;
                                if(docTypeID1 == 1339){
                                    uForm = ftpU.ftpUploadDocConvert(request, tip);
                                }else {
                                    uForm = ftpU.ftpUpload(request, tip);
                                }


                                docList dl = new docList();
                                dl.setEditFile(2);
                                if ("2".equals(typ)) {
                                    dl.setDocCategory(1);
                                } else if ("3".equals(typ)) {
                                    dl.setDocCategory(2);
                                }

                                dl.setStatus("0");
                                dl.setHasHTMLfile(docTypeID1);

                                if (!"".equals(uForm.get("docdate"))) {
                                    dl.setDocDate(uForm.get("docdate"));
                                }
                                dl.setDocTypeID(Integer.parseInt(partID));
                                dl.setDocNameAz(uForm.get("documentNameAZ"));
                                dl.setDocNameRu(uForm.get("documentNameRu"));
                                dl.setDocNameEn(uForm.get("documentNameEn"));

                                dl.setAuthorAz(uForm.get("authorAz"));
                                dl.setAuthorRu(uForm.get("authorRu"));
                                dl.setAuthorEn(uForm.get("authorEn"));

                                if ("50004".equals(partID)) {
                                    dl.setDocNumber(uForm.get("numb"));
                                } else {
                                    dl.setDocNumber("1");
                                }

                                dl.setLink(uForm.get("link"));
                                dl.setDocNumber(uForm.get("numb"));
                                dl.setQrCodePath(uForm.get("qrCodePicture"));

                                if(docTypeID1 == 1339) {
                                    dl.setFilePath(uForm.get("docFilePH"));
                                }else {
                                    dl.setFilePath(uForm.get("upload"));
                                }
                                dl.setId(pList.size() + 1);

                                pList.add(dl);
                                session.setAttribute(constant.SES_PICTURES, pList);
                            }
                        }
                    } else if ("2".equals(newEdit)) {   // Edit Data
                        if(!"50000".equals(partID)){
                            long id = Long.parseLong(ID);
                            if ("3012".equals(partID)) {
                                long realID = Long.parseLong(rID);
                                String newVal = "";
                                String oldVal = "";
                                for (contact cc : pcList) {
                                    if (cc.getPerContactID() == realID) {
                                        String cType = request.getParameter("contList");
                                        cc.setContactTypeID(Long.parseLong(cType));
                                        if (cc.getContactVal() == null ? request.getParameter("contVal") != null : !cc.getContactVal().equals(request.getParameter("contVal"))) {
                                            oldVal = oldVal + " Elaqe - " + cc.getContactVal();
                                            newVal = newVal + " Elaqe - " + request.getParameter("contVal");
                                        }
                                        cc.setContactVal(request.getParameter("contVal"));
                                        if (cc.getState() != 0) {
                                            cc.setState(2);
                                        } else {
                                            oldVal = " ";
                                            newVal = " ";
                                        }
                                    }
                                }
                                session.setAttribute(constant.SES_PCONTACTS, pcList);
                            } else if ("50001".equals(partID) || "50002".equals(partID) || "50003".equals(partID) || "50004".equals(partID)) {
                                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                                ftpUtility ftpU = new ftpUtility();
                                if (!isMultipart) {
                                    //logger.info(" upload File not e multipart");
                                } else {
                                    int tip = 0;
                                    int docTypeID1 = 1000;
                                    String docType = request.getParameter("docTypeID");
                                    if (!"".equals(docType) && docType != null) {
                                        docTypeID1 = Integer.parseInt(docType);
                                    }
                                    if ("50001".equals(partID)) {
                                        tip = 1;
                                    } else if ("50002".equals(partID)) {
                                        tip = 2;
                                    } else if ("50003".equals(partID)) {
                                        tip = 3;
                                    } else if ("50004".equals(partID)) {
                                        if("1339".equals(docType)){
                                            tip = 10;
                                        }else {
                                            tip = 4;
                                        }
                                    }else if ("50006".equals(partID)) {
                                        if("1339".equals(docType)){
                                            tip = 10;
                                        }else {
                                            tip = 4;
                                        }
                                    }

                                    UploadForm uForm;
                                    if(docTypeID1 == 1339){
                                        uForm = ftpU.ftpUploadDocConvert(request, tip);
                                    }else {
                                        uForm = ftpU.ftpUpload(request, tip);
                                    }

                                    for (docList dl : pList) {
                                        if (dl.getRelID() == id) {
                                            if (!"".equals(uForm.get("docdate"))) {
                                                dl.setDocDate(uForm.get("docdate"));
                                            } else {
                                                dl.setDocNameAz("");
                                            }
                                            String a = uForm.get("documentNameAZ");
                                            dl.setDocTypeID(Integer.parseInt(partID));
                                            dl.setDocNameAz(uForm.get("documentNameAZ"));
                                            dl.setDocNameRu(uForm.get("documentNameRu"));
                                            dl.setDocNameEn(uForm.get("documentNameEn"));

                                            dl.setAuthorAz(uForm.get("authorAz"));
                                            dl.setAuthorRu(uForm.get("authorRu"));
                                            dl.setAuthorEn(uForm.get("authorEn"));

                                            dl.setLink(uForm.get("link"));
                                            dl.setDocNumber(uForm.get("numb"));
                                            dl.setQrCodePath("");
                                            if(docTypeID1 == 1339){
                                                if (uForm.get("docFilePH") != null && !"".equals(uForm.get("docFilePH"))) {
                                                    dl.setFilePath(uForm.get("docFilePH"));
                                                    dl.setEditFile(2);
                                                }
                                            }else {
                                                if (uForm.get("upload") != null && !"".equals(uForm.get("upload"))) {
                                                    dl.setFilePath(uForm.get("upload"));
                                                    dl.setEditFile(2);
                                                }
                                            }
                                            if (!"0".equals(dl.getStatus())) {
                                                dl.setStatus("1");
                                            }
                                        }
                                        session.setAttribute(constant.SES_PICTURES, pList);
                                    }
                                }
                            }
                        }
                    } else if ("3".equals(newEdit)) { //delete
                        List<person> inf = (List<person>) session.getAttribute(constant.SES_EMPLIST);
                        long realID = Long.parseLong(rID);
                        if ("4001".equals(partID)) {
                            for (person ed : inf) {
                                if (ed.getID() == realID) {
                                    if (ed.getState() != 0) {
                                        ed.setState(3);
                                    } else if (ed.getState() == 0) {
                                        ed.setState(4);
                                    }
                                }
                            }
                            session.setAttribute(constant.SES_EMPLIST, inf);
                        }
                        if ("3012".equals(partID)) {
                            for (contact cc : pcList) {
                                if (cc.getPerContactID() == realID) {
                                    if (cc.getState() != 0) {
                                        cc.setState(3);
                                    } else if (cc.getState() == 0) {
                                        cc.setState(4);
                                    }
                                }
                            }
                            session.setAttribute(constant.SES_PCONTACTS, pcList);
                        } else if ("50001".equals(partID) || "50002".equals(partID) || "50003".equals(partID) || "50004".equals(partID)|| "50006".equals(partID)) {
                            for (docList exm : pList) {
                                if (exm.getRelID() == realID) {
                                    if (!"0".equals(exm.getStatus())) {
                                        exm.setStatus("3");
                                    }
                                }
                            }
                            session.setAttribute(constant.SES_PCONTACTS, pcList);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                address = null;
                forward = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            RequestDispatcher disp = request.getRequestDispatcher(address);
            if (forward) {
                disp.forward(request, response);
            } else {
                if (address != null) {
                    response.sendRedirect(address);
                }
            }
        }
    }
}
