package Model.Articolo;

import Model.Categoria.Categoria;
import Model.Categoria.CategoriaExtractor;
import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLArticoloDAO implements ArticoloDAO<SQLException>{

    public SQLArticoloDAO(){
        super();
    }

    @Override
    public List<Articolo> fetchArticoloBySex(String sesso) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            String query = "SELECT a.*, t.id_nome, c.Nome " +
                    "FROM articolo a, taglia t, colore c, size s, tinta t1\n" +
                    "WHERE a.Sesso=? AND a.ID_articolo = s.ID_articolo AND s.id_nome = t.id_nome" +
                    "  AND t1.ID_articolo = a.ID_articolo AND t1.cod_esadecimale = c.cod_esadecimale;";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, sesso);
                ResultSet rs = ps.executeQuery();
                ArticoloExtractor articoloExtractor = new ArticoloExtractor();
                List<Articolo> articoli = new ArrayList<>();
                Categoria categoria = null;
                while (rs.next()){
                    int i = 0;
                    articoli.add(i, articoloExtractor.extract(rs));
                    articoli.get(i).setCategoria(new Categoria());
                    CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
                    categoria = categoriaExtractor.extract(rs);
                    articoli.get(i).setCategoria(categoria);
                    i++;
                }

                return articoli;
            }
        }
    }

    @Override
    public List<Articolo> fetchArticolo(String sesso, int id_categoria) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> fetchArticolo(String sesso, double prezzo) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> fetchArticolo(String taglia, String sesso) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> fetchArticolo(String sesso, LocalDate date) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> fetchArticoloByDesc(String descrizione) throws SQLException {
        return null;
    }

    @Override
    public boolean createArticolo(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("articolo", "ar");
           queryBuilder.insert("ID_articolo", "Prezzo", "Sesso", "Descrizione", "sconto");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, articolo.getIDarticolo());
                ps.setDouble(2, articolo.getPrezzo());
                ps.setString(3, articolo.getSesso());
                ps.setString(4, articolo.getDescrizione());
                ps.setDouble(5, articolo.getSconto());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean updateArticolo(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("articolo", "ar");
            queryBuilder.update("ID_articolo", "Prezzo", "Sesso", "Descrizione", "sconto", "data_inserimento",
                    "ID_categoria").where("ar.ID_articolo=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, articolo.getIDarticolo());
                ps.setDouble(2, articolo.getPrezzo());
                ps.setString(3, articolo.getSesso());
                ps.setString(4, articolo.getDescrizione());
                ps.setDouble(5, articolo.getSconto());
                ps.setDouble(6, articolo.getIDarticolo());
                ps.setDate(7, Date.valueOf(articolo.getData_inserimento()));
                ps.setInt(8, articolo.getCategoria().getId_categoria());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean deleteArticolo(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("articolo", "ar");
            queryBuilder.delete().where("ar.ID_articolo=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, articolo.getIDarticolo());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
