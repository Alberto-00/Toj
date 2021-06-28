package Model.Account;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.util.Optional;

public class SQLAccountDAO implements AccountDAO{

    @Override
    public Optional<Account> findAccount(String email, String password, boolean admin) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT a.* FROM account_user a " +
                    "WHERE a.Email = '" + email + "'"+" AND a.Password_User = '" +password+ "'"+" AND a.Admin_user = " + admin +";");
            Account account = null;
            if(resultSet.next())
                account = new AccountExtractor().extract(resultSet);
            return Optional.ofNullable(account);
        }
    }

    @Override
    public Optional<Account> checkAccount(String email) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT a.* FROM account_user a " +
                    "WHERE a.Email = '" + email + "';");
            Account account = null;
            if(resultSet.next())
                account = new AccountExtractor().extract(resultSet);
            return Optional.ofNullable(account);
        }
    }

    @Override
    public int createAccount(String email, String password, boolean admin) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("account_user", "a");
            QueryBuilder queryBuilder1 = new QueryBuilder("dati_cliente", "data");
            queryBuilder1.insert("Email");
            queryBuilder.insert("Email", "Password_user", "Admin_user");
            int rows;
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, email);
                ps.setString(2, password);
                ps.setBoolean(3, admin);
                ps.executeUpdate();
            }
            try (PreparedStatement ps = con.prepareStatement(queryBuilder1.generateQuery())){
                ps.setString(1, email);
                rows = ps.executeUpdate();
            }
            return rows;
        }
    }

    @Override
    public boolean updateAccount(Account account) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("account_user", "a");
            if (account.getPassword() != null && account.getPassword().compareTo("") != 0){
                queryBuilder.update("Email", "Password_user", "Admin_user").where("Email = '" + account.getEmail() + "'");
                try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                    ps.setString(1, account.getEmail());
                    ps.setString(2,account.getPassword());
                    ps.setBoolean(3,account.isAdmin());
                    int rows = ps.executeUpdate();
                    return rows == 1;
                }
            } else{
                queryBuilder.update("Email", "Admin_user").where("Email = '" + account.getEmail() + "'");
                try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                    ps.setString(1, account.getEmail());
                    ps.setBoolean(2,account.isAdmin());
                    int rows = ps.executeUpdate();
                    return rows == 1;
                }
            }
        }
    }
}
