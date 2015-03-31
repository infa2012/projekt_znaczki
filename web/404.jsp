<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:template>

    <jsp:attribute name="page_header">
        404 - <small> Strona nie została znaleziona</small>
    </jsp:attribute>

    <jsp:body>
        <div class="row">

            <div class="col-lg-12">
                <div class="jumbotron">
                    <h1>
                        <span class="error-404">404</span>
                    </h1>
                    <p>Strona, której szukasz nie mogła zostać odnaleziona.</p>
                </div>
            </div>

        </div>
    </jsp:body>

</t:template>