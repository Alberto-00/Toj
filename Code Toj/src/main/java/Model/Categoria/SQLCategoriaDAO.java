package Model.Categoria;

import Model.storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLCategoriaDAO implements CategoriaDAO<SQLException>{

    @Override
    public List<Categoria> doRetrieveAll() throws SQLException{
        try (Connection con = ConPool.getConnection()){
            try (PreparedStatement ps = con.prepareStatement("SELECT DISTINCT c.* FROM categoria c;")){
                ResultSet rs = ps.executeQuery();
                CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
                List<Categoria> categorie = new ArrayList<>();
                while (rs.next()){
                    categorie.add(categoriaExtractor.extract(rs));
                }
                return categorie;
            }
        }
    }

    @Override
    public List<Categoria> doRetrieveBySex(String sex) throws SQLException {
        try (Connection con = ConPool.getConnection()){
            try (PreparedStatement ps = con.prepareStatement("SELECT DISTINCT c.ID_categoria, c.nome_categoria " +
                    "FROM categoria c INNER JOIN articolo a on c.ID_categoria = a.ID_categoria " +
                    "WHERE a.Sesso = '" + sex + "';")){
                ResultSet rs = ps.executeQuery();
                CategoriaExtractor categoriaExtractor = new CategoriaExtractor();
                List<Categoria> categorie = new ArrayList<>();
                while (rs.next()){
                    categorie.add(categoriaExtractor.extract(rs));
                }
                return categorie;
            }
        }
    }
}
