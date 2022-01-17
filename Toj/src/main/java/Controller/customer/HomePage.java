package Controller.customer;

import Controller.http.Controller;
import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomePage", value = "/HomePage")
public class HomePage extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
            List<Articolo> articoli = sqlArticoloDAO.doRetrieveNewArrivals("F");
            request.setAttribute("articoli", articoli);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
