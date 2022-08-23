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
        <td width="10%" class="tableleft">编号</td>
        <td><span id="id">${charge.id}</span></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">收费项目名称</td>
        <td><input type="text" id="chargeName" value=${charge.chargeName} /></td>
    </tr>

    <tr>
        <td width="10%" class="tableleft">收费金额</td>
        <td><input type="text" id="chargeMoney" value=${charge.chargeMoney}/></td>
    </tr>
    <tr>
        <td colspan="2">
			<center>
				<button onclick="A()" type="button" class="btn btn-primary">保存</button> &nbsp;&nbsp;
                <button onclick="B()" type="button" class="btn btn-success">返回列表</button>
			</center>
		</td>
    </tr>
</table>
</form>
</body>
</html>
<script>
    function A(){
        $.ajax({
            type: "POST",
            url: "/charge/"+$("#id").html(),
            data: "chargeName="+$("#chargeName").val()+"&chargeMoney="+$("#chargeMoney").val()+"&_method=PUT",
            dataType:"json",
            success: function(vo){
                if (vo.code==200){
                    window.location.href="/hospital/charge.html";
                }
            }
        });
    }
    function B(){
        window.location.href="/hospital/charge.html";
    }
</script>