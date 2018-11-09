
<%@ page import="javax.jws.soap.SOAPBinding" %>
<%@ page import="domain.usersInfo" %>
<%@ page import="web.constant" %>
<%@ page import="domain.Resource" %>
<%@ page import="java.util.Map" %>
<%--<%@ page import="web.*" %>--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    usersInfo user = ( usersInfo ) session.getAttribute(constant.USER);
    Map<Long , Resource> langMap = ( Map<Long , Resource> ) request.getServletContext().getAttribute(constant.RESOURCES_LOCALE);
    Map<Long , String> constantsMap = ( Map<Long , String> ) request.getServletContext().getAttribute(constant.CONSTANTS);
    long currLang = Long.parseLong(constantsMap.get(1000L));
    if ( user != null  ){
        response.sendRedirect("index.jsp");
        currLang = user.getCurrLangId();
    }
    String errorMessage= (String)request.getSession().getAttribute(constant.MESSAGE);
    if (errorMessage == null){
        errorMessage = "";
    }
%>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>QMMI</title>
    <link rel="stylesheet" type="text/css" href="css/styless.css">
    <script src="plugins/jquery-1.12.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#password").val('');
            $("#username").val('');
        });
        function getPressBT(eventObj){
            var keycode;
            if(eventObj.keyCode) //For IE
                keycode = eventObj.keyCode;
            else if(eventObj.Which)
                keycode = eventObj.Which;  // For FireFox
            else
                keycode = eventObj.charCode;
            if (keycode==13){
                clickBTN();
                return false;
            }
        }
        function clickBTN() {

            $("#lgForm").submit();
        }
    </script>
</head>
<body>
<div class="header">
    <div>
        <img src="img/logo.png" height="45px" style="padding: 10px 0px;">
    </div>
    <div>
        <div  style="margin: 0px auto; width: 312px;">
            <div class="rounded"> Inzibatçı paneli  </div>
        </div>
    </div>
    <div>

    </div>

</div>

<div class="login_container">

    <div class="form_box">
        <form action="login?action=login" method="post" id="lgForm" name="lgForm" onkeydown="return getPressBT(event);">
            <table style=" width:500px; border:2px solid #80a1c0 ; background-color: #fff;" align="center">
                <tbody>

                <tr>
                    <td colspan="5" style="font-family:tahoma, Helvetica, sans-serif; font-size:14px; font-weight:bold; color:#fff; text-align:center " height="62"bgcolor="#80a1c0 " > Sistemə daxili istifadəçilər tərəfindən giriş </td>
                </tr>

                <tr style="height:40px"> <td colspan="5">${sessionScope.message}</td> </tr>

                <tr style="display: none">
                    <td >
                        <select id="sLangList" name="sLangList" style="margin-left:20px;width:150px">
                            <option id="<%=Long.parseLong(constantsMap.get(1000L))%>" value="<%=Long.parseLong(constantsMap.get(1000L))%>"> Azərbaycan dili </option>
                            <option id="<%=Long.parseLong(constantsMap.get(1002L))%>" value="<%=Long.parseLong(constantsMap.get(1002L))%>"> İngilis dili </option>
                            <option id="<%=Long.parseLong(constantsMap.get(1001L))%>" value="<%=Long.parseLong(constantsMap.get(1001L))%>"> Rus dili </option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input id="username" name="username" type="text" placeholder="Istifadəçi" onfocus="this.placeholder = '';" onblur="if (this.placeholder == '') {this.placeholder = 'İstifadəçi';}" class="user"  /></td>

                </tr>
                <tr>
                    <td  height="10px"></td>
                </tr>
                <tr>

                    <td > <input class="password" name="password" id="password" type="password" placeholder="Şifrə" onfocus="this.placeholder = '';" onblur="if (this.placeholder == '') {this.placeholder = 'Şifrə';}" /></td>

                </tr>
                <tr>
                    <td  height="10px"></td>
                </tr>
                <tr>
                    <td ><input class="buttonEnter" type="button" id="btnOK" name="btnOK" value="Daxil ol" onclick="clickBTN();"
                                value="Daxil ol" ></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>

                </tbody>
            </table>
        </form>
    </div>
</div>
</body>
</html>