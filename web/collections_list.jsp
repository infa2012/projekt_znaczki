<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<t:template>
    <jsp:attribute name="page_header">
        ${page_title}
    </jsp:attribute>

    <jsp:body>
        <div class="col-lg-3">
            <div class="list-group">
                <a href="MyStamps" class="list-group-item${activeCol==-1?" active":""}">
                    Moje znaczki <span class="badge">${allStampsNum}</span>
                </a>
                <a href="AddNewCollection" class="list-group-item">
                    Nowa Kolekcja <span class="badge" style="font-weight:bold">+</span>
                </a>
            </div>
                
            <div class="list-group">
                <c:forEach items="${collections}" var="collection">
                <a href="AddNewCollection" class="list-group-item${activeCol==collection.id?" active":""}">
                    ${collection.name}<span class="badge">${collection.size}</span>
                </a>
                </c:forEach>
                
            </div>
        </div>
        <div class=""
        <c:choose>
            <c:when test="${fn:length(stamps_in_collection) > 0}">
                <div class="container">
                    <div class="row">
                    <c:set var="ind" value="0"></c:set>
                    <c:forEach items="${stamps_in_collection}" var="stamp" varStatus="status">
                        <div class=" col-sm-12 col-md-6 col-lg-4">
                        <div class="panel panel-default">
                            <div class="panel-heading" role="tab" id="Heading-${stamp.id}">
                                    <h4 class="panel-title">
                                        <a href="StampEdit?id=${stamp.id}" >
                                            ${stamp.name}
                                        </a>
                                    </h4>
                            </div>
                            <div class="panel-body">
                                <span class="stamp-date"> ${stamp.added_on}</span>
                                <br />
                                <img data-src="holder.js/164x164" class="pull-right " alt="holder" title="Brak Obrazka">
                                <span class="stamp-notes"> ${stamp.notes} </span>
                            </div>
                            <c:if test="${stamp.user}">
                            <div class="panel-footer">
                                Użytkownik: Krzysztof Kozłowski
                            </div>
                            </c:if>
                        </div>
                            <c:set var="ind" value="${ind+1}"></c:set>
                            <c:if test="${ind==2}">
                                <div class="clearfix visible-md-block"></div>
                            </c:if>
                            <c:if test="${ind==3}">
                                <c:set var="ind" value="0"></c:set>
                                <div class="clearfix visible-lg-block"></div>
                            </c:if>
                        </div>
                                
                    </c:forEach>
                        
                    </div>
                </div>
                <script>
                   $(document).ready(function(){
                       $(".panel").mouseenter(function(){
                           $(this).removeClass("panel-default");
                           $(this).addClass("panel-primary");
                       });
                       $(".panel").mouseleave(function(){
                           $(this).removeClass("panel-primary");
                           $(this).addClass("panel-default");
                       });
                   });
                </script>
        </c:when>
                <c:otherwise>
                    <div class="alert alert-warning">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>Nie masz żadnych znaczków.</strong>
                    </div>
                </c:otherwise>
                    
            </c:choose>
        </jsp:body>
    </t:template>
