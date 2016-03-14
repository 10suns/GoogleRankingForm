<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Ranking</title>
  </head>
  <body>
    <h1>Result</h1>
    <c:choose>
      <c:when test="${ranking == -1}">
        out of 50 first result
      </c:when>
      <c:otherwise>
        Ranking of <b>${domain}</b> is ${ranking} for <b>${keyword}</b>
      </c:otherwise>
    </c:choose>
  </body>
</html>
