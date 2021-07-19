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

@WebServlet("/EditAccount")
public class EditAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    UserEJBRemote userEJB;
    private Logger logger = Logger.getLogger(EditAccount.class.getName());

    public EditAccount() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("DoPost editAccount");
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        String password = request.getParameter("password");
        String id = session.getAttribute("userSessionId").toString();


        User user = userEJB.editAccount(id, name, email, country, password);

        session.setAttribute("userSession", user);
        logger.info("Profile was Edited");
        response.sendRedirect("SearchItem");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
