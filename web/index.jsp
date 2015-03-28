<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:template>
    <jsp:attribute name="page_header">
        Strona główna
    </jsp:attribute>

    <jsp:body>
        <c:forEach items="${users}" var="user">
            ${user.id}<br>
            ${user.name}<br>
            ${user.email}<br><br>
        </c:forEach>
    </jsp:body>
</t:template>