package domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Samire on 12/14/2016.
 */
public class examples implements Serializable {

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public String getExmNameAz() {
        return exmNameAz;
    }

    public void setExmNameAz(String exmNameAz) {
        this.exmNameAz = exmNameAz;
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public String getExmNameRu() {
        return exmNameRu;
    }

    public void setExmNameRu(String exmNameRu) {
        this.exmNameRu = exmNameRu;
    }

    public String getExmNameEn() {
        return exmNameEn;
    }

    public void setExmNameEn(String exmNameEn) {
        this.exmNameEn = exmNameEn;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
    }

    public String getExmKeyWordAz() {
        return exmKeyWordAz;
    }

    public void setExmKeyWordAz(String exmKeyWordAz) {
        this.exmKeyWordAz = exmKeyWordAz;
    }

    public String getExmKeyWordRu() {
        return exmKeyWordRu;
    }

    public void setExmKeyWordRu(String exmKeyWordRu) {
        this.exmKeyWordRu = exmKeyWordRu;
    }

    public String getExmKeyWordEn() {
        return exmKeyWordEn;
    }

    public void setExmKeyWordEn(String exmKeyWordEn) {
        this.exmKeyWordEn = exmKeyWordEn;
    }

    public String getReferenceAz() {
        return referenceAz;
    }

    public void setReferenceAz(String referenceAz) {
        this.referenceAz = referenceAz;
    }

    public String getReferenceRu() {
        return referenceRu;
    }

    public void setReferenceRu(String referenceRu) {
        this.referenceRu = referenceRu;
    }

    public String getReferenceEn() {
        return referenceEn;
    }

    public void setReferenceEn(String referenceEn) {
        this.referenceEn = referenceEn;
    }

    public String getTextAz() {
        return textAz;
    }

    public void setTextAz(String textAz) {
        this.textAz = textAz;
    }

    public String getTextRu() {
        return textRu;
    }

    public void setTextRu(String textRu) {
        this.textRu = textRu;
    }

    public String getTextEn() {
        return textEn;
    }

    public void setTextEn(String textEn) {
        this.textEn = textEn;
    }

    public String getNoteAz() {
        return noteAz;
    }

    public void setNoteAz(String noteAz) {
        this.noteAz = noteAz;
    }

    public String getNoteRu() {
        return noteRu;
    }

    public void setNoteRu(String noteRu) {
        this.noteRu = noteRu;
    }

    public String getNoteEn() {
        return noteEn;
    }

    public void setNoteEn(String noteEn) {
        this.noteEn = noteEn;
    }


    public long getOvertakenRegionID() {
        return OvertakenRegionID;
    }

    public void setOvertakenRegionID(long overtakenRegionID) {
        this.OvertakenRegionID = overtakenRegionID;
    }



    public long getMete() {
        return mete;
    }

    public void setMete(long mete) {
        this.mete = mete;
    }

    public String getCarriersList() {
        return carriersList;
    }

    public void setCarriersList(String carriersList) {
        this.carriersList = carriersList;
    }

    public String getOrgansList() {
        return organsList;
    }

    public void setOrgansList(String organsList) {
        this.organsList = organsList;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getExmClassID() {
        return exmClassID;
    }

    public void setExmClassID(String exmClassID) {
        this.exmClassID = exmClassID;
    }

    public String getExmTypeID() {
        return exmTypeID;
    }

    public void setExmTypeID(String exmTypeID) {
        this.exmTypeID = exmTypeID;
    }

    public String getExmGenreID() {
        return exmGenreID;
    }

    public void setExmGenreID(String exmGenreID) {
        this.exmGenreID = exmGenreID;
    }

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getPhURL() {
        return phURL;
    }

    public void setPhURL(String phURL) {
        this.phURL = phURL;
    }

    public long getStatusID() {
        return statusID;
    }

    public void setStatusID(long statusID) {
        this.statusID = statusID;
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public String getEventComment() {
        return eventComment;
    }

    public void setEventComment(String eventComment) {
        this.eventComment = eventComment;
    }

    public long getEventTypeID() {
        return eventTypeID;
    }

    public void setEventTypeID(long eventTypeID) {
        this.eventTypeID = eventTypeID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getYuneskoList() {
        return yuneskoList;
    }

    public void setYuneskoList(String yuneskoList) {
        this.yuneskoList = yuneskoList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCheckHtml() {
        return checkHtml;
    }

    public void setCheckHtml(long checkHtml) {
        this.checkHtml = checkHtml;
    }

    private long ID;
    private long statusID;
    private long eventTypeID;
    private Date operDate;
    private String  eventComment;
    private String  status;
    private String opType;
    private String exmNameAz;
    private String exmNameRu;
    private String exmNameEn;

    private String regCode;

    private Date eDate;

    private String exmKeyWordAz;
    private String exmKeyWordRu;
    private String exmKeyWordEn;

    private String referenceAz;
    private String referenceRu;
    private String referenceEn;

    private String textAz;
    private String textRu;
    private String textEn;

    private String noteAz;
    private String noteRu;
    private String noteEn;

    private String categoryID;
    private String groupID;
    private String exmClassID;
    private String exmTypeID;
    private String exmGenreID;
    private String regionID;
    private long OvertakenRegionID;
    private long mete;
    private String carriersList;
    private String organsList;
    private String yuneskoList;
    private String phURL;
    private String userName;
    private long checkHtml;

}
