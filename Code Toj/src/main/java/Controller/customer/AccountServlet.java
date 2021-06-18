package Controller.customer;

import Controller.customer.Validator.AccountValidator;
import Controller.http.Controller;
import Controller.http.InvalidRequestException;
import Model.Account.Account;
import Model.Account.AccountSession;
import Model.Account.SQLAccountDAO;
import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;
import Model.search.Paginator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AccountServlet", value = "/customers/*")
@MultipartConfig
public class AccountServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = getPath(request); //abbiamo preso tutto il pezzo dopo "/customers/*"
        switch (path){
            case "/products":{
                String id = request.getParameter("id");
                String sex = request.getParameter("sex");
                int idParse = Integer.parseInt(id);

                SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                try {
                    List<Articolo> newArrivalBySex = sqlArticoloDAO.pagination(sex);
                    Articolo articolo = sqlArticoloDAO.doRetrieveProductById(idParse);
                    if (articolo != null) {
                        List<Articolo> filterColor = sqlArticoloDAO.doRetrieveProductByNome(articolo.getNome());
                        request.setAttribute("filterColor", filterColor);
                        request.setAttribute("articolo", articolo);
                        request.setAttribute("nuoviArrivi", newArrivalBySex);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.getRequestDispatcher(view("customer/products")).forward(request, response);
                break;
            }

            case "/productsList": {
                SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                int intPage = parsePage(request);
                String sex = request.getParameter("sex");

                if (sex.compareTo("M") == 0)
                    request.setAttribute("sex", "Uomo");
                else
                    request.setAttribute("sex", "Donna");

                Paginator paginator = new Paginator(intPage, 18);
                try {
                    int size = sqlArticoloDAO.countAll(sex);
                    request.setAttribute("pages", paginator.getPages(size));
                    List<Articolo> products = sqlArticoloDAO.pagination(sex, paginator);
                    if (products != null) {
                        request.setAttribute("productsList", products);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                request.getRequestDispatcher(view("customer/men_&_Women")).forward(request, response);
                break;
            }

            case "/sigin":
                request.getRequestDispatcher(view("customer/login")).forward(request, response);
                break;

            case "/logout":
                HttpSession session = request.getSession(false);
                try {
                    authenticate(session);
                } catch (InvalidRequestException e) {
                    e.printStackTrace();
                }
                AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
                String redirect = accountSession.isAdmin() ? "/Toj/crm/sigin" : "/Toj/customers/sigin";
                session.removeAttribute("accountSession");
                session.invalidate();
                response.sendRedirect(redirect);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String path = getPath(request);
        switch (path){
            case "/sigin":{
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                try {
                    validate(AccountValidator.validateSigin(request));
                    Account tmpAccount = new Account();
                    tmpAccount.setEmail(email);
                    tmpAccount.setPassword(password);
                    SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                    Optional<Account> accounts = null;
                    try {
                        accounts = sqlAccountDAO.findAccount(tmpAccount.getEmail(), tmpAccount.getPassword(), false);
                        if(accounts.isPresent()){
                            AccountSession accountSession = new AccountSession(accounts.get());
                            request.getSession(true).setAttribute("accountSession", accountSession);
                            response.sendRedirect("/Toj/dashboard/home");
                        } else {
                            throw new InvalidRequestException("Credenziali non valide", List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                        }
                    } catch (InvalidRequestException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException | InvalidRequestException throwables) {
                    throwables.printStackTrace();
                }
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
