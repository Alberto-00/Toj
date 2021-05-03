package Model.Taglia;

import Model.Articolo.Articolo;

import java.util.List;

public class Taglia {

    private String id_nome;
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
}
