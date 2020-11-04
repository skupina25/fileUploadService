package fri.uni_lj.si.fileUploadService.api;

import fri.uni_lj.si.fileUploadService.models.FileData;
import fri.uni_lj.si.fileUploadService.services.FileService;
import fri.uni_lj.si.fileUploadService.services.FileStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
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
    public List<FileData> getFiles() {
        return fileService.getFiles();
    }

    @PostMapping(
            path = "upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )

    public FileData insertFileData (@RequestParam("file") MultipartFile file) {
        FileData fd = new FileData(UUID.randomUUID(), file.getOriginalFilename(), "");
        return fileService.insertFileData(fd, file);
    }

    @GetMapping(path = "{id}")
    public FileData getFileDataById (@PathVariable("id") UUID id) {
        return fileService.getFileDataById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteFileDataById (@PathVariable("id") UUID id) {
        fileService.deleteFileDataById(id);
    }
}
