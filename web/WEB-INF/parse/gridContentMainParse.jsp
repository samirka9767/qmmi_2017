<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>

<%@ page import="web.constant" %>
<%@ page import="domain.*" %>
<%@ page contentType="application/json; charset=UTF-8" language="java" %>
<%
    Result contentList = (Result) request.getAttribute(constant.GRID_CONTENT);
    JSONObject content = new JSONObject();
    content.put("page", contentList.getPageNumber());
    content.put("total", contentList.getTotalPageCount());
    content.put("records", contentList.getRecordCount());
    JSONArray rows = new JSONArray();
    for (Row row : contentList.getRows()) {
        int i=0;
        JSONObject r = new JSONObject();
        for(Column gp: row.getColumns()){
            r.put(gp.getKey().toLowerCase(), gp.getValue());
        }
        rows.put(r);
    }
    content.put("rows", rows );
%>
    <%=content.toString()%>
