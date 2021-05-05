package Model.Articolo;

import Model.Categoria.CategoriaExtractor;
import Model.Colore.ColoreExtractor;
import Model.Taglia.TagliaExtractor;
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
    public List<Articolo> doRetrieveAll() throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("articolo", "a");
            queryBuilder.select("a.*", "s.Quantita", "s.id_nome", "c.nome").innerJoin("size", "s")
                    .on("s.ID_articolo = a.ID_articolo").innerJoin("tinta", "t")
                    .on("a.ID_articolo = t.ID_articolo").innerJoin("colore", "c")
                    .on("c.cod_esadecimale = t.cod_esadecimale");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery());){
                ResultSet rs = ps.executeQuery();
                List<Articolo> articoli = new ArrayList<>();
                CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
                TagliaExtractor tagliaExtractor = new TagliaExtractor();
                ColoreExtractor coloreExtractor = new ColoreExtractor();
                ArticoloExtractor articoloExtractor = new ArticoloExtractor();
                for(int i = 0; rs.next(); i++){
                    articoli.add(i, articoloExtractor.extract(rs));
                    articoli.get(i).setCategoria(categoriaExtractor.extract(rs));
                    articoli.get(i).setColori(new ArrayList<>());
                    articoli.get(i).getColori().add(coloreExtractor.extract(rs));
                    articoli.get(i).setTaglie(new ArrayList<>());
                    articoli.get(i).getTaglie().add(tagliaExtractor.extract(rs));
                }
                return articoli;
            }
        }
    }

    @Override
    public List<Articolo> doRetrieveBySex(String sesso) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> doRetriveBySexAndCat(String sesso, int id_categoria) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> doRetrieveBySexAndPrice(String sesso, double prezzoMin, double prezzoMax) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> doRetrieveBySexAndSize(String taglia, String sesso) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> doRetrieveBySexAndDate(String sesso, LocalDate data) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> doRetrieveBySexAndDate(String sesso, String colore) throws SQLException {
        return null;
    }

    @Override
    public List<Articolo> doRetrieveByDesc(String descrizione) throws SQLException {
        return null;
    }

    @Override
    public boolean doCreateArticolo(Articolo articolo) throws SQLException {
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
    public boolean doUpdateArticolo(Articolo articolo) throws SQLException {
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
    public boolean doDeleteArticolo(Articolo articolo) throws SQLException {
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
