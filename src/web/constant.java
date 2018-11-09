package web;

public class constant {

    public static final String RESOURCES_LOCALE = "resourcesLocale";
    public static final String CONSTANTS = "constants";
    public static final String MESSAGE = "message";


    public static final String ACTION_LOGIN = "login";
    public static final String USER = "user";
    public static final String PAGE_MAIN = "index.jsp";
    public static final String PAGE_LOGIN = "login.jsp";
    public static final String ACTION_GET_GRID_CONTENT = "getGridContent";
    public static final String COMBO_CONTENT = "comboContent";
    public static final String GRID_TOTAL_COUNT = "gridTotalCount";
    public static final String GRID_CONTENT = "grid_content";
    public static final String REQUIRES_AUTH = "requires_auth";
    public static final String ACTION_USER_ACCESS = "loadAccessInfo";
    public static final String ACTION_SET_SEARCH_TEXT = "setData";
    public static final String ACTION_INT_SEARCH = "intSearch";


    public static final String ACTION_FILLCOMBO_BY_PARID ="fillComboByParamID";

    public static final String ACTION_SAVE_EMPLOYEE_INFO = "saveEmployeeInfo";
    public static final String ACTION_SAVE_EXAMPLES_INFO = "saveExamplesInfo";
    public static final String ACTION_GET_EMPLOYEE_INFO = "getEmployeeInfo";
    public static final String ACTION_GET_EXAMPLES_INFO = "getExamplesInfo";
    public static final String SES_PINFO = "personalInfo";
    public static final String SES_CATST = "categoryStructure";
    public static final String SES_PEDUCATION = "personalEducation";
    public static final String SES_PADDRESS = "personalAddress";
    public static final String SES_PCONTACTS = "personalContact";
    public static final String SES_PICTURES = "examplePictures";
    public static final String SES_QRCODE = "examplePictures";
    public static final String SES_SPINFO = "personalInfoMeetings";
    public static final String SES_SPEMPLOYEEPH = "employeePhotos";

    public static final String ACTION_SAVE_CARRIERS_INFO = "saveCarriersInfo";
    public static final String ACTION_UPDATE_EXAMPLE_STATUS = "updateStatus";

    public static final String ACTION_SET_SESSION = "setSession";
    public static final String SES_QUESTIONSPH = "questionsPhotos";
    public static final String SES_CAR_ORG = "carOrgPhotos";
    public static final String SES_CAR_ORG1 = "carOrgPhotos1";
    ;
    public static final String ACTION_GET_FILE = "loadPhoto";

    public static final String SES_EMPLIST = "EmployeeListForExam";
    public static final String ACTION_LOAD_EMPlIST = "loadEmpList";

    // struktur
    public static final String ACTION_LOAD_STRUKTUR = "loadStrukturList";
    public static final String ACTION_SAVE_STRUKTUR = "saveStrukturInfo";
    public static final String ACTION_GET_STRUKTUR_ORG_INFO = "getStrukturFullInfo";
    public static final String ACTION_GET_CARRIERS_INFO = "getCarriersInfo";
    public static final String SES_ORG_INFO = "getOrgInfo";
    public static final String SES_CATEGORY_INFO = "getCategoryInfo";
    public static final String SES_CARRY_INFO = "getCarryInfo";
    public static final String SES_ORGAN_INFO = "getOrganInfo";


    public static final String ACTION_DOWN_FILE ="opFile";
    public static final String ACTION_LOAD_RIGHT_PANEL ="loadRightPanel";


    //Etrafli axtariw
    public static final String ACTION_LOAD_PARAM ="loadSearchList";
    public static final String ACTION_MAKE_ADV_PARAM ="makeADVParams";

    public static final String SES_ADV_WHERE = "advSearchWhere";
    public static final String SES_ADV_FROM = "advSearchFrom";

    public static final String ACTION_DELETE_SESSION_PARAMS1 ="deleteSessionParams1";

