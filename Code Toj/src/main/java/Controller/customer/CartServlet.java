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

@WebServlet(name = "CartServlet", value = "/carts/*")
public class CartServlet extends Controller {

    private static final SQLArticoloDAO productDao = new SQLArticoloDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path) {
                case "/add":
                    HttpSession session = request.getSession();
                    int id = Integer.parseInt(request.getParameter("id"));
                    String taglia = request.getParameter("size");
                    Articolo optArticolo = productDao.doRetrieveProductById_Size(taglia, id);

                    if (optArticolo != null){
                        synchronized (session){
                            Cart sessionCartNotLog = (Cart) session.getAttribute("productNotLog");
                            if(sessionCartNotLog == null)
                                sessionCartNotLog = new Cart();

                            int quantity = Integer.parseInt(request.getParameter("quantity"));
                            if (quantity <= optArticolo.getQuantity())
                                sessionCartNotLog.addProduct(optArticolo, quantity);

                            session.setAttribute("cartNorLog", sessionCartNotLog);
                        }
                        response.sendRedirect("../customers/products?id=" + optArticolo.getIDarticolo() + "&sex=" +
                                optArticolo.getSesso());
                    } else {
                        notFound();
                    }
                    break;

                case "/remove":
                    int removeId = Integer.parseInt(request.getParameter("id"));
                    if(getSessionCart(request.getSession(false)).removeProduct(removeId)){
                        response.sendRedirect("./customer/cart");
                    } else {
                        notFound();
                    } break;

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
