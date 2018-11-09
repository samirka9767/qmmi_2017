<%@ page import="utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div id="final_exDialog"></div>
<div id="yekun_dialog"></div>
<div id="operationExamples_new"></div>
<div id="docExamples_new"></div>
<div id="libraryExamples_new"></div>
<div id="imagesExamples_new"></div>
<div id="generalExamples_new"></div>
<div id="reference_new"></div>
<div id="mediaExamples_new"></div>
<div id="ExamplesDelete_dialog"></div>
<div class="container_popup">
    <form notEnter="notEnter" name="frm_struktur" id="frm_struktur"
          action="cs1?action=saveExamplesInfo" method="POST">
    <div>
        <form id="" name="" action="" method="POST">
            <table class="table_forums top-table">
                <tbody>
                <tr>
                    <td width="75px"><label><%=Util.getLocaleText(request, 126)%>  </label></td>
                    <td width="200"><input id="registerCode1" type="text" disabled="disabled"> </td>



                    <td ><label><%=Util.getLocaleText(request, 223)%><span class="required-field"></span></label></td>
                    <td >
                        <select id="ExmCategory" name="ExmCategoryID"  multiple="multiple" class="multi" onchange="loadDependentCombo(18, localeLanguages['191'],'ExmGroup','ExmCategory',1013,1008)"></select>
                    </td>

                    <td><label><%=Util.getLocaleText(request, 130)%><span class="required-field"></span></label></td>
                    <td><select id="region" name="regionID"  multiple="multiple" class="multi"></select></td>

                    <td><label>YUNESKO</label></td>
                    <td >
                        <select id="yuneskoDomain" name="yuneskoDomain" multiple="multiple"  class="multi" <%--onchange=combosChange(0)--%>></select>
                    </td>


                    <td style="display: none;"><input id="eDate" name="eDate"></td>

                    <td style="display: none;"  width="100px"><label><%=Util.getLocaleText(request, 128)%> <span class="required-field"></span></label></td>
                    <td style="display: none;"><select id="OvertakenRegion" name="OvertakenRegionID"></select></td>



                    <td rowspan="3" width="105" valign="top">
                        <div class="divChoosePhoto">
                            <div id="divImage">

                                <div id="qrCodeMain" style="border: 1px solid black;; height:100px;width: 100px;">
                                </div>
                            </div>

                            <div class="buttons_container buttons_container_popup">
                                <div id="div_qr" class="button_block" attribute_hideShod="1">
                                    <button id="btn_QRcode" class="icon_qrcode" name="btn_structureNew" style="height: 31px;width: 26px" data-name="new"></button>
                                    <span class="button-text"><%=Util.getLocaleText(request, 214)%></span>
                                </div>
                            </div>
                        </div>
                    </td>

                </tr>
                <tr>

                    <td ><label><%=Util.getLocaleText(request, 117)%>  </label></td>
                    <td>
                        <select id="ExmGroup" name="ExmGroupID"  onchange="loadDependentCombo(18, localeLanguages['191'],'ExmClass','ExmCategory',1014,1008)"></select>
                    </td>


                    <td><label><%=Util.getLocaleText(request, 118)%></label></td>
                    <td>
                        <select id="ExmType" name="ExmTypeID" onchange="loadDependentCombo(18, localeLanguages['191'],'ExmGenre','ExmCategory',1015,1008)"></select>
                    </td>


                    <td ><label><%=Util.getLocaleText(request, 199)%> </label></td>
                    <td >
                        <select id="cOrganization" name="cOrganization" multiple="multiple" class="multi" <%--onchange=combosChange(0)--%>></select>
                    </td>

                    <td><label><%=Util.getLocaleText(request, 105)%></label></td>
                    <td align="left">
                        <select id="cCarriesListMulti" name="cCarriesListMulti" multiple="multiple" class="multi"></select>
                    </td>

                </tr>
                <tr>
                    <td><label><%=Util.getLocaleText(request, 119)%> </label></td>
                    <td >
                        <select id="ExmGenre" name="ExmGenreID" onchange="loadDependentCombo(18, localeLanguages['191'],'ExmGenre1','ExmCategory',1015,1008)" ></select>
                    </td>
                    <td style="display: none">
                        <select id="ExmGenre1" name="ExmGenreID1" <%--onchange="loadDependentCombo(18, localeLanguages['191'],'ExmGenre','ExmGenre',1015,1008)"--%>></select>
                    </td>



                    <td ><label><%=Util.getLocaleText(request, 116)%> </label></td>
                    <td>
                        <select id="ExmClass" name="ExmClassID" onchange="loadDependentCombo(18, localeLanguages['191'],'ExmType','ExmCategory',1017,1008)"></select>
                    </td>

                    <td><label>M/N</label></td>
                    <td><select id="mete" name="mete"></select></td>
                </tr>

                </tbody>
            </table>
        </form>
    </div>
    <div class=" popup_with_menu margin-top-5">
        <table class="full-width">
            <tr>
                <td width="170">
                    <div class="toLeft" style="min-height:unset; height:357px ! important;">
                        <ul class="nav nav_tab" id="structureTab">
                            <li><a id="generalExamples" attr_partid="50000" class="selected_link"
                                   href="#"><%=Util.getLocaleText(request, 120)%>
                            </a></li>
                            <li><a id="referenceFiles" attr_partid="50006" class="selected_link"
                                   href="#">Arayış
                            </a></li>
                            <li><a id="imagesExamples" attr_partid="50001"
                                   href="#"><%=Util.getLocaleText(request, 121)%>
                            </a></li>
                            <li><a id="mediaExamples" attr_partid="50002" href="#"><%=Util.getLocaleText(request, 122)%>
                            </a></li>
                            <li><a id="libraryExamples" attr_partid="50003"
                                   href="#"><%=Util.getLocaleText(request, 123)%>
                            </a></li>
                            <li><a id="docExamples" attr_partid="50004" href="#"><%=Util.getLocaleText(request, 124)%>
                            </a></li>
                            <li><a id="operationExamples" attr_partid="50005"
                                   href="#"><%=Util.getLocaleText(request, 125)%>
                            </a></li>
                        </ul>
                    </div>
                </td>
                <td>
                    <div class="tab_container">
                        <div class="tablebutton">

                            <table class="full-width">
                                <tr>
                                    <td class="tetx_td2"><span class="title-tab"></span></td>
                                    <div></div>

                                    <td align="right" id="td_schButtons">

                                        <div id="buttons" class="buttons_container buttons_container_popup">
                                            <div id="div_new" class="button_block">
                                                <button id="btn_examplesNew" class="icon_new" name="btn_structureNew"
                                                        attribute_hideShod="2"
                                                        data-name="new" attr_newedit="1"></button>
                                                <span class="button-text"><%=Util.getLocaleText(request, 109)%></span>
                                            </div>
                                            <div id="div_edit" class="button_block">
                                                <button id="btn_examplesEdit" class="icon_edit" name="btn_structureEdit"
                                                        attribute_hideShod="3"
                                                        data-name="new" attr_newedit="2"></button>
                                                <span class="button-text"><%=Util.getLocaleText(request, 110)%></span>
                                            </div>
                                            <div id="div_del" class="button_block">
                                                <button id="btn_examplesDel" class="icon_del" name="btn_structureDel"
                                                        attribute_hideShod="4"
                                                        data-name="del" attr_newedit="3"></button>
                                                <span class="button-text"><%=Util.getLocaleText(request, 111)%></span>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="tab_content" style="height:auto!important;">
                            <form notEnter="notEnter" name="frm_struktur1" id="frm_struktur1"
                                  action="cs1?action=saveExamplesInfo" method="POST">
                                <div name="generalExamples" style="display: none">

                                    <table class="table_forums">
                                        <tbody>
                                            <tr>
                                                <td><label><%=Util.getLocaleText(request, 136)%><span class="required-field"></span></label></td>
                                                <td><input id="exmNameAz" name="exmNameAz" placeholder="AZ"></td>
                                                <td></td>
                                                <td><input id="exmNameRu" name="exmNameRu" placeholder="RU"></td>
                                                <td></td>
                                                <td><input id="exmNameEn" name="exmNameEn" placeholder="EN"></td>
                                             </tr>
                                            <tr>
                                                <td><label><%=Util.getLocaleText(request, 129)%></label></td>
                                                <td><input id="exmKeyWordAz" name="exmKeyWordAz" placeholder="AZ"></td>
                                                <td></td>
                                                <td><input id="exmKeyWordRu" name="exmKeyWordRu" placeholder="RU"></td>
                                                <td></td>
                                                <td><input id="exmKeyWordEn" name="exmKeyWordEn" placeholder="EN"></td>

                                            </tr>
                                           <%-- <tr>
                                                <td valign="top"> <label><%=Util.getLocaleText(request, 131)%>  </label></td>
                                                <td><textarea rows="4" id="referenceAz" name="referenceAz" placeholder="AZ"></textarea></td>
                                                <td></td>
                                                <td><textarea rows="4" id="referenceRu" name="referenceRu" placeholder="RU"></textarea></td>
                                                <td></td>
                                                <td><textarea rows="4" id="referenceEn" name="referenceEn" placeholder="EN"></textarea></td>

                                            </tr>--%>
                                            <%--<tr>
                                                <td valign="top">  <label><%=Util.getLocaleText(request, 174)%><span class="required-field"></span> </label></td>
                                                <td><textarea id="textAz" name="textAz" rows="5"   placeholder="AZ"></textarea></td>
                                                <td></td>
                                                <td ><textarea id="textRu" name="textRu" rows="5"   placeholder="RU"></textarea></td>
                                                <td></td>
                                                <td><textarea id="textEn" name="textEn" rows="5" placeholder="EN"></textarea></td>
                                            </tr>--%>
                                            <tr style="display: none;">
                                                <td valign="top">   <label><%=Util.getLocaleText(request, 133)%> </label></td>
                                                <td><textarea rows="5" id="noteAz" name="noteAz" placeholder="AZ"></textarea></td>
                                                <td></td>
                                                <td ><textarea rows="5" id="noteRu" name="noteRu"  placeholder="RU"></textarea></td>
                                                <td></td>
                                                <td ><textarea rows="5" id="noteEn" name="noteEn"placeholder="EN"></textarea></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </form>
                            <div name="addressStructure,contactStructure,imagesExamples,mediaExamples,libraryExamples,docExamples,operationExamples,referenceFiles"
                                 style="display: none">
                                <div name="grid_area" class="grid_area" style="height:292px;">
                                    <table id="examples_newMain_grid"></table>
                                    <div id="examples_newMain_pager"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </td>
            </tr>
        </table>
    </div>
    </form>
