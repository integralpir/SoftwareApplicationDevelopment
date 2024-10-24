package ru.romanorlov.relational_data_model.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.romanorlov.relational_data_model.model.entity.AmiiboSeries;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AmiiboSeriesMapper implements RowMapper<AmiiboSeries> {
    @Override
    public AmiiboSeries mapRow(ResultSet rs, int rowNum) throws SQLException {
        AmiiboSeries amiiboSeries = new AmiiboSeries();

        amiiboSeries.setTitle(rs.getString("title"));
        amiiboSeries.setGameSeriesTitle(rs.getString("game_series_title"));

        return amiiboSeries;
    }
}