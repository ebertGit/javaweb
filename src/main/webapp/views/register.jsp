<html>
<head>
  <title>Register</title>
  <link href="<%= request.getContextPath() %>/static/common.css" rel="stylesheet">
  <style>
    .main-panel {
      height: 430px;
    }
    .register-item {
      margin: 25px 0;
    }
  </style>
</head>
<body class="center">
  <div style="background-color: pink;">
    <h1>Register form</h1>
  </div>
  <div class="main-panel">
    <p class="panel-title" align="center">Sign Up</p>
      <form action="doRegistration" method="post">
        <div class="register-item"><input type="text" name="username" placeholder="User Name" class="input-text" /></div>
        <div class="register-item"><input type="password" name="password" placeholder="Password" class="input-password" /></div>
        <div class="register-item"><input type="password" name="repeatPassword" placeholder="Repeat Password" class="repeat-password" /></div>
        <div style="margin-top: 50px;"><input type="submit" value="Sign Up" class="submit" /></div>
      </form>
  </div>
</body>
</html>
