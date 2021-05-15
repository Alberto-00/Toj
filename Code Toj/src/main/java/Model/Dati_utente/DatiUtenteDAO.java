package Model.Dati_utente;

import java.util.List;

public interface DatiUtenteDAO <E extends Exception>{

    List<DatiUtente> doRetriveAll() throws E;

    boolean doCreateDatiUtente (DatiUtente datiUtente) throws E;

    boolean doUpdateDatiUtete (DatiUtente datiUtente) throws E;

    boolean doDeleteDatiUtente (DatiUtente datiUtente) throws E;
}
