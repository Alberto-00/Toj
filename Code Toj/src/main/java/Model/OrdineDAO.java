package Model;

import java.sql.*;
import java.util.List;

public class OrdineDAO {

    public OrdineDAO(){
        super();
    }

    public List<Ordine> doRetrieveAll(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM ordine");
            ResultSet rs = ps.executeQuery();
            List<Ordine> ordini = null;
            if (rs.next()) {
                Ordine ordine = new Ordine();
                ordine.setID_ordine(rs.getString("ID_ordine"));
                ordine.setData_acquisto(rs.getDate("data_acquisto"));
                ordine.setPacchetto_regalo(rs.getBoolean("pacchetto_regalo"));
                ordine.setData_spedizione(rs.getDate("data_spedizione"));
                ordine.setDescrizione(rs.getString("Descrizione"));
                ordini.add(ordine);
            }
            ps.close();
            rs.close();
            return ordini;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ordine doRetrieveAllByID(String id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM ordine WHERE ordine.ID_ordine='" + id + "'");
            ResultSet rs = ps.executeQuery();
            Ordine ordine = new Ordine();
            if (rs.next()) {
                ordine.setID_ordine(rs.getString("ID_ordine"));
                ordine.setData_acquisto(rs.getDate("data_acquisto"));
                ordine.setPacchetto_regalo(rs.getBoolean("pacchetto_regalo"));
                ordine.setData_spedizione(rs.getDate("data_spedizione"));
                ordine.setDescrizione(rs.getString("Descrizione"));
            }
            ps.close();
            rs.close();
            return ordine;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Ordine ordine) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO ordine (ID_ordine, data_acquisto, pacchetto_regalo, data_spedizione, " +
                            "Descrizione) VALUES(?,?,?,?,?)");
            ps.setString(1, ordine.getID_ordine());
            ps.setDate(2, (Date) ordine.getData_acquisto());
            ps.setBoolean(3, ordine.isPacchetto_regalo());
            ps.setDate(4, (Date) ordine.getData_spedizione());
            ps.setString(5, ordine.getDescrizione());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM ordine WHERE ordine.ID_ordine'" + id + "'");
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
