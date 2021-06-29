package Model.Account;

import java.util.Optional;

public interface AccountDAO <E extends Exception>{

    Optional<Account> findAccount(String email, String password, boolean admin) throws E;

    Optional<Account> checkAccount(String email) throws E;

    int createAccount (String email, String password, boolean admin) throws E;

    boolean updateAccount(Account account) throws E;

    int countCustomers() throws E;
}
