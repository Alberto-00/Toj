<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<header>
  <i class="fas fa-bars"></i>
  <span class="account">
      <c:if test="${not empty accountSession}">
          Welcome ${accountSession.email}!
      </c:if>
  </span>
</header>