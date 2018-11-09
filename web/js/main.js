var documents = [];
var person = [];
var qrPic = [];
var pothdoc = [];
var ExampleOperation = [];
var referenceFile = [];
var rightPanel = [];
var media = [];
var library = [];
var categoryStructure = [];
var advSearch1 = [];
var menu;
var model = {};
var lists = [];
var listm = [];
var menuList1 = [];
var menuList2 = [];
var buttonList1 = [];
var btnL1 = [];
var buttonList2 = [];
var btnL2 = [];
var rightPanel = [];
var partID = 0;
var topPosition = 0;
var rightssecond = "";
var mNewEdit = 0;
var gridRealID = 0;
var treeID = -1;
var employee = [];
var examplesMass = [];
var trainings_2 = [];
var documents = [];
var person = [];
var correctAnswer = 0;
var questionsAnswer = [];
var mpPartID1 = 0;
var selectedRowId = 0;
var question = [];
var employeeList = [];
var employeeListExam = [];
var examInfoList = [];
var planguageInfo = [];
var peducation = [];
var pcontact = [];
var eexam = [];
var etraining = [];
var viewMode = 0;
var menuTitle = "";
var examples = {
    contentHeader: '&nbsp;',
    title: localeLanguages['101'],
    grid: main_grid_examples,
    dialog_buttons: [
        {
            class: "pullLeft", id: "final_ex",
            text: localeLanguages['215'], click: function () {
            mNewEdit = 0;
            var load_jsp_nameDel = "modals/yekun.jsp";
            load_dialog_status(2);
            $('#yekun_dialog').load(load_jsp_nameDel, function () {
                $("#yekun_dialog").dialog("open");
                return false;
            });

        }

        },
        {
            text: localeLanguages['134'], click: function () {
            var qrPictureName = $("#qrcode img").attr('src');

            var arrayCategory = $('#ExmCategory option:selected');
            var arrayGroup = $('#ExmGroup option:selected');
            var arrayClass = $('#ExmClass option:selected');
            var arrayType = $('#ExmType option:selected');
            var arrayGenre = $('#ExmGenre option:selected');
            var OvertakenRegionID = $('#OvertakenRegion :selected').val();
            var mete = $('#mete :selected').val();

            var arrayCarries = $('#cCarriesListMulti option:selected');
            var arrayOrganization = $('#cOrganization option:selected');
            var arrayYunesko = $('#yuneskoDomain option:selected');

            var categoryListId = '';
            var groupListId = '';
            var classListId = '';
            var typeListId = '';
            var genderListId = '';
            var carriesListId = '';
            var organsListId = '';
            var yuneskoListId = '';

            $('#region').attr('multiple', 'multiple');
            var arrayRegionID = $('#region option:selected');
            var regionListId = '';
            for (var i = 0; i < arrayRegionID.length; i++) {
                regionListId += $(arrayRegionID[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayCategory.length; i++) {
                categoryListId += $(arrayCategory[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayGroup.length; i++) {
                groupListId += $(arrayGroup[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayClass.length; i++) {
                classListId += $(arrayClass[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayType.length; i++) {
                typeListId += $(arrayType[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayGenre.length; i++) {
                genderListId += $(arrayGenre[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayCarries.length; i++) {
                carriesListId += $(arrayCarries[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayOrganization.length; i++) {
                organsListId += $(arrayOrganization[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayYunesko.length; i++) {
                yuneskoListId += $(arrayYunesko[i]).val() + "/---/";
            }

            if ($("#exmNameAz").val().trim() != ''  && $("#exmKeyWordAz").val().trim() != ''   && categoryListId != ''  && regionListId != '') {

                var form = $("form", this);
                var g = $('#grid');
                var sel_id = g.jqGrid('getGridParam', 'selrow');
                var exmpID = g.jqGrid('getCell', sel_id, 'id');
                var url = form.attr("action") + '&operType=' + mNewEdit + '&id=' + exmpID
                    + '&arrayCategoryList=' + categoryListId + '&arrayGroupList=' + groupListId + '&arrayClassList=' + classListId
                    + '&arrayTypeList=' + typeListId + '&arrayGenreList=' + genderListId + '&carriesListId=' + carriesListId + '&yuneskoListId=' + yuneskoListId
                    + '&organsListId=' + organsListId + '&qrPicture=' + qrPictureName + '&arrayRegionID=' + regionListId;

                $.post(url, form.serialize(), function (data) {
                    success : {
                        main_grid(model);
                    }
                });
                $(this).dialog("close");
            }
            else {
                if (localeLanguages['192'] == '1000') {
                    alert('"Ad Az/Ru/En","Kateqoriya" sahələri doldurulmalıdır.');
                } else if (localeLanguages['192'] == '1001') {
                    alert('"Имя Az/Ru/En","Категория"  являются важными полями.');
                } else if (localeLanguages['192'] == '1002') {
                    alert('"Name Az/Ru/En","Category"  are necessary fields.');
                }
            }
        }

        },
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]
};
var approvedExamples = {
    contentHeader: '&nbsp;',
    grid: main_grid_examples,

    dialog_buttons: [
        {
            class: "pullLeft",id: "viewApprove",
            text: localeLanguages['216'], click: function () {
            mNewEdit = 0;
            var load_jsp_nameDel = "modals/yekun.jsp";
            load_dialog_status(3);
            $('#yekun_dialog').load(load_jsp_nameDel, function () {
                $("#yekun_dialog").dialog("open");
                return false;
            });

        }

        },
        {
            text: localeLanguages['134'], click: function () {
            var qrPictureName = $("#qrcode img").attr('src');

            var arrayCategory = $('#ExmCategory option:selected');
            var arrayGroup = $('#ExmGroup option:selected');
            var arrayClass = $('#ExmClass option:selected');
            var arrayType = $('#ExmType option:selected');
            var arrayGenre = $('#ExmGenre option:selected');
            var OvertakenRegionID = $('#OvertakenRegion :selected').val();
            var mete = $('#mete :selected').val();

            var arrayCarries = $('#cCarriesListMulti option:selected');
            var arrayOrganization = $('#cOrganization option:selected');
            var arrayYunesko = $('#yuneskoDomain option:selected');

            var categoryListId = '';
            var groupListId = '';
            var classListId = '';
            var typeListId = '';
            var genderListId = '';
            var carriesListId = '';
            var organsListId = '';
            var yuneskoListId = '';

            $('#region').attr('multiple', 'multiple');
            var arrayRegionID = $('#region option:selected');
            var regionListId = '';
            for (var i = 0; i < arrayRegionID.length; i++) {
                regionListId += $(arrayRegionID[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayCategory.length; i++) {
                categoryListId += $(arrayCategory[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayGroup.length; i++) {
                groupListId += $(arrayGroup[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayClass.length; i++) {
                classListId += $(arrayClass[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayType.length; i++) {
                typeListId += $(arrayType[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayGenre.length; i++) {
                genderListId += $(arrayGenre[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayCarries.length; i++) {
                carriesListId += $(arrayCarries[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayOrganization.length; i++) {
                organsListId += $(arrayOrganization[i]).val() + "/---/";
            }
            for (var i = 0; i < arrayYunesko.length; i++) {
                yuneskoListId += $(arrayYunesko[i]).val() + "/---/";
            }

            if ($("#exmNameAz").val().trim() != ''  && $("#exmKeyWordAz").val().trim() != ''   && categoryListId != ''  && regionListId != '') {

                var form = $("form", this);
                var g = $('#grid');
                var sel_id = g.jqGrid('getGridParam', 'selrow');
                var exmpID = g.jqGrid('getCell', sel_id, 'id');
                var url = form.attr("action") + '&operType=' + mNewEdit + '&id=' + exmpID
                    + '&arrayCategoryList=' + categoryListId + '&arrayGroupList=' + groupListId + '&arrayClassList=' + classListId
                    + '&arrayTypeList=' + typeListId + '&arrayGenreList=' + genderListId + '&carriesListId=' + carriesListId + '&yuneskoListId=' + yuneskoListId
                    + '&organsListId=' + organsListId + '&qrPicture=' + qrPictureName + '&arrayRegionID=' + regionListId;

                $.post(url, form.serialize(), function (data) {
                    success : {
                        main_grid(model);
                    }
                });
                $(this).dialog("close");
            }
            else {
                if (localeLanguages['192'] == '1000') {
                    alert('"Ad Az/Ru/En","Kateqoriya" ,"İşğal olunmuş əraziyə aid","Mətn Az/Ru/En" sahələri doldurulmalıdır.');
                } else if (localeLanguages['192'] == '1001') {
                    alert('"Имя Az/Ru/En","Категория" ,"На оккупированной территории","Текст Az/Ru/En" являются важными полями.');
                } else if (localeLanguages['192'] == '1002') {
                    alert('"Name Az/Ru/En","Category" ,"In the occupied territory","Text Az/Ru/En" are necessary fields.');
                }
            }
        }

        },
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]

};
var unapprovedExamples = {
    contentHeader: '&nbsp;',
    grid: main_grid_examples,
    dialog_buttons: [
        {
            class: "pullLeft",id: "viewUnApprove",
            text: localeLanguages['216'], click: function () {
            var load_jsp_nameDel = "modals/yekun.jsp";
            load_dialog_status(1);
            $('#yekun_dialog').load(load_jsp_nameDel, function () {
                $("#yekun_dialog").dialog("open");
                return false;
            });

        }
        },
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]
};
var carriers = {
    contentHeader: '&nbsp;',
    grid: main_grid_carriers,
    dialog_buttons: [
        {
            text: localeLanguages['134'], click: function () {

            if ($("#cNameAz").val().trim() != '') {
                var form = $("form", this);
                var g = $('#grid');
                var sel_id = g.jqGrid('getGridParam', 'selrow');
                var carrID = g.jqGrid('getCell', sel_id, 'id');
                var langID = g.jqGrid('getCell', sel_id, 'langID');

                var url = form.attr("action") + '&operType=' + mNewEdit + '&partID=' + partID;
                if (mNewEdit == 2) {
                    url = url + '&id=' + carrID
                }
                $.post(url, form.serialize(), function (data) {
                    success : {
                        main_grid(model);
                    }
                });
                $(this).dialog("close");
            }
            else {
                if (localeLanguages['192'] == '1000') {
                    alert('"Mutleq sahələr doldurulmalıdır.');
                } else if (localeLanguages['192'] == '1001') {
                    alert('"Ad Az/Ru/En","Kateqoriya" ,"İşğal olunmuş əraziyə aid","Mətn Az/Ru/En" sahələri doldurulmalıdır.');
                } else if (localeLanguages['192'] == '1001') {
                    alert('"Ad Az/Ru/En","Kateqoriya" ,"İşğal olunmuş əraziyə aid","Mətn Az/Ru/En" sahələri doldurulmalıdır.');
                }
            }
        }

        },
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]
};
var organization = {
    contentHeader: '&nbsp;',
    grid: main_grid_organization,
    dialog_buttons: [
        {
            text: localeLanguages['134'], click: function () {


            if ($("#cNameAz").val().trim() != '') {

                var form = $("form", this);
                var g = $('#grid');
                var sel_id = g.jqGrid('getGridParam', 'selrow');
                var orgID = g.jqGrid('getCell', sel_id, 'id');
                var langID = g.jqGrid('getCell', sel_id, 'langID');

                var url = form.attr("action") + '&operType=' + mNewEdit + '&partID=' + partID;
                if (mNewEdit == 2) {
                    url = url + '&id=' + orgID
                }

                $.post(url, form.serialize(), function (data) {
                    success : {
                        main_grid(model);
                    }
                });
                $(this).dialog("close");
            }
            else {
                alert('"Ad sahəsi mütləq sahədir!')
            }
        }

        },
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]
};

var advancedSearch = {
    contentHeader: '&nbsp;',
    grid: main_grid_advancedSearch,

    dialog_buttons: [
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]

};
var employees = {
    contentHeader: '&nbsp;',
    grid: main_grid_employees,
    dialog_buttons: [
        {
            text: localeLanguages['134'], click: function () {
            var organization = $("#empOrganization :selected").val();
            var position = $("#empPosition :selected").val();


            if ($("#fname").val().trim() != '' && $("#mName").val().trim() != '' && $("#lname").val().trim() != '' && $("#hireDate2").val().trim() != '' && organization != -1 && position != -1) {

                var form = $("form", this);
                var g = $('#grid');
                var sel_id = g.jqGrid('getGridParam', 'selrow');
                var empID = g.jqGrid('getCell', sel_id, 'id');
                var perID = g.jqGrid('getCell', sel_id, 'perid');
                var cid = g.jqGrid('getCell', sel_id, 'cid');
                var url = form.attr("action") + '&operType=' + mNewEdit + '&id=' + empID + '&perId=' + perID + '&cid=' + cid;

                url = url + '&pinCode=' + $("#pinCode").val() + '&gender=' + $("#gender").val() + '&empBirthday=' + $("#empBirthday").val();
                //+ '&organization='+organization+'&position='+position
                $.post(url, form.serialize(), function (data) {
                    success : {
                        main_grid(model);
                    }
                });
                $(this).dialog("close");
            }
            else {
                alert('"Ad","Soyad","Ata adı","Doğum tarixi","Qurum","Vəzifə" sahələri mütləq sahələrdir!')
            }
        }
        },
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]
};

var category = {

    dialog_buttons1: [
        {
            text: localeLanguages['134'], click: function () {

            var orgType = $("#categoryType :selected").val();

            if ($("#categorytName").val().trim() == '' ||  orgType == -1 || $("#regCodePart").val().trim() == '') {
                if (localeLanguages['192'] == '1000') {
                    alert('"Ad",Tipi və Kod sahələri doldurulmalıdır.');
                } else if (localeLanguages['192'] == '1001') {
                    alert('"Имя" важное поле.');
                } else if (localeLanguages['192'] == '1002') {
                    alert('"Name" is necessary field.');
                }
                return false;
            } else {
                var form = $("form", this);
                var url = form.attr("action") + '&operType=' + mNewEdit + '&parentID=' + treeID + '&partID=' + partID;
                if (mNewEdit == 1) {
                    url = url + '&orgID=0';
                } else if (mNewEdit == 2) {
                    url = url + '&orgID=' + treeID;
                }
                $.post(url, form.serialize(), function (data) {
                    success:{
                        loadStrukture(partID);
                    }
                });
                $(this).dialog("close");
            }
        }
        },
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]
};

var categories11 = {
    dialog_buttons: [
        {
            text: localeLanguages['134'], click: function () {

            var orgType = $("#categoryType :selected").val();

            if ($("#categorytName").val().trim() == '' ||  orgType == -1 || $("#regCodePart").val().trim() == '') {
                if (localeLanguages['192'] == '1000') {
                    alert('"Ad",Tipi və Kod sahələri doldurulmalıdır.');
                } else if (localeLanguages['192'] == '1001') {
                    alert('"Имя" важное поле.');
                } else if (localeLanguages['192'] == '1002') {
                    alert('"Name" is necessary field.');
                }
                return false;
            } else {
                var form = $("form", this);
                var url = form.attr("action") + '&operType=' + mNewEdit + '&parentID=' + treeID + '&partID=' + partID;
                if (mNewEdit == 1) {
                    url = url + '&orgID=0';
                } else if (mNewEdit == 2) {
                    url = url + '&orgID=' + treeID;
                }
                $.post(url, form.serialize(), function (data) {
                    success:{
                        loadStrukture(partID);
                    }
                });
                $(this).dialog("close");
            }
        }
        },
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]
};
var structure = {
    dialog_buttons: [
        {
            text: localeLanguages['134'], click: function () {

            var orgType = $("#sOrgTypeID :selected").val();

            if ($("#inOrgName").val().trim == '' || $("#inOrgDate").val().trim() == '' || orgType == -1) {
                if (localeLanguages['192'] == '1000') {
                    alert('"Tip","Ad" ,"Yaranma tarixi" sahələri doldurulmalıdır.');
                } else if (localeLanguages['192'] == '1001') {
                    alert('"Тип","Имя" ,"Дата создания" являются важными полями.');
                } else if (localeLanguages['192'] == '1002') {
                    alert('"Type","Name" ,"Date of creation" are necessary fields.');
                }
                return false;
            } else {
                var form = $("form", this);
                var url = form.attr("action") + '&operType=' + mNewEdit + '&parentID=' + treeID + '&partID=' + partID;

                if (mNewEdit == 1) {
                    url = url + '&orgID=0';
                } else if (mNewEdit == 2) {
                    url = url + '&orgID=' + treeID;
                }
                $.post(url, form.serialize(), function (data) {
                    success:{
                        loadStrukture(partID);
                        $("#rightPanelInfo").html("");
                    }
                });
                $(this).dialog("close");
            }
        }
        },
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]
};
var users = {
    grid: main_grid_users,
    dialog_buttons: [
        {
            text: localeLanguages['134'], click: function () {
            var form = $("form", this);
            var res;
            var vals = $("#uName").val().trim();
//------------------ getting selected values of chk ----------------------------
            var chk = -1;
            var usr = -1;
            if (document.getElementById('chActiv').checked) {
                chk = 1;
            } else {
                chk = 0;
            }
            if (document.getElementById('chAdmin').checked) {
                usr = 1;
            } else {
                usr = 0;
            }
            var menuID = 0;
            var chkVal = "";
            var rights = "";
            var element;
            for (var j = 20; j <= 30; j++) {
                for (var i = 0; i <= 100; i++) {
                    element = document.getElementById('ch' + j + i);
                    if (typeof(element) != 'undefined' && element != null) {
                        if (element.checked) {
                            if (j == 21) {
                                menuID = 1007;
                            } else if (j == 22) {
                                menuID = 1001;
                            } else if (j == 23) {
                                menuID = 1006;
                            } else if (j == 24) {
                                menuID = 1008;
                            } else if (j == 25) {
                                menuID = 1009;
                            } else if (j == 26) {
                                menuID = 1011;
                            } else if (j == 27) {
                                menuID = 1010;
                            } else if (j == 28) {
                                menuID = 1012;
                            } else if (j == 29) {
                                menuID = 1002;
                            }else if (j == 30) {
                                menuID = 1017;
                            }
                            chkVal = chkVal + element.value + "*";
                        }
                    }
                }
                if (menuID != 0 && chkVal != "") {
                    rights = rights + menuID + ":" + chkVal + "/";
                }
                menuID = 0;
                chkVal = "";
            }
            if ($("#uName").val().trim() == '' || $("#sEmpName :selected").val() == '-1' ||
                $("#passw").val().trim() == '' || rights == '') {
                alert("Bütün sahələr vacib sahələrdir");
                return false;
            } else {
                if (mNewEdit == 1) {
                    $.ajax({
                        type: 'GET',
                        dataType: 'json',
                        url: 'cs?action=chkUser&uName=' + vals,
                        success: function (data) {
                            $.each(data.dsList, function (i, ds) {
                                res = ds.res;
                            });
                            if (res == 10) {
                                alert("İstifadəçi adının yoxlanılmasında səhv baş vermişdir.");
                                return false;
                            } else if (res == 0) {
                                alert("Seçilmiş istifadəçi adı artıq sistemdə qeydə alınıb. Zəhmət olmasa başqa istifadəçi adı müəyyən edin.");
                                return false;
                            }

                            if (res == 100) {
                                rights = rights + rightssecond;
                                var url = form.attr("action") + "&operType=" + mNewEdit + "&act=" + chk + "&rights=" +
                                    rights + "&usr=" + usr;
                                $.post(url, form.serialize(), function (data) {
                                    success : {
                                        main_grid(model);
                                    }
                                });
                                $("#dlgMenu").dialog("close");
                            }
                        },
                        error: function (data) {
                        },
                        complete: function (jqXHR, textStatus) {
                        }
                    });
                } else if (mNewEdit == 2) {
                    rights = rights + rightssecond;
                    var url = form.attr("action") + "&operType=" + mNewEdit + "&act=" + chk + "&rights=" +
                        rights + "&usr=" + usr;
                    var g = $('#grid');
                    var sel_id = g.jqGrid('getGridParam', 'selrow');
                    var usrID = g.jqGrid('getCell', sel_id, 'id');
                    var PerID = g.jqGrid('getCell', sel_id, 'perid');
                    url = url + '&usID=' + usrID + '&pID=' + PerID;

                    $.post(url, form.serialize(), function (data) {
                        success : {
                            main_grid(model);
                        }
                    });
                    $("#dlgMenu").dialog("close");
                }
            }
        }
        },
        {
            text: localeLanguages['135'], click: function () {
            $(this).dialog("close");
        }
        }
    ]
};
function loadStrukture(part) {
    $("#rightPanelInfo").html('');
    var cc = 0;
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: 'cs?action=loadStrukturList&partID=' + part + '&langID=' + localeLanguages['192'],
        success: function (data) {
            listm = [];
            lists = [];
            $("#mainTree").html('');
            var html = '';
            var beg = '<ul>';
            var end = '</ul>';
            $.each(data.dsList, function (i, ds) {
                listm.push(ds);
                lists.push(ds);
                cc = cc + 1;
            });
            var tt = '';
            var tt1 = '';

            var k = '';
            k = addChild(listm, 0);
            if (k != '') {
                tt = tt + k; }

            var k1;
            k1 = addChild1(listm, 0);
            if (k1 != '') {
                tt1 = tt1 + k1; }
            html = tt;
            html = beg + html + end;
            $("#mainTree").html(html);
            $("#mainTree").treeview({
                persist: "location",
                collapsed: true,
                //unique: true,
                animated: "fast"
            });

            categoryStructure = [tt1];


        },
        error: function (data) {
        },
        complete: function (jqXHR, textStatus) {
            //$("#total").html("Cəmi : "+cc+" struktur vahidi");
        }
    });
}
function combosChange(item) {
    $("#searchFooterTextId").val('');
    main_grid(model);
}
function main_grid(model) {
    var w = 0;
    var h = 0;
    var url1;
    $("#rightPanelInfo").html('');
    var categoryList = 0;
    var groupList = 0;
    var classList = 0;
    var typeList = 0;
    var StatusList = 0;
    var genreList = 0;
    var exampleStatus = "1";
    if ($("#categoryList :selected").val()) {

                categoryList = $("#categoryList :selected").val();
            if ($("#groupList :selected").val()) {
                groupList = $("#groupList :selected").val();
            }
            if ($("#classList :selected").val()) {
                classList = $("#classList :selected").val();
            }
            if ($("#typeList :selected").val()) {
                typeList = $("#typeList :selected").val();
            }
            if ($("#genreList :selected").val()) {
                genreList = $("#genreList :selected").val();
            }
    }
    if ($("#statusList :selected").val()) {
        StatusList = $("#statusList :selected").val();
    }

    var w = grid_dimension().width;
    var h = grid_dimension().height;
    function grid_dimension() {
        var dimension = {};
        dimension.width = $('.center-area').width() - 4;
        dimension.height = $('.center-area').height() - 56;
        return dimension;
    }

    $(window).resize(function () {
        var g = $('#grid');
        g.setGridWidth(grid_dimension().width);
        g.setGridHeight(grid_dimension().height);
    });

    if (partID == 1008) {
        exampleStatus = "1025,1039";
    }
    else if (partID == 1009) {
        exampleStatus = "1018";
    }
    else if (partID == 1011) {
        exampleStatus = "1019";
    }

       url1  = model.grid.url;
        if (url1 != '') {
            url1 = url1 + '&groupList=' + groupList + '&classList=' + classList + '&categoryList=' + categoryList + '&statusID=' + exampleStatus +
            '&mStatusList=' + StatusList + '&typeList=' + typeList + '&genreList=' + genreList + '&lang_id=' + localeLanguages['192'];
        }

    $("#grid").jqGrid('GridUnload');
    jQuery("#grid").jqGrid({
        url: url1,
        datatype: "json",
        colNames: model.grid.col_names,
        colModel: model.grid.colModels,
        rowNum: 20,
        rowList: [20, 40, 50, 100, 200],
        pager: '#pager',
        sortname: 'id',
        sortable: true,
        sortorder: "asc",
        viewrecords: true,
        gridview: true,
        scrollOffset: 0,
        close: function(){
            $(this).close();
        },
        jsonReader: {
            repeatitems: false,
            root: "rows",
            page: "page",
            total: "total",
            records: "records"
        },
        height: h,
        width: w,
        onSelectRow: function (id) {
            var g = $('#grid');
            var gridSelid = g.jqGrid('getGridParam', 'selrow');
            selectedRowId = g.jqGrid('getCell', gridSelid, 'id');
            gridRealID = g.jqGrid('getCell', gridSelid, 'id');
            loadRightPanelInfo();
        },
        ondblClickRow: function (id) {
            if (partID != 1009 && partID != 1011) {
                $("#btn_editMain").click();
            }else if (partID == 1009 || partID == 1011) {
                if ($('#btn_viewMain_div').css('display') != 'none') {
                    $("#btn_viewMain").click();
                } else if($('#btn_editMain_div').css('display') != 'none') {
                    $("#btn_editMain").click();
                }
            }
        },
        gridComplete: function () {
            if (mNewEdit == 2) {
                $("#grid").setSelection(selectedRowId, true);
            } else {
                var top_rowid = $('#grid' + ' tr:nth-child(2)').attr('id');
                $("#grid").setSelection(top_rowid, true);
            }
            var sum = parseInt($('#grid').getGridParam('records'));
            $("#total").html(localeLanguages['211'] + " : " + sum + ' ' + menuTitle);
        },
        loadComplete: function () {
            $.each($("#grid").getDataIDs(),
                function (index, key) {
                    if (index % 2 !== 0) {
                        var t = $("#" + key, $("#grid"));
                    }
                }
            );
            $("#div_block").hide();

        },

        loadBeforeSend: function () {
            topPosition = ($(window).height() - 20) / 2;
            $("#div_block>div").css({'top': topPosition + 'px'});
            $("#div_block").show();

        }
    });

}
function buttons_click() {
    $('.buttons_container button:not(#btn_editMain,#btn_viewMain,#btn_pdfMain,#btn_dictionaryMain,#btn_passport,#btn_delMain,#btn_advsearchMain)').click(function () {
        mNewEdit = 1;
        pothdoc = [];
        ExampleOperation = [];
        var qrPic = [];
        media = [];
        library = [];
        referenceFile = [];
        documents = [];
        trainings_2 = [];
        person = [];
        test = [];
        testView = [];
        examRegistration = [];
        examEvaluation = [];
        questionsAnswer = [];
        employee = [];
        examplesMass = [];
        employeeListExam = [];
        examInfoList = [];
        planguageInfo = [];
        pcontact = [];
        peducation = [];
        eexam = [];
        etraining = [];
        question = [];
        correctAnswer = 0;
        load_dialog();
        window.buttonName = $(this).attr('id');
        window.buttonName = buttonName.slice(4);
        var partName = $('.selected_link').attr('id');
        var dialogAttr = partName + '_' + window.buttonName + '_dialog';

        window.gridName = partName + '_' + window.buttonName + '_grid';
        window.pagerName = partName + '_' + window.buttonName + '_pager';
        var load_jsp_name;
        if (partName == 'mOrganization' || partName == 'carriers') {
            load_jsp_name = 'modals/' + 'carriers_' + window.buttonName + '.jsp'
        } else if (partName == 'approvedExamples' || partName == 'unapprovedExamples') {
            partName = 'examples';
            load_jsp_name = 'modals/' + partName + '_' + window.buttonName + '.jsp';

        } else {
            load_jsp_name = 'modals/' + partName + '_' + window.buttonName + '.jsp';
        }


        $("#dlgMenu").load(load_jsp_name, function () {
            loadMainDialogHeaderInfo(partID, function () {
                $('#dlgMenu').parent().removeAttr('name').attr('name', dialogAttr);
                $("#dlgMenu").dialog("open");
            });
        });
    });

    $('#btn_editMain').click(function () {
        if ($('#btn_editMain_div').css('display') != 'none' || partID == 1017) {
            mNewEdit = 2;
            correctAnswer = 0;
            var g = $("#grid");
            var selID = g.jqGrid('getGridParam', 'selrow');
            var realId;
            var dic_thema_id;
            var url;
            if (partID == 1012) {
                realId = g.jqGrid('getCell', selID, 'id');
            } else if (partID == 1002) {
                realId = g.jqGrid('getCell', selID, 'id');

            } else {
                realId = g.jqGrid('getCell', selID, 'id');
                dic_thema_id = g.jqGrid('getCell', selID, 'dic_thema_id');
            }

            if (partID == 1001) {
                var perID = g.jqGrid('getCell', selID, 'perid');
                url = 'cs?action=getEmployeeInfo&realId=' + realId + '&perID=' + perID;
            } else if (partID == 1006 || partID == 1010) {
                realId = treeID;
                url = 'cs?action=getStrukturFullInfo&treeID=' + treeID + '&partID=' + partID;
            }
            else if (partID == 1007) {
                url = 'login?action=loadAccessInfo&st=' + realId
            } else if (partID == 1012) {
                url = 'cs?action=getCarriersInfo&realId=' + realId + '&partID=' + partID;
            } else if (partID == 1002) {
                url = 'cs?action=getCarriersInfo&realId=' + realId + '&partID=' + partID;
            } else if (partID == 1008) {
                url = 'cs?action=getExamplesInfo&realId=' + realId + '&langID=' + localeLanguages['192'];
            } else if (partID == 1009) {
                url = 'cs?action=getExamplesInfo&realId=' + realId + '&langID=' + localeLanguages['192'];
            } else if (partID == 1011) {
                url = 'cs?action=getExamplesInfo&realId=' + realId + '&langID=' + localeLanguages['192'];
            } else if (partID == 1017) {
                url = 'cs?action=getExamplesInfo&realId=' + realId + '&langID=' + localeLanguages['192'];
            }

            if (realId != false) {
                load_dialog();
                window.buttonName = $(this).attr('id');
                window.buttonName = buttonName.slice(4);
                var partName = $('.selected_link').attr('id');

                var load_jsp_name;
                window.gridName = partName + '_newMain' + '_grid';
                window.pagerName = partName + '_newMain' + '_pager';
                dialogAttr = partName + '_' + 'newMain_dialog';

                if (partName == 'mOrganization' || partName == 'carriers') {
                    load_jsp_name = 'modals/' + 'carriers' + '_newMain' + '.jsp';
                } else if (partName == 'approvedExamples' || partName == 'unapprovedExamples' || partName == 'advancedSearch') {

                    partName = 'examples';
                    window.gridName = partName + '_newMain' + '_grid';
                    window.pagerName = partName + '_newMain' + '_pager';
                    load_jsp_name = 'modals/' + partName + '_newMain' + '.jsp';
                } else {
                    load_jsp_name = 'modals/' + partName + '_newMain' + '.jsp';
                }
                loadMainInfo(url, function () {
                    $("#dlgMenu").load(load_jsp_name, function () {
                        loadMainDialogHeaderInfo(partID, function () {
                        });
                        $('#dlgMenu').parent().removeAttr('name').attr('name', dialogAttr);
                        $("#dlgMenu").dialog("open");

                    });
                });
            }
        }
    });
    $('#btn_viewMain').click(function () {
        if ($('#btn_viewMain_div').css('display') != 'none') {
            mNewEdit = 2;
            var g = $("#grid");
            var selID = g.jqGrid('getGridParam', 'selrow');
            var realId;
            var url;
            realId = g.jqGrid('getCell', selID, 'id');
            if (partID == 1009) {
                url = 'cs?action=getExamplesInfo&realId=' + realId + '&langID=' + localeLanguages['192'];
            } else if (partID == 1011) {
                url = 'cs?action=getExamplesInfo&realId=' + realId + '&langID=' + localeLanguages['192'];
            }
            if (realId != false) {
                load_dialog();
                var load_jsp_name;
                window.buttonName = $(this).attr('id');
                window.buttonName = buttonName.slice(4);
                var partName = $('.selected_link').attr('id');
                dialogAttr = partName + '_' + 'newMain_dialog';
                if (partName == 'mOrganization' || partName == 'carriers') {
                    load_jsp_name = 'modals/' + 'carriers' + '_newMain' + '.jsp';
                } else if (partName == 'approvedExamples' || partName == 'unapprovedExamples' || partName == 'advancedSearch') {
                    partName = 'examples';
                    load_jsp_name = 'modals/' + partName + '_newMain' + '.jsp';
                } else {
                    load_jsp_name = 'modals/' + partName + '_newMain' + '.jsp';
                }
                window.gridName = partName + '_newMain' + '_grid';
                window.pagerName = partName + '_newMain' + '_pager';

                loadMainInfo(url, function () {
                    $("#dlgMenu").load(load_jsp_name, function () {
                        $('#dlgMenu').parent().removeAttr('name').attr('name', dialogAttr);
                        $("#dlgMenu").dialog("open");
                        loadMainDialogHeaderInfo(partID, function () {
                        });
                    });
                });
            }
        }
    });
    $("#btn_passport").click(function () {
        var g = $('#grid');
        var sel_id = g.jqGrid('getGridParam', 'selrow');
        var realId = g.jqGrid('getCell', sel_id, 'id');
        var langid = localeLanguages['192'];
        reportPassport(realId, langid, 'pdf');

    });
    $("#btn_pdfMain").click(function () {
        if(partID == 1010) {
            var json;
            $.ajax({
                type: 'GET',
                dataType: 'json',
                url: 'cs?action=loadStrukturList&partID=' + 1010 + '&langID=' + 1000,
                success: function (data) {
                    listm = [];
                    lists = [];
                    $("#mainTree").html('');
                    $.each(data.dsList, function (i, ds) {
                        listm.push(ds);
                        lists.push(ds);
                    });
                    var tt = '';
                    var tt1 = '';


                    var k1;
                    k1 = addChild1(listm, 0);
                    if (k1 != '') {
                        tt1 = tt1 + k1; }
                    json = [tt1];
                    $.ajax({
                        url:'cs1?action=setSession&typ=1&newEdit=' + 0 + '&partID=' + 11111+'&docTypeID='+1,
                        type:"POST",
                        dataType:'json',
                        data: {json:json},
                        success:function(data) {

                        }
                    });
                 //
                },
                error: function (data) {
                },
                complete: function (jqXHR, textStatus) {
                    //$("#total").html("Cəmi : "+cc+" struktur vahidi");
                }
            });
            FullMundericatReport(1, '', 'pdf');
            $("#category").click();
        } else {
            var categoryList = -1;
            var groupList = -1;
            var classList = -1;
            var typeList = -1;
            var StatusList = -1;
            var genreList = -1;
            var gridType = 0;
            var StatusList = 0;
            var exampleStatus = "1";
            if ($("#categoryList :selected").val()) {
                categoryList = $("#categoryList :selected").val();
            }
            if ($("#groupList :selected").val()) {
                groupList = $("#groupList :selected").val();
            }
            if ($("#classList :selected").val()) {
                classList = $("#classList :selected").val();
            }
            if ($("#typeList :selected").val()) {
                typeList = $("#typeList :selected").val();
            }
            if ($("#genreList :selected").val()) {
                genreList = $("#genreList :selected").val();
            }
            if ($("#statusList :selected").val()) {
                StatusList = $("#statusList :selected").val();
            }
            var langId = localeLanguages['192'];
            var pg = $("#grid").getGridParam('page');
            var rw = $("#grid").getGridParam('rowNum');
            var gSort = $("#grid").getGridParam('sortorder');
            var gSortI = $("#grid").getGridParam('sortname');

            if (partID == 1008) {
                exampleStatus = "1025,1039";
                gridType = 4;
            } else if (partID == 1011) {
                exampleStatus = "1019";
                gridType = 4;
            } else if (partID == 1009) {
                exampleStatus = "1018";
                gridType = 4;
            } else if (partID == 1012) {
                gridType = 2;
            } else if (partID == 1002) {
                gridType = 3;
            } else if (partID == 1001) {
                gridType = 1;
            } else if (partID == 1007) {
                gridType = 7;
            } else if (partID == 1017) {
                gridType = 8;
            }
            var url = '';
            url = url + '&pNumber=' + pg + '&pSize=' + rw + '&gSort=' + gSort + '&gSortI=' + gSortI + '&newEdit=' + mNewEdit;
            url = url + '&categoryList=' + categoryList + '&groupList=' + groupList + '&classList=' + classList
            + '&typeList=' + typeList + '&genreList=' + genreList + '&mStatusList=' + StatusList + '&lang_id=' + langId + '&statusID=' + exampleStatus;
            var g = $('#grid');
            var sel_id = g.jqGrid('getGridParam', 'selrow');
            var realId = g.jqGrid('getCell', sel_id, 'id');
            reportMainGrid(gridType, url, 'pdf');
        }

    });

    $('#btn_dictionaryMain,#btn_advsearchMain').click(function () {
        employeeListExam = [];
        load_dialog();
        window.buttonName = $(this).attr('id');
        window.buttonName = buttonName.slice(4);
        window.gridName = window.buttonName + '_grid';
        window.pagerName = window.buttonName + '_pager';

        var load_jsp_name = 'modals/' + window.buttonName + '.jsp';
        $("#" + window.buttonName).load(load_jsp_name, function () {
            $("#" + window.buttonName).dialog("open");
        });
    });
    $('#btn_delMain').click(function () {
        mNewEdit = 3;
        load_dialog();
        var load_jsp_name = 'modals/delInfo.jsp';
        $("#delMain").load(load_jsp_name, function () {
            $("#delMain").dialog("open");
        });
    });

    $("#searchSubmit").button().click(function () {
        var tmpModel = model;
        tmpModel.grid.url = "cs?action=intSearch" + setGType();
        var form = $("#frm22");
        var url = form.attr("action");
        $.post(url, form.serialize(), function (data) {
            success : {
                main_grid(tmpModel);
            }
        });
    });

}
function load_dialog() {
    var buttons = model.dialog_buttons;
    var g = $("#grid");
    var selID = g.jqGrid('getGridParam', 'selrow');
    viewMode = g.jqGrid('getCell', selID, 'view_mode');
    if (viewMode == 1 && mNewEdit == 2) {
        buttons = {
            'İmtina et': function () {
                $(this).dialog("close");
            }
        };

    }
    $("#dlgMenu").html(' ');
    $("#delMain").html(' ');
    $("#advsearchMain").html(' ');
    $("#dictionaryMain").html(' ');
    $(".ui-dialog-content").html(' ');
    var w = $('.container_popup').width() + '10px';

    $("#dlgMenu").dialog({
        title: window.title,
        resizable: false,
        autoOpen: false,
        minHeight: 200,
        maxHeight: 620,
        maxWidth: 1063,
        width: w,
        position: { my: "center", at: "center", of: window },

        close: function (event, ui) {
            $("div[data-type=secondTabDialog]").remove();
            $("#dlgMenu").html('');
            $('.ui-dialog').remove();
            $('#ExamplesDelete_dialog').remove();
        },
        modal: true,
        buttons: buttons
    });
    $("#delMain").dialog({
        title: localeLanguages['111'],
        resizable: false,
        autoOpen: false,
        minHeight: 200,
        maxHeight: 620,
        width: 400,
        modal: true,
        position: { my: "center", at: "center", of: window },
        close: function (event, ui) {
            $("div[data-type=secondTabDialog]").remove();
            $("#delMain").html('');
            $('.ui-dialog').remove();
        },
        buttons: {
            'Tətbiq et': function () {
                var form = $("form", this);
                var g = $('#grid');
                var sel_id = g.jqGrid('getGridParam', 'selrow');
                var realId = g.jqGrid('getCell', sel_id, 'id');
                var organid = g.jqGrid('getCell', sel_id, 'id');
                var carryid = g.jqGrid('getCell', sel_id, 'id');
                var url = 'cs1?action=deleteData&partID=' + partID;
                if (partID == 1002) {
                    url = url + '&realID=' + organid;
                } else if (partID == 1004) {
                    url = url + '&realID=' + realId;
                } else if (partID == 1008) {
                    url = url + '&realID=' + realId;
                } else if (partID == 1009) {
                    url = url + '&realID=' + realId;
                } else if (partID == 1011) {
                    url = url + '&realID=' + realId;
                } else if (partID == 1012) {
                    url = url + '&realID=' + carryid;
                } else if (partID == 1001) {
                    url = url + '&realID=' + realId;
                } else if (partID == 1006) {
                    url = url + '&realID=' + treeID;
                } else if (partID == 1010) {
                    url = url + '&realID=' + treeID;
                } else if (partID == 1007) {
                    url = url + '&realID=' + realId;
                }
                $.post(
                    url,
                    function (data) {
                        success:{
                            if (partID != 1006 && partID != 1010) {
                                main_grid(model)
                            } else {
                                loadStrukture(partID)
                            }
                        }
                    });
                $(this).dialog("close");
            },
            'İmtina et': function () {
                $(this).dialog("close");
            }
        }
    });

    $("#dictionaryMain").dialog({
        title: 'Soraqça',
        resizable: false,
        autoOpen: false,
        minHeight: 200,
        maxHeight: 620,
        maxWidth: 1063,
        width: 700,
        position: { my: "center", at: "center", of: window },
        modal: true,
        buttons: {
            'Bağla': function () {
                $(this).dialog("close");
            }
        }
    });
    $("#advsearchMain").dialog({
        title: localeLanguages['178'],
        resizable: false,
        autoOpen: false,
        minHeight: 200,
        maxHeight: 620,
        maxWidth: 1063,
        width: 700,
        position: { my: "center", at: "center", of: window },
        modal: true,
        close: function (event, ui) {
            $("#dialog-search_new").parent(".ui-dialog").remove();
            $("#dialog-search_del").parent(".ui-dialog").remove();
        },
        buttons: {
            "İmtina et": function () {
                $(this).dialog("close");
                // $("#dialog-search_new").parent(".ui-dialog").remove();
            },
            "Tətbiq et": function () {
                main_grid(model);
                $(this).dialog("close");
                //  $("#dialog-search_new").parent(".ui-dialog").remove();
            }
        }
    });

}
function block_show_hide() {
    $('.filter-area').show();
    $('.grid-area').show();
    $('#mainTree').hide();
    if (menu == "structure" || menu == "category") {
        $('.filter-area').hide();
        $('.grid-area').hide();
        $('.sidebar2').hide();
        $('#mainTree').show();
    }
    else if (menu == "users" || menu == "carriers" || menu == 'advancedSearch' || menu == 'mOrganization') {
        $('.filter-area').hide();
        $('.sidebar2').show();
    } else {
        $('.filter-area').show();
        $('#mainTree').hide();
        $('.sidebar2').show();
    }
}
function button_show_hide() {
    $('.button_block').show();
    if (menu == "employees" || menu == "users") {
        $('#btn_viewMain').parent().hide();
    } else if (menu == 'approvedExamples' || menu == 'unapprovedExamples') {
        $('#btn_newMain').parent().hide();
        $('#btn_editMain').parent().hide();
        $('#btn_delMain').parent().hide();
        $('#btn_viewMain').parent().hide();
        if ($('#btn_viewMain_div').css('display') != 'none') {
            $("#viewApprove").hide();
            $("#viewUnApprove").hide();
        }
    } else if (menu == "structure") {
        $('#btn_viewMain').parent().hide();
        $('#btn_dictionaryMain').parent().hide();
    } else if (menu == "carriers") {
        $('#btn_newMain').parent().show();
        $('#btn_editMain').parent().show();
        $('#btn_viewMain').parent().hide();
    } else if (menu == 'approvedExamples') {
        $('#btn_passport').parent().show();
    } else {
        $('.button_block').show();
    }
}
function total_show_hide() {

    if (menu == 'structure' || menu == 'category') {
        $("#total").parent().hide();
    }
    else {

        $("#total").parent().show();
    }
}
function filter_show_hide() {

    $('.filter-area select').parent().hide();
    $('.filter-area select').parent().prev().hide();
    if (menu == 'employees') {
        $('#statusList').parent().show();
        $('#statusList').parent().prev().show();
    }
    else if (menu == 'examples') {
        $('#categoryList').parent().show();
        $('#categoryList').parent().prev().show();

        $('#groupList').parent().show();
        $('#groupList').parent().prev().show();

        $('#classList').parent().show();
        $('#classList').parent().prev().show();

        $('#typeList').parent().show();
        $('#typeList').parent().prev().show();

        $('#genreList').parent().show();
        $('#genreList').parent().prev().show();
    }
    else if (menu == 'approvedExamples' || menu == 'unapprovedExamples') {
        $('#categoryList').parent().show();
        $('#categoryList').parent().prev().show();
        $('#groupList').parent().show();
        $('#groupList').parent().prev().show();

        $('#classList').parent().show();
        $('#classList').parent().prev().show();

        $('#typeList').parent().show();
        $('#typeList').parent().prev().show();

        $('#genreList').parent().show();
        $('#genreList').parent().prev().show();
    } else {
        $('.filter-area select').parent().hide();
        $('.filter-area select').parent().prev().hide();

    }
}

function design() {
    $('.center-area').removeClass('center-area3');
    $('.center-area').removeClass('center-area2');
    var contentInnerHeight = $('.filter-area').height();
    contentInnerHeight = +contentInnerHeight;

    if (contentInnerHeight > 30) {
        $('.center-area').addClass('center-area2')
    }
    else {
        $('.center-area').removeClass('center-area2')
    }
    var displayFilterArea = $('.filter-area').css('display');

    if (displayFilterArea == 'none') {
        $('.center-area').addClass('center-area3');
    }
    else {
        $('.center-area').removeClass('center-area3')
    }
}

function checkButtons(btL) {
//    debugger;
    var temp = '';
    var element;
    var mID = 0;


    for (var n = 0; n < btL.length; n++) {
        var menu = btL[n].menuID;
        var btn = btL[n].buttonID;
        var temp = '';


        btn = btn.substr(1, btn.length - 2) + ',';

        if (menu == 'employees') {
            mID = 22;
        } else if (menu == 'structure') {
            mID = 23;
        } else if (menu == 'users') {
            mID = 21;
        } else if (menu == 'examples') {
            mID = 24;
        } else if (menu == 'category') {
            mID = 27;
        } else if (menu == 'approvedExamples') {
            mID = 25;
        } else if (menu == 'unapprovedExamples') {
            mID = 26;
        } else if (menu == 'carriers') {
            mID = 28;
        } else if (menu == 'mOrganization') {
            mID = 29;
        }else if (menu == 'advancedSearch') {
            mID = 30;
        }
        for (var i = 0; i < btn.length; i++) {
            if (btn[i] != ',') {
                temp = temp + btn[i];
            } else {
                for (k = 0; k <= 100; k++) {
                    element = document.getElementById("ch" + mID + k);
                    if (typeof(element) != 'undefined' && element != null) {

                        if (element.value == temp.trim()) {

                            element.checked = true;
                        }
                    }
                }
                temp = '';
            }
        }
    }
}

function loadAccessInfo(st) {
    var menu = '';
    var k = '';
    $.ajax({
        type: 'GET',
        dataType: 'json',
        url: 'login?action=loadAccessInfo&st=' + st,
        success: function (data) {
            $.each(data.dsList, function (i, ds) {


                if (ds.levelID == 1) {
                    if (ds.menuID == 'examples') {
                        k = ds.menuID;
                    }
                    if (menu == '') {
                        menu = ds.menuID;

                        menuList1.push({menuID: menu});
                        for (var j = 0; j < menuList1.length; j++) {

                        }
                    } else if (menu != ds.menuID) {

                        menu = ds.menuID;
                        menuList1.push({menuID: menu});
                    }
                    buttonList1.push({menuID: menu, buttonID: ds.buttonID});
                    for (var j = 0; j < buttonList1.length; j++) {

                    }
                } else if (ds.levelID == 2) {
                    if (menu == '') {
                        menu = ds.menuID;
                        menuList2.push({menuID: menu});
                    } else if (menu != ds.menuID) {
                        menu = ds.menuID;
                        menuList2.push({menuID: menu});
                    }
                    buttonList2.push({menuID: menu, buttonID: ds.buttonID});
                }
            });
            showHideMenuList(menuList1, k);
        },
        error: function (data) {
        },
        complete: function (jqXHR, textStatus) {
        }
    });
}
function showHideMenuList(menuL, k) {
    $("#examples").hide();
    $("#approvedExamples").hide();
    $("#unapprovedExamples").hide();
    $("#category").hide();
    $("#carriers").hide();
    $("#mOrganization").hide();
    $("#structure").hide();
    $("#employees").hide();
    $("#users").hide();
    $("#advancedSearch").hide();
    for (var n = 0; n < menuL.length; n++) {

        var menu = menuL[n].menuID;
        $("#" + menu).show();
    }
    if (k == 'examples') {
        $("#" + k).click();
    }
    else {
        visibleFirstClick();
        setTimeout(visibleFirstClick, 50);
    }


}
function visibleFirstClick() {
    $('.nav li a:visible').first().click();
}
function showHideButtonList(buttonL, menuID, tip) {
    if (tip == 1) {
        $("#btn_newMain_div").hide();
        $("#btn_editMain_div").hide();
        $("#btn_delMain_div").hide();
        $("#btn_viewMain_div").hide();
        $("#btn_pdfAdmin_div").hide();
        $("#btn_advsearchMain_div").hide();
        $("#btn_pdfMain_div").hide();
        $("#btn_dictionaryMain_div").hide();
        $("#btn_passport_div").hide();
    }
    for (var n = 0; n < buttonL.length; n++) {
        var menu = buttonL[n].menuID;
        var btn = buttonL[n].buttonID;
        var temp = '';

        if (menu == menuID) {
            btn = btn.substr(1, btn.length - 2) + ',';
            for (var i = 0; i < btn.length; i++) {
                if (btn[i] != ',') {
                    temp = temp + btn[i];
                } else {
                    if (tip == 1) {
                        $("#" + temp.trim() + "_div").show();
                    }
                    temp = '';
                }
            }
        }

    }

}

$(document).ready(function () {
    loadAccessInfo(0);
    $('#searchFooterTextId').attr("placeholder", localeLanguages['190']);
    $('.nav li a').click(function () {
        $(this).parents('.nav').find('a').removeClass('selected_link');
        $(this).addClass('selected_link');
        $("#searchFooterTextId").val('');
        window.title = $(this).text();
        $('#title').text(title);
        menu = $(this).attr('id');
        mNewEdit = 0;
        switch (menu) {
            case 'examples':
                deleteSessionParams();
                deleteAllRecordByFileNameADV(examples);
                model = examples;
                partID = 1008;
                menuTitle = localeLanguages['220'];
                break;
            case 'approvedExamples':
                deleteSessionParams();
                deleteAllRecordByFileNameADV(approvedExamples);
                model = approvedExamples;
                partID = 1009;
                menuTitle = localeLanguages['221'];
                break;
            case 'unapprovedExamples':
                deleteSessionParams();
                deleteAllRecordByFileNameADV(unapprovedExamples);
                model = unapprovedExamples;
                partID = 1011;
                menuTitle = localeLanguages['222'];
                break;

            case 'carriers':
                deleteSessionParams();
                deleteAllRecordByFileNameADV(carriers);
                model = carriers;
                partID = 1012;
                menuTitle = localeLanguages['105'];
                break;

            case 'category':
                deleteSessionParams();
                deleteAllRecordByFileNameADV(categories11);
                model = categories11;
                partID = 1010;
                menuTitle = localeLanguages['223'];
                break;
            case 'employees':
                deleteSessionParams();
                deleteAllRecordByFileNameADV(employees);
                model = employees;
                partID = 1001;
                menuTitle = localeLanguages['224'];
                break;

            case 'mOrganization':
                deleteSessionParams();
                deleteAllRecordByFileNameADV(organization);
                model = organization;
                partID = 1002;
                menuTitle = localeLanguages['199'];
                break;

            case 'structure':
                deleteSessionParams();
                deleteAllRecordByFileNameADV(structure);
                model = structure;
                partID = 1006;

                break;
            case 'users':
                deleteSessionParams();
                deleteAllRecordByFileNameADV(users);
                model = users;
                partID = 1007;
                menuTitle = localeLanguages['225'];
                break;

            case 'advancedSearch':
                deleteSessionParams();
                deleteAllRecordByFileNameADV(advSearch1);
                model = advancedSearch;
                partID = 1017;
                menuTitle = localeLanguages['178'];
                break;

        }

        filter_show_hide();
        block_show_hide();
        button_show_hide();
        showHideButtonList(buttonList1, $(this).attr('id'), 1);
        design();
        total_show_hide();

        if (model == employees) {
            $('#statusList').getComboContent(4,'', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                $('#statusList').change();
            }, 2);
        } else if (model == examples || model == unapprovedExamples || model == approvedExamples) {
            $('#categoryList').getComboContent(1012, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                $('#categoryList').change();
            }, 7);
        }
        else {
            if (model != structure && model != categories11) {
             main_grid(model);
            }else {
               loadStrukture(partID);

            }
        }

    });
   // $('.nav ul:nth-child(1) li:first-child a').click();
    buttons_click();

});
function load_dialog_status(tip) {
    $("#yekun_dialog").dialog({
        title: localeLanguages['215'],
        resizable: false,
        autoOpen: false,
        minHeight: 200,
        maxHeight: 620,
        position: { my: "center", at: "center", of: window },
        maxWidth: 1063,
        width: 700,
        modal: true,
        close:function(event,ui){
            $(this).html('');
            $(this).parent().remove();
        },
        buttons: [
            {
                text: localeLanguages['134'],
                click: function () {
                    mNewEdit = 0;
                    var g = $('#grid');
                    var sel_id = g.jqGrid('getGridParam', 'selrow');
                    var exmpID22 = g.jqGrid('getCell', sel_id, 'id');

                    var form = $("form", "#yekun_dialog");
                    var url = form.attr("action") + '&partID=' + partID+'&exmpID22='+exmpID22;
                    var status = $("#status :selected").val();
                    var reson = $("#reson").val();
                    if (status != -1) {
                        if (partID == 1008) {
                            $.post(url, form.serialize(), function (data) {
                                success : {
                                    main_grid(model);
                                }
                            });
                            $(this).dialog("close");
                            $("#dlgMenu").dialog("close");
                        } else {
                            if (reson != '') {
                                $.post(url, form.serialize(), function (data) {
                                    success : {
                                        main_grid(model);
                                    }
                                });
                                $(this).dialog("close");
                                $("#dlgMenu").dialog("close");
                            } else {
                                alert("sebeb mutleqdir!");
                            }

                        }

                    } else {
                        alert("Status sahesi mutleqdir!!");
                    }


                }

            },
            {
                text: localeLanguages['135'], click: function () {
                $(this).dialog("close");
            }
            }
        ]


    });
}

function deleteAllRecordByFileNameADV(myArr) {
    var index = null;
    for (var i = 0; i < myArr.length; i++) {

        index = i;
        break;

    }
    if (index !== null) {
        myArr.splice(index, 1);
    }
}

function deleteSessionParams() {
    $.ajax({
        type: "post",
        url: "cs?action=deleteSessionParams1",
        success: function () {
        }
    })
}
