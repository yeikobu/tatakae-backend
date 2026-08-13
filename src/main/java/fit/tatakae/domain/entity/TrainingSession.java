package fit.tatakae.domain.entity;

import fit.tatakae.domain.exception.FraudulentSessionException;
import fit.tatakae.domain.valueobject.RepsCount;
import fit.tatakae.domain.valueobject.SessionTimeframe;

import java.time.Clock;
import java.time.Instant;

public class TrainingSession {
    private final User user;
    private final Exercise exercise;
    private final RepsCount reps;
    private final SessionTimeframe timeframe;
    private final Clock clock;

    public TrainingSession(User user, Exercise exercise, int reps, Instant start, Instant end, Clock clock) {
        this.user = user;
        this.exercise = exercise;
        this.reps = new RepsCount(reps);
        this.timeframe = new SessionTimeframe(start, end);
        this.clock = clock;

        if (this.reps.value() > exercise.getExerciseMaxRepsAllowedPerMinute()) {
            throw new FraudulentSessionException("Registered exercise max reps are greater than exercise max reps allowed per minute");
        }
    }

    // MARK: - Getters
    public User getUser() {
        return user;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getReps() {
        return reps.value();
    }

    public Instant getStart() {
        return timeframe.start();
    }

    public Instant getEnd() {
        return timeframe.end();
    }

    public Clock getClock() {
        return clock;
    }
}
