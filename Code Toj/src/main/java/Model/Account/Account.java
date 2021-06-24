package Model.Account;

import Model.Dati_utente.DatiUtente;
import Model.Ordine.Ordine;
import Model.Sconto.Sconto;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;import java.util.List;

public class Account {

    //Dati dell'Account
    private String email;
    private String password;
    private boolean admin;
    private List<Ordine> ordini;
    private List<Sconto> codSconto;
    private DatiUtente dtUser;

    public Account(){
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
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hashedPwd = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder();
            for(byte bit : hashedPwd){
                builder.append(String.format("%02x", bit));
            }
            this.password = builder.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }

    public void passWord(String psw){
        this.password = psw;
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

    public DatiUtente getDtUser() {
        return dtUser;
    }

    public void setDtUser(DatiUtente dtUser) {
        this.dtUser = dtUser;
    }
}
