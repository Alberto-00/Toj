package Model.Sconto;

import Model.Account.Account;
import Model.Account.AccountExtractor;
import Model.Articolo.Articolo;
import Model.Articolo.ArticoloExtractor;
import Model.Categoria.CategoriaExtractor;
import Model.Colore.ColoreExtractor;
import Model.Taglia.TagliaExtractor;
import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLScontoDAO implements ScontoDAO<SQLException>{


    @Override
    public List<Sconto> doRetriveByUser(String email) throws SQLException {
        try(Connection con = ConPool.getConnection() ){
            QueryBuilder queryBuilder = new QueryBuilder("cod_sconto", "s");
            queryBuilder.select("s.codice","account_user.Email").innerJoin("posseduto", "p")
                    .on("s.codice = p.codice,").innerJoin("posseduto", "p1")
                    .on("a.Email = p1.Email").where("account_user.Email = " + email);

            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())){
                ResultSet rs = ps.executeQuery();
                List<Sconto> sconti = new ArrayList<>();
                AccountExtractor accountExtractor = new AccountExtractor();
                ScontoExtractor scontoExtractor = new ScontoExtractor();
                Sconto sconto = new Sconto();
                sconto.setUser(new Account());
                sconto.getUser().setEmail(email);
                for(int i = 0; rs.next(); i++){
                    sconto = scontoExtractor.extract(rs);
                    sconti.add(i,sconto);
                }
                return sconti;
            }
        }
    }

    @Override
    public boolean doCreateSconto(Sconto sconto) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("sconto", "s");
            queryBuilder.insert("codice","data_scadenza","sconto");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,sconto.getCodice());
                ps.setDate(2, Date.valueOf(sconto.getDataScadenza()));
                ps.setDouble(3,sconto.getSconto());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doUpdateSconto(Sconto sconto) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("sconto", "s");
            queryBuilder.update("codice", "data_scadenza", "sconto").where("s.codice = "+sconto.getCodice());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, sconto.getCodice());
                ps.setDate(2, Date.valueOf(sconto.getDataScadenza()));
                ps.setDouble(3,sconto.getSconto());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doDeleteSconto(Sconto sconto) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("sconto", "s");
            queryBuilder.delete().where("s.codice = " + sconto.getCodice());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, sconto.getCodice());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
