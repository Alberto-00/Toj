package Model.Colore;

import Model.Articolo.Articolo;
import Model.Taglia.Taglia;

import java.util.List;

public interface ColoreDAO<E extends Exception> {

    List<Colore> doRetrieveBySex(String sex) throws E;

    boolean createColore(Colore colore) throws E;

    boolean updateColore(Colore colore) throws E;

    boolean deleteColore(Colore colore) throws E;
}
