package Model;

import java.sql.*;
import java.util.ArrayList;

public class CartaElettronicaDAO {

    public CartaElettronicaDAO(){
        super();
    }

    public CartaElettronica doRetrieveByUserPay(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT carta_elettronica.* FROM carta_elettronica, account_user " +
                            "WHERE carta_elettronica.Email = account_user.Email");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CartaElettronica payCard = new CartaElettronica();
                payCard.setIDcarta(rs.getString("Codice_carta"));
                payCard.setDescrizione(rs.getString("Descrizione"));
                payCard.setEmailUser(rs.getString("Email"));
                ps.close();
                rs.close();
                return payCard;
            }
            ps.close();
            rs.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<CartaElettronica> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM carta_elettronica");
            ResultSet rs = ps.executeQuery();
            ArrayList<CartaElettronica> payCards = new ArrayList<>();
            while (rs.next()){
                CartaElettronica payCard = new CartaElettronica();
                payCard.setIDcarta(rs.getString("Codice_carta"));
                payCard.setDescrizione(rs.getString("Descrizione"));
                payCard.setEmailUser(rs.getString("Email"));
                payCards.add(payCard);
            }
            ps.close();
            rs.close();
            return payCards;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void doSave(CartaElettronica payCard) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO carta_elettronica (Codice_carta, Descrizione, Email) " +
                            "VALUES(?,?,?)");
            ps.setString(1, payCard.getIDcarta());
            ps.setString(2, payCard.getDescrizione());
            ps.setString(3, payCard.getEmail());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String payCod) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM carta_elettronica WHERE Codice_carta='" + payCod + "'");
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doUpdatePayCard(CartaElettronica payCard){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE carta_elettronica SET Descrizione='" + payCard.getDescrizione() + "', Descrizione='" +
                            payCard.getDescrizione() + "', Email='" + payCard.getEmail()
                            + "', WHERE Codice_carta=" + payCard.getIDcarta());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
