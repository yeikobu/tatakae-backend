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

    public boolean isPublic() {
        return this.privacyLevel == PrivacyLevel.PUBLIC;
    }

    public boolean isFromCountry(String otherCountry) {
        return this.country.equals(otherCountry);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User other)) {
            return false;
        }
        return this.userId.equals(other.userId);
    }

    @Override
    public int hashCode() {
        return this.userId.hashCode();
    }
}
