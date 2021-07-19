package servlet;

import data.Item;
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

@WebServlet("/DetailItem")
public class DetailItem extends HttpServlet {
    @EJB
    ItemEJBRemote itemEJB;
    private Logger logger = Logger.getLogger(DetailItem.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doGet DetailItem");
        HttpSession session = request.getSession();
        String detailItemId = request.getParameter("itemId");
        Item item = itemEJB.getItemById(detailItemId);
        session.setAttribute("item", item);
        response.sendRedirect("item.jsp");


    }
}
