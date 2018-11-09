<%@ page import="java.util.List" %>
<%@ page import="domain.Row" %>
<%@ page import="web.constant"%>
<%@ page import="domain.Column"%>
<%@ page import="org.json.JSONObject"%>
<%@ page import="org.json.JSONArray"%>
<%@ page contentType="application/json; charset=UTF-8" language="java" %>
<%
    List<Row> contentList = (List<Row>) request.getAttribute(constant.COMBO_CONTENT);
    JSONObject content = new JSONObject();
    JSONArray rows = new JSONArray();
    for ( Row row : contentList ) {
        JSONObject r = new JSONObject();
        for(Column gp: row.getColumns()){
            r.put(gp.getKey().toLowerCase(), (String)gp.getValue());
        }
        rows.put(r);
    }
    content.put("options", rows );
%>
<%=content.toString()%>