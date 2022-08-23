<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加收费项目--中软高科-2015</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>
    <script type="text/javascript" src="../Js/ckeditor/ckeditor.js"></script>
 

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
		$('#backid').click(function(){
				window.location.href="charge.html";
		 });
    });
    </script>
</head>
<body>
<form action="charge.html" method="post" class="definewidth m20">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">病历号</td>
        <td>${hospital.id}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">姓名</td>
        <td>${register.registerName}</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收费项目</td>
        <td><input onblur="A()" id="chargeName" type="text" name="pname" value=""/>输入自动匹配出来</td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收费金额</td>
        <td><input id="chargeMoney" type="text" name="pname" value=""/></td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button type="button" onclick="B()" class="btn btn-primary" >保存</button> &nbsp;&nbsp;
                <button type="button" onclick="C()" class="btn btn-success" >返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>
<script>
    function A() {
        $.ajax({
            type: "GET",
            url: "/charge/queryByName",
            data: "chargeName=" + $("#chargeName").val(),
            dataType: "json",
            success: function (vo) {
                $("#chargeMoney").val(vo.chargeMoney);
            }
        });
    }
    function B(){
        $.ajax({
            type: "POST",
            url: "add",
            data: "hospitalId=${hospital.id}"+"&chargeName=" + $("#chargeName").val()+"&chargeMoney="+$("#chargeMoney").val(),
            dataType: "json",
            success: function (vo) {
                if (vo.code==200) {
                    window.location.href = "/hospital/charge2.html";
                }
            }
        });
    }
    function C(){
        window.location.href = "/hospital/charge2.html";
    }
</script>