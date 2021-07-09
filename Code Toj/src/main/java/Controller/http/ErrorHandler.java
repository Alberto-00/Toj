package Controller.http;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface ErrorHandler {

    default void authenticate (HttpSession session) throws InvalidRequestException {
        if (session == null || session.getAttribute("userSession") == null){
            throw new InvalidRequestException("Errore autenticazione", List.of("Non sei autenticato"),
                    HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    default void notFound() throws InvalidRequestException{
        throw new InvalidRequestException("Errore interno", List.of("Risorsa non trovata"),
                HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
