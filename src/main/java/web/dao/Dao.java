package web.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import web.model.User;
import web.util.Util;

@Component
public class Dao {

     private static final SessionFactory sessionFactory = new Util().getSessionFactory();
  /*  Util util=new Util();
    @Autowired
    EntityManagerFactory emf  = util.entityManagerFactory().getNativeEntityManagerFactory();

    EntityManager em = emf.createEntityManager();

    public User getUser(Long Id) {

        User user = em.find(User.class, Id);
        em.detach(user);
        return user;
    }
 */
  public User getUserId(Long id) {

      Session session = sessionFactory.openSession();
      Transaction transaction = session.getTransaction();
      transaction.begin();

      User user =  session.get(User.class, id);

      transaction.commit();
      session.close();
      return user;
  }


}
