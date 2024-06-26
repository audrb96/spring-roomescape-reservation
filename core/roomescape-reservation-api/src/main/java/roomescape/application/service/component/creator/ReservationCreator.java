package roomescape.application.service.component.creator;

import org.springframework.stereotype.Component;
import roomescape.application.service.component.validator.CreateReservationValidator;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.ReservationRepository;
import roomescape.domain.reservation.vo.ReservationDate;
import roomescape.domain.reservation.vo.ReservationId;
import roomescape.domain.reservation.vo.ReservationName;
import roomescape.domain.reservationtime.ReservationTime;
import roomescape.domain.theme.Theme;

@Component
public class ReservationCreator {

    private final CreateReservationValidator createReservationValidator;
    private final ReservationRepository reservationRepository;

    public ReservationCreator(
            CreateReservationValidator createReservationValidator,
            ReservationRepository reservationRepository
    ) {
        this.createReservationValidator = createReservationValidator;
        this.reservationRepository = reservationRepository;
    }

    public Reservation create(
            ReservationName reservationName,
            ReservationDate reservationDate,
            ReservationTime reservationTime,
            Theme theme
    ) {
        createReservationValidator.validate(reservationDate, reservationTime);

        Reservation createdReservation = Reservation.create(
                ReservationId.empty(),
                reservationName,
                reservationDate,
                reservationTime,
                theme
        );

        return reservationRepository.save(createdReservation);
    }
}
