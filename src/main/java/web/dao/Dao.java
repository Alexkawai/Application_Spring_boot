package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;

import java.util.List;

public interface Dao {
     void save(User user) ;
    User getById(Long id) ;
    void delete(Long id) ;
    List<User> allUsers() ;
    void edit(User user) ;
    User findUserByUsername(String email);

    UserDetails loadUserByUsername(String email) ;
}
