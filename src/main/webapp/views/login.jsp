<html>
<head>
  <title>login</title>
  <link href="<%= request.getContextPath() %>/static/common.css" rel="stylesheet">
</head>
<body class="center">
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
</body>
</html>
