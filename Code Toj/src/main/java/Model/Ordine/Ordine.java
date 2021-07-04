package Model.Ordine;
import Model.Articolo.Articolo;
import Model.Sconto.Sconto;
import Model.Account.Account;
import java.sql.SQLException;
import java.util.*;

public class Ordine {

    private String ID_ordine;
    private Date data_acquisto;
    private Date data_spedizione;
    private int quantita_articolo;
    private double total;
    private List<Articolo> articoli;
    private Sconto codSconto;
    private Account user;

    public Ordine(){
        user = new Account();
        ID_ordine = generateID();
        data_acquisto = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(data_acquisto);
        cal.add(Calendar.DAY_OF_MONTH, 10);
        data_spedizione = cal.getTime();
        articoli = new ArrayList<>();
        this.total = 0;
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

    public Date getData_spedizione() {
        return data_spedizione;
    }

    public void setData_spedizione(Date data_spedizione) {
        this.data_spedizione = data_spedizione;
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public void setArticoli(List<Articolo> articoli) {
        this.articoli = articoli;
    }

    public Sconto getCodSconto() {
        return codSconto;
    }

    public void setCodSconto(Sconto codSconto) {
        this.codSconto = codSconto;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public int getQuantita() {
        return quantita_articolo;
    }

    public void setQuantita(int quantita) {
        this.quantita_articolo = quantita;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void applySconto() throws SQLException {
        SQLOrdineDAO sqlOrdineDAO = new SQLOrdineDAO();
        Sconto sconto = sqlOrdineDAO.getOrdineSconto(getID_ordine());
        if(sconto!=null) {
            setTotal(Math.round((this.total * sconto.getSconto())*100.0)/100.00);
        }
        setTotal(getTotal()+4.5);
    }

    public double total() throws SQLException {
        for(Articolo a: this.articoli){
            this.total += a.getQuantita_articolo_in_Ordine() * a.getPrezzo();
        }
        applySconto();
        return getTotal();
    }

    private String  generateID() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    public boolean containsArticolo(int id){
        for (Articolo c: articoli){
            if(c.getIDarticolo() == id)
                return true;
        }
        return false;
    }
}