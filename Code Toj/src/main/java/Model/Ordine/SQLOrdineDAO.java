package Model.Ordine;

import Model.Account.Account;
import Model.Account.AccountExtractor;
import Model.Articolo.Articolo;
import Model.Articolo.ArticoloExtractor;
import Model.search.Paginator;
import Model.storage.ConPool;
import Model.storage.QueryBuilder;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SQLOrdineDAO implements OrdineDAO<SQLException> {

    public SQLOrdineDAO(){
        super();
    }

    @Override
    public int doRetrieveAll() throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT COUNT(*) as count FROM ordine;");
            if (resultSet.next())
                return resultSet.getInt("count");
            return 0;
        }
    }

    @Override
    public List<Ordine> fetchOrdine(String email) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "o");
            queryBuilder.select("*,c.Quantita_articolo,a.*,ac.Password_User,ac.Admin_user,s.Quantita").
                    innerJoin("composizione", "c").on("o.ID_ordine = c.ID_ordine").
                    innerJoin("account_user", "ac").on("o.Email = ac.Email").
                    innerJoin("articolo", "a").on("c.ID_articolo = a.ID_articolo").
                    innerJoin("size", "s").on("a.ID_articolo = s.ID_articolo").
                    where("o.Email='" + email + "'");

            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ResultSet rs = ps.executeQuery();
                OrdineExtractor ordineExtractor = new OrdineExtractor();
                AccountExtractor accountExtractor = new AccountExtractor();
                ArticoloExtractor articoloExtractor = new ArticoloExtractor();
                Map<String, Ordine> ordineMap = new LinkedHashMap<>();

                if (rs.next()) {
                    Ordine ordine = ordineExtractor.extract(rs);
                    ordine.setUser(accountExtractor.extract(rs));
                    ordine.setArticoli(new ArrayList<>());
                    ordine.getArticoli().add(articoloExtractor.extract2(rs));
                    ordineMap.put(ordine.getID_ordine(), ordine);

                    while (rs.next()) {
                        String ID_ordine = rs.getString("ID_ordine");
                        if (!ordineMap.containsKey(ID_ordine)) {
                            ordine = ordineExtractor.extract(rs);
                            ordine.setArticoli(new ArrayList<>());
                            ordineMap.put(ID_ordine, ordine);
                            ordineMap.get(ID_ordine).getArticoli().add(articoloExtractor.extract2(rs));
                        }
                        if (!ordineMap.get(ID_ordine).containsArticolo(rs.getInt("ID_articolo")))
                            ordineMap.get(ID_ordine).getArticoli().add(articoloExtractor.extract2(rs));
                    }
                }
                return new ArrayList<>(ordineMap.values());
            }
        }
    }

    @Override
    public boolean doInsertOrdine(Ordine ordine) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = con.prepareStatement("INSERT INTO ordine (ID_ordine, data_acquisto, data_spedizione, Email) VALUES " +
                    "('" + ordine.getID_ordine() +"','"+ formatter.format(ordine.getData_acquisto()) + "','" + formatter.format(ordine.getData_spedizione()) + "','"
                    + ordine.getUser().getEmail() +"')");
            int rows;
            rows = ps.executeUpdate();

            for (Articolo a: ordine.getArticoli()){
                doInsertComposizione(a, ordine);
            }
            return rows == 1;
        }
    }

    private boolean doInsertComposizione(Articolo a, Ordine o) throws SQLException{
        try(Connection con = ConPool.getConnection()) {
            PreparedStatement ps1 = con.prepareStatement("INSERT INTO composizione (ID_ordine, ID_articolo, Quantita_articolo) VALUES " +
                        "('" + o.getID_ordine() +"',"+ a.getIDarticolo() + "," + a.getLocalQuantity() + ")");
            int rows = ps1.executeUpdate();
            return rows == 1;
        }
    }

    @Override
    public boolean doUpdateOrdine(Ordine ordine) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "o");
            queryBuilder.update("ID_ordine", "data_acquisto", "data_spedizione", "Email").where("o.ID_ordine=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, ordine.getID_ordine());
                ps.setDate(2, (Date) ordine.getData_acquisto());
                ps.setDate(3, (Date) ordine.getData_spedizione());
                ps.setString(4, ordine.getUser().getEmail());
                ps.setString(5, ordine.getID_ordine());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public boolean doDeleteOrdine(Ordine ordine) throws SQLException {
        try(Connection con = ConPool.getConnection()) {
            QueryBuilder queryBuilder = new QueryBuilder("ordine", "o");
            queryBuilder.delete().where("o.ID_ordine=?");
            try (PreparedStatement ps = con.prepareStatement(queryBuilder.generateQuery())) {
                ps.setString(1, ordine.getID_ordine());
                int rows = ps.executeUpdate();
                return rows == 1;
            }
        }
    }

    @Override
    public int countOrdini() throws SQLException {
        int ordini = 0;
        try(Connection con = ConPool.getConnection()){
            Statement stm = con.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT count(*) FROM ordine");
            if(resultSet.next()){
                ordini=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ordini;
    }

    @Override
    public ArrayList<Ordine> getOrders(Paginator paginator) throws SQLException {
        ArrayList<Ordine> ordini = new ArrayList<>();
        try(Connection con = ConPool.getConnection()){
            PreparedStatement stm = con.prepareStatement("SELECT * " +
                    "FROM ordine LIMIT ?,?");
            stm.setInt(1,paginator.getOffset());
            stm.setInt(2,paginator.getLimit());
            ResultSet resultSet = stm.executeQuery();

            while (resultSet.next()){
                Ordine ordine =  new Ordine();
                ordine.setID_ordine(resultSet.getString(1));
                ordine.setData_acquisto(resultSet.getDate(2));
                ordine.setData_spedizione(resultSet.getDate(3));
                ordine.setUser(new Account());
                ordine.getUser().setEmail(resultSet.getString(4));
                ordini.add(ordine);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ordini;
    }
}
