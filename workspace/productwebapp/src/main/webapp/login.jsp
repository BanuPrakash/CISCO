<%--
  Created by IntelliJ IDEA.
  User: banuprakash
  Date: 22/07/24
  Time: 4:12 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <div>
    ${param.msg}
  </div>
  <form method="post" action="login">
    Email <input type="email" name="email"/> <br />
    Password <input type="password" name="pwd"> <br />
    <button type="submit">Login</button>
  </form>
</body>
</html>
