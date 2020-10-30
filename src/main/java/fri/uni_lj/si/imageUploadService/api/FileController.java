package fri.uni_lj.si.imageUploadService.api;

import fri.uni_lj.si.imageUploadService.models.FileData;
import fri.uni_lj.si.imageUploadService.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/files")
@CrossOrigin("*")
public class FileController {

    private final FileService fileService;

    // krnekig
    @Autowired
    public FileController(FileService is) {
        this.fileService = is;
    }

    @GetMapping
    public List<FileData> getImages() {
        return fileService.getFiles();
    }

    @PostMapping
    public FileData insertImageData (@RequestBody FileData i) {
        return fileService.insertFileData(i);
    }

    @GetMapping(path = "{id}")
    public FileData getImageDataById (@PathVariable("id") UUID id) {
        return fileService.getFileDataById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteImageDataById (@PathVariable("id") UUID id) {
        fileService.deleteFileDataById(id);
    }
}
