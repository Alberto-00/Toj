package Model.Sconto;

import java.util.List;

public interface ScontoDAO <E extends Exception> {

    List<Sconto> doRetriveByUser(String email) throws E;

    boolean doCreateSconto(Sconto sconto) throws E;

    boolean doUpdateSconto(Sconto sconto) throws E;

    boolean doDeleteSconto(Sconto sconto) throws E;
}
