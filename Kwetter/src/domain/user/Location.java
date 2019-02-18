package domain.user;

public class Location {
    private String country;
    private String city;
    private String street;
    private String house_number;

    public Location(String country, String city, String street, String house_number) {
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

    public String getHouse_number() {
        return house_number;
    }
}
