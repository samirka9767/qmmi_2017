package service;

import domain.*;

import java.util.*;

/**
 * Created by a.ulviyya on 05.07.2016.
 */
public interface saveDataService {
    public void saveStrukturInfo(String opType,String orgID,String parentID,finalInfo pfList,
                                 List<address1> paList,List<contact> pcList)throws Exception;
    public void saveCategoryInfo(String opType,String orgID,String parentID,String partID,categoryFinalInfo pfList)throws Exception;
    public void saveCarriersoryInfo(String opType,String carryID,String langID,carriersInfo pfList,docList dd)throws Exception;
    public void saveOrganizationInfo(String opType,String organid,String langID,carriersInfo pfList,docList dd)throws Exception;
    public void saveUserInfo(String opType, usersInfo uInfo ,long uID) throws Exception;
    public void saveEmployeeInfo(person pp,String opType,List<subjElement> piList,List<contact> pcList,docList dd) throws Exception;
    public void saveExamplesInfo(examples pp,String opType,List<subjElement> piList,List<contact> pcList,List<docList> dsl,List<docList> qrDsl,String userID) throws Exception;
    public void updateExamplesStatus(String exampleID,String statusID,String reson,String userid) throws Exception;
    public void deleteData(String dic_thema_id,String partID,String realID) throws Exception;
    public void updatePassportPath(String exampleID,String filePath,long type)throws Exception;
    public void saveSubj(String subjTypeID, String cVal0AZ,String cVal0RU,String cVal0EN, String cValAZ,String cValRU,String cValEN, String newEditmp, String realID) throws Exception;

}
