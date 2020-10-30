package fri.uni_lj.si.fileUploadService.api;

import fri.uni_lj.si.fileUploadService.models.FileData;
import fri.uni_lj.si.fileUploadService.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public FileData insertFileData (@RequestBody FileData i) {
        return fileService.insertFileData(i);
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
