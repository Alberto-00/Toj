package Controller.customer;

import Controller.http.Controller;
import Controller.http.InvalidRequestException;
import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;
import Model.Cart.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CartServlet", value = "/carts/*")
public class CartServlet extends Controller {

    private static final SQLArticoloDAO productDao = new SQLArticoloDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String path = getPath(request);
            switch (path) {
                case "/add" -> {
                    int id = Integer.parseInt(request.getParameter("id"));
                    String taglia = request.getParameter("size");
                    Articolo optArticolo = productDao.doRetrieveProductById_Size(taglia, id);

                    if (optArticolo != null) {
                        synchronized (session) {
                            Cart sessionCartNotLog = (Cart) session.getAttribute("cartNotLog");
                            if (sessionCartNotLog == null)
                                sessionCartNotLog = new Cart();

                            int quantity = Integer.parseInt(request.getParameter("quantity"));
                            if (quantity <= optArticolo.getTaglie().get(0).getQuantita()){
                                Articolo tmpArt = sessionCartNotLog.find(id, taglia);
                                if (tmpArt != null){
                                    if (quantity + tmpArt.getLocalQuantity() > tmpArt.getTaglie().get(0).getQuantita()) {
                                        session.setAttribute("msgQuantity", "Quantità non disponibile.");
                                        response.sendRedirect("../customers/products?id=" + optArticolo.getIDarticolo() + "&sex=" +
                                                optArticolo.getSesso());
                                        break;
                                    }
                                }
                                sessionCartNotLog.addProduct(optArticolo, quantity, taglia);
                            }
                            session.setAttribute("cartNotLog", sessionCartNotLog);
                        }
                        response.sendRedirect("../customers/products?id=" + optArticolo.getIDarticolo() + "&sex=" +
                                optArticolo.getSesso());
                    } else {
                        notFound();
                    }
                }

                case "/remove" -> {
                    int removeId = Integer.parseInt(request.getParameter("id"));
                    String size = request.getParameter("size");
                    synchronized (session) {
                        Cart sessionCartNotLog = (Cart) session.getAttribute("cartNotLog");
                        if (sessionCartNotLog != null) {
                            sessionCartNotLog.removeProduct(removeId, size);
                            if (sessionCartNotLog.getItems().size() ==  0)
                                session.removeAttribute("cartNotLog");
                            else
                                session.setAttribute("cartNotLog", sessionCartNotLog);
                        }
                    }
                    response.sendRedirect("../customers/cart");
                }

                default -> notFound();
            }
        }catch(SQLException ex){
            log(ex.getMessage());
        } catch (InvalidRequestException ex){
            log(ex.getMessage());
            ex.handle(request, response);
        }
    }
}
