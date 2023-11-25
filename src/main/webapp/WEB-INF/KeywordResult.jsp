<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Keyword Result</title>
</head>
<body>
	<c:choose>
		<c:when test="${! empty films}">
			<ul>
				<c:forEach var="film" items="${films}">
					<h5>${film.title}</h5>
					<a href="findFilmById.do?filmId=${film.filmId}">About This Film</a>
					<li>${film.description}</li>
					<hr>
					<br>
					<br>
				</c:forEach> 
			</ul>
		</c:when>
		<c:otherwise>
			<p>***** NO Films Found *****</p>
		</c:otherwise>
	</c:choose>

</body>
</html>