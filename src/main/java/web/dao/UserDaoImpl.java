package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = entityManager
                .createQuery("from User order by id asc", User.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }


}
