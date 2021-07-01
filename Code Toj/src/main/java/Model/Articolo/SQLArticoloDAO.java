package Model.Articolo;

import Model.Categoria.Categoria;
import Model.Categoria.CategoriaExtractor;
import Model.Colore.Colore;
import Model.Colore.ColoreExtractor;
import Model.Colore.SQLColoreDAO;
import Model.Path_immagini.PathImgExtractor;
import Model.Path_immagini.SQLPathImgDAO;
import Model.Taglia.SQLTagliaDAO;
import Model.Taglia.Taglia;
import Model.Taglia.TagliaExtractor;
import Model.search.Condition;
import Model.search.Operator;
import Model.search.Paginator;
import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class SQLArticoloDAO implements ArticoloDAO<SQLException>{

    public SQLArticoloDAO(){
        super();
    }

    @Override
    public List<Articolo> doRetrieveNewArrivals(String sex) throws SQLException{
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
                    "AND a.Sesso = '" + sex + "'" + " ORDER BY a.ID_articolo Desc, p.pathName;");

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
                    "WHERE a.ID_articolo = ? ORDER BY p.pathName;");

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
    public Articolo doRetrieveProductById_Size(String size, int id) throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT a.*, s.Quantita, ta.id_nome, c.nome_colore, " +
                    "c.cod_esadecimale, p.pathName, c2.nome_categoria " +
                    "FROM articolo a INNER JOIN size s on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN categoria c2 on a.ID_categoria = c2.ID_categoria "+
                    "INNER JOIN taglia ta on s.id_nome = ta.id_nome " +
                    "INNER JOIN tinta t on a.ID_articolo = t.ID_articolo " +
                    "INNER JOIN colore c on c.cod_esadecimale = t.cod_esadecimale " +
                    "INNER JOIN pathimg p on a.ID_articolo = p.ID_articolo " +
                    "WHERE a.ID_articolo = ? AND ta.id_nome = ? ORDER BY p.pathName;");

            ps.setInt(1, id);
            ps.setString(2, size);

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
                    "WHERE a.nome_articolo = '" + nome + "' ORDER BY p.pathName;");

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
    public List<String> doRetrieveProductByNome() throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT a.nome_articolo as nome " +
                    "FROM articolo a;");
            ResultSet rs = ps.executeQuery();
            List<String> nomi = new ArrayList<>();
            while (rs.next()){
                nomi.add(rs.getString("nome"));
            }
            return nomi;
        }
    }

    @Override
    public List<Articolo> search(List<Condition> conditions, boolean latest) throws SQLException{
        try (Connection con = ConPool.getConnection()){
            String query = ArticoloQuery.search(conditions, latest);
            try (PreparedStatement ps = con.prepareStatement(query)){
                for (int i = 0; i < conditions.size(); i++){
                    if (conditions.get(i).getOperator() == Operator.MATCH){
                        ps.setObject(i+1, "%" + conditions.get(i).getValue() + "%");
                    } else {
                        ps.setObject(i+1, conditions.get(i).getValue()); //i+1 perchè setObject parte da 1
                    }
                }

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
    }

    @Override
    public List<Articolo> searchPagination(List<Condition> conditions, Paginator paginator, boolean latest)
            throws SQLException{

        try (Connection con = ConPool.getConnection()){
            String query = ArticoloQuery.searchPagination(conditions, paginator, latest);
            try (PreparedStatement ps = con.prepareStatement(query)){
                for (int i = 0; i < conditions.size(); i++){
                    if (conditions.get(i).getOperator() == Operator.MATCH){
                        ps.setObject(i+1, "%" + conditions.get(i).getValue() + "%");
                    } else {
                        ps.setObject(i+1, conditions.get(i).getValue()); //i+1 perchè setObject parte da 1
                    }
                }

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
    }

    @Override
    public double maxPrice() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT MAX(Prezzo) as price " +
                    "FROM articolo a;");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getDouble("price");
            return 0;
        }
    }

    @Override
    public double minPrice() throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT MIN(Prezzo) as price " + "FROM articolo a;");
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                return rs.getDouble("price");
            return 0;
        }
    }

    @Override
    public int countArticoli() throws SQLException {
        int articoli = 0;
        try(Connection con = ConPool.getConnection()){
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT count(*)" +
                    "FROM articolo");
            if(resultSet.next()){
                articoli=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return articoli;
    }

    @Override
    public List<Articolo> getArticoli() throws SQLException{
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT a.*, s.Quantita, ta.id_nome, c.nome_colore, " +
                    "c.cod_esadecimale, c2.nome_categoria, s.Quantita " +
                    "FROM articolo a INNER JOIN size s on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN categoria c2 on a.ID_categoria = c2.ID_categoria "+
                    "INNER JOIN taglia ta on s.id_nome = ta.id_nome " +
                    "INNER JOIN tinta t on a.ID_articolo = t.ID_articolo " +
                    "INNER JOIN colore c on c.cod_esadecimale = t.cod_esadecimale " +
                    "ORDER BY a.ID_articolo");

            ResultSet rs = ps.executeQuery();

            ArticoloExtractor articoloExtractor = new ArticoloExtractor();
            CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
            ColoreExtractor coloreExtractor = new ColoreExtractor();
            TagliaExtractor tagliaExtractor = new TagliaExtractor();
            Map<Integer, Articolo> productMap = new LinkedHashMap<>();

            if(rs.next()){
                Articolo articolo = articoloExtractor.extract(rs);
                articolo.setCategoria(categoriaExtractor.extract(rs));

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
                    }
                    if(!productMap.get(idProduct).containsSize(tagliaExtractor.extract(rs).getId_nome())) {
                        productMap.get(idProduct).getTaglie().add(tagliaExtractor.extract(rs));
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
    public List<Articolo> getArticoliPage(Paginator paginator) throws SQLException{
        try(Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT a.*, s.Quantita, ta.id_nome, c.nome_colore, " +
                    "c.cod_esadecimale, c2.nome_categoria " +
                    "FROM articolo a INNER JOIN size s on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN categoria c2 on a.ID_categoria = c2.ID_categoria "+
                    "INNER JOIN taglia ta on s.id_nome = ta.id_nome " +
                    "INNER JOIN tinta t on a.ID_articolo = t.ID_articolo " +
                    "INNER JOIN colore c on c.cod_esadecimale = t.cod_esadecimale " +
                    "WHERE a.ID_articolo <= ? AND a.ID_articolo >= ? ORDER BY a.ID_articolo");
            ps.setInt(1,paginator.getLastId());
            ps.setInt(2,paginator.getFirstId());
            ResultSet rs = ps.executeQuery();

            ArticoloExtractor articoloExtractor = new ArticoloExtractor();
            CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
            ColoreExtractor coloreExtractor = new ColoreExtractor();
            TagliaExtractor tagliaExtractor = new TagliaExtractor();
            Map<Integer, Articolo> productMap = new LinkedHashMap<>();

            if(rs.next()){
                Articolo articolo = articoloExtractor.extract(rs);
                articolo.setCategoria(categoriaExtractor.extract(rs));

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
                    }
                    if(!productMap.get(idProduct).containsSize(tagliaExtractor.extract(rs).getId_nome())) {
                        productMap.get(idProduct).getTaglie().add(tagliaExtractor.extract(rs));
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
    public void doCreateArticolo(Articolo articolo) throws SQLException {
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO articolo " + "VALUES (?,?,?,?,?,?,?,?)")) {
                ps.setInt(1, articolo.getIDarticolo());
                ps.setDouble(2, articolo.getPrezzo());
                ps.setString(3, articolo.getSesso());
                ps.setString(4, articolo.getDescrizione());
                ps.setDouble(5, articolo.getSconto());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                ps.setString(6, formatter.format(articolo.getData_inserimento()));
                ps.setInt(7, articolo.getCategoria().getId_categoria());
                ps.setString(8, articolo.getNome());
                ps.executeUpdate();
            }

            SQLColoreDAO sqlColoreDAO = new SQLColoreDAO();
            sqlColoreDAO.createTinta(articolo);
            SQLTagliaDAO sqlTagliaDAO = new SQLTagliaDAO();
            sqlTagliaDAO.createSize(articolo);
            SQLPathImgDAO sqlPathImgDAO = new SQLPathImgDAO();
            sqlPathImgDAO.createPathImg(articolo);
        }
    }

    @Override
    public void doUpdateArticolo(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            QueryBuilder queryBuilder = new QueryBuilder("articolo", "ar");
            queryBuilder.update("ID_articolo", "Prezzo", "Sesso", "Descrizione", "sconto", "data_inserimento",
                    "ID_categoria",  "nome_articolo").where("ID_articolo=?");

            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, articolo.getIDarticolo());
                ps.setDouble(2, articolo.getPrezzo());
                ps.setString(3, articolo.getSesso());
                ps.setString(4, articolo.getDescrizione());
                ps.setDouble(5, articolo.getSconto());
                ps.setString(6, formatter.format(date));
                ps.setInt(7, articolo.getCategoria().getId_categoria());
                ps.setString(8, articolo.getNome());
                ps.setInt(9, articolo.getIDarticolo());
                ps.executeUpdate();

                SQLTagliaDAO sqlTagliaDAO = new SQLTagliaDAO();
                for(Taglia t: articolo.getTaglie())
                    sqlTagliaDAO.doUpdateSize(articolo, t);

                SQLColoreDAO sqlColoreDAO = new SQLColoreDAO();
                sqlColoreDAO.deleteTinta(articolo);
                sqlColoreDAO.createTinta(articolo);
            }
        }
    }

    @Override
    public boolean reduceSize(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE size s INNER JOIN articolo a on s.ID_articolo = a.ID_articolo " +
                    "INNER JOIN taglia t on s.id_nome = t.id_nome SET s.Quantita = s.Quantita - " + articolo.getLocalQuantity() +
                    " WHERE a.ID_articolo = " + articolo.getIDarticolo() + " AND t.id_nome = '" + articolo.getChosenSize() + "';");
            int rows = ps.executeUpdate();
            return rows == 1;
        }
    }

    @Override
    public boolean doDeleteArticolo(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("articolo", "ar");
            queryBuilder.delete().where("ID_articolo=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, articolo.getIDarticolo());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public int maxID() throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT MAX(ID_articolo)" +
                    "as id FROM articolo")) {
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                    return rs.getInt("id");
            }
            return 0;
        }
    }
}
