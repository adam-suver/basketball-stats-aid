package com.techelevator.dao;

import com.techelevator.model.Player;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.techelevator.exception.DaoException;

import java.util.List;

@Component
public class JdbcPlayerDao implements PlayerDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPlayerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Player> getAllPlayers() {

        return null;
    }

    @Override
    public void addPlayerToTable(int playerId, String firstName, String lastName) {
        String sql = "INSERT INTO player (player_id, first_name, last_name) " +
                "VALUES (?, ?, ?);";
        try {
            jdbcTemplate.update(sql, playerId, firstName, lastName);
    } catch(CannotGetJdbcConnectionException e) {
        throw new DaoException("Could not connect to data source");
    } catch(BadSqlGrammarException e) {
        throw new DaoException("Bad SQL grammar - Review the SQL statement syntax");
    } catch(DataIntegrityViolationException e) {
        throw new DaoException("Invalid operation - Data integrity error");
    }
    }


}
