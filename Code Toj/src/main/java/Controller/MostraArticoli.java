package Controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet(name = "MostraArticoli", value = "/MostraArticoli")
@MultipartConfig
public class MostraArticoli extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("cover");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        //settare anche la jsp products min 2ore:40
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
