package servlet;

import data.User;
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


@WebServlet("/Login")
public class Login extends HttpServlet {
    @EJB
    UserEJBRemote userEJB;

    private Logger logger = Logger.getLogger(Login.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("DoPost of Login");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session;
        User user = userEJB.login(email, password);
        if (user != null) {
            session = request.getSession();
            session.setAttribute("userSessionId", user.getId());
            session.setAttribute("userSession", user);
            logger.info("Login success!");
            response.sendRedirect("menu.jsp");
        } else {
            logger.info("Login error!");
            response.sendRedirect("login.jsp");
        }
    }

}
