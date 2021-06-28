package Model.Dati_utente;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DatiUtenteExtractor implements ResultSetExtractor<DatiUtente> {

    @Override
    public DatiUtente extract(ResultSet rs) throws SQLException {
        DatiUtente dtUser = new DatiUtente();
        dtUser.setNome(rs.getString("Nome"));
        dtUser.setCognome(rs.getString("Cognome"));
        dtUser.setDataDiNascita(rs.getDate("ddn"));
        dtUser.setNumeroTelefonico(rs.getString("Telefono"));
        dtUser.setVia(rs.getString("Via"));
        dtUser.setCAP(rs.getString("CAP"));
        dtUser.setCity(rs.getString("city"));
        dtUser.setPaese(rs.getString("paese"));
        dtUser.setAppartamento(rs.getString("appartamento"));
        return dtUser;
    }
}
