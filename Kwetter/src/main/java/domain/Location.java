package domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "location")
public class Location implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(name = "county")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house_number")
    private String housenumber;
    @OneToOne
    private Details details;

    public Location(String country, String city, String street, String housenumber) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.housenumber = housenumber;
    }

    public Location() {
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHousenumber(String house_number) {
        this.housenumber = house_number;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house_number='" + housenumber + '\'' +
                '}';
    }
}
