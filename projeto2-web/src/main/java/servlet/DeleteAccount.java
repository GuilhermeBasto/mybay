package servlet;

import ejb.UserEJBRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {
    @EJB
    UserEJBRemote userEJB;
    private Logger logger = Logger.getLogger(servlet.DeleteItem.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("do post Delete Account");
        HttpSession session = request.getSession();
        userEJB.deleteAccount((int) session.getAttribute("userSessionId"));
        response.sendRedirect("Logout");


    }
}

