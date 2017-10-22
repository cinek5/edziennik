<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>My profile</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/i18n/defaults-*.min.js"></script>
    <style>
        .data {
          color: #d7dae0;
        }
        
        .thumbnail {
            background-color: black;
            border-radius: 2%;

        }
        .img-thumbnail {
            background-color: #d7dae0;
        }
    </style>

</head>
<body>
    <jsp:include page="navbar.jsp"/>
    <div class="container">
        <div  class="thumbnail" style="margin: 0 auto; width: 50%;"  >
          <div style="margin: 0 auto; width: 80%">
            <h1 class="data">${user.name } ${user.surname }</h1> 
            <h2 class="data">Date of birth: ${user.birthdate }</h2>
            <h2 class="data">E mail: ${user.email }</h2>
        
            <div>
            <img class="img-thumbnail" width=400 src="http://www.skijumping.pl/newsy/zdjecia/powieksz/malysz_wywiad_ibk_2009-01-03_05-09-54.jpg">
            </div>
            <c:if test="${myProfile }">
            <button class="btn btn-primary" style="margin: 30px 0;">Edit my profile </button>
            </c:if>
          </div>
        </div>
    </div>

<!-- main content-->



</html>