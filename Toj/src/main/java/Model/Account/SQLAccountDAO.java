package Model.Account;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.util.Optional;

public class SQLAccountDAO implements AccountDAO<SQLException>{

    @Override
    public Optional<Account> findAccount(String email, String password, boolean admin) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT a.* FROM account_user a " +
                    "WHERE a.Email = '" + email + "'"+" AND a.Password_User = '" +password+ "'"+" AND a.Admin_user = " + admin +";")){
                ResultSet resultSet = ps.executeQuery();
                Account account = null;
                if(resultSet.next())
                    account = new AccountExtractor().extract(resultSet);
                return Optional.ofNullable(account);
            }
        }
    }

    @Override
    public int count() throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) as count " +
                    "FROM account_user WHERE Admin_user = 0;")){
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next())
                    return resultSet.getInt("count");
                return 0;
            }
        }
    }

    @Override
    public Optional<Account> checkAccount(String email) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            try(PreparedStatement ps = con.prepareStatement("SELECT a.* FROM account_user a " +
                    "WHERE a.Email = '" + email + "';")){
                ResultSet resultSet = ps.executeQuery();
                Account account = null;
                if(resultSet.next())
                    account = new AccountExtractor().extract(resultSet);
                return Optional.ofNullable(account);
            }
        }
    }

    @Override
    public int createAccount(String email, String password, boolean admin) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("account_user", "a");
            QueryBuilder queryBuilder1 = new QueryBuilder("dati_cliente", "data");
            queryBuilder.insert("Email", "Password_user", "Admin_user");
            queryBuilder1.insert("Email");
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

    @Override
    public int countCustomers() throws SQLException {
        int customers = 0;
        try (Connection con = ConPool.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement("SELECT count(*)" +
                    "FROM account_user WHERE Admin_user = false")){
                ResultSet resultSet = ps.executeQuery();
                if (resultSet.next()) {
                    customers = resultSet.getInt(1);
                }
                return customers;
            }
        }
    }
}
