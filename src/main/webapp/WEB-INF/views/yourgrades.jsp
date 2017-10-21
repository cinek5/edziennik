<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">
<!DOCTYPE html>
<head>
    <title>Your grades</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
=
    <style>
    	.accepted {
    	 background-color: #81f4b3;
    	 padding: 2px 3px;
    	 margin: 0;
    	 height: 90%;
    	 
    	}
    </style>

</head>
<body>

<jsp:include page="navbar.jsp"/>


<!-- main content-->


    <div class="container">
       <h2>Your grades</h2>
        <table class="table">
            <thead>
                <th>Course name</th>
                <th>Teacher</th>
                <th>Grade</th>
                <th>Status</th>
                <th></th>
            </thead>
            <tbody>
            	<c:forEach var="gradeObject" items="${grades}">
 					 <tr> 
 					 	<td>${gradeObject.course.name}</td>
 					 	<td>${gradeObject.course.teacher.name} ${gradeObject.course.teacher.surname}</td>
 					 	<td>${gradeObject.grade}</td>
 					 	<td>
 					 		<c:if test="${gradeObject.accepted }"> 
 	                        <span class="accepted">Accepted </span>
 					 		</c:if>
 					 		<c:if test="${not gradeObject.accepted }"> 
 	                        Not accepted yet
 					 		</c:if>
 					 	</td>
 					 	<td>	
 					 		<c:if test="${not gradeObject.accepted }">
 					 	<a href="<c:url value ="/student/acceptGrade/${gradeObject.id }"/>" class="btn btn-info togglebutton" type="button">Accept grade</a> 
 					 		</c:if>
 					 	</td>
 					 </tr>
				</c:forEach>
              
             		
            </tbody>
        </table>
    </div>









</body>
</html>