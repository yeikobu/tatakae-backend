package fit.tatakae.domain.entity;

import fit.tatakae.domain.exception.InvalidUserException;

public class User {
    private final String userId;
    private final String username;
    private final String country;
    private final PrivacyLevel privacyLevel;

    public User(String userId, String username, String country, PrivacyLevel privacyLevel) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new InvalidUserException("User id cannot be null or empty");
        }
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidUserException("Username cannot be null or empty");
        }

        this.userId = userId;
        this.username = username;
        this.country = country;
        this.privacyLevel = privacyLevel;
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
