package models;

import io.ebean.Finder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name="pid")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="created_on", columnDefinition="DATETIME")
    private LocalDateTime createdOn;

    @ManyToOne(cascade = CascadeType.ALL)
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }



    public static final Finder<Integer, Person> find = new Finder<>(Person.class);
}
