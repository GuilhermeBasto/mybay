package servlet;

import ejb.UserEJBRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
    @EJB
    UserEJBRemote userEJB;

    private Logger logger = Logger.getLogger(CreateAccount.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("DoPost create account");
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

       /* if (userEJB.checkEmailExists(email)) {
            logger.info(" Email already used");
            response.sendRedirect("register.jsp");
        } else {*/
            userEJB.createAccount(name, country, email, password);
            logger.info(" User created successfully");
            response.sendRedirect("login.jsp");
        //}
    }

}

