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

@WebServlet("BuyItem")
public class BuyItem extends HttpServlet {
    @EJB
    ItemEJBRemote itemEJB;
    private Logger logger = Logger.getLogger(BuyItem.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("do post buy Item");
        HttpSession session = request.getSession();
        Item detailItem = (Item) session.getAttribute("item");
        itemEJB.buyItem(detailItem.getId());
        response.sendRedirect("SearchItem");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("do get buy Item");
        HttpSession session = request.getSession();
        String detailItemId = request.getParameter("itemId");
        Item item = itemEJB.getItemById(detailItemId);
        session.setAttribute("item", item);
        response.sendRedirect("buyItem.jsp");


    }
}
