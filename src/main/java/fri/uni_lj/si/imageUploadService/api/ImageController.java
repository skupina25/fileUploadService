package fri.uni_lj.si.imageUploadService.api;

import fri.uni_lj.si.imageUploadService.models.ImageData;
import fri.uni_lj.si.imageUploadService.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/images")
@CrossOrigin("*")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService is) {
        this.imageService = is;
    }

    @GetMapping
    public List<ImageData> getImages() {
        return imageService.getImages();
    }

    @PostMapping
    public ImageData insertImageData (@RequestBody ImageData i) {
        return imageService.insertImageData(i);
    }

    @GetMapping(path = "{id}")
    public ImageData getImageDataById (@PathVariable("id") UUID id) {
        return imageService.getImageDataById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteImageDataById (@PathVariable("id") UUID id) {
        imageService.deleteImageDataById(id);
    }
}
