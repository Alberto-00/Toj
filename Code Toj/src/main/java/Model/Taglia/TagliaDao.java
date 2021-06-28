package Model.Taglia;

import Model.Articolo.Articolo;

import java.util.List;

public interface TagliaDao <E extends Exception>{

    List<Taglia> doRetrieveAll() throws E;

    List<Taglia> doRetrieveBySex(String sex) throws E;

    boolean createSize(Articolo articolo) throws E;

    boolean doCreateTaglia(Taglia taglia) throws E;

    boolean doUpdateTaglia(Taglia taglia) throws E;

    boolean doDeleteTaglia(Taglia taglia) throws E;
}
