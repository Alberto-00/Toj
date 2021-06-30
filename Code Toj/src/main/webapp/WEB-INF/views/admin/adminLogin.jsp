<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <jsp:include page="/WEB-INF/views/partials/admin/head.jsp">
        <jsp:param name="adminStyles" value="adminLogin"/>
        <jsp:param name="title" value="T&#x000F8;j | Admin Login"/>
        <jsp:param name="adminScripts" value="adminFormValidate"/>
    </jsp:include>
</head>
<body>
<form name="admin-login" class="login-form" action="${pageContext.request.contextPath}/adminServlet/adminLogin" method="post">
    <div class="login-form-logo-container">
        <img class="login-form-logo" src="${pageContext.request.contextPath}/icons/logo.png" alt="logo">
    </div>
    <div class="login-form-content">
        <div class="login-form-header">
            <label>Accedi al tuo account amministratore</label>
        </div>
        <input class="login-form-input" type='email' name="email" id="idEmail" placeholder="Email" required autocomplete="off" >
        <input class="login-form-input" type="password" name="password" placeholder="Password" required autocomplete="off">
        <c:if test="${not empty msg}">
            <label class="error">${msg}</label>
        </c:if>
        <button class="login-form-button"  type="submit">Accedi</button>
    </div>
</form>
</body>
</html>