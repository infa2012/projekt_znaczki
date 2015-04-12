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
                    Do: <strong><a href="#">Gohanzo</a></strong>
                </div>
                <div class="panel-heading">
                    <input type="text" value="cos" class="form-control" placeholder="Temat wiadomości">
                </div>
                <div class="panel-body">
                    <textarea class="form-control textarea-resize-vertical" rows="15" placeholder="Treść wiadomości"></textarea>
                    <input type="submit" style="margin-top: 20px;" class="btn btn-primary pull-right" value="Wyślij wiadomość!">
                </div>
            </div>
        </div>
    </jsp:body>
</t:template>