package main.com.w1zer.repository;

import main.com.w1zer.entity.Profile;
import main.com.w1zer.exception.NotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProfileRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProfileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Profile rowMapper(ResultSet rs, int rowNum) throws SQLException {
        return new Profile(
                rs.getLong("id"),
                rs.getString("login"),
                rs.getString("password"));
    }

    public List<Profile> getAll() {
        String SELECT_ALL_QUERY = "SELECT id, login, password FROM profile";

        return jdbcTemplate.query(SELECT_ALL_QUERY, this::rowMapper);
    }

    public Profile getById(Long id) {
        String SELECT_BY_ID_QUERY = "SELECT id, login, password FROM profile WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, this::rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Profile with id %d not found".formatted(id));
        }
    }

    public Profile getByLogin(String login) {
        String SELECT_BY_LOGIN_QUERY = "SELECT id, login, password FROM profile WHERE login = ?";

        try {
            return jdbcTemplate.queryForObject(SELECT_BY_LOGIN_QUERY, this::rowMapper, login);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Profile with login %s not found".formatted(login));
        }
    }

    public void insert(Profile profile) {
        String INSERT_QUERY = "INSERT INTO profile (login, password) VALUES (?, ?)";

        jdbcTemplate.update(INSERT_QUERY, profile.getLogin(), profile.getPassword());
    }

    public void delete(Long id) {
        String DELETE_QUERY = "DELETE FROM profile WHERE id = ?";

        jdbcTemplate.update(DELETE_QUERY, id);
    }

    public void update(Profile profile) {
        String UPDATE_QUERY = "UPDATE profile SET login = ?, password = ? WHERE id = ?";

        jdbcTemplate.update(UPDATE_QUERY, profile.getLogin(), profile.getPassword(), profile.getId());
    }

}
