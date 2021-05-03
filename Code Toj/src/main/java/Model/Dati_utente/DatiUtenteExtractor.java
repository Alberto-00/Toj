package Model.Dati_utente;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatiUtenteExtractor implements ResultSetExtractor<DatiUtente> {

    @Override
    public DatiUtente extract(ResultSet rs) throws SQLException {
        DatiUtente dtUser = new DatiUtente();
        dtUser.setNome(rs.getString("Nome"));
        dtUser.setCognome(rs.getString("Cognome"));
        dtUser.setDataDiNascita(rs.getDate("ddn").toLocalDate());
        dtUser.setNumeroTelefonico(rs.getString("Telefono"));
        dtUser.setVia(rs.getString("Via"));
        dtUser.setNumeroCivico(rs.getString("N_civico"));
        dtUser.setCAP(rs.getString("CAP"));
        return dtUser;
    }
}
