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
            ResultSet resultSet = stm.executeQuery("SELECT a.* " +
                    "FROM account_user a " +
                    "WHERE a.Email = "+"\'"+email+"\'"+" AND a.Password_User = "+"\'"+password+"\'"+" AND a.Admin_user = "+admin+" ");

            Account account = null;
            if(resultSet.next()){

                account = new AccountExtractor().extract(resultSet);
                System.out.println("SIAMO NELL'IF");
            }
            return Optional.ofNullable(account);
        }
    }
    /*
    @Override
    public Optional<Account> findAccount(String email, String password, boolean admin) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT a.* " +
                    "FROM account_user a " +
                    "WHERE a.Email = "+"\'"+email+"\'"+" AND a.Password_User = "+"\'"+password+"\'"+" AND a.Admin_user = "+admin+" ");
            System.out.println("PS RETURN" + ps.toString());
            System.out.println("PS Excecute " + ps.executeQuery().getString(0));
            ResultSet rs = ps.executeQuery();
            System.out.println(rs.next());

            Account account = null;
            if(rs.next()){
                account = new AccountExtractor().extract(rs);
            }
            System.out.println("ACCOUTN SQL"+account.getEmail()+" "+account.getPassword());
            return Optional.ofNullable(account);
        }
    }*/




    /*
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
    }*/
}
