package fri.uni_lj.si.fileUploadService.services;

import fri.uni_lj.si.fileUploadService.bucket.BucketName;
import fri.uni_lj.si.fileUploadService.models.FileData;
import fri.uni_lj.si.fileUploadService.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class FileService {

    @Autowired
    private FileDataRepository fileDataRepository;
    private final FileStore fileStore;

    @Autowired
    public FileService(FileStore fileStore) {
        this.fileStore = fileStore;
    }

    @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 100))
    public List<FileData> getFiles() {
        return fileDataRepository.findAll();
    }
    
    @Recover
    public List<FileData> getFilesRecover() {
        List<FileData> recoverList = new ArrayList<>();
        FileData fd = new FileData();
        fd.setTitle("Recovery title");
        fd.setUri("https://www.planetware.com/wpimages/2020/02/france-in-pictures-beautiful-places-to-photograph-eiffel-tower.jpg");
        recoverList.add(fd);
        return recoverList;
    }

    public FileData getFileDataById (Long id) {
        Optional<FileData> fileData = fileDataRepository.findById(id);
        if (fileData.isPresent())
            return fileData.get();
        return null;
    }

    public FileData insertFileData (FileData fd, MultipartFile file) {
        String fName = pushFileToS3(file);
        String regionName = "eu-central-1";

        fd.setUri("https://" + BucketName.BUCKET_NAME.getBucketName() + ".s3." + regionName + ".amazonaws.com/files/" + fName);
        return fileDataRepository.save(fd);
    }

    public void deleteFileDataById (Long id) {
        Optional<FileData> fileData = fileDataRepository.findById(id);
        if (fileData.isPresent()) {
            fileStore.deleteFile(BucketName.BUCKET_NAME.getBucketName(), fileData.get().getUri().split("amazonaws.com/")[1]);
        }
        fileDataRepository.deleteById(id);
    }

    /*
    *
    * Helper functions
    *
    **/
    private String pushFileToS3(MultipartFile file) {
        // Check if file is empty.
        isFileEmpty(file);

        // Check if file is image.
        checkFileType(file);

        // Grab some metadata from file if there is some.
        Map<String, String> metadata = extractMetadata(file);

        // Store the file to S3 and save data to DB.
        String path = String.format("%s/files", BucketName.BUCKET_NAME.getBucketName());
        String fileName = formatNewFileName(file, UUID.randomUUID());

        try {
            fileStore.saveFile(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return fileName;
    }

    private String formatNewFileName(MultipartFile file, UUID randomUUID) {
        String[] splitFileName = file.getOriginalFilename().split("\\.");
        return String.format("%s_%s.%s", splitFileName[0], randomUUID, splitFileName[1]);
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private void checkFileType(MultipartFile file) {
        if (!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image, but it is of type [" + file.getContentType() + "].");
        }
    }

    private void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Can not upload empty file [" + file.getSize() + "]");
        }
    }
}
