package Controller.admin;

import Controller.http.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminServlet", value = "/adminServlet/*")
public class AdminServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);
        switch (path){
            case "/adminGestioneClienti":
                request.getRequestDispatcher(view("admin/adminGestioneClienti")).forward(request, response);
                break;
            case "/adminGestioneOrdini":
                request.getRequestDispatcher(view("admin/adminGestioneOrdini")).forward(request, response);
                break;
            case "/adminGestioneArticoli":
                request.getRequestDispatcher(view("admin/adminGestioneArticoli")).forward(request, response);
                break;
            case "/adminHomepage":
                request.getRequestDispatcher(view("admin/adminHomepage")).forward(request, response);
                break;
            case "/adminProfilo":
                request.getRequestDispatcher(view("admin/adminProfilo")).forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);
    }
}
