<%@ page import="domain.usersInfo" %>
<%@ page import="web.constant" %>
<%@ page import="java.util.Map" %>
<%@ page import="utils.Util" %>
<%@ page import="domain.Resource" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
<script type="text/javascript">
  var  localeLanguages = <%=Util.getLocaleTexts(request).toString()%>;
</script>
<head>
  <meta charset="ANSI">
  <title>QMMI</title>
  <link rel="stylesheet" href="plugins/grid/ui.jqgrid.css">
  <link rel="stylesheet" href="plugins/jquery-ui.css">
  <link rel="stylesheet" href="plugins/jquery.treeview.css">
  <link rel="stylesheet" href="plugins/multiselect/jquery.multiselect.css"/>
  <link rel="stylesheet" href="css/font-awesome.min.css">

  <link rel="stylesheet" type="text/css"  href="css/styless.css">

<%--  <script type="text/javascript">
    var a = "ww";
    $(document).ready(function () {
      $.sessionControl('init',{
        timeout : <%=session.getMaxInactiveInterval() + 5%>
      });
    })
  </script>--%>
</head>
<body>
<div id="div_block"  align="center" style="display: none"> <div>Zəhmət olmasa gözləyin...</div></div> <%--<%=Util.getLocaleText(request, 227)%>--%>
<div id="dlgMenu"></div>
<div id="messageMain"></div>
<div id="dictionaryMain"></div>
<div id="delMain"></div>
<div id="advsearchMain"></div>

<div class="header">
  <div>
    <img height="45px" src="img/logo.png" style="padding: 10px 0px;">
  </div>
  <div>
    <div  style="margin: 0px auto; width: 312px;visibility: hidden;">
      <div class="rounded"> Inzibatçı paneli  </div>
    </div>
  </div>
  <div>
    <div class="logout_area" >
      <div>${sessionScope.user.fullName}  </div>
      <a href="logout.jsp" class="exit_a fa fa-power-off"></a>
    </div>
  </div>

</div>

<div class="container">
  <div class="sidebar1 nav" style="position: relative">
    <ul >
      <li><a href="#" id="examples"><%=Util.getLocaleText(request, 101)%></a></li>
      <li style="margin-top: 30px;"><a href="#" id="approvedExamples"><%=Util.getLocaleText(request, 102)%></a></li>
      <li><a href="#" id="unapprovedExamples"><%=Util.getLocaleText(request, 103)%></a></li>


      <li style=" margin-top: 30px;"><a href="#" id="category"><%=Util.getLocaleText(request, 104)%></a></li>
      <li><a href="#" id="carriers"><%=Util.getLocaleText(request, 105)%></a></li>
      <li><a href="#" id="mOrganization"><%=Util.getLocaleText(request, 199)%></a></li>

    </ul>
    <ul style="position: absolute; bottom: 0px; width: calc(100% - 10px);">
      <li style="   margin-top: 30px;"><a href="#" id="advancedSearch"><%=Util.getLocaleText(request, 178)%></a></li>
      <li style="   margin-top: 30px;"><a href="#" id="structure"><%=Util.getLocaleText(request, 106)%></a></li>
      <li><a href="#" id="employees"><%=Util.getLocaleText(request, 107)%></a></li>
      <li><a href="#" id="users"><%=Util.getLocaleText(request, 108)%></a></li>
    </ul>
  </div>
  <div class="content">
    <div class="content-caption">
      <div id="title" class="title"></div>
      <div class="buttons_container">
        <div class="button_block" id="btn_newMain_div">
          <button id="btn_newMain"  class="icon_new" name="btn_newNewAdmin"></button>
          <span class="button-text"><%=Util.getLocaleText(request, 109)%></span>
        </div>
        <div class="button_block" id="btn_editMain_div">
          <button id="btn_editMain" class="icon_edit" name="btn_editAdmin"></button>
          <span class="button-text"><%=Util.getLocaleText(request, 110)%></span>
        </div>
        <div class="button_block" id="btn_delMain_div">
          <button id="btn_delMain" class="icon_del"></button>
          <span class="button-text"><%=Util.getLocaleText(request, 111)%></span>
        </div>
        <div class="button_block" id="btn_viewMain_div">
          <button id="btn_viewMain" class="icon_list"></button>
          <span class="button-text"><%=Util.getLocaleText(request, 173)%></span>
        </div>

        <div class="button_block" id="btn_passport_div">
          <button id="btn_passport" class="icon_passport" style="background-size: 29px auto!important"></button>
          <span class="button-text"><%=Util.getLocaleText(request, 217)%></span>
        </div>
        <div class="button_block" id="btn_pdfMain_div">
          <button id="btn_pdfMain" class="icon_pdf"></button>
          <span class="button-text"><%=Util.getLocaleText(request, 112)%></span>
        </div>

        <div class="button_block" id="btn_advsearchMain_div">
          <button id="btn_advsearchMain" class="icon_search"></button>
          <span class="button-text"><%=Util.getLocaleText(request, 113)%></span>
        </div>
        <div  class="button_block" id="btn_dictionaryMain_div">
          <button  id="btn_dictionaryMain"class="icon_dictionary"></button>
          <span class="button-text soraqca"><%=Util.getLocaleText(request, 114)%></span>
        </div>
      </div>
    </div>
    <div class="content-inner">
      <div class="filter-area">
        <table>
          <tr>
            <td ><label><%=Util.getLocaleText(request, 223)%></label></td>
            <td width="250"> <select id="categoryList" name="categoryList" onchange="loadDependentCombo(8, localeLanguages['191'],'groupList','categoryList',1013,1008)" ></select></td>
            <td><label><%=Util.getLocaleText(request, 117)%></label></td>
            <td width="250"> <select id="groupList" name="groupList" onchange="loadDependentCombo(8, localeLanguages['191'],'classList','categoryList',1014,1008)" ></select></td>
            <td><label><%=Util.getLocaleText(request, 118)%></label></td>
            <td width="250"> <select id="typeList" name="typeList" onchange="loadDependentCombo(8, localeLanguages['191'],'genreList','categoryList',1015,1008)"  ></select></td>
            <td style="display: table-cell !important;"><label><%=Util.getLocaleText(request, 119)%></label></td>
            <td width="250"> <select id="genreList" name="categoryList" onchange="combosChange(0);"></select></td>
            <td><label><%=Util.getLocaleText(request, 180)%></label></td>
            <td> <select id="statusList" name="statusList" onchange="combosChange(0);"></select></td>
            <td><label><%=Util.getLocaleText(request, 116)%></label></td>
            <td width="250"> <select id="classList" name="classList" onchange="loadDependentCombo(8, localeLanguages['191'],'typeList','categoryList',1017,1008)" ></select></td>
          </tr>

        </table>
      </div>
      <div class="center-area">
        <div class="grid-area">
          <table id="grid"></table>
          <div id="pager"></div>
        </div>
        <div id="mainTree" class="tree" style="display: none;">
        </div>
      </div>
      <div class="total-area">
        <div class="margin_padding_5px" id="total"
             style="padding: 10px ! important; margin:0 0px 3px 0 !important; height: 30px;"></div>
      </div>

    </div>
    <div class="sidebar2">
      <div class="rightInfo-area" id="rightPanelInfo"> </div>
      <div class="search-area">
        <div class="search">
   <form name="frm22" id="frm22" method="post" action="cs?action=setData"   onkeydown="return getPress(event);">

            <input name="seacrhText" id="searchFooterTextId"  type="text" size="40" placeholder="" onkeyup="chnText(this.value);" >
            <input class="button_srch" type="button" value="" id="searchSubmit" name="searchSubmit">

        </form>
        </div>
      </div>
    </div>
  </div>
  <div class="clearfix"></div>
