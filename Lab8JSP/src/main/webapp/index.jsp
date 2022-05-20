<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ALALALALALLA</title>
    <link rel="stylesheet" href="./styles.css">
</head>
<body>
<div id="bg"></div>

<form action="LoginController" method="post">
    <div class="form-field">
        <input type="text" placeholder="username" name="username" required/>
    </div>

    <div class="form-field">
        <input type="password" placeholder="Password" name="password" required/>
    </div>

    <div class="form-field">
        <button class="btn" type="submit">Log in</button>
    </div>
    <a href="register.jsp">No account? Register here</a>
<%--    Enter username : <input type="text" name="username"> <br>--%>
<%--    Enter password : <input type="password" name="password"> <br>--%>
<%--    <input class="button" type="submit" value="Login"/>--%>
</form>
</body>
</html>