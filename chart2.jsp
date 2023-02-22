<%--
  Created by IntelliJ IDEA.
  User: 12140
  Date: 2023/2/22
  Time: 0:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询界面</title>
</head>
<body>
<form action="chartServlet?method=search" method="post">
    <center>
        <table border="1">
            <tr>
                <td>请输入你想要查询的地区：</td>
                <td><input type="text" name="whe"></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><input type="submit" value="查询"></td>
            </tr>
        </table>
    </center>
</form>
</body>
</html>
