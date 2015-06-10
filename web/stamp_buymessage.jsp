<%-- 
    Document   : StampBuyMessage
    Created on : 2015-06-10, 20:07:27
    Author     : Oghur
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<t:template>
    <jsp:attribute name="page_header">
        Stan zakupu
    </jsp:attribute>       
    <jsp:body>
        ${message}
    </jsp:body>
</t:template>
