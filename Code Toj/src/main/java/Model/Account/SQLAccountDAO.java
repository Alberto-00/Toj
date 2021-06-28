package Model.Account;

import Model.search.Paginator;
import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
            }
            return Optional.ofNullable(account);
        }
    }

    @Override
    public int countCustomers(){
        int customers = 0;
        try(Connection con = ConPool.getConnection()){
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT count(*)" +
                    "FROM account_user " +
                    "where Admin_user = false");
            if(resultSet.next()){
                customers=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customers;
    }

    @Override
    public ArrayList<Account> getAccounts(Paginator paginator) throws SQLException {
        ArrayList<Account> accounts = new ArrayList<>();
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stm = con.prepareStatement("SELECT *" +
                    "FROM account_user " +
                    "WHERE Admin_user = false " +
                    "LIMIT ?,?");
            stm.setInt(1,paginator.getOffset());
            stm.setInt(2,paginator.getLimit());
            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()){
                Account account = new Account();
                account.setEmail(resultSet.getString(1));
                account.passWord(resultSet.getString(2));
                account.setAdmin(resultSet.getBoolean(3));
                accounts.add(account);
            }
        }
        return accounts;
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
