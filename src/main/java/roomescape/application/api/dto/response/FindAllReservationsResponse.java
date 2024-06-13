package roomescape.application.api.dto.response;

import roomescape.domain.reservation.ReservationView;
import roomescape.domain.reservation.ReservationViews;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllReservationsResponse {

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm";

    private final Long id;

    private final String name;

    private final String date;

    private final Time time;


    public FindAllReservationsResponse(Long id, String name, String date, Time time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static FindAllReservationsResponse from(ReservationView reservationView) {
        return new FindAllReservationsResponse(
                reservationView.getReservationId(),
                reservationView.getReservationName(),
                reservationView.getFormattedReservationDate(DATE_PATTERN),
                new FindAllReservationsResponse.Time(
                        reservationView.getReservationTimeId(),
                        reservationView.getFormattedReservationTimeStartAt(TIME_PATTERN)
                )
        );
    }

    public static List<FindAllReservationsResponse> toFindAllReservationsResponses(ReservationViews reservationViews) {
        return reservationViews.getReservationViews()
                .stream()
                .map(FindAllReservationsResponse::from)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    private static class Time {
        
        private final Long timeId;

        private final String startAt;

        public Time(Long timeId, String startAt) {
            this.timeId = timeId;
            this.startAt = startAt;
        }

        public Long getTimeId() {
            return timeId;
        }

        public String getStartAt() {
            return startAt;
        }
    }
}
