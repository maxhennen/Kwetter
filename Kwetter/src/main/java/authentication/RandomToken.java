package authentication;


import domain.User;
import io.jsonwebtoken.Jwts;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class RandomToken {

    private static final String API_KEY = "MY_SECRET_API_KEY";

    /**
     * Generate a random string.
     */
    public String createJWT(User user) {

        try {
            Date expired = Date.from(ZonedDateTime.now().plusHours(1).toInstant());
            Date issuedAt = Date.from(ZonedDateTime.now().toInstant());

            return Jwts.builder()
                    .setIssuer(user.getEmail())
                    .setSubject(user.getName())
                    .setExpiration(expired)
                    .setIssuedAt(issuedAt)
                    .setId(UUID.randomUUID().toString()).compact();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
