package models;

import io.ebean.Finder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "role")
public class Role {
    @Id
    @Column(name="id")
    private Integer role_id;

    @Column(name = "role")
    private String role;


    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public static final Finder<Integer, Role> find = new Finder<>(Role.class);
}
