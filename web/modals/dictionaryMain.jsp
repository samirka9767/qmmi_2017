<%@ page import="utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

</head>
<body>
<div id="dictionaryDel_dialog"></div>
<div id="themeDictionary_new"></div>
<div id="positionTypeDictionary_new"></div>
<div id="positionDictionary_new"></div>
<div id="defaultDictionary_new"></div>
<div class="container_popup">
    <div class=" popup_with_menu margin-top-5">
        <table class="full-width">
            <tr>
                <td width="170">
                    <div class="toLeft">
                        <ul class="nav nav_tab" id="structureTab">
                            <li><a id="orgTypeDictionary" attr_dicPartId="2" class="selected_link"
                                   href="#"><%=Util.getLocaleText(request, 175)%>
                            </a>
                            </li>
                            <li><a id="positionDictionary" attr_dicPartId="10014" class="selected_link"
                                   href="#"><%=Util.getLocaleText(request, 218)%>
                            </a></li>
                            <li><a id="positiontypeDictionary" attr_dicPartId="5" class="selected_link"
                                   href="#"><%=Util.getLocaleText(request, 176)%>
                            </a></li>
                            <%--<li><a id="categoryTypeDictionary" attr_dicPartId="7" class="selected_link"
                                   href="#"><%=Util.getLocaleText(request, 177)%>
                            </a></li>--%>
                            <li><a id="contactDictionary" attr_dicPartId="11" class="selected_link"
                                   href="#"><%=Util.getLocaleText(request, 130)%>
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

                                        <div id="buttons" class="buttons_container buttons_container_popup">
                                            <div class="button_block">
                                                <button id="btn_dictionaryNew" attr_dic_newedit="1" class="icon_new"
                                                        name="btn_dictionaryNew" data-name="new"></button>
                                                <span class="button-text"><%=Util.getLocaleText(request, 109)%></span>
                                            </div>
                                            <div class="button_block">
                                                <button id="btn_dictionaryEdit" attr_dic_newedit="2" class="icon_edit"
                                                        attr_dic_newedit="2"
                                                        name="btn_dictionaryEdit" data-name="edit"></button>
                                                <span class="button-text"><%=Util.getLocaleText(request, 110)%></span>
                                            </div>
                                            <div class="button_block">
                                                <button id="btn_dictionaryDel" attr_dic_newedit="3" class="icon_del"
                                                        name="btn_dictionaryDel" data-name="del"></button>
                                                <span class="button-text"><%=Util.getLocaleText(request, 111)%></span>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="tab_content">
                            <div name="grid_area" class="grid_area" style="height:400px;">
                                <table id="dictionaryMain_grid"></table>
                                <div id="dictionaryMain_pager"></div>
                            </div>
                        </div>
                    </div>

                </td>
            </tr>
        </table>
    </div>
</div>

<script type="text/javascript" src="js/pDictionary.js"></script>
<script type="text/javascript" src="js/secondDict_for_dictionary.js"></script>
</body>
</html>