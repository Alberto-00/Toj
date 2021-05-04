package Model.Colore;

import Model.Articolo.Articolo;

import java.util.List;

public interface ColoreDAO<E extends Exception> {

    List<Colore> fetchColoreByArticolo(int id_articolo) throws E;

    List<Colore> fetchColore() throws E;

    boolean createColore(Colore colore) throws E;

    boolean updateColore(Colore colore) throws E;

    boolean deleteColore(Colore colore) throws E;
}
