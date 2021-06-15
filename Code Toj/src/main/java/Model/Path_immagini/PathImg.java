package Model.Path_immagini;

import Model.Articolo.Articolo;
import Model.Dati_utente.DatiUtente;
import Model.Ordine.Ordine;
import Model.Sconto.Sconto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class PathImg {

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
}