package Model.Taglia;

import Model.Articolo.Articolo;
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
            PreparedStatement ps = con.prepareStatement("SELECT t.id_nome FROM taglia t " +
                  " ORDER BY t.id_nome;");
            ResultSet rs = ps.executeQuery();
            List<Taglia> taglie = new ArrayList<>();
            while (rs.next()){
                Taglia taglia = new Taglia();
                taglia.setId_nome(rs.getString("id_nome"));
                taglie.add(taglia);
            }
            return taglie;
        }
    }

    @Override
    public List<Taglia> doRetrieveAllByID(Articolo articolo) throws SQLException {
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT t.id_nome, s.quantita FROM taglia t " +
                    "INNER JOIN size s on s.id_nome = t.id_nome " +
                    "INNER JOIN articolo a on s.ID_articolo = a.ID_articolo " +
                    "WHERE a.ID_articolo = ? ORDER BY t.id_nome");
            ps.setInt(1, articolo.getIDarticolo());
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
            PreparedStatement ps = con.prepareStatement("SELECT t.id_nome " +
                    "FROM taglia t INNER JOIN size s on s.id_nome = t.id_nome " +
                    "INNER JOIN articolo a on s.ID_articolo = a.ID_articolo " +
                    "WHERE a.Sesso = '" + sex + "' " +
                    "GROUP BY t.id_nome " +
                    "ORDER BY t.id_nome;");
            ResultSet rs = ps.executeQuery();
            List<Taglia> taglie = new ArrayList<>();
            while (rs.next()){
                Taglia taglia = new Taglia();
                taglia.setId_nome(rs.getString("id_nome"));
                taglie.add(taglia);
            }
            return taglie;
        }
    }

    @Override
    public boolean doUpdateSize(Articolo articolo, Taglia taglia) throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("size", "s");
            queryBuilder.update("Quantita").where("ID_articolo = " + articolo.getIDarticolo() + " AND " +
                    "id_nome ='" + taglia.getId_nome() + "'");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, taglia.getQuantita());
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

    @Override
    public boolean createSize(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO size " + "VALUES (?,?,?)")) {
                int rows = 0;
                for (int i = 0; i < articolo.getTaglie().size(); i++){
                    ps.setString(1,articolo.getTaglie().get(i).getId_nome());
                    ps.setInt(2,articolo.getIDarticolo());
                    ps.setInt(3,articolo.getTaglie().get(i).getQuantita());
                    ps.executeUpdate();
                    rows++;
                }
                return rows == articolo.getTaglie().size();
            }
        }
    }
}
