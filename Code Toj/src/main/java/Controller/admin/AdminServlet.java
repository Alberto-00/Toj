package Controller.admin;

import Controller.customer.Validator.AccountValidator;
import Controller.http.Controller;
import Controller.http.InvalidRequestException;
import Model.Account.Account;
import Model.Account.AccountSession;
import Model.Account.SQLAccountDAO;
import Model.Articolo.Articolo;
import Model.Articolo.SQLArticoloDAO;
import Model.Categoria.Categoria;
import Model.Categoria.SQLCategoriaDAO;
import Model.Colore.Colore;
import Model.Colore.SQLColoreDAO;
import Model.Dati_utente.DatiUtente;
import Model.Dati_utente.SQLDatiUtenteDAO;
import Model.Ordine.Ordine;
import Model.Ordine.SQLOrdineDAO;
import Model.Path_immagini.PathImg;
import Model.Taglia.SQLTagliaDAO;
import Model.Taglia.Taglia;
import Model.search.Paginator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "AdminServlet", value = "/adminServlet/*")
@MultipartConfig
public class AdminServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AccountSession accountSession = (AccountSession) request.getSession().getAttribute("accountSession");
            String path = getPath(request);
            switch (path) {
                case "/adminGestioneClienti":
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin()){
                        SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                        SQLDatiUtenteDAO sqlDatiUtenteDAO = new SQLDatiUtenteDAO();

                        int intPage = parsePage(request);
                        Paginator paginator = new Paginator(intPage,10);
                        int size = sqlAccountDAO.countCustomers();
                        request.setAttribute("pages",paginator.getPages(size));
                        List<DatiUtente> datiUtenti = sqlDatiUtenteDAO.doRetriveAll(paginator);
                        request.setAttribute("accounts", datiUtenti);
                        request.getRequestDispatcher(view("admin/adminGestioneClienti")).forward(request, response);
                    }
                    else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminGestioneOrdini":
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin()) {
                        SQLOrdineDAO sqlOrdineDAO = new SQLOrdineDAO();

                        int intPage = parsePage(request);
                        Paginator paginator = new Paginator(intPage,10);
                        int size = sqlOrdineDAO.countOrdini();
                        request.setAttribute("pages",paginator.getPages(size));
                        ArrayList<Ordine> ordini = sqlOrdineDAO.getOrders(paginator);
                        request.setAttribute("ordini", ordini);
                        request.getRequestDispatcher(view("admin/adminGestioneOrdini")).forward(request, response);
                    } else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminGestioneArticoli":
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin()){
                        SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();

                        boolean latest = false;
                        int size = sqlArticoloDAO.getArticoli().size();
                        int intPage = parsePage(request);

                        request.setAttribute("numPage", intPage);

                        Paginator paginator = new Paginator(intPage, 10, latest);
                        request.setAttribute("pages", paginator.getPages(size));
                        int count = paginator.getCount();
                        List<Articolo> products = sqlArticoloDAO.getArticoliPage(paginator);
                        request.setAttribute("productsList", products);
                        request.setAttribute("count", count);

                        request.getRequestDispatcher(view("admin/adminGestioneArticoli")).forward(request, response);
                    }

                    else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminGestioneArticoliForm":
                    //if (request.isRequestedSessionIdValid() && accountSession.isAdmin())
                        int id = Integer.parseInt(request.getParameter("id"));
                        SQLArticoloDAO sqlArticoloDAO2 = new SQLArticoloDAO();
                        SQLColoreDAO sqlColoreDAO = new SQLColoreDAO();
                        Articolo articolo = sqlArticoloDAO2.doRetrieveProductById(id);
                        request.setAttribute("articolo",articolo);
                        request.setAttribute("colori", sqlColoreDAO.doRetrieveAll());
                        request.getRequestDispatcher(view("admin/adminGestioneArticoliForm")).forward(request, response);
                    //else
                        //throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminGestioneArticoliAggiungi" :
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin()){
                        SQLColoreDAO sqlColoreDAO1 = new SQLColoreDAO();
                        SQLTagliaDAO sqlTagliaDAO = new SQLTagliaDAO();
                        SQLCategoriaDAO sqlCategoriaDAO = new SQLCategoriaDAO();
                        request.setAttribute("categorie", sqlCategoriaDAO.doRetrieveAll());
                        request.setAttribute("taglie", sqlTagliaDAO.doRetrieveAll());
                        request.setAttribute("colori", sqlColoreDAO1.doRetrieveAll());
                        request.getRequestDispatcher(view("admin/adminGestioneArticoliAggiungi")).forward(request, response);
                    }
                    else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminHomepage":
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin()){
                        SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                        SQLOrdineDAO sqlOrdineDAO = new SQLOrdineDAO();
                        SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                        request.setAttribute("customers",sqlAccountDAO.countCustomers());
                        request.setAttribute("ordini",sqlOrdineDAO.countOrdini());
                        request.setAttribute("articoli",sqlArticoloDAO.countArticoli());
                        request.getRequestDispatcher(view("admin/adminHomepage")).forward(request, response);
                    }
                    else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminProfilo":
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin())
                        request.getRequestDispatcher(view("admin/adminProfilo")).forward(request, response);
                   else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminLogin":
                    request.getRequestDispatcher(view("admin/adminLogin")).forward(request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Risorsa non trovata");
            }
        }catch (InvalidRequestException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path){
                case "/adminLogin":{
                    request.setAttribute("back",view("/adminServlet/adminLogin"));
                    validate(AccountValidator.validateSigin(request));
                    Account tmpAccount = new Account();
                    tmpAccount.setEmail(request.getParameter("email"));
                    tmpAccount.setPassword(request.getParameter("password"));
                    SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                    Optional<Account> accounts = sqlAccountDAO.findAccount(tmpAccount.getEmail(), tmpAccount.getPassword(), true);

                    if(accounts.isPresent()){
                        AccountSession accountSession = new AccountSession(accounts.get());
                        request.getSession(true).setAttribute("accountSession", accountSession);
                        response.sendRedirect("./adminHomepage");
                    } else {
                        response.sendRedirect("./adminLogin");
                    }
                    break;
                }

                case "/adminLogout":
                    HttpSession session = request.getSession(false);
                    authenticate(session);
                    AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
                    session.removeAttribute("accountSession");
                    session.invalidate();
                    response.sendRedirect("./adminLogin");
                    break;

                case "/adminGestioneArticoliFormModify":
                    SQLArticoloDAO articoloDAO1 = new SQLArticoloDAO();
                    Articolo articolo2 = new Articolo();
                    articolo2.setIDarticolo(Integer.parseInt(request.getParameter("idArticolo")));
                    articolo2.setIDarticolo(Integer.parseInt(request.getParameter("idArticolo")));
                    articolo2.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
                    articolo2.setSesso(request.getParameter("sesso").toUpperCase());
                    articolo2.setDescrizione(request.getParameter("descrizione"));
                    articolo2.setSconto(Integer.parseInt(request.getParameter("sconto")));
                    Categoria categoria2 = new Categoria();
                    categoria2.setId_categoria(Integer.parseInt(request.getParameter("idCategoria")));
                    articolo2.setCategoria(categoria2);
                    articolo2.setNome(request.getParameter("nome"));
                    articoloDAO1.doUpdateArticolo(articolo2);

                    int quantita2 = Integer.parseInt(request.getParameter("quantita"));
                    if(request.getParameterValues("taglia") == null){
                        SQLTagliaDAO sqlTagliaDAO = new SQLTagliaDAO();
                        List<Taglia> newTaglie = sqlTagliaDAO.doRetrieveAll(); /*sta qui*/
                        for (Taglia t: newTaglie) {
                            t.setQuantita(quantita2);
                        }
                        articolo2.setTaglie(newTaglie);

                    }
                    else{
                        String taglie []= request.getParameterValues("taglia");
                        List<Taglia> newTaglie = new ArrayList();
                        for (String str: taglie) {
                            Taglia taglia = new Taglia();
                            taglia.setId_nome(str);
                            taglia.setQuantita(quantita2);
                            newTaglie.add(taglia);
                        }
                        articolo2.setTaglie(newTaglie);
                    }
                    break;

                case "/adminGestioneArticoliFormDelete":
                    Articolo articoloDelete = new Articolo();
                    articoloDelete.setIDarticolo(Integer.parseInt(request.getParameter("id")));

                    SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                    sqlArticoloDAO.doDeleteArticolo(articoloDelete);
                    response.sendRedirect("./adminHomepage");
                    break;

                case "/adminGestioneArticoliFormInsert":
                    request.setAttribute("back","/adminGestioneArticoli");
                    SQLArticoloDAO articoloDao = new SQLArticoloDAO();
                    Articolo articolo = new Articolo();
                    articolo.setIDarticolo(Integer.parseInt(request.getParameter("idArticolo")));
                    articolo.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
                    articolo.setSesso(request.getParameter("sesso").toUpperCase());
                    articolo.setDescrizione(request.getParameter("descrizione"));
                    articolo.setSconto(Integer.parseInt(request.getParameter("sconto")));

                    Categoria categoria = new Categoria();
                    categoria.setId_categoria(Integer.parseInt(request.getParameter("idCategoria")));
                    articolo.setCategoria(categoria);
                    articolo.setNome(request.getParameter("nome"));

                    String[] quantita = request.getParameterValues("quantita");
                    String[] taglie = request.getParameterValues("taglia");

                    if(taglie.length > 0){
                        articolo.setTaglie(new ArrayList<>());
                        for (int i = 0; i < taglie.length; i++) {
                            Taglia taglia = new Taglia();
                            taglia.setId_nome(taglie[i]);
                            articolo.getTaglie().add(taglia);
                        }
                        for (int i = 0, j = 0; i < quantita.length; i++) {
                            if (!quantita[i].isBlank()) {
                                articolo.getTaglie().get(j).setQuantita(Integer.parseInt(quantita[i]));
                                j++;
                            }
                        }
                    }
                    else{
                        notFound();
                    }
                    Date date = new Date();
                    articolo.setData_inserimento(date);
                    String[] colorValues = request.getParameterValues("colore");
                    SQLColoreDAO sqlColoreDAO = new SQLColoreDAO();
                    articolo.setColori(new ArrayList<>());

                    for (String colorValue : colorValues) {
                        articolo.getColori().add(sqlColoreDAO.doRetrieveById(colorValue));
                    }

                    Part filePart = request.getPart("path");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    PathImg pathImg = new PathImg();
                    pathImg.setPathName(fileName);
                    articolo.setPaths(new ArrayList<>());
                    articolo.getPaths().add(pathImg);

                    articoloDao.doCreateArticolo(articolo);
                    String uploadRoot = getUploadPath();
                    try(InputStream fileStream = filePart.getInputStream()){
                        File file = new File(uploadRoot + fileName);
                        Files.copy(fileStream,file.toPath());
                    }
                    response.sendRedirect("/../adminHomepage");
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
