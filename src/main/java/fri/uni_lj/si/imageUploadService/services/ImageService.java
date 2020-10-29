package fri.uni_lj.si.imageUploadService.services;

import fri.uni_lj.si.imageUploadService.models.ImageData;
import fri.uni_lj.si.imageUploadService.models.ImageDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {

    private final ImageDataDao imageDataDao;

    @Autowired
    public ImageService(@Qualifier("postgres") ImageDataDao imageDataDao) {
        this.imageDataDao = imageDataDao;
    }

    public List<ImageData> getImages() {
        return imageDataDao.getImages();
    }

    public Optional<ImageData> getImageDataById (UUID id) {
        return imageDataDao.getImageById(id);
    }

    public ImageData insertImageData (ImageData img) {
        return imageDataDao.insertImageData(img);
    }

    public int deleteImageDataById (UUID id) {
        return  imageDataDao.deleteImageDataById(id);
    }
}
