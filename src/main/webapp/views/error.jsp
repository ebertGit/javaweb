<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>

<% String msg = (String)request.getAttribute("msg"); %>

<html>
<head>
  <title>Login-error</title>
  <link href="<%= request.getContextPath() %>/static/common.css" rel="stylesheet">
</head>
<body class="center">
  <h1>error.jsp</h1>
  <% out.println(new Date()); %>
  <hr>
  <div style="background-color: red;">
    <h2><%= msg %>
  </div>
</body>
</html>
