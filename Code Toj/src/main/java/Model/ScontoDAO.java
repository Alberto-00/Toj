package Model;

import java.sql.*;
import java.util.List;

public class ScontoDAO {

    public ScontoDAO(){
        super();
    }

   /* public Sconto doRetrieveAllByID(String codice) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM cod_sconto, ordine " +
                            "WHERE cod_sconto.ID_ordine= ordine.ID_ordine");
            ResultSet rs = ps.executeQuery();
            Sconto sconto = new Sconto();
            if (rs.next()) {
                sconto.setCodice(rs.getString("codice"));
                sconto.setDataScadenza(rs.getDate("data_scadenza"));
                sconto.setSconto(rs.getDouble("sconto"));
                sconto.setOrdineID(rs.getString("ID_ordine"));
            }
            ps.close();
            rs.close();
            return sconto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Sconto sconto) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO cod_sconto (codice, data_scadenza, sconto, ID_ordine) " +
                            "VALUES(?,?,?,?)");
            ps.setString(1, sconto.getCodice());
            ps.setDate(2, (Date) sconto.getDataScadenza());
            ps.setDouble(3, sconto.getSconto());
            ps.setString(4, sconto.getOrdineID());
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
                    "DELETE FROM cod_sconto WHERE cod_sconto.codice'" + id + "'");
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}
