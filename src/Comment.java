public class Comment {
    private String Id;
    private String text;
    private String dataCreated;
    private User author;

    public Comment(String id, String text, String dataCreated, User author) {
        Id = id;
        this.text = text;
        this.dataCreated = dataCreated;
        this.author = author;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(String dataCreated) {
        this.dataCreated = dataCreated;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
