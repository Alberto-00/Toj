package Model.Path_immagini;

import Controller.http.JSONSerializable;
import Model.Articolo.Articolo;
import Model.Categoria.Categoria;
import Model.Dati_utente.DatiUtente;
import Model.Ordine.Ordine;
import Model.Sconto.Sconto;
import org.json.simple.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class PathImg implements JSONSerializable, Cloneable {

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
}