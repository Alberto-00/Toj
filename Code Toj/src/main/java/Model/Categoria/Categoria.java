package Model.Categoria;

import Controller.http.JSONSerializable;
import Model.Articolo.Articolo;
import org.json.simple.JSONObject;

import java.util.List;

public class Categoria implements JSONSerializable {

    private int id_categoria;
    private String nome;
    private List<Articolo> articolo;

    public Categoria(){
        super();
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Articolo> getArticolo() {
        return articolo;
    }

    public void setArticolo(List<Articolo> articolo) {
        this.articolo = articolo;
    }

    @Override
    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("id_categoria", id_categoria);
        object.put("nome_categoria", nome);
        return object;
    }
}
