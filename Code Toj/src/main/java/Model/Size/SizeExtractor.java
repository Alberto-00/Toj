package Model.Size;

import Model.Sconto.Sconto;
import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SizeExtractor implements ResultSetExtractor<Size> {

    @Override
    public Size extract(ResultSet rs) throws SQLException {
        Size size = new Size();
        size.setQuantita(rs.getInt("Quantita"));
        return size;
    }

}
