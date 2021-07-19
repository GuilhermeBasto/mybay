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

@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
    @EJB
    ItemEJBRemote itemEJB;
    private Logger logger = Logger.getLogger(servlet.DeleteItem.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("do post Delete Item");
        HttpSession session = request.getSession();
        Item detailItem = (Item) session.getAttribute("item");
        itemEJB.deleteItem(detailItem.getId());
        response.sendRedirect("SearchItem");


    }
}

