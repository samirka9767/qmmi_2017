var staticSrc;
var getIdTabMenu = 0;
$(document).ready(function () {
    $('.nav_tab>li a').click(function () {
        $(this).parent().parent().find('a').removeClass('selected_link');
        $(this).addClass('selected_link');
        var title_tab = $(this).text();
        $('.title-tab').text(title_tab);
        $('div').removeClass('displayBlock');
        getIdTabMenu = $(this).attr('id');
        console.log(getIdTabMenu+"= getIdTabMenu");
        mpPartID1 = $(this).attr('attr_partid');
        console.log(mpPartID1 + "mpPartID1 ////");
        $('div[name*=' + getIdTabMenu + ']').addClass('displayBlock');
        if(getIdTabMenu == 'contactEmployee') {
            $('.buttons_container_popup').show();
        }else {
            $('.buttons_container_popup').hide();
        }
        setTimeout(tabs_grid, 200);

    });

    $('#structureTab li a').click(function () {
        $('.buttons_container_popup').hide();
        if(getIdTabMenu == 'contactEmployee') {
            $('.buttons_container_popup').show();
        }else if(getIdTabMenu == 'contactStructure'){
            $('.buttons_container_popup').show();
        }else {
            $('.buttons_container_popup').hide();
        }
        var gridAreaDisplay = $('.grid_area').parent().css('display');
        if(partID == 1008) {
            if (gridAreaDisplay == 'block') {
                if (getIdTabMenu == 'operationExamples') {
                    $('.buttons_container_popup').hide();
                } else {
                    $('.buttons_container_popup').show();
                    $("#div_qr").show();
                    $("#div_new").show();
                    $("#div_edit").show();
                    $("#div_del").show();
                }
            } else {
                $('.buttons_container_popup').show();
                $("#div_qr").show();
                $("#div_new").hide();
                $("#div_edit").hide();
                $("#div_del").hide();
            }
        }else if(partID == 1009) {
            console.log("is it here? ");
            if($('#btn_editMain_div').css('display') != 'none'){
                console.log("and here?");
                $('.buttons_container_popup').show();
                $("#div_qr").show();
                $("#div_new").hide();
                $("#div_edit").hide();
                $("#div_del").hide();
            }
        }

    });
    function visibleFirstClick() {

        $('.nav_tab li:visible').first().children('a').click();
    }
    setTimeout(visibleFirstClick, 100);
});

