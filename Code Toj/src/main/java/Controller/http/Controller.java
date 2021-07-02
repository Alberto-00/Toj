package Controller.http;

import Model.Account.AccountSession;
import Model.Articolo.Articolo;
import Model.Cart.Cart;
import Model.Path_immagini.PathImg;
import Model.Path_immagini.SQLPathImgDAO;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller extends HttpServlet implements ErrorHandler {

    protected String getPath(HttpServletRequest req) {
        return req.getPathInfo() != null ? req.getPathInfo() : "/";
    }

    protected String view(String viewPath){
        String basePath = getServletContext().getInitParameter("basePath");
        String engine = getServletContext().getInitParameter("engine");
        return basePath + viewPath + engine;
    }

    protected void validate(RequestValidator validator) throws InvalidRequestException{
        if(validator.hasErrors()){
            throw new InvalidRequestException("Validation Error", validator.getErrors(),
                    HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected String back(HttpServletRequest request){
        return request.getServletPath() + request.getPathInfo();
    }

    protected String getUploadPath(){
        return System.getenv("CATALINA_HOME") + File.separator + "uploads" + File.separator;
    }

    protected int parsePage(HttpServletRequest request){
        return Integer.parseInt(request.getParameter("page"));
    }

    protected AccountSession getAccountSession(HttpSession session){
        return (AccountSession) session.getAttribute("accountSession");
    }

    protected Cart getSessionCart(HttpSession session){
        return (Cart) session.getAttribute("accountCart");
    }

    protected void sendJson(HttpServletResponse response, JSONObject object) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(object.toString());
        writer.flush();/*scarico del buffer*/
    }

    protected boolean uploadImg(Articolo articolo, HttpServletRequest request) throws ServletException, IOException, SQLException {
        List<String> filesName = new ArrayList<>();
        for (Part part: request.getParts()) {
            if (part.getName().compareTo("path") == 0) {
                filesName.add(Paths.get(part.getSubmittedFileName()).getFileName().toString());
            }
        }
        if (filesName.get(0).compareTo("") == 0) {
            return true;
        }

        SQLPathImgDAO sqlPathImgDAO = new SQLPathImgDAO();
        List<PathImg> pathImg = new ArrayList<>();
        List<String> fullPath = new ArrayList<>();
        String uploadRoot = getUploadPath();
        for (String str: filesName)
            fullPath.add(sqlPathImgDAO.writePath(articolo) + str);

        for (String pathFile: fullPath){
            if (sqlPathImgDAO.findPath(pathFile)){
                return false;
            }
        }
        for (String s : fullPath) {
            PathImg pathImg1 = new PathImg();
            pathImg1.setPathName(s);
            pathImg.add(pathImg1);
        }
        articolo.setPaths(new ArrayList<>());
        for (PathImg p: pathImg)
            articolo.getPaths().add(p);
        int i = 0;
        for (Part part: request.getParts()) {
            if (part.getName().compareTo("path") == 0) {
                try(InputStream fileStream = part.getInputStream()){
                    File file = new File(uploadRoot + fullPath.get(i));
                    Files.copy(fileStream, file.toPath());
                }
                i++;
            }
        }
        return true;
    }

    protected boolean removeImg(Articolo articolo) throws SQLException {
        SQLPathImgDAO sqlPathImgDAO = new SQLPathImgDAO();
        if ((sqlPathImgDAO.countByID(articolo) - articolo.getPaths().size()) < 2)
            return false;

        String deleteRoot = getUploadPath();
        for(int i = 0; i < articolo.getPaths().size(); i++){
            String pat2h = deleteRoot + articolo.getPaths().get(i).getPathName();
            File foto = new File(pat2h);
            if (foto.delete())
                sqlPathImgDAO.deletePath(articolo.getPaths().get(i).getPathName());
        }
        return true;
    }
}
