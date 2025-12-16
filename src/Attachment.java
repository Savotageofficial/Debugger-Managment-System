
public class Attachment {
    private String id;
    private String fileName;
    private String filePath;
    private Long size;
    private String mimeType;

    public Attachment(String id, String fileName, String filePath, Long size, String mimeType) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.size = size;
        this.mimeType = mimeType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public String getMimeType() {
        return mimeType;
    }
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
