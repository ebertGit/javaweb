<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>

<% String msg = (String)request.getAttribute("msg"); %>
<% String userName = (String)request.getAttribute("userName"); %>

<html>
<head>
  <title>Login-success</title>
  <link href="<%= request.getContextPath() %>/static/common.css" rel="stylesheet">
</head>
<body class="center">
  <h1>success.jsp</h1>
  <% out.println(new Date()); %>
  <hr>
  <div>
    <h2>Hi, <%= userName %></h2>
    <h2><%= msg %>
  </div>
</body>
</html>