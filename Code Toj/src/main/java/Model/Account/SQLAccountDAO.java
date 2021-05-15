package Model.Account;

import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;

public class SQLAccountDAO implements AccountDAO{
    @Override
    public List<Account> doRetrieveAll() throws Exception {
        return null;
    }

    @Override
    public boolean doCreateArticolo(Account account) throws Exception {
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
    public boolean doUpdateArticolo(Account account) throws Exception {
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
    public boolean doDeleteArticolo(Account account) throws Exception {
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
