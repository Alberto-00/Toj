package Model.Articolo;

import Model.Categoria.CategoriaExtractor;
import Model.Colore.ColoreExtractor;
import Model.Path_immagini.PathImgExtractor;
import Model.Size.SizeExtractor;
import Model.Taglia.TagliaExtractor;
import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SQLArticoloDAO implements ArticoloDAO<SQLException>{

    public SQLArticoloDAO(){
        super();
    }

    @Override
    public List<Articolo> doRetrieveAllNewProducts() throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT a.*, s.Quantita, ta.id_nome, c.nome, " +
                    "c.cod_esadecimale, p.pathName " +
                    "FROM articolo a INNER JOIN size s on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN taglia ta on s.id_nome = ta.id_nome " +
                    "INNER JOIN tinta t on a.ID_articolo = t.ID_articolo " +
                    "INNER JOIN colore c on c.cod_esadecimale = t.cod_esadecimale " +
                    "INNER JOIN pathimg p on a.ID_articolo = p.ID_articolo " +
                    "WHERE a.data_inserimento <= current_date " +
                    "AND a.data_inserimento >= date_sub(current_date, INTERVAL  1 month)");

            ResultSet rs = ps.executeQuery();
            ArticoloExtractor articoloExtractor = new ArticoloExtractor();
            CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
            ColoreExtractor coloreExtractor = new ColoreExtractor();
            TagliaExtractor tagliaExtractor = new TagliaExtractor();
            PathImgExtractor pathImgExtractor = new PathImgExtractor();
            SizeExtractor sizeExtractor = new SizeExtractor();
            Map<Integer, Articolo> productMap = new LinkedHashMap<>();

            if(rs.next()){
                Articolo articolo = articoloExtractor.extract(rs);
                articolo.setCategoria(categoriaExtractor.extract(rs));
                articolo.setSize(sizeExtractor.extract(rs));

                articolo.setPaths(new ArrayList<>());
                articolo.getPaths().add(pathImgExtractor.extract(rs));

                articolo.setColori(new ArrayList<>());
                articolo.getColori().add(coloreExtractor.extract(rs));

                articolo.setTaglie(new ArrayList<>());
                articolo.getTaglie().add(tagliaExtractor.extract(rs));
                productMap.put(articolo.getIDarticolo(), articolo);

                while (rs.next()){
                    int idProduct = rs.getInt("ID_articolo");
                    if(!productMap.containsKey(idProduct)){
                        articolo = articoloExtractor.extract(rs);
                        articolo.setCategoria(categoriaExtractor.extract(rs));
                        articolo.setSize(sizeExtractor.extract(rs));

                        articolo.setPaths(new ArrayList<>());
                        articolo.setColori(new ArrayList<>());
                        articolo.setTaglie(new ArrayList<>());
                        productMap.put(idProduct, articolo);
                        productMap.get(idProduct).getColori().add(coloreExtractor.extract(rs));
                        productMap.get(idProduct).getTaglie().add(tagliaExtractor.extract(rs));
                        productMap.get(idProduct).getPaths().add(pathImgExtractor.extract(rs));
                    }
                    if(!productMap.get(idProduct).containsSize(tagliaExtractor.extract(rs).getId_nome())) {
                        productMap.get(idProduct).getTaglie().add(tagliaExtractor.extract(rs));
                    }

                    if (!productMap.get(idProduct).containsPath(pathImgExtractor.extract(rs).getPathName())){
                        productMap.get(idProduct).getPaths().add(pathImgExtractor.extract(rs));
                    }

                    if (!productMap.get(idProduct).containsColors(coloreExtractor.extract(rs).getNome())){
                        productMap.get(idProduct).getColori().add(coloreExtractor.extract(rs));
                    }
                }
            }
            return new ArrayList<>(productMap.values());
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
