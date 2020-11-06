package fri.uni_lj.si.fileUploadService.api;

import fri.uni_lj.si.fileUploadService.models.FileData;
import fri.uni_lj.si.fileUploadService.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/files")
@CrossOrigin("*")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService is) {
        this.fileService = is;
    }

    @GetMapping
    public ResponseEntity<Object> getFiles() {
        List<FileData> allFiles = fileService.getFiles();
        return ResponseEntity.status(HttpStatus.OK).body(allFiles);
    }

    @PostMapping(
            path = "upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> insertFileData (@RequestParam("file") MultipartFile file) {
        FileData fd = new FileData(UUID.randomUUID(), file.getOriginalFilename(), "");
        FileData insertedFileData = fileService.insertFileData(fd, file);

        if (insertedFileData.getTitle().equals("fail") && insertedFileData.getUri().equals("fail")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save data to DB.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(insertedFileData);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getFileDataById (@PathVariable("id") UUID id) {
        if (id.toString().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id of file is a required param");
        }

        FileData fd = fileService.getFileDataById(id).orElse(null);

        if (fd == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(fd);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteFileDataById (@PathVariable("id") UUID id) {
        if (id.toString().equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id of file is a required param");
        }

        int isDeleted = fileService.deleteFileDataById(id);

        if (isDeleted == 1) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("FileData with id: " + id + " was succesfully deleted.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FileData with id: " + id + " was not found.");
    }
}
