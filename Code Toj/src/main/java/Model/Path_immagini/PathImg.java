package Model.Path_immagini;

import Controller.http.JSONSerializable;
import Model.Articolo.Articolo;
import org.json.simple.JSONObject;

import java.util.List;

public class PathImg implements JSONSerializable {

    //Dati dell'Account
    private String pathName;
    private List<Articolo> articoli;

    public PathImg() {
        super();
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    @Override
    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("pathName", pathName);
        return object;
    }

    public static String writePath(Articolo articolo){
        String path="";
        if(articolo.getSesso().compareTo("F") == 0){
            path+="donna\\";
            int x = articolo.getCategoria().getId_categoria();
            switch (x) {
                case 1 -> path += "cappotti\\Cappotti\\";
                case 2 -> path += "cappotti\\Giacche\\";
                case 4 -> path += "costumi\\Bikini\\";
                case 5 -> path += "costumi\\Intero\\";
                case 6 -> path += "felpe\\";
                case 7 -> path += "maglie\\Camicetta\\";
                case 8 -> path += "maglie\\Maglietta\\";
                case 10 -> path += "maglie\\Top\\";
                case 11 -> path += "pantaloni\\Lunghi\\";
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
                case 12 -> path += "pantaloni\\Corto\\";
                default -> path = "";
            }
        }
        return path;
    }
}