/**
 * Created by Zibeyda on 29.04.2016.
 */
var getIdTabMenu = 0;
var dicPartID = 0;
$(document).ready(function () {
    $('.nav_tab>li a').click(function () {
        $(this).parent().parent().find('a').removeClass('selected_link');
        $(this).addClass('selected_link');
        var title_tab = $(this).text();
        $('.title-tab').text(title_tab);
        getIdTabMenu = $(this).attr('id');
        dicPartID = $(this).attr('attr_dicPartId');
        setTimeout(tabs_grid_dic, 100);
    });
    $('.nav_tab li:first-child a').click();
});

function tabs_grid_dic() {
    var col_name, col_model = '';
    console.log("getIdTabMenu = "+getIdTabMenu);

    var widthGrid = $('.grid_area').width() - 10;
    var heightGrid = $('.grid_area').height() - 58;
    var url;
    if (getIdTabMenu == 'positionDictionary') {

         url = 'cs?action=getGridContent&gridType=102'+'&dicPartId='+dicPartID+ '&lang_id=' + localeLanguages['192'];
        col_name=['№', localeLanguages['185'], localeLanguages['154'],'tree','pos','ischeck','id'];
        col_model=[
            {name: 'row1', index: 'ROW1', sorttype: 'int', width:'12',align:'center'},
            {name: 'organization', index: 'ORGANIZATION', width: 45},
            {name: 'position', index: 'POSITION', width: 45},
            {name: 'tree', index: 'TREE', width: 45,hidden:true},
            {name: 'pos', index: 'POS', width: 45,hidden:true},
            {name: 'ischeck', index: 'ISCHECK', width: 45,hidden:true},
            {name: 'id', index: 'ID', width: 10,hidden:true}
        ];
    } else {
        url = 'cs?action=getGridContent&gridType=101'+'&dicPartId='+dicPartID+ '&lang_id=' + localeLanguages['192'];
        col_name=['№', localeLanguages['136'], localeLanguages['137'],'ischeck','id'];
        col_model=[
            {name: 'row1', index: 'ROW1', sorttype: 'int', width:'12',align:'center'},
            {name: 'full_name', index: 'FULL_NAME', width: 45},
            {name: 'description', index: 'DESCRIPTION', width: 45},
            {name: 'ischeck', index: 'ISCHECK', width: 45,hidden:true},
            {name: 'id', index: 'ID', width: 10,hidden:true}
        ];
    }


    $('#' + window.gridName).jqGrid('GridUnload');
    jQuery('#' + window.gridName).jqGrid({
        url: url,
        height: heightGrid,
        width: widthGrid,
        datatype: "json",
        colNames: col_name,
        colModel: col_model,
        rowNum: 100,
        rowList: [200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100],
        pager: '#' + window.pagerName,
        scrollOffset: 0,
        sortname: 'id',
        ondblClickRow: function (id) {
            $("#btn_dictionaryEdit").click();
        }
    });



};
