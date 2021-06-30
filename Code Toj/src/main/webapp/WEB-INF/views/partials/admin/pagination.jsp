<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach var="page" begin="1" end="${pages}">
    <div class="paginator">
        <a id="elem${page}" href="${pageContext.request.contextPath}/adminServlet/${param.servlet}?page=${page}">${page}</a>
    </div>
</c:forEach>

<script type="text/javascript" defer>
    $("#elem${numPage}").addClass("active");
</script>