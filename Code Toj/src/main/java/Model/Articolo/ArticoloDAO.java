package Model.Articolo;

import java.util.List;

public interface ArticoloDAO <E extends Exception>{

    List<Articolo> doRetrieveNewProducts() throws E;

    List<Articolo> doRetrieveNewProductsBySex(String sex) throws E;

    Articolo doRetrieveProductById(int id) throws E;

    boolean doCreateArticolo(Articolo articolo) throws E;

    boolean doUpdateArticolo(Articolo articolo) throws E;

    boolean doDeleteArticolo(Articolo articolo) throws E;
}
