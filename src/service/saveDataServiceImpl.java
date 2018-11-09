package service;

import dao.*;
import domain.*;

import java.util.*;

public class saveDataServiceImpl implements saveDataService {
    private saveDataDB saveDAO = new saveDataDBImpl();

    @Override
    public void saveStrukturInfo(String opType,String orgID,String parentID,finalInfo pfList,
                                 List<address1> paList,List<contact> pcList)throws Exception{
        saveDAO.saveStrukturInfo(opType, orgID, parentID, pfList, paList, pcList);
    }
    @Override
    public void saveCategoryInfo(String opType,String orgID,String parentID,String partID,categoryFinalInfo pfList)throws Exception {
        saveDAO.saveCategoryInfo(opType, orgID, parentID, partID, pfList);
    }
    @Override
    public void saveCarriersoryInfo(String opType,String carryID,String langID,carriersInfo pfList,docList dd)throws Exception {
    saveDAO.saveCarriersoryInfo(opType, carryID, langID, pfList,dd);
    }
    @Override
    public void saveOrganizationInfo(String opType,String organid,String langID,carriersInfo pfList,docList dd)throws Exception {
    saveDAO.saveOrganizationInfo(opType, organid, langID, pfList,dd);
    }


    @Override
    public void saveUserInfo(String opType, usersInfo uInfo, long uID) throws Exception {
        saveDAO.saveUserInfo(opType, uInfo,uID);
    }

    @Override
    public void saveEmployeeInfo(person pp, String opType,List<subjElement> piList,List<contact> pcList,docList dd) throws Exception{
        saveDAO.saveEmployeeInfo(pp, opType,piList,pcList,dd);
    }
    @Override
    public void updateExamplesStatus(String exampleID,String statusID,String reson,String userid) throws Exception{
        saveDAO.updateExamplesStatus(exampleID, statusID, reson,userid);
    }
    @Override
    public void saveExamplesInfo(examples pp, String opType,List<subjElement> piList,List<contact> pcList,List<docList> dsl,List<docList> qrDsl,String userID) throws Exception{
        saveDAO.saveExamplesInfo(pp, opType, piList, pcList, dsl,qrDsl,userID);
    }

     @Override
     public void updatePassportPath(String exampleID,String filePath,long type)throws Exception{
        saveDAO.updatePassportPath(exampleID, filePath, type);
    }

    @Override
    public void saveSubj(String subjTypeID, String cVal0AZ,String cVal0RU,String cVal0EN, String cValAZ,String cValRU,String cValEN, String newEditmp, String realID) throws Exception{
        saveDAO.saveSubj(subjTypeID, cValAZ,cValRU,cValEN, cVal0AZ,cVal0RU,cVal0EN, newEditmp, realID);
    }

    @Override
    public void deleteData(String dic_thema_id, String partID, String realID) throws Exception {
        saveDAO.deleteData(dic_thema_id, partID, realID);
    }
}
