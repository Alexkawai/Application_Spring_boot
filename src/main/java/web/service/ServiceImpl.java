package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import web.dao.Dao;
import web.model.User;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
    private Dao dao;
    @Autowired
    public ServiceImpl(Dao dao) {
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
    @Transactional
    @Override
    public void save(User user) {
        dao.save(user);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        dao.delete(id);
    }
    @Transactional
    @Override
    public void edit(User user) {
        dao.edit(user);
    }

}
