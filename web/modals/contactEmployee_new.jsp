<%@ page import="utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en"><head>
  <title>Title</title>

</head>
<body>
<div class="white-bg">

  <div  class="container_popup padding-10">
    <form notEnter="notEnter" name="frmContact" id="frmContact" action="cs1?action=setSession&typ=1" method="post"  onkeydown="return getPress1(event);">
      <table class="table_forums">
        <tr>
          <td align="left"  height="40"><%=Util.getLocaleText(request, 159)%></td>
          <td align="left">
            <select  id="contList" name="contList" ></select>
          </td>
        </tr>
        <tr>
          <td height="40" ><%=Util.getLocaleText(request, 150)%> </td>
          <td align="left"> <input type="text"  name="contVal" id="contVal" ></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<script type="text/javascript" src="js/sharedFunctions.js"></script>
<script type="text/javascript">
  function getPress1(eventObj) {
    var keycode;
    if (eventObj.keyCode) { //For IE
      keycode = eventObj.keyCode;
    } else if (eventObj.Which) {
      keycode = eventObj.Which;  // For FireFox
    } else keycode = eventObj.charCode;
    if (keycode == 13) { //if the key is the enter
      $('#frmContact').click();
      return false;
    }
  }
</script>
</body>
</html>