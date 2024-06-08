package roomescape.application.api.dto.response;

import roomescape.domain.reservation.Reservation;

public class CreateReservationResponse {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm";

    private final Long id;

    private final String name;

    private final String date;

    private final String time;

    public CreateReservationResponse(Long id, String name, String date, String time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public static CreateReservationResponse from(Reservation reservation) {
        return new CreateReservationResponse(
                reservation.getId(),
                reservation.getReservationName(),
                reservation.fetchReservationDateTime(DATE_FORMAT),
                reservation.fetchReservationDateTime(TIME_FORMAT)
        );
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

    public String getTime() {
        return time;
    }
}