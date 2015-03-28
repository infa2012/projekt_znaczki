<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="page_header" fragment="true" %>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Znaczki</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
        <link href="css/my_css.css" rel="stylesheet">
        <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
        <link href="css/jquery.dataTables_themeroller.css" rel="stylesheet">

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.validate.min.js"></script>
        <script src="js/jquery.dataTables.min.js"></script>
        <script type="text/javascript">
            jQuery.extend(jQuery.validator.messages, {
                required: "Te pole jest wymagane",
                remote: "Napraw te pole",
                email: "Proszę wprowadzić poprawny adres e-mail",
                url: "Proszę wprowadzić poprawny adres URL.",
                date: "Proszę wprowadzić poprawną datę.",
                dateISO: "Please enter a valid date (ISO).",
                number: "Proszę wprowadzić poprawną liczbę.",
                digits: "Proszę tu wpisać tylko liczby.",
                creditcard: "Proszę wprowadzić poprawny numer karty kredytowej.",
                equalTo: "Hasła muszą być takie same!",
                accept: "Proszę wprowadzić wartość z poprawnym rozszerzeniem.",
                maxlength: jQuery.validator.format("Proszę wprowadzić więcej niż {0} znaków."),
                minlength: jQuery.validator.format("Proszę wprowadzić conajmniej {0} znaków."),
                rangelength: jQuery.validator.format("Proszę wprowadzić od {0} do {1} znaków."),
                range: jQuery.validator.format("Proszę wprowadzić wartość mieszczącą się w granicy od {0} do {1}."),
                max: jQuery.validator.format("Proszę wprowadzić wartość mniejszą lub równą {0}."),
                min: jQuery.validator.format("Proszę wprowadzić wartość większą lub równą {0}.")
            });
        </script>
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>

    <body>

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="main">Serwis ogłoszeń</a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav navbar-right">                                                                                                    
                        <c:choose> 
                            <c:when test="${sessionScope.is_admin != null}">
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Panel admina <b class="caret"></b></a>
                                    <ul class="dropdown-menu">
                                        <li>
                                            <a href="users">Użytkownicy</a>
                                        </li>
                                        <li>
                                            <a href="keywords">Zabronione słowa</a>
                                        </li>
                                        <li>
                                            <a href="add_news">Dodaj newsa</a>
                                        </li>
                                        <li>
                                            <a href="reported_announcements">Zgłoszone do moderacji ogłoszenia</a>
                                        </li>
                                    </ul>
                                </li>
                            </c:when>     
                        </c:choose>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Użytkownik <b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <c:choose> 
                                    <c:when test="${sessionScope.user_id == null}">
                                        <li>
                                            <a href="login">Logowanie</a>
                                        </li>
                                        <li>
                                            <a href="register">Rejestracja</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li>
                                            <a href="logout">Wyloguj się</a>
                                        </li>

                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </li>
                        <li>
                            <c:choose> 
                                <c:when test="${sessionScope.user_id == null}">
                                    <span id="log_info">Nie jesteś zalogowany</span>
                                </c:when>
                                <c:otherwise>
                                    <span id="log_info">Jesteś zalogowany jako <strong>${sessionScope.login}</strong></span>
                                </c:otherwise>
                            </c:choose>

                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container -->
        </nav>

        <!-- Page Content -->
        <div class="container">            
            <!-- Marketing Icons Section -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        <jsp:invoke fragment="page_header"/>
                    </h1>
                </div>
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <jsp:doBody/>   
                        </div>
                    </div>
                </div>
            </div>

            <hr>

            <!-- Footer -->
            <footer>
                <div class="row">
                    <div class="col-lg-12">
                        <p>Copyright &copy; Serwis ogłoszeń 2015</p>
                    </div>
                </div>
            </footer>

        </div>
        <!-- /.container -->



    </body>

</html>
