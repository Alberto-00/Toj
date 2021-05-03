package Model.Categoria;

import Model.Articolo.Articolo;

import java.util.List;

public class Categoria {

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
}
