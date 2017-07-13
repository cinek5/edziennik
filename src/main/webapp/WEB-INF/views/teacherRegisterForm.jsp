<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Teacher form</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/custom.css">
</head>
<body>
<div class="container" style="padding-bottom: 50px">
    <div class="jumbotron" style="padding-bottom: 50px">
        <h1>Register new teacher.</h1>
    </div>
    <div class="col-md-4"></div>
    <div class="col-md-4">
   
        <form:form class="form-group" method="POST" modelAttribute="teacher">
            <div class="row">
                <div class="form-group">
                    <label for="email">Email address:</label>
                    <form:input type="email" class="form-control" id="email" path="email"/>
                     <c:if test="${pageContext.request.method=='POST'}"><form:errors path="email" /></c:if>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <form:input type="text" class="form-control" id="username" path="username"/>
                     <c:if test="${pageContext.request.method=='POST'}"><form:errors path="username" /></c:if>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                <label for="password">Password:</label>
                <form:input type="password" class="form-control" id="password" path="password"/>
                 <c:if test="${pageContext.request.method=='POST'}"><form:errors cssClass="error" path="password" /></c:if>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <form:input type="text" class="form-control" id="name" path="name"/>
                     <c:if test="${pageContext.request.method=='POST'}"><form:errors cssClass="error" path="name" /></c:if>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="surname">Surname:</label>
                    <form:input type="text" class="form-control" id="surname" path="surname"/>
                     <c:if test="${pageContext.request.method=='POST'}"><form:errors cssClass="error" path="surname" /></c:if>
                </div>
            </div>
            <div class="row">
                <div class="form-group">
                    <label for="birthdate">Date of birth:</label>
                    <form:input type="date" class="form-control" id="birthdate" path="birthdate"/>
                     <c:if test="${pageContext.request.method=='POST'}"><form:errors cssClass="error" path="birthdate" /></c:if>
                </div>
            </div>
            <div class="row">
                <button type="submit" class="btn btn-primary btn-large">Submit</button>
            </div>




        </form:form>
    </div>
    <div class="col-md-4"></div>

</div>
</body>
</html>