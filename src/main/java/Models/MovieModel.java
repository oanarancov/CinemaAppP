package Models;

import java.util.UUID;

public class MovieModel {
    private String price, name, description, key;
    private ReservationState reservationState;

    public MovieModel(String name, String price, String description, ReservationState reservationState) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.key = UUID.randomUUID().toString();
        this.reservationState = reservationState;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ReservationState getReservationState() {
        return reservationState;
    }

    public void setReservationState(ReservationState reservationState) {
        this.reservationState = reservationState;
    }
}
