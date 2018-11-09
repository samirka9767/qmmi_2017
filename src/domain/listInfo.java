package domain;

import java.io.Serializable;
import java.util.Date;

public class listInfo implements Serializable {
    private long id;
    private long posId;
    private long orgId;
    private long index;

    private String description1;
    private String description2;
    private int elemType;
    private long state;


    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPosId() {
        return posId;
    }

    public void setPosId(long posId) {
        this.posId = posId;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public int getElemType() {
        return elemType;
    }

    public void setElemType(int elemType) {
        this.elemType = elemType;
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }
}
