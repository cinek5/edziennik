<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Teacher courses</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
	<style>
	  .jumbotron {
	  	background-color:#e87187;
	  	color: #f9f9f9;
	  	height: 40%;
	  }
	  .jumbotron h3 {
	    width: 90%;
	    padding: 0;
	    margin: auto auto;
	  }
	</style>
</head>
<script  src="<c:url value="/resources/scripts/logout.js" />"></script>
<body>

<!-- Top navba-->
   <jsp:include page="navbar.jsp"/>


<!-- main content-->


    <div class="container">
    	<c:if test="${not empty noCourses }">
    	<div class = "jumbotron">
        <h3>Sorry, there are no courses avaible to sign in.</h3>
        </div>
       </c:if>
    
    	<c:if test="${empty noCourses }"> 
       <h2>Courses avaible to sign in</h2>
       
      
        <table class="table">
            <thead>
                <th>Course name</th>
                <th>Number of students attending</th>
                <th></th>
            </thead>
            <tbody>
               <c:forEach var="course" items="${courses}">
 					 <tr> 
 					 	<td>${course.name}</td>
 					 	<td>${course.size}</td>
 					 	<td><a href="<c:url value="/student/signIn/${course.id}"/>" class="btn btn-info" role="button">Sign in</a></td>
 					 </tr>
				</c:forEach>
            </tbody>
        </table>
       </c:if>
    </div>









</body>
</html>