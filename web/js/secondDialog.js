var clickedBtn;
var getStrBtn;
var BtnType;
var clickedBtnDel;
var getStrBtnDel;
var clickedBtnDelSlice;
var dlgNameDel;
var newEditmp;
var currID;
var phType = 0;
function btnclickPhoto() {
    var src = document.getElementById("personImage1").src;
    document.getElementById("lpPhoto1").src = src;
    phType = 3;
    $("#ui-dialog-title-dlg_loadPhoto").html("Şəkil");
    $("#dlg_loadPhoto").dialog("open");
}
$(document).ready(function () {
    $("#btn_QRcode").attr("operType", mNewEdit);

    buttons_click();

});
function SetBtnType(BtnType) {
    if (BtnType == 'btnAdd') {
        newEditmp = 1;
    } else if (BtnType == 'btnEdit') {
        newEditmp = 2;
    } else if (BtnType == 'btnDel') {
        newEditmp = 3;
    } else if (BtnType == "btnPhotoQ") {
        phType = 1;
        newEditmp = 1;
        var src = document.getElementById("QuestionImage").src;
    } else if (BtnType == "btnPhotoEmp") {
        newEditmp = 1;
        phType = 3;
        var src = document.getElementById("personImage1").src;
    } else if (BtnType == "btnPhotoCarOrg") {
        newEditmp = 1;
        phType = 2;
        var src = document.getElementById("personImage2").src;
    }
}
function loadSecondDialogHeaderInfo(menupart, part, callback) {
    console.log(newEditmp + "=newEditmp 4444444444");
    console.log(part + "=part menupart=" + menupart);
    if (part == 3012) {
        var g;
        if (menupart == 1006) {
            g = $('#structure_newMain_grid');
        } else if (menupart == 1001) {
            g = $('#employees_newMain_grid');
        }

        var sel_id = g.jqGrid('getGridParam', 'selrow');
        var realID = g.jqGrid('getCell', sel_id, 'realID');
        var tipID = g.jqGrid('getCell', sel_id, 'tipID');
        var contval = g.jqGrid('getCell', sel_id, 'contv');
        if (newEditmp == 1) {
            $('#contList').getComboContent(3, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                //  $("#contList").change();
                if (callback) {
                    callback();
                }
            }, 2);

        }
        else if (newEditmp == 2) {
            $('#contList').getComboContent(3, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                for (var j = 0; j < pcontact.length; j++) {
                    var contVal = g.jqGrid('getCell', sel_id, 'contv');
                    if (tipID == pcontact[j].tipID && contval == pcontact[j].contv) {
                        var contType = pcontact[j].contType;
                        setComboSelectedIndex1("contList", pcontact[j].tipID);
                    }

                }
            }, 2);
            for (var j = 0; j < pcontact.length; j++) {
                if (tipID == pcontact[j].tipID && contval == pcontact[j].contv) {
                    var contv = pcontact[j].contv;
                    $("#contVal").val(contv);
                }
            }
            if (callback) {
                callback();
            }
        }
    } else if (part == 50001) {
        g = $('#examples_newMain_grid');
        var sel_id = g.jqGrid('getGridParam', 'selrow');
        var realID = g.jqGrid('getCell', sel_id, 'realID');


        if (newEditmp == 2) {
            var docnameAz = "";
            var docnameRu = "";
            var docnameEn = "";
            var authorAz = "";
            var authorRu = "";
            var authorEn = "";
            var docdate = "";
            var docUrl = "";
            for (var j = 0; j < pothdoc.length; j++) {
                docnameAz = pothdoc[j].docnameAz;
                docnameRu = pothdoc[j].docnameRu;
                docnameEn = pothdoc[j].docnameEn;
                authorAz = pothdoc[j].authorAz;
                authorRu = pothdoc[j].authorRu;
                authorEn = pothdoc[j].authorEn;
                docdate = pothdoc[j].docdate;

                if(pothdoc[j].docUrl != null){
                    docUrl = pothdoc[j].docUrl;
                }

                if (pothdoc[j].realID == realID) {
                    $("#documentNameAZ").val(docnameAz);
                    $("#documentNameRu").val(docnameRu);
                    $("#documentNameEn").val(docnameEn);
                    $("#authorAz").val(authorAz);
                    $("#authorRu").val(authorRu);
                    $("#authorEn").val(authorEn);
                    $("#docdate").val(docdate);
                    //     $("#upload").val(docUrl);
                    $("#fileformlabel").text(docUrl);
                }


            }


            if (callback) {
                callback();
            }
        }
        if (callback) {
            callback();
        }
    } else if (part == 50002) {
        g = $('#examples_newMain_grid');
        var sel_id = g.jqGrid('getGridParam', 'selrow');
        var realID = g.jqGrid('getCell', sel_id, 'realID');


        if (newEditmp == 2) {
            var docnameAz = "";
            var docnameRu = "";
            var docnameEn = "";
            var authorAz = "";
            var authorRu = "";
            var authorEn = "";
            var docdate = "";
            var docUrl = "";
            var docLink = "";
            for (var j = 0; j < media.length; j++) {
                docnameAz = media[j].docnameAz;
                docnameRu = media[j].docnameRu;
                docnameEn = media[j].docnameEn;
                authorAz = media[j].authorAz;
                authorRu = media[j].authorRu;
                authorEn = media[j].authorEn;
                docdate = media[j].docdate;
                if(media[j].docUrl != null){
                    docUrl = media[j].docUrl;
                }
                if (media[j].realID == realID) {
                    docLink = media[j].docLink;
                    $("#documentNameAZ").val(docnameAz);
                    $("#documentNameRu").val(docnameRu);
                    $("#documentNameEn").val(docnameEn);
                    $("#authorAz").val(authorAz);
                    $("#authorRu").val(authorRu);
                    $("#authorEn").val(authorEn);
                    $("#docdate").val(docdate);
                    $("#link").val(docLink);
                    $("#fileformlabel").text(docUrl);
                }
            }


            if (callback) {
                callback();
            }
        }
        if (callback) {
            callback();
        }
    } else if (part == 50003) {
        g = $('#examples_newMain_grid');
        var sel_id = g.jqGrid('getGridParam', 'selrow');
        var realID = g.jqGrid('getCell', sel_id, 'realID');

        if (newEditmp == 2) {
            var docnameAz = "";
            var docnameRu = "";
            var docnameEn = "";
            var authorAz = "";
            var authorRu = "";
            var authorEn = "";
            var docUrl = "";
            var docNo = "";
            var docLink = "";
            for (var j = 0; j < library.length; j++) {
                docnameAz = library[j].docnameAz;
                docnameRu = library[j].docnameRu;
                docnameEn = library[j].docnameEn;
                authorAz = library[j].authorAz;
                authorRu = library[j].authorRu;
                authorEn = library[j].authorEn;
                docNo = library[j].docNo;
                docLink = library[j].docLink;
                docdate = library[j].docdate;

                if(library[j].docUrl != null){
                    docUrl = library[j].docUrl;
                }

                if (library[j].realID == realID) {
                    $("#documentNameAZ").val(docnameAz);
                    $("#documentNameRu").val(docnameRu);
                    $("#documentNameEn").val(docnameEn);
                    $("#authorAz").val(authorAz);
                    $("#authorRu").val(authorRu);
                    $("#authorEn").val(authorEn);
                    $("#docdate").val(docdate);
                    $("#link").val(docLink);
                    $("#numb").val(docNo);
                    //  $("#upload").val(docUrl);
                    $("#fileformlabel").text(docUrl);
                }
            }

            if (callback) {
                callback();
            }
        }
        if (callback) {
            callback();
        }
    } else if (part == 50004) {
        g = $('#examples_newMain_grid');
        var sel_id = g.jqGrid('getGridParam', 'selrow');
        var realID = g.jqGrid('getCell', sel_id, 'realID');

        if(newEditmp == 1) {
            $('#documentTypeID').getComboContent(15, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            },1);
        }
        if (newEditmp == 2) {
            var docnameAz = "";
            var docnameRu = "";
            var docnameEn = "";
            var authorAz = "";
            var authorRu = "";
            var authorEn = "";
            var docdate = "";
            var docUrl = "";
            var docNo = "";
            var docLink = "";
            var isHtml = -1;
            console.log(documents.length+"  =documents.length");
            $('#documentTypeID').getComboContent(15, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                for (var j = 0; j < documents.length; j++) {
                    docnameAz = documents[j].docnameAz;
                    docnameRu = documents[j].docnameRu;
                    docnameEn = documents[j].docnameEn;
                    authorAz = documents[j].authorAz;
                    authorRu = documents[j].authorRu;
                    authorEn = documents[j].authorEn;
                    docdate = documents[j].docdate;
                    docNo = documents[j].docNo;
                    docLink = documents[j].docLink;
                    isHtml = documents[j].isHtmlContain;
                    if(documents[j].docUrl != null){
                        docUrl = documents[j].docUrl;
                    }
                    if (documents[j].realID == realID) {
                        setComboSelectedIndex1("documentTypeID",isHtml);
                        $("#documentNameAZ").val(docnameAz);
                        $("#documentNameRu").val(docnameRu);
                        $("#documentNameEn").val(docnameEn);
                        $("#authorAz").val(authorAz);
                        $("#authorRu").val(authorRu);
                        $("#authorEn").val(authorEn);
                        $("#docdate").val(docdate);
                        $("#link").val(docLink);
                        $("#numb").val(docNo);
                        //   $("#upload").val(docUrl);
                        $("#fileformlabel").text(docUrl);
                    }
                }
            },1);

            if (callback) {
                callback();
            }
        }
        if (callback) {
            callback();
        }
    } else if (part == 50006) {
        g = $('#examples_newMain_grid');
        var sel_id = g.jqGrid('getGridParam', 'selrow');
        var realID = g.jqGrid('getCell', sel_id, 'realID');

        if(newEditmp == 1) {
            $('#documentTypeID').getComboContent(17, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            },1);

        }
        if (newEditmp == 2) {
            var docnameAz = "";
            var docnameRu = "";
            var docnameEn = "";
            var authorAz = "";
            var authorRu = "";
            var authorEn = "";
            var docdate = "";
            var docUrl = "";
            var docNo = "";
            var docLink = "";
            var isHtml = -1;
            console.log(referenceFile.length+"  =documents.length");
            $('#documentTypeID').getComboContent(17, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                for (var j = 0; j < referenceFile.length; j++) {
                    docnameAz = referenceFile[j].docnameAz;
                    docnameRu = referenceFile[j].docnameRu;
                    docnameEn = referenceFile[j].docnameEn;
                    authorAz = referenceFile[j].authorAz;
                    authorRu = referenceFile[j].authorRu;
                    authorEn = referenceFile[j].authorEn;
                    docdate = referenceFile[j].docdate;
                    docNo = referenceFile[j].docNo;
                    docLink = referenceFile[j].docLink;
                    isHtml = referenceFile[j].isHtmlContain;
                    if(referenceFile[j].docUrl != null){
                        docUrl = referenceFile[j].docUrl;
                    }
                    if (referenceFile[j].realID == realID) {
                        setComboSelectedIndex1("documentTypeID",isHtml);
                        $("#documentNameAZ").val(docnameAz);
                        $("#documentNameRu").val(docnameRu);
                        $("#documentNameEn").val(docnameEn);
                        $("#authorAz").val(authorAz);
                        $("#authorRu").val(authorRu);
                        $("#authorEn").val(authorEn);
                        $("#docdate").val(docdate);
                        $("#link").val(docLink);
                        $("#numb").val(docNo);
                        //   $("#upload").val(docUrl);
                        $("#fileformlabel").text(docUrl);
                    }
                }
            },1);

            if (callback) {
                callback();
            }
        }
        if (callback) {
            callback();
        }
    } else {
        if (callback) {
            callback();
        }
    }
}

