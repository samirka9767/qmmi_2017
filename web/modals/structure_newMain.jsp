<%@ page import="utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script  type="text/javascript">
        $(document).ready(function() {
            $('[date="date"]').inputmask();       // -------------islemelidi
            $('#stCreateDate').datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'dd-mm-yy'
            });
        });
    </script>

</head>
<body>
<div id="contactStructure_new"></div>
<div id="contactStructure_edit"></div>
<div id="addressStructure_new"></div>
<div id="structureDel_dialog"></div>
<div  class="container_popup">
    <div class=" popup_with_menu margin-top-5">
        <table  class="full-width" >
            <tr>
                <td width="170" >
                    <div class="toLeft">
                        <ul  class="nav nav_tab" id="structureTab">
                            <li><a id="generalInfoStructure" class="selected_link" href="#" attr_partid="50001"><%=Util.getLocaleText(request, 120)%></a></li>
<%--
                            <li><a id="addressStructure" href="#"><%=Util.getLocaleText(request, 149)%></a></li>
--%>
                            <li><a id="contactStructure" href="#" attr_partid="3012"><%=Util.getLocaleText(request, 150)%></a></li>
                        </ul>
                    </div>
                </td>
                <td >
                    <div class="tab_container" >
                        <div class="tablebutton">

                            <table class="full-width">
                                <tr>
                                    <td class="tetx_td2"><span class="title-tab"></span></td>

                                    <td align="right" id="td_schButtons">

                                        <div class="buttons_container buttons_container_popup">
                                            <div class="button_block">
                                                <button id="btn_structureNew"  class="icon_new" name="btn_structureNew" data-name="new" attr_newedit="1"></button>
                                                <span class="button-text" attr_newedit="1"><%=Util.getLocaleText(request, 109)%></span>
                                            </div>
                                            <div class="button_block">
                                                <button id="btn_structureEdit" class="icon_edit" name="btn_structureEdit" data-name="edit" attr_newedit="2"></button>
                                                <span class="button-text" attr_newedit="2"><%=Util.getLocaleText(request, 110)%></span>
                                            </div>
                                            <div class="button_block">
                                                <button id="btn_structureDel" class="icon_del" name="btn_structureDel" data-name="del" attr_newedit="3"></button>
                                                <span class="button-text" attr_newedit="3"><%=Util.getLocaleText(request, 111)%></span>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="tab_content">
                            <form notEnter="notEnter" name="frm_struktur" id="frm_struktur"
                                  action="cs1?action=saveStrukturInfo" method="POST">
                                <div name="generalInfoStructure" style="display: none">
                                    <table class="table_forums" style="width:100%">
                                        <tr>
                                            <td id="strTip" width="110"><label><%=Util.getLocaleText(request, 146)%></label></td>
                                            <td class="az"><select id="sOrgTypeID" name="sOrgTypeID"><option value="-1"></option></select></td>
                                        </tr>

                                        <tr>
                                            <td id="strName"><label><%=Util.getLocaleText(request, 136)%></label></td>
                                            <td id="name" class="az"><input id="inOrgName" name="inOrgName"></td>
                                        </tr>

                                        <tr>
                                            <td id="strCreateDate"><label><%=Util.getLocaleText(request, 147)%></label></td>
                                            <td style="width: 578px;"><input id="inOrgDate" name="inOrgDate"   date="date"  data-inputmask="'alias': 'dd-mm-yyyy'"></td>
                                        </tr>

                                        <tr>
                                            <td id="strAddress"><label><%=Util.getLocaleText(request, 149)%></label></td>
                                            <td style="width: 578px;"><input id="inOrgAddress" name="inOrgAddress"  ></td>
                                        </tr>
                                        <tr>
                                            <td style="vertical-align:top"><label><%=Util.getLocaleText(request, 133)%> </label></td>
                                            <td><textarea style="height:200px;" id="inOrgNote" name="inOrgNote"></textarea>
                                            </td>
                                        </tr>

                                    </table>
                                </div>
                            </form>
                            <div name="contactStructure"  style="display: none">
                                <div name="grid_area"  class="grid_area" style="height:400px;">
                                    <table  id="structure_newMain_grid"></table>
                                    <div id="structure_newMain_pager"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </td>
            </tr>
        </table>
    </div>
</div>

<script type="text/javascript" src="js/tab.js"></script>
<script type="text/javascript" src="js/secondDialog.js"></script>
</body>
</html>