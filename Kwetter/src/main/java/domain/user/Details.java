package domain.user;

import javax.persistence.*;

@Entity
@Table(name = "details")
public class Details {

    @GeneratedValue
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

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }

    public Location getLocation() {
        return location;
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