function buttons_click() {
    $('.tab_container button').not('button[ data-name=del]').click(function () {
        var clickedBtn = $(this).data('name');
        console.log("clickedBtn** = **" + clickedBtn);
        var selectedMenu = $('.nav_tab .selected_link').attr('id');
        newEditmp = $(this).attr('attr_newedit');
        var qrCodeOperType = $(this).attr('opertype');
        var selectedMenuTitle = $('.nav_tab .selected_link').text();
        console.log("selectedMenuTitle = " + selectedMenuTitle);
        var popupName = selectedMenu + '_' + clickedBtn;
        console.log("popupName == "+popupName);
        var load_jsp_name2 = 'modals/' + popupName + '.jsp';

        function load_dialog_3() {
            $("#" + popupName).html(' ');
            $("div[data-type=secondTabDialog] .ui-dialog-content").html(" ");
            $("div[data-type=secondTabDialog]").remove();
            var w = '';
            var h = 620;
            if (popupName == 'eduEmployee_new' || popupName == 'experienceEmployee_new' || popupName == 'addressStructure_new') {
                w = 450;
            }
            else if (popupName == 'contactEmployee_new') {
                w = 400;
            }
            else if (popupName == 'contactStructure_new' || popupName == 'contactStructure_edit') {
                load_jsp_name2 = 'modals/contactEmployee_new.jsp';
            }
            else if (popupName == 'imagesExamples_new') {
                w = 671;
                load_jsp_name2 = 'modals/imagesExamples_new.jsp';
            }
            else if (popupName == 'referenceFiles_new') {
                w = 671;
                popupName = 'imagesExamples_new';
                load_jsp_name2 = 'modals/imagesExamples_new.jsp';
                console.log("+++++++++++++++   "+load_jsp_name2);
            }
            else if (popupName == 'docExamples_new') {
                w = 671;
                load_jsp_name2 = 'modals/imagesExamples_new.jsp';
            }
            else if (popupName == 'libraryExamples_new') {
                w = 671;
                load_jsp_name2 = 'modals/imagesExamples_new.jsp';
            }
            else if (popupName == 'mediaExamples_new') {
                w = 671;
                load_jsp_name2 = 'modals/imagesExamples_new.jsp';
            }
            else if (popupName == 'generalExamples_new') {
                load_jsp_name2 = 'modals/GenerateQRcode.jsp';
            }
            else {
                load_jsp_name2 = 'modals/' + popupName + '.jsp';
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
                        if (partID == 1008 || partID == 1009 || partID == 1011) {

                            if (mpPartID1 == 50000) {
                                var qrCodePicture = $("#qrcode img").attr('src');
                                var url = 'cs1?action=setSession&typ=1&newEdit=' + qrCodeOperType + '&realID=' + 1 + '&partID=' + mpPartID1 + '&qrCodePicture=' + qrCodePicture;

                                $('#qrCodeForm').ajaxForm({
                                    url: url,
                                    cache: false,
                                    success: function (data) {
                                        $("#generalExamples_new").dialog("close");
                                        var div = $("#qrcode img");
                                        div.appendTo('#qrCodeMain');
                                    }
                                });
                                $('#qrCodeForm').submit();


                            } else {
                                if ($("#documentNameAZ").val().trim() != '' && ($("#fileformlabel").text().trim() != '' || $("#link").val().trim() != '')) {
                                    if(mpPartID1 != 50004 && mpPartID1 != 50006) {
                                        if (docInfo() != false) {
                                            $(this).dialog("close");
                                        }
                                    }else {
                                        var g = $('#examples_newMain_grid');
                                        var sel_id = g.jqGrid('getGridParam', 'selrow');
                                        var isHtmlContain = g.jqGrid('getCell', sel_id, 'isHtmlContain');
                                        var checkHtmlContain = -1;
                                        for (var j = 0; j < examplesMass.length; j++) {
                                            checkHtmlContain = examplesMass[j].checkHtml;
                                        }
                                        console.log("newEditmp ** = "+newEditmp);
                                        if(newEditmp == 1) {
                                            if(checkHtmlContain == 1 && $("#documentTypeID option:selected").val()== 1339 ){
                                                alert("Arayış üçün .doc genişlənməli sənəd artıq əlavə olunub!!");
                                            }else {
                                                if($("#documentTypeID option:selected").val() == 1339){
                                                    var fileformlabel = $("#fileformlabel").text();
                                                    var checkDocConvert = fileformlabel.substr(fileformlabel.length - 3);
                                                    if(checkDocConvert != "doc") {
                                                        alert("Bu əməliyyat üçün yalnız .doc genişlənməli sənəd seçilməlidir");
                                                    }else {
                                                        if (docInfo() != false) {
                                                            $(this).dialog("close");
                                                        }
                                                    }
                                                }else {
                                                    if (docInfo() != false) {
                                                        $(this).dialog("close");
                                                    }
                                                }
                                            }
                                        }else if(newEditmp == 2){
                                            if(checkHtmlContain == 1 && $("#documentTypeID option:selected").val()== 1339 && isHtmlContain != 1339 ){
                                                alert("Arayış üçün .doc genişlənməli sənəd artıq əlavə olunub!!");
                                            }else {
                                                if($("#documentTypeID option:selected").val() == 1339){
                                                    var fileformlabel = $("#fileformlabel").text();
                                                    var checkDocConvert = fileformlabel.substr(fileformlabel.length - 3);
                                                    if(checkDocConvert != "doc") {
                                                        alert("Bu əməliyyat üçün yalnız .doc genişlənməli sənəd seçilməlidir");
                                                    }else {
                                                        if (docInfo() != false) {
                                                            $(this).dialog("close");
                                                        }
                                                    }
                                                }else {
                                                    if (docInfo() != false) {
                                                        $(this).dialog("close");
                                                    }
                                                }
                                            }
                                        }



                                    }
                                }else {
                                    alert("Ad,Link və ya Şəkil/Video/Sənəd/Fayl sahələri mütləqdir");
                                }

                            }

                        } else if (partID == 1001 || partID == 1006) {
                            if ($("#contList :selected").val() == -1 || $("#contVal").val().trim() == "") {
                                if (localeLanguages['192'] == '1000') {
                                    alert(" \"Əlaqə tipi\", \"Əlaqə\" sahələrini boş buraxmaq olmaz.  ");
                                } else if (localeLanguages['192'] == '1001') {
                                    alert(" \"Тип Контакта\", \"Контакт\" должны быть заполнены.  ");
                                } else if (localeLanguages['192'] == '1002') {
                                    alert(" \"Contact type\", \"Contact\" are necessary fields.  ");
                                }
                            } else {
                                contactInfo(popupName, partID);
                            }
                        }
                    },
                    'İmtina et': function () {
                        $(this).dialog("close");
                    }
                }
            });
        }

        load_dialog_3();
        $("#" + popupName).load(load_jsp_name2, function () {
            loadSecondDialogHeaderInfo(partID, mpPartID1, function () {
                $("#" + popupName).parent().removeAttr('data-type').attr('data-type', 'secondTabDialog');
                putLabels();
                $("#" + popupName).dialog("open");
                return false;
            });
        });
    });

    $('button[data-name=del]').click(function () {
        console.log("data-name=del 7777777777777777777777");
        newEditmp = 3;
        clickedBtnDel = $(this).attr('id');
        getStrBtnDel = clickedBtnDel.indexOf('_') + 1;
        clickedBtnDelSlice = clickedBtnDel.slice(getStrBtnDel);
        dlgNameDel = clickedBtnDelSlice + '_dialog';
        var load_jsp_nameDel = 'modals/delInfo.jsp';
        var arrayName;
        var g;
        if (partID == 1008) {
            console.log("here!!");
            g = $('#examples_newMain_grid');
            if (dlgNameDel == 'examplesDel_dialog') {
                dlgNameDel = 'ExamplesDelete_dialog';
            }
        } else if (partID == 1001) {
            if (mpPartID1 == 3012) {
                g = $('#employees_newMain_grid');
            }
            if (mpPartID1 == 3011) {
                g = $('#employees_newMain_grid');
            }
        } else {
            if (mpPartID1 == 3011) {
                g = $('#employees_newMain_grid');
            } else if (mpPartID1 == 3012) {
                g = $('#structure_newMain_grid');
            }
        }
        var sel_id = g.jqGrid('getGridParam', 'selrow');
        var realID = g.jqGrid('getCell', sel_id, 'realID');
        var url = "cs1?action=setSession" + '&newEdit=' + newEditmp + '&partID=' + mpPartID1 + '&realID=' + realID;
        if (realID != false) {
            console.log(dlgNameDel);
            $("#" + dlgNameDel).dialog({
                title: localeLanguages['111'],
                resizable: false,
                autoOpen: false,
                minHeight: 200,
                maxHeight: 620,
                width: 400,
                modal: true,
                close: function (event, ui) { $('#ExamplesDelete_dialog').html();},
                buttons: {
                    'Tətbiq et': function () {

                        $.post(url, function (data) {
                            success:{
                                deleteFromArray(realID);
                                tabs_grid();
                            }
                        });
                        $(this).dialog("close");
                    }


                    ,
                    'İmtina et': function () {
                        $(this).dialog("close");
                    }
                }
            });

            $("#" + dlgNameDel).load(load_jsp_nameDel, function () {
                $("#" + dlgNameDel).dialog("open");
            });
        } else {
            if (localeLanguages['192'] == '1000') {
                alert('"Heç bir sətir seçilməmişdir.');
            } else if (localeLanguages['192'] == '1001') {
                alert('"Поле не выбрано.');
            } else if (localeLanguages['192'] == '1002') {
                alert('"Please,select a row.');
            }
        }
    });
    $('#btn_QRcode').click(function () {
        var clickedBtn = $(this).data('name');
        var selectedMenu = $('.nav_tab .selected_link').attr('id');
        newEditmp = $(this).attr('attr_newedit');
        var qrCodeOperType = $(this).attr('opertype');
        var selectedMenuTitle = $('.nav_tab .selected_link').text();
        console.log("selectedMenuTitle = " + selectedMenuTitle);
        var popupName = selectedMenu + '_' + clickedBtn;
        var load_jsp_name2 = 'modals/' + popupName + '.jsp';
        if (popupName == 'generalExamples_new') {
            load_jsp_name2 = 'modals/GenerateQRcode.jsp';
        }

        function load_dialogQR() {
            $("#" + popupName).html(' ');
            $("div[data-type=secondTabDialog] .ui-dialog-content").html(" ");
            $("div[data-type=secondTabDialog]").remove();
            var w = '';
            var h = 620;
            if (popupName == 'generalExamples_new') {
                load_jsp_name2 = 'modals/GenerateQRcode.jsp';
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
                        if (partID == 1008 || partID == 1009 || partID == 1011) {

                            if (mpPartID1 == 50000) {
                                var qrCodePicture = $("#qrcode img").attr('src');
                                var url = 'cs1?action=setSession&typ=1&newEdit=' + qrCodeOperType + '&realID=' + 1 + '&partID=' + mpPartID1 + '&qrCodePicture=' + qrCodePicture;

                                $('#qrCodeForm').ajaxForm({
                                    url: url,
                                    cache: false,
                                    success: function (data) {
                                        $("#generalExamples_new").dialog("close");
                                        var div = $("#qrcode img");
                                        div.appendTo('#qrCodeMain');
                                    }
                                });
                                $('#qrCodeForm').submit();
                            }
                        }
                    },
                    'İmtina et': function () {
                        $(this).dialog("close");
                    }
                }
            });
        }

        load_dialogQR();
        $("#" + popupName).load(load_jsp_name2, function () {
            loadSecondDialogHeaderInfo(partID, mpPartID1, function () {
                $("#" + popupName).parent().removeAttr('data-type').attr('data-type', 'secondTabDialog');
                putLabels();
                $("#" + popupName).dialog("open");
                return false;
            });
        });
        return false;
    });

    $('#btnPhotoCarOrg_choosePhoto1').click(function () {
        clickedBtn = $(this).attr('id');
        console.log(clickedBtn);
        getStrBtn = clickedBtn.indexOf('_') + 1;
        BtnType = clickedBtn.substring(0, getStrBtn - 1);
        SetBtnType(BtnType);
        console.log("clickedBtn  = " + clickedBtn);
        clickedBtn = clickedBtn.slice(getStrBtn);
        var dlgName = clickedBtn + '_dialog';
        var titlePopup = $(this).data('title-popup');
        var w = 400;
        var h = 620;
        var load_jsp_name2 = 'modals/' + 'chooseImage' + '_dialog.jsp';

        function load_dialog1() {
            $("#" + dlgName).html(' ');
            $("#" + dlgName).dialog({
                title: localeLanguages['208'],
                resizable: false,
                autoOpen: false,
                minHeight: 200,
                maxHeight: h,
                maxWidth: 1063,
                width: w,
                modal: true,
                buttons: {
                    'Tətbiq et': function () {
                        if (dlgName == 'choosePhoto1_dialog') {
                            var src = document.getElementById("lpPhoto1").src;
                            var url = 'cs1?action=setSession&newEdit=' + newEditmp + '&partID=2&phType=' + phType + '&foldID=0&realID=0' + '&ph=' + partID;
                            var val = $("#docFilePH").val();
                            var ss = val.substr(val.length - 3, 3).toUpperCase();
                            if (ss == "jpg".toUpperCase() || ss == "png".toUpperCase() || ss == "jpeg".toUpperCase()) {
                                $('#frmPhoto').ajaxForm({
                                    url: url,
                                    cache: false,
                                    success: function (data) {
                                        if (phType == 2) {
                                            document.getElementById("personImage2").src = src;
                                        }
                                    },
                                    error: function () {
                                        console.log("Error while uploading");
                                    }
                                });
                                $('#frmPhoto').submit();
                            } else {
                                alert("Şəkil JPG, GIF və ya PNG formatında olmalıdır.");
                                return false;
                            }
                            $(this).dialog("close");
                        }
                    },
                    'İmtina et': function () {
                        $(this).dialog("close");
                    }
                },
                close: function (event, ui) {
                    $("#choosePhoto1_dialog").remove();
                }
            });
        }

        load_dialog1();
        $("#" + dlgName).load(load_jsp_name2, function () {
            $("#" + dlgName).dialog("open");
            return false;
        });
        return false;
    });
    $('#btnPhotoEmp_choosePhoto').click(function () {
        clickedBtn = $(this).attr('id');
        getStrBtn = clickedBtn.indexOf('_') + 1;
        BtnType = clickedBtn.substring(0, getStrBtn - 1);
        SetBtnType(BtnType);
        clickedBtn = clickedBtn.slice(getStrBtn);
        console.log("clickedBtn  = " + clickedBtn);
        var dlgName = clickedBtn + '_dialog';
        var titlePopup = $(this).data('title-popup');
        var w = '';
        var h = 620;
        var load_jsp_name2 = 'modals/' + 'choosePhoto' + '_dialog.jsp';
        if (dlgName == 'choosePhoto_dialog') {
            w = 400;
            load_jsp_name2 = 'modals/chooseImage_dialog.jsp';
        }
        function load_dialog_4() {
            $("#" + dlgName).html(' ');
            $("#" + dlgName).dialog({
                title: localeLanguages['208'],
                resizable: false,
                autoOpen: false,
                minHeight: 200,
                maxHeight: h,
                maxWidth: 1063,
                width: w,
                modal: true,
                buttons: {
                    'Tətbiq et': function () {
                        if (dlgName == 'choosePhoto_dialog') {
                            var src = document.getElementById("lpPhoto1").src;
                            var url = 'cs1?action=setSession&newEdit=' + newEditmp + '&partID=1&phType=' + phType + '&foldID=0&realID=0';
                            var val = $("#docFilePH").val();
                            var ss = val.substr(val.length - 3, 3).toUpperCase();
                            if (ss == "jpg".toUpperCase() || ss == "png".toUpperCase() || ss == "jpeg".toUpperCase()) {
                                $('#frmPhoto').ajaxForm({
                                    url: url,
                                    cache: false,
                                    success: function (data) {
                                        if (phType == 1) {
                                            document.getElementById("QuestionImage").src = src;
                                        } /*else if (phType == 2) {
                                         document.getElementById("answerImage").src = src;
                                         }*/ else if (phType == 3) {
                                            document.getElementById("personImage1").src = src;
                                        }
                                    },
                                    error: function () {
                                        console.log("Error while uploading");
                                    }
                                });
                                $('#frmPhoto').submit();
                            } else {
                                alert("Şəkil JPG, GIF və ya PNG formatında olmalıdır.");
                                return false;
                            }
                            $(this).dialog("close");
                        }
                    },
                    'İmtina et': function () {
                        $(this).dialog("close");
                    }
                }
            });
        }

        load_dialog_4();
        $("#" + dlgName).load(load_jsp_name2, function () {
            $("#" + dlgName).dialog("open");

            return false;
        });

        return false;
    });


}

