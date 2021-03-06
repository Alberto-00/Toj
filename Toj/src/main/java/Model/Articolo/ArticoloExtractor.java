package Model.Articolo;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticoloExtractor implements ResultSetExtractor<Articolo> {

    /* utilizzeremo questo metodo per snellire il codice del DAO ed evitare
    di scrivere linee di codice ridondanti */

    @Override
    public Articolo extract(ResultSet rs) throws SQLException {
        Articolo articolo = new Articolo();
        articolo.setIDarticolo(rs.getInt("ID_articolo"));
        articolo.setPrezzo(rs.getDouble("Prezzo"));
        articolo.setSesso(rs.getString("Sesso"));
        articolo.setDescrizione(rs.getString("Descrizione"));
        articolo.setSconto(rs.getDouble("sconto"));
        articolo.setData_inserimento(rs.getDate("data_inserimento"));
        articolo.setNome(rs.getString("nome_articolo"));
        return articolo;
    }

    public Articolo extract2(ResultSet rs) throws SQLException {
        Articolo articolo = new Articolo();
        articolo.setIDarticolo(rs.getInt("ID_articolo"));
        articolo.setPrezzo(rs.getDouble("Prezzo"));
        articolo.setSesso(rs.getString("Sesso"));
        articolo.setDescrizione(rs.getString("Descrizione"));
        articolo.setSconto(rs.getDouble("sconto"));
        articolo.setData_inserimento(rs.getDate("data_inserimento"));
        articolo.setNome(rs.getString("nome_articolo"));
        articolo.setQuantita_articolo_in_Ordine(rs.getInt("Quantita_articolo"));
        return articolo;
    }

}
