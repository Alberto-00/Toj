package Model.Colore;

import Model.Articolo.Articolo;

import java.util.List;

public interface ColoreDAO<E extends Exception> {

    List<Colore> doRetrieveAll() throws E;

    Colore doRetrieveById(String hex) throws E;

    List<Colore> doRetrieveBySex(String sex) throws E;

    boolean createTinta(Articolo articolo) throws E;

    boolean deleteTinta(Articolo articolo) throws E;
}
