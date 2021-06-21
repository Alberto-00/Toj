package Model.Ordine;

import Model.Articolo.Articolo;
import Model.Sconto.Sconto;
import Model.Account.Account;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Ordine {

    private String ID_ordine;
    private LocalDate data_acquisto;
    private boolean pacchetto_regalo;
    private LocalDate data_spedizione;
    private String descrizione;
    private int quantita;
    private double total;
    private List<Articolo> articoli;
    private List<Sconto> codSconto;
    private Account user;

    public Ordine(List<Articolo> articoli, int quantity){
        this.articoli = new ArrayList<>();
        this.articoli.addAll(articoli);
        this.quantita = quantity;
        this.total = 0;
    }

    public String getID_ordine() {
        return ID_ordine;
    }

    public void setID_ordine(String ID_ordine) {
        this.ID_ordine = ID_ordine;
    }

    public LocalDate getData_acquisto() {
        return data_acquisto;
    }

    public void setData_acquisto(LocalDate data_acquisto) {
        this.data_acquisto = data_acquisto;
    }

    public boolean isPacchetto_regalo() {
        return pacchetto_regalo;
    }

    public void setPacchetto_regalo(boolean pacchetto_regalo) {
        this.pacchetto_regalo = pacchetto_regalo;
    }

    public LocalDate getData_spedizione() {
        return data_spedizione;
    }

    public void setData_spedizione(LocalDate data_spedizione) {
        this.data_spedizione = data_spedizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    public List<Sconto> getCodSconto() {
        return codSconto;
    }

    public void setCodSconto(List<Sconto> codSconto) {
        this.codSconto = codSconto;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double total(){
        for(Articolo a: this.articoli){
            this.total += a.getPrezzo();
        }
        return getTotal();
    }
}
