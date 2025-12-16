import java.time.LocalDateTime;

public class Comment {

    private String id;
    private String text;
    private LocalDateTime dateCreated;
    private User author;


    public Comment(String id, String text, User author) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.dateCreated = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
}
