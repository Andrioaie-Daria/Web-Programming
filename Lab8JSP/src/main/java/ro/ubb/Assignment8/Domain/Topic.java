package ro.ubb.Assignment8.Domain;

public class Topic {
    private int id;
    private String title;
    private String description;
    private int authorId;

    private int numberOfComments;

    public Topic(int id, String title, String description, int authorId){
        this.id  = id;
        this.title = title;
        this.description = description;
        this.authorId = authorId;
        this.numberOfComments = 0;
    }

    public Topic(int id, String title, String description, int authorId, int number_of_comments){
        this.id  = id;
        this.title = title;
        this.description = description;
        this.authorId = authorId;
        this.numberOfComments = number_of_comments;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int id) {
        this.authorId = id;
    }

    public int getNumberOfComments(){
        return this.numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments){
        this.numberOfComments = numberOfComments;
    }
}
