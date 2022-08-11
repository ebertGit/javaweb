<html>
<head>
  <title>login</title>
  <link href="<%= request.getContextPath() %>/static/common.css" rel="stylesheet">
</head>
<body class="center">
<div>
  <div style="background-color: green;">
    <h1>Login form</h1>
  </div>
  <form action="doLogin" method="post">
    <table width="600px">
      <tr>
        <th>User Name</th>
        <td><input type="text" name="username" /></td>
      </tr>
      <tr>
        <th>Password</th>
        <td><input type="password" name="password" /></td>
      </tr>
    </table>
    <input type="submit" value="Login" />
  </form>
</div>
<hr>
<div>
  <div style="background-color: pink;">
    <h1>Registration form</h1>
  </div>
  <form action="doRegistration" method="post">
    <table width="600px">
      <tr>
        <th>User Name</th>
        <td><input type="text" name="username" /></td>
      </tr>
      <tr>
        <th>Password</th>
        <td><input type="password" name="password" /></td>
      </tr>
    </table>
    <input type="submit" value="Registry" />
  </form>
</div>
</body>
</html>
