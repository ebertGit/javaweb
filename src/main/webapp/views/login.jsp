<html>
<head>
  <title>login</title>
  <link href="<%= request.getContextPath() %>/static/common.css" rel="stylesheet">
  <style>
    .login-item {
      margin: 30px 0;
    }
    .signUp-text {
      color: rgb(177, 177, 177);
      font-size: small;
    }
    .signUp-text a {
      text-decoration: none;
    }
    .signUp-text a:visited {
      color: rgb(177, 177, 177);
    }
  </style>
</head>
<body class="center">
<div>
  <div style="background-color: green;">
    <h1>Login form</h1>
  </div>
  <div class="main-panel">
    <p class="panel-title" align="center">Login</p>
    <form action="doLogin" method="post">
      <div class="login-item"><input type="text" name="username" placeholder="User Name" class="input-text" /></div>
      <div class="login-item"><input type="password" name="password" placeholder="Password" class="input-password" /></div>
      <div style="margin-top: 50px;"><input type="submit" value="Login" class="submit" /></div>
    </form>
    <div class="signUp-text"><a href="<%= request.getContextPath() %>/hello/register">Sign up</a></div>
  </div>
</div>
</body>
</html>
