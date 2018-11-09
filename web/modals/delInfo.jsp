<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
  <title>Title</title>

</head>
<body>
<div class="white-bg">

  <div  class="container_popup padding-10" >
    <form >
      <table class="table_forums" style="height: 127px">
        <tr>
          <td align="left">
            <form method="post" action="cs1?action=setSession&typ=1" id="frmDelete" name="frmDelete">
              <div style="text-align: center" id="delMessage">  </div>
            </form>
          </td>
        </tr>

      </table>
    </form>
  </div>
</div>
<script type="text/javascript">
  $(document).ready(function(){
    var label="";
    if (localeLanguages['192'] == '1000') {
      label = "Seçilmiş məlumatı silmək istədiyinizə əminsiniz?"
    } else if (localeLanguages['192'] == '1001') {
      label = "Удалить информацию?";
    } else if (localeLanguages['192'] == '1002') {
      label ="Delete information?";
    }
    $("#delMessage").html(label);
  });
</script>
</body>
</html>