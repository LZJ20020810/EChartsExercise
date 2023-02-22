<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 12140
  Date: 2023/2/21
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <script src="lib/echarts.min.js"></script>
  <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <title>浏览界面</title>
</head>
<body>
<%--<%--%>
<%--  Object message=request.getAttribute("message");--%>
<%--  if(message!=null&&!"".equals(message)){--%>
<%--    %>--%>
<%--<script type="text/javascript">--%>
<%--  alert(<%=request.getAttribute("message")%>);--%>
<%--</script>--%>
<%--<%}%>--%>

<div align="center">
  <table border="1">
    <tr>
      <th>地区名称</th>
      <th>地区人数</th>
    </tr>

    <c:forEach items="${list}" var="l">
      <tr>
        <td>${l.name}</td>
        <td>${l.count}</td>
      </tr>
    </c:forEach>
  </table>
</div>

<%--以下内容--图--不显示--%>
<div id="main" style="width:400px;height:400px;"></div>

<script type="text/javascript">
  var myCharts=echarts.init(document.getElementById("main"));

  var array=new Array();
  var index=0;
  <c:forEach items="${list}" var="l">
  array[index++]=${l.count};
  </c:forEach>

  //指定表
  var option={
    title:{
      text:"地区人数图"
    },
    tooltip:{
      show:true
    },
    legend:{
      data:['各地区人数']
    },
    xAxis:[
            {
      type:'category',
      data:[
              <c:forEach items="${list}" var="l">
              ["${l.name}"],
        </c:forEach>
      ]
    }
    ],
    yAxis:[
      {
        type:'value'
      }
    ],
    series:[
      {
        name:'count',
        type:'bar',
        data:array
      }
    ]
  };

  myCharts.setOption(option);
</script>
</body>
</html>
