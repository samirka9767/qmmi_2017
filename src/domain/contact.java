package domain;

import java.io.Serializable;

public class contact implements Serializable {
    private long rownum;
    private long perContactID;
    private long contactTypeID;
    private String contactTypeName;
    private String contactVal;
    private long state;

    public long getPerContactID() {
        return perContactID;
    }

    public void setPerContactID(long perContactID) {
        this.perContactID = perContactID;
    }

     public long getRownum() {
        return rownum;
    }

    public void setRownum(long rownum) {
        this.rownum = rownum;
    }

    public long getContactTypeID() {
        return contactTypeID;
    }

    public void setContactTypeID(long contactTypeID) {
        this.contactTypeID = contactTypeID;
    }

    public String getContactTypeName() {
        return contactTypeName;
    }

    public void setContactTypeName(String contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

    public String getContactVal() {
        return contactVal;
    }

    public void setContactVal(String contactVal) {
        this.contactVal = contactVal;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }
}
