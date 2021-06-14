package Controller.customer;

import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "InitServlet", value = "/InitServlet", loadOnStartup = 0)
public class InitServlet extends HttpServlet {

    @Override
    public void init() throws ServletException{
        List<Articolo> articoli;
        SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
        try {
            articoli = sqlArticoloDAO.doRetrieveAllNewProducts();
            getServletContext().setAttribute("nuoviArrivi",articoli);
            System.out.println("AAAAAAAAAAA");
            super.init();
        } catch (SQLException throwables) {
            System.out.println("cjcjc");
            throwables.printStackTrace();
        }
    }
}
