
<%@page import="web.constant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log out</title>
    </head>
    <body>
        <%
            session.removeAttribute(constant.USER);
            session.invalidate();
            response.sendRedirect(request.getContextPath()+"/"+constant.PAGE_LOGIN);
        %>
    </body>
</html>