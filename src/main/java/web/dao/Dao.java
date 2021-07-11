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


    public void save(User user) {
        EntityManager em =  lcemfb.getObject().createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }
    public User getById(Long id) {

        EntityManager em = lcemfb.getObject().createEntityManager();
        User user = em.find(User.class, id);
        em.detach(user);
        em.close();
        return user;
    }
    public void delete(Long id) {
        EntityManager em = lcemfb.getObject().createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class,id);
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }
    public List<User> allUsers() {
        EntityManager em = lcemfb.getObject().createEntityManager();
        List<User> users = em.createQuery("from User",User.class).getResultList();
        em.close();
        return users;
    }
    public void edit(User user) {
        EntityManager em = lcemfb.getObject().createEntityManager();
        em.getTransaction().begin();
        User user2=em.find(User.class, user.getId());
        user2.setId(user.getId());
        user2.setName(user.getName());
        user2.setLastName(user.getLastName());
        user2.setEmail(user.getEmail());

        em.merge(user2);
        em.getTransaction().commit();
        em.close();
    }



}
