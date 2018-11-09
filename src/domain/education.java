package domain;

import java.io.Serializable;
import java.util.Date;

public class education implements Serializable {
    private long ID;
    private long educTypeID;
    private long educID;
    private long edFinID;
    private Date begDate;
    private Date endDate;
    private String educType;
    private String educOrg;
    private String finTip;
    private String notes;
    private long state;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getEducTypeID() {
        return educTypeID;
    }

    public void setEducTypeID(long educTypeID) {
        this.educTypeID = educTypeID;
    }

    public long getEducID() {
        return educID;
    }

    public void setEducID(long educID) {
        this.educID = educID;
    }

    public long getEdFinID() {
        return edFinID;
    }

    public void setEdFinID(long edFinID) {
        this.edFinID = edFinID;
    }

    public Date getBegDate() {
        return begDate;
    }

    public void setBegDate(Date begDate) {
        this.begDate = begDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEducType() {
        return educType;
    }

    public void setEducType(String educType) {
        this.educType = educType;
    }

    public String getEducOrg() {
        return educOrg;
    }

    public void setEducOrg(String educOrg) {
        this.educOrg = educOrg;
    }

    public String getFinTip() {
        return finTip;
    }

    public void setFinTip(String finTip) {
        this.finTip = finTip;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }
}
