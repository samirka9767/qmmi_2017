<%@ page import="utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<body>
<div id="employeeDel_dialog"></div>
<div id="addOrg_dialog"></div>
<div id="choosePhoto_dialog"></div>
<div id="employeeNew_dialog"></div>
<div id="eduEmployee_new"></div>
<div id="contactEmployee_new"></div>
<div id="experienceEmployee_new"></div>
<div class="container_popup">

    <div>
        <form id="frmEmployees" name="frmEmployee" action="cs1?action=saveEmployeeInfo" method="POST">
            <table class="table_forums top-table">
                <tbody>
                <tr>
                    <td width="80"><%=Util.getLocaleText(request, 152)%>
                    </td>
                    <td width="280"><input id="fname" name="fname"></td>

                    <td width="56"></td>
                    <td><%=Util.getLocaleText(request, 153)%>
                    </td>
                    <td width="280">
                        <select id="empOrganization" name="empOrganization"
                                onchange="loadDependentCombo(5,'','empPosition','empOrganization','',1001)"></select>
                    </td>
                    <td width="105" rowspan="3" valign="top">
                        <div class="divChoosePhoto">
                            <div id="divImage">
                                <img width="100%" height="auto" src="img/profile-picture.jpg" id="personImage1">
                            </div>
                            <button id="btnPhotoEmp_choosePhoto" class="btn_choosePhoto" role="button"
                                    data-title-popup="Photo"></button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><%=Util.getLocaleText(request, 136)%>
                    </td>
                    <td><input id="lname" name="lname"></td>

                    <td></td>
                    <td><%=Util.getLocaleText(request, 154)%>
                    </td>
                    <td><select id="empPosition" name="empPosition"></select></td>

                </tr>
                <tr>
                    <td><%=Util.getLocaleText(request, 151)%>
                    </td>
                    <td><input id="mName" name="mName"></td>

                    <td></td>
                    <td><%=Util.getLocaleText(request, 127)%>
                    </td>
                    <td>
                        <input id="hireDate2" name="hireDate" style="min-width: auto; width: 118px ! important;">
                        <input id="fireDate" name="fireDate"
                               style="min-width: auto; width: 118px ! important; margin-left: 5px;" <%--data-inputmask="'alias': 'dd-mm-yyyy'"--%>>
                    </td>

                </tr>

                </tbody>
            </table>
        </form>
    </div>
    <div class=" popup_with_menu margin-top-5">
        <table class="full-width">
            <tr>
                <td width="170">
                    <div class="toLeft" style="min-height: 370px;">
                        <ul class="nav nav_tab" id="employeeTab">
                            <li><a id="gInfoEmployee" class="selected_link"
                                   href="#"><%=Util.getLocaleText(request, 158)%>
                            </a></li>
                            <li><a id="contactEmployee" attr_partid="3012"><%=Util.getLocaleText(request, 150)%>
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

                                    <td align="right" id="td_schButtons">

                                        <div class="buttons_container buttons_container_popup" style="display: block">
                                            <div class="button_block">
                                                <button id="btn_employeeNew" class="icon_new" name="btn_employeeNew"
                                                        data-name="new" attr_newedit="1"></button>
                                                <span class="button-text"><%=Util.getLocaleText(request, 109)%></span>
                                            </div>
                                            <div class="button_block">
                                                <button id="btnEdit_employeeNew" class="icon_edit"
                                                        name="btnEdit_employeeNew"
                                                        data-name="new" attr_newedit="2"></button>
                                                <span class="button-text"><%=Util.getLocaleText(request, 110)%></span>
                                            </div>
                                            <div class="button_block">
                                                <button id="BtnDel_employeeDel" class="icon_del" name="btn_employeeDel"
                                                        data-name="del" attr_newedit="3"></button>
                                                <span class="button-text"><%=Util.getLocaleText(request, 111)%></span>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="tab_content">
                            <div name="gInfoEmployee" style="display: none">
                                <table class="table_forums">
                                    <tr>
                                        <td><label><%=Util.getLocaleText(request, 155)%>
                                        </label></td>
                                        <td width="200"><input id="pinCode" name="pinCode"></td>
                                        <td width="78"></td>
                                        <td><label><%=Util.getLocaleText(request, 157)%>
                                        </label></td>
                                        <td width="200"><select id="gender" name="gender"></select></td>
                                    </tr>
                                    <tr>
                                        <td><label><%=Util.getLocaleText(request, 156)%>
                                        </label></td>
                                        <td><input id="empBirthday" name="empBirthday"></td>
                                        <td colspan="3"></td>

                                    </tr>


                                </table>
                            </div>
                            <div name="contactEmployee"
                                 style="display: none">
                                <div name="grid_area" class="grid_area" style="height:300px;">
                                    <table id="employees_newMain_grid"></table>
                                    <div id="employees_newMain_pager"></div>
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
<script>

    $(document).ready(function () {
        $("#gInfoEmployee").click();
        function tableZ() {
            $('.tableScrollBody>table').width($('.tableScrollBody').width());

        }

        setTimeout(tableZ, 100);


        $('[date="date"]').inputmask();


        $('#empBirthday').datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: '1950:' + new Date().getFullYear().toString(),
            dateFormat: 'dd-mm-yy'
        });

        $('#hireDate2').datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: '1950:' + new Date().getFullYear().toString(),
            dateFormat: 'dd-mm-yy'
        });

        $('#fireDate').datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: '1950:' + new Date().getFullYear().toString(),
            dateFormat: 'dd-mm-yy'
        });



    });


    $('#hireDate2').attr("placeholder", localeLanguages['196']);
    $('#fireDate').attr("placeholder", localeLanguages['197']);

</script>
</body>
</html>
