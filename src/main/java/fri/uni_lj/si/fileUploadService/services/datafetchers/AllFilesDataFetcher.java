package fri.uni_lj.si.fileUploadService.services.datafetchers;

import fri.uni_lj.si.fileUploadService.models.FileData;
import fri.uni_lj.si.fileUploadService.repository.FileDataRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllFilesDataFetcher implements DataFetcher<List<FileData>> {

    @Autowired
    FileDataRepository fileDataRepository;

    @Override
    public List<FileData> get(DataFetchingEnvironment dataFetchingEnvironment) {
        return fileDataRepository.findAll();
    }
}
