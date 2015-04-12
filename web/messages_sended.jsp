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
                    Odebrane
                </a>
                <a href="messages_sended" class="list-group-item active">Wysłane</a>
            </div>
        </div>
        <div class="col-lg-9">
            <c:choose> 
                <c:when test="${fn:length(sendedMessages) > 0}">
                    <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                        <c:forEach items="${sendedMessages}" var="message" varStatus="status">
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
                    </div>
                </c:when>
                <c:otherwise>
                    Niestety nie masz jeszcze żadnych wiadomości.
                </c:otherwise>
            </c:choose>
        </div>
    </jsp:body>
</t:template>