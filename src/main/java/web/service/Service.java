package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import web.dao.Dao;
import web.dao.DaoImpl;
import web.model.User;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {
    private Dao dao;
    @Autowired
    public Service(Dao dao) {
        this.dao = dao;
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
    public User findUserByUsername(String email) {
        return dao.findUserByUsername(email);
    }
}
