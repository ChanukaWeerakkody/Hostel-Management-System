package lk.ijse.Hostel.bo;

import lk.ijse.Hostel.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVE:
                return new ReservationBOImpl();
            case RESERVE_DETAIL:
                return new ReservationDetailBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        USER,STUDENT,ROOM,RESERVE,RESERVE_DETAIL
    }
}
