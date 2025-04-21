<%--
  Created by IntelliJ IDEA.
  User: banuprakash
  Date: 21/04/25
  Time: 2:22 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Sample JSP Page</h1>
    <pre>
        scriptlets are for dynamic content in JSP page,
        writing java statements <% %>
        Expression is for writing output, same as out.print()
    </pre>
    <%
        for(int i = 1; i <= 10; i++) {

    %>
        5 * <%= i %> = <%= 5 * i %> <br />
    <%
        }
    %>
</body>
</html>
