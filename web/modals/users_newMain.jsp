<%@ page import="utils.Util" %>
<%--
  Created by IntelliJ IDEA.
  User: a.ulviyya
  Date: 02.07.2016
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>UltEmp</title>
    <script type="text/javascript">
        $(document).ready(function () {
            if (mNewEdit == 1) {

            }
            if (btnL1.length > 0) {
                var g = $("#grid");
                var selID = g.jqGrid('getGridParam', 'selrow');
                var pID = g.jqGrid('getCell', selID, 'empid');
                var prID = g.jqGrid('getCell', selID, 'id');
                var fName = g.jqGrid('getCell', selID, 'fullname');
                $.ajax({
                    type: 'GET',
                    dataType: 'json',
                    url: 'cs?action=loadUserInfo&prID=' + prID,
                    success: function (data) {
                        $.each(data.dsList, function (i, ds) {
                            $("#uName").val(ds.uname);
                            $("#passw").val(ds.pass);
                            if (ds.act == 1) {
                                document.getElementById("chActiv").checked = true;
                            } else {
                                document.getElementById("chActiv").checked = false;
                            }
                            if (ds.usr == 1) {
                                document.getElementById("chAdmin").checked = true;
                            } else {
                                document.getElementById("chAdmin").checked = false;
                            }
                        });
                    },
                    error: function (data) {
                    },
                    complete: function (jqXHR, textStatus) {
                    }
                });
                checkButtons(btnL1);
                $("#sEmpName").append('<option value="' + pID + '">' + fName + '</option>');
            } else {
                $("#uName").val('');
                $("#passw").val('');
                var element = document.getElementById('chActiv');
                element.checked = false;
                element = document.getElementById('chAdmin');
                element.checked = false;
            }
        });
        function selectRow(row) {
            var i = 0;
            var ch = 0;
            var element;
            for (i = 0; i <= 100; i++) {
                element = document.getElementById('ch' + row + i);
                if (typeof(element) != 'undefined' && element != null) {
                    if (element.checked) {
                        ch = 1;
                        break;
                    }
                }
            }

            for (i = 0; i <= 9; i++) {
                element = document.getElementById('ch' + row + i);
                if (typeof(element) != 'undefined' && element != null) {
                    if (ch == 1) {
                        element.checked = false;
                    } else {
                        element.checked = true;
                    }
                }
            }
        }
        ;
        function selectCol(col) {
            var i = 0;
            var ch = 0;
            var element;
            var element1;
            for (i = 20; i <= 30; i++) {
                element = document.getElementById('ch' + i + col);
                if (typeof(element) != 'undefined' && element != null) {
                    if (element.checked) {
                        ch = 1;
                        break;
                    }
                }
            }
            if (col == 100) {
                for (i = 20; i <= 30; i++) {
                    element = document.getElementById('ch' + i + col);
                    if (typeof(element) != 'undefined' && element != null) {
                        if (ch == 1) {
                            for (var r = 20; r <= 30; r++) {
                                for (var c = 0; c <= 9; c++) {
                                    element1 = document.getElementById('ch' + r + c);
                                    if (typeof(element1) != 'undefined' && element1 != null) {
                                        element1.onclick = function () {
                                            return true
                                        };
                                    }
                                }
                            }
                            element.checked = false;
                        } else {
                            for (var r = 20; r <= 30; r++) {
                                for (var c = 0; c <= 9; c++) {
                                    element1 = document.getElementById('ch' + r + c);
                                    if (typeof(element1) != 'undefined' && element1 != null) {
                                        element1.checked = false;
                                        element1.onclick = function () {
                                            return false
                                        };
                                    }
                                }
                            }
                            element.checked = true;
                        }
                    }
                }
            } else {
                for (i = 20; i <= 30; i++) {
                    element = document.getElementById('ch' + i + col);
                    if (typeof(element) != 'undefined' && element != null) {
                        if (ch == 1) {
                            element.checked = false;
                        } else {
                            element.checked = true;
                        }
                    }
                }
            }
            if (col == 7) {
                for (i = 25; i <= 26; i++) {
                    element = document.getElementById('ch' + i + col);
                    var element2 = document.getElementById('ch' + i + 2);
                    element2.checked = false;
                }
            } if (col == 2) {
                for (i = 25; i <= 26; i++) {
                    element = document.getElementById('ch' + i + col);
                    var element2 = document.getElementById('ch' + i + 7);
                    element2.checked = false;
                }
            }

        }
        ;
        function isCheked(col, row,col2) {

            var element = document.getElementById('ch' + row + col);
            var element1 = document.getElementById('ch' + row + col2);
            if (element.checked) {
                element1.checked = false;
            } else {
                element1.checked = true;
            }
        }
    </script>

