package lk.ijse.Hostel.dao.custom.impl;

import lk.ijse.Hostel.dao.custom.ReservationDAO;
import lk.ijse.Hostel.entity.Reserve;
import lk.ijse.Hostel.entity.Room;
import lk.ijse.Hostel.entity.Student;
import lk.ijse.Hostel.util.FactoryConfiguration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public ArrayList<Reserve> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Criteria criteria = session.createCriteria(Reserve.class);
        List reserve = criteria.list();

        ArrayList<Reserve> custom = new ArrayList<>(reserve);

        transaction.commit();
        session.close();
        return custom;
    }

    @Override
    public boolean save(Reserve entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reserve entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();

        Student load = session.load(Student.class, s);
        session.delete(load);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Reserve search(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Reserve reserve=session.get(Reserve.class,s);
        transaction.commit();
        session.close();
        return reserve;
    }

    @Override
    public List<Reserve> searchReservedRoomById(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Reserve WHERE room = :roomTypeId";
        Query query = session.createQuery(hql);

        Room room = new Room();
        room.setRoomTypeId(id);

        query.setParameter("roomTypeId", room);
        List<Reserve> r = query.list();

        transaction.commit();
        session.close();

        return r;
    }
}
