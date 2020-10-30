package fri.uni_lj.si.fileUploadService.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class FileData {

    private final UUID id;
    private String title;
    private String description;
    private String uri;

    public FileData(@JsonProperty("id") UUID id, @JsonProperty("title") String title,
                    @JsonProperty("description") String description, @JsonProperty("uri") String uri) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.uri = uri;
    }

    public UUID getId() {
        return id;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
