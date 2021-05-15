package Model.Ordine;

import java.util.List;

public interface OrdineDAO  <E extends Exception>{

    List<Ordine> fetchOrdine(Ordine ordine) throws E;

    Ordine fetchOrdine(Ordine ordine, String idOrdine) throws E;

    boolean doCreateOrdine(Ordine ordine) throws E;

    boolean doUpdateOrdine(Ordine ordine) throws E;

    boolean doDeleteOrdine(Ordine ordine) throws E;
}
