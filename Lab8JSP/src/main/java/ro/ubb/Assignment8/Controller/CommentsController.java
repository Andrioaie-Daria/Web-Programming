package ro.ubb.Assignment8.Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ro.ubb.Assignment8.Domain.Comment;
import ro.ubb.Assignment8.Domain.Topic;
import ro.ubb.Assignment8.Domain.User;
import ro.ubb.Assignment8.Model.DBManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CommentsController extends HttpServlet {

    private void printArrayToResponse(ArrayList<Comment> comments, HttpServletResponse response) throws IOException {
        JSONArray jsonComments = new JSONArray();
        for (Comment comment : comments) {
            JSONObject jsonComment = new JSONObject();
            jsonComment.put("id", comment.getId());
            jsonComment.put("topicId", comment.getTopicId());
            jsonComment.put("userId", comment.getUserId());
            jsonComment.put("text", comment.getText());
            jsonComments.add(jsonComment);
        }
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonComments.toJSONString());
        out.flush();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null && action.equals("getAllForTopic")){
            int topicId = Integer.parseInt(request.getParameter("topicId"));
            response.setContentType("application/JSON");
            DBManager dbManager = new DBManager();
            ArrayList<Comment> comments = dbManager.getAllCommentsForTopic(topicId);
            printArrayToResponse(comments, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBManager dbManager = new DBManager();
        User user = (User)request.getSession().getAttribute("user");
        int userId = user.getId();
        int topicId = Integer.parseInt(request.getParameter("topicid"));
        String text = request.getParameter("text");
        Comment newComment = new Comment(0, topicId, userId, text);
        dbManager.addNewComment(newComment);
        response.sendRedirect("topic.jsp?topicid="+topicId);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Hi");

        DBManager dbManager = new DBManager();
        User user = (User)request.getSession().getAttribute("user");
        int userId = user.getId();
        int commentId = Integer.parseInt(request.getParameter("commentId"));
        //int commentId = Integer.parseInt(request.getParameter());
        //int commentId = Integer.parseInt(request.getParameter("data"));
        System.out.println(userId + " wants to delete comment " + commentId);
        dbManager.deleteComment(commentId, userId);

        response.sendRedirect("home.jsp");
    }
}
