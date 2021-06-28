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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "CartServlet", value = "/carts/*")
public class CartServlet extends Controller {

    private static final SQLArticoloDAO productDao = new SQLArticoloDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/add": {
                    HttpSession session = request.getSession();
                    int id = Integer.parseInt(request.getParameter("id"));
                    String taglia = request.getParameter("size");
                    Articolo optArticolo = productDao.doRetrieveProductById_Size(taglia, id);

                    if (optArticolo != null) {
                        synchronized (session) {
                            Cart sessionCartNotLog = (Cart) session.getAttribute("cartNotLog");
                            if (sessionCartNotLog == null)
                                sessionCartNotLog = new Cart();

                            int quantity = Integer.parseInt(request.getParameter("quantity"));
                            if (quantity <= optArticolo.getQuantity())
                                sessionCartNotLog.addProduct(optArticolo, quantity, taglia);

                            session.setAttribute("cartNotLog", sessionCartNotLog);
                        }
                        response.sendRedirect("../customers/products?id=" + optArticolo.getIDarticolo() + "&sex=" +
                                optArticolo.getSesso());
                    } else {
                        notFound();
                    }
                    break;
                }

               case "/remove":
                   HttpSession session = request.getSession();
                   int removeId = Integer.parseInt(request.getParameter("id"));
                   String size = request.getParameter("size");
                   synchronized (session) {
                       Cart sessionCartNotLog = (Cart) session.getAttribute("cartNotLog");
                       if (sessionCartNotLog != null) {
                           sessionCartNotLog.removeProduct(removeId, size);
                           session.setAttribute("cartNotLog", sessionCartNotLog);
                       }
                   }
                   response.sendRedirect("../customers/cart");
                   break;

                default:
                    notFound();
            }
        }catch(SQLException ex){
            log(ex.getMessage());
        } catch (InvalidRequestException ex){
            log(ex.getMessage());
            ex.handle(request, response);
        }
    }
}
