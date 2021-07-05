<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/stuServlet?method=add" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>姓名:</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>性别:</td>
            <td><input type="radio" name="sex" value="男">男
                <input type="radio" name="sex" value="女">女
            </td>
        </tr>
        <tr>
            <td>爱好:</td>
            <td><input type="checkbox" name="hobby" value="葡萄">葡萄
                <input type="checkbox" name="hobby" value="樱桃">樱桃
                <input type="checkbox" name="hobby" value="桃子">桃子
                <input type="checkbox" name="hobby" value="梨子">梨子
            </td>
        </tr>
        <tr>
            <td>出生日期:</td>
            <td>
                <input type="date" name="birthday">
            </td>
        </tr>
        <tr>
            <td>个人介绍:</td>
            <td><textarea name="sdesc" cols="40" rows="5"></textarea></td>
        </tr>
        <tr>
            <td>头像:</td>
            <td>
                <input type="file" name="fileName">
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="添加">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
