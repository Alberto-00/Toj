package Model.Ordine;

import java.util.List;

public interface OrdineDAO  <E extends Exception>{

    List<Ordine> fetchOrdine(Ordine ordine) throws E;

    Ordine fetchOrdine(Ordine ordine, String idOrdine) throws E;

    boolean createOrdine(Ordine ordine) throws E;

    boolean updateOrdine(Ordine ordine) throws E;

    boolean deleteOrdine(Ordine ordine) throws E;
}
