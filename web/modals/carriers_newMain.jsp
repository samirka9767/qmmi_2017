<%@ page import="utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Title</title>

</head>
<body>
<div id="employeeDel_dialog"></div>
<div id="choosePhoto1_dialog"></div>
<div class="container_popup">

  <div>
    <form id="frmCarriers" name="frmCarriers" action="cs1?action=saveCarriersInfo" method="post">
      <table class="table_forums top-table">
        <tbody>
        <tr>
          <td width="80"><%=Util.getLocaleText(request, 198)%></td>
          <td width="100"><input id="cNameAz" name="cNameAz" placeholder="AZ"></td>
          <td width="100"><input id="cNameRu" name="cNameRu" placeholder="RU"></td>
          <td width="100"><input id="cNameEn" name="cNameEn" placeholder="EN"></td>

          <td width="56"></td>

          <td width="105" rowspan="3" valign="top">
            <div class="divChoosePhoto">
              <div id="divImage1">
                <img width="100%" height="auto" src="img/profile-picture.jpg" id="personImage2">
              </div>
              <button id="btnPhotoCarOrg_choosePhoto1" class="btn_choosePhoto"
                      data-title-popup="Photo"></button>
            </div>
          </td>
        </tr>
        <tr>
          <td><%=Util.getLocaleText(request, 174)%></td>
          <td><textarea id="CTextAz" name="CTextAz" placeholder="AZ"></textarea></td>
          <td><textarea id="CTextRu" name="CTextRu" placeholder="RU"></textarea></td>
          <td><textarea id="CTextEn" name="CTextEn" placeholder="EN"></textarea></td>

          <td></td>

        </tr>

        </tbody>
      </table>
    </form>
  </div>

</div>
<script type="text/javascript" src="js/tab.js"></script>
<script type="text/javascript" src="js/secondDialog.js"></script>
<script>

  $(document).ready(function() {
    function tableZ() {
      $('.tableScrollBody>table').width($('.tableScrollBody').width());

    }
    setTimeout(tableZ, 100);
  });

  if (mNewEdit == 1) {
    $("#eduEmployee").click();
    $("#infoEmployee").parent().hide();
    $("#trainingEmployee").parent().hide();
    $("#examEmployee").parent().hide();
    $("#eduEmployee").click();

  }

</script>
</body>
</html>
