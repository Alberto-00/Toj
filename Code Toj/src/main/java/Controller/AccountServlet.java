package Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AccountServlet", value = "/AccountServlet")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/"; //abbiamo preso tutto il pezzo dopo "/account"
        switch (path){
            case "signin": //login cliente (pagina)
                break;
            case "/":
                break;
            case "/create":
                break;
            case "/show":
                break;
            case "/secret":
                break;
            case "/signup":
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = (request.getPathInfo() != null) ? request.getPathInfo() : "/"; //abbiamo preso tutto il pezzo dopo "/account"
        switch (path){
            case "/secret": //login admin (ricerca nel db)
                break;
            case "/create":
                break;
            case "/update":
                break;
            case "/logout":
                break;
            case "/signin": //login cliente (ricerca nel db)
                break;
            case "/signup": //login cliente (ricerca nel db)
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Operazione non consentita");
        }
    }
}

