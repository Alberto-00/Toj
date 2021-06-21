package Model.Categoria;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaExtractor implements ResultSetExtractor<Categoria> {

    @Override
    public Categoria extract(ResultSet rs) throws SQLException{
        Categoria categoria = new Categoria();
        categoria.setId_categoria(rs.getInt("ID_categoria"));
        categoria.setNome(rs.getString("nome_categoria"));
        return categoria;
    }
}
