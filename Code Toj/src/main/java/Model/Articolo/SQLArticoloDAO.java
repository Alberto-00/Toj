package Model.Articolo;

import Model.Categoria.Categoria;
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
    public List<Articolo> fetchArticolo(String sesso) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("articolo", "ar");
            String query = queryBuilder.select().innerJoin("categoria", "cat")
                    .on("cat.ID_categoria = ar.ID_categoria").where("ar.ID_articolo=?").generateQuery();
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, sesso);
                ResultSet rs = ps.executeQuery();
                ArticoloExtractor articoloExtractor = new ArticoloExtractor();
                List<Articolo> articoli = new ArrayList<>();
                Categoria categoria = null;
                if(rs.next()){
                    int i = 0;
                    articoli.add(i, articoloExtractor.extract(rs));
                    articoli.get(i).setCategoria(new Categoria());
                    categoria = categoriaExtractor.extract(rs);
                    articoli.get(i).getCategoria() = categoria;
                }
                for(int i = 1; rs.next(); i++){
                    articoli.add(i, articoloExtractor.extract(rs));
                }

                return articoli;
            }
        }
    }

    @Override
    public List<Articolo> fetchArticolo(int id_categoria) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> fetchArticolo(String categoria, String sesso) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> fetchArticolo(String taglia, String sesso, String categoria) throws SQLException {
        return null;
    }

    @Override
    public Articolo fetchArticolo(String... field) throws SQLException {
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
