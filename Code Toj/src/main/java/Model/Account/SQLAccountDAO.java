package Model.Account;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SQLAccountDAO implements AccountDAO{

    @Override
    public Optional<Account> findAccount(String email, String password, boolean admin) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT a.* " +
                    "FROM account_user a " +
                    "WHERE Email = ? AND Password_User = ? AND Admin_user = ?");

            ps.setString(1, email);
            ps.setString(2, password);
            ps.setBoolean(3, admin);

            ResultSet rs = ps.executeQuery();
            Account account = null;
            if(rs.next()){
                account = new AccountExtractor().extract(rs);
            }
            return Optional.ofNullable(account);
        }
    }

    @Override
    public boolean doCreateArticolo(Account account) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("account_user", "a");
            queryBuilder.insert("Email", "Password_user", "Admin_user");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, account.getEmail());
                ps.setString(2,account.getPassword());
                ps.setBoolean(3,account.isAdmin());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doUpdateArticolo(Account account) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("account_user", "a");
            queryBuilder.update("Email", "Password_user", "Admin_user").where("a.Email = " + account.getEmail());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, account.getEmail());
                ps.setString(2,account.getPassword());
                ps.setBoolean(3,account.isAdmin());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doDeleteArticolo(Account account) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("account_user", "a");
            queryBuilder.delete().where("a.Email = " + account.getEmail());
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, account.getEmail());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }
}
