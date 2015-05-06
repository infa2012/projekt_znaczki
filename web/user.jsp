<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:template>
    <jsp:attribute name="page_header">
        User
    </jsp:attribute>

    <jsp:body>

        <div class="row">
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Dane użytkownika:</h3>
                    </div>
                    <div class="panel-body">
                        <table class="table">
                            <tr>
                                <td><strong>Imię</strong></td>
                                <td>${user.name}</td>
                            </tr>
                            <tr>
                                <td><strong>Nazwisko</strong></td>
                                <td>${user.surname}</td>
                            </tr>
                            <tr>
                                <td><strong>Login</strong></td>
                                <td>${user.login}</td>
                            </tr>
                            <tr>
                                <td><strong>E-mail</strong></td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td><strong>Utworzony:</strong></td>
                                <td>${user.created_at}</td>
                            </tr>
                            <tr>
                                <td><strong>Wiadomość</strong></td>
                                <td>
                                    <c:choose> 
                                        <c:when test="${sessionScope.user_id == user.id}">
                                            <button class="btn btn-primary disabled">Nie możesz wysłać do siebie</button>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="btn btn-primary" href="message_write?recipient=${user.id}">Wyślij!</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

        </div>

    </jsp:body>

</t:template>