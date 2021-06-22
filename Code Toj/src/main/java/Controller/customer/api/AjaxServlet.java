package Controller.customer.api;

import Controller.http.Controller;
import Controller.http.InvalidRequestException;
import Model.Articolo.Articolo;
import Model.Articolo.ArticoloSearch;
import Model.Articolo.SQLArticoloDAO;
import Model.search.Condition;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AjaxServlet", value = "/ajax/*")
public class AjaxServlet extends Controller {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request); //abbiamo preso tutto il pezzo dopo "/customers/*"
        try {
            switch (path) {
                case "/api-product": {
                    String id = request.getParameter("id");
                    int idParse = Integer.parseInt(id);
                    SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();

                    JSONObject root = new JSONObject();
                    Articolo articolo = sqlArticoloDAO.doRetrieveProductById(idParse);
                    if (articolo != null)
                        root.put("articolo", articolo.toJson());

                    sendJson(response, root);
                    break;
                }

                case "/api": {
                    SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                    String sex = request.getParameter("sex");
                    JSONObject root = new JSONObject();

                    if (sex.compareToIgnoreCase("M") == 0) {
                        List<Articolo> articoliMen = sqlArticoloDAO.doRetrieveNewArrivals("M");
                        JSONArray arr = new JSONArray();
                        root.put("products", arr);
                        articoliMen.forEach(am -> arr.add(am.toJson()));
                    } else {
                        List<Articolo> articoliWomen = sqlArticoloDAO.doRetrieveNewArrivals("F");
                        JSONArray arr = new JSONArray();
                        root.put("products", arr);
                        articoliWomen.forEach(am -> arr.add(am.toJson()));
                    }
                    sendJson(response, root);
                    break;
                }

                case "/api-searchBar": {
                    SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                    List<String> nomi = sqlArticoloDAO.doRetrieveProductByNome();
                    JSONObject root = new JSONObject();
                    JSONArray arr = new JSONArray();
                    root.put("listName", arr);

                    for (int i = 1; i < nomi.size(); i++) {
                        if(nomi.get(i) != null){
                            JSONObject obj = new JSONObject();
                            obj.put("name", nomi.get(i));
                            arr.add(obj);
                        }
                    }
                    sendJson(response, root);
                    break;
                }

                case "/api-verifyFilter":
                    List<Condition> conditions = new ArticoloSearch().buildSearch(request);
                    System.out.println(conditions.size());
                    if (conditions.size() <= 0){
                        JSONObject root = new JSONObject();
                        root.put("ms", "Articoli non trovati. Riprova.");
                        sendJson(response, root);
                        break;
                    } else
                        response.sendRedirect(view("customer/men_&_Women"));
                    break;

                default:
                    notFound();
            }
        } catch(SQLException ex){
            log(ex.getMessage());
        } catch (InvalidRequestException e) {
            e.printStackTrace();
        }
    }
}


