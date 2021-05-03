package Model.Ordine;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLOrdineDAO implements OrdineDAO<SQLException> {

    public SQLOrdineDAO(){
        super();
    }

    @Override
    public List<Ordine> fetchOrdine(Ordine ordine) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "o");
            String query = queryBuilder.select().where("o.Email=?").generateQuery();
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, ordine.getUser().getEmail());
                ResultSet rs = ps.executeQuery();
                OrdineExtractor ordineExtractor = new OrdineExtractor();
                List<Ordine> ordini = new ArrayList<>();
                while (rs.next()){
                    ordini.add(ordineExtractor.extract(rs));
                }
                return ordini;
            }
        }
    }

    @Override
    public Ordine fetchOrdine(Ordine ordine, String idOrdine) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "o");
            String query = queryBuilder.select().innerJoin("account_user", "ac")
                    .on("card.Email = ac.Email").where("ac.Email=? AND o.ID_ordine=?").generateQuery();
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, ordine.getUser().getEmail());
                ps.setString(2, idOrdine);
                ResultSet rs = ps.executeQuery();
                OrdineExtractor ordineExtractor = new OrdineExtractor();
                Ordine ordine1 = new Ordine();
                while (rs.next()){
                    ordine1 = ordineExtractor.extract(rs);
                }
                return ordine1;
            }
        }
    }

    @Override
    public boolean createOrdine(Ordine ordine) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "o");
            queryBuilder.insert("ID_ordine", "data_acquisto", "pacchetto_regalo", "data_spedizione",
                    "Descrizione", "Email");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, ordine.getID_ordine());
                ps.setDate(2, Date.valueOf(ordine.getData_acquisto()));
                ps.setBoolean(3, ordine.isPacchetto_regalo());
                ps.setDate(4, Date.valueOf(ordine.getData_spedizione()));
                ps.setString(5, ordine.getDescrizione());
                ps.setString(6, ordine.getUser().getEmail());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean updateOrdine(Ordine ordine) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "o");
            queryBuilder.update("ID_ordine", "data_acquisto", "pacchetto_regalo", "data_spedizione",
                    "Descrizione", "Email").where("o.ID_ordine=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, ordine.getID_ordine());
                ps.setDate(2, Date.valueOf(ordine.getData_acquisto()));
                ps.setBoolean(3, ordine.isPacchetto_regalo());
                ps.setDate(4, Date.valueOf(ordine.getData_spedizione()));
                ps.setString(5, ordine.getDescrizione());
                ps.setString(6, ordine.getUser().getEmail());
                ps.setString(7, ordine.getID_ordine());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean deleteOrdine(Ordine ordine) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "o");
            queryBuilder.delete().where("o.ID_ordine=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, ordine.getID_ordine());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
