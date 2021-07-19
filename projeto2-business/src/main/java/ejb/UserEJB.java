package ejb;

import data.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class UserEJB implements UserEJBRemote {
    private final static Logger logger = Logger.getLogger(UserEJB.class.getName());

    @PersistenceContext(name = "Users")
    EntityManager em;

    public UserEJB() {
        // TODO Auto-generated constructor stub
    }

    private String hashPassword(String password) {
        logger.info("Hash password");
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(password.getBytes());
            // Get the hash's bytes
            byte[] bytes = md.digest();
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public User login(String email, String password) {
        logger.info("Login");
        String hashedPassword = hashPassword(password);
        logger.info("hashed password->"+password);

        Query q = em.createQuery("FROM User e WHERE email=:email AND password=:password");
        q.setParameter("email", email);
        q.setParameter("password", hashedPassword);
        if (q.getResultList().size() == 0)
            return null;

        else
            return (User) q.getSingleResult();
    }


    public boolean checkEmailExists(String email) {
        logger.info("Check if email exist" + email);
        boolean exists = true;
        Query q = em.createQuery("FROM User e WHERE e.email=:email");
        q.setParameter("email", email);
        if (q.getResultList().size() == 0)
            exists = false;
        return exists;
    }

    public void createAccount(String name, String country, String email, String password) {
        logger.info("Create account");
        User newUser = new User(name, country, email, hashPassword(password));
        em.persist(newUser);
        logger.info("User registered");
    }

    public User editAccount(String id, String name, String email, String country, String password) {
        logger.info("Edit account");
        User editedUser = em.find(User.class, Integer.parseInt(id));
        editedUser.setName(name);
        editedUser.setEmail(email);
        editedUser.setCountry(country);
        editedUser.setPassword(hashPassword(password));
        em.persist(editedUser);
        logger.info("User edited with success");
        return editedUser;
    }

    public void deleteAccount(int id) {
        logger.info("delete account");
        User user = em.find(User.class, id);
        em.remove(user);
    }

    public List<User> getAllUsers(){
        logger.info("get all users");
        Query q = em.createQuery("FROM User");
        return q.getResultList();

    }

}
