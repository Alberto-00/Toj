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
import Model.Cart.Cart;
import Model.Categoria.SQLCategoriaDAO;
import Model.Colore.SQLColoreDAO;
import Model.Dati_utente.DatiUtente;
import Model.Dati_utente.SQLDatiUtenteDAO;
import Model.Ordine.Ordine;
import Model.Ordine.SQLOrdineDAO;
import Model.Sconto.Sconto;
import Model.Taglia.SQLTagliaDAO;
import Model.search.Condition;
import Model.search.Paginator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@WebServlet(name = "AccountServlet", value = "/customers/*")
public class AccountServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String path = getPath(request);
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
                    SQLCategoriaDAO sqlCategoriaDAO = new SQLCategoriaDAO();
                    SQLTagliaDAO tagliaDAO = new SQLTagliaDAO();
                    SQLColoreDAO sqlColoreDAO = new SQLColoreDAO();

                    request.setAttribute("minPrice", sqlArticoloDAO.minPrice());
                    request.setAttribute("maxPrice", sqlArticoloDAO.maxPrice());
                    String sex = request.getParameter("Sesso");

                    boolean latest = false;
                    String latestReq = request.getParameter("latest");
                    if (latestReq != null && latestReq.compareTo("true") == 0){
                        latest = true;
                        request.setAttribute("latest", latestReq);
                    }
                    List<Articolo> searchProducts = sqlArticoloDAO.search(conditions, latest);

                    if (sex != null && sex.compareTo("") != 0){
                        if (sex.compareTo("M") == 0) {
                            request.setAttribute("sesso", "M");
                            request.setAttribute("sex", "Uomo");
                        }
                        else{
                            request.setAttribute("sesso", "F");
                            request.setAttribute("sex", "Donna");
                        }
                        request.setAttribute("categorie", sqlCategoriaDAO.doRetrieveBySex(sex));
                        request.setAttribute("taglie", tagliaDAO.doRetrieveBySex(sex));
                        request.setAttribute("colori", sqlColoreDAO.doRetrieveBySex(sex));
                    } else {
                        request.setAttribute("sex", "Abbigliamento T&#x000F8;j");
                        request.setAttribute("categorie", sqlCategoriaDAO.doRetrieveAll());
                        request.setAttribute("taglie", tagliaDAO.doRetrieveAll());
                        request.setAttribute("colori", sqlColoreDAO.doRetrieveAll());
                    }

                    if (searchProducts.size() == 0){
                        request.setAttribute("flag", false);
                        request.setAttribute("count", 0);
                        request.getRequestDispatcher(view("customer/men_&_Women")).forward(request, response);
                        break;
                    }
                    request.setAttribute("flag", true);

                    int size = searchProducts.size();
                    int intPage = parsePage(request);

                    request.setAttribute("numPage", intPage);
                    request.setAttribute("conditions", conditions);

                    Paginator paginator = new Paginator(intPage, 18, conditions, latest);
                    request.setAttribute("pages", paginator.getPages(size));
                    int count = paginator.getCount();
                    List<Articolo> products = sqlArticoloDAO.searchPagination(conditions, paginator, latest);
                    request.setAttribute("productsList", products);
                    request.setAttribute("count", count);
                    request.getRequestDispatcher(view("customer/men_&_Women")).forward(request, response);
                    break;
                }

                case "/sigin":
                    request.getRequestDispatcher(view("customer/login")).forward(request, response);
                    break;

                case "/logout":
                    HttpSession session = request.getSession(false);
                    authenticate(session);
                    session.removeAttribute("userSession");
                    session.removeAttribute("userInfSession");
                    session.invalidate();
                    response.sendRedirect("../index.jsp");
                    break;

                case "/aboutUs": {
                    SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                    SQLOrdineDAO sqlOrdineDAO = new SQLOrdineDAO();
                    SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();

                    SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String dateBeforeString = "20-06-2021";
                    LocalDate date = LocalDate.now();
                    String dateAfterString = date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear();
                    Date dateBefore = myFormat.parse(dateBeforeString);
                    Date dateAfter = myFormat.parse(dateAfterString);
                    long difference = dateAfter.getTime() - dateBefore.getTime();
                    long daysBetween = (difference / (1000*60*60*24));

                    if (daysBetween > 0)
                        request.setAttribute("daysOfOpen",daysBetween);
                    else request.setAttribute("daysOfOpen", 0);
                    if (sqlAccountDAO.count() > 0)
                        request.setAttribute("account", sqlAccountDAO.count());
                    else request.setAttribute("account", 0);
                    if (sqlOrdineDAO.doRetrieveAll() > 0)
                        request.setAttribute("ordini", sqlOrdineDAO.doRetrieveAll());
                    else request.setAttribute("ordini", 0);
                    if (sqlArticoloDAO.countArticoli() > 0)
                        request.setAttribute("articoli", sqlArticoloDAO.countArticoli());
                    else request.setAttribute("articoli", 0);
                    request.getRequestDispatcher(view("customer/aboutUs")).forward(request, response);
                    break;
                }

                case "/contactUs": // contact the site for help (pagina)
                    request.getRequestDispatcher(view("customer/contactUs")).forward(request, response);
                    break;

                case "/account": // show user's account (cliente)
                    HttpSession session1 = request.getSession();
                    SQLOrdineDAO sqlOrdineDAO = new SQLOrdineDAO();
                    SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                    AccountSession accountSession = (AccountSession) session1.getAttribute("userSession");
                    if (accountSession != null){
                        List<Ordine> ordini = sqlOrdineDAO.fetchOrdine(accountSession.getEmail());
                        Map<String, List<Articolo>> articoli = new LinkedHashMap<>();
                        request.setAttribute("ordini", ordini);
                        for (Ordine o: ordini) {
                            List<Articolo> tmp = new ArrayList<>();
                            for (Articolo a : o.getArticoli()) {
                                int id = a.getIDarticolo();
                                tmp.add(sqlArticoloDAO.doRetrieveProductById(id));
                            }
                            articoli.put(o.getID_ordine(), tmp);
                        }
                        request.setAttribute("articoli", articoli);
                    }
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
        } catch (ParseException e) {
            e.printStackTrace();
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
                    SQLDatiUtenteDAO sqlDatiUtenteDAO = new SQLDatiUtenteDAO();
                    Optional<Account> accounts = sqlAccountDAO.findAccount(tmpAccount.getEmail(), tmpAccount.getPassword(), false);
                    Optional<DatiUtente> datiUtenteOptional = sqlDatiUtenteDAO.findUserData(tmpAccount.getEmail());
                    HttpSession session = request.getSession();

                    if(accounts.isPresent()){
                        synchronized (session){
                            AccountSession accountSession = (AccountSession) session.getAttribute("userSession");
                            DatiUtente datiUtente = (DatiUtente) session.getAttribute("userInfSession");

                            if (accountSession == null)
                                accountSession = new AccountSession(accounts.get());
                            else {
                                session.removeAttribute("userSession");
                                accountSession = new AccountSession(accounts.get());
                            }

                            if (datiUtente != null)
                                session.setAttribute("userInfSession", datiUtente);

                            datiUtenteOptional.ifPresent(utente -> session.setAttribute("userInfSession", utente));

                            session.setAttribute("userSession", accountSession);
                            response.sendRedirect("../index.jsp");
                        }
                    } else {
                        request.setAttribute("msg", "Credenziali errate!");
                        request.getRequestDispatcher(view("customer/login")).forward(request, response);
                    }
                    break;
                }

                case "/logout": {
                    HttpSession session = request.getSession(false);
                    authenticate(session);
                    session.removeAttribute("userSession");
                    session.removeAttribute("userInfSession");
                    session.invalidate();
                    response.sendRedirect("../index.jsp");
                    break;
                }

                case "/registration":{
                    validate(AccountValidator.validateSigin(request));
                    Account tmpAccount = new Account();
                    tmpAccount.setEmail(request.getParameter("email"));
                    tmpAccount.setPassword(request.getParameter("password"));
                    SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                    HttpSession session = request.getSession();

                    if (sqlAccountDAO.checkAccount(tmpAccount.getEmail()).isEmpty()){
                        sqlAccountDAO.createAccount(tmpAccount.getEmail(), tmpAccount.getPassword(), false);
                        Optional<Account> accounts = sqlAccountDAO.findAccount(tmpAccount.getEmail(), tmpAccount.getPassword(), false);
                        if (accounts.isPresent()){
                            synchronized (session){
                                DatiUtente datiUtente = new DatiUtente();
                                AccountSession accountSession = new AccountSession(accounts.get());
                                session.setAttribute("userSession", accountSession);
                                session.setAttribute("userInfSession", datiUtente);
                            }
                        }
                        response.sendRedirect("../index.jsp");
                    } else {
                        request.setAttribute("msg2", "Email già esistente!");
                        request.getRequestDispatcher(view("customer/login")).forward(request, response);
                    }
                    break;
                }

                case "/updateAnagraphicalDates":{
                    validate(AccountValidator.updateData(request));
                    HttpSession session = request.getSession();
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    String telefono = request.getParameter("telefono");
                    String password = request.getParameter("password");

                    synchronized (session){
                        DatiUtente datiUtente = (DatiUtente) session.getAttribute("userInfSession");
                        AccountSession accountSession = (AccountSession) session.getAttribute("userSession");
                        SQLAccountDAO accountDAO = new SQLAccountDAO();
                        SQLDatiUtenteDAO datiUtenteDAO = new SQLDatiUtenteDAO();

                        Account account = new Account();
                        account.setEmail(accountSession.getEmail());
                        account.setAdmin(false);

                        if (nome != null)
                          datiUtente.setNome(nome);

                        if (cognome != null)
                          datiUtente.setCognome(cognome);

                        if (telefono != null)
                          datiUtente.setNumeroTelefonico(telefono);

                        if (request.getParameter("birthday") != null && request.getParameter("birthday").compareTo("") != 0){
                            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
                            datiUtente.setDataDiNascita(birthday);
                        }

                        if (password != null && password.compareTo("") != 0)
                            account.setPassword(password);

                        datiUtente.setUser(new Account());
                        datiUtente.getUser().setEmail(accountSession.getEmail());
                        datiUtenteDAO.updateDatiUtete(datiUtente);
                        accountDAO.updateAccount(account);

                        session.setAttribute("userInfSession", datiUtente);
                        session.setAttribute("userSession", accountSession);
                        request.getRequestDispatcher(view("customer/myAccount")).forward(request, response);
                    }
                    break;
                }

                case "/updateAddessUser": {
                    validate(AccountValidator.updateAddress(request));
                    HttpSession session = request.getSession();
                    String indirizzo = request.getParameter("indirizzo");
                    String appartamento = request.getParameter("appartamento");
                    String city = request.getParameter("city");
                    String paese = request.getParameter("paese");
                    String CAP = request.getParameter("cap");

                    synchronized (session) {
                        DatiUtente datiUtente = (DatiUtente) session.getAttribute("userInfSession");
                        AccountSession accountSession = (AccountSession) session.getAttribute("userSession");
                        SQLDatiUtenteDAO datiUtenteDAO = new SQLDatiUtenteDAO();

                        if (indirizzo != null)
                            datiUtente.setVia(indirizzo);

                        if (CAP != null)
                            datiUtente.setCAP(CAP);

                        if (appartamento != null)
                            datiUtente.setAppartamento(appartamento);

                        if (city != null)
                            datiUtente.setCity(city);

                        if (paese != null)
                            datiUtente.setPaese(paese);

                        datiUtente.setUser(new Account());
                        datiUtente.getUser().setEmail(accountSession.getEmail());
                        datiUtenteDAO.updateAddressUtente(datiUtente);

                        session.setAttribute("userInfSession", datiUtente);
                        request.getRequestDispatcher(view("customer/myAccount")).forward(request, response);
                        break;
                    }
                }

                case "/checkout":{
                    validate(AccountValidator.updateAddress(request));
                    validate(AccountValidator.updateData(request));

                    HttpSession session = request.getSession();
                    SQLOrdineDAO sqlOrdineDAO = new SQLOrdineDAO();
                    SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                    SQLDatiUtenteDAO sqlDatiUtenteDAO = new SQLDatiUtenteDAO();
                    SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();

                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    String telefono = request.getParameter("telefono");
                    String indirizzo = request.getParameter("indirizzo");
                    String appartamento = request.getParameter("appartamento");
                    String city = request.getParameter("citta");
                    String paese = request.getParameter("stato");
                    String CAP = request.getParameter("cap");

                    if (request.getParameter("createPassword") != null){
                        if (session.getAttribute("userSession") == null){
                            if (sqlAccountDAO.checkAccount(email).isEmpty()){
                                Account tmp = new Account();
                                tmp.setPassword(password);
                                validate(AccountValidator.validateSigin(request));
                                sqlAccountDAO.createAccount(email, tmp.getPassword(), false);
                                AccountSession accountSession = new AccountSession(sqlAccountDAO.findAccount(email, tmp.getPassword(), false).get());
                                session.setAttribute("userSession", accountSession);
                                DatiUtente datiUtente = new DatiUtente();
                                if (appartamento != null)
                                    datiUtente.setAppartamento(appartamento);

                                datiUtente.setPaese(paese); datiUtente.setCity(city); datiUtente.setNome(nome); datiUtente.setCognome(cognome);
                                datiUtente.setNumeroTelefonico(telefono); datiUtente.setVia(indirizzo); datiUtente.setCAP(CAP);
                                datiUtente.setUser(new Account());
                                datiUtente.getUser().setEmail(email);
                                sqlDatiUtenteDAO.updateAddressUtente(datiUtente);
                                sqlDatiUtenteDAO.updateDatiUtete(datiUtente);
                                session.setAttribute("userInfSession", datiUtente);

                                Cart articoli = (Cart) session.getAttribute("cartNotLog");
                                for (Articolo a: articoli.getItems())
                                    sqlArticoloDAO.reduceSize(a);
                                session.removeAttribute("cartNotLog");

                                Ordine ordine = new Ordine();
                                ordine.getUser().setEmail(email);
                                ordine.setArticoli(articoli.getItems());
                                Sconto coupon = (Sconto) session.getAttribute("coupon");
                                if (coupon != null) {
                                    ordine.setCodSconto(coupon);
                                    session.removeAttribute("coupon");
                                }
                                sqlOrdineDAO.doInsertOrdine(ordine);
                                response.sendRedirect("../index.jsp");
                            }
                            else {
                                request.setAttribute("msg2", "Email già esistente!");
                                request.getRequestDispatcher(view("customer/checkout")).forward(request, response);
                            }
                        } else {
                            request.setAttribute("msg2", "Usa la tua e-mail e password.");
                            request.getRequestDispatcher(view("customer/checkout")).forward(request, response);
                        }
                    } else {
                        AccountSession accountSession = (AccountSession) session.getAttribute("userSession");
                        if(accountSession == null){
                            request.setAttribute("msg2", "Clicca sul checkbox e inserisci la password.");
                            request.getRequestDispatcher(view("customer/checkout")).forward(request, response);
                        } else {
                            if (accountSession.getEmail().compareTo(email) == 0){
                                DatiUtente datiUtente = (DatiUtente) session.getAttribute("userInfSession");

                                datiUtente.setPaese(paese); datiUtente.setCity(city); datiUtente.setNome(nome); datiUtente.setCognome(cognome);
                                datiUtente.setNumeroTelefonico(telefono); datiUtente.setVia(indirizzo); datiUtente.setAppartamento(appartamento);
                                datiUtente.setCAP(CAP);
                                datiUtente.setUser(new Account());
                                datiUtente.getUser().setEmail(email);

                                sqlDatiUtenteDAO.updateDatiUtete(datiUtente);
                                sqlDatiUtenteDAO.updateAddressUtente(datiUtente);

                                Cart articoli = (Cart) session.getAttribute("cartNotLog");
                                for (Articolo a: articoli.getItems())
                                    sqlArticoloDAO.reduceSize(a);
                                session.removeAttribute("cartNotLog");

                                Ordine ordine = new Ordine();
                                ordine.getUser().setEmail(email);
                                ordine.setArticoli(articoli.getItems());
                                Sconto coupon = (Sconto) session.getAttribute("coupon");
                                if (coupon != null) {
                                    ordine.setCodSconto(coupon);
                                    session.removeAttribute("coupon");
                                }
                                sqlOrdineDAO.doInsertOrdine(ordine);
                                response.sendRedirect("../index.jsp");
                            } else {
                                request.setAttribute("msg2", "Usa la tua e-mail e password.");
                                request.getRequestDispatcher(view("customer/checkout")).forward(request, response);
                            }
                        }
                    }
                    break;
                }

                case "/contactUs": {
                    SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                    if(sqlAccountDAO.checkAccount(request.getParameter("email")).isPresent())
                        response.sendRedirect("../index.jsp");
                    else {
                        request.setAttribute("msg", "Inserisci la tua e-mail di login.");
                        request.getRequestDispatcher(view("customer/contactUs")).forward(request, response);
                    }
                    break;
                }

                default:
                    notFound();
            }
        } catch(SQLException ex){
            log(ex.getMessage());
        } catch(InvalidRequestException e){
            log(e.getMessage());
            e.handle(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
