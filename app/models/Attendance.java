package models;
import akka.actor.ProviderSelection;
import io.ebean.Finder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

//    @Column(name="email")
//    private String email;

    @Column(name = "check_in", columnDefinition = "DATETIME")
    private LocalDateTime checkIn;
    @Column(name="check_out", columnDefinition = "DATETIME")
    private LocalDateTime checkOut;

    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;
  //  @JoinColumn(name="id",referencedColumnName = "id")


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public static final Finder<Integer, Attendance> find = new Finder<>(Attendance.class);


//    public List<Person> getEmail() {
//        List<Person> persons=Person.find.query().select("email").findList();
//        return persons;
//    }
//    @Override
//    public String toString() {
//        return "Attendance{" +
//                "id=" + id +
//                ", email='" + email + '\'' +
//                ", checkIn=" + checkIn +
//                ", checkOut=" + checkOut +
//                '}';}

    }
