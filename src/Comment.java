import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private String id;
    private String text;
    private LocalDateTime dateCreated;
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String authorid;

    public Comment(String id, String text, String author) {
        this.id = id;
        this.text = text;
        this.authorid = author;
        this.dateCreated = LocalDateTime.now();
    }
    public Comment(String id, String text, String datecreated , String author) {
        this.id = id;
        this.text = text;
        this.authorid = author;
        this.dateCreated = LocalDateTime.parse(datecreated, format);
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

    public String getAuthor() {

        return authorid;
    }

    public void setAuthor(String author) {

        this.authorid = author;
    }
}