    // users
    public static final String ACTION_SAVE_USERS = "saveUsers";
    public static final String ACTION_LOAD_USERINFO = "loadUserInfo";
    public static final String ACTION_CHECK_USERNAME = "chkUser";
    //tables
    public static final String TABLE_COM_PERSONS = "CULTTREASREGISTER_COMMON.COM_PERSONS";
    public static final String TABLE_MAN_EXAMPLE_INF = "CULTTREASREGISTER_MANAGEMENT.MAN_EXAMPLE_INF";
    public static final String TABLE_MAN_RELATED_CATEGORY_EXAMPLE = "CULTTREASREGISTER_MANAGEMENT.MAN_RELATED_CATEGORY_EXAMPLE";
    public static final String TABLE_MAN_EXAMPLE_MULTY_OPTION = "CULTTREASREGISTER_MANAGEMENT.MAN_EXAMPLE_MULTY_OPTION";
    public static final String TABLE_MAN_EXAMPLES = "CULTTREASREGISTER_MANAGEMENT.MAN_EXAMPLE";
    public static final String TABLE_MAN_EXAMPLE_STATUS = "CULTTREASREGISTER_MANAGEMENT.MAN_EXAMPLE_STATUS";
    public static final String TABLE_MAN_EMPLOYEES = "CULTTREASREGISTER_HR.HR_EMPLOYEES";
    public static final String TABLE_COM_PERSON_CONTACTS = " CULTTREASREGISTER_COMMON.COM_PERSON_CONTACTS ";
    public static final String TABLE_COM_PERSON_UNIQ = " CULTTREASREGISTER_COMMON.COM_PERSON_UNIQ ";
    public static final String TABLE_EXAMPLE_PHOTO =" CULTTREASREGISTER_MANAGEMENT.MAN_EXAMPLE_MEDIA ";
    public static final String TABLE_EXAMPLE_MEDIA_LANG =" CULTTREASREGISTER_MANAGEMENT.MAN_EXAMPLE_MEDIA_LANG ";
    public static final String TABLE_SEC_USERS = " CULTTREASREGISTER_SECURITY.SEC_USERS ";
    public static final String TABLE_SEC_USERS_ACCESS = " CULTTREASREGISTER_SECURITY.SEC_USER_ACCESS ";

    public static final String TABLE_ORG_TREE = "CULTTREASREGISTER_ORGANIZATION.ORG_TREE ";
    public static final String TABLE_ORG_TREE_DETAILS = " CULTTREASREGISTER_ORGANIZATION.ORG_TREE_INF_DETAILS ";
    public static final String TABLE_CAT_TREE = "CULTTREASREGISTER_MANAGEMENT.MAN_CATEGORIES ";
    public static final String TABLE_CAT_TREE_DETAILS = " CULTTREASREGISTER_MANAGEMENT.MAN_CATEGORIES_INF_DETAILS ";
    public static final String TABLE_ORGAN = "CULTTREASREGISTER_MANAGEMENT.MAN_ORGANIZATION ";
    public static final String TABLE_ORGAN_DETAILS = " CULTTREASREGISTER_MANAGEMENT.MAN_ORGAN_INF_DETAILS";
    public static final String TABLE_CAR = "CULTTREASREGISTER_MANAGEMENT.MAN_CARRIES ";
    public static final String TABLE_CAR_DETAILS = " CULTTREASREGISTER_MANAGEMENT.MAN_CARRIES_INF_DETAILS";
    public static final String TABLE_ORG_TREE_CONTACT =" CULTTREASREGISTER_ORGANIZATION.ORG_TREE_INF_CONTACT";
    public static final String ORG_TREE_INF_ADDRESS = " CULTTREASREGISTER_ORGANIZATION.ORG_TREE_INF_ADDRESS";

    public static final String TABLE_COM_DICTIONARY = "CULTTREASREGISTER_COMMON.COM_DICTIONARY";
    public static final String TABLE_COM_LOCALE_RESOURCE = "CULTTREASREGISTER_COMMON.COM_LOCALE_RESOURCE";
    public static final String  TABLE_HR_ORG_POSITION = "CULTTREASREGISTER_HR.HR_ORG_POSITION";
    public static final String TABLE_COM_PERSON_PHOTO = "CULTTREASREGISTER_COMMON.COM_PERSON_PHOTO";

    public static final String ACTION_DELETE_DATA = "deleteData";
    public static final String ACTION_LOAD_DICT_LIST = "loadDicList";

    public static final String ACTION_SAVE_SUBJ1 = "saveSubjectDictionary";
    public static final String ACTION_CHECK_RECORD_COUNT_DC = "checkRecordCountInDIC";

    public static final String ATT_QMMI = "QMMI/";
    public static final String ATT_PIC = "PICTURES/";
    public static final String ATT_LIBRARY = "LIBRARY/";
    public static final String ATT_DOCUMENTS = "DOCUMENTS/";
    public static final String ATT_QRCODE = "QRCODE/";
    public static final String ATT_EMPLOYEES = "EMPLOYEES/";
    public static final String ATT_CARRIERS = "CARRIERS/";
    public static final String ATT_PASSPORT = "PASSPORT/";
    public static final String ATT_MEDIA = "MEDIA/";
    public static final String ATT_HTML = "HTML/";
}
