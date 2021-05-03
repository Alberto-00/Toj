package Model.Carta_Elettronica;

import java.util.List;

public interface CartaElettronicaDAO<E extends Exception>{

    List<CartaElettronica> fetchCartaElettronica(CartaElettronica payCard) throws E;

    boolean createCartaElettronica(CartaElettronica payCard) throws E;

    boolean updateCartaElettronica(CartaElettronica payCard) throws E;

    boolean deleteCartaElettronica(CartaElettronica payCard) throws E;
}
