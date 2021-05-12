package Model.Carta_Elettronica;

import Model.Account.Account;

public class CartaElettronica {

    //Dati della Carta di pagamento
    private String IDcarta, descrizione;
    private Account user;

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

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }
}
