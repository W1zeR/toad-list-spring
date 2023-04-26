package main.com.w1zer.repository;

import main.com.w1zer.entity.Toad;
import main.com.w1zer.exception.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ToadRepository {
    private final JdbcTemplate jdbcTemplate;

    public ToadRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Toad rowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Toad(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getObject("type", String.class),
                rs.getObject("weight", Long.class),
                rs.getObject("length", BigDecimal.class),
                rs.getObject("birthday", Date.class),
                rs.getObject("description", String.class),
                rs.getLong("id_profile"));
    }

    public List<Toad> getAll() {
        String SELECT_ALL_QUERY = "SELECT id, name, type, weight, length, birthday, description, id_profile FROM toad";

        return jdbcTemplate.query(SELECT_ALL_QUERY, this::rowMapper);
    }

    public Toad getById(Long id) {
        String SELECT_BY_ID_QUERY =
                "SELECT id, name, type, weight, length, birthday, description, id_profile FROM toad WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, this::rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Toad with id %d not found".formatted(id));
        }
    }

    public List<Toad> getByIdProfile(Long idProfile) {
        String SELECT_BY_ID_PROFILE_QUERY =
                "SELECT id, name, type, weight, length, birthday, description, id_profile FROM toad WHERE id_profile = ?";

        return jdbcTemplate.query(SELECT_BY_ID_PROFILE_QUERY, this::rowMapper, idProfile);
    }

    public void insert(Toad toad) {
        String INSERT_QUERY = "INSERT INTO toad (name, type, weight, length, birthday, description, id_profile) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(INSERT_QUERY, toad.getName(), toad.getType(), toad.getWeight(), toad.getLength(),
                toad.getBirthday(), toad.getDescription(), toad.getIdProfile());
    }

    public void delete(Long id) {
        String DELETE_QUERY = "DELETE FROM toad WHERE id = ?";

        jdbcTemplate.update(DELETE_QUERY, id);
    }

    public void update(Toad toad) {
        String UPDATE_QUERY =
                "UPDATE toad SET name = ?, type = ?, weight = ?, length = ?, birthday = ?, description = ? WHERE id = ?";

        jdbcTemplate.update(UPDATE_QUERY, toad.getName(), toad.getType(), toad.getWeight(), toad.getLength(),
                toad.getBirthday(), toad.getDescription(), toad.getId());
    }
}
