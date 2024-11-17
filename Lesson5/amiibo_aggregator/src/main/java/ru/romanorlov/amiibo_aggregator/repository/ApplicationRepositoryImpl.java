package ru.romanorlov.amiibo_aggregator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import ru.romanorlov.amiibo_aggregator.model.entity.Amiibo;
import ru.romanorlov.amiibo_aggregator.model.entity.AmiiboSeries;
import ru.romanorlov.amiibo_aggregator.model.entity.GameSeries;
import ru.romanorlov.amiibo_aggregator.repository.mapper.AmiiboMapper;
import ru.romanorlov.amiibo_aggregator.repository.mapper.AmiiboSeriesMapper;
import ru.romanorlov.amiibo_aggregator.repository.mapper.GameSeriesMapper;

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
    public void insertGameSeries(GameSeries gameSeries) {
        jdbcTemplate.update("INSERT INTO game_series VALUES (?) ON CONFLICT DO NOTHING", gameSeries.getTitle());
    }

    @Override
    public void insertGameSeries(List<GameSeries> gameSeries) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(gameSeries.toArray());
        namedParameterJdbcTemplate.batchUpdate("INSERT INTO game_series VALUES (:title) ON CONFLICT DO NOTHING", batch);
    }

    @Override
    public GameSeries findGameSeriesById(String title) {
        return jdbcTemplate.queryForObject("SELECT * FROM game_series WHERE title = ?", new GameSeriesMapper(), title);
    }

    @Override
    public void insertAmiiboSeries(AmiiboSeries amiiboSeries) {
        jdbcTemplate.update("INSERT INTO amiibo_series (title, game_series_title) VALUES (?, ?) ON CONFLICT DO NOTHING",
                amiiboSeries.getTitle(), amiiboSeries.getGameSeriesTitle());
    }

    @Override
    public void insertAmiiboSeries(List<AmiiboSeries> amiiboSeriesList) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(amiiboSeriesList.toArray());
        namedParameterJdbcTemplate.batchUpdate("INSERT INTO amiibo_series (title, game_series_title) VALUES (:title, :gameSeriesTitle) ON CONFLICT DO NOTHING", batch);
    }

    @Override
    public AmiiboSeries findAmiiboSeriesByTitle(String title) {
        return jdbcTemplate.queryForObject("SELECT * FROM amiibo_series WHERE title = ?", new AmiiboSeriesMapper(), title);
    }

    @Override
    public void insertAmiibo(Amiibo amiibo) {
        jdbcTemplate.update("INSERT INTO amiibo (amiibo_series_title, game_series_title, character, image_link, name, type) VALUES (?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING",
                amiibo.getAmiiboSeriesTitle(), amiibo.getGameSeriesTitle(), amiibo.getCharacter(), amiibo.getImageLink(), amiibo.getName(), amiibo.getType());
    }

    @Override
    public void insertAmiibo(List<Amiibo> amiiboList) {
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(amiiboList.toArray());
        namedParameterJdbcTemplate.batchUpdate("INSERT INTO amiibo (amiibo_series_title, game_series_title, character, image_link, name, type) VALUES (:amiiboSeriesTitle, :gameSeriesTitle, :character, :imageLink, :name, :type) ON CONFLICT DO NOTHING", batch);
    }

    @Override
    public Amiibo findAmiiboById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM amiibo WHERE id = ?", new AmiiboMapper(), id);
    }

    @Override
    public List<Amiibo> findAllAmiibos() {
        return jdbcTemplate.queryForList("SELECT * FROM amiibo", Amiibo.class);
    }

    @Override
    public Amiibo findAmiiboByCharacter(String character) {
        return jdbcTemplate.queryForObject("SELECT * FROM amiibo WHERE character = ?", new AmiiboMapper(), character);
    }

    @Override
    public void deleteInfoFromTables() {
        jdbcTemplate.execute(
                "DELETE FROM amiibo;" +
                "DELETE FROM amiibo_series;" +
                "DELETE FROM game_series;" +
                "ALTER SEQUENCE amiibo_id_seq RESTART;");
    }
}
