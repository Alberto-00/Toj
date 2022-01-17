package Model.storage;

import java.sql.ResultSet;
import java.sql.SQLException;

/* Estraiamo il Resulset e lo inseriamo in un Bean */

public interface ResultSetExtractor<B> {

    //B è l'entità che si vuole mappare
    B extract(ResultSet resultSet) throws SQLException;
}
