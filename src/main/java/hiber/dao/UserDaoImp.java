package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findUserByCar(int series, String model) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("select user from User user where" +
                        " user.car.model = :model and" +
                        " user.car.series = :series", User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList().isEmpty() ? null : query.getResultList().get(0);
    }

    @Override
    public void cleanAllTable() {
        sessionFactory.getCurrentSession().createQuery("delete from User").executeUpdate();
        sessionFactory.getCurrentSession().createQuery("delete from Car").executeUpdate();
    }

}