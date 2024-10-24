package ru.romanorlov.relational_data_model.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import ru.romanorlov.relational_data_model.model.entity.GameSeries;
import ru.romanorlov.relational_data_model.repository.mapper.GameSeriesMapper;

import java.util.List;

@Repository
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ApplicationRepositoryImpl(JdbcTemplate jdbcTemplate,
                                     NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void loadData() {
        dropTables();
        //TODO доделать
    }

    @Override
    public void insertGameSeries(GameSeries gameSeries) {
        jdbcTemplate.update("INSERT INTO game_series VALUES (?)", gameSeries.getTitle());
    }

    @Override
    public void insertGameSeries(List<GameSeries> gameSeries) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(gameSeries.toArray());
        namedParameterJdbcTemplate.batchUpdate("INSERT INTO game_series VALUES (:title)", batch);
    }

    public GameSeries findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM game_series WHERE id = ?", new GameSeriesMapper(), id);
    }

    private void dropTables() {
        jdbcTemplate.execute(
            "DELETE FROM amiibo_amiibo_series;" +
                "DELETE FROM amiibo_game_series;" +
                "DELETE FROM amiibo_series;" +
                "DELETE FROM game_series;" +
                "DELETE FROM release;" +
                "DELETE FROM amiibo;" +
                "ALTER SEQUENCE amiibo_id_seq RESTART;" +
                "ALTER SEQUENCE release_id_seq RESTART;" +
                "ALTER SEQUENCE game_series_id_seq RESTART;" +
                "ALTER SEQUENCE amiibo_series_id_seq RESTART;");
    }
}
