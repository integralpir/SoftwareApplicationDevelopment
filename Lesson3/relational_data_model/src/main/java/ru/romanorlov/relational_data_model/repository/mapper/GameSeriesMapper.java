package ru.romanorlov.relational_data_model.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.romanorlov.relational_data_model.model.entity.GameSeries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameSeriesMapper implements RowMapper<GameSeries> {
    @Override
    public GameSeries mapRow(ResultSet rs, int rowNum) throws SQLException {
        GameSeries gameSeries = new GameSeries();

        gameSeries.setId(rs.getInt("id"));
        gameSeries.setTitle(rs.getString("title"));

        return gameSeries;
    }
}
