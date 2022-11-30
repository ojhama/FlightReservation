<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find Flights:</title>
</head>
<body>
<form action="findFlights" method="post">

<h2>Find Flights:</h2>
Form:<input type="text" name="from" />
To:<input type="text" name="to" />
Departure Date:<input type="text" name="departureDate" />
<input type="submit" name="search" />
</form>
</body>
</html>