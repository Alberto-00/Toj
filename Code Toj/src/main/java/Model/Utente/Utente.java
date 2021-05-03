package Model.Utente;

import Model.Carta_Elettronica.CartaElettronica;
import Model.Dati_utente.DatiUtente;
import Model.Ordine.Ordine;
import Model.Sconto.Sconto;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;import java.util.List;

public class Utente {

    //Dati dell'Account
    private String email;
    private String password;
    private boolean admin;
    private List<Ordine> ordini;
    private List<Sconto> codSconto;
    private List<CartaElettronica> cartePay;
    private DatiUtente dtUser;

    public Utente(){
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.password = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    public List<Sconto> getCodSconto() {
        return codSconto;
    }

    public void setCodSconto(List<Sconto> codSconto) {
        this.codSconto = codSconto;
    }

    public List<CartaElettronica> getCartePay() {
        return cartePay;
    }

    public void setCartePay(List<CartaElettronica> cartePay) {
        this.cartePay = cartePay;
    }

    public DatiUtente getDtUser() {
        return dtUser;
    }

    public void setDtUser(DatiUtente dtUser) {
        this.dtUser = dtUser;
    }
}