</div>
<div style="visibility: hidden; position:absolute; top:10px; height:10px;">
    <form id="downForm1" name='downForm1' action='cs?action=loadPhoto&typ=1' method='post'>
        <input type="text" value="" id="filePath" name="filePath">
        <input type="submit" value="" id="fileOpen1" name="fileOpen1">
    </form>
</div>
<div id="link_div" style="display: none;">
    <a id="docLink1" >link</a>
</div>
<script type="text/javascript" src="js/tab.js"></script>
<script type="text/javascript" src="js/secondDialog.js"></script>
</body>
</html>
<script>
    $(document).ready(function () {

        $('[date="date"]').inputmask();       // -------------islemelidi
        $('#stCreateDate').datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: 'dd-mm-yy'
        });
        $('#eDate').datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: 'dd-mm-yy'
        });
    });
    if(partID == 1009 || partID == 1011) {
        $('#exmNameAz').attr('readonly', true);
        $('#exmNameRu').attr('readonly', true);
        $('#exmNameEn').attr('readonly', true);
        $('#exmKeyWordAz').attr('readonly', true);
        $('#exmKeyWordRu').attr('readonly', true);
        $('#exmKeyWordEn').attr('readonly', true);
        $('#textAz').attr('readonly', true);
        $('#textRu').attr('readonly', true);
        $('#textEn').attr('readonly', true);
        $('#noteAz').attr('readonly', true);
        $('#noteRu').attr('readonly', true);
        $('#noteEn').attr('readonly', true);
        $('#OvertakenRegion').prop('disabled', true);
        $('#mete').prop('disabled', true);
        $('#region').multiselect();
        $("#region").multiselect('disable');

        $('#ExmCategory').multiselect();
        $("#ExmCategory").multiselect("disable");

        $('#ExmGroup').multiselect();
        $("#ExmGroup").multiselect("disable");

        $('#ExmClass').multiselect();
        $("#ExmClass").multiselect("disable");

        $('#ExmType').multiselect();
        $("#ExmType").multiselect("disable");

        $('#ExmGenre').multiselect();
        $("#ExmGenre").multiselect("disable");

        $('#cOrganization').multiselect();
        $("#cOrganization").multiselect("disable");

        $('#cCarriesListMulti').multiselect();
        $("#cCarriesListMulti").multiselect("disable");

        $('#yuneskoDomain').multiselect();
        $("#yuneskoDomain").multiselect("disable");
    }
    function loadGroupCombo() {
        $('#OvertakenRegion').getComboContent(10, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
        }, 4);
        $('#mete').getComboContent(10, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
        }, 4);
        $('#region').getComboContent(11, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            $('#region').multiselect({
                noneSelectedText: localeLanguages['191'],
                checkAllText: localeLanguages['212'],
                uncheckAllText: localeLanguages['213'],
                selectedList: 2,
                minWidth: '300',
                click: function (event, ui) {
                    if (ui.checked) {
                        //$('#multiple_dic_speciality option:selected').attr("status","3");
                    } else {
                        //ui.attr("status","2");
                    }
                }
            });
        }, 4);

        $('#cCarriesListMulti').getComboContent('1', '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            $('#cCarriesListMulti').multiselect({
                noneSelectedText: localeLanguages['191'],
                checkAllText: localeLanguages['212'],
                uncheckAllText: localeLanguages['213'],
                selectedList: 2,
                minWidth: '300',
                click: function (event, ui) {
                    if (ui.checked) {
                        //$('#multiple_dic_speciality option:selected').attr("status","3");
                    } else {
                        //ui.attr("status","2");
                    }
                }
            });
        }, 9);
        $('#cOrganization').getComboContent('1', '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            $('#cOrganization').multiselect({
                noneSelectedText: localeLanguages['191'],
                checkAllText: localeLanguages['212'],
                uncheckAllText: localeLanguages['213'],
                selectedList: 2,
                minWidth: '300',
                click: function (event, ui) {
                    if (ui.checked) {
                        //$('#multiple_dic_speciality option:selected').attr("status","3");
                    } else {
                        //ui.attr("status","2");
                    }
                }
            });
        }, 10);
        $('#yuneskoDomain').getComboContent(14, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
            $('#yuneskoDomain').multiselect({
                noneSelectedText: localeLanguages['191'],
                checkAllText: localeLanguages['212'],
                uncheckAllText: localeLanguages['213'],
                selectedList: 2,
                minWidth: '300',
                click: function (event, ui) {
                    if (ui.checked) {
                        //$('#multiple_dic_speciality option:selected').attr("status","3");
                    } else {
                        //ui.attr("status","2");
                    }
                }
            });
        }, 4);

        if (mNewEdit == 1) {
            $("#final_ex").hide();
            $('#ExmCategory').getComboContent(1012, '', '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {
                $('#ExmCategory').multiselect({
                    noneSelectedText: localeLanguages['191'],
                    checkAllText: localeLanguages['212'],
                    uncheckAllText: localeLanguages['213'],
                    selectedList: 2,
                    minWidth: '300',
                    click: function (event, ui) {
                        if (ui.checked) {
                            //$('#multiple_dic_speciality option:selected').attr("status","3");
                        } else {
                            //ui.attr("status","2");
                        }
                    }
                });
                $('#ExmCategory').change();
            }, 7);
        }else if(mNewEdit == 2){
            $("#final_ex").show();
        }

    }
</script>
