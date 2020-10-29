package fri.uni_lj.si.imageUploadService.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class DBAccessService implements ImageDataDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ImageData insertImageData(UUID id, ImageData i) {
        final String sql = "INSERT INTO imageData (id, title, description, uri) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, new Object[] { id, i.getTitle(), i.getDescription(), i.getUri() });
        return new ImageData(id, i.getTitle(), i.getDescription(), i.getUri());
    }

    @Override
    public List<ImageData> getImages() {
        final String sql = "SELECT id, title, description, uri FROM imageData";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID imgId = UUID.fromString(resultSet.getString("id"));
            String imgTitle = resultSet.getString("title");
            String imgDesc = resultSet.getString("description");
            String imgUri = resultSet.getString("uri");
            return new ImageData(imgId, imgTitle, imgDesc, imgUri);
        });
    }

    @Override
    public Optional<ImageData> getImageById(UUID id) {
        final String sql = "SELECT id, title, description, uri FROM imageData WHERE id = ?";
        ImageData imgData = jdbcTemplate.queryForObject(sql, new Object[] { id }, (resultSet, i) -> {
            UUID imgId = UUID.fromString(resultSet.getString("id"));
            String imgTitle = resultSet.getString("title");
            String imgDesc = resultSet.getString("description");
            String imgUri = resultSet.getString("uri");
            return new ImageData(imgId, imgTitle, imgDesc, imgUri);
        });
        return Optional.ofNullable(imgData);
    }

    @Override
    public int deleteImageDataById(UUID id) {
        final String sql = "DELETE FROM imageData WHERE id = ?";
        return jdbcTemplate.update(sql, new Object[] { id });
    }
}
