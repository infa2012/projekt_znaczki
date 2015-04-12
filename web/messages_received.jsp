<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:template>
    <jsp:attribute name="page_header">
        Wiadomości
    </jsp:attribute>

    <jsp:body>
        <div class="col-lg-3">
            <div class="list-group">
                <a href="messages_received" class="list-group-item active">
                    Odebrane
                </a>
                <a href="messages_sended" class="list-group-item">Wysłane</a>
            </div>
        </div>
        <div class="col-lg-9">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong><a href="#">Co u Ciebie słychać?</a></strong>
                    <span class="pull-right">28.01.2015 15:48:30</span>
                </div>       
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <strong><a href="#">Masz super znaczki hehe!</a></strong>
                    <span class="pull-right">28.01.2015 15:48:30</span>
                </div>    
            </div>
        </div>
    </jsp:body>
</t:template>