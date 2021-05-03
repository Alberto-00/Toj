package Model;

public class Articolo {

    //Dati dell'articolo
    private String sesso, descrizione;
    private double prezzo, sconto;
    private int IDarticolo;
    private final Categoria categoria;

    //Constructor
    public Articolo(){
        super();
        categoria = new Categoria();
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

    public int getIdCategoria(){
        return categoria.getId_categoria();
    }

}
