package domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by a.ulviyya on 05.07.2016.
 */
public class person implements Serializable {
    private long ID;
    private Date birthDate;
    private String fName;
    private String mName;
    private String lName;
    private long orgID;
    private long positionID;
    private Date hireDate;
    private Date fireDate;
    private long personID;
    private long genderID;
    private long state;
    private String fullName;
    private String totalMark;
    private String phURL;
    private String pinCode;
    private String empPhoto;
    private long pinCodeID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String sName) {
        this.mName = sName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public long getOrgID() {
        return orgID;
    }

    public void setOrgID(long orgID) {
        this.orgID = orgID;
    }

    public long getPositionID() {
        return positionID;
    }

    public void setPositionID(long _positionID) {
        this.positionID = _positionID;
    }

    public Date getFireDate() {
        return fireDate;
    }

    public void setFireDate(Date fireDate) {
        this.fireDate = fireDate;
    }

    public long getPinCodeID() {
        return pinCodeID;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date _hireDate) {
        this.hireDate = _hireDate;
    }


    public long getGenderID() {
        return genderID;
    }

    public void setGenderID(long genderID) {
        this.genderID = genderID;
    }



    public long getPersonID() {
        return personID;
    }

    public void setPersonID(long _personID) {
        this.personID = _personID;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public void setFullName(String _fullName) {
        this.fullName = _fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setTotalMark(String _totalMark) {
        this.totalMark = _totalMark;
    }

    public String getTotalMark() {
        return totalMark;
    }

    public String getPhURL() {
        return phURL;
    }
    public void setPhURL(String phURL) {
        this.phURL = phURL;
    }



    public String getPinCode() {
        return pinCode;
    }
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }


    public long getPincodeID() {
        return pinCodeID;
    }

    public void setPinCodeID(long pinCodeID) {
        this.pinCodeID = pinCodeID;
    }

    public String getEmpPhoto() {
        return empPhoto;
    }

    public void setEmpPhoto(String empPhoto) {
        this.empPhoto = empPhoto;
    }

}