function tabs_grid() {

    var col_name, col_model = '';
    var widthGrid = $('.grid_area').width() - 10;
    var heightGrid = $('.grid_area').height() - 58;
    var hideAuthor = "";
    if (getIdTabMenu == 'imagesExamples' || getIdTabMenu == 'mediaExamples' || getIdTabMenu == 'docExamples' || getIdTabMenu == 'libraryExamples') {
        col_name = [localeLanguages['136'],localeLanguages['141'],localeLanguages['136'],
            localeLanguages['136'],localeLanguages['141'],localeLanguages['141'],'realID','docUrl','docLink','docNo','isHtmlContain','id'];
        col_model = [
            {name: 'docnameAz', index: 'docnameAz', width:40},
            {name: 'authorAz', index: 'authorAz', width: 50},
            {name: 'docnameRu', index: 'docnameRu',hidden:true},
            {name: 'docnameEn', index: 'docnameEn',hidden:true},
            {name: 'authorRu', index: 'authorRu',hidden:true},
            {name: 'authorEn', index: 'authorEn',hidden:true},
            {name: 'realID', index: 'realID',hidden:true},
            {name: 'docUrl', index: 'docUrl',hidden:true},
            {name: 'docLink', index: 'LINK',hidden:true},
            {name: 'docNo', index: 'docNo',hidden:true},
            {name: 'isHtmlContain', index: 'DOCUMENTCONTAIN',hidden:true},
            {name: 'id', index: 'id',hidden:true}
        ];
    } else if ( getIdTabMenu == 'referenceFiles') {
        col_name = [localeLanguages['136'],localeLanguages['141'],localeLanguages['136'],
            localeLanguages['136'],localeLanguages['141'],localeLanguages['141'],'realID','docUrl','docLink','docNo','isHtmlContain','id'];
        col_model = [
            {name: 'docnameAz', index: 'docnameAz', width:40},
            {name: 'authorAz', index: 'authorAz', width: 50,hidden:true},
            {name: 'docnameRu', index: 'docnameRu',hidden:true},
            {name: 'docnameEn', index: 'docnameEn',hidden:true},
            {name: 'authorRu', index: 'authorRu',hidden:true},
            {name: 'authorEn', index: 'authorEn',hidden:true},
            {name: 'realID', index: 'realID',hidden:true},
            {name: 'docUrl', index: 'docUrl',hidden:true},
            {name: 'docLink', index: 'LINK',hidden:true},
            {name: 'docNo', index: 'docNo',hidden:true},
            {name: 'isHtmlContain', index: 'DOCUMENTCONTAIN',hidden:true},
            {name: 'id', index: 'id',hidden:true}
        ];
    } else if (getIdTabMenu == 'operationExamples') {
        col_name = [localeLanguages['180'], localeLanguages['127'], localeLanguages['145'],localeLanguages['163'],'statusID'];
        col_model = [
            {name: 'status', index: 'status', width: 20},
            {name: 'eventDate', index: 'eventDate', width: 20},
            {name: 'reason', index: 'reason', width: 20},
            {name: 'userName', index: 'UNAME', width: 50},
            {name: 'statusID', index: 'statusID', width: 50,hidden:true}
        ];
    } else if (getIdTabMenu == 'addressStructure') {
        col_name = ['№',  localeLanguages['186'],  localeLanguages['149']];
        col_model = [
            {name: 'id', index: 'id', width: 10},
            {name: 'invdate', index: 'invdate2', width: 45},
            {name: 'invdate', index: 'invdate2', width: 45}];
    } else if (getIdTabMenu == 'contactStructure' || getIdTabMenu == 'contactEmployee' ) {
        col_name = ['№', localeLanguages['159'], localeLanguages['150'],'tipID','realID'];
        col_model = [
            {name: 'id', index: 'id', width: 10},
            {name: 'contType', index: 'contType', width: 45},
            {name: 'contv', index: 'contv', width: 45},
            {name: 'tipID', index: 'tipID', width: 45,hidden:true},
            {name: 'realID', index: 'realID', width: 45,hidden:true}
        ];
    }
    else {
        col_name, col_model = '';
    }

    $('#' + window.gridName).jqGrid('GridUnload');
    jQuery('#' + window.gridName).jqGrid({
        height: heightGrid,
        width: widthGrid,
        datatype: "local",
        colNames: col_name,
        colModel: col_model,
        rowNum: 10,
        rowList: [10, 20],
        pager: '#' + window.pagerName,
        scrollOffset: 0,
        sortname: 'id',
        onSelectRow: function (id) {
            var sel_id;
            var docPath;
            var docLink;
            var g = $("#" + window.gridName);
            sel_id = g.jqGrid('getGridParam', 'selrow');
            docPath = g.jqGrid('getCell', sel_id, 'docUrl');
            var sel_id = g.jqGrid('getGridParam', 'selrow');
        },
        ondblClickRow: function (id) {
            var sel_id;
            var docPath;
            var docLink;
            var g = $("#" + window.gridName);
            sel_id = g.jqGrid('getGridParam', 'selrow');
            docPath = g.jqGrid('getCell', sel_id, 'docUrl');
            docLink = g.jqGrid('getCell', sel_id, 'docLink');
            if (docPath != "") {
                downloadExamplesFiles(mpPartID1,docPath);
            }
            if(docLink.length != 0) {

            }

            if(docLink != ""){
                if (docLink.indexOf('https://') == -1 && docLink.indexOf('http://') == -1 )
                {
                    docLink = "https://"+docLink;
                }
                $("#docLink1").attr("href",docLink);
                $("#docLink1").attr("target", "_blank");
                document.getElementById("docLink1").click();
            }
        }
    });



    if (mpPartID1 == 3011) {
        if (peducation.length > 0) {
            for (var m = 0; m < peducation.length; m++) {
                jQuery("#" + window.gridName).jqGrid('addRowData', m + 1, peducation[m]);
            }
        }

    } else if (mpPartID1 == 3012) {
        if (pcontact.length > 0) {
            for (var m = 0; m < pcontact.length; m++) {
                jQuery("#" + window.gridName).jqGrid('addRowData', m + 1, pcontact[m]);
            }
        }

    }
    else if (mpPartID1 == 3013) {
        if (eexam.length > 0) {
            for (var m = 0; m < eexam.length; m++) {
                jQuery("#" + window.gridName).jqGrid('addRowData', m + 1, eexam[m]);
            }
        }

    } else if (mpPartID1 == 50001) {
        if (pothdoc.length > 0) {
            for (var m = 0; m < pothdoc.length; m++) {
                jQuery('#examples_newMain_grid').jqGrid('addRowData', m + 1, pothdoc[m]);
            }
        }

    }else if (mpPartID1 == 50002) {
        if (media.length > 0) {
            for (var m = 0; m < media.length; m++) {
                jQuery('#examples_newMain_grid').jqGrid('addRowData', m + 1, media[m]);
            }
        }
    }else if (mpPartID1 == 50003) {
        if (library.length > 0) {
            for (var m = 0; m < library.length; m++) {
                jQuery('#examples_newMain_grid').jqGrid('addRowData', m + 1, library[m]);
            }
        }
    }else if (mpPartID1 == 50004) {
        if (documents.length > 0) {
            for (var m = 0; m < documents.length; m++) {
                jQuery('#examples_newMain_grid').jqGrid('addRowData', m + 1, documents[m]);
            }
        }
    }else if (mpPartID1 == 50005) {
        if (ExampleOperation.length > 0) {
            for (var m = 0; m < ExampleOperation.length; m++) {
                jQuery('#examples_newMain_grid').jqGrid('addRowData', m + 1, ExampleOperation[m]);
            }
        }
    }else if (mpPartID1 == 50006) {
        if (referenceFile.length > 0) {
            for (var m = 0; m < referenceFile.length; m++) {
                jQuery('#examples_newMain_grid').jqGrid('addRowData', m + 1, referenceFile[m]);
            }
        }
    }
};