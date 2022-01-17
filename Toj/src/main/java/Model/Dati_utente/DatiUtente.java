package Model.Dati_utente;

import Model.Account.Account;

import java.util.Date;

public class DatiUtente {

    private String cognome;
    private String nome;
    private Date dataDiNascita;
    private String via;
    private String CAP;
    private String city;
    private String paese;
    private String appartamento;
    private String numeroTelefonico;
    private Account user;

    public DatiUtente(){
        super();
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getCAP() {
        return CAP;
    }

    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getAppartamento() {
        return appartamento;
    }

    public void setAppartamento(String appartamento) {
        this.appartamento = appartamento;
    }
}
