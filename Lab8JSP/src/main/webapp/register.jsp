<%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 5/16/2022
  Time: 10:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="./styles.css">
</head>
<body>
<div id="bg"></div>

<form action="RegisterController" method="post">
    <div class="form-field">
        <input type="text" placeholder="Your name" name="name" required/>
    </div>

    <div class="form-field">
        <input type="text" placeholder="Your username" name="username" required/>
    </div>

    <div class="form-field">
        <input type="password" placeholder="Password" name="password1" required/>
    </div>

    <div class="form-field">
        <input type="password" placeholder="Confirm password" name="password2" required/>
    </div>

    <div class="form-field">
        <button class="btn" type="submit">Register</button>
    </div>
    <%--    Enter username : <input type="text" name="username"> <br>--%>
    <%--    Enter password : <input type="password" name="password"> <br>--%>
    <%--    <input class="button" type="submit" value="Login"/>--%>
</form>
</body>
</html>
