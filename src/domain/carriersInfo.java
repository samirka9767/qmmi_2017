package domain;

import java.io.Serializable;

/**
 * Created by Samire on 12/7/2016.
 */
public class carriersInfo implements Serializable {

    private long state;
    private long langIDaz;
    private long langIDru;
    private long langIDen;
    private long carryID;
    private String carryNameAz;
    private String carryNameRu;
    private String carryNameEn;
    private String desAz;
    private String desRu;
    private String desEn;
    private String phURL;

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public long getLangIDaz() {
        return langIDaz;
    }

    public void setLangIDaz(long langIDaz) {
        this.langIDaz = langIDaz;
    }

    public long getLangIDru() {
        return langIDru;
    }

    public void setLangIDru(long langIDru) {
        this.langIDru = langIDru;
    }

    public long getLangIDen() {
        return langIDen;
    }

    public void setLangIDen(long langIDen) {
        this.langIDen = langIDen;
    }

    public long getCarryID() {
        return carryID;
    }

    public void setCarryID(long carryID) {
        this.carryID = carryID;
    }

    public String getCarryNameAz() {
        return carryNameAz;
    }

    public void setCarryNameAz(String carryNameAz) {
        this.carryNameAz = carryNameAz;
    }

    public String getCarryNameRu() {
        return carryNameRu;
    }

    public void setCarryNameRu(String carryNameRu) {
        this.carryNameRu = carryNameRu;
    }

    public String getCarryNameEn() {
        return carryNameEn;
    }

    public void setCarryNameEn(String carryNameEn) {
        this.carryNameEn = carryNameEn;
    }

    public String getDesAz() {
        return desAz;
    }

    public void setDesAz(String desAz) {
        this.desAz = desAz;
    }

    public String getDesRu() {
        return desRu;
    }

    public void setDesRu(String desRu) {
        this.desRu = desRu;
    }

    public String getDesEn() {
        return desEn;
    }

    public void setDesEn(String desEn) {
        this.desEn = desEn;
    }

    public String getPhURL() {
        return phURL;
    }

    public void setPhURL(String phURL) {
        this.phURL = phURL;
    }
}
