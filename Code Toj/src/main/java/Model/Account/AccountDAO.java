package Model.Account;

import java.util.List;

public interface AccountDAO <E extends Exception>{

    List<Account> doRetrieveAll() throws E;

    boolean doCreateArticolo(Account account) throws E;

    boolean doUpdateArticolo(Account account) throws E;

    boolean doDeleteArticolo(Account account) throws E;
}
