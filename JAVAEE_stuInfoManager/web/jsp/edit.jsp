<%@ page import="java.lang.annotation.Target" %><%--
  Created by IntelliJ IDEA.
  User: 32714
  Date: 2021/6/28
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/stuServlet?method=updateStu" method="post" enctype="multipart/form-data">
        <input type="hidden" name="sid" value="${stu.sid}">
        <input type="hidden" name="oldphoto" value="${stu.photo}">
    <table>
        <tr>
            <td>姓名:</td>
            <td><input type="text" name="name" value="${stu.name}"></td>
        </tr>
        <tr>
            <td>性别:</td>
            <td>

                <input type="radio" name="sex" value="男"  <c:if test="${stu.sex=='男'}">checked</c:if> >男
                <input type="radio" name="sex" value="女" <c:if test="${stu.sex=='女'}">checked</c:if> >女
            </td>
        </tr>
        <tr>
            <td>爱好:</td>
            <td>

                <input type="checkbox" name="hobby" value="吃" <c:if test="${fn:contains(stu.hobby, '吃')}">checked</c:if>>吃
                <input type="checkbox" name="hobby" value="喝" <c:if test="${fn:contains(stu.hobby, '喝')}">checked</c:if>>喝
                <input type="checkbox" name="hobby" value="嫖" <c:if test="${fn:contains(stu.hobby, '嫖')}">checked</c:if>>嫖
                <input type="checkbox" name="hobby" value="赌" <c:if test="${fn:contains(stu.hobby, '赌')}">checked</c:if>>赌
            </td>
        </tr>
        <tr>
            <td>出生日期:</td>
            <td>
                <input type="date" name="birthday" value="${stu.birthday}">
            </td>
        </tr>
        <tr>
            <td>个人介绍</td>
            <td>
                <textarea name="sdesc" cols="40" rows="5">${stu.sdesc}</textarea>
            </td>
        </tr>
        <tr>
            <td>头像:</td>
            <td>
                <img src="http://localhost:80/img/${stu.photo}" width="100px" height="100px">
                <input type="file" name="fileName">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="修改">
            </td>
        </tr>

    </table>
    </form>
</body>
</html>
