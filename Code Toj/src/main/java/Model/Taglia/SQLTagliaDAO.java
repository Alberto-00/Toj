package Model.Taglia;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLTagliaDAO implements TagliaDao{

    @Override
    public List<Taglia> doRetrieveAll() throws SQLException {
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT t.id_nome, s.quantita FROM taglia t " +
                    "INNER JOIN size s on s.id_nome = t.id_nome ORDER BY t.id_nome;");
            ResultSet rs = ps.executeQuery();
            TagliaExtractor tagliaExtractor = new TagliaExtractor();
            List<Taglia> taglie = new ArrayList<>();
            while (rs.next()){
                taglie.add(tagliaExtractor.extract(rs));
            }
            return taglie;
        }
    }

    @Override
    public List<Taglia> doRetrieveBySex(String sex) throws SQLException {
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT t.id_nome, s.quantita " +
                    "FROM taglia t INNER JOIN size s on s.id_nome = t.id_nome " +
                    "INNER JOIN articolo a on s.ID_articolo = a.ID_articolo " +
                    "WHERE a.Sesso = '" + sex + "' " +
                    "ORDER BY t.id_nome;");
            ResultSet rs = ps.executeQuery();
            TagliaExtractor tagliaExtractor = new TagliaExtractor();
            List<Taglia> taglie = new ArrayList<>();
            while (rs.next()){
                taglie.add(tagliaExtractor.extract(rs));
            }
            return taglie;
        }
    }

    @Override
    public boolean doCreateTaglia(Taglia taglia) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("taglia", "t");
            queryBuilder.insert("id_nome");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,taglia.getId_nome());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doUpdateTaglia(Taglia taglia) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("taglia", "t");
            queryBuilder.update("id_nome").where("t.id_nome = " + taglia.getId_nome());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,taglia.getId_nome());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doDeleteTaglia(Taglia taglia) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("taglia", "t");
            queryBuilder.delete().where("t.id_nome = " + taglia.getId_nome());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1,taglia.getId_nome());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
