package Model.Path_immagini;

import Model.Articolo.Articolo;
import Model.storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLPathImgDAO implements PathImgDAO<SQLException> {

    @Override
    public boolean createPathImg(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            int rows = 0;
            for(PathImg p: articolo.getPaths()){
                try (PreparedStatement ps = con.prepareStatement("INSERT INTO pathimg " + "VALUES (?,?)")) {
                    ps.setString(1, p.getPathName());
                    ps.setInt(2, articolo.getIDarticolo());
                    rows = ps.executeUpdate();
                }
            }
            return rows == 1;
        }
    }

    @Override
    public boolean findPath(String pathName) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            String tmp = pathName.replace("\\", "\\\\");
            try (PreparedStatement ps = con.prepareStatement("SELECT * " +
                    "FROM pathimg WHERE pathName = " + "'" + tmp + "'")) {
                ResultSet rs = ps.executeQuery();
                return rs.next();
            }
        }
    }

    @Override
    public int countByID(Articolo articolo) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as count " +
                    "FROM pathimg WHERE ID_articolo = " + articolo.getIDarticolo())) {
                ResultSet rs = ps.executeQuery();
                if (rs.next())
                    return rs.getInt("count");
                return 0;
            }
        }
    }

    @Override
    public List<PathImg> doRetrieveByID(Articolo articolo) throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT * " +
                    "FROM pathimg WHERE ID_articolo = " + articolo.getIDarticolo())) {
                ResultSet rs = ps.executeQuery();
                List<PathImg> pathImgs = new ArrayList<>();
                PathImgExtractor pathImgExtractor = new PathImgExtractor();
                while (rs.next()) {
                    pathImgs.add(pathImgExtractor.extract(rs));
                }
                return pathImgs;
            }
        }
    }

    @Override
    public String writePath(Articolo articolo){
        String path="";
        if(articolo.getSesso().compareTo("F") == 0){
            path+="donna\\";
            int x = articolo.getCategoria().getId_categoria();
            switch (x) {
                case 1 -> path += "cappotti\\cappotti\\";
                case 2 -> path += "cappotti\\giacche\\";
                case 4 -> path += "costumi\\Bikini\\";
                case 5 -> path += "costumi\\Intero\\";
                case 6 -> path += "felpe\\";
                case 7 -> path += "maglie\\Camicetta\\";
                case 8 -> path += "maglie\\Maglietta\\";
                case 10 -> path += "maglie\\Top\\";
                case 11 -> path += "pantaloni\\Lungo\\";
                case 12 -> path += "pantaloni\\Corti\\";
                case 13 -> path += "gonne\\Corta\\";
                case 14 -> path += "gonne\\Lunga\\";
                default -> path = "";
            }
        } else{
            path +="uomo\\";
            int x = articolo.getCategoria().getId_categoria();
            switch (x) {
                case 1 -> path += "cappotti\\cappotti\\";
                case 2 -> path += "cappotti\\giacche\\";
                case 3 -> path += "costumi\\";
                case 6 -> path += "felpe\\";
                case 7 -> path += "maglie\\camicie\\";
                case 8 -> path += "maglie\\polo\\";
                case 9 -> path += "maglie\\t-shirt\\";
                case 11 -> path += "pantaloni\\Lungo\\";
                case 12 -> path += "pantaloni\\Corti\\";
                default -> path = "";
            }
        }
        return path;
    }

    @Override
    public boolean deletePath(String pathName) throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            String tmp = pathName.replace("\\", "\\\\");
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM pathimg WHERE pathName = '" + tmp + "';")){
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
