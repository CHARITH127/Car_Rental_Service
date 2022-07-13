package lk.carRentalSystem.service.impl;

import lk.carRentalSystem.dto.DriverScheduleDTO;
import lk.carRentalSystem.dto.ReservationDTO;
import lk.carRentalSystem.entity.DriverSchedule;
import lk.carRentalSystem.entity.Reservation;
import lk.carRentalSystem.repo.DriverScheduleRepo;
import lk.carRentalSystem.repo.ReservationRepo;
import lk.carRentalSystem.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private DriverScheduleRepo scheduleRepo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public String generateReservationId() {
        String result = reservationRepo.genarateReservationId();
        String id;

        if (result == null) {
            id = "R-001";
            return id;
        } else {
            int tempID = Integer.parseInt(result.split("-")[1]);
            tempID = tempID + 1;
            if (tempID < 9) {
                id = "R-00" + tempID;
                return id;
            } else if (tempID < 99) {
                id = "R-0" + tempID;
                return id;
            } else {
                id = "R-" + tempID;
                return id;
            }
        }

    }

    @Override
    public void saveReservationWithoutDriver(ReservationDTO dto) {
        if (reservationRepo.existsById(dto.getReservation_id())) {
            throw new RuntimeException("Reservation is already booking");
        } else {
            reservationRepo.save(mapper.map(dto, Reservation.class));
        }

    }

    @Override
    public void saveReservationWithDriver(DriverScheduleDTO scheduleDTO) {
        scheduleRepo.save(mapper.map(scheduleDTO, DriverSchedule.class));
    }

    @Override
    public void deleteReservation(String id) {
        if (reservationRepo.existsById(id)) {
            reservationRepo.deleteById(id);
        } else {
            throw new RuntimeException("Not Such a Reservation");
        }

    }

    @Override
    public ReservationDTO getReservationById(String id) {
        if (reservationRepo.existsById(id)) {
            return mapper.map(reservationRepo.findById(id),ReservationDTO.class);
        } else {
            throw new RuntimeException("Not Such a Reservation");
        }

    }

    @Override
    public void updateReservation(ReservationDTO dto) {
        if (reservationRepo.existsById(dto.getReservation_id())) {
            reservationRepo.save(mapper.map(dto, Reservation.class));
        } else {
            throw new RuntimeException("Not Such a Reservation");
        }
    }

    @Override
    public ReservationDTO searchReservation(String id) {
        if (reservationRepo.existsById(id)) {
            return mapper.map(reservationRepo.findById(id).get(), ReservationDTO.class);
        } else {
            throw new RuntimeException("Not Such a Reservation");
        }
    }

    @Override
    public List<ReservationDTO> getReservationByCustomer(String cID) {
        return mapper.map(reservationRepo.getReservationByCustomer(cID), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> getTodayPickups(Date date) {
        return mapper.map(reservationRepo.getTodayPickups(date), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> getTodayReserving(Date date) {
        return mapper.map(reservationRepo.getTodayPickups(date), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> getTodayReturning() {
        LocalDate date = LocalDate.now();
        Date today = Date.valueOf(date);
        return mapper.map( reservationRepo.getTodayReturn(today), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

    @Override
    public List<ReservationDTO> getReservationByCustomerAboutToAccept(String cID) {
        return mapper.map(reservationRepo.getReservationByCustomerAboutToAccept(cID), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }


    @Override
    public List<ReservationDTO> getAllReservations() {
        return mapper.map(reservationRepo.findAll(), new TypeToken<List<ReservationDTO>>() {
        }.getType());
    }

}
