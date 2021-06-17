package Controller.customer.api;

import Controller.http.Controller;
import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AjaxServlet", value = "/ajax/*")
public class AjaxServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request); //abbiamo preso tutto il pezzo dopo "/customers/*"
        switch (path) {
            case "/api-product": {
                String id = request.getParameter("coloreID");
                int idParse = Integer.parseInt(id);
                SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();

                try {
                    JSONObject root = new JSONObject();
                    JSONArray arr = new JSONArray();
                    Articolo articolo = sqlArticoloDAO.doRetrieveProductById(idParse);

                    if (articolo != null) {
                        List<Articolo> articoliColor = sqlArticoloDAO.doRetrieveProductByNome(articolo.getNome());
                        request.setAttribute("articolo", articolo);
                        request.setAttribute("articolos", articoliColor);
                        root.put("articolo", articolo.toJson());
                        root.put("colori", arr);
                        articoliColor.forEach(ar -> arr.add(ar.toJson()));
                    }
                    sendJson(response, root);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }

            case "/api": {
                SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                try {
                    String sex = request.getParameter("sex");
                    JSONObject root = new JSONObject();
                    if (sex.compareToIgnoreCase("M") == 0) {
                        List<Articolo> articoliMen = sqlArticoloDAO.doRetrieveNewProductsBySex("M");
                        JSONArray arr = new JSONArray();
                        root.put("products", arr);
                        articoliMen.forEach(am -> arr.add(am.toJson()));
                    } else {
                        List<Articolo> articoliWomen = sqlArticoloDAO.doRetrieveNewProductsBySex("F");
                        JSONArray arr = new JSONArray();
                        root.put("products", arr);
                        articoliWomen.forEach(am -> arr.add(am.toJson()));
                    }
                    sendJson(response, root);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }
        }
    }
}
