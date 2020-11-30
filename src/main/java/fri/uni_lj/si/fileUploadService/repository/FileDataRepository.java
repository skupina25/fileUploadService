package fri.uni_lj.si.fileUploadService.repository;

import fri.uni_lj.si.fileUploadService.models.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDataRepository extends JpaRepository<FileData, Long> {
}
