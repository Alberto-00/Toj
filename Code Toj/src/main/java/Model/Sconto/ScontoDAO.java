package Model.Sconto;

import java.util.List;

public interface ScontoDAO <E extends Exception> {

    List<Sconto> doRetrieveAll() throws E;

    Sconto doRetrieveByName(String id) throws E;

    boolean findSconto(String id) throws E;

    boolean doCreateSconto(Sconto sconto) throws E;

    boolean doUpdateSconto(Sconto sconto) throws E;

    boolean doDeleteSconto(String sconto) throws E;
}
