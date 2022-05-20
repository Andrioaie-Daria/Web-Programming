<%@ page import="ro.ubb.Assignment8.Domain.User" %><%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 5/15/2022
  Time: 8:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<%
    User user = (User)session.getAttribute("user");
    if(user == null){
        response.sendRedirect("index.jsp");
        return;
    }
    else{
%>
<div id="nav-placeholder"></div>

<p>Home page works!</p>

<section id="topics">

</section>

<script>
    $.get("navbar.html", function(data){
        $("#nav-placeholder").replaceWith(data);
        $("#username").innerHTML(<%= user.getName()%>)
    });

    $(document).ready(function(){
        getAllTopics(function(topics){
            console.log(topics);
            $("#topics").html("");
            $("#topics").append("<div id='accordion'>");
            for(const name in topics){
                let heading = 'heading' + topics[name].id;
                let collapse = 'collapse' + topics[name].id;
                let topicRedirect = 'topic.jsp?topicid=' + topics[name].id;
                $("#topics").append("<div class='card'>" +
                    "<div class='card-header' id='" + heading + "'>" +
                    "<h6 class='mb-0'> " +
                    "<a href='" + topicRedirect + "' class='btn btn-link collapsed' data-toggle='collapse' data-target='" + collapse + "' aria-expanded='false' aria-controls='" + collapse + "'>" +
                    topics[name].title + "</a>" + "        " + topics[name].number_of_comments + " comments" +"</h6></div>" +
                    "<div id='" + collapse + "' class='collapse show' aria-labelledby='" + heading + "' data-parent='#accordion'>" +
                    "<div class='card-body'>" + topics[name].description + "</div></div></div>");
            }

            $("#topics").append("</div>");
            console.log("Reloaded documents");
        })
    })

</script>
<%
    }
%>
</body>
</html>
