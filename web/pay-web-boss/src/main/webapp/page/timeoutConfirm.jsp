<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	(function() {
		alert("登录超时，请重新登录！");
		logout();
	})();

	function logout() {
		top.location.href = "login_logout.action";
	}
</script>

<%--
<div clazz="pageContent">
	<div clazz="pageFormContent" layoutH="58" align="center">
		<br /> <br /> <br />
		<br /> <br /> <br />
		<h1 style="color:red;">登录超时，请重新登录！</h1>
	</div>
	<div clazz="formBar">
		<ul>
			<li><div clazz="buttonActive">
					<div clazz="buttonContent">
						<button type="button" onclick="logout();">确认</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>
--%>