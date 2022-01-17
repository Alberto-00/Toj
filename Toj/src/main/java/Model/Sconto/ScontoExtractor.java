package Model.Sconto;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScontoExtractor implements ResultSetExtractor<Sconto> {

    @Override
    public Sconto extract(ResultSet rs) throws SQLException {
        Sconto sconto = new Sconto();
        sconto.setCodice(rs.getString("codice"));
        sconto.setDataScadenza(rs.getDate("data_scadenza").toLocalDate());
        sconto.setSconto(rs.getDouble("sconto"));
        return sconto;
    }
}
