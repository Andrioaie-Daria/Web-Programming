<%@ page import="ro.ubb.Assignment8.Domain.User" %>
<%@ page import="ro.ubb.Assignment8.Domain.Topic" %><%--
  Created by IntelliJ IDEA.
  User: daria
  Date: 5/17/2022
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Topic</title>
    <script src="js/jquery-2.0.3.js"></script>
    <script src="js/ajax-utils.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

</head>
<body>
<%

    User user = (User)session.getAttribute("user");
    //Topic topic = (Topic)session.getAttribute("topic");
    if(user == null){
        response.sendRedirect("index.jsp");
        return;
    }
    else{
%>
<div id="nav-placeholder"></div>

<section id="comments">

</section>
<section id="add_comment">

    <form id="addCommentForm" style=" margin-left: 100px" action="CommentsController" method="post">
        <div class="form-field">
            <input type="text" placeholder="Your comment" name="text" required/>
        </div>

        <div class="form-field">
            <input id="topicInput" type="hidden" name="topicid" required/>
        </div>
        <div class="form-field">
            <button class="btn" id="addedComment" type="submit">Post</button>
        </div>
    </form>
</section>
<%
    }
%>

<%--<script src="//code.jquery.com/jquery.min.js"></script>--%>
<script>
    $.get("navbar.html", function(data){
        $("#nav-placeholder").replaceWith(data);
    });

    let topicid = ${param.topicid};
    console.log("The id from the get request is " + topicid);
    topicid = topicid.toString();
    $(document).ready(function(){
        document.getElementById("topicInput").setAttribute('value',topicid);

        // $("#topicInput").value = topicid;
        // console.log($("#topicInput").value);
        console.log(document.getElementById("topicInput").getAttribute('value'));


        getCommentsForTopic(topicid, function(comments){
            // console.log(comments);
            // $("#comments").html("");
            // $("#comments").append("<ol class='list-group list-group-numbered'>");
            // for(const name in comments){
            //     $("#comments").append("<li class='list-group-item d-flex justify-content-between align-items-start'>" +
            //         " <div class='ms-2 me-auto'>" +
            //         "<div class='fw-bold'>" + comments[name].userId+ "</div>" + comments[name].text +
            //         "</div>" + "<span class='badge bg-primary rounded-pill'><button id='deleteButton'></button></span>" + "</li>");
            // }
            //
            // $("#comments").append("</ol>");


            console.log(comments);
            $("#comments").html("");
            $("#comments").append("<div id='accordion'>");
            for(const name in comments){
                let heading = 'heading' + comments[name].id;
                let collapse = 'collapse' + comments[name].id;
                let deleteBtn = 'delete' + comments[name].id;

                $("#comments").append("<div class='card'>" +
                    "<div class='card-header' id='" + heading + "'>" +
                    "<h6 class='mb-0'> " +
                    "<button class='btn btn-link collapsed' data-toggle='collapse' data-target='" + collapse + "' aria-expanded='false' aria-controls='" + collapse + "'>User " +
                     comments[name].userId + "</button></h6></div>" +
                    "<div id='" + collapse + "' class='collapse show' aria-labelledby='" + heading + "' data-parent='#accordion'>" +
                    "<div class='card-body'>" + comments[name].text + "<button id='" + deleteBtn + "' hidden class='btn btn-outline-info' onclick='deleteComment(" + comments[name].id+ ");'>Delete</button></div></div></div>");


                if(comments[name].userId === <%=user.getId()%>){
                    document.getElementById(deleteBtn).removeAttribute("hidden");
                }
            }

            $("#comments").append("</div>");

        })
    })

</script>

</body>
</html>
