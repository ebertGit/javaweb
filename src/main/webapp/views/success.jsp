<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>

<% String msg = (String)request.getAttribute("msg"); %>
<% String userName = (String)request.getAttribute("userName"); %>
<% String homeLink = request.getContextPath() + request.getServletPath(); %>

<html>
<head>
  <title>Login-success</title>
  <link href="<%= request.getContextPath() %>/static/common.css" rel="stylesheet">
  <style>
    .header {
      position: fixed;
      top: 0;
      width: 100%;
      height: 35px;
      text-align: left;
      padding: 12px 24px;
    }
    .home-link {
      display: inline-block;
      text-decoration: none;
    }
    .home-link:visited {
      color: black;
    }
    .site-name {
      font-size: 1.3rem;
      font-weight: 600;
      color: black;
    }
    .category {
      float: left;
      margin-top: -9px;
      width: 280px;
      box-sizing: border-box;
      border: 1px solid #bdbdbd;
      background-color: white;
    }
    .content {
      margin: 68px 259px 0px 289px;
      border: 1px solid #bdbdbd;
      background-color: white;
    }
    .toc-box-outer {
      float: right;
      width: 250px;
      box-sizing: border-box;
      border: 1px solid #bdbdbd;
      background-color: white;
    }
  </style>
</head>
<body class="center">
  <div class="header">
    <a class="home-link" href="<%= homeLink %>"><span class="site-name">Work Memo</span></a>
  </div>
  <div class="category">
    <ul style="padding: 24px 0;">
      <li>
        <section>
          <p>category title</p>
          <ul>
            <li>memo-1</li>
            <li>memo-1</li>
          </ul>
        </section>
      </li>
      <li>
        <section>
          <p>category title</p>
          <ul>
            <li>memo-1</li>
            <li>memo-1</li>
          </ul>
        </section>
      </li>
    <ul>
    <span>category</span>
  </div><!-- end category. -->

  <!-- table of contents -->
  <div class="toc-box-outer">
    <div style="font-weight:bold;text-align:center;">content title</div>
    <hr>
    <div class="toc-box-inner">
      <ul>
        <li>
          <a href="#">paragraph title</a>
          <ul>
            <li>sub title 1</li>
            <li>sub title 2</li>
          </ul>
        </li>
      </ul>
    </div>
  </div>

  <div class="content">
    <h1>success.jsp</h1>
    <% out.println(new Date()); %>
    <hr>
    <div>
      <h2>Hi, <%= userName %></h2>
      <h2><%= msg %>
    </div>
  </div><!-- end content. -->
  <div style="clear:both;"></div>
</body>
</html>