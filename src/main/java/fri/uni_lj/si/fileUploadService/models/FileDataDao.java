package fri.uni_lj.si.fileUploadService.models;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileDataDao {

    FileData insertFileData (UUID id, FileData i);

    default FileData insertFileData (FileData i) {
        UUID id = UUID.randomUUID();
        return insertFileData(id, i);
    }

    List<FileData> getFiles();

    Optional<FileData> getFileById (UUID id);

    int deleteFileDataById (UUID id);
}
