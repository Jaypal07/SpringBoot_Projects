<%@page language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="Style.css">
</head>
<body>
<h1>Result is : <%= session.getAttribute("result") %></h1>
<h1>Result using jstl : ${result}</h1>
</body>
</html>
