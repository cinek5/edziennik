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
</head>
<body>

<jsp:include page="navbar.jsp"/>



<!-- main content-->


    <div class="container">
       <h2>Your courses</h2>
        <table class="table">
            <thead>
                <th>Course name</th>
                <th>Number of students attending</th>
                <th></th>
            </thead>
            <tbody>
               <c:forEach var="entry" items="${coursesMap}">
 					 <tr> 
 					 	<td>${entry.key.name}</td>
 					 	<td>${entry.value }</td>
 					 	<td><a href="<c:url value="/teacher/showStudents/${entry.key.id}"/>" class="btn btn-info" role="button">Show students list</a></td>
 					 </tr>
				</c:forEach>
            </tbody>
        </table>
    </div>









</body>
</html>