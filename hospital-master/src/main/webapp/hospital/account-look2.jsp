<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>结算详细--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css"/>
    <link rel="stylesheet" type="text/css" href="../Css/style.css"/>
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }

        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">
        $(function () {
            $('#newNav').click(function () {
                window.location.href = "add.html";
            });
        });

        function checkall() {
            var alls = document.getElementsByName("check");
            var ch = document.getElementById("checkall");
            if (ch.checked) {
                for (var i = 0; i < alls.length; i++) {
                    alls[i].checked = true;
                }
            } else {
                for (var i = 0; i < alls.length; i++) {
                    alls[i].checked = false;
                }
            }
        }

        function delAll() {
            var alls = document.getElementsByName("check");
            var ids = new Array();
            for (var i = 0; i < alls.length; i++) {
                if (alls[i].checked) {
                    ids.push(alls[i].value);
                }
            }
            if (ids.length > 0) {
                if (confirm("确认操作?")) {
                    alert("成功!");
                }
            } else {
                alert("请选中要操作的项");
            }
        }

        $(function () {
            $('#backid').click(function () {
                window.location.href = "account.html";
            });
        });
    </script>
</head>
<body>

<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>收费项目管理</th>
        <th>项目名称</th>
        <th>收费项目</th>
        <th>收费金额</th>
        <th>检查日期</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${charges}" var="charge">
        <tr>
            <td style="vertical-align:middle;">${charge.id}</td>
            <td style="vertical-align:middle;">${charge.register.registerName}</td>
            <td style="vertical-align:middle;">${charge.chargeName}</td>
            <td style="vertical-align:middle;">${charge.chargeMoney}</td>
            <td style="vertical-align:middle;">${charge.chargeDateFormat}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <th colspan="5">
            <div class="inline pull-right page">
                <a name="" id="firstPage" onclick="A()">第一页</a>
                <a name="" id="prePage" onclick="B()">上一页</a>
                <span id="currentPage"></span>
                <a name="" id="nextPage" onclick="C()">下一页</a>
                <a name="" id="lastPage" onclick="D()">最后一页</a>
                &nbsp;&nbsp;&nbsp;共<span id="total" class='current'></span>条记录
                <span id="pageNum"></span>/<span id="pages"></span>页
                <span id="pageSize" class="hidden"></span>
            </div>
            <div>
                <button onclick=F() type="button" class="btn btn-success" >返回列表</button>
                <button type="button" class="btn btn-success" >导出excel</button>
            </div>
        </th>
    </tr>
</table>


<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">总花费：</td>
        <td>${hospital.totalCost}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">押金：</td>
        <td>${hospital.deposit}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">余额：</td>
        <td>${hospital.restCost}</td>
    </tr>
</table>

</body>
</html>
<script>
    function A () {
        if ($("#firstPage").attr("class")=="btn btn-small disabled"){
            return;
        }
        let num=$("#firstPage").attr("name");
        $("#pageNum").html(num);
        window.location.href="/charge/list?id=${id}&pageNum="+$("#pageNum").html()+"&pageSize="+$("#pageSize").html();
    };
    function B() {
        if ($("#prePage").attr("class")=="btn btn-small disabled"){
            return;
        }
        let num=$("#prePage").attr("name");
        $("#pageNum").html(num);
        window.location.href="/charge/list?id=${id}&pageNum="+$("#pageNum").html()+"&pageSize="+$("#pageSize").html();
    };
    function C () {
        if ($("#nextPage").attr("class")=="btn btn-small disabled"){
            return;
        }
        let num=$("#nextPage").attr("name");
        $("#pageNum").html(num);
        window.location.href="/charge/list?id=${id}&pageNum="+$("#pageNum").html()+"&pageSize="+$("#pageSize").html();
    };
    function D () {
        if ($("#lastPage").attr("class")=="btn btn-small disabled"){
            return;
        }
        let num=$("#lastPage").attr("name");
        $("#pageNum").html(num);
        window.location.href="/charge/list?id=${id}&pageNum="+$("#pageNum").html()+"&pageSize="+$("#pageSize").html();
    };
    function E (j) {
        $("#pageNum").html(j);
        window.location.href="/charge/list?id=${id}&pageNum="+$("#pageNum").html()+"&pageSize="+$("#pageSize").html();
    };
    function F(){
        window.location.href="/hospital/charge2.html?id=${id}&pageNum="+$("#pageNum").html()+"&pageSize="+$("#pageSize").html();
    }
    $(function () {
        //填充信息
        $("#total").html(${pageInfo.total});
        $("#pages").html(${pageInfo.pages});
        $("#pageNum").html(${pageInfo.pageNum});
        $("#pageSize").html(${pageInfo.pageSize});
        let str = "";
        for (let i = 1; i <= ${pageInfo.pages}; i++) {
            str += '<a onclick=E(' + i + ')>' + i + '</a>';
        }
        $("#currentPage").html(str);

        //设置按钮的属性值和样式
        $("#firstPage").attr("name", 1);
        $("#prePage").attr("name", ${pageInfo.prePage});
        $("#nextPage").attr("name", ${pageInfo.nextPage});
        $("#lastPage").attr("name", ${pageInfo.pages});
        if (${pageInfo.isFirstPage}) {//如果是第一页：上一页和首页禁用
            $("#firstPage").addClass("btn btn-small disabled");
            $("#prePage").addClass("btn btn-small disabled");
            $("#current1").addClass("btn btn-small disabled");
        }
        if (${pageInfo.isLastPage}) {//如果已经是最后一页：下一页和末页禁用
            $("#nextPage").addClass("btn btn-small disabled");
            $("#lastPage").addClass("btn btn-small disabled");
        }
    })
</script>