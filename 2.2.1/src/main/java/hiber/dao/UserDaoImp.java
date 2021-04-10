package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   Session session;
   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      List<User> list = null;
      Transaction transaction = null;
      session = sessionFactory.openSession();
      try {
         transaction = session.beginTransaction();
         TypedQuery<User> query = session.createQuery("select user from User user " +
                 "left join user.car car where car.model = :paramModel and car.series = :paramSeries");
         query.setParameter("paramModel", model);
         query.setParameter("paramSeries", series);
         list = query.getResultList();
         transaction.commit();
      } catch (HibernateException e) {
         e.printStackTrace();
      } finally {
         if (transaction != null) {
            transaction.rollback();
         }
         session.close();
      }
      return list.get(0);
   }
}

