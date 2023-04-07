package lk.ijse.Hostel.dao.custom.impl;

import lk.ijse.Hostel.dao.custom.UserDAO;
import lk.ijse.Hostel.entity.User;
import lk.ijse.Hostel.util.FactoryConfiguration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(User.class);
        List user = criteria.list();

        ArrayList<User> all = new ArrayList<>(user);

        transaction.commit();
        session.close();
        return all;
    }

    @Override
    public boolean save(User entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user=session.get(User.class,s);
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public User search(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user=session.get(User.class,s);
        transaction.commit();
        session.close();
        return user;
    }
}
