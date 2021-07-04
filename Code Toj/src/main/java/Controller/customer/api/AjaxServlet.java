package Controller.customer.api;

import Controller.http.Controller;
import Controller.http.InvalidRequestException;
import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;
import Model.Cart.Cart;
import Model.Sconto.SQLScontoDAO;
import Model.Sconto.Sconto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AjaxServlet", value = "/ajax/*")
public class AjaxServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);
        HttpSession session = request.getSession(false);
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

                case "/api-coupon": {
                    Cart cart = (Cart) session.getAttribute("cartNotLog");
                    if (cart == null || cart.getItems().size() <= 0) {
                        JSONObject root = new JSONObject();
                        root.put("sconto", "Carrello vuoto.");
                        sendJson(response, root);
                        break;
                    }
                    SQLScontoDAO sqlScontoDAO = new SQLScontoDAO();
                    Sconto sconto = new Sconto();
                    sconto.setCodice(request.getParameter("coupon"));
                    JSONObject root = new JSONObject();
                    if (sqlScontoDAO.findSconto(request.getParameter("coupon")))
                        root.put("sconto", "Coupon già utilizzato.");
                    else {
                        if (sqlScontoDAO.doRetrieveByName(sconto.getCodice()) != null) {
                            session.setAttribute("coupon", sqlScontoDAO.doRetrieveByName(sconto.getCodice()));
                            root.put("sconto", sqlScontoDAO.doRetrieveByName(sconto.getCodice()).toJson());
                        } else {
                            root.put("sconto", "");
                        }
                    }
                    sendJson(response, root);
                    break;
                }

                case "/api-checkout": {
                    Cart cart = (Cart) session.getAttribute("cartNotLog");
                    JSONObject root = new JSONObject();
                    if (cart == null || cart.getItems().size() <= 0)
                        root.put("msg", "Carrello vuoto.");
                    else
                        root.put("msg", "");
                    sendJson(response, root);
                    break;
                }

                case "/api-updateCart": {
                    Cart cart = (Cart) session.getAttribute("cartNotLog");
                    String size = request.getParameter("size");
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    int id = Integer.parseInt(request.getParameter("id"));
                    Articolo articolo = cart.find(id, size);
                    JSONObject root = new JSONObject();
                    if (articolo != null) {
                        articolo.lessLocalQuantity(quantity);
                        root.put("msg", "true");
                    } else {
                        root.put("msg", "false");
                    }
                    sendJson(response, root);
                    break;
                }

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


