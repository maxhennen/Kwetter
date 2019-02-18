package domain.user;

public class Details {
    private String bio;
    private String website;
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
}
