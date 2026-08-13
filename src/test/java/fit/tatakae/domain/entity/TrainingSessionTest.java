package fit.tatakae.domain.entity;

import fit.tatakae.domain.exception.FraudulentSessionException;
import org.junit.jupiter.api.Test;

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
}