</head>
<body>
<div class="white-bg">

    <div class="container_popup padding-10">
        <form id="frmUsers" name="frmUsers" action="cs1?action=saveUsers" method="post">
            <table style="width:100%">
                <tr>
                    <td>
                        <fieldset>
                            <table style="width:100%" class="table_forums ">
                                <tr style="height:30px">
                                    <td><label><%=Util.getLocaleText(request, 136)%>
                                    </label></td>
                                    <td width="230"><select id="sEmpName" name="sEmpName"></select></td>
                                    <td width="30"></td>
                                    <td width=""><label><%=Util.getLocaleText(request, 169)%>
                                    </label></td>
                                    <td width="230"><input id="uName" name="uName"></td>
                                    <td width="30"></td>
                                    <td width="" align="right"><label><%=Util.getLocaleText(request, 161)%>
                                    </label></td>
                                    <td width="" align="left"><input type="checkbox" id="chActiv" name="chActiv"></td>
                                </tr>
                                <tr style="height:30px">
                                    <td><label><%=Util.getLocaleText(request, 160)%>
                                    </label></td>
                                    <td><input class="input" type="password" id="passw" name="passw"></td>
                                    <td colspan="4"></td>
                                    <td style="display:none"><label><%=Util.getLocaleText(request, 162)%>
                                    </label></td>
                                    <td style="display:none" align="left"><input type="checkbox" id="chAdmin" name="chAdmin"></td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                </tr>
                <tr>
                    <td>
                        <div class="margin-top-20">

                            <div class="tableScrollHead">
                                <table class="tableZ full-width">

                                    <tr style="height:10px">
                                        <th width="35">
                                            <div class="bgth">
                                                <span class="th"><%=Util.getLocaleText(request, 164)%></span>
                                                <span class="th1"><%=Util.getLocaleText(request, 125)%></span>
                                            </div>
                                        </th>
                                        <th id="o1" onclick="selectCol(1);"><%=Util.getLocaleText(request, 109)%>
                                        </th>
                                        <th id="o2" onclick="selectCol(2);"><%=Util.getLocaleText(request, 110)%>
                                        </th>
                                        <th id="o3" onclick="selectCol(3);"><%=Util.getLocaleText(request, 111)%>
                                        </th>
                                        <th id="o4" onclick="selectCol(7);"><%=Util.getLocaleText(request, 173)%>
                                        </th>
                                        <th id="o5" onclick="selectCol(5);"><%=Util.getLocaleText(request, 114)%>
                                        </th>
                                        <th id="o7" onclick="selectCol(6);"><%=Util.getLocaleText(request, 165)%>
                                        </th>
                                        <th id="o8" onclick="selectCol(8);"><%=Util.getLocaleText(request, 217)%>
                                        </th>
                                        <th id="o9" onclick="selectCol(9);"><%=Util.getLocaleText(request, 113)%>
                                        </th>
                                        <th id="o100" onclick="selectCol(100);">Ümumi baxış</th>
                                    </tr>
                                </table>
                            </div>

                            <div class="tableScrollBody">
                                <table class="tableZ full-width" id="listOfButtons">
                                    <tr>
                                        <td id="p21" onclick="selectRow(21);"><%=Util.getLocaleText(request, 108)%>
                                        </td>
                                        <%--yeni--%>
                                        <td><input type="checkbox" name="ch211" id="ch211" value="1"></td>
                                        <td><input type="checkbox" name="ch212" id="ch212" value="2"></td>
                                        <td><input type="checkbox" name="ch213" id="ch213" value="3"></td>
                                        <td></td>
                                        <td><input type="checkbox" name="ch215" id="ch215" value="5"></td>
                                        <td><input type="checkbox" name="ch216" id="ch216" value="6"></td>
                                        <td></td>
                                        <td></td>
                                        <td><input type="checkbox" name="ch21100" id="ch21100" value="100"></td>
                                    </tr>
                                    <tr>
                                        <td id="p22" onclick="selectRow(22);"><%=Util.getLocaleText(request, 107)%>
                                        </td>
                                        <%--redakte--%>
                                        <td><input type="checkbox" name="ch221" id="ch221" value="1"></td>
                                        <td><input type="checkbox" name="ch222" id="ch222" value="2"></td>
                                        <td><input type="checkbox" name="ch223" id="ch223" value="3"></td>
                                        <td></td>
                                        <td><input type="checkbox" name="ch225" id="ch225" value="5"></td>
                                        <td><input type="checkbox" name="ch226" id="ch226" value="6"></td>
                                        <td><%--<input type="checkbox" name="ch227" id="ch227" value="7">--%></td>
                                        <td><%--<input type="checkbox" name="ch228" id="ch228" value="8">--%></td>
                                        <td><input type="checkbox" name="ch22100" id="ch22100" value="100"></td>
                                    </tr>
                                    <tr>
                                        <td id="p23" onclick="selectRow(23);"><%=Util.getLocaleText(request, 106)%>
                                        </td>
                                        <td><input type="checkbox" name="ch231" id="ch231" value="1"></td>
                                        <td><input type="checkbox" name="ch232" id="ch232" value="2"></td>
                                        <td><input type="checkbox" name="ch233" id="ch233" value="3"></td>
                                        <td></td>
                                        <td><input type="checkbox" name="ch235" id="ch235" value="5"></td>
                                        <td><%--<input type="checkbox" name="ch236" id="ch236" value="6">--%></td>
                                        <td><%--<input type="checkbox" name="ch237" id="ch237" value="7">--%></td>
                                        <td><%--<input type="checkbox" name="ch238" id="ch238" value="8">--%></td>
                                        <td><input type="checkbox" name="ch23100" id="ch23100" value="100"></td>
                                    </tr>

                                    <tr>
                                        <td id="p24" onclick="selectRow(24);"><%=Util.getLocaleText(request, 101)%>
                                        </td>
                                        <td><input type="checkbox" name="ch241" id="ch241" value="1"></td>
                                        <td><input type="checkbox" name="ch242" id="ch242" value="2"></td>
                                        <td><input type="checkbox" name="ch243" id="ch243" value="3"></td>
                                        <td></td>
                                        <td><input type="checkbox" name="ch245" id="ch245" value="5"></td>
                                        <td><input type="checkbox" name="ch246" id="ch246" value="6"></td>
                                        <td><%--<input type="checkbox" name="ch247" id="ch247" value="7">--%></td>
                                        <td><%--<input type="checkbox" name="ch248" id="ch248" value="8">--%></td>
                                        <td><input type="checkbox" name="ch24100" id="ch24100" value="100"></td>
                                    </tr>
                                    <tr>
                                        <td id="p25" onclick="selectRow(25);"><%=Util.getLocaleText(request, 102)%>
                                        </td>
                                        <td><%--<input type="checkbox" name="ch251" id="ch251" value="1">--%></td>
                                        <td><input type="checkbox" name="ch252" id="ch252" value="2" onclick="isCheked(2,25,7)"></td>
                                        <td><%--<input type="checkbox" name="ch253" id="ch253" value="3">--%></td>
                                        <td><input type="checkbox" name="ch257" id="ch257" value="7"
                                                   onclick="isCheked(7,25,2);"></td>
                                        <td><input type="checkbox" name="ch255" id="ch255" value="5"></td>
                                        <td><input type="checkbox" name="ch256" id="ch256" value="6"></td>
                                        <td><input type="checkbox" name="ch258" id="ch258" value="8"></td>
                                        <td><%--<input type="checkbox" name="ch258" id="ch258" value="8">--%></td>
                                        <td><input type="checkbox" name="ch25100" id="ch25100" value="100"></td>
                                    </tr>
                                    <tr>
                                        <td id="p26" onclick="selectRow(26);"><%=Util.getLocaleText(request, 103)%>
                                        </td>
                                        <td><%--<input type="checkbox" name="ch261" id="ch261" value="1">--%></td>
                                        <td><input type="checkbox" name="ch262" id="ch262" value="2" onclick="isCheked(2,26,7)"></td>
                                        <td><%--<input type="checkbox" name="ch263" id="ch263" value="3">--%></td>
                                        <td><input type="checkbox" name="ch267" id="ch267" value="7" onclick="isCheked(7,26,2)"></td>
                                        <td><input type="checkbox" name="ch265" id="ch265" value="5"></td>
                                        <td><input type="checkbox" name="ch266" id="ch266" value="6"></td>
                                        <td></td>
                                        <td><%--<input type="checkbox" name="ch268" id="ch268" value="8">--%></td>
                                        <td><input type="checkbox" name="ch26100" id="ch26100" value="100"></td>
                                    </tr>
                                    <tr>
                                        <td id="p27" onclick="selectRow(27);"><%=Util.getLocaleText(request, 104)%>
                                        </td>
                                        <td><input type="checkbox" name="ch271" id="ch271" value="1"></td>
                                        <td><input type="checkbox" name="ch272" id="ch272" value="2"></td>
                                        <td><input type="checkbox" name="ch273" id="ch273" value="3"></td>
                                        <td></td>
                                        <td><input type="checkbox" name="ch275" id="ch275" value="5"></td>
                                        <td><input type="checkbox" name="ch276" id="ch276" value="6"></td>
                                        <td><%--<input type="checkbox" name="ch277" id="ch277" value="7">--%></td>
                                        <td><%--<input type="checkbox" name="ch278" id="ch278" value="8">--%></td>
                                        <td><input type="checkbox" name="ch27100" id="ch27100" value="100"></td>
                                    </tr>
                                    <tr>
                                        <td id="p28" onclick="selectRow(28);"><%=Util.getLocaleText(request, 105)%>
                                        </td>
                                        <td><input type="checkbox" name="ch281" id="ch281" value="1"></td>
                                        <td><input type="checkbox" name="ch282" id="ch282" value="2"></td>
                                        <td><input type="checkbox" name="ch283" id="ch283" value="3"></td>
                                        <td></td>
                                        <td><input type="checkbox" name="ch285" id="ch285" value="5"></td>
                                        <td><input type="checkbox" name="ch286" id="ch286" value="6"></td>
                                        <td><%--<input type="checkbox" name="ch287" id="ch287" value="7">--%></td>
                                        <td><%--<input type="checkbox" name="ch288" id="ch288" value="8">--%></td>
                                        <td><input type="checkbox" name="ch28100" id="ch28100" value="100"></td>
                                    </tr>
                                    <tr>
                                        <td id="p29" onclick="selectRow(29);"><%=Util.getLocaleText(request, 199)%>
                                        </td>
                                        <td><input type="checkbox" name="ch291" id="ch291" value="1"></td>
                                        <td><input type="checkbox" name="ch292" id="ch292" value="2"></td>
                                        <td><input type="checkbox" name="ch293" id="ch293" value="3"></td>
                                        <td></td>
                                        <td><input type="checkbox" name="ch295" id="ch295" value="5"></td>
                                        <td><input type="checkbox" name="ch296" id="ch296" value="6"></td>
                                        <td><%--<input type="checkbox" name="ch297" id="ch297" value="7">--%></td>
                                        <td><%--<input type="checkbox" name="ch298" id="ch298" value="8">--%></td>
                                        <td><input type="checkbox" name="ch29100" id="ch29100" value="100"></td>
                                    </tr>
                                    <tr>
                                        <td id="p30" onclick="selectRow(30);"><%=Util.getLocaleText(request, 178)%>
                                        </td>
                                        <td><%--<input type="checkbox" name="ch301" id="ch301" value="1">--%></td>
                                        <td><%--<input type="checkbox" name="ch302" id="ch302" value="2">--%></td>
                                        <td><%--<input type="checkbox" name="ch303" id="ch303" value="3">--%></td>
                                        <td></td>
                                        <td><%--<input type="checkbox" name="ch305" id="ch305" value="5">--%></td>
                                        <td><%--<input type="checkbox" name="ch306" id="ch306" value="6">--%></td>
                                        <td><%--<input type="checkbox" name="ch307" id="ch307" value="7">--%></td>
                                        <td><input type="checkbox" name="ch309" id="ch309" value="9"></td>
                                        <td><input type="checkbox" name="ch30100" id="ch30100" value="100"></td>
                                    </tr>
                                </table>
                            </div>

                        </div>
                    </td>
                </tr>
            </table>
        </form>

    </div>
</div>
<script type="text/javascript" src="js/popup.js"></script>
</body>
</html>


