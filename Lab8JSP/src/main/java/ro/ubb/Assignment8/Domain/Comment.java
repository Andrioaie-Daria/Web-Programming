package ro.ubb.Assignment8.Domain;

public class Comment {
    private int id;
    private String text;
    private int userId;
    private int topicId;

    public Comment(int id, int topicId, int userId, String text){
        this.id = id;
        this.text = text;
        this.userId = userId;
        this.topicId = topicId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public int getTopicId() { return topicId; }

    public void setTopicId(int id) {
        this.topicId = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
