package fri.uni_lj.si.fileUploadService.models;

public class RecognitionLabel {
    private String name;
    private Float confidence;

    public RecognitionLabel(String name, Float confidence) {
        this.name = name;
        this.confidence = confidence;
    }

    public Float getConfidence() {
        return confidence;
    }

    public String getName() {
        return name;
    }
}
