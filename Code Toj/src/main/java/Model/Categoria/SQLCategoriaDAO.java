package Model.Categoria;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLCategoriaDAO implements CategoriaDAO{
    @Override
    public List<Categoria> doRetrieveBySex(String sex) throws SQLException {
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT c.ID_categoria, c.nome_categoria " +
                    "FROM categoria c INNER JOIN articolo a on c.ID_categoria = a.ID_categoria " +
                    "WHERE a.Sesso = '" + sex + "';");
            ResultSet rs = ps.executeQuery();
            CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
            List<Categoria> categorie = new ArrayList<>();
            while (rs.next()){
                categorie.add(categoriaExtractor.extract(rs));
            }
            return categorie;
        }
    }

    @Override
    public boolean doCreateCategoria(Categoria categoria) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("categoria", "c");
            queryBuilder.insert("ID_categoria", "Nome");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, categoria.getId_categoria());
                ps.setString(2,categoria.getNome());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doUpdateCategoria(Categoria categoria) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("categoria", "c");
            queryBuilder.update("ID_categoria", "Nome").where("a.Email = " + categoria.getId_categoria());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, categoria.getId_categoria());
                ps.setString(2,categoria.getNome());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doDeleteCategoria(Categoria categoria) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("categoria", "c");
            queryBuilder.delete().where("a.Email = " + categoria.getId_categoria());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setInt(1, categoria.getId_categoria());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
