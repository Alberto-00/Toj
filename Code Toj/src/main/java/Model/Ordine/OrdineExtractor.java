package Model.Ordine;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdineExtractor implements ResultSetExtractor<Ordine> {

    @Override
    public Ordine extract(ResultSet rs) throws SQLException {
        Ordine ordine = new Ordine();
        ordine.setID_ordine(rs.getString("ID_ordine"));
        ordine.setData_acquisto(rs.getDate("data_acquisto").toLocalDate());
        ordine.setPacchetto_regalo(rs.getBoolean("pacchetto_regalo"));
        ordine.setData_spedizione(rs.getDate("data_spedizione").toLocalDate());
        ordine.setDescrizione(rs.getString("Descrizione"));
        return ordine;
    }
}
