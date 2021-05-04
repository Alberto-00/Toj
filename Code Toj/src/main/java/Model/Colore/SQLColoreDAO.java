package Model.Colore;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SQLColoreDAO implements ColoreDAO<SQLException> {

    public SQLColoreDAO(){
        super();
    }

    @Override
    public List<Colore> fetchColoreByArticolo(int id_articolo) throws SQLException {
        return null;
    }

    @Override
    public List<Colore> fetchColore() throws SQLException {
        return null;
    }

    @Override
    public boolean createColore(Colore colore) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("colore", "c");
            queryBuilder.insert("cod_esadecimale", "Nome");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, colore.getCod_esadecimale());
                ps.setString(2, colore.getNome());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean updateColore(Colore colore) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("colore", "c");
            queryBuilder.update("cod_esadecimale", "Nome").where("c.cod_esadecimale=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, colore.getCod_esadecimale());
                ps.setString(2, colore.getNome());
                ps.setString(3, colore.getCod_esadecimale());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean deleteColore(Colore colore) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("colore", "c");
            queryBuilder.delete().where("c.cod_esadecimale=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, colore.getCod_esadecimale());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
