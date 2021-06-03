package Controller.http;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.File;

public class Controller extends HttpServlet {

    protected static DataSource source;

    protected String getPath(HttpServletRequest req) {
        return req.getPathInfo() != null ? req.getPathInfo() : "/";
    }

    protected String view(String viewPath){
        String basePath = getServletContext().getInitParameter("basePath");
        String engine = getServletContext().getInitParameter("engine");
        return basePath + viewPath + engine;
    }

    protected String back(HttpServletRequest request){
        return request.getServletPath() + request.getPathInfo();
    }

    protected  String getUploadPath(){
        return System.getenv("CATALINA_HOME") + File.separator + "uploads" + File.separator;
    }
}
