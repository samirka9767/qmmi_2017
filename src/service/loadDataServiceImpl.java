package service;

import dao.loadDataFromDB;
import dao.loadDataFromDBImpl;
import domain.*;

import java.sql.Connection;
import java.util.*;

public class loadDataServiceImpl implements loadDataService {
    private loadDataFromDB ldDAO = new loadDataFromDBImpl();

    public List<Row> getComboContent(String sType, String comboType, Map<String, String[]> parameterMap, usersInfo loggedUser) throws Exception {
        return ldDAO.getComboContent(sType, comboType, parameterMap, loggedUser);
    }

    @Override
    public Result getGridContent(PagingObject po, String gridType, Map<String, String[]> parameterMap, String searchText, usersInfo loggedUser,String statusID,String from,String where) throws Exception {
        return ldDAO.getGridContent(po, gridType, parameterMap, searchText, loggedUser,statusID,from,where);

    }

    @Override
    public List<listInfo> loadStrukturList(String partID,long langID) throws Exception {
        return ldDAO.loadStrukturList(partID,langID);
    }

    @Override
    public finalInfo getOrgInfo(long treeID) throws Exception {
        return ldDAO.getOrgInfo(treeID);
    }
    @Override
    public categoryFinalInfo getCategoryInfo(long treeID) throws Exception {
        return ldDAO.getCategoryInfo(treeID);
    }
    @Override
    public carriersInfo getCariesInfo(long carryID) throws Exception {
        return ldDAO.getCariesInfo(carryID);
    }
    @Override
    public carriersInfo getOrganizationInfo(long carryID) throws Exception {
        return ldDAO.getOrganizationInfo(carryID);
    }

    @Override
    public List<contact> getOrgContacts(long treeID) throws Exception {
        return ldDAO.getOrgContacts(treeID);
    }

    @Override
    public person getEmployeeInfo(long empId) throws Exception {
        return ldDAO.getEmployeeInfo(empId);
    }

    @Override
    public List<examples> getExamplesInfo(long exmpID,String langID) throws Exception{
        return ldDAO.getExamplesInfo(exmpID, langID);
    }


    @Override
    public List<contact> getPersonContact(long perID) throws Exception {
        return ldDAO.getPersonContact(perID);
    }

    @Override
    public List<docList> loadQRphoto(Connection con, long exmplID,long picType) throws Exception  {
        return ldDAO.loadQRphoto(con,exmplID,picType);
    }


    @Override
    public List<docList> loadPhoto(Connection con, long exmplID,long picType) throws Exception  {
        return ldDAO.loadPhoto(con,exmplID,picType);
    }


    @Override
    public List<docList> getExamplesPictures(Connection cc, long relID, long relType) throws Exception  {
        return ldDAO.getExamplesPictures(cc, relID, relType);
    }

    @Override
    public List<examples> getExamplesOperation(Connection cc, long relID, long langID) throws Exception   {
        return ldDAO.getExamplesOperation(cc, relID, langID);
    }




    @Override
    public int checkUser(String uName) throws Exception {
        return ldDAO.checkUser(uName);
    }


    @Override
    public int checkDictRecord(String text, int dictType,String org,String pos) throws Exception {
        return ldDAO.checkDictRecord(text, dictType, org, pos);
    }

    @Override
    public usersInfo loadUserInfo(String pID) throws Exception {
        return ldDAO.loadUserInfo(pID);
    }

    @Override
    public List<docList> loadRightPanelPhoto(long realID, int iType) throws Exception  {
        return ldDAO.loadRightPanelPhoto(realID, iType);
    }

    @Override
    public List<categoryFinalInfo> getCategoryStructure(long catID, int id) throws Exception {
        return ldDAO.getCategoryStructure(catID,id);
    }


    @Override
    public List<person> loadEmpList(int OrgID) throws Exception {
        return ldDAO.loadEmpList(OrgID);
    }


    @Override
    public List<Row> getSelectContent(String type, Map<String, String[]> parameterMap, usersInfo loggedUser) throws Exception {
        return ldDAO.getSelectContent(type, parameterMap, loggedUser);
    }


    @Override
    public List<subjElement> getADVSearchMenuList(String partID) throws Exception {
        return ldDAO.getADVSearchMenuList(partID);
    }

    @Override
    public String getADVInfo(String paramID, String paramVal, String val, String cond, String typ) throws Exception {
        return ldDAO.getADVInfo(paramID, paramVal, val, cond, typ);
    }
    @Override
    public List<listInfo> loadComboForAdvancedSearch(String prmID,long parametr) throws Exception{
        return ldDAO.loadComboForAdvancedSearch(prmID, parametr);
    }


}
