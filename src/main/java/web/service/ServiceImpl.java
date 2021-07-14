package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.Dao;
import web.model.User;

import java.util.List;

@Service
public class ServiceImpl implements ServiceInterface{
    Dao dao;
    @Autowired
    public void setDao(Dao dao) {
        this.dao = dao;
    }
    @Override
    public User getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<User> allUsers() {
        return dao.allUsers();
    }

    @Override
    public void save(User user) {
        dao.save(user);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    public void edit(User user) {
        dao.edit(user);
    }
}
