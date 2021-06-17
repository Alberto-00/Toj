package Controller.customer;

import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;
import Controller.http.Controller;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "InitServlet", value = "/InitServlet", loadOnStartup = 0)
public class InitServlet extends Controller {

    @Override
    public void init() throws ServletException{
        try {
            SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
            List<Articolo> articoli = sqlArticoloDAO.doRetrieveNewProductsBySex("F");
            getServletContext().setAttribute("articoli", articoli);
            super.init();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
