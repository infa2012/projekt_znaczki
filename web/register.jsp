<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<t:template>

    <jsp:attribute name="page_header">
        Rejestracja
    </jsp:attribute>

    <jsp:body>
        ${message}

        <form class="form-horizontal" id="validation_form" method="post">                                
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">Imię*</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="name" name="name" placeholder="Imię">                                       
                </div>
            </div>
            <div class="form-group">
                <label for="surname" class="col-sm-2 control-label">Nazwisko*</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="surname" placeholder="Nazwisko" name="surname">
                </div>
            </div>
            <div class="form-group">
                <label for="login" class="col-sm-2 control-label">Login*</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="login" placeholder="Login" name="login">
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">E-mail*</label>
                <div class="col-sm-5">
                    <input type="email" class="form-control" id="email" name="email" placeholder="E-mail">
                </div>
            </div>
            <div class="form-group" id="haslo_form_group">
                <label for="password" class="col-sm-2 control-label">Hasło*</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" id="password" name="password" placeholder="Hasło">
                </div>      
            </div>
            <div class="form-group">
                <label for="repeat_password" class="col-sm-2 control-label">Powtórz hasło*</label>
                <div class="col-sm-5">
                    <input type="password" class="form-control" id="repeat_password" name="repeat_password" placeholder="Powtórz hasło">
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
                    <button type="submit" class="btn btn-primary">Zarejestruj się</button>
                </div>
            </div>
        </form>

        <script type="text/javascript">
            $("#validation_form").validate({
                rules: {
                    name: {
                        required: true,
                        rangelength: [3, 50]
                    },
                    surname: {
                        required: true,
                        rangelength: [3, 50]
                    },
                    password: {
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
                    repeat_password: {
                        required: true,
                        rangelength: [5, 50],
                        equalTo: "#password"
                    }
                }
            });
        </script>
    </jsp:body>

</t:template>