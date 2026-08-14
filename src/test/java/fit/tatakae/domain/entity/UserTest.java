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

    @Test
    public void shouldConfirmWhenUserPrivacyIsPublic() {
        // Arrange
        User user = new User("1", "Jacob", "cl", PrivacyLevel.PUBLIC);

        // Act
        boolean isPublic = user.isPublic();

        // Assert
        assertTrue(isPublic);
    }

    @Test
    public void shouldDenyWhenUserPrivacyIsPrivate() {
        // Arrange
        User user = new User("1", "Jacob", "cl", PrivacyLevel.PRIVATE);

        // Act
        boolean isPublic = user.isPublic();

        // Assert
        assertFalse(isPublic);
    }

    @Test
    public void shouldConfirmWhenUserIsFromTheQueriedCountry() {
        // Arrange
        User user = new User("1", "Jacob", "cl", PrivacyLevel.PUBLIC);

        // Act
        boolean isFromCountry = user.isFromCountry("cl");

        // Assert
        assertTrue(isFromCountry);
    }

    @Test
    public void shouldDenyWhenUserIsNotFromTheQueriedCountry() {
        // Arrange
        User user = new User("1", "Jacob", "cl", PrivacyLevel.PUBLIC);

        // Act
        boolean isFromCountry = user.isFromCountry("us");

        // Assert
        assertFalse(isFromCountry);
    }

    @Test
    public void shouldBeEqualToItself() {
        // Arrange
        User user = new User("1", "Jacob", "cl", PrivacyLevel.PUBLIC);

        // Act
        boolean isEqual = user.equals(user);

        // Assert
        assertTrue(isEqual);
    }

    @Test
    public void shouldNotBeEqualToNullOrToADifferentType() {
        // Arrange
        User user = new User("1", "Jacob", "cl", PrivacyLevel.PUBLIC);

        // Act
        // Assert
        assertFalse(user.equals(null));
        assertFalse(user.equals("not a user"));
    }

    @Test
    public void shouldBeEqualToAnotherUserWithTheSameIdEvenIfAttributesDiffer() {
        // Arrange
        User original = new User("1", "Jacob", "cl", PrivacyLevel.PUBLIC);
        User sameIdDifferentAttributes = new User("1", "Someone Else", "us", PrivacyLevel.PRIVATE);

        // Act
        // Assert
        assertEquals(original, sameIdDifferentAttributes);
        assertEquals(original.hashCode(), sameIdDifferentAttributes.hashCode());
    }

    @Test
    public void shouldNotBeEqualWhenIdsDifferEvenIfAttributesMatch() {
        // Arrange
        User first = new User("1", "Jacob", "cl", PrivacyLevel.PUBLIC);
        User second = new User("2", "Jacob", "cl", PrivacyLevel.PUBLIC);

        // Act
        // Assert
        assertNotEquals(first, second);
    }
}
