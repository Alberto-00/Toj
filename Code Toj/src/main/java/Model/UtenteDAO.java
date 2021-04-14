package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class UtenteDAO {

    public Utente doRetrieveByMailAccount(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM account_user WHERE email=?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente d = new Utente();
                d.setEmail(rs.getString(1));
                d.setPassword(rs.getString(2));
                return d;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private GregorianCalendar toCalendar(java.sql.Date date){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTimeInMillis(date.getTime());
        return cal;
    }

    public UtenteDAO doRetrieveByMailDatiAnagrafici(String email) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM account_user A, dati_cliente D WHERE A.Email=D.Email");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Utente d = new Utente();
                d.setEmail(rs.getString("Email"));
                d.setPassword(rs.getString("Password_User"));
                d.setNome(rs.getString("Nome"));
                d.setCognome(rs.getString("Cognome"));

                //Translazione da SQLDate A GregorianCalendar
                java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
                d.setDataDiNascita(toCalendar(date));

                d.setNumeroTelefonico(rs.getString("Telefono"));
                d.setVia(rs.getString("Via"));
                d.setNumeroCivico(rs.getString("N_civico"));
                d.setCAP(rs.getString("CAP"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Categoria> doRetrieveAll(){
        try (Connection con = ConPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM categoria");
            ResultSet rs = ps.executeQuery();
            ArrayList<Categoria> categorie = new ArrayList<>();
            while (rs.next()){
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("id"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescrizione(rs.getString("descrizione"));
                categorie.add(categoria);
            }
            return categorie;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    // la funzione seguente è inutile perchè il DB è riempito tramite tool esterno
    // sarebbe utile se l'applicazione fornisse un form per riempirlo. IDEA! aggiungi questa feature all'applicazione
    // è un buon modo per verificare la sua correttezza
    public void doSave(Categoria categoria) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO categoria (id, nome, descrizione) VALUES(?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, categoria.getId());
            ps.setString(2, categoria.getNome());
            ps.setString(3, categoria.getDescrizione());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            categoria.setId(id);
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
