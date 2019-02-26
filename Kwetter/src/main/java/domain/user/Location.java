package domain.user;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "location")
public class Location {

    @GeneratedValue
    @Id
    private long id;
    @Column(name = "county")
    private String country;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house_number")
    private int house_number;

    public Location(String country, String city, String street, int house_number) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.house_number = house_number;
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

    public int getHouse_number() {
        return house_number;
    }

    @Override
    public String toString() {
        return "Location{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house_number='" + house_number + '\'' +
                '}';
    }
}
