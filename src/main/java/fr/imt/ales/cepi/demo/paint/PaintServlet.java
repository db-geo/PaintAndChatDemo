package fr.imt.ales.cepi.demo.paint;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet for the paint page. Handle only GET method. Dispatch to paint.jsp
 */
@WebServlet("/paint")
public class PaintServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("jsp/paint.jsp").forward(request, response);
    }
}