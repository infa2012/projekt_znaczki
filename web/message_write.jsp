<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<t:template>
    <jsp:attribute name="page_header">
        Wiadomości
    </jsp:attribute>

    <jsp:body>
        <div class="col-lg-3">
            <div class="list-group">
                <a href="messages_received" class="list-group-item">
                    Odebrane <span class="badge">${fn:length(receivedMessages)}</span>
                </a>
                <a href="messages_sended" class="list-group-item">
                    Wysłane <span class="badge">${fn:length(sendedMessages)}</span>
                </a>
            </div>
        </div>
        <div class="col-lg-9">
            ${message}
            <form method="post">    
                <div class="panel panel-default">
                    <c:if test="${messageToAnswer.sender != null}">
                        <div class="panel-heading">
                            Do: <strong><a href="user?id=${messageToAnswer.sender}">Wejdź na profil użytkownika</a></strong>
                        </div>
                    </c:if>

                    <div class="panel-heading">
                        <input type="text" value="${messageToAnswer.topic != null ? 'Re:'+=messageToAnswer.topic : ''}" name="topic" class="form-control" placeholder="Temat wiadomości">
                    </div>
                    <div class="panel-body">
                        <textarea class="form-control textarea-resize-vertical" rows="15" name="content" placeholder="Treść wiadomości"></textarea>
                        <input type="submit" style="margin-top: 20px;" class="btn btn-primary pull-right" value="Wyślij wiadomość!">
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>
</t:template>