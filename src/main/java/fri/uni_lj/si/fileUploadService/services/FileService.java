package fri.uni_lj.si.fileUploadService.services;

import fri.uni_lj.si.fileUploadService.models.FileData;
import fri.uni_lj.si.fileUploadService.models.FileDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {

    private final FileDataDao fileDataDao;

    @Autowired
    public FileService(@Qualifier("postgres") FileDataDao fileDataDao) {
        this.fileDataDao = fileDataDao;
    }

    public List<FileData> getFiles() {
        return fileDataDao.getFiles();
    }

    public Optional<FileData> getFileDataById (UUID id) {
        return fileDataDao.getFileById(id);
    }

    public FileData insertFileData (FileData fd) {
        return fileDataDao.insertFileData(fd);
    }

    public int deleteFileDataById (UUID id) {
        return  fileDataDao.deleteFileDataById(id);
    }
}
