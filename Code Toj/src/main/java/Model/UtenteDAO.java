package Model;

import java.sql.*;
import java.util.ArrayList;

public class UtenteDAO {

    public UtenteDAO(){
        super();
    }

    public Utente doRetrieveByEmailUser(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM account_user A, dati_cliente D WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente user = new Utente();
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password_User"));
                user.setNome(rs.getString("Nome"));
                user.setCognome(rs.getString("Cognome"));
               // user.setDataDiNascita(rs.getDate("ddn"));
                user.setNumeroTelefonico(rs.getString("Telefono"));
                user.setVia(rs.getString("Via"));
                user.setNumeroCivico(rs.getString("N_civico"));
                user.setCAP(rs.getString("CAP"));
                user.setAdmin(rs.getBoolean("Admin_user"));
                ps.close();
                rs.close();
                return user;
            }
            ps.close();
            rs.close();
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Utente> doRetrieveAllByMailDatiAnagrafici() {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM account_user A, dati_cliente D WHERE A.Email=D.Email");
            ResultSet rs = ps.executeQuery();
            ArrayList<Utente> users = new ArrayList<>();
            if (rs.next()) {
                Utente user = new Utente();
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password_User"));
                user.setNome(rs.getString("Nome"));
                user.setCognome(rs.getString("Cognome"));
                //user.setDataDiNascita(rs.getDate("ddn"));
                user.setNumeroTelefonico(rs.getString("Telefono"));
                user.setVia(rs.getString("Via"));
                user.setNumeroCivico(rs.getString("N_civico"));
                user.setCAP(rs.getString("CAP"));
                user.setAdmin(rs.getBoolean("Admin_user"));
                users.add(user);
            }
            ps.close();
            rs.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Utente user) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO account_user (Email, Password_User, Admin_user) VALUES(?,?,?,?)");
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isAdmin());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ps.close();
            PreparedStatement ps1 = con.prepareStatement(
                    "INSERT INTO dati_cliente (Nome, Cognome, ddn, Telefono, Via, N_civico, CAP) " +
                            "VALUES(?,?,?,?,?,?,?)");
            ps1.setString(1, user.getNome());
            ps1.setString(2, user.getCognome());
            //ps1.setDate(3, (Date) user.getDataDiNascita());
            ps1.setString(4, user.getNumeroTelefonico());
            ps1.setString(5, user.getVia());
            ps1.setString(6, user.getNumeroCivico());
            ps1.setString(7, user.getCAP());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ps1.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doDelete(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM account_user ac, dati_cliente dc " +
                            "WHERE ac.Email = dc.Email AND Email='" + email + "'");
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void doUpdateUser(Utente user){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE account_user SET Password_User='" + user.getPassword() +
                            "' WHERE Email=" + user.getEmail());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
