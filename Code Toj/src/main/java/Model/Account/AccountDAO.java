package Model.Account;

import Model.search.Paginator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AccountDAO <E extends Exception>{

    Optional<Account> findAccount(String email, String password, boolean admin) throws E;

    public int countCustomers() throws E;

    ArrayList<Account> getAccounts(Paginator paginator) throws E;

}
