package Controller.customer;

import Controller.http.Controller;
import Controller.http.InvalidRequestException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = "/dashboard/*")
public class DashboardServlet extends Controller {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String path = getPath(request);
            switch (path){
                case "/home":
                    authorize(request.getSession(false));
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
                default:
                    notFound();
            }
        } catch (InvalidRequestException ex){
            log(ex.getMessage());
            ex.handle(request, response);
        }
    }
}
