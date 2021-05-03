package Model.Carta_Elettronica;

import Model.Utente.Utente;

public class CartaElettronica {

    //Dati della Carta di pagamento
    private String IDcarta, descrizione;
    private Utente user;

    //Constructor
    public CartaElettronica(){
        super();
    }

    //Getter & Setter
    public String getIDcarta() {
        return IDcarta;
    }

    public void setIDcarta(String IDcarta) {
        this.IDcarta = IDcarta;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Utente getUser() {
        return user;
    }

    public void setUser(Utente user) {
        this.user = user;
    }
}
