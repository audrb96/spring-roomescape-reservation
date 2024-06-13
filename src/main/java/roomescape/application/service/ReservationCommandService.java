package roomescape.application.service;

import org.springframework.stereotype.Service;
import roomescape.application.mapper.ReservationEntityMapper;
import roomescape.application.mapper.ReservationMapper;
import roomescape.application.service.command.CreateReservationCommand;
import roomescape.application.service.command.DeleteReservationCommand;
import roomescape.domain.reservation.Reservation;
import roomescape.repository.ReservationRepository;
import roomescape.repository.entity.ReservationEntity;

@Service
public class ReservationCommandService {

    private final ReservationRepository reservationRepository;

    public ReservationCommandService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(CreateReservationCommand createReservationCommand) {
        Reservation reservation = createReservationCommand.toReservation();
        ReservationEntity savedEntity = reservationRepository.save(ReservationEntityMapper.toReservationEntity(reservation));

        return ReservationMapper.toReservation(savedEntity);
    }

    public void deleteReservation(DeleteReservationCommand deleteReservationCommand) {
        reservationRepository.delete(deleteReservationCommand.getReservationId());
    }
}