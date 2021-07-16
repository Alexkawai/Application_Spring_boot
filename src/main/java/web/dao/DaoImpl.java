package web.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class DaoImpl implements Dao{
    private EntityManagerFactory emf;
    private EntityManager  em ;

    public DaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
        this.em = emf.createEntityManager();
    }

    public void save(User user) {
        em.getTransaction().begin();
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        em.merge(user);
        em.getTransaction().commit();
    }
    public User getById(Long id) {
        User user = em.find(User.class, id);
        em.detach(user);
        return user;
    }
    public void delete(Long id) {
        em.getTransaction().begin();
        User user = em.find(User.class,id);
        em.remove(user);
        em.getTransaction().commit();
    }
    public List<User> allUsers() {
        return em.createQuery("from User",User.class).getResultList();
    }
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

    public UserDetails loadUserByUsername(String email) {
        User user = em.createQuery(" from User  where email = :email ",User.class)
                .setParameter("email",email).getSingleResult();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    public User findUserByUsername(String email) {
        return  em.createQuery(" from User  where email = :email ",User.class)
                .setParameter("email",email).getSingleResult();

    }



}
