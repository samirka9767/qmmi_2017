/**
 * Created by a.ulviyya on 02.07.2016.
 */

var main_grid_examples = {
    'url':'cs?action=getGridContent&gridType=4',
    'col_names': ['№', localeLanguages['104'], localeLanguages['136'], localeLanguages['180'],'id'],
    'colModels': [
        {name: 'rnum', index: 'ID', width: 5},
        {name: 'katname', index: 'KATNAME', width: 30},
        {name: 'exname', index: 'EXNAME', width: 30,align:'center'},
        {name: 'status', index: 'STATUS', width: 20,hidden:'true'},
        {name: 'id', index: 'ID', width: 30,hidden:'true'}
    ]
};
var main_grid_advancedSearch = {
    'url':'cs?action=getGridContent&gridType=8',
    'col_names': ['№', localeLanguages['164'], localeLanguages['136'], localeLanguages['104'],'id'],
    'colModels': [
        {name: 'rnum', index: 'ID', width: 5},
        {name: 'menu', index: 'MENU', width: 30},
        {name: 'exname', index: 'EXNAME', width: 30},
        {name: 'katname', index: 'KATNAME', width: 20},
        {name: 'id', index: 'ID', width: 30,hidden:'true'}
    ]
};


var main_grid_organization= {
    'url':'cs?action=getGridContent&gridType=3'+'&statusID=0',
    'col_names': ['№', localeLanguages['198'], localeLanguages['174'], 'langID','photo_url','id',],
    'colModels': [
        {name: 'rnum', index: 'ID', width: 5},
        {name: 'organname', index: 'ORGANNAME', width: 45},
        {name: 'description', index: 'DESCRIPTION', width: 20,hidden:'true'},
        {name: 'langid', index: 'LANGID', width: 30,hidden:'true'},
        {name: 'photo_url', index: 'PHOTO_URL', width: 30,hidden:'true'},
        {name: 'id', index: 'ID', width: 30,hidden:'true'}
    ]
};
var main_grid_carriers= {
    'url':'cs?action=getGridContent&gridType=2'+'&statusID=0',
    'col_names': ['№', localeLanguages['198'], localeLanguages['174'], 'langID','photo_url','id',],
    'colModels': [
        {name: 'rnum', index: 'ID', width: 5},
        {name: 'carryname', index: 'CARRYNAME', width: 45},
        {name: 'description', index: 'DESCRIPTION', width: 20,hidden:'true'},
        {name: 'langid', index: 'LANGID', width: 30,hidden:'true'},
        {name: 'photo_url', index: 'PHOTO_URL', width: 30,hidden:'true'},
        {name: 'id', index: 'ID', width: 30,hidden:'true'}
    ]
};
var main_grid_employees =
{
    'url':'cs?action=getGridContent&gridType=1'+'&statusID=0',
    'col_names': ['№',  localeLanguages['181'],localeLanguages['153'], localeLanguages['154'], localeLanguages['183'],localeLanguages['184'],'perID','id','photo_url','cid'],
    'colModels': [
        {name: 'rnum', index: 'ID', width: 10},
        {name: 'fullname', index: 'FULLNAME', width: 70,align:'left'},
        {name: 'orgname', index: 'ORGNAME', width: 80,align:'left'},
        {name: 'positionname', index: 'POSITIONNAME', width: 50},
        {name: 'email', index: 'EMAIL', width: 25},
        {name: 'phone', index: 'PHONE', width: 25},
        {name: 'perid', index: 'PERID', width: 25,hidden:'true'},
        {name: 'id', index: 'ID', width: 25,hidden:'true'},
        {name: 'photo_url', index: 'PHOTO_URL', width: 25,hidden:'true'},
        {name: 'cid', index: 'CID', width: 25,hidden:'true'}
    ]

};

var main_grid_users = {
    'url': 'cs?action=getGridContent&gridType=7'+'&statusID=0',
    'col_names': ['№', localeLanguages['181'],localeLanguages['157'],localeLanguages['154'], localeLanguages['163'],'empid','perid','id'],
    'colModels': [
        {name: 'rnum', index: 'ID', width: 5},
        {name: 'fullname', index: 'FULLNAME', width: 100,align:'left'},
        {name: 'gen', index: 'GEN', width: 20,align:'left'},
        {name: 'positionname', index: 'POSITIONNAME', width: 60},
        {name: 'user_name', index: 'USER_NAME', width: 40},
        {name: 'empid', index: 'empid', width: 40,hidden:true},
        {name: 'perid', index: 'perid', width: 40,hidden:true},
        {name: 'id', index: 'ID', width: 55,hidden:true}
    ]

};
