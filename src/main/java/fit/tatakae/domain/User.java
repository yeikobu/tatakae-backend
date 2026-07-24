package fit.tatakae.domain;

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
