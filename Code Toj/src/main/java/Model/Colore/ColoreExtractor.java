package Model.Colore;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColoreExtractor implements ResultSetExtractor<Colore> {

    @Override
    public Colore extract(ResultSet rs) throws SQLException{
        Colore colore = new Colore();
        colore.setCod_esadecimale(rs.getString("cod_esadecimale"));
        colore.setNome(rs.getString("nome_colore"));
        return colore;
    }
}
