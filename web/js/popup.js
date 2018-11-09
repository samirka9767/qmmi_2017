/**
 * Created by Zibeyda on 29.04.2016.
 */

$(document).ready(function () {
    setTimeout(gridShow, 100);
    setTimeout(tableZ, 100);


});
function gridShow() {

    var col_name, col_model = '';
    var widthGrid = $('.grid_area').width()-4;
    console.log("WWWWWW = "+widthGrid);

    var selectedMain = $('.sidebar1 .selected_link').attr('id');

    var heightGrid = $('.grid_area').height() - 58;

    if (window.gridName == 'advsearchMain_grid') {

        col_name = [ 'PARAMETR', 'DƏYƏR', 'ŞƏRT','paramID','opType','curOP','paramCount'];
        col_model =  [
            {name: 'paramName', index: 'paramName', sorttype: 'text' },
            {name: 'paramVal', index: 'paramVal', sorttype: 'text' },
            {name: 'paramCond', index: 'paramCond', sorttype: 'text' },
            {name: 'paramID', index: 'paramID', sorttype: 'text',hidden:true},//,hidden:true,hidden:true ,hidden:true
            {name: 'opType', index: 'opType', sorttype: 'text',hidden:true},
            {name: 'curOP', index: 'curOP', sorttype: 'text',hidden:true},

            {name: 'paramCount', index: 'paramCount', sorttype: 'int',hidden:true}
        ];
    }
    else {
        col_name = ['№', localeLanguages['181'], localeLanguages['153']];
        col_model = [
            {name: 'id', index: 'id', width: 5},
            {name: 'invdate', index: 'invdate2', width: 65},
            {name: 'name', index: 'name asc', width: 30}];
    }

    console.log("window.gridName********* = " + window.gridName);
    console.log("widthGrid" + widthGrid+"   "+heightGrid);
    $('#' + window.gridName).jqGrid('GridUnload');
    jQuery('#' + window.gridName).jqGrid({
        height: heightGrid,
        width: widthGrid,
        url: '',
        datatype: "json",
        colNames: col_name,
        colModel: col_model,
        rowNum: 10,
        loadonce:true,
        rowList: [10, 20],
        pager: '#' + window.pagerName,
        scrollOffset: 0,
        rownumbers: true,
        sortname: 'id',
        onSelectRow: function (id) {
            var g = $("#" + window.gridName);
            var sel_id = g.jqGrid('getGridParam', 'selrow');
            currID = g.jqGrid('getCell', sel_id, 'realID');
        },
        ondblClickRow:function(id) {
            if(partID == 1017){
                $("#btn_editSearchMain").click();
            }
        }
    });

    if (advSearch1.length>0) {
        for (var i = 0; i < advSearch1.length; i++) {
            jQuery('#advsearchMain_grid').jqGrid('addRowData', i + 1, advSearch1[i]);
        }
    }
}
function tableZ() {
    var table_length = $('.tableScrollHead th').length;

    for (var i = 0; i <= table_length; i++) {
        var theadWidth = $('.tableScrollHead th:nth-child(' + i + ')').width();
        console.log(theadWidth+"**");
        $('.tableScrollBody td:nth-child(' + i + ')').width(theadWidth);
    }
}




