package servlet;

import ejb.ItemEJBRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/MyItems")
public class MyItems extends HttpServlet {

    @EJB
    ItemEJBRemote itemEJB;

    private Logger logger = Logger.getLogger(MyItems.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("DoPost MyItems");
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userSessionId");
        session.setAttribute("myItems", itemEJB.getAllItemsFormUser(userId));
        response.sendRedirect("myItems.jsp");


    }
}
