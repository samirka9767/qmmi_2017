<%@ page import="utils.Util" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Title</title>

</head>
<body>
<div class="white-bg">

    <div class="container_popup padding-10">
        <form>
            <table class="table_forums">
                <tr>
                    <td align="left" height="40"><%=Util.getLocaleText(request, 136)%>
                    </td>
                    <td align="left">
                        <input type="text" id="_nameAZ" name="_nameAZ" placeholder="AZ">
                    </td>
                    <td align="left">
                        <input type="text" id="_nameRU" name="_nameRU" placeholder="RU" style="display: none">
                    </td>
                    <td align="left">
                        <input type="text" id="_nameEN" name="_nameEN" placeholder="EN" style="display: none">
                    </td>
                </tr>
                <tr>
                    <td height="40"><%=Util.getLocaleText(request, 137)%>
                    </td>
                    <td align="left"><input id="_descriptionAZ" type="text" placeholder="AZ"></td>
                    <td align="left"><input id="_descriptionRU" type="text" placeholder="RU" style="display: none"></td>
                    <td align="left"><input id="_descriptionEN" type="text" placeholder="EN" style="display: none"></td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>