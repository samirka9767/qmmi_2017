<%@ page import="utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Title</title>

</head>
<body>
<div class="white-bg">

    <div class="container_popup padding-10">
        <form name="finalOperation" id="finalOperation" action="cs1?action=updateStatus" method="post"
              onkeydown="return getPress(event);">
            <table class="table_forums">
                <tr>
                    <td align="left" height="40"><%=Util.getLocaleText(request, 180)%>
                    </td>
                    <td align="left">
                        <select id="status" name="statusID"></select>
                    </td>
                </tr>
                <tr>
                    <td height="40"><%=Util.getLocaleText(request, 145)%>
                    </td>
                    <td align="left"><input type="text" name="reson" id="reson"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        var parametr4;
        if (partID == 1008) {
            parametr4 = "1041";
        } else {
            parametr4 = "1037,1038"
        }
        $("#status").getComboContent(parametr4, localeLanguages['191'], '&param1=' + localeLanguages['192'] + '&param2=-1&param3=-1', function () {

        }, 11);
    });
</script>
</body>
</html>