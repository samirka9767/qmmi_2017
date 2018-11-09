package domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class docList implements Serializable {
    private long id;
    private long partID;
    private long relID;
    private long docTypeID;
    private int hasHTMLfile;
    private int docCategory;
    private String docTypeS;
    private String docNumber;
    private String docDate;
    private int docDateLibrary;
    private String docNoteAz;
    private String docNoteRu;
    private String docNoteEn;
    private String filePath;


    private String qrCodePath;
    private String status;
    private int docCount;
    private int editFile;
    private long folderID;
    
    private String url;
    private InputStream phIn;
    private List<listInfo> foldList;

    private String docNameAz;
    private String docNameRu;
    private String docNameEn;

    private String authorAz;
    private String authorRu;
    private String authorEn;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String link;


    public String getDocNoteRu() {
        return docNoteRu;
    }

    public void setDocNoteRu(String docNoteRu) {
        this.docNoteRu = docNoteRu;
    }

    public String getDocNoteEn() {
        return docNoteEn;
    }

    public void setDocNoteEn(String docNoteEn) {
        this.docNoteEn = docNoteEn;
    }

    public String getDocNameAz() {
        return docNameAz;
    }

    public void setDocNameAz(String docNameAz) {
        this.docNameAz = docNameAz;
    }

    public String getDocNameRu() {
        return docNameRu;
    }

    public void setDocNameRu(String docNameRu) {
        this.docNameRu = docNameRu;
    }

    public String getDocNameEn() {
        return docNameEn;
    }

    public void setDocNameEn(String docNameEn) {
        this.docNameEn = docNameEn;
    }

    public String getAuthorAz() {
        return authorAz;
    }

    public void setAuthorAz(String authorAz) {
        this.authorAz = authorAz;
    }

    public String getAuthorRu() {
        return authorRu;
    }

    public void setAuthorRu(String authorRu) {
        this.authorRu = authorRu;
    }

    public String getAuthorEn() {
        return authorEn;
    }

    public void setAuthorEn(String authorEn) {
        this.authorEn = authorEn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPartID() {
        return partID;
    }

    public void setPartID(long partID) {
        this.partID = partID;
    }

    public long getDocTypeID() {
        return docTypeID;
    }

    public void setDocTypeID(long docTypeID) {
        this.docTypeID = docTypeID;
    }

    public int getDocCategory() {
        return docCategory;
    }

    public void setDocCategory(int docCategory) {
        this.docCategory = docCategory;
    }

    public String getDocTypeS() {
        return docTypeS;
    }

    public void setDocTypeS(String docTypeS) {
        this.docTypeS = docTypeS;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public int getDocDateLibrary(){
        return  docDateLibrary;
    }

    public void setDocDateLibrary(int docDateLibrary){
          this.docDateLibrary = docDateLibrary;
    }
    public String getDocNoteAz() {
        return docNoteAz;
    }

    public void setDocNoteAz(String docNoteAz) {
        this.docNoteAz = docNoteAz;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDocCount() {
        return docCount;
    }

    public void setDocCount(int docCount) {
        this.docCount = docCount;
    }

    public int getEditFile() {
        return editFile;
    }

    public void setEditFile(int editFile) {
        this.editFile = editFile;
    }

    public long getRelID() {
        return relID;
    }

    public void setRelID(long relID) {
        this.relID = relID;
    }

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public InputStream getPhIn() {
        return phIn;
    }

    public void setPhIn(InputStream phIn) {
        this.phIn = phIn;
    }

    public long getFolderID() {
        return folderID;
    }

    public void setFolderID(long folderID) {
        this.folderID = folderID;
    }

    public List<listInfo> getFoldList() {
        return foldList;
    }

    public void setFoldList(List<listInfo> foldList) {
        this.foldList = foldList;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }

    public int getHasHTMLfile() {
        return hasHTMLfile;
    }

    public void setHasHTMLfile(int hasHTMLfile) {
        this.hasHTMLfile = hasHTMLfile;
    }
}
