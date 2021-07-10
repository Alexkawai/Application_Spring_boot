package web.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.config.Util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository

public class Dao {

    private LocalContainerEntityManagerFactoryBean lcemfb;
    @Autowired
    public void setLcemfb(LocalContainerEntityManagerFactoryBean lcemfb) {
        this.lcemfb = lcemfb;
    }





/*
    public List<User> allFilms() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User",User.class).list();
    }





    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }


    public void edit(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

*/
    public void save(User user) {
        EntityManager em =  lcemfb.getObject().createEntityManager();

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
    public User getById(Long id) {

        EntityManager em = lcemfb.getObject().createEntityManager();
        User user = em.find(User.class, id);
        em.detach(user);
        return user;
    }
    public void delete(User user) {
        EntityManager em = lcemfb.getObject().createEntityManager();
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }



}
