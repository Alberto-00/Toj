package Model.Articolo;

import Model.search.Paginator;

import java.util.List;

public interface ArticoloDAO <E extends Exception>{


    List<Articolo> doRetrieveNewProductsBySex(String sex) throws E;

    List<Articolo> doRetrieveProductByNome(String nome) throws E;

    List<Articolo> doRetrieveProductBySex(String sex, Paginator paginator) throws E;

    List<Articolo> doRetrieveProductBySexType(String sex, String type) throws E;
    
    int countAll() throws E;

    Articolo doRetrieveProductById(int id) throws E;

    boolean doCreateArticolo(Articolo articolo) throws E;

    boolean doUpdateArticolo(Articolo articolo) throws E;

    boolean doDeleteArticolo(Articolo articolo) throws E;
}
