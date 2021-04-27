package Model;

import java.util.Date;

public class Sconto {

    private String codice;
    private Date dataScadenza;
    private double sconto;
    private Ordine ordine;

    public Sconto(){
        super();
        ordine = new Ordine();
    }

    public String getOrdineID(){
        return ordine.getID_ordine();
    }

    public void setOrdineID(String id){
        this.ordine.setID_ordine(id);
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }
}
