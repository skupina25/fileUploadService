package fri.uni_lj.si.fileUploadService.services.datafetchers;

import fri.uni_lj.si.fileUploadService.models.FileData;
import fri.uni_lj.si.fileUploadService.repository.FileDataRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileByIdDataFetcher implements DataFetcher<FileData> {

    @Autowired
    FileDataRepository fileDataRepository;

    @Override
    public FileData get(DataFetchingEnvironment dataFetchingEnvironment) {
        Long id = Long.valueOf(dataFetchingEnvironment.getArgument("id").toString());

        return fileDataRepository.findById(id).orElse(null);
    }
}
