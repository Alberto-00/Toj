package Model.Dati_utente;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class SQLDatiUtenteDAO implements DatiUtenteDAO{
    @Override
    public List<DatiUtente> doRetriveAll() throws Exception {
        return null;
    }

    @Override
    public boolean doCreateDatiUtente(DatiUtente datiUtente) throws Exception {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("dati_cliente", "c");
            queryBuilder.insert("Nome", "Cognome", "ddn", "Telefono", "Via", "N_civico", "CAP", "Email");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,datiUtente.getNome());
                ps.setString(2,datiUtente.getCognome());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doUpdateDatiUtete(DatiUtente datiUtente) throws Exception {
        return false;
    }

    @Override
    public boolean doDeleteDatiUtente(DatiUtente datiUtente) throws Exception {
        return false;
    }
}
