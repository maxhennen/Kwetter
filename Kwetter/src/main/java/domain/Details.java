package domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "details")
public class Details implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(name = "bio")
    private String bio;
    @Column(name = "website")
    private String website;
    @OneToOne(mappedBy = "location")
    private Location location;

    public Details(String bio, String website, Location location) {
        this.bio = bio;
        this.website = website;
        this.location = location;
    }

    public Details(String bio, String website) {
        this.bio = bio;
        this.website = website;
    }

    public Details() {
    }

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }

    public Location getLocation() {
        return location;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Details{" +
                "bio='" + bio + '\'' +
                ", website='" + website + '\'' +
                ", location=" + location +
                '}';
    }
}
