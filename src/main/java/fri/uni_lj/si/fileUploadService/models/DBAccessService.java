package fri.uni_lj.si.fileUploadService.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class DBAccessService implements FileDataDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DBAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public FileData insertFileData(UUID id, FileData i) {
        final String sql = "INSERT INTO fileData (id, title, uri) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, new Object[] { id, i.getTitle(), i.getUri() });
            return new FileData(id, i.getTitle(), i.getUri());
        } catch (Exception e) {
            return new FileData(id, "fail", "fail");
        }
    }

    @Override
    public List<FileData> getFiles() {
        final String sql = "SELECT id, title, uri FROM fileData";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID fid = UUID.fromString(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String uri = resultSet.getString("uri");
            return new FileData(fid, title, uri);
        });
    }

    @Override
    public Optional<FileData> getFileById(UUID id) {
        final String sql = "SELECT id, title, uri FROM fileData WHERE id = ?";
        try {
            FileData data = jdbcTemplate.queryForObject(sql, new Object[] { id }, (resultSet, i) -> {
                UUID fid = UUID.fromString(resultSet.getString("id"));
                String title = resultSet.getString("title");
                String uri = resultSet.getString("uri");
                return new FileData(fid, title, uri);
            });
            return Optional.ofNullable(data);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public int deleteFileDataById(UUID id) {
        final String sql = "DELETE FROM fileData WHERE id = ?";
        return jdbcTemplate.update(sql, new Object[] { id });
    }
}
