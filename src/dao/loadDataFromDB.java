package dao;

import domain.*;

import java.sql.Connection;
import java.util.*;

/**
 * Created by a.ulviyya on 02.07.2016.
 */
public interface loadDataFromDB {
   public Result getGridContent(PagingObject po, String gridType,Map<String,String[]> parameterMap,String searchText , usersInfo loggedUser,String statusID,String from,String where ) throws Exception;
   public List<Row> getComboContent(String sType,String comboType, Map<String,String[]> parameterMap , usersInfo loggedUser) throws Exception;
   public List<listInfo> loadStrukturList(String partID,long langID) throws Exception;
   public finalInfo getOrgInfo(long treeID) throws Exception;
   public categoryFinalInfo getCategoryInfo(long treeID) throws Exception;
   public carriersInfo getCariesInfo(long carryID) throws Exception ;
   public carriersInfo getOrganizationInfo(long carryID) throws Exception ;
   public person getEmployeeInfo( long empId) throws Exception;
   public List<examples>  getExamplesInfo(long exmpID,String langID) throws Exception;
   public List<contact> getPersonContact(long perID) throws Exception;
   public List<docList> loadQRphoto(Connection con, long exmplID,long picType) throws Exception;
   public List<docList> loadPhoto(Connection con, long exmplID,long picType) throws Exception;
   public List<docList> getExamplesPictures(Connection cc, long relID, long relType) throws Exception;
   public List<examples> getExamplesOperation(Connection cc, long relID, long langID) throws Exception ;
   public List<contact> getOrgContacts(long treeID) throws Exception;


   public int checkUser(String uName) throws Exception;
   public int checkDictRecord(String text, int dictType,String org,String pos) throws Exception ;
   public usersInfo loadUserInfo(String pID) throws Exception;
   public List<person> loadEmpList( int OrgID) throws Exception;
   public List<Row> getSelectContent( String type, Map<String, String[]> parameterMap , usersInfo loggedUser) throws Exception;
   public List<subjElement> getADVSearchMenuList(String partID) throws Exception;
   public String getADVInfo(String paramID,String paramVal,String val,String cond,String typ)throws Exception;
   public List<listInfo> loadComboForAdvancedSearch(String prmID,long parametr) throws Exception;
   public List<docList> loadRightPanelPhoto(long realID, int iType) throws Exception;
   public List<categoryFinalInfo> getCategoryStructure(long catID, int id) throws Exception;
}
