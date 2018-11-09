package domain;

import java.io.Serializable;
import java.util.List;

public class subjElement implements Serializable {
    private long ID;
    private long subjectID;
    private long listID;
    private long addressID;
    private String subjectName;
    private String subjectValue;
    private long subjectTypeID;
    private long partID;
    private int state;
    private int st1;
    private String subjList;
    private String subjParseDate;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(long subjectID) {
        this.subjectID = subjectID;
    }

    public long getListID() {
        return listID;
    }

    public void setListID(long listID) {
        this.listID = listID;
    }

    public long getAddressID() {
        return addressID;
    }

    public void setAddressID(long addressID) {
        this.addressID = addressID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectValue() {
        return subjectValue;
    }

    public void setSubjectValue(String subjectValue) {
        this.subjectValue = subjectValue;
    }

    public long getSubjectTypeID() {
        return subjectTypeID;
    }

    public void setSubjectTypeID(long subjectTypeID) {
        this.subjectTypeID = subjectTypeID;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSt1() {
        return st1;
    }

    public void setSt1(int st1) {
        this.st1 = st1;
    }

    public long getPartID() {
        return partID;
    }

    public void setPartID(long partID) {
        this.partID = partID;
    }

    public String getSubjList() {
        return subjList;
    }

    public void setSubjList(String subjList) {
        this.subjList = subjList;
    }

    public String getSubjParseDate() {
        return subjParseDate;
    }

    public void setSubjParseDate(String subjParseDate) {
        this.subjParseDate = subjParseDate;
    }
}
