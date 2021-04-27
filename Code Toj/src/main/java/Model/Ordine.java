package Model;

import java.util.Date;

public class Ordine {

    private String ID_ordine;
    private Date data_acquisto;
    private boolean pacchetto_regalo;
    private Date data_spedizione;
    private String descrizione;
    private Utente user;

    public Ordine(){
        super();
        user = new Utente();
    }

    public String getID_ordine() {
        return ID_ordine;
    }

    public void setID_ordine(String ID_ordine) {
        this.ID_ordine = ID_ordine;
    }

    public Date getData_acquisto() {
        return data_acquisto;
    }

    public void setData_acquisto(Date data_acquisto) {
        this.data_acquisto = data_acquisto;
    }

    public boolean isPacchetto_regalo() {
        return pacchetto_regalo;
    }

    public void setPacchetto_regalo(boolean pacchetto_regalo) {
        this.pacchetto_regalo = pacchetto_regalo;
    }

    public Date getData_spedizione() {
        return data_spedizione;
    }

    public void setData_spedizione(Date data_spedizione) {
        this.data_spedizione = data_spedizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    private String getEmailUser(){
        return user.getEmail();
    }

    private void setEmailUser(String email){
        this.user.setEmail(email);
    }
}
