<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact to me</title>
</head>
<body>
    <div align="center">
        <h1>Contact</h1>
        <form action="" method="GET" >
          <input type="hidden" name="id" value="${contact.id}" />
          <table>
              <tr>
                  <td>Keyword:</td>
                  <td><input type="text" name="keyword" value="${keyword}"/></td>
              </tr>
              <tr>
                  <td>Domain:</td>
                  <td><input type="text" name="domain" value="${domain}"/></td>
              </tr>
              <tr>
                  <td colspan="2" align="center"><input type="submit" value="Submit"></td>
              </tr>
          </table>
        </form>
    </div>
</body>
</html>
