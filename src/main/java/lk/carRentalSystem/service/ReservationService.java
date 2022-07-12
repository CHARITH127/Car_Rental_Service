package lk.carRentalSystem.service;

import lk.carRentalSystem.dto.CarDTO;
import lk.carRentalSystem.dto.DriverScheduleDTO;
import lk.carRentalSystem.dto.ReservationDTO;
import lk.carRentalSystem.entity.Reservation;

import java.sql.Date;
import java.util.List;

public interface ReservationService {

    String generateReservationId();

    void saveReservationWithoutDriver(ReservationDTO dto);

    void saveReservationWithDriver(DriverScheduleDTO scheduleDTO);

    void deleteReservation(String id);

    ReservationDTO getReservationById(String id);

    void updateReservation(ReservationDTO dto);

    ReservationDTO searchReservation(String id);

    List<ReservationDTO> getReservationByCustomer(String cID);

    List<ReservationDTO> getTodayPickups(Date date);

    List<ReservationDTO> getTodayReserving(Date date);

    List<ReservationDTO> getReservationByCustomerAboutToAccept(String cID);

    List<ReservationDTO> getAllReservations();


}
