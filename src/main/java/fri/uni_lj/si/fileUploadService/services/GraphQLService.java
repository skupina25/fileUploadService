package fri.uni_lj.si.fileUploadService.services;

import fri.uni_lj.si.fileUploadService.services.datafetchers.AllFilesDataFetcher;
import fri.uni_lj.si.fileUploadService.services.datafetchers.FileByIdDataFetcher;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

@Service
public class GraphQLService {

    @Value("classpath:filedata.graphql")
    Resource resource;

    private GraphQL graphQL;

    @Autowired
    private AllFilesDataFetcher allFilesDataFetcher;

    @Autowired
    private FileByIdDataFetcher fileByIdDataFetcher;

    @PostConstruct
    private void loadSchema() throws IOException {
        InputStream schemaStream = resource.getInputStream();

        StringWriter writer = new StringWriter();
        IOUtils.copy(schemaStream, writer, "UTF-8");
        String schemaString = writer.toString();

        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaString);
        RuntimeWiring runtimeWiring = RuntimeWiring
                .newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allFiles", allFilesDataFetcher)
                        .dataFetcher("fileById", fileByIdDataFetcher)
                )
                .build();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        graphQL = GraphQL.newGraphQL(schema).build();
    }

    public GraphQL getGraphQL() {
        return graphQL;
    }
}
