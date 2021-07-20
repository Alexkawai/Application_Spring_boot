package web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Transactional
@Repository
public class DaoImpl implements Dao{

   @PersistenceContext
   private EntityManager  em ;


    @Override
    public void save(User user) {
        em.getTransaction().begin();
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        em.merge(user);
        em.getTransaction().commit();
    }
    @Override
    public User getById(Long id) {
        User user = em.find(User.class, id);
        em.detach(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        User user = em.find(User.class,id);
        em.remove(user);
        em.getTransaction().commit();
    }
    @Override
    public List<User> allUsers() {
        return em.createQuery("from User",User.class).getResultList();
    }

    @Override
    public void edit(User user) {
        em.getTransaction().begin();
        User user2=em.find(User.class, user.getId());
        user2.setId(user.getId());
        user2.setName(user.getName());
        user2.setLastName(user.getLastName());
        user2.setEmail(user.getEmail());

        em.merge(user2);
        em.getTransaction().commit();
    }
    @Override
    public User loadUserByUsername(String email) {
        User user = em.createQuery("select distinct u from User u left join fetch u.roles where u.email = :email ",User.class)
                .setParameter("email",email).getSingleResult();
        return user;
    }




}
