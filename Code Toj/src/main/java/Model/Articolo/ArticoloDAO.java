package Model.Articolo;

import java.util.List;

public interface ArticoloDAO <E extends Exception>{

    List<Articolo> doRetrieveAllNewProducts() throws E;

    boolean doCreateArticolo(Articolo articolo) throws E;

    boolean doUpdateArticolo(Articolo articolo) throws E;

    boolean doDeleteArticolo(Articolo articolo) throws E;
}
