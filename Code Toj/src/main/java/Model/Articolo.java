package Model;

import java.util.List;

public class Articolo {

    //Dati dell'articolo
    private String sesso, descrizione;
    private double prezzo, sconto;
    private int IDarticolo;
    private Categoria categoria;
    private List<Taglia> taglie;
    private List<Colore> colori;

    //Constructor
    public Articolo(){
        super();
    }

    //Getter & Setter
    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getIDarticolo() {
        return IDarticolo;
    }

    public void setIDarticolo(int IDarticolo) {
        this.IDarticolo = IDarticolo;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Taglia> getTaglie() {
        return taglie;
    }

    public void setTaglie(List<Taglia> taglie) {
        this.taglie = taglie;
    }

    public List<Colore> getColori() {
        return colori;
    }

    public void setColori(List<Colore> colori) {
        this.colori = colori;
    }
}
