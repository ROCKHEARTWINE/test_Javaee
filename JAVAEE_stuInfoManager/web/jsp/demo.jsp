<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/stuServlet?method=add">添加学生</a>
<a href="${pageContext.request.contextPath}/stuServlet?method=update">修改学生</a>
<a href="${pageContext.request.contextPath}/stuServlet?method=find">查询学生</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/teacherServlet?method=add">添加老师</a>
<a href="${pageContext.request.contextPath}/teacherServlet?method=update">修改老师</a>
<a href="${pageContext.request.contextPath}/teacherServlet?method=find">查询老师</a>
</body>
</html>
