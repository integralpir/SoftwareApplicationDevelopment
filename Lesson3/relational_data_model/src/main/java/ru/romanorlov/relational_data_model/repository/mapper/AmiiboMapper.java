package ru.romanorlov.relational_data_model.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.romanorlov.relational_data_model.model.entity.Amiibo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AmiiboMapper implements RowMapper<Amiibo> {
    @Override
    public Amiibo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Amiibo amiibo = new Amiibo();

        amiibo.setAmiiboSeriesTitle(rs.getString("amiibo_series_title"));
        amiibo.setGameSeriesTitle(rs.getString("game_series_title"));
        amiibo.setCharacter(rs.getString("character"));
        amiibo.setImageLink(rs.getString("image_link"));
        amiibo.setName(rs.getString("name"));
        amiibo.setType(rs.getString("type"));

        return amiibo;
    }
}
