package Controller.customer;

import Controller.customer.Validator.AccountValidator;
import Controller.http.Controller;
import Controller.http.InvalidRequestException;
import Model.Account.Account;
import Model.Account.AccountSession;
import Model.Account.SQLAccountDAO;
import Model.Articolo.Articolo;
import Model.Articolo.ArticoloSearch;
import Model.Articolo.SQLArticoloDAO;
import Model.Categoria.SQLCategoriaDAO;
import Model.Colore.SQLColoreDAO;
import Model.Taglia.SQLTagliaDAO;
import Model.search.Condition;
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
        try{
            String path = getPath(request); //abbiamo preso tutto il pezzo dopo "/customers/*"
            switch (path) {
                case "/products": {
                    String id = request.getParameter("id");
                    String sex = request.getParameter("sex");
                    int idParse = Integer.parseInt(id);

                    SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                    List<Articolo> newArrivalBySex = sqlArticoloDAO.doRetrieveNewArrivals(sex);
                    Articolo articolo = sqlArticoloDAO.doRetrieveProductById(idParse);

                    if (articolo != null) {
                        List<Articolo> filterColor = sqlArticoloDAO.doRetrieveProductByNome(articolo.getNome());
                        request.setAttribute("filterColor", filterColor);
                        request.setAttribute("articolo", articolo);
                        request.setAttribute("nuoviArrivi", newArrivalBySex);
                    }
                    request.getRequestDispatcher(view("customer/products")).forward(request, response);
                    break;
                }

                case "/shop": {
                    List<Condition> conditions = new ArticoloSearch().buildSearch(request);
                    SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                    List<Articolo> searchProducts = sqlArticoloDAO.search(conditions);
                    SQLCategoriaDAO sqlCategoriaDAO = new SQLCategoriaDAO();
                    SQLTagliaDAO tagliaDAO = new SQLTagliaDAO();
                    SQLColoreDAO sqlColoreDAO = new SQLColoreDAO();

                    int size = searchProducts.size();
                    int intPage = parsePage(request);
                    String sex = request.getParameter("Sesso");
                    request.setAttribute("numPage", intPage);
                    request.setAttribute("minPrice", sqlArticoloDAO.minPrice(sex));
                    request.setAttribute("maxPrice", sqlArticoloDAO.maxPrice(sex));
                    request.setAttribute("conditions", conditions);

                    if (sex.compareTo("M") == 0)
                        request.setAttribute("sex", "Uomo");
                    else
                        request.setAttribute("sex", "Donna");

                    Paginator paginator = new Paginator(intPage, 18, conditions);
                    request.setAttribute("pages", paginator.getPages(size));
                    int count = paginator.getCount();
                    List<Articolo> products = sqlArticoloDAO.searchPagination(conditions, paginator);

                    request.setAttribute("categorie", sqlCategoriaDAO.doRetrieveBySex(sex));
                    request.setAttribute("taglie", tagliaDAO.doRetrieveBySex(sex));
                    request.setAttribute("colori", sqlColoreDAO.doRetrieveBySex(sex));

                    request.setAttribute("productsList", products);
                    request.setAttribute("count", count);
                    request.getRequestDispatcher(view("customer/men_&_Women")).forward(request, response);
                }
                break;

                case "/sigin":
                    request.getRequestDispatcher(view("customer/login")).forward(request, response);
                    break;

                case "/logout":
                    HttpSession session = request.getSession(false);
                    authenticate(session);
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
                    notFound();
            }
        } catch(SQLException ex){
            log(ex.getMessage());
        } catch(InvalidRequestException e){
            log(e.getMessage());
            e.handle(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            String path = getPath(request);
            switch (path){
                case "/sigin":{
                    validate(AccountValidator.validateSigin(request));
                    Account tmpAccount = new Account();
                    tmpAccount.setEmail(request.getParameter("email"));
                    tmpAccount.setPassword(request.getParameter("password"));
                    SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                    Optional<Account> accounts = sqlAccountDAO.findAccount(tmpAccount.getEmail(), tmpAccount.getPassword(), false);
                    if(accounts.isPresent()){
                        AccountSession accountSession = new AccountSession(accounts.get());
                        request.getSession(true).setAttribute("accountSession", accountSession);
                        response.sendRedirect("/Toj/dashboard/home");
                    } else {
                        throw new InvalidRequestException("Credenziali non valide", List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
                    }
                    break;
                }

                case "/registration":
                    authorize(request.getSession(false));
                    request.setAttribute("back", view("customer/myAccount"));
                    break;

                default:
                    notFound();
            }
        } catch(SQLException ex){
            log(ex.getMessage());
        } catch(InvalidRequestException e){
            log(e.getMessage());
            e.handle(request, response);
        }
    }
}
