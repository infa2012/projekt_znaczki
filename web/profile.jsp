<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:template>
    <jsp:attribute name="page_header">
        Profil
    </jsp:attribute>

    <jsp:body>
        ${message}

        <form class="form-horizontal" id="validation_form" method="post">                                
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Imię*</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Imię" value="${user.name}">                                       
                </div>
            </div>
            <div class="form-group">
                <label for="surname" class="col-sm-2 control-label">Nazwisko*</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="surname" placeholder="Nazwisko" value="${user.surname}" name="surname">
                </div>
            </div>
            <div class="form-group">
                <label for="login" class="col-sm-2 control-label">Login*</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="login" placeholder="Login" value="${user.login}" name="login">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">E-mail*</label>
                <div class="col-sm-5">
                    <input type="email" class="form-control" id="email" name="email" value="${user.email}" placeholder="E-mail">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">Adres</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="address" name="address" value="${user.address}" placeholder="Adres">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">Miasto</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="city" name="city" value="${user.city}" placeholder="miasto">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">Państwo</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="country" name="country" value="${user.country}" placeholder="Państwo">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5">
                    <div class="checkbox">
                        <label>
                            * Pola z gwiazdkami nie mogą być puste
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-5">
                    <button type="submit" class="btn btn-primary">Zapisz zmiany</button>
                </div>
            </div>
        </form>

        <script type="text/javascript">
            $("#validation_form").validate({
                rules: {
                    name: {
                        required: true,
                        rangelength: [5, 50]
                    },
                    surname: {
                        required: true,
                        rangelength: [5, 50]
                    },
                    login: {
                        required: true,
                        rangelength: [5, 50]
                    },
                    email: {
                        required: true,
                        email: true
                    },
                }
            });
        </script>
    </jsp:body>

</t:template>