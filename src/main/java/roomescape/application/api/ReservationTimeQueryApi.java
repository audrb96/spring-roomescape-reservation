package roomescape.application.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.api.dto.response.FindReservationTimesResponse;
import roomescape.application.service.ReservationTimeService;
import roomescape.domain.reservationtime.ReservationTimes;

import java.util.List;

@RestController
public class ReservationTimeQueryApi {

    private final ReservationTimeService reservationTimeService;

    public ReservationTimeQueryApi(ReservationTimeService reservationTimeService) {
        this.reservationTimeService = reservationTimeService;
    }

    @GetMapping("/times")
    public ResponseEntity<List<FindReservationTimesResponse>> findReservationTimes() {
        ReservationTimes reservationTimes = reservationTimeService.findReservationTimes();

        return ResponseEntity.ok(FindReservationTimesResponse.toFindReservationResponses(reservationTimes));
    }
}