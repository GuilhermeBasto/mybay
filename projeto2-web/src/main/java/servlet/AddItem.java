package servlet;

import ejb.ItemEJBRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

@WebServlet("/AddItem")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class AddItem extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    ItemEJBRemote itemEJB;
    private Logger logger = Logger.getLogger(AddItem.class.getName());

    public AddItem() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("DoPost AddItem");
        HttpSession session = request.getSession();

        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String country = request.getParameter("originCountry");
        String price = request.getParameter("price");

        String userId = session.getAttribute("userSessionId").toString();

        InputStream inputStream = null; // input stream of the upload file
        Part filePart = request.getPart("photograph");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] byteArray = null;

        if (filePart != null && filePart.getSize() != 0) {
            inputStream = filePart.getInputStream();
            byte[] byteChunk = new byte[4096];
            int n;
            while ((n = inputStream.read(byteChunk)) > 0) {
                baos.write(byteChunk, 0, n);
            }
            byteArray = baos.toByteArray();
        }


        itemEJB.createItem(userId, name, category, country, byteArray, Integer.parseInt(price));
        response.sendRedirect("menu.jsp");


    }
}
