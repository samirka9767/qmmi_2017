<%--
  Created by IntelliJ IDEA.
  User: a.ulviyya
  Date: 02.07.2016
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Title</title>

</head>
<body>
<div class="white-bg">

    <div class="container_popup padding-10">

        <table class="table_forums" style="width: 80%; margin: 0px auto;">
            <tr>
                <td align="left" height="40" style="text-align: center">
                    <img width="180" height="210" id="Photo1" src="img/profile-picture.jpg">
                </td>

            </tr>
            <tr>
                <td height="40">
                    <div class=" fileform">
                        <div id="fileformlabel"></div>
                        <div class="selectbutton">Upload</div>
                        <input type="file" onchange="getName(this.value);" id="upload" name="upload">
                    </div>
                </td>
            </tr>
        </table>

    </div>
</div>

<script>
    function getName(str) {
        if (str.lastIndexOf('\\')) {
            var i = str.lastIndexOf('\\') + 1;
        }
        else {
            var i = str.lastIndexOf('/') + 1;
        }
        var filename = str.slice(i);
        var uploaded = document.getElementById("fileformlabel");
        uploaded.innerHTML = filename;
    }
</script>
</body>
</html>
