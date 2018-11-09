package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class categoryFinalInfo implements Serializable {
    private long ID;
    private long index1;
    private long tip;
    private long parentID;
    private String parentID1;
    private long langIdAz;
    private long langIdRu;
    private long langIdEn;
    private long finTypeID;
    private double order_by;
    private String finTypeName;
    private String Notes;
    private String NotesRu;
    private String NotesEn;
    private String regCodePart;
    private String catName;
    private String catNameRu;
    private String catNameEn;
    private long state;
    private String formula;
    private String name;

    public String getRegCodePart() {
        return regCodePart;
    }

    public void setRegCodePart(String regCodePart) {
        this.regCodePart = regCodePart;
    }

    public long getLangIdAz() {
        return langIdAz;
    }

    public void setLangIdAz(long langIdAz) {
        this.langIdAz = langIdAz;
    }

    public long getLangIdRu() {
        return langIdRu;
    }

    public void setLangIdRu(long langIdRu) {
        this.langIdRu = langIdRu;
    }

    public long getLangIdEn() {
        return langIdEn;
    }

    public void setLangIdEn(long langIdEn) {
        this.langIdEn = langIdEn;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatNameRu() {
        return catNameRu;
    }

    public void setCatNameRu(String catNameRu) {
        this.catNameRu = catNameRu;
    }

    public String getCatNameEn() {
        return catNameEn;
    }

    public void setCatNameEn(String catNameEn) {
        this.catNameEn = catNameEn;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public String getNotesRu() {
        return NotesRu;
    }

    public void setNotesRu(String NotesRu) {
        this.NotesRu = NotesRu;
    }


    public String getNotesEn() {
        return NotesEn;
    }

    public void setNotesEn(String NotesEn) {
        this.NotesEn = NotesEn;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public String getoName() {
        return catName;
    }

    public void setoName(String catName) {
        this.catName = catName;
    }
    public String getoNameRu() {
        return catNameRu;
    }

    public void setoNameRu(String catNameRu) {
        this.catNameRu = catNameRu;
    }
    public String getoNameEn() {
        return catNameEn;
    }

    public void setoNameEn(String catNameEn) {
        this.catNameEn = catNameEn;
    }

    public long getIndex1() {
        return index1;
    }

    public void setIndex1(long index1) {
        this.index1 = index1;
    }


    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getParentID() {
        return parentID;
    }

    public void setParentID(long parentID) {
        this.parentID = parentID;
    }

    public long getTip() {
        return tip;
    }

    public void setTip(long tip) {
        this.tip = tip;
    }

    public String getParentID1() {
        return parentID1;
    }

    public void setParentID1(String parentID1) {
        this.parentID1 = parentID1;
    }
}
