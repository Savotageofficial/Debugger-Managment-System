import java.time.LocalDateTime;

/**
 * Represents a comment added to a bug report.
 * A comment is used to record discussion, feedback, or status-related notes
 * made by a user (Admin, Developer, or Tester).
 */
public class Comment {

    // Unique identifier for the comment
    private String id;

    // The actual comment text/content
    private String text;

    // Timestamp indicating when the comment was created
    private LocalDateTime dateCreated;

    // The user who authored the comment
    private User author;

    /**
     * Creates a new comment with the given id, text, and author.
     * The creation date is automatically set at the time of creation.
     */
    public Comment(String id, String text, User author) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.dateCreated = LocalDateTime.now();
    }

    // Getter for comment ID
    public String getId() {
        return id;
    }

    // Setter for comment ID
    public void setId(String id) {
        this.id = id;
    }

    // Getter for comment text
    public String getText() {
        return text;
    }

    /**
     * Updates the comment text.
     * This can be used if editing comments is allowed in the system.
     */
    public void setText(String text) {
        this.text = text;
    }

    // Getter for comment creation timestamp
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * Updates the creation date of the comment.
     * Typically not used, but provided for flexibility or file-loading scenarios.
     */
    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    // Getter for the author of the comment
    public User getAuthor() {
        return author;
    }

    /**
     * Updates the author of the comment.
     * Useful when reconstructing objects from persisted storage.
     */
    public void setAuthor(User author) {
        this.author = author;
    }
}
