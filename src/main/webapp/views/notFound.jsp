<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>

<% String msg = (String)request.getAttribute("msg"); %>

<html>
<head>
  <title>Not Found</title>
  <link href="<%= request.getContextPath() %>/static/common.css" rel="stylesheet">
</head>
<body class="center">
  <h1>notFound.jsp</h1>
  <hr>
  <div style="background-color: red;">
    <h2>Page [<%= msg %>] was not found.</h2>
  </div>
</body>
</html>
