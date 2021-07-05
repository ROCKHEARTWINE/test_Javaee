
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
</head>
<body>
<%--条件查询--%>
    <div align="center">
        <form action="${pageContext.request.contextPath}/stuServlet?method=findPages" method="post">

            <p>
                姓名:<input type="text" name="name" value="${param.name}">
                性别:<select name="sex">

                        <option value="-1" <c:if test="${param.sex==-1}">selected</c:if> >--请选择--</option>
                        <option value="0" <c:if test="${param.sex==0}">selected</c:if> >男</option>
                        <option value="1" <c:if test="${param.sex==1}">selected</c:if>>女</option>
                     </select>
                <input type="submit" value="查询">
            </p>

        </form>

    </div>
    <div style="position: relative;left: 270px">
        <input type="button" value="批量删除" onclick="delAll()">
    </div>
<%--数据展示--%>
    <table border="1px" width="1200px" cellspacing="0px" cellpadding="7px" align="center">
            <tr>
                <th><input type="checkbox" name="all">全选/全不选</th>
                <th>学号</th>
                <th>姓名</th>
                <th>头像</th>
                <th>性别</th>
                <th>生日</th>
                <th>爱好</th>
                <th>操作</th>
            </tr>

        <c:if test="${pb.list!=null}">
            <c:forEach items="${pb.list}" var="stu">
                <tr>
                    <td><input type="checkbox" name="checkbox" value="${stu.sid}"></td>
                    <td>${stu.sid}</td>
                    <td>${stu.name}</td>
                    <td><img src="http://localhost:80/img/${stu.photo}" width="100px" height="100px"></td>
                    <td>${stu.sex}</td>
                    <td>${stu.birthday}</td>
                    <td>${stu.hobby}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/stuServlet?method=findStu&sid=${stu.sid}">修改</a>
                    </td>

                </tr>

            </c:forEach>

        </c:if>
    </table>
<%--分页栏--%>
    <p align="center">
        <a href="${pageContext.request.contextPath}/stuServlet?method=findPages&currentPage=1&pageSize=3&name=${param.name}&sex=${param.sex}">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <c:if test="${pb.currentPage==1}">
            <a href="#">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
        </c:if>
        <c:if test="${pb.currentPage!=1}">
            <a href="${pageContext.request.contextPath}/stuServlet?method=findPages&currentPage=${pb.currentPage-1}&pageSize=3&name=${param.name}&sex=${param.sex}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
        </c:if>

        <c:if test="${pb.currentPage==pb.totalPage}">
            <a href="#">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
        </c:if>
        <c:if test="${pb.currentPage!=pb.totalPage}">
            <a href="${pageContext.request.contextPath}/stuServlet?method=findPages&currentPage=${pb.currentPage+1}&pageSize=3&name=${param.name}&sex=${param.sex}">下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
        </c:if>
        <a href="${pageContext.request.contextPath}/stuServlet?method=findPages&currentPage=${pb.totalPage}&pageSize=3&name=${param.name}&sex=${param.sex}">尾页</a>&nbsp;&nbsp;&nbsp;&nbsp;

        当前页${pb.currentPage} &nbsp;&nbsp;总条数${pb.totalCount}  &nbsp;&nbsp;每页显示${pb.rows} &nbsp;&nbsp;共${pb.totalPage}页
    </p>
</body>
<script>

    $("input[name='all']").click(function(){
        //设置除全选框外其他的所有复选框的选中状态为 全选框的选中状态
        $("input[name='checkbox']").prop("checked",this.checked)

    })


    //批量删除
    function delAll() {
        //获取当前页面中除全选框外被选中的复选框   返回值是数组（数组里放的是每一个被选中的复选框）
        var inputs= $("input[name='checkbox']:checked")
        //遍历被选中的复选框
        var ids =""
           //在遍历过程中获取每一个复选框的value值进行拼接
        $.each(inputs,function (index,input) {

            if(index==inputs.length-1){
                ids+=input.value
            }else{
                ids+=input.value+","
            }
        })
        //发送请求带着ids
        location.href="${pageContext.request.contextPath}/stuServlet?method=delStu&ids="+ids
    }
</script>
</html>
