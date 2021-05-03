package Model;

import Model.Articolo.Articolo;
import Model.storage.ConPool;

import java.sql.*;
import java.util.ArrayList;

public class ArticoloDAO {

    public ArticoloDAO(){
        super();
    }

    public Articolo doRetrieveById(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM articolo WHERE ID_articolo=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Articolo articolo = new Articolo();
                articolo.setIDarticolo(rs.getInt("ID_articolo"));
                articolo.setDescrizione(rs.getString("Descrizione"));
                articolo.setPrezzo(rs.getDouble("Prezzo"));
                articolo.setSesso(rs.getString("Sesso"));
                articolo.setSconto(rs.getDouble("sconto"));
                ps.close();
                rs.close();
                return articolo;
            }
            ps.close();
            rs.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Articolo> doRetrieveBySex(String sex) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM articolo WHERE Sesso=?");
            ps.setString(1, sex);
            ResultSet rs = ps.executeQuery();
            ArrayList<Articolo> articoli = new ArrayList<>();
            if (rs.next()) {
                Articolo articolo = new Articolo();
                articolo.setIDarticolo(rs.getInt("ID_articolo"));
                articolo.setDescrizione(rs.getString("Descrizione"));
                articolo.setPrezzo(rs.getDouble("Prezzo"));
                articolo.setSesso(rs.getString("Sesso"));
                articolo.setSconto(rs.getDouble("sconto"));
                articoli.add(articolo);
            }
            ps.close();
            rs.close();
            return articoli;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Articolo> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM articolo");
            ResultSet rs = ps.executeQuery();
            ArrayList<Articolo> articoli = new ArrayList<>();
            while (rs.next()){
                Articolo articolo = new Articolo();
                articolo.setIDarticolo(rs.getInt("ID_articolo"));
                articolo.setDescrizione(rs.getString("Descrizione"));
                articolo.setPrezzo(rs.getDouble("Prezzo"));
                articolo.setSesso(rs.getString("Sesso"));
                articolo.setSconto(rs.getDouble("sconto"));
                articoli.add(articolo);
            }
            ps.close();
            rs.close();
            return articoli;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doSave(Articolo articolo) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO articolo (ID_articolo, Prezzo, " +
                            "Sesso, Descrizione, sconto) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, articolo.getIDarticolo());
            ps.setDouble(2, articolo.getPrezzo());
            ps.setString(3, articolo.getSesso());
            ps.setString(4, articolo.getDescrizione());
            ps.setDouble(5, articolo.getSconto());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            articolo.setIDarticolo(id);
            ps.close();
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM articolo WHERE ID_articolo=" + id +
                    Statement.RETURN_GENERATED_KEYS);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdateArticolo(Articolo articolo){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE articolo SET Descrizione='" + articolo.getDescrizione() +
                            "', Prezzo=" + articolo.getPrezzo()+ ", Sesso='" + articolo.getSesso()
                            + "',sconto=" + articolo.getSconto() + ", WHERE ID_articolo=" + articolo.getIDarticolo());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
