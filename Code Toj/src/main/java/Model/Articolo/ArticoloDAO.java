package Model.Articolo;

import Model.search.Condition;
import Model.search.Paginator;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface ArticoloDAO <E extends Exception>{

    List<Articolo> doRetrieveNewArrivals(String sex) throws E;

    List<Articolo> doRetrieveProductByNome(String nome) throws E;

    List<String> doRetrieveProductByNome() throws E;

    List<Articolo> search(List<Condition> conditions, boolean latest) throws E;

    List<Articolo> searchPagination(List<Condition> conditions, Paginator paginator, boolean latest) throws E;

    double maxPrice() throws E;

    double minPrice() throws E;

    Articolo doRetrieveProductById(int id) throws E;

    Articolo doRetrieveProductById_Size(String size, int id) throws E;

    int countArticoli() throws E;

    List<Articolo> getArticoli() throws E;

    List<Articolo> getArticoliPage(Paginator paginator) throws E;

    boolean reduceSize(Articolo articolo) throws E;

    void doCreateArticolo(Articolo articolo) throws E;

    void doUpdateArticolo(Articolo articolo) throws E;

    boolean doDeleteArticolo(Articolo articolo) throws E;
}
