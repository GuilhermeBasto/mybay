package ejb;

import data.Item;
import data.User;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.logging.Logger;

@Singleton
public class EmailEJB {
    private final static Logger logger = Logger.getLogger(EmailEJB.class.getName());
    @EJB
    UserEJBRemote userEJB;
    @EJB
    ItemEJBRemote itemEJB;
    @Resource(name = "java:/jboss/mail/gmail")
    private Session session;

    public EmailEJB() {
    }

    // Send Email automatically at 00:00:00
    @Schedule(hour = "0", minute = "0", second = "0")
    public void run() {
        sendMail();
    }

    private void sendMail() {
        logger.info("send mail");
        List<User> users = userEJB.getAllUsers();
        List<Item> items = itemEJB.getThreeNewestItems();
        StringBuilder body = new StringBuilder();
        body.append("The new three items on MyBay are:").append("\n\n");
        for (Item i : items) {
            body.append("Name: ")
                    .append(i.getName())
                    .append("\n")
                    .append("Origin Country: ")
                    .append(i.getOriginCountry())
                    .append("\n")
                    .append("Price: ")
                    .append(i.getPrice())
                    .append("\n\n");
        }
        for (User u : users) {
            try {
                Message message = new MimeMessage(session);
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getEmail()));
                message.setSubject("MyBay Catalog");
                message.setText(body.toString());
                Transport.send(message);
                logger.info("Email sent to " + u.getEmail());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
