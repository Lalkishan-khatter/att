package dto;

import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.Date;

public class PersonAttendance {

    private int id;
    private String name;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }
}
