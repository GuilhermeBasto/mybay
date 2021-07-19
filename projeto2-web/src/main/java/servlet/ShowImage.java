package servlet;

import data.Item;
import ejb.ItemEJBRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

@WebServlet("ShowImage")
public class ShowImage extends HttpServlet {
    @EJB
    ItemEJBRemote itemEJB;
    private Logger logger = Logger.getLogger(BuyItem.class.getName());

    public ShowImage() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("do get show image");
        String itemId = request.getParameter("id");
        Item item = itemEJB.getItemById(itemId);
        response.setContentType("image/jpeg");
        OutputStream oImage;
        oImage = response.getOutputStream();
        oImage.write(item.getPhotograph());
        oImage.flush();
        oImage.close();
    }
}
