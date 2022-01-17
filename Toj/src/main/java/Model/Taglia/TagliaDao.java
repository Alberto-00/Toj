package Model.Taglia;

import Model.Articolo.Articolo;

import java.util.List;

public interface TagliaDao <E extends Exception>{

    List<Taglia> doRetrieveAll() throws E;

    List<Taglia> doRetrieveAllByID(Articolo articolo) throws E;

    List<Taglia> doRetrieveBySex(String sex) throws E;

    boolean createSize(Articolo articolo) throws E;

    boolean doUpdateSize(Articolo articolo, Taglia taglia) throws E;

}
