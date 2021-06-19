package Model.Articolo;

import Model.Categoria.CategoriaExtractor;
import Model.Colore.ColoreExtractor;
import Model.Path_immagini.PathImgExtractor;
import Model.Taglia.TagliaExtractor;
import Model.search.Paginator;
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
    public List<Articolo> pagination(String sex) throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT a.*, s.Quantita, ta.id_nome, c.nome_colore, " +
                    "c.cod_esadecimale, p.pathName, c2.nome_categoria " +
                    "FROM articolo a INNER JOIN size s on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN categoria c2 on a.ID_categoria = c2.ID_categoria " +
                    "INNER JOIN taglia ta on s.id_nome = ta.id_nome " +
                    "INNER JOIN tinta t on a.ID_articolo = t.ID_articolo " +
                    "INNER JOIN colore c on c.cod_esadecimale = t.cod_esadecimale " +
                    "INNER JOIN pathimg p on a.ID_articolo = p.ID_articolo " +
                    "WHERE a.data_inserimento <= current_date " +
                    "AND a.data_inserimento >= date_sub(current_date, INTERVAL  1 month) " +
                    "AND a.Sesso = '" + sex + "'" + " ORDER BY a.ID_articolo Desc");

            ResultSet rs = ps.executeQuery();
            ArticoloExtractor articoloExtractor = new ArticoloExtractor();
            CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
            ColoreExtractor coloreExtractor = new ColoreExtractor();
            TagliaExtractor tagliaExtractor = new TagliaExtractor();
            PathImgExtractor pathImgExtractor = new PathImgExtractor();
            Map<Integer, Articolo> productMap = new LinkedHashMap<>();

            if(rs.next()){
                Articolo articolo = articoloExtractor.extract(rs);
                articolo.setCategoria(categoriaExtractor.extract(rs));

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
    public List<Articolo> pagination(String sex, Paginator paginator) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT a.*, s.Quantita, ta.id_nome, c.nome_colore, " +
                    "c.cod_esadecimale, p.pathName, c2.nome_categoria " +
                    "FROM articolo a INNER JOIN size s on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN categoria c2 on a.ID_categoria = c2.ID_categoria " +
                    "INNER JOIN taglia ta on s.id_nome = ta.id_nome " +
                    "INNER JOIN tinta t on a.ID_articolo = t.ID_articolo " +
                    "INNER JOIN colore c on c.cod_esadecimale = t.cod_esadecimale " +
                    "INNER JOIN pathimg p on a.ID_articolo = p.ID_articolo " +
                    "WHERE a.Sesso = '" + sex + "' AND a.ID_articolo <= ? AND a.ID_articolo > ? ");

            ps.setInt(1, paginator.getLastId());
            ps.setInt(2, paginator.getFirstId());

            ResultSet rs = ps.executeQuery();
            ArticoloExtractor articoloExtractor = new ArticoloExtractor();
            CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
            ColoreExtractor coloreExtractor = new ColoreExtractor();
            TagliaExtractor tagliaExtractor = new TagliaExtractor();
            PathImgExtractor pathImgExtractor = new PathImgExtractor();
            Map<Integer, Articolo> productMap = new LinkedHashMap<>();

            if(rs.next()){
                Articolo articolo = articoloExtractor.extract(rs);
                articolo.setCategoria(categoriaExtractor.extract(rs));

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
    public Articolo doRetrieveProductById(int id) throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT a.*, s.Quantita, ta.id_nome, c.nome_colore, " +
                    "c.cod_esadecimale, p.pathName, c2.nome_categoria " +
                    "FROM articolo a INNER JOIN size s on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN categoria c2 on a.ID_categoria = c2.ID_categoria "+
                    "INNER JOIN taglia ta on s.id_nome = ta.id_nome " +
                    "INNER JOIN tinta t on a.ID_articolo = t.ID_articolo " +
                    "INNER JOIN colore c on c.cod_esadecimale = t.cod_esadecimale " +
                    "INNER JOIN pathimg p on a.ID_articolo = p.ID_articolo " +
                    "WHERE a.ID_articolo = ?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            ArticoloExtractor articoloExtractor = new ArticoloExtractor();
            CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
            ColoreExtractor coloreExtractor = new ColoreExtractor();
            TagliaExtractor tagliaExtractor = new TagliaExtractor();
            PathImgExtractor pathImgExtractor = new PathImgExtractor();

            if(rs.next()){
                Articolo articolo = articoloExtractor.extract(rs);
                articolo.setCategoria(categoriaExtractor.extract(rs));

                articolo.setPaths(new ArrayList<>());
                articolo.getPaths().add(pathImgExtractor.extract(rs));

                articolo.setColori(new ArrayList<>());
                articolo.getColori().add(coloreExtractor.extract(rs));

                articolo.setTaglie(new ArrayList<>());
                articolo.getTaglie().add(tagliaExtractor.extract(rs));
                while (rs.next()){
                    if(!articolo.containsSize(tagliaExtractor.extract(rs).getId_nome())) {
                        articolo.getTaglie().add(tagliaExtractor.extract(rs));
                    }

                    if (!articolo.containsPath(pathImgExtractor.extract(rs).getPathName())){
                        articolo.getPaths().add(pathImgExtractor.extract(rs));
                    }

                    if (!articolo.containsColors(coloreExtractor.extract(rs).getNome())){
                        articolo.getColori().add(coloreExtractor.extract(rs));
                    }
                }
                return articolo;
            }
            return null;
        }
    }

    @Override
    public List<Articolo> doRetrieveProductByNome(String nome) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT a.*, s.Quantita, ta.id_nome, c.nome_colore, " +
                    "c.cod_esadecimale, p.pathName, c2.nome_categoria " +
                    "FROM articolo a INNER JOIN size s on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN categoria c2 on a.ID_categoria = c2.ID_categoria "+
                    "INNER JOIN taglia ta on s.id_nome = ta.id_nome " +
                    "INNER JOIN tinta t on a.ID_articolo = t.ID_articolo " +
                    "INNER JOIN colore c on c.cod_esadecimale = t.cod_esadecimale " +
                    "INNER JOIN pathimg p on a.ID_articolo = p.ID_articolo " +
                    "WHERE a.nome_articolo = '" + nome + "'");

            ResultSet rs = ps.executeQuery();

            ArticoloExtractor articoloExtractor = new ArticoloExtractor();
            CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
            ColoreExtractor coloreExtractor = new ColoreExtractor();
            TagliaExtractor tagliaExtractor = new TagliaExtractor();
            PathImgExtractor pathImgExtractor = new PathImgExtractor();
            Map<Integer, Articolo> productMap = new LinkedHashMap<>();

            if(rs.next()){
                Articolo articolo = articoloExtractor.extract(rs);
                articolo.setCategoria(categoriaExtractor.extract(rs));

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
    public List<Articolo> doRetrieveProductBySexType(String sex, String type) throws SQLException {
    try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT a.*, s.Quantita, ta.id_nome, c.nome_colore, " +
                    "c.cod_esadecimale, p.pathName, c2.nome_categoria " +
                    "FROM articolo a INNER JOIN size s on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN categoria c2 on a.ID_categoria = c2.ID_categoria " +
                    "INNER JOIN taglia ta on s.id_nome = ta.id_nome " +
                    "INNER JOIN tinta t on a.ID_articolo = t.ID_articolo " +
                    "INNER JOIN colore c on c.cod_esadecimale = t.cod_esadecimale " +
                    "INNER JOIN pathimg p on a.ID_articolo = p.ID_articolo " +
                    "WHERE a.Sesso = '" + sex + "' AND c2.nome_categoria= '"+type+"'");

            ResultSet rs = ps.executeQuery();
            ArticoloExtractor articoloExtractor = new ArticoloExtractor();
            CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
            ColoreExtractor coloreExtractor = new ColoreExtractor();
            TagliaExtractor tagliaExtractor = new TagliaExtractor();
            PathImgExtractor pathImgExtractor = new PathImgExtractor();
            Map<Integer, Articolo> productMap = new LinkedHashMap<>();

            if(rs.next()){
                Articolo articolo = articoloExtractor.extract(rs);
                articolo.setCategoria(categoriaExtractor.extract(rs));

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
    public int getFirstId(String sex, int off) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT ID_articolo as id, MIN(ID_articolo) as min FROM articolo " +
                    "WHERE Sesso='"+sex+"' LIMIT ?,18446744073709551615");

            ps.setInt(1, off);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("min") : 0;
        }
    }



    @Override
    public int countAll(String sex) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as count FROM articolo WHERE Sesso='" + sex + "'");
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt("count") : 0;
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
