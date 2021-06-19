package Model.Articolo;

import Model.search.Paginator;

import java.util.List;

public interface ArticoloDAO <E extends Exception>{


    List<Articolo> pagination(String sex) throws E;

    List<Articolo> doRetrieveProductByNome(String nome) throws E;

    List<Articolo> pagination(String sex, Paginator paginator) throws E;

    List<Articolo> doRetrieveProductBySexType(String sex, String type) throws E;

    int getFirstId(String sex, int off) throws E;
    
    int countAll(String sex) throws E;

    Articolo doRetrieveProductById(int id) throws E;

    boolean doCreateArticolo(Articolo articolo) throws E;

    boolean doUpdateArticolo(Articolo articolo) throws E;

    boolean doDeleteArticolo(Articolo articolo) throws E;
}
