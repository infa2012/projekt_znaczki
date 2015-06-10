<%-- 
    Document   : stamp_buyaccept
    Created on : 2015-06-09, 21:59:55
    Author     : Oghur
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<t:template>
    <jsp:attribute name="page_header">
        Zatwierdzenie
    </jsp:attribute>

    <jsp:body>
        Sprawdź czy poniższy znaczek jest tym który chcesz kupić.
        <table border="1">
            <th>ID znaczka</th>
            <th>Nazwa znaczka</th>
            <th>Opis</th>
            <th>Data wydruku</th>
            <th>Cena</th>
                <c:forEach items="${stamp}" var="stud">
                <tr>             
                    <td>${stud.id}</td>
                    <td>${stud.name}</td>
                    <td>${stud.notes}</td>
                    <td>${stud.print_year}</td>  
                    <td>${stud.price} zł.</td> 
                </tr>
                </br>
                Cena: ${stud.price} zł.                                              
            </c:forEach>             
        </table>
        <form action="./BuyStamp" method="POST">
            <c:forEach items="${stamp}" var="stud2">
                <input type="hidden" name="stampid" value="${stud2.id}"/>
            </c:forEach>            
            </br>Sposób wysyłki
            </br>
            <input type="radio" name="delivery" value="kurier">Kurier
            </br>
            <input type="radio" name="delivery" value="paczkomat">Paczkomat
            </br>
            <input type="radio" name="delivery" value="poczta">Poczta Polska
            </br>
            </br>Płatność
            </br>
            <input type="radio" name="payment" value="przelew">Przelew
            </br>
            <input type="radio" name="payment" value="odbior">Przy odbiorze
            </br>
            <input type="submit" class="btn btn-primary" value="Zatwierdź zakup"/>           
        </form>

    </jsp:body>
</t:template>