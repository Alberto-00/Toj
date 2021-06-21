package Model.Articolo;

import Model.search.Condition;
import Model.search.Paginator;

import java.util.List;

public interface ArticoloDAO <E extends Exception>{

    List<Articolo> doRetrieveNewArrivals(String sex) throws E;

    List<Articolo> doRetrieveProductByNome(String nome) throws E;

    List<Articolo> search(List<Condition> conditions) throws E;

    List<Articolo> searchPagination(List<Condition> conditions, Paginator paginator) throws E;

    List<Articolo> searchCategory(List<Condition> conditions) throws E;

    List<Articolo> searchTaglia(List<Condition> conditions) throws E;

    List<Articolo> searchColore(List<Condition> conditions) throws E;

    double maxPrice(String sex) throws E;

    double minPrice(String sex) throws E;

    Articolo doRetrieveProductById(int id) throws E;

    boolean doCreateArticolo(Articolo articolo) throws E;

    boolean doUpdateArticolo(Articolo articolo) throws E;

    boolean doDeleteArticolo(Articolo articolo) throws E;
}
