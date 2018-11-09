package domain;

import java.util.List;

/**
 * Created by a.ulviyya on 30.06.2016.
 */
public class usersInfo {
    private long id;
    private long empid;
    private long perid;
    private String userName;
    private String pswd;
    private String fullname;
    private long orgID;
    private long userType;
    private long userAct;
    private String rights;
    private long currLangId;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }


    public long getCurrLangId() {
        return currLangId;
    }

    public void setCurrLangId(long currLangId) {
        this.currLangId = currLangId;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }
    public void setFullName(String _fullname){this.fullname=_fullname;}
    public String getFullName(){return fullname;}

    public long getOrgID() {
        return orgID;
    }

    public void setOrgID(long orgID) {
        this.orgID = orgID;
    }
    public long getUserType() {
        return userType;
    }

    public void setUserType(long userType) {
        this.userType = userType;
    }

    public long getEmpid() {
        return empid;
    }

    public void setEmpid(long empid) {
        this.empid = empid;
    }

    public long getPerid() {
        return perid;
    }

    public void setPerid(long perid) {
        this.perid = perid;
    }
    public long getUserAct() {
        return userAct;
    }

    public void setUserAct(long userAct) {
        this.userAct = userAct;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }
}

