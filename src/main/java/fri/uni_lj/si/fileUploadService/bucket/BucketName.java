package fri.uni_lj.si.fileUploadService.bucket;

public enum BucketName {

    BUCKET_NAME("skupina25-aws-bucket");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return this.bucketName;
    }
}
