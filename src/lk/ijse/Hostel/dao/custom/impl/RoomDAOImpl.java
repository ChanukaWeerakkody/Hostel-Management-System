package lk.ijse.Hostel.dao.custom.impl;

import lk.ijse.Hostel.dao.custom.RoomDAO;
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

public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Room.class);
        List rooms = criteria.list();
        ArrayList<Room> allRoom = new ArrayList<>(rooms);
        transaction.commit();
        session.close();
        return allRoom;
    }

    @Override
    public boolean save(Room entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room entity) throws IOException {
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
        Transaction transaction = session.beginTransaction();

        Room load = session.load(Room.class, s);
        session.delete(load);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room search(String s) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Room room=session.get(Room.class,s);
        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public List<Room> getRoomDataFromType(String type) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM Room WHERE type = :pType";
        Query query = session.createQuery(hql);

        query.setParameter("pType", type);
        List<Room> room = query.list();
        transaction.commit();
        session.close();
        return room;
    }

    @Override
    public Room getRoom(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.get(Room.class, id);

        transaction.commit();
        session.close();

        return room;
    }
}
