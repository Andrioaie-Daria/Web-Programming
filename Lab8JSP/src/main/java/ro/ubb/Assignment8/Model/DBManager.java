package ro.ubb.Assignment8.Model;

import ro.ubb.Assignment8.Domain.Comment;
import ro.ubb.Assignment8.Domain.Topic;
import ro.ubb.Assignment8.Domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DBManager {
    private Statement statement;
    private static Connection connection;

    public DBManager(){
        connect();
    }

    public void connect(){
        try{
            String url = "jdbc:mysql://localhost:3306/forum";
            //String driver = "com.mysql.jdbc.Driver";
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            connection = DriverManager.getConnection(url, "root", "");
            statement = connection.createStatement();

        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    public Connection getConnection() {
        if(connection == null)
            connect();
        return connection;
    }

    public User authenticate(String username, String password){
        ResultSet result;
        User user = null;
        try{

            String sql = ("SELECT * FROM users WHERE username='" + username + "' AND password='" + password +"'");
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            result = stmt.executeQuery();

            if(result.next()){
                user = new User(result.getInt("id"), result.getString("username") ,result.getString("name"), result.getString("password"));
            }
            result.close();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return user;
    }

    public User register(String username, String name, String password){
        ResultSet result;
        User user = null;
        try{
            String select_users = ("SELECT * FROM users WHERE username='" + username + "'");
            PreparedStatement select_statement = getConnection().prepareStatement(select_users);
            result = select_statement.executeQuery();

            if(!result.next()){
                String insert_user = ("INSERT INTO users(username, name, password) VALUES ('" + username + "', '" + name + "', '" + password + "')");
                PreparedStatement insert_statement = getConnection().prepareStatement(insert_user);
                int rows = insert_statement.executeUpdate();
                if(rows > 0){
                    ResultSet final_result = select_statement.executeQuery();
                    final_result.next();
                    user = new User(final_result.getInt("id"), final_result.getString("username") ,final_result.getString("name"), final_result.getString("password"));
                }
            }
            result.close();
        } catch (SQLException exception){
            exception.printStackTrace();
        }
        return user;
    }

    public ArrayList<Topic> getAllTopics(){
        ArrayList<Topic> topics = new ArrayList<>();
        try {
            String getAll_sql = "SELECT t.id, t.title, t.description, t.authorId, c.total FROM topics t LEFT JOIN (SELECT DISTINCT topicId, COUNT(*) AS total FROM comments) c ON t.id = c.topicId";
            PreparedStatement statement = getConnection().prepareStatement(getAll_sql);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                int count;
                if(Objects.equals(result.getString(5), "NULL")){
                    count = 0;
                }
                else{
                    count = result.getInt(5);
                }
                topics.add(new Topic(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), result.getInt(5)));
            }
            result.close();

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return topics;
    }

    public ArrayList<Topic> getUserTopics(int userId){
        ArrayList<Topic> topics = new ArrayList<>();
        try {
            String getAll_sql = "SELECT t.id, t.title, t.description, t.authorId, c.total FROM topics t LEFT JOIN (SELECT DISTINCT topicId, COUNT(*) AS total FROM comments) c ON t.id = c.topicId WHERE t.authorId=" + userId;
            PreparedStatement statement = getConnection().prepareStatement(getAll_sql);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                int count;
                if(Objects.equals(result.getString(5), "NULL")){
                    count = 0;
                }
                else{
                    count = result.getInt(5);
                }
                topics.add(new Topic(result.getInt(1), result.getString(2), result.getString(3), result.getInt(4), count));
            }
            result.close();

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return topics;
    }

    public void addNewTopic(Topic topic){
        try {
            String sql = "INSERT INTO topics(title, description, authorId) VALUES (?, ?, ?)";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setString(1, topic.getTitle());
            statement.setString(2, topic.getDescription());
            statement.setInt(3, topic.getAuthorId());
            statement.executeUpdate();

        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void addNewComment(Comment comment){
        try {
            String sql = "INSERT INTO comments(topicId, userId, text) VALUES (?, ?, ?)";
            PreparedStatement statement = getConnection().prepareStatement(sql);
            statement.setInt(1, comment.getTopicId());
            statement.setInt(2, comment.getUserId());
            statement.setString(3, comment.getText());
            statement.executeUpdate();

        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void deleteComment(int commentId, int userId){
        try {
            String select_users = ("SELECT * FROM comments WHERE id=" + commentId);
            PreparedStatement select_statement = getConnection().prepareStatement(select_users);
            ResultSet result = select_statement.executeQuery();
            result.next();
            int actualUserId = result.getInt(3);

            if(actualUserId == userId){
                String sql = "DELETE FROM comments WHERE id=" + commentId;
                PreparedStatement statement = getConnection().prepareStatement(sql);
                statement.executeUpdate();
                System.out.println("Delete is finished");
            }

        } catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public ArrayList<Comment> getAllCommentsForTopic(int topicId){
        ArrayList<Comment> comments = new ArrayList<>();
        try {
            String getAll_sql = "SELECT * FROM comments WHERE topicId=" + topicId;
            PreparedStatement statement = getConnection().prepareStatement(getAll_sql);
            ResultSet result = statement.executeQuery();

            while(result.next()){
                comments.add(new Comment(result.getInt(1), result.getInt(2), result.getInt(3), result.getString(4)));
            }
            result.close();

        }catch (SQLException exception){
            exception.printStackTrace();
        }
        return comments;
    }
}