</div>
<div class="footer">
</div>
<form name="downloadForm" id="downloadForm" method="post" action="cs?action=downloadFile" style="display: none">
    <input name="filePath" id="filePath"  type="text" >
    <input  type="submit" value="" id="downloadButton" name="downloadButton">
 </form>

<script type="text/javascript" src="plugins/jquery-1.12.1.min.js"></script>

<script type="text/javascript" src="plugins/jquery-ui.js"></script>
<script type="text/javascript" src="plugins/jquery.inputmask-2.x/dist/jquery.inputmask.bundle.min.js"></script>
<script type="text/javascript" src="plugins/jquery.treeview.js" ></script>
<script type="text/javascript" src="plugins/jQuery.Form.js" ></script>
<script type="text/javascript" src="js/commonParts.js"></script>
<script type="text/javascript" src="js/sharedFunctions.js"></script>

<script type="text/javascript" src="plugins/multiselect/jquery.multiselect.min.js"></script>
<script type="text/javascript" src="plugins/multiselect/jquery.multiselect.filter.min.js"></script>
<script type="text/javascript" src="plugins/davidshimjs-qrcodejs-04f46c6/qrcode.js"></script>
<script type="text/javascript" src="js/report.js"></script>

<script type="text/javascript" src="plugins/grid/grid.locale-en.js"></script>
<script type="text/javascript" src="plugins/grid/jquery.jqGrid.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<form method="post" id="downloadSubmit" style="display:none"></form>
</body>

</html>
<%
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
  response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
  response.setDateHeader("Expires", 0); // Proxies.

  String fullName = "";
  long personId = -1;
  usersInfo user = null;
//    Map<Long , Module> moduleMap = ( Map<Long , Module> ) request.getServletContext().getAttribute(constant.MODULS);
  if ( request.getSession().getAttribute(constant.USER) !=null ) {
  user =(usersInfo) request.getSession().getAttribute(constant.USER);
  Map<Long , Resource> langMap = ( Map<Long , Resource> ) request.getServletContext().getAttribute(constant.RESOURCES_LOCALE);

  fullName = user.getFullName();
  personId = user.getPerid();

  response.setHeader(constant.REQUIRES_AUTH, String.valueOf(0));
%>
<%}else {
  response.setHeader(constant.REQUIRES_AUTH, String.valueOf(1));
  response.sendRedirect("login.jsp");

}
%>