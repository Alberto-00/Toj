package Model;

public class CartaElettronica {

    //Dati della Carta di pagamento
    private String IDcarta, descrizione;
    private Utente user;

    //Constructor
    public CartaElettronica(){
        super();
        user = new Utente();
    }

    //Getter & Setter
    public String getEmail() {
        return user.getEmail();
    }

    public void setEmailUser(String email) {
        user.setEmail(email);
    }

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
}
