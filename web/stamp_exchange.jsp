<%-- 
    Document   : stamp_exchange
    Created on : 2015-06-09, 19:48:45
    Author     : Oghur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<t:template>
    <jsp:attribute name="page_header">
        Kup znaczki
    </jsp:attribute>

    <jsp:body>
        Dostępne znaczki
        </br>
        <table border="1">
            <th>ID znaczka</th>
            <th>Nazwa znaczka</th>
            <th>Opis</th>
            <th>Data wydruku</th>
            <th>Cena</th>
                <c:forEach items="${allStamps}" var="stud">
                <tr>
                <form action="./BuyStampAccept" method="POST">
                    <td>${stud.id}</td>
                    <td>${stud.name}</td>
                    <td>${stud.notes}</td>
                    <td>${stud.print_year}</td>  
                    <td>${stud.price}</td> 
                    <input type="hidden" name="stampid" value="${stud.id}"/>
                    <td><input type="submit" class="btn btn-primary" value="Kup"/> </td>
               </form>
            
        </tr>

    </c:forEach>

</table>
</jsp:body>
</t:template>
