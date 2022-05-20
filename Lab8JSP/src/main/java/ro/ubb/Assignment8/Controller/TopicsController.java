package ro.ubb.Assignment8.Controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

public class TopicsController extends HttpServlet {
    private void printArrayToResponse(ArrayList<Topic> topics, HttpServletResponse response) throws IOException {
        JSONArray jsonTopics = new JSONArray();
        for (Topic topic : topics) {
            JSONObject jsonTopic = new JSONObject();
            jsonTopic.put("id", topic.getId());
            jsonTopic.put("title", topic.getTitle());
            jsonTopic.put("description", topic.getDescription());
            jsonTopic.put("number_of_comments", topic.getNumberOfComments());
            jsonTopics.add(jsonTopic);
        }
        PrintWriter out = new PrintWriter(response.getOutputStream());
        out.println(jsonTopics.toJSONString());
        out.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null && action.equals("getAll")){
            response.setContentType("application/JSON");
            DBManager dbManager = new DBManager();
            ArrayList<Topic> topics = dbManager.getAllTopics();
            printArrayToResponse(topics, response);
        }
        else if(action != null && action.equals("getUserTopics")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            System.out.println("Topic controller: get topics for user " + userId);
            response.setContentType("application/JSON");
            DBManager dbManager = new DBManager();
            ArrayList<Topic> topics = dbManager.getUserTopics(userId);
            printArrayToResponse(topics, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBManager dbManager = new DBManager();
        User user = (User)request.getSession().getAttribute("user");
        int userId = user.getId();
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Topic newTopic = new Topic(0, title, description, userId);
        dbManager.addNewTopic(newTopic);
        response.sendRedirect("userTopics.jsp");
    }
}
