package Model.Ordine;

import Model.Articolo.Articolo;
import Model.Sconto.Sconto;
import Model.search.Paginator;

import java.util.List;

public interface OrdineDAO  <E extends Exception>{

    List<Ordine> fetchOrdine(String email) throws E;

    List<Articolo> fetchArticoliOrdine(String idOrdine) throws E;

    Sconto getOrdineSconto(String sconto) throws E;

    int doRetrieveAll() throws E;

    boolean doInsertOrdine(Ordine ordine) throws E;

    int countOrdini() throws E;

    List<Ordine> getOrders(Paginator paginator) throws E;

    boolean doUpdateOrdine(Ordine ordine) throws E;

    boolean doDeleteOrdine(Ordine ordine) throws E;
}
