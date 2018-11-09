package utils;

import domain.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by vugar on 13.02.2015.
 */
public class parseJSON {
    private Log logger = LogFactory.getLog(getClass());
    protected SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    protected SimpleDateFormat format1 = new SimpleDateFormat("hh:mm:ss");

    public void loadEmployeelInfo(HttpServletResponse response, person p,List<contact> contL) throws IOException {
        try {
            JsonFactory factory = new JsonFactory();
            PrintWriter out = response.getWriter();
            JsonGenerator generator = factory.createJsonGenerator(out);
            generator.writeStartObject();
            generator.writeArrayFieldStart("dsList");
            perContList(generator, contL, p.getID(), 7, 7);
            generator.writeStartObject();
            generator.writeStringField("empPhoto", p.getPhURL());
            generator.writeStringField("fname", p.getfName());
            generator.writeStringField("lname", p.getlName());
            generator.writeStringField("mName", p.getmName());
            generator.writeNumberField("org_id", p.getOrgID());
            generator.writeNumberField("position_id", p.getPositionID());
            generator.writeStringField("hiredate", format.format(p.getHireDate()));
            if(p.getFireDate() != null){
                generator.writeStringField("firedate", format.format(p.getFireDate()));
            }
            generator.writeStringField("pin", p.getPinCode());
            generator.writeStringField("bdate", format.format(p.getBirthDate()));
            generator.writeNumberField("gendid", (p.getGenderID()));
            generator.writeNumberField("partID", 2);
            generator.writeEndObject();
            generator.writeEndArray();
            generator.writeEndObject();
            generator.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void loadExamplesInfo(HttpServletResponse response,List<examples> exmpL,List<docList> examplesFiles,List<examples> exmpOper) throws IOException {
        try {
            JsonFactory factory = new JsonFactory();
            PrintWriter out = response.getWriter();
            JsonGenerator generator = factory.createJsonGenerator(out);
            generator.writeStartObject();
            generator.writeArrayFieldStart("dsList");
            examplesFiles(generator, examplesFiles, 70001);
            exmpOper(generator, exmpOper);
            for (examples exmp : exmpL) {
                generator.writeStartObject();
                generator.writeStringField("qrPhoto", exmp.getPhURL());
                generator.writeStringField("reysterCode", exmp.getRegCode());
                generator.writeStringField("examNameAz", exmp.getExmNameAz());
                generator.writeStringField("examNameRu", exmp.getExmNameRu());
                generator.writeStringField("examNameEn", exmp.getExmNameEn());
                generator.writeStringField("categoryID", exmp.getCategoryID());
                generator.writeStringField("groupID", exmp.getGroupID());
                generator.writeStringField("classID", exmp.getExmClassID());
                generator.writeStringField("typeID", exmp.getExmTypeID());
                generator.writeStringField("janrID", exmp.getExmGenreID());
                generator.writeStringField("regionID", exmp.getRegionID());
                generator.writeStringField("carryID", exmp.getCarriersList());
                generator.writeStringField("orgID", exmp.getOrgansList());
                generator.writeStringField("yuneskoID", exmp.getYuneskoList());
                if(exmp.geteDate() == null){
                    generator.writeStringField("eDate", "");
                }else {
                    generator.writeStringField("eDate", format.format(exmp.geteDate()));
                }
                generator.writeStringField("keyWordAz", exmp.getExmKeyWordAz());
                generator.writeStringField("keyWordRu", exmp.getExmKeyWordRu());
                generator.writeStringField("keyWordEn", exmp.getExmKeyWordEn());
                generator.writeNumberField("overTakenPlaceID", exmp.getOvertakenRegionID());
                generator.writeNumberField("mete", exmp.getMete());
                generator.writeStringField("textAz", exmp.getTextAz());
                generator.writeStringField("textRu", exmp.getTextRu());
                generator.writeStringField("textEn", exmp.getTextEn());
                generator.writeStringField("noteAz", exmp.getNoteAz());
                generator.writeStringField("noteRu", exmp.getNoteRu());
                generator.writeStringField("noteEn", exmp.getNoteEn());
                generator.writeNumberField("checkHtml", exmp.getCheckHtml());
                generator.writeNumberField("partID", 7000);
                generator.writeEndObject();

            }
            generator.writeEndArray();
            generator.writeEndObject();
            generator.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void loadOrgFullInfo(HttpServletResponse response,  List<contact> contL, finalInfo fin) throws Exception {
        try {
            JsonFactory factory = new JsonFactory();
            PrintWriter out = response.getWriter();
            JsonGenerator generator = factory.createJsonGenerator(out);
            generator.writeStartObject();
            generator.writeArrayFieldStart("dsList");
            generator.writeStartObject();
            generator.writeStringField("cDate", format.format(fin.getStDate()));
            generator.writeStringField("orgTypN", fin.getFinTypeName());
            generator.writeNumberField("orgTypID", fin.getFinTypeID());
            generator.writeStringField("orgName", fin.getoName());
            generator.writeStringField("inOrgAddress", fin.getAddress());
            generator.writeStringField("orgDesc", fin.getNotes());
            generator.writeNumberField("partID", 1);
            generator.writeEndObject();
            perContList(generator, contL, fin.getID(), 3003, 3003);
            generator.writeEndArray();
            generator.writeEndObject();
            generator.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Load prisoners full info ex =" + ex.toString());
        }
    }
    public void loadCategoryFullInfo(HttpServletResponse response,categoryFinalInfo fin) throws Exception {
        try {
            JsonFactory factory = new JsonFactory();
            PrintWriter out = response.getWriter();
            JsonGenerator generator = factory.createJsonGenerator(out);
            generator.writeStartObject();
            generator.writeArrayFieldStart("dsList");
            generator.writeStartObject();
            generator.writeStringField("catTypN", fin.getFinTypeName());
            generator.writeNumberField("catTypID", fin.getFinTypeID());
            generator.writeStringField("catName", fin.getoName());
            generator.writeStringField("catNameRu", fin.getoNameRu());
            generator.writeStringField("catNameEn", fin.getoNameEn());
            generator.writeStringField("catDesc", fin.getNotes());
            generator.writeStringField("catDescRu", fin.getNotesRu());
            generator.writeStringField("catDescEn", fin.getNotesEn());
            generator.writeStringField("code", fin.getRegCodePart());
            generator.writeNumberField("partID", 3);
            generator.writeEndObject();
            generator.writeEndArray();
            generator.writeEndObject();
            generator.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Load prisoners full info ex =" + ex.toString());
        }
    }

    public void loadCarriersFullInfo(HttpServletResponse response,carriersInfo fin) throws Exception {
        try {
            JsonFactory factory = new JsonFactory();
            PrintWriter out = response.getWriter();
            JsonGenerator generator = factory.createJsonGenerator(out);
            generator.writeStartObject();
            generator.writeArrayFieldStart("dsList");
            generator.writeStartObject();
            generator.writeStringField("carryPicture", fin.getPhURL());
            generator.writeStringField("carryNameAz", fin.getCarryNameAz());
            generator.writeStringField("carryNameRu", fin.getCarryNameRu());
            generator.writeStringField("carryNameEn", fin.getCarryNameEn());
            generator.writeStringField("carryDescAz", fin.getDesAz());
            generator.writeStringField("carryDescRu", fin.getDesRu());
            generator.writeStringField("carryDescEn", fin.getDesEn());
            generator.writeNumberField("partID", 4);
            generator.writeEndObject();
            generator.writeEndArray();
            generator.writeEndObject();
            generator.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Load prisoners full info ex =" + ex.toString());
        }
    }
    public void loadOrganizationFullInfo(HttpServletResponse response,carriersInfo fin) throws Exception {
        try {
            JsonFactory factory = new JsonFactory();
            PrintWriter out = response.getWriter();
            JsonGenerator generator = factory.createJsonGenerator(out);
            generator.writeStartObject();
            generator.writeArrayFieldStart("dsList");
            generator.writeStartObject();
            generator.writeStringField("orgPicture", fin.getPhURL());
            generator.writeStringField("organNameAz", fin.getCarryNameAz());
            generator.writeStringField("organNameRu", fin.getCarryNameRu());
            generator.writeStringField("organNameEn", fin.getCarryNameEn());
            generator.writeStringField("organDescAz", fin.getDesAz());
            generator.writeStringField("organDescRu", fin.getDesRu());
            generator.writeStringField("organDescEn", fin.getDesEn());
            generator.writeNumberField("partID", 5);
            generator.writeEndObject();
            generator.writeEndArray();
            generator.writeEndObject();
            generator.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("Load prisoners full info ex =" + ex.toString());
        }
    }

    public void perContList(JsonGenerator generator, List<contact> contL, long rID, long pID, long sID) throws Exception {
        for (contact se : contL) {
            generator.writeStartObject();
            generator.writeStringField("contType", se.getContactTypeName());
            generator.writeStringField("contv", se.getContactVal());
            generator.writeNumberField("id", se.getRownum());
            generator.writeNumberField("tipID", se.getContactTypeID());
            generator.writeNumberField("realID", se.getPerContactID());
            generator.writeNumberField("sID", sID);
            generator.writeNumberField("sfull", rID);
            generator.writeNumberField("partID", pID);
            generator.writeEndObject();
        }
    }

    public void examplesFiles(JsonGenerator generator, List<docList> examplesFiles,  long pID) throws Exception {
        for (docList ds : examplesFiles) {
            generator.writeStartObject();
            generator.writeNumberField("docType", ds.getDocTypeID());
            generator.writeStringField("docNo", ds.getDocNumber());
            generator.writeNumberField("isHtmlContain", ds.getHasHTMLfile());
            generator.writeStringField("docnameAz", ds.getDocNameAz());
            generator.writeStringField("docnameRu", ds.getDocNameRu());
            generator.writeStringField("docnameEn", ds.getDocNameEn());
            generator.writeStringField("authorAz", ds.getAuthorAz());
            generator.writeStringField("authorRu", ds.getAuthorRu());
            generator.writeStringField("authorEn", ds.getAuthorEn());
            if (ds.getDocDate() != null) {
                generator.writeStringField("docdate",ds.getDocDate());
            } else {
                generator.writeStringField("docdate", "");
            }
            generator.writeStringField("docUrl", ds.getFilePath());
            generator.writeStringField("docLink", ds.getLink());
            generator.writeNumberField("realID", ds.getRelID());
            generator.writeNumberField("id", ds.getId());
            generator.writeNumberField("partID", ds.getDocTypeID());
            generator.writeEndObject();
        }
    }
    public void exmpOper(JsonGenerator generator, List<examples> exmpOper) throws Exception {
        for (examples ds : exmpOper) {
            generator.writeStartObject();
            generator.writeNumberField("statusID", ds.getStatusID());
            generator.writeNumberField("eventTypeID", ds.getEventTypeID());
            generator.writeStringField("status", ds.getStatus());
            generator.writeStringField("reason", ds.getEventComment());
            generator.writeStringField("userName", ds.getUserName());
            if (ds.getOperDate() != null) {
                generator.writeStringField("eventDate", format.format(ds.getOperDate()));
            } else {
                generator.writeStringField("eventDate", "");
            }
            generator.writeNumberField("partID", 50005);
            generator.writeEndObject();

        }
    }

}


