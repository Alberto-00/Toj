package Model.Utente;

import Model.storage.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteExtractor implements ResultSetExtractor<Utente> {

    @Override
    public Utente extract(ResultSet rs) throws SQLException {
        Utente user = new Utente();
        user.setEmail(rs.getString("Email"));
        user.setPassword(rs.getString("Password_User"));
        user.setAdmin(rs.getBoolean("Admin_user"));
        return user;
    }
}
