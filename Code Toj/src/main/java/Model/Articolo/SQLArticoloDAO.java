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
    public List<Articolo> doRetrieveAllNewProducts() throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT a.*, s.Quantita, s.id_nome, c.nome " +
                    "FROM articolo a INNER JOIN size s on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN tinta t on a.ID_articolo = t.ID_articolo INNER JOIN colore c " +
                    "on c.cod_esadecimale = t.cod_esadecimale " +
                    "WHERE a.data_inserimento <= current_date " +
                    "AND a.data_inserimento >= date_sub(current_date, INTERVAL  1 month)");
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

    @Override
    public boolean doCreateArticolo(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("articolo", "ar");
           queryBuilder.insert("ID_articolo", "Prezzo", "Sesso", "Descrizione", "sconto", "data_inserimento",
                   "ID_categoria", "Nome", "path_img");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, articolo.getIDarticolo());
                ps.setDouble(2, articolo.getPrezzo());
                ps.setString(3, articolo.getSesso());
                ps.setString(4, articolo.getDescrizione());
                ps.setDouble(5, articolo.getSconto());
                ps.setDate(6, (Date) (articolo.getData_inserimento()));
                ps.setDouble(7, articolo.getIDarticolo());
                ps.setString(8, articolo.getNome());
                ps.setString(9, articolo.getPath());
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
                    "ID_categoria",  "Nome", "path_img").where("ar.ID_articolo=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, articolo.getIDarticolo());
                ps.setDouble(2, articolo.getPrezzo());
                ps.setString(3, articolo.getSesso());
                ps.setString(4, articolo.getDescrizione());
                ps.setDouble(5, articolo.getSconto());
                ps.setDate(6, (Date) (articolo.getData_inserimento()));
                ps.setDouble(7, articolo.getIDarticolo());
                ps.setString(8, articolo.getNome());
                ps.setString(9, articolo.getPath());
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
