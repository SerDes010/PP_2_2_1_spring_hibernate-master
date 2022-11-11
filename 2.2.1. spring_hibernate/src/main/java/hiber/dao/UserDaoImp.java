package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   //@Autowired
   private SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query =  sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User getModelAndSeries(String model, int series) {
      TypedQuery<User> query= sessionFactory.getCurrentSession().createQuery("FROM User as u WHERE u.car.model =:model AND u.car.series =: series")
              .setParameter("model",model)
              .setParameter("series",series);
      return query.getSingleResult();
   }

}
