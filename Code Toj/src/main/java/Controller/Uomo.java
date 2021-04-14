package Controller;

import Model.Articolo;
import Model.ArticoloDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "UomoServlet", value = "/UomoServlet")
public class Uomo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArticoloDAO service = new ArticoloDAO();
        ArrayList<Articolo> articoli = service.doRetrieveBySex("M");
        request.setAttribute("articoliUomo", articoli);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/results/uomo.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
