package web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import service.loadDataService;
import service.loadDataServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import domain.*;
import service.*;
import utils.DocConvert;

import utils.ftpUtility;

import utils.parseJSON;
@WebServlet(name = "contServlet")
public class contServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String ACTION = "action";
        String address = null;
        Boolean forward = false;
        usersInfo userInfo = null;
        Log logger = LogFactory.getLog(getClass());
        HttpSession session = request.getSession();
        loadDataService ldService = new loadDataServiceImpl();
        parseJSON pj = new parseJSON();
        try {
            ACTION = request.getParameter("action");
            userInfo = (usersInfo) session.getAttribute(constant.USER);
            if (userInfo == null) {
                response.setHeader(constant.REQUIRES_AUTH, String.valueOf(1));
                logger.info("session-da user yoxdu");
                address = constant.PAGE_LOGIN;
                forward = true;
            } else {
                if (ACTION.equalsIgnoreCase(constant.ACTION_GET_GRID_CONTENT)) {
                    PagingObject pagingObject = getPagingObject(request);
                    String gridType = request.getParameter("gridType");
                    String statusID = request.getParameter("statusID");

                    List<String> advWhere = (List<String>) session.getAttribute(constant.SES_ADV_WHERE);
                    List<String> advFrom = (List<String>) session.getAttribute(constant.SES_ADV_FROM);

                    String where = "";
                    String from = "";
                    if (advWhere != null) {
                        for (String whr : advWhere) {
                            where = "\n " + where + whr;
                        }
                        for (String frm : advFrom) {
                            from = "\n " + from + frm;
                        }
                    }

                    Result result = ldService.getGridContent(pagingObject, gridType, request.getParameterMap(), "", userInfo, statusID, from, where);
                    request.getSession().setAttribute(constant.GRID_TOTAL_COUNT, result.getRecordCount());
                    request.setAttribute(constant.GRID_CONTENT, result);
                    address = "/WEB-INF/parse/gridContentMainParse.jsp";
                    forward = true;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_FILLCOMBO_BY_PARID)) {
                    response.setContentType("text/html;charset=UTF-8");
                    try {
                        String paramID = request.getParameter("paramID");
                        String langid = request.getParameter("langID");
                        List<listInfo> infL = ldService.loadComboForAdvancedSearch(paramID, Long.parseLong(langid));
                        logger.info("subjID = " + paramID);
                        JsonFactory factory = new JsonFactory();
                        PrintWriter out = response.getWriter();
                        JsonGenerator generator = factory.createJsonGenerator(out);
                        generator.writeStartObject();
                        generator.writeArrayFieldStart("dsList");
                        for (listInfo ds : infL) {
                            generator.writeStartObject();
                            generator.writeNumberField("id", ds.getId());
                            generator.writeStringField("full_name", ds.getDescription1());
                            generator.writeStringField("short_name", ds.getDescription2());
                            generator.writeEndObject();
                        }
                        generator.writeEndArray();
                        generator.writeEndObject();
                        generator.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        logger.info("Fill combo by subj servlet" + ex.toString());
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_SET_SEARCH_TEXT)) {
                    try {
                        String p1 = request.getParameter("seacrhText");
                        session.setAttribute("p1", p1);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_INT_SEARCH)) {
                    String p1 = (String) session.getAttribute("p1");
                    PagingObject pagingObject = getPagingObject(request);
                    String gridType = request.getParameter("gridType");
                    String statusID = request.getParameter("statusID");
                    Result result = ldService.getGridContent(pagingObject, gridType, request.getParameterMap(), p1, userInfo, statusID, "", "");
                    request.getSession().setAttribute(constant.GRID_TOTAL_COUNT, result.getRecordCount());
                    request.setAttribute(constant.GRID_CONTENT, result);
                    address = "/WEB-INF/parse/gridContentMainParse.jsp";
                    forward = true;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_LOAD_DICT_LIST)) {
                    PagingObject pagingObject = getPagingObject(request);
                    String gridType = request.getParameter("gridType");
                    String statusID = request.getParameter("statusID");
                    Result result = ldService.getGridContent(pagingObject, gridType, request.getParameterMap(), "", userInfo, statusID, "", "");
                    request.getSession().setAttribute(constant.GRID_TOTAL_COUNT, result.getRecordCount());
                    request.setAttribute(constant.GRID_CONTENT, result);
                    address = "/WEB-INF/parse/gridContentMainParse.jsp";
                    forward = true;
                } else if (ACTION.equalsIgnoreCase(constant.COMBO_CONTENT)) {
                    String comboType = request.getParameter("comboType");
                    String sType = request.getParameter("sType");  //select type
                    Map<String, String[]> parameterMap = request.getParameterMap();
                    List<Row> params = ldService.getComboContent(sType, comboType, parameterMap, userInfo);
                    request.setAttribute(constant.COMBO_CONTENT, params);
                    address = "WEB-INF/parse/comboContentParse.jsp";
                    forward = true;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_LOAD_STRUKTUR)) {
                    try {
                        String partID = request.getParameter("partID");

                        String lang = request.getParameter("langID");
                        long langID = Long.parseLong(lang);
                        List<listInfo> infL = ldService.loadStrukturList(partID, langID);
                        JsonFactory factory = new JsonFactory();
                        PrintWriter out = response.getWriter();
                        JsonGenerator generator = factory.createJsonGenerator(out);
                        generator.writeStartObject();
                        generator.writeArrayFieldStart("dsList");
                        for (listInfo ds : infL) {
                            generator.writeStartObject();
                            generator.writeNumberField("id", ds.getId());
                            generator.writeNumberField("tip", ds.getElemType());
                            generator.writeStringField("formula", ds.getDescription1());
                            generator.writeStringField("fname", ds.getDescription2());
                            generator.writeNumberField("index1", ds.getIndex());
                            generator.writeEndObject();
                        }
                        generator.writeEndArray();
                        generator.writeEndObject();
                        generator.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_GET_STRUKTUR_ORG_INFO)) {
                    response.setContentType("text/html;charset=UTF-8");
                    try {
                        String partID = request.getParameter("partID");
                        String tID = request.getParameter("treeID");
                        long treeID = Long.parseLong(tID);
                        if ("1010".equals(partID)) {
                            categoryFinalInfo pfList = ldService.getCategoryInfo(treeID);
                            pj.loadCategoryFullInfo(response, pfList);
                            session.setAttribute(constant.SES_CATEGORY_INFO, pfList);
                        } else if ("1006".equals(partID)) {
                            List<contact> pcList = ldService.getOrgContacts(treeID);
                            finalInfo pfList = ldService.getOrgInfo(treeID);
                            pj.loadOrgFullInfo(response, pcList, pfList);
                            session.setAttribute(constant.SES_PCONTACTS, pcList);
                            session.setAttribute(constant.SES_ORG_INFO, pfList);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_GET_CARRIERS_INFO)) {
                    response.setContentType("text/html;charset=UTF-8");
                    try {
                        Connection con = null;
                        String realId = request.getParameter("realId");
                        String partID = request.getParameter("partID");
                        long carryID = Long.parseLong(realId);

                        if ("1012".equals(partID)) {
                            List<docList> dl = ldService.loadPhoto(con, Long.parseLong(realId), 1);
                            carriersInfo pfList = ldService.getCariesInfo(carryID);
                            if (dl != null && dl.size() != 0) {
                                pfList.setPhURL(dl.get(0).getFilePath());
                            } else {
                                pfList.setPhURL("1");
                            }
                            pj.loadCarriersFullInfo(response, pfList);
                            session.setAttribute(constant.SES_CARRY_INFO, pfList);
                        } else if ("1002".equals(partID)) {
                            List<docList> dl = ldService.loadPhoto(con, Long.parseLong(realId), 2);
                            carriersInfo pfList = ldService.getOrganizationInfo(carryID);
                            if (dl != null && dl.size() != 0) {
                                pfList.setPhURL(dl.get(0).getFilePath());
                            } else {
                                pfList.setPhURL("1");
                            }
                            pj.loadOrganizationFullInfo(response, pfList);
                            session.setAttribute(constant.SES_ORGAN_INFO, pfList);
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_GET_EMPLOYEE_INFO)) {
                    try {
                        Connection con = null;
                        String rID = request.getParameter("realId");
                        Long perID = Long.parseLong(request.getParameter("perID"));
                        Long realId = Long.parseLong(rID);
                        person p = null;
                        p = ldService.getEmployeeInfo(realId);
                        List<docList> dl = ldService.loadPhoto(con, perID, 3);
                        List<contact> pcList = ldService.getPersonContact(perID);
                        if (dl != null && dl.size() != 0) {
                            p.setPhURL(dl.get(0).getFilePath());
                        } else {
                            p.setPhURL("1");
                        }
                        pj.loadEmployeelInfo(response, p, pcList);
                        session.setAttribute(constant.SES_PCONTACTS, pcList);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_GET_EXAMPLES_INFO)) {
                    try {
                        Connection con = null;
                        List<docList> dl = null;
                        String rID = request.getParameter("realId");
                        String langID = request.getParameter("langID");
                        Long realId = Long.parseLong(rID);
                        Long lngID = Long.parseLong(langID);
                        List<examples> exmp = ldService.getExamplesInfo(realId, langID);
                        dl = ldService.loadQRphoto(con, realId, 1034);
                        List<docList> examplesFiles = ldService.getExamplesPictures(con, realId, 1);
                        List<examples> exmpOper = ldService.getExamplesOperation(con, realId, lngID);
                        session.setAttribute(constant.SES_PICTURES, examplesFiles);
                        if (dl != null && dl.size() != 0) {
                            exmp.get(0).setPhURL(dl.get(0).getQrCodePath());
                        } else {
                            exmp.get(0).setPhURL("0");
                        }
                        pj.loadExamplesInfo(response, exmp, examplesFiles, exmpOper);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_LOAD_RIGHT_PANEL)) {
                    response.setContentType("text/html;charset=UTF-8");
                    try {
                        String pID = request.getParameter("id");
                        String ity = request.getParameter("iType");
                        long prisID = Long.parseLong(pID);
                        int iType = Integer.parseInt(ity);
                        List<docList> infL = null;
                        infL = ldService.loadRightPanelPhoto(prisID, iType);
                        JsonFactory factory = new JsonFactory();
                        PrintWriter out = response.getWriter();
                        JsonGenerator generator = factory.createJsonGenerator(out);
                        generator.writeStartObject();
                        generator.writeArrayFieldStart("dsList");
                        for (docList ds : infL) {
                            generator.writeStartObject();
                            generator.writeStringField("filePath", ds.getFilePath());
                            generator.writeEndObject();
                        }
                        generator.writeEndArray();
                        generator.writeEndObject();
                        generator.flush();
                        //session.setAttribute(constant.SES_PRIGTHPANEL, infL);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    address = null;
                    forward = false;
                }else if (ACTION.equalsIgnoreCase(constant.ACTION_GET_FILE)) {
                    try {
                        String a = request.getParameter("filePath");
                        //response.setContentType("jpg");
                        ftpUtility ff = new ftpUtility();
                        ff.ftpDownload(a, response);
                        response.flushBuffer();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }else if (request.getParameter("action").equalsIgnoreCase(constant.ACTION_DOWN_FILE)) {
                    response.setContentType("application/octet-stream");
                    try {
                        String a = "";
                        String tt = request.getParameter("typ");
                        if ("1".equals(tt)) {
                            a = request.getParameter("filePath");
                        }

                        response.setHeader("Content-Disposition", "attachment; filename=" + a);
                        ftpUtility ff = new ftpUtility();
                        ff.ftpDownload(a, response);
                        response.flushBuffer();



                    }  catch (Exception ex) {  ex.printStackTrace();
                        logger.info(" OpenFile " + ex.getMessage());
                    }
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_CHECK_USERNAME)) {
                    response.setContentType("text/html;charset=UTF-8");
                    try {
                        String uName = request.getParameter("uName");
                        int res = ldService.checkUser(uName);
                        JsonFactory factory = new JsonFactory();
                        PrintWriter out = response.getWriter();
                        JsonGenerator generator = factory.createJsonGenerator(out);
                        generator.writeStartObject();
                        generator.writeArrayFieldStart("dsList");
                        generator.writeStartObject();
                        generator.writeNumberField("res", res);
                        generator.writeEndObject();
                        generator.writeEndArray();
                        generator.writeEndObject();
                        generator.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        logger.info("Servlet 1 change password " + ex.toString());
                    }
                    address = null;
                    forward = false;
                }else if (ACTION.equalsIgnoreCase(constant.ACTION_CHECK_RECORD_COUNT_DC)) {
                    try {
                        String text = request.getParameter("_nameAZ");
                        String desc = request.getParameter("_descriptionAZ");
                        int  dicType = Integer.parseInt(request.getParameter("dicPartID"));
                        int count = -1;
                        if(dicType != 10014 ) {
                             count = ldService.checkDictRecord(text, dicType,"1","1");
                        }else {
                             count = ldService.checkDictRecord(text, dicType,text,desc);
                        }
                        JsonFactory factory = new JsonFactory();
                        PrintWriter out = response.getWriter();
                        JsonGenerator generator = factory.createJsonGenerator(out);
                        generator.writeStartObject();
                        generator.writeArrayFieldStart("dsList");
                        generator.writeStartObject();
                        generator.writeNumberField("count", count);
                        generator.writeEndObject();
                        generator.writeEndArray();
                        generator.writeEndObject();
                        generator.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_LOAD_USERINFO)) {
                    response.setContentType("text/html;charset=UTF-8");
                    try {
                        String prID = request.getParameter("prID");
                        usersInfo uInf = ldService.loadUserInfo(prID);
                        JsonFactory factory = new JsonFactory();
                        PrintWriter out = response.getWriter();
                        JsonGenerator generator = factory.createJsonGenerator(out);
                        generator.writeStartObject();
                        generator.writeArrayFieldStart("dsList");
                        generator.writeStartObject();
                        generator.writeNumberField("act", uInf.getUserAct());
                        generator.writeNumberField("usr", uInf.getUserType());
                        generator.writeStringField("uname", uInf.getUserName());
                        generator.writeStringField("pass", uInf.getPswd());
                        generator.writeEndObject();
                        generator.writeEndArray();
                        generator.writeEndObject();
                        generator.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        //logger.info("While fill combo 1 servlet" + ex.toString());
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_LOAD_EMPlIST)) {
                    try {

                        String OrgID = request.getParameter("orgID");
                        List<person> infL = ldService.loadEmpList(Integer.parseInt(OrgID));

                        JsonFactory factory = new JsonFactory();
                        PrintWriter out = response.getWriter();
                        JsonGenerator generator = factory.createJsonGenerator(out);
                        generator.writeStartObject();
                        generator.writeArrayFieldStart("dsList");
                        for (person ds : infL) {
                            generator.writeStartObject();
                            generator.writeStringField("full_name", ds.getfName());
                            generator.writeStringField("result", " ");
                            generator.writeNumberField("id", ds.getID());
                            generator.writeEndObject();
                        }
                        generator.writeEndArray();
                        generator.writeEndObject();
                        generator.flush();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_LOAD_PARAM)) {
                    response.setCharacterEncoding("UTF-8");
                    List<subjElement> subj;
                    try {
                        String partID = request.getParameter("partID");
                        subj = ldService.getADVSearchMenuList(partID);
                        PrintWriter out = response.getWriter();
                        JsonFactory factory = new JsonFactory();
                        JsonGenerator generator = factory.createJsonGenerator(out);
                        generator.writeStartObject();
                        generator.writeArrayFieldStart("dsList");

                        for (subjElement ds : subj) {
                            generator.writeStartObject();
                            generator.writeNumberField("element_type", ds.getSubjectTypeID());
                            generator.writeNumberField("id", ds.getID());
                            generator.writeStringField("full_name", ds.getSubjectName());
                            generator.writeNumberField("listid", ds.getListID());
                            generator.writeEndObject();
                        }
                        generator.writeEndArray();
                        generator.writeEndObject();
                        generator.flush();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        logger.info("Servlet Loadsubj error " + ex.getMessage());
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_DELETE_SESSION_PARAMS1)) {
                    try {
                        deleteSessionParams(request);
                        session.removeAttribute(constant.SES_ADV_WHERE);
                        session.removeAttribute(constant.SES_ADV_FROM);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    address = null;
                    forward = false;
                } else if (ACTION.equalsIgnoreCase(constant.ACTION_MAKE_ADV_PARAM)) {
                    response.setContentType("text/html;charset=UTF-8");
                    List<String> where = (List<String>) session.getAttribute(constant.SES_ADV_WHERE);
                    if (where == null) {
                        where = new ArrayList<String>();
                    }
                    List<String> from = (List<String>) session.getAttribute(constant.SES_ADV_FROM);
                    if (from == null) {
                        from = new ArrayList<String>();
                    }
                    try {
                        String typ = request.getParameter("typ");
                        if ("1".equals(typ)) {
                            String whr = "";
                            String frm = "";
                            String val = request.getParameter("val");
                            String paramID = request.getParameter("ADVsearchList");
                            String paramVal = "";
                            String cond = "";
                            if ("1000".equals(val) || "1005".equals(val)) {
                                cond = request.getParameter("condSTList");
                                paramVal = request.getParameter("paramVal");
                            } else if ("1002".equals(val)) {
                                cond = request.getParameter("condDTList");
                                paramVal = request.getParameter("paramVal");
                            } else if ("1003".equals(val)) {
                                cond = "=";
                                paramVal = request.getParameter("paramList");
                            }

                            logger.info("paramID = " + paramID + " // paramVal = " + paramVal + " // cond = " + cond + " //val= " + val);
                            whr = ldService.getADVInfo(paramID, paramVal, val, cond, "2");
                            frm = ldService.getADVInfo(paramID, paramVal, val, cond, "1");


                            int chk = -1;
                            int chkWR = 0;

                            //  whr = whr.replace("#",  paramCount);
//
                            if (where.size() > 0) {
                                for (Iterator i = where.iterator(); i.hasNext(); ) {
                                    String element = (String) i.next();
//                                    logger.info(element+" element //// whr "+cc);
//                                    else{
                                    if (element.indexOf(whr) == -1) {
                                        chk = 0;
                                    }
//                                    }
                                }
                            } else {
                                chk = 0;
                            }
                            if (chk == 0) {
                                where.add(whr);
                            }
                            if (chkWR == 10) {
                                where.add(whr);
                            }
                            chk = -1;
                            from.add(frm);
                        } else if ("2".equals(typ)) {
                            String whr = "";
                            String frm = "";
                            String typEd = request.getParameter("typEd");
                            //logger.info(typEd + "=typEd");

                            //    String paramCount = request.getParameter("paramCount");
                            if (!"4".equals(typEd)) {
                                String val = request.getParameter("val");
                                String paramID = request.getParameter("valID");
                                whr = ldService.getADVInfo(paramID, "", val, "", "2");
                                frm = ldService.getADVInfo(paramID, "", val, "", "1");
                                int pos = whr.indexOf(",");
                                if (pos != -1) {
                                    String tt = whr.substring(0, pos);
                                    whr = tt;
                                }

                                for (Iterator i = where.iterator(); i.hasNext(); ) {
                                    String element = (String) i.next();
                                    //  logger.info(element + "=elemett=" + whr);
                                   /* String a = "AND ST.DICT_EVENT_TYPE_ID = 1018\n" +"AND TO_DATE(ST.EVENT_DATE)  <= TO_DATE('28-02-2017','dd-mm-YYYY')";
                                    String b = "AND ST.DICT_EVENT_TYPE_ID = 1018\n" +"AND TO_DATE(ST.EVENT_DATE)   ";*/
                                    if (element.indexOf(whr) != -1) {
                                        i.remove();

                                    }
                                } //
                                if (!"".equals(frm.trim())) {
                                    for (Iterator i = from.iterator(); i.hasNext(); ) {
                                        String element = (String) i.next();
                                        if (element.indexOf(frm) != -1) {
                                            i.remove();
                                        }
                                    }
                                }
                            } else if ("4".equals(typEd)) {
                                where = null;
                                from = null;
                            }

                        }
                        session.setAttribute(constant.SES_ADV_WHERE, where);
                        session.setAttribute(constant.SES_ADV_FROM, from);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        logger.info("load setSsession params Search errorrrr " + ex.toString());
                    } finally {
                        where = null;
                        from = null;
                    }
                    address = null;
                    forward = false;
                }
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

    private PagingObject getPagingObject(HttpServletRequest request) {
        PagingObject object = new PagingObject();
        if (request.getParameter("page") != null) {
            object.setPageNumber(Integer.parseInt(request.getParameter("page")));
        } else {
            object.setPageNumber(1);
        }
        if (request.getParameter("rows") != null) {
            object.setPageSize(Integer.parseInt(request.getParameter("rows")));
        } else {
            object.setPageSize(20);
        }
        if (request.getParameter("sidx") != null) {
            object.setSortIndex(request.getParameter("sidx"));
        } else {
            object.setSortIndex("1");
        }
        if (request.getParameter("sord") != null) {
            object.setSortOrder(request.getParameter("sord"));
        } else {
            object.setSortOrder("desc");
        }
        return object;
    }

    private void deleteSessionParams(HttpServletRequest request) throws Exception {
        try {
            HttpSession session = request.getSession();
            session.removeAttribute(constant.SES_PINFO);
            session.removeAttribute(constant.SES_PADDRESS);
            session.removeAttribute(constant.SES_PCONTACTS);
            session.removeAttribute(constant.SES_PEDUCATION);
            session.removeAttribute(constant.SES_SPINFO);
            session.removeAttribute(constant.SES_QRCODE);
            session.removeAttribute(constant.SES_CAR_ORG);
            session.removeAttribute(constant.SES_CAR_ORG1);
            session.removeAttribute(constant.SES_EMPLIST);
            session.removeAttribute(constant.SES_ORG_INFO);
            session.removeAttribute(constant.SES_CATEGORY_INFO);
            session.removeAttribute(constant.SES_CARRY_INFO);
            session.removeAttribute(constant.SES_ORGAN_INFO);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
