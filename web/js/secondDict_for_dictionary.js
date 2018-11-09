var clickedBtn;
var clickedBtnDel;
var getStrBtnDel;
var clickedBtnDelSlice;
var dlgNameDel;
var newEditmp;
var currID;
var phType = 0;
$(document).ready(function () {
    function buttons_click_dic() {
        $('.tab_container button').not('button[ data-name=del]').click(function () {
            var clickedBtn = $(this).data('name');

            var selectedMenu = $('.nav_tab .selected_link').attr('id');
            newEditmp = $(this).attr('attr_dic_newedit');
            var selectedMenuTitle = $('.nav_tab .selected_link').text();
            var popupName = selectedMenu + '_' + clickedBtn;
            console.log(newEditmp + " =newEditmp //////mpPartID1 = " + mpPartID1);
            var load_jsp_name2 = 'modals/' + popupName + '.jsp';

            function load_dialog_2() {
                $("#" + popupName).html(' ');
                var w = 400;
                var h = 620;
                mpPartID1 = dicPartID;
                if (popupName == 'positionDictionary_edit' || popupName == 'positionDictionary_new') {
                    mpPartID1 = dicPartID;
                    load_jsp_name2 = 'modals/positionDictionary_new.jsp';
                    popupName = 'positionDictionary_new';
                } else {
                    mpPartID1 = dicPartID;
                    load_jsp_name2 = 'modals/defaultDictionary_new.jsp';
                    popupName = 'defaultDictionary_new';
                }
                $("#" + popupName).dialog({
                    title: selectedMenuTitle,
                    resizable: false,
                    autoOpen: false,
                    minHeight: 200,
                    maxHeight: h,
                    maxWidth: 1063,
                    width: w,
                    minWidth: 400,
                    modal: true,
                    buttons: {
                        'Tətbiq et': function () {
                            var g = $('#dictionaryMain_grid');
                            var sel_id = g.jqGrid('getGridParam', 'selrow');
                            var realID = g.jqGrid('getCell', sel_id, 'id');
                            var action;

                            var _nameAZ = $("#_nameAZ").val();
                            var _nameRU = $("#_nameRU").val();
                            var _nameEN = $("#_nameEN").val();
                            var _descriptionAZ = $("#_descriptionAZ").val();
                            var _descriptionRU = $("#_descriptionRU").val();
                            var _descriptionEN = $("#_descriptionEN").val();

                            var position = $('#dic_position').val();
                            var organization = $('#dic_organization').val();

                            if (dicPartID != 10014) {
                                var url1 = 'cs?action=checkRecordCountInDIC&dicPartID=' + dicPartID;
                                var action1 = '&_nameAZ=' + _nameAZ.trim();
                                var res = -1;
                                var res1;
                                if ($("#_nameAZ").val().trim() != '') {
                                    $.ajax({
                                        type: 'POST',
                                        dataType: 'json',
                                        data: action1,
                                        url: url1,
                                        success: function (data) {
                                            $.each(data.dsList, function (i, ds) {
                                                res = ds.count;
                                                if (res == 0) {
                                                    action = '&_nameAZ=' + _nameAZ + '&_nameRU=' + _nameRU + '&_nameEN=' + _nameEN +
                                                    '&_descriptionAZ=' + _descriptionAZ + '&_descriptionRU=' + _descriptionRU + '&_descriptionEN=' + _descriptionEN;
                                                    var url = 'cs1?action=saveSubjectDictionary&dicPartID=' + dicPartID + '&newEditmp='
                                                        + newEditmp + '&realID=' + realID;
                                                    $.ajax({
                                                        type: 'POST',
                                                        data: action,
                                                        url: url,
                                                        success: function (data) {
                                                            tabs_grid_dic();
                                                        }
                                                    });

                                                } else {
                                                    alert("Bu yazı artıq daxil edilmişdir!");
                                                }
                                            });
                                        }
                                    });
                                    $(this).dialog("close");
                                } else {
                                    alert("Ad sahəsi mütləq sahədir!!!!!");
                                }
                            } else {
                                if (position == -1 || organization == -1) {
                                    alert("Müəssisə , Vəzifə sahələri mütləq sahələrdir.");
                                } else {
                                    action = '&_nameAZ=' + position + '&_descriptionAZ=' + organization;
                                    var url = 'cs1?action=saveSubjectDictionary&dicPartID=' + dicPartID + '&newEditmp=' + newEditmp + '&realID=' + realID;
                                    var url1 = 'cs?action=checkRecordCountInDIC&dicPartID=' + dicPartID+action;
                                    $.ajax({
                                        type: 'POST',
                                        dataType: 'json',
                                        url: url1,
                                        success: function (data) {
                                            $.each(data.dsList, function (i, ds) {
                                                res = ds.count;
                                                if (res == 0) {
                                                    $.ajax({
                                                        type: 'POST',
                                                        data: action,
                                                        url: url,
                                                        success: function (data) {
                                                            tabs_grid_dic();
                                                        }
                                                    });

                                                } else {
                                                    alert("Bu yazı artıq daxil edilmişdir!");
                                                }
                                            });
                                        }
                                    });
                                    $(this).dialog("close");
                                }
                            }
                        },
                        'İmtina et': function () {
                            $(this).dialog("close");
                        }
                    }
                });
            }

            load_dialog_2();
            $("#" + popupName).load(load_jsp_name2, function () {
                loadSecondDialogHeaderInfo_2(mpPartID1, function () {
                    $("#" + popupName).dialog("open");
                    return false;
                });

            });
        });
        $('button[data-name=del]').click(function () {
            newEditmp = 3;
            clickedBtnDel = $(this).attr('id');
            getStrBtnDel = clickedBtnDel.indexOf('_') + 1;
            clickedBtnDelSlice = clickedBtnDel.slice(getStrBtnDel);
            dlgNameDel = clickedBtnDelSlice + '_dialog';
            var titlePopup = $(this).data('title-popup');
            var w = '';
            var h = 620;
            var load_jsp_nameDel = 'modals/delInfo.jsp';
            var g = $('#dictionaryMain_grid');
            var sel_id = g.jqGrid('getGridParam', 'selrow');
            var realID = -1;
            realID = g.jqGrid('getCell', sel_id, 'id');
            var ischeck = g.jqGrid('getCell', sel_id, 'ischeck');
            $("#" + dlgNameDel).dialog({
                title: localeLanguages['111'],
                resizable: false,
                autoOpen: false,
                minHeight: 200,
                maxHeight: 620,
                width: 400,
                modal: true,
                buttons: {
                    'Tətbiq et': function () {
                        var g = $('#dictionaryMain_grid');
                        var sel_id = g.jqGrid('getGridParam', 'selrow');
                        var realID = g.jqGrid('getCell', sel_id, 'id');
                        var url = 'cs1?action=saveSubjectDictionary&dicPartID=' + dicPartID + '&newEditmp='
                            + newEditmp + '&realID=' + realID;
                        $.post(
                            url,
                            function (data) {
                                success:{
                                    tabs_grid_dic();
                                }
                            });
                        $(this).dialog("close");
                    }, 'İmtina et': function () {
                        $(this).dialog("close");
                    }
                }
            });
            if(realID != false) {
                if(ischeck == 0) {
                    $("#" + dlgNameDel).load(load_jsp_nameDel, function () {
                        $("#" + dlgNameDel).dialog("open");
                    });
                }else {
                    alert("Silmək əməliyyatı nəzərdə tutulmayıb!");
                }
            }else {
                alert("Heç bir sətir seçilməmişdir!");
            }

        });
    }

    buttons_click_dic();

});
function loadSecondDialogHeaderInfo_2(part, callback) {
    if (newEditmp == 1) {
        $("#dic_organization").getComboContent(4, localeLanguages['191'], '&param1=-1&param2=-1&param3=-1', function () {
            $("#dic_position").getComboContent(5, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {

            }, 2);
        }, 12);
    }
    if (newEditmp == 2) {
        if (part != 10014) {
            var g = $('#dictionaryMain_grid');
            var sel_id = g.jqGrid('getGridParam', 'selrow', '');
            var realID = g.jqGrid('getCell', sel_id, 'id');

            var dicName = g.jqGrid('getCell', sel_id, 'full_name');
            var dicName1 = g.jqGrid('getCell', sel_id, 'description');
            if (realID != false) {
                $("#_nameAZ").val(dicName);
                $("#_descriptionAZ").val(dicName1);
                if (callback) {
                    callback();
                }
            } else alert("Heç bir sətir seçilməmişdir!!");

        } else {
            var g = $('#dictionaryMain_grid');
            var sel_id = g.jqGrid('getGridParam', 'selrow', '');
            var realID = g.jqGrid('getCell', sel_id, 'id');
            $("#dic_organization").getComboContent(4, localeLanguages['191'], '&param1=-1&param2=-1&param3=-1', function () {
                $("#dic_position").getComboContent(5, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                    $("#dic_position").change();
                    var organization = g.jqGrid('getCell', sel_id, 'tree');
                    var position = g.jqGrid('getCell', sel_id, 'pos');
                    if (realID != false) {
                        $("#dic_position").val(position);
                        $("#dic_organization").val(organization);
                        if (callback) {
                            callback();
                        }
                    } else alert('"Heç bir sətir seçilməmişdir!!"');
                }, 2);
            }, 12);


        }


    }

    else {
        if (callback) {
            callback();
        }
    }
}