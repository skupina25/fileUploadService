package fri.uni_lj.si.imageUploadService.models;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageDataDao {

    ImageData insertImageData (UUID id, ImageData i);

    default ImageData insertImageData (ImageData i) {
        UUID id = UUID.randomUUID();
        return insertImageData(id, i);
    }

    List<ImageData> getImages();

    Optional<ImageData> getImageById (UUID id);

    int deleteImageDataById (UUID id);
}
