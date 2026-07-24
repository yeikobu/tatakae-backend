package fit.tatakae.domain;

import fit.tatakae.domain.exception.FraudulentSessionException;
import fit.tatakae.domain.exception.InconsistentSessionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;


public class TrainingSessionTest {

    @Test
    public void isTrainingSessionValid() {
        // Arrange
        User user = new User("axw1", "Jacob", "CL", PrivacyLevel.PUBLIC);
        Exercise exercise = Exercise.PULL_UP;
        int reps = 15;
        Instant dateExecuted = Instant.parse("2026-07-22T10:00:00Z");
        Instant start = dateExecuted;
        Instant end = start.plusSeconds(60);
        Clock clock = Clock.fixed(dateExecuted, ZoneOffset.UTC);

        // Act
        TrainingSession trainingSession = new TrainingSession(user, exercise, reps, start, end, clock);

        //Assert
        assertEquals(user, trainingSession.getUser());
        assertEquals(exercise, trainingSession.getExercise());
        assertEquals(reps, trainingSession.getReps());
        assertEquals(start, trainingSession.getStart());
        assertEquals(end, trainingSession.getEnd());
        assertEquals(clock, trainingSession.getClock());
    }

    // Prueba que las repeticiones no sean inconsistentes, es decir, iguales o inferiores a cero
    @ParameterizedTest
    @ValueSource(ints = {0, -1, -50})
    public void shouldThrowExceptionWhenRepsAreInconsistent(int invalidQuantityReps) {
        // Arrange
        User user = new User("axw1", "Jacob", "CL", PrivacyLevel.PUBLIC);
        Exercise exercise = Exercise.PULL_UP;
        Instant dateExecuted = Instant.parse("2026-07-22T10:00:00Z");
        Instant start = dateExecuted;
        Instant end = start.plusSeconds(60);
        Clock clock = Clock.fixed(dateExecuted, ZoneOffset.UTC);

        // Act
        // Assert
        assertThrows(InconsistentSessionException.class, () -> {
            new TrainingSession(user, exercise, invalidQuantityReps, start, end, clock);
        });
    }

    // Prueba que el usuario haya ejecutado una serie con repeticiones realistas
    @Test
    public void shouldTrownAndExceptionWhenRepsAreMoreThanTheMaxRepsAllowedPerMinute() {
        // Arrange
        User user = new User("axw1", "Jacob", "CL", PrivacyLevel.PUBLIC);
        Exercise exercise = Exercise.PULL_UP;
        int reps = 78;
        Instant dateExecuted = Instant.parse("2026-07-22T10:00:00Z");
        Instant start = dateExecuted;
        Instant end = start.plusSeconds(60);
        Clock clock = Clock.fixed(dateExecuted, ZoneOffset.UTC);

        // Act
        // Assert
        assertThrows(FraudulentSessionException.class, () -> {
            new TrainingSession(user, exercise, reps, start, end, clock);
        });
    }

    @Test
    public void shouldThrowExceptionWhenEndIsBeforeStart() {
        // Arrange
        User user = new User("axw1", "Jacob", "CL", PrivacyLevel.PUBLIC);
        Exercise exercise = Exercise.PULL_UP;
        int reps = 65;
        Instant dateExecuted = Instant.parse("2026-07-22T10:00:00Z");
        Instant end = dateExecuted;
        Instant start = end.plusSeconds(60);
        Clock clock = Clock.fixed(dateExecuted, ZoneOffset.UTC);


        //Act and Assert
        assertThrows(InconsistentSessionException.class, () -> {
            new TrainingSession(user, exercise, reps, start, end, clock);
        });
    }

    @Test
    public void shouldThrowExceptionWhenStartIsEqualToEnd() {
        // Arrange
        User user = new User("axw1", "Jacob", "CL", PrivacyLevel.PUBLIC);
        Exercise exercise = Exercise.PULL_UP;
        int reps = 65;
        Instant dateExecuted = Instant.parse("2026-07-22T10:00:00Z");
        Instant start = dateExecuted;
        Instant end = Instant.parse("2026-07-22T10:00:00Z");
        Clock clock = Clock.fixed(dateExecuted, ZoneOffset.UTC);

        //Act and Assert
        assertThrows(InconsistentSessionException.class, () -> {
            new TrainingSession(user, exercise, reps, start, end, clock);
        });
    }
}
