package Model.Colore;

import java.sql.SQLException;
import java.util.List;

public interface ColoreDAO<E extends Exception> {

    List<Colore> doRetrieveAll() throws E;

    List<Colore> doRetrieveBySex(String sex) throws E;

    boolean createTinta(int idArticolo,String idColore) throws E;

    boolean createColore(Colore colore) throws E;

    boolean updateColore(Colore colore) throws E;

    boolean deleteColore(Colore colore) throws E;
}
