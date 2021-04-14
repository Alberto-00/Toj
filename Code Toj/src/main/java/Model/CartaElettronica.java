package Model;

public class CartaElettronica {

    //Dati della Carta di pagamento
    private String IDcarta, descrizione;

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
}
