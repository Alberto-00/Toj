package Model.Account;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountExtractor implements ResultSetExtractor<Account> {

    @Override
    public Account extract(ResultSet rs) throws SQLException {
        Account user = new Account();
        user.setEmail(rs.getString("Email"));
        user.setAdmin(rs.getBoolean("Admin_user"));
        return user;
    }
}
