package Controller.adminServlets;

import Controller.customer.Validator.AccountValidator;
import Controller.http.Controller;
import Controller.http.InvalidRequestException;
import Model.Account.Account;
import Model.Account.AccountSession;
import Model.Account.SQLAccountDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AdminServlet", value = "/adminServlet/*")
public class AdminServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            AccountSession accountSession = (AccountSession) request.getSession().getAttribute("accountSession");
            String path = getPath(request);
            switch (path) {
                case "/adminGestioneClienti":
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin() == true)
                        request.getRequestDispatcher(view("admin/adminGestioneClienti")).forward(request, response);
                    else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminGestioneOrdini":
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin() == true)
                        request.getRequestDispatcher(view("admin/adminGestioneOrdini")).forward(request, response);
                    else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminGestioneArticoli":
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin() == true)
                        request.getRequestDispatcher(view("admin/adminGestioneArticoli")).forward(request, response);
                    else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminHomepage":
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin() == true){
                        request.getRequestDispatcher(view("admin/adminHomepage")).forward(request, response);
                    }
                    else
                        throw new InvalidRequestException("Non sei Autorizzato", List.of("Non sei Autorizzato"), HttpServletResponse.SC_FORBIDDEN);
                    break;

                case "/adminProfilo":
                    if (request.isRequestedSessionIdValid() && accountSession.isAdmin() == true)
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
        }catch (InvalidRequestException e) {
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
                        System.out.println(accountSession.getEmail());
                        request.getSession(true).setAttribute("accountSession", accountSession);
                        response.sendRedirect("./adminHomepage");
                    } else {
                        throw new InvalidRequestException("Credenziali non valide", List.of("Credenziali non valide"), HttpServletResponse.SC_BAD_REQUEST);
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
