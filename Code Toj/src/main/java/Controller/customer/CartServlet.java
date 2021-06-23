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

@WebServlet(name = "CartServlet", value = "/cart/*")
public class CartServlet extends Controller {

    private static final SQLArticoloDAO productDao = new SQLArticoloDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*try {
            String path = getPath(request);
            switch (path) {
                case "/add":
                    request.setAttribute("back", view("customer/products"));
                    validate(CartValidator.validateProduct(request));
                    int id = Integer.parseInt(request.getParameter("id"));
                    Articolo optArticolo = productDao.doRetrieveProductById(id);
                    if (optArticolo != null){
                        int quantity = Integer.parseInt(request.getParameter("quantity"));
                        if (request.getSession(false).getAttribute("accountCart") == null)
                            request.getSession(false).setAttribute("accountCart", new Cart(new ArrayList<>()));

                        getSessionCart(request.getSession(false)).addProduct(optArticolo, quantity);
                        response.sendRedirect("/Toj/customer/products?id=" + optArticolo.getIDarticolo());
                    } else {
                        notFound();
                    }
                    break;

                case "/remove":
                    validate(CommonValidator.validateId(request));
                    int removeId = Integer.parseInt(request.getParameter("id"));
                    if(getSessionCart(request.getSession(false)).removeProduct(removeId)){
                        response.sendRedirect("/Toj/customer/cart");
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
        }*/
    }
}
