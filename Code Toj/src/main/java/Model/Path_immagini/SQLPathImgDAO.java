package Model.Path_immagini;

import Model.Articolo.Articolo;
import Model.storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLPathImgDAO implements PathImgDAO {

    @Override
    public boolean createPathImg(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("INSERT INTO pathimg " + "VALUES (?,?)")) {
                ps.setString(1,articolo.getPaths().get(0).getPathName());
                ps.setInt(2,articolo.getIDarticolo());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
