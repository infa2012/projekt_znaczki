<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:template>
    <jsp:attribute name="page_header">
        Logowanie
    </jsp:attribute>

    <jsp:body>
        ${message}
        <form class="form-horizontal" id="validation_form" method="post">                                
            <div class="form-group">
                <label for="login" class="col-sm-2 control-label">Login*</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="login" name="login" placeholder="Login">
                </div>
            </div>
            <div class="form-group" id="haslo_form_group">
                <label for="password" class="col-sm-2 control-label">Hasło*</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Hasło">
                </div>      
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5">
                    <button type="submit" class="btn btn-primary">Zaloguj się</button>
                </div>
            </div>
        </form>
        <script type="text/javascript">
            $("#validation_form").validate({
                rules: {
                    password: {
                        required: true,
                        rangelength: [5, 50]
                    },
                    login: {
                        required: true,
                        rangelength: [5, 50]
                    }
                }
            });
        </script>
    </jsp:body>
</t:template>