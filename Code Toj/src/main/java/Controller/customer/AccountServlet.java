package Controller.customer;

import Controller.http.Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AccountServlet", value = "/customers/*")
@MultipartConfig
public class AccountServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request); //abbiamo preso tutto il pezzo dopo "/customers/*"
        switch (path){
            case "/products":{

                request.getRequestDispatcher(view("customer/products")).forward(request, response);
                break;
            }

            case "/Men":
                request.getRequestDispatcher(view("customer/Men")).forward(request, response);
                break;

            case "/Women":
                request.getRequestDispatcher(view("customer/Men")).forward(request, response);
                break;

            case "/sigin":
                request.getRequestDispatcher(view("customer/login")).forward(request, response);
                break;

            case "/aboutUs":
                request.getRequestDispatcher(view("customer/aboutUs")).forward(request, response);
                break;

            case "/contactUs": // contact the site for help (pagina)
                request.getRequestDispatcher(view("customer/contactUs")).forward(request, response);
                break;

            case "/account": // show user's account (cliente)
                request.getRequestDispatcher(view("customer/myAccount")).forward(request, response);
                break;

            case "/privacy": // show privacy policy (pagina)
                request.getRequestDispatcher(view("customer/privacyPolicy")).forward(request, response);
                break;

            case "/termsCondition": // show terms and conditions (pagina)
                request.getRequestDispatcher(view("customer/terminiCondizioni")).forward(request, response);
                break;

            case "/cart": // show cart (pagina)
                request.getRequestDispatcher(view("customer/cart")).forward(request, response);
                break;

            case "/checkout":
                request.getRequestDispatcher(view("customer/checkout")).forward(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request);
        switch (path){
            case "/sigin":{
                String username = request.getParameter("usernameInput");
                String password = request.getParameter("passwordInput");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            }
            case "/sigUp":{
                String username = request.getParameter("usernameOutput");
                String email = request.getParameter("email");
                String password = request.getParameter("passwordOutput");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;
            }
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");
        }
    }
}
