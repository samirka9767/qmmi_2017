package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class finalInfo implements Serializable {
    private long ID;
    private long AddrsID;
    private long finTypeID;
    private double order_by;
    private Date stDate;
    private String finTypeName;
    private String address;
    private String Notes;

    private String oName;
    private String oChiefName;

    private long state;


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }


    public long getAddrsID() {
        return AddrsID;
    }

    public void setAddrsID(long AddrsID) {
        this.AddrsID = AddrsID;
    }



    public double getOrder_by() {
        return order_by;
    }

    public void setOrder_by(double order_by) {
        this.order_by = order_by;
    }


    public long getFinTypeID() {
        return finTypeID;
    }

    public void setFinTypeID(long finTypeID) {
        this.finTypeID = finTypeID;
    }

    public Date getStDate() {
        return stDate;
    }

    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    public String getFinTypeName() {
        return finTypeName;
    }

    public void setFinTypeName(String finTypeName) {
        this.finTypeName = finTypeName;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }
    public String getoChiefName() {
        return oChiefName;
    }

    public void setoChiefName(String oChiefName) {
        this.oChiefName = oChiefName;
    }


}
