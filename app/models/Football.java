package models;

import io.ebean.Finder;

import javax.persistence.*;


@Entity
@Table(name = "results")
public class Football {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "home_score")
    private int home_score;
    @Column(name = "away_score")
    private int away_score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHome_score() {
        return home_score;
    }

    public void setHome_score(int home_score) {
        this.home_score = home_score;
    }

    public int getAway_score() {
        return away_score;
    }

    public void setAway_score(int away_score) {
        this.away_score = away_score;
    }

    public static final Finder<Integer, Football> finds = new Finder<>(Football.class);

}




