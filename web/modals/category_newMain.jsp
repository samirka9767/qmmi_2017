<%@ page import="utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Title</title>

</head>
<body>
<div  class="white-bg">

  <div  class="container_popup padding-10">
    <form id="categoriesForm" name="categoriesForm" action="cs1?action=saveStrukturInfo"  method="post">
    <table style="width:100%" class="table_forums">
      <tr  height="35">
        <td><label><%=Util.getLocaleText(request, 146)%></label></td>
        <td colspan="3"><select id="categoryType" name="categoryType"></select></td>
      </tr>
      <tr  height="35">
        <td><label><%=Util.getLocaleText(request, 226)%></label></td>
        <td colspan="3"><input id="regCodePart" name="regCodePart"></td>
      </tr>
      <tr  height="35">
        <td width="20"><label><%=Util.getLocaleText(request, 136)%></label></td>
        <td><input type="text" id="categorytName" name="categorytName" placeholder="AZ"></td>
        <td><input type="text" id="categorytNameRu" name="categorytNameRu" placeholder="RU"></td>
        <td><input type="text" id="categorytNameEn" name="categorytNameEn" placeholder="EN"></td>

      </tr>

      <tr  height="35" style="display: none;">
        <td width="70"><label><%=Util.getLocaleText(request, 137)%></label></td>
        <td><input  type="text" id="categoryDescription" name="categoryDescription" placeholder="AZ"></td>
        <td><input  type="text" id="categoryDescriptionRu" name="categoryDescriptionRu" placeholder="RU"></td>
        <td><input  type="text" id="categoryDescriptionEn" name="categoryDescriptionEn" placeholder="EN"></td>

      </tr>


    </table>
    </form>
  </div>
</div>

</body>
</html>
