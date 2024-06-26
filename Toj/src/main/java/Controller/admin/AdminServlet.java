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
import Model.Path_immagini.SQLPathImgDAO;
import Model.Taglia.SQLTagliaDAO;
import Model.Taglia.Taglia;
import Model.search.Paginator;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "AdminServlet", value = "/adminServlet/*")
@MultipartConfig
public class AdminServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String path = getPath(request);
            switch (path) {
                case "/adminGestioneClienti": {
                    AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
                    if (accountSession != null) {
                        if (request.isRequestedSessionIdValid() && accountSession.isAdmin()) {
                            SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                            SQLDatiUtenteDAO sqlDatiUtenteDAO = new SQLDatiUtenteDAO();

                            int intPage = parsePage(request);
                            Paginator paginator = new Paginator(intPage, 10);
                            int size = sqlAccountDAO.countCustomers();
                            request.setAttribute("pages", paginator.getPages(size));
                            request.setAttribute("numPage", intPage);
                            List<DatiUtente> datiUtenti = sqlDatiUtenteDAO.doRetriveAll(paginator);
                            request.setAttribute("accounts", datiUtenti);
                            request.getRequestDispatcher(view("admin/adminGestioneClienti")).forward(request, response);
                        }
                    } else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;
                }

                case "/adminGestioneOrdini": {
                    AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
                    if (accountSession != null){
                        if (request.isRequestedSessionIdValid() && accountSession.isAdmin()) {
                            SQLOrdineDAO sqlOrdineDAO = new SQLOrdineDAO();

                            int intPage = parsePage(request);
                            Paginator paginator = new Paginator(intPage, 10);
                            int size = sqlOrdineDAO.countOrdini();
                            request.setAttribute("pages", paginator.getPages(size));
                            request.setAttribute("numPage", intPage);
                            List<Ordine> ordini = sqlOrdineDAO.getOrders(paginator);
                            for (Ordine o : ordini) {
                                List<Articolo> articoli = sqlOrdineDAO.fetchArticoliOrdine(o.getID_ordine());
                                o.setArticoli(new ArrayList<>());
                                for (Articolo a : articoli)
                                    o.getArticoli().add(a);
                            }
                            request.setAttribute("ordini", ordini);
                            request.getRequestDispatcher(view("admin/adminGestioneOrdini")).forward(request, response);
                        }
                    }
                    else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;
                }

                case "/adminGestioneArticoli": {
                    AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
                    if (accountSession != null) {
                        if (request.isRequestedSessionIdValid() && accountSession.isAdmin()) {
                            SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();

                            int size = sqlArticoloDAO.getArticoli().size();
                            int intPage = parsePage(request);
                            request.setAttribute("numPage", intPage);
                            Paginator paginator = new Paginator(intPage, 10, false);
                            request.setAttribute("pages", paginator.getPages(size));
                            int count = paginator.getCount();
                            List<Articolo> products = sqlArticoloDAO.getArticoliPage(paginator);
                            request.setAttribute("productsList", products);
                            request.setAttribute("count", count);
                            request.getRequestDispatcher(view("admin/adminGestioneArticoli")).forward(request, response);
                        }
                    } else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;
                }

                case "/adminGestioneArticoliForm": { /*view of page Modify-Product and Delete-Product*/
                    int id = Integer.parseInt(request.getParameter("id"));
                    SQLArticoloDAO sqlArticoloDAO2 = new SQLArticoloDAO();
                    SQLColoreDAO sqlColoreDAO = new SQLColoreDAO();
                    SQLTagliaDAO sqlTagliaDAO = new SQLTagliaDAO();
                    SQLCategoriaDAO sqlCategoriaDAO = new SQLCategoriaDAO();
                    SQLPathImgDAO sqlPathImgDAO = new SQLPathImgDAO();
                    Articolo articolo = sqlArticoloDAO2.doRetrieveProductById(id);

                    Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
                    if (errors != null) {
                        request.setAttribute("errors", errors);
                        session.removeAttribute("errors");
                    }
                    request.setAttribute("articolo", articolo);
                    request.setAttribute("colori", sqlColoreDAO.doRetrieveAll());
                    request.setAttribute("categorie", sqlCategoriaDAO.doRetrieveAll());
                    request.setAttribute("taglie", sqlTagliaDAO.doRetrieveAllByID(articolo));
                    request.setAttribute("paths", sqlPathImgDAO.doRetrieveByID(articolo));
                    request.getRequestDispatcher(view("admin/adminGestioneArticoliForm")).forward(request, response);
                    break;
                }

                case "/adminGestioneArticoliAggiungi" : {
                    AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
                    if (accountSession != null){
                        if (request.isRequestedSessionIdValid() && accountSession.isAdmin()) {
                            SQLColoreDAO sqlColoreDAO1 = new SQLColoreDAO();
                            SQLTagliaDAO sqlTagliaDAO = new SQLTagliaDAO();
                            SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                            SQLCategoriaDAO sqlCategoriaDAO = new SQLCategoriaDAO();

                            Map<String, String> errors = (Map<String, String>) session.getAttribute("errors");
                            if (errors != null) {
                                request.setAttribute("errors", errors);
                                session.removeAttribute("errors");
                            }
                            request.setAttribute("categorie", sqlCategoriaDAO.doRetrieveAll());
                            request.setAttribute("taglie", sqlTagliaDAO.doRetrieveAll());
                            request.setAttribute("colori", sqlColoreDAO1.doRetrieveAll());
                            request.setAttribute("maxID", sqlArticoloDAO.maxID());
                            request.getRequestDispatcher(view("admin/adminGestioneArticoliAggiungi")).forward(request, response);
                        }
                    }
                    else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;
                }

                case "/adminHomepage": {
                    AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
                    if (accountSession != null) {
                        if (request.isRequestedSessionIdValid() && accountSession.isAdmin()) {
                            SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                            SQLOrdineDAO sqlOrdineDAO = new SQLOrdineDAO();
                            SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                            request.setAttribute("customers", sqlAccountDAO.countCustomers());
                            request.setAttribute("ordini", sqlOrdineDAO.countOrdini());
                            request.setAttribute("articoli", sqlArticoloDAO.countArticoli());
                            request.getRequestDispatcher(view("admin/adminHomepage")).forward(request, response);
                        }
                    } else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;
                }

                case "/adminLogin":
                    request.getRequestDispatcher(view("admin/adminLogin")).forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<String, String> errors = new LinkedHashMap<>();
            HttpSession session = request.getSession();
            String path = getPath(request);
            switch (path){
                case "/adminLogin":{
                    validate(AccountValidator.validateSigin(request));
                    Account tmpAccount = new Account();
                    tmpAccount.setEmail(request.getParameter("email"));
                    tmpAccount.setPassword(request.getParameter("password"));
                    SQLAccountDAO sqlAccountDAO = new SQLAccountDAO();
                    Optional<Account> accounts = sqlAccountDAO.findAccount(tmpAccount.getEmail(), tmpAccount.getPassword(), true);

                    if(accounts.isPresent()){
                        AccountSession accountSession = new AccountSession(accounts.get());
                        session.setAttribute("accountSession", accountSession);
                        response.sendRedirect("./adminHomepage");
                    } else {
                        request.setAttribute("msg", "Credenziali errate.");
                        request.getRequestDispatcher(view("admin/adminLogin")).forward(request, response);
                    }
                    break;
                }

                case "/adminLogout": {
                    AccountSession accountSession = (AccountSession) session.getAttribute("accountSession");
                    if (accountSession != null) {
                        session.removeAttribute("accountSession");
                        session.invalidate();
                        response.sendRedirect("./adminLogin");
                    }
                    break;
                }

                case "/adminGestioneArticoliFormModify": {
                    SQLArticoloDAO articoloDAO1 = new SQLArticoloDAO();
                    Articolo articolo2 = new Articolo();
                    articolo2.setIDarticolo(Integer.parseInt(request.getParameter("idArticolo")));
                    articolo2.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
                    articolo2.setSesso(request.getParameter("sesso").toUpperCase());
                    articolo2.setDescrizione(request.getParameter("descrizione"));
                    articolo2.setSconto(Double.parseDouble(request.getParameter("sconto")));

                    Categoria categoria2 = new Categoria();
                    categoria2.setId_categoria(Integer.parseInt(request.getParameter("idCategoria")));
                    articolo2.setCategoria(categoria2);
                    articolo2.setNome(request.getParameter("nome"));

                    String[] quantita = request.getParameterValues("quantita");
                    articolo2.setTaglie(new ArrayList<>());
                    SQLTagliaDAO sqlTagliaDAO = new SQLTagliaDAO();
                    int i = 0;
                    for (Taglia t: sqlTagliaDAO.doRetrieveAllByID(articolo2)){
                        t.setQuantita(Integer.parseInt(quantita[i]));
                        articolo2.getTaglie().add(t);
                        i++;
                    }
                    String[] colori = request.getParameterValues("colore");
                    articolo2.setColori(new ArrayList<>());
                    for (String s : colori) {
                        Colore colore = new Colore();
                        colore.setCod_esadecimale(s);
                        articolo2.getColori().add(colore);
                    }
                    if (!uploadImg(articolo2, request))
                        errors.put("pathModify", "Foto già presente.");

                    String[] deletePath = request.getParameterValues("deletePath");
                    if (deletePath != null && deletePath.length > 0) {
                        Articolo deletePathArticolo = new Articolo();
                        deletePathArticolo.setIDarticolo(articolo2.getIDarticolo());
                        deletePathArticolo.setPaths(new ArrayList<>());
                        for (i = 0; i < deletePath.length; i++) {
                            PathImg pathImg = new PathImg();
                            pathImg.setPathName(deletePath[i]);
                            deletePathArticolo.getPaths().add(pathImg);
                        }
                        if (!removeImg(deletePathArticolo))
                            errors.put("pathRemove", "Lascia almeno due foto.");
                    }
                    if (errors.size() > 0){
                        session.setAttribute("errors", errors);
                        response.sendRedirect("./adminGestioneArticoliForm?id=" + request.getParameter("idArticolo"));
                        break;
                    }
                    articoloDAO1.doUpdateArticolo(articolo2);
                    response.sendRedirect("./adminGestioneArticoli?page=1");
                    break;
                }

                case "/adminGestioneArticoliFormDelete": {
                    SQLArticoloDAO sqlArticoloDAO = new SQLArticoloDAO();
                    int id = Integer.parseInt(request.getParameter("id"));
                    int idDelete = Integer.parseInt(request.getParameter("idDelete"));
                    Articolo articoloDelete = sqlArticoloDAO.doRetrieveProductById(idDelete);

                    if (articoloDelete == null) {
                        errors.put("msgID", "ID non trovato.");
                        session.setAttribute("errors", errors);
                        response.sendRedirect("./adminGestioneArticoliForm?id=" + id);
                        break;
                    }
                    String deleteRoot = getUploadPath();
                    for (int i = 0; i < articoloDelete.getPaths().size(); i++) {
                        String pat2h = deleteRoot + articoloDelete.getPaths().get(i).getPathName();
                        File foto = new File(pat2h);
                        foto.delete();
                    }
                    sqlArticoloDAO.doDeleteArticolo(articoloDelete);
                    response.sendRedirect("./adminGestioneArticoli?page=1");
                    break;
                }

                case "/adminGestioneArticoliFormInsert": {
                    int count = 0;
                    for (Part part : request.getParts()) {
                        if (part.getName().compareTo("path") == 0)
                            count++;
                    }
                    if (count < 2)
                        errors.put("msgPath", "Inserisci almeno due foto.");

                    SQLArticoloDAO articoloDao = new SQLArticoloDAO();
                    Articolo articolo = new Articolo();

                    articolo.setIDarticolo(Integer.parseInt(request.getParameter("idArticolo")));
                    Articolo tmp = articoloDao.doRetrieveProductById(articolo.getIDarticolo());

                    if (tmp == null) {
                        articolo.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
                        articolo.setSesso(request.getParameter("sesso").toUpperCase());
                        articolo.setDescrizione(request.getParameter("descrizione"));
                        articolo.setSconto(Double.parseDouble(request.getParameter("sconto")));

                        Categoria categoria = new Categoria();
                        categoria.setId_categoria(Integer.parseInt(request.getParameter("idCategoria")));
                        articolo.setCategoria(categoria);
                        articolo.setNome(request.getParameter("nome"));

                        String[] quantita = request.getParameterValues("quantita");
                        String[] taglie = request.getParameterValues("taglia");

                        if (taglie.length > 0) {
                            articolo.setTaglie(new ArrayList<>());
                            for (String s : taglie) {
                                Taglia taglia = new Taglia();
                                taglia.setId_nome(s);
                                articolo.getTaglie().add(taglia);
                            }
                            for (int i = 0, j = 0; i < quantita.length; i++) {
                                if (!quantita[i].isBlank()) {
                                    articolo.getTaglie().get(j).setQuantita(Integer.parseInt(quantita[i]));
                                    j++;
                                }
                            }
                        } else
                            notFound();

                        Date date = new Date();
                        articolo.setData_inserimento(date);
                        String[] colorValues = request.getParameterValues("colore");
                        SQLColoreDAO sqlColoreDAO = new SQLColoreDAO();
                        articolo.setColori(new ArrayList<>());

                        for (String colorValue : colorValues)
                            articolo.getColori().add(sqlColoreDAO.doRetrieveById(colorValue));

                        if (!uploadImg(articolo, request))
                            errors.put("pathIsPresent", "Foto già presente.");
                    } else
                        errors.put("msg", "ID già presente.");

                    if (errors.size() > 0) {
                        session.setAttribute("errors", errors);
                        response.sendRedirect("./adminGestioneArticoliAggiungi");
                        break;
                    }
                    articoloDao.doCreateArticolo(articolo);
                    response.sendRedirect("./adminGestioneArticoli?page=1");
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
        }
    }
}
