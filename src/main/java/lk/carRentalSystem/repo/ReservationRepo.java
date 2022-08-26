package lk.carRentalSystem.repo;

import lk.carRentalSystem.dto.ReservationDTO;
import lk.carRentalSystem.entity.Car;
import lk.carRentalSystem.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface ReservationRepo extends JpaRepository<Reservation, String> {
    @Query(value = "select reservation_id from Reservation order by reservation_id desc limit 1",nativeQuery = true)
    String genarateReservationId();

    @Query(value = "select * from Reservation where customer_customerNic=?",nativeQuery = true)
    List<Reservation> getReservationByCustomer(String cID);

    @Query(value = "select * from Reservation where  reservation_status='pending'",nativeQuery = true)
    List<Reservation> getReservationAboutToAccept();

    @Query(value = "select * from Reservation where  reservation_status='pending' or reservation_status='accept'",nativeQuery = true)
    List<Reservation> getAllReservations();

    @Query(value = "select * from Reservation where pick_up_date=? and reservation_status='Accept'",nativeQuery = true)
    List<Reservation> getTodayPickups(Date date);

    @Query(value = "select * from Reservation where reserve_date=?",nativeQuery = true)
    List<Reservation> getTodayReserving(Date date);

    @Query(value = "select * from Reservation where return_date=? and reservation_status='Accept'",nativeQuery = true)
    List<Reservation> getTodayReturn(Date date);
}
