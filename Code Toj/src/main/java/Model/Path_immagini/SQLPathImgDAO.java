package Model.Path_immagini;

import Model.Articolo.Articolo;
import Model.storage.ConPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPathImgDAO implements PathImgDAO {

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
            try (PreparedStatement ps = con.prepareStatement("SELECT * " +
                    "FROM pathimg WHERE pathName = " + "'" + pathName + "'")) {

                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    return true;
                }else
                    return false;
            }

        }
    }

    @Override
    public String writePath(Articolo articolo){
        String path="";
        if(articolo.getSesso().compareTo("F") == 0){
            path+="donna\\";
            int x = articolo.getCategoria().getId_categoria();
            switch (x){
                case 1:
                    path+="cappotti\\cappotti\\";
                    break;
                case 2:
                    path+="cappotti\\giacche\\";
                    break;
                case 4:
                    path+="costumi\\Bikini\\";
                    break;
                case 5:
                    path+="costumi\\Intero\\";
                    break;
                case 6:
                    path+="felpe\\";
                    break;
                case 7:
                    path+="maglie\\Camicetta\\";
                    break;
                case 8:
                    path+="maglie\\Maglietta\\";
                    break;
                case 10:
                    path+="maglie\\Top\\";
                    break;
                case 11:
                    path+="pantaloni\\Lungo\\";
                    break;
                case 12:
                    path+="pantaloni\\Corti\\";
                    break;
                case 13:
                    path+="gonne\\Corta\\";
                    break;
                case 14:
                    path+="gonne\\Lunga\\";
                    break;
                default:
                    path="";
            }
        } else{
            path +="uomo\\";
            int x = articolo.getCategoria().getId_categoria();
            switch (x){
                case 1:
                    path+="cappotti\\cappotti\\";
                    break;
                case 2:
                    path+="cappotti\\giacche\\";
                    break;
                case 3:
                    path+="costumi\\";
                    break;
                case 6:
                    path+="felpe\\";
                    break;
                case 7:
                    path+="maglie\\camicie\\";
                    break;
                case 8:
                    path+="maglie\\polo\\";
                    break;
                case 9:
                    path+="maglie\\t-shirt\\";
                    break;
                case 11:
                    path+="pantaloni\\Lungo\\";
                    break;
                case 12:
                    path+="pantaloni\\Corti\\";
                    break;
                default:
                    path="";
            }
        }
        return path;
    }
}
