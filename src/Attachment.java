/**
 * Represents an attachment associated with a bug report.
 * Attachments can include files such as screenshots, log files,
 * or any additional resources that help describe or reproduce a bug.
 */
public class Attachment {

    // Unique identifier for the attachment
    private String id;

    // Original name of the attached file
    private String fileName;

    // File system path where the attachment is stored
    private String filePath;

    // Size of the attachment file (in bytes)
    private Long size;

    // MIME type of the file (e.g., image/png, text/plain)
    private String mimeType;

    /**
     * Creates a new attachment with its metadata.
     * This constructor is typically used when attaching a file to a bug report
     * or when loading attachment data from persistent storage.
     */
    public Attachment(String id, String fileName, String filePath, Long size, String mimeType) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.size = size;
        this.mimeType = mimeType;
    }

    // Getter for attachment ID
    public String getId() {
        return id;
    }

    // Setter for attachment ID
    public void setId(String id) {
        this.id = id;
    }

    // Getter for the file path
    public String getFilePath() {
        return filePath;
    }

    /**
     * Updates the file path of the attachment.
     * Useful when files are moved or reloaded from storage.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // Getter for the file name
    public String getFileName() {
        return fileName;
    }

    /**
     * Updates the file name of the attachment.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // Getter for file size
    public Long getSize() {
        return size;
    }

    /**
     * Updates the size of the attachment.
     * Mainly used when reconstructing objects from saved data.
     */
    public void setSize(Long size) {
        this.size = size;
    }

    // Getter for MIME type
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Updates the MIME type of the attachment.
     * Helps identify how the file should be handled or displayed.
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
