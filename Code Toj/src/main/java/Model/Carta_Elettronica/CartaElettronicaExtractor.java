package Model.Carta_Elettronica;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CartaElettronicaExtractor implements ResultSetExtractor<CartaElettronica>{

    @Override
    public CartaElettronica extract(ResultSet rs) throws SQLException{
        CartaElettronica carta = new CartaElettronica();
        carta.setIDcarta(rs.getString("Codice_carta"));
        carta.setDescrizione(rs.getString("Descrizione"));
        return carta;
    }
}
