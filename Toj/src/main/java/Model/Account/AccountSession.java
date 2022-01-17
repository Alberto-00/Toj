package Model.Account;

public class AccountSession {

    private final String email;
    private final String password;
    private final boolean isAdmin;

    public AccountSession(Account account){
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.isAdmin = account.isAdmin();
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getPassword() {
        return password;
    }
}
