package Model.Account;

import java.util.Optional;

public interface AccountDAO <E extends Exception>{

    Optional<Account> findAccount(String email, String password, boolean admin) throws E;

}
