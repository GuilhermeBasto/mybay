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

@WebServlet("/SearchItem")
public class SearchItem extends HttpServlet {
    @EJB
    ItemEJBRemote itemEJB;
    private Logger logger = Logger.getLogger(SearchItem.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("search items");
        HttpSession session = request.getSession();
        String searchBy = request.getParameter("searchBy");
        String search1 = request.getParameter("search1");
        String search2 = request.getParameter("search2");
        String sort = request.getParameter("sort");

        if (searchBy != null && sort != null) {
            switch (searchBy) {
                case "All":
                    logger.info("All");
                    session.setAttribute("Items", itemEJB.getAllItems(sort));
                    break;
                case "Country":
                    session.setAttribute("Items", itemEJB.getItemsByCountry(search1, sort));
                    break;
                case "Category":
                    session.setAttribute("Items", itemEJB.getItemsByCategory(search1, sort));
                    break;
                case "Name":
                    session.setAttribute("Items", itemEJB.getItemsByName(search1, sort));
                    break;
                case "Price":
                    session.setAttribute("Items", itemEJB.getItemsBetweenPrice(Integer.parseInt(search1), Integer.parseInt(search2), sort));
                    break;
                case "Date":
                    session.setAttribute("Items", itemEJB.getItemsAfterDate(search1, sort));
                    break;
            }
        }

        response.sendRedirect("menu.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("Items", itemEJB.getAllItems("desc"));
        response.sendRedirect("menu.jsp");

    }

}
