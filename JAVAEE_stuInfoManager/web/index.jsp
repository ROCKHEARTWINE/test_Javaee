<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h2>欢迎您${user.username}</h2>
  <a href="${pageContext.request.contextPath}/jsp/add.jsp">添加学生</a>
  <a href="${pageContext.request.contextPath}/stuServlet?method=findPages&currentPage=1&rows=3">查询学生</a>
  </body>
</html>
