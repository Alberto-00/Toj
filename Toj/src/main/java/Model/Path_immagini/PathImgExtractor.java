package Model.Path_immagini;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PathImgExtractor implements ResultSetExtractor<PathImg> {

    @Override
    public PathImg extract(ResultSet rs) throws SQLException {
        PathImg path = new PathImg();
        path.setPathName(rs.getString("pathName"));
        return path;
    }
}
