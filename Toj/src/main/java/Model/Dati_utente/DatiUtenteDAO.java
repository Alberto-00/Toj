package Model.Dati_utente;

import Model.search.Paginator;

import java.util.List;
import java.util.Optional;

public interface DatiUtenteDAO <E extends Exception>{

    Optional<DatiUtente> findUserData(String email) throws E;

    List<DatiUtente> doRetriveAll(Paginator paginator) throws E;

    boolean updateDatiUtete (DatiUtente datiUtente) throws E;

    boolean updateAddressUtente (DatiUtente datiUtente) throws E;

    boolean doDeleteDatiUtente (DatiUtente datiUtente) throws E;

}
