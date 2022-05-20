<%@ page import="ro.ubb.Assignment8.Domain.User" %><%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 5/16/2022
  Time: 2:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My topics</title>
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
<section id="add_topic">
    <button id="addTopicButton" class="btn btn-outline-info" style="margin-left: 700px">Add a topic</button>
    <form id="addForm" style="display: none; margin-left: 700px" action="TopicsController" method="post">
        <div class="form-field">
            <input type="text" placeholder="Enter title" name="title" required/>
        </div>

        <div class="form-field">
            <input type="text" placeholder="Enter description" name="description" required/>
        </div>

        <div class="form-field">
            <button class="btn" id="addedTopic" type="submit">Add</button>
        </div>
    </form>
</section>
<section id="topics">

</section>
<%
    }
%>



<%--<script src="//code.jquery.com/jquery.min.js"></script>--%>
<script>
    $.get("navbar.html", function(data){
        $("#nav-placeholder").replaceWith(data);
        $("#username").innerHTML(<%= user.getName()%>)
    });

    $(document).ready(function(){
        $("#addTopicButton").click(function(){
            console.log(1);
            if($("#addForm").css('display') === 'none')
                $("#addForm").css('display', 'block');
            else{
                $("#addForm").css('display', 'none');
            }
        })

        getUserTopics(<%= user.getId()%>, function(topics){
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
        })
        console.log("Reloaded documents");
    })

</script>
</body>
</html>