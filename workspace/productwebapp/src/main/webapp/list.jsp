<%--
  Created by IntelliJ IDEA.
  User: banuprakash
  Date: 21/04/25
  Time: 2:39 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
    <h1>Product List</h1>
    <table>
        <tr>
            <th>ID</th> <th>NAME</th> <th>PRICE</th> <th>Select</th>
        </tr>
        <c:forEach var="product" items="${list}">
          <tr>
              <td>
                  ${product.id}
              </td>
              <td>
                      ${product.name}
              </td>
              <td>
                      ${product.price}
              </td>
              <td>
                  <input type="radio" name="product" />
              </td>
          </tr>
        </c:forEach>
        <tr >
                <td colspan="4"><button type="button">Add to Cart</button></td>
        </tr>
    </table>
</body>
</html>
