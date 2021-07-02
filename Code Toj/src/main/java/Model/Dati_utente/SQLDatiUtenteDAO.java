package Model.Dati_utente;

import Model.Account.Account;
import Model.search.Paginator;
import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SQLDatiUtenteDAO implements DatiUtenteDAO{

    @Override
    public Optional<DatiUtente> findUserData(String email) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("dati_cliente", "data");
            queryBuilder.select("*").where("Email = '" + email + "'");
            DatiUtente datiUtente = null;
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ResultSet rs = ps.executeQuery();
                if (rs.next()){
                 DatiUtenteExtractor datiUtenteExtractor = new DatiUtenteExtractor();
                 datiUtente = datiUtenteExtractor.extract(rs);
                }
            }
            return Optional.ofNullable(datiUtente);
        }
    }

    @Override
    public boolean updateDatiUtete(DatiUtente datiUtente) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String str;
            if (datiUtente.getDataDiNascita() != null){
                str = "'" + formatter.format(datiUtente.getDataDiNascita()) + "'";
            } else str = null;

            PreparedStatement ps = con.prepareStatement("UPDATE dati_cliente SET " +
                    "Nome='" + datiUtente.getNome() + "'," + "Cognome='" + datiUtente.getCognome() + "',"
            + "ddn=" + str + ", Telefono='" + datiUtente.getNumeroTelefonico()
                    +"' WHERE Email='" + datiUtente.getUser().getEmail() + "';");

            int rows = ps.executeUpdate();
            return rows == 1;
        }
    }

    @Override
    public boolean updateAddressUtente(DatiUtente datiUtente) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("dati_cliente", "data");
            queryBuilder.update("Via", "CAP", "city", "paese", "appartamento").where("Email = '" + datiUtente.getUser().getEmail() + "'");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, datiUtente.getVia());
                ps.setString(2, datiUtente.getCAP());
                ps.setString(3, datiUtente.getCity());
                ps.setString(4, datiUtente.getPaese());
                if (datiUtente.getAppartamento() != null)
                    ps.setString(5, datiUtente.getAppartamento());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public List<DatiUtente> doRetriveAll(Paginator paginator) throws SQLException {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stm = con.prepareStatement("SELECT * " +
                    "FROM dati_cliente LIMIT ?,?");
            stm.setInt(1,paginator.getOffset());
            stm.setInt(2,paginator.getLimit());
            ResultSet resultSet = stm.executeQuery();
            DatiUtenteExtractor datiUtenteExtractor = new DatiUtenteExtractor();
            List<DatiUtente> datiUtenti = new ArrayList<>();
            while (resultSet.next()){
                DatiUtente datiUtente = datiUtenteExtractor.extract(resultSet);
                datiUtente.setUser(new Account());
                datiUtente.getUser().setEmail(resultSet.getString("Email"));
                datiUtenti.add(datiUtente);
            }
            return datiUtenti;
        }
    }

    @Override
    public boolean doDeleteDatiUtente(DatiUtente datiUtente) throws SQLException {
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stm = con.prepareStatement("DELETE " +
                    "FROM dati_cliente WHERE dati_cliente.Email = " + datiUtente.getUser().getEmail());
            stm.executeQuery();
        }
        return false;
    }
}
