package fit.tatakae.domain.entity;

import fit.tatakae.domain.exception.InvalidUserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @ParameterizedTest
    @NullAndEmptySource
    public void shouldThrowExceptionWhenUserNAmeIsNullOrEmpty(String invalidUserName) {
        // Arrange
        String userId = "asddd33";
        String country = "cl";
        PrivacyLevel privacyLevel = PrivacyLevel.PUBLIC;

        // Act and Assert
        assertThrows(InvalidUserException.class, () -> {
            new User(userId, invalidUserName, country, privacyLevel);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void shouldThrowExceptionWhenUserIdIsNullOrEmpty(String invalidUserId) {
        // Arrange
        String username = "Jacob";
        String country = "cl";
        PrivacyLevel privacyLevel = PrivacyLevel.PUBLIC;

        // Act + Assert
        assertThrows(InvalidUserException.class, () -> {
            new User(invalidUserId, username, country, privacyLevel);
        });
    }

    @Test
    public void isValidUserTest() {
        //Arrange
        String userId = "asddd33";
        String username = "Jacob";
        String country = "cl";
        PrivacyLevel privacyLevel = PrivacyLevel.PUBLIC;

        //Act
        User user = new User(userId, username, country, privacyLevel);

        //Assert
        assertEquals(userId, user.getUserId());
        assertEquals(username, user.getUsername());
        assertEquals(country, user.getCountry());
        assertEquals(privacyLevel, user.getPrivacyLevel());
    }
}
