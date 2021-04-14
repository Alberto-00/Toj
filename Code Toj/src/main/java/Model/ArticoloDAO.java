package Model;

import java.sql.*;
import java.util.ArrayList;

public class ArticoloDAO {

    public ArticoloDAO(){
        super();
    }

    public Articolo doRetrieveByIdArticolo(int id) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM articolo WHERE ID_articolo=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Articolo articolo = new Articolo();
                articolo.setIDarticolo(rs.getInt("ID_articolo"));
                articolo.setColore(rs.getString("Colore"));
                articolo.setDescrizione(rs.getString("Descrizione"));
                articolo.setPrezzo(rs.getDouble("Prezzo"));
                articolo.setQuantita(rs.getInt("Quantita"));
                articolo.setSesso(rs.getString("Sesso"));
                articolo.setTaglia(rs.getString("Taglia"));
                articolo.setTipo(rs.getString("Tipo"));
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

    public ArrayList<Articolo> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM articolo");
            ResultSet rs = ps.executeQuery();
            ArrayList<Articolo> articoli = new ArrayList<>();
            while (rs.next()){
                Articolo articolo = new Articolo();
                articolo.setIDarticolo(rs.getInt("ID_articolo"));
                articolo.setColore(rs.getString("Colore"));
                articolo.setDescrizione(rs.getString("Descrizione"));
                articolo.setPrezzo(rs.getDouble("Prezzo"));
                articolo.setQuantita(rs.getInt("Quantita"));
                articolo.setSesso(rs.getString("Sesso"));
                articolo.setTaglia(rs.getString("Taglia"));
                articolo.setTipo(rs.getString("Tipo"));
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
                    "INSERT INTO articolo (ID_articolo, Colore, Prezzo, Quantita, " +
                            "Tipo, Taglia, Sesso, Descrizione) VALUES(?,?,?,?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, articolo.getIDarticolo());
            ps.setString(2, articolo.getColore());
            ps.setDouble(3, articolo.getPrezzo());
            ps.setInt(4, articolo.getQuantita());
            ps.setString(5, articolo.getTipo());
            ps.setString(6, articolo.getTaglia());
            ps.setString(7, articolo.getSesso());
            ps.setString(8, articolo.getDescrizione());
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
                    "UPDATE articolo SET Tipo='" + articolo.getTipo() + "', Descrizione='" +
                            articolo.getDescrizione() + "', Colore='" +articolo.getColore()
                            + "', Prezzo=" + articolo.getPrezzo()+ ", Quantita=" + articolo.getQuantita()
                            +", Taglia='" + articolo.getTaglia()+ "', Sesso='" + articolo.getSesso()
                            + "', WHERE ID_articolo=" + articolo.getIDarticolo());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
