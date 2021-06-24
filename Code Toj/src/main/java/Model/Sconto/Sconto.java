package Model.Sconto;

import Controller.http.JSONSerializable;
import Model.Ordine.Ordine;
import Model.Account.Account;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sconto implements JSONSerializable {

    private String codice;
    private LocalDate dataScadenza;
    private double sconto;
    private Ordine ordine;
    private Account user;

    public Sconto(){
        super();
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    @Override
    public JSONObject toJson(){
        JSONObject object = new JSONObject();
        object.put("codice", codice);
        object.put("sconto", sconto);
        object.put("data", dataScadenza.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return object;
    }
}