function putLabels() {
    var label = "";
    var fayl = "";
    var date1 = "";
    var date2 = "";
    var html1 = "";
    var html2 = "";
    var author = "";
    if (mpPartID1 == 50001) {
        label = '<label>' + localeLanguages['206'] + '</label>';
        fayl = '<label>' + localeLanguages['208'] + '</label>';
        date1 = '<label>' + localeLanguages['127'] + '</label>';
        author = '<label>' + localeLanguages['141'] + '</label>';
        $("#trDocumentType").css('display','none');
        $("#trLink").css('display','none');
        $("#number").css('display','none');

    } else if (mpPartID1 == 50002) {
        label = '<td width="70"><label>' + localeLanguages['207'] + '</label></td>';
        fayl = '<label>' + localeLanguages['209'] + '</label>';
        date1 = '<label>' + localeLanguages['231'] + '</label>';
        author = '<label>' + localeLanguages['141'] + '</label>';
        $("#number").css('display','none');
        $("#trDocumentType").css('display','none');

    }
    else if (mpPartID1 == 50003) {
        label = '<td width="70"><label>' + localeLanguages['136'] + '</label></td>';
        html2 =
            '<td><label>' + localeLanguages['204'] + '</label></td>';
        date1 = '<label>' + localeLanguages['142'] + '</label>';
        fayl = '<label>' + localeLanguages['138'] + '</label>';
        author = '<label>' + localeLanguages['141'] + '</label>';
        $("#trDocumentType").css('display','none');
        $("#number").css('display','none');
    } else if (mpPartID1 == 50004) {
        label = '<label>' + localeLanguages['143'] + '</label>';
        fayl = '<label>' + localeLanguages['210'] + '</label>';
        date1 = '<label>' + localeLanguages['127'] + '</label>';
        author = '<label>' + localeLanguages['228'] + '</label>';
        $("#trDocumentType").show();
    } else if (mpPartID1 == 50006) {
        label = '<label>' + localeLanguages['230'] + '</label>';
        fayl = '<label>' + localeLanguages['131'] + '</label>';
        date1 = '<label>' + localeLanguages['127'] + '</label>';
        author = '<label>' + localeLanguages['228'] + '</label>';
        $("#tr_Author").css('display','none');
        $("#trDate1").css('display','none');
        $("#trLink").css('display','none');
        $("#number").css('display','none');
        $("#trDocumentType").hide();
    }

    $("#fileName").html(label);
    $("#file1").html(fayl);
    $("#date1").html(date1);
    $("#date2").html(date2);
    $("#author").html(author);
}



