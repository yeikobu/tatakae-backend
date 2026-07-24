package fit.tatakae.domain;

import fit.tatakae.domain.exception.InvalidUserException;

public class User {
    private String userId;
    private String username;
    private String country;
    private PrivacyLevel privacyLevel;

    public User(String userId, String username, String country, PrivacyLevel privacyLevel) {
        this.userId = userId;
        this.username = username;
        this.country = country;
        this.privacyLevel = privacyLevel;

        if (userId == null || userId.trim().isEmpty()) {
            throw new InvalidUserException("User id cannot be null or empty");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidUserException("Username cannot be null or empty");
        }
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getCountry() {
        return this.country;
    }

    public PrivacyLevel getPrivacyLevel() {
        return this.privacyLevel;
    }
}
