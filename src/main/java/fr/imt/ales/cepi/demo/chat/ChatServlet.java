package fr.imt.ales.cepi.demo.chat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet for the chat page. Handle only GET method. Dispatch to chat.jsp
 */
@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("jsp/chat.jsp").forward(request, response);
    }
}