package Model.Articolo;

import Model.Categoria.Categoria;
import Model.Colore.Colore;
import Model.Taglia.Taglia;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

public class Articolo {

    //Dati dell'articolo
    private String sesso, descrizione, path, nome;
    private double prezzo, sconto;
    private int IDarticolo;
    private Date data_inserimento;
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

    public Date getData_inserimento() {
        return data_inserimento;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData_inserimento(Date data_inserimento) {
        this.data_inserimento = data_inserimento;
    }

    public void writeCover(String uploadPath, Part stream) throws IOException{
        try(InputStream fileStream = stream.getInputStream()){
            File file = new File(uploadPath + path);
            Files.copy(fileStream, file.toPath());
        }
    }

    public String[] splitPath(String path){
        String [] pathSplitted = path.split(",");
        return pathSplitted;
    }

    public String[] splitPath(){
        String [] pathSplitted = this.path.split(",");
        return pathSplitted;
    }
}
