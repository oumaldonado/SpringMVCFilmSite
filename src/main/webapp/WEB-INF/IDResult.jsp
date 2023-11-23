<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID Result</title>
</head>
<body>
<c:choose>
    <c:when test="${! empty film}">
      <ul>
        <li>${film.filmId}</li>
        <li>${film.title}</li>
        <li>${film.language}</li>
        <li>${film.releaseYear}</li>
        <li>${film.rating}</li>
        <li>${film.description}</li>
      </ul>
    </c:when>
    <c:otherwise>
      <p>***** NO Film Found *****</p>
    </c:otherwise>
  </c:choose>
</body>
</html>