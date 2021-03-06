package Model.Taglia;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagliaExtractor implements ResultSetExtractor<Taglia> {

    @Override
    public Taglia extract(ResultSet rs) throws SQLException {
        Taglia taglia = new Taglia();
        taglia.setId_nome(rs.getString("id_nome"));
        taglia.setQuantita(rs.getInt("Quantita"));
        return taglia;
    }
}
