package Model.Articolo;

import Controller.http.JSONSerializable;
import Model.Categoria.Categoria;
import Model.Colore.Colore;
import Model.Path_immagini.PathImg;
import Model.Taglia.Taglia;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Articolo implements JSONSerializable, Cloneable {

    //Dati dell'articolo
    private String sesso, descrizione, nome;
    private double prezzo;
    private int IDarticolo, sconto;
    private Date data_inserimento;
    private int quantity;
    private int localQuantity;
    private String chosenSize;
    private Categoria categoria;
    private List<PathImg> paths;
    private List<Taglia> taglie;
    private List<Colore> colori;

    //Constructor
    public Articolo(){
        this.categoria = new Categoria();
        this.paths = new ArrayList<>();
        this.colori = new ArrayList<>();
        this.taglie = new ArrayList<>();
        localQuantity = 0;
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

    public int getSconto() {
        return sconto;
    }

    public void setSconto(int sconto) {
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

    public List<PathImg> getPaths() {
        return paths;
    }

    public void setPaths(List<PathImg> paths) {
        this.paths = paths;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLocalQuantity() {
        return localQuantity;
    }

    public void setLocalQuantity(int localQuantity) {
        this.localQuantity += localQuantity;
    }

    public void lessLocalQuantity(int localQuantity){
        this.localQuantity = localQuantity;
    }

    public String getChosenSize() {
        return chosenSize;
    }

    public void setChosenSize(String chosenSize) {
        this.chosenSize = chosenSize;
    }

    public boolean containsPath(String str){
        for (PathImg p: paths){
            if(p.getPathName().equals(str))
                return true;
        }
        return false;
    }

    public boolean containsSize(String taglia){
        for (Taglia t: taglie){
            if(t.getId_nome().equals(taglia))
                return true;
        }
        return false;
    }

    public boolean containsColors(String color){
        for (Colore c: colori){
            if(c.getNome().equals(color))
                return true;
        }
        return false;
    }

    public double getPrezzoScontato(){
        if(this.sconto > 0){
            return this.prezzo - (this.prezzo * this.sconto);
        }
        return this.prezzo;
    }

    public double totalPrice(){
        return getPrezzoScontato() * this.localQuantity;
    }

    @Override
    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        JSONArray taglie = new JSONArray();
        JSONArray colori = new JSONArray();
        JSONArray paths = new JSONArray();
        object.put("sesso", sesso);
        object.put("descrizione", descrizione);
        object.put("nome", nome);
        object.put("prezzo", prezzo);
        object.put("IDarticolo", IDarticolo);
        object.put("sconto", sconto);
        object.put("categoria", this.categoria.toJson());
        object.put("taglie", taglie);
        object.put("paths", paths);
        object.put("colori", colori);
        this.taglie.forEach(t -> taglie.add(t.toJson()));
        this.colori.forEach(c -> colori.add(c.toJson()));
        this.paths.forEach(p -> paths.add(p.toJson()));
        return object;
    }
}
