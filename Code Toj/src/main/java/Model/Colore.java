package Model;

import Model.Articolo.Articolo;

import java.util.List;

public class Colore {

    private String cod_esadecimale, nome;
    private List<Articolo> articoli;

    public Colore(){
        super();
    }

    public String getCod_esadecimale() {
        return cod_esadecimale;
    }

    public void setCod_esadecimale(String cod_esadecimale) {
        this.cod_esadecimale = cod_esadecimale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }
}
