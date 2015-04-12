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
                <a href="messages_received" class="list-group-item active">
                    Odebrane
                </a>
                <a href="messages_sended" class="list-group-item">Wysłane</a>
            </div>
        </div>
        <div class="col-lg-9">      
            <c:choose> 
                <c:when test="${fn:length(receivedMessages) > 0}">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <c:forEach items="${receivedMessages}" var="message" varStatus="status">                   
                            <div class="panel panel-default">
                                <div class="panel-heading" role="tab" id="Heading-${message.id}">
                                    <h4 class="panel-title">
                                        <a href="#" class="btn btn-success btn-sm">Gohanzo</a>
                                        &nbsp;
                                        /
                                        &nbsp;
                                        <a data-toggle="collapse" data-parent="#accordion" href="#${message.id}-collapse" aria-expanded="true" aria-controls="${message.id}-collapse">
                                            ${message.topic}
                                        </a>
                                        &nbsp;
                                        /
                                        &nbsp;
                                        <a href="message_write?recipient=1&message_answer=1" class="btn btn-default btn-sm">Odpisz!</a>
                                        <span class="pull-right">${message.created_at}</span>
                                    </h4>
                                </div>
                                <div id="${message.id}-collapse" class="panel-collapse collapse" role="tabpanel" aria-labelledby="Heading-${message.id}">
                                    <div class="panel-body">
                                        ${message.content}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>        
                    </c:when>
                    <c:otherwise>
                        Niestety nie masz jeszcze żadnych wiadomości.
                    </c:otherwise>
                </c:choose>
            </div>
        </jsp:body>
    </t:template>