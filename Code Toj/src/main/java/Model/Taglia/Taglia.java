package Model.Taglia;

import Controller.http.JSONSerializable;
import Model.Articolo.Articolo;
import org.json.simple.JSONObject;

import java.util.List;

public class Taglia implements JSONSerializable {

    private String id_nome;
    private int quantita;
    private List<Articolo> articoli;

    public Taglia(){
        super();
    }

    public String getId_nome() {
        return id_nome;
    }

    public void setId_nome(String id_nome) {
        this.id_nome = id_nome;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("id_nome", id_nome);
        object.put("quantita", quantita);
        return object;
    }
}
