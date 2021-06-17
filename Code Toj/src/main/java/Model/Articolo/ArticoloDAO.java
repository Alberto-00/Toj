package Model.Articolo;

import java.util.List;

public interface ArticoloDAO <E extends Exception>{


    List<Articolo> doRetrieveNewProductsBySex(String sex) throws E;

    List<Articolo> doRetrieveProductByNome(String nome) throws E;

    Articolo doRetrieveProductById(int id) throws E;

    boolean doCreateArticolo(Articolo articolo) throws E;

    boolean doUpdateArticolo(Articolo articolo) throws E;

    boolean doDeleteArticolo(Articolo articolo) throws E;
}
