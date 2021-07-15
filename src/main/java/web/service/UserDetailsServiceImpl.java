package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.Dao;
import web.model.User;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private  Dao dao;
    @Autowired
    public UserDetailsServiceImpl(Dao dao) {
        this.dao = dao;
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user =  dao.loadUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }


    public User getById(Long id) {
        return dao.getById(id);
    }

    public List<User> allUsers() {
        return dao.allUsers();
    }

    public void save(User user) {
        dao.save(user);
    }

    public void delete(Long id) {
        dao.delete(id);
    }

    public void edit(User user) {
        dao.edit(user);
    }
}
