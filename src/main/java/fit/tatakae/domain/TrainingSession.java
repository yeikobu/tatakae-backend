package fit.tatakae.domain;

import fit.tatakae.domain.exception.FraudulentSessionException;
import fit.tatakae.domain.exception.InconsistentSessionException;

import java.time.Clock;
import java.time.Instant;

public class TrainingSession {
    private User user;
    private Exercise exercise;
    private int reps;
    private Instant start;
    private Instant end;
    private Clock clock;

    public TrainingSession(User user, Exercise exercise, int reps, Instant start, Instant end, Clock clock) {
        this.user = user;
        this.exercise = exercise;
        this.reps = reps;
        this.start = start;
        this.end = end;
        this.clock = clock;

        if(reps <= 0) {
            throw new InconsistentSessionException("Reps quantity must be greater than 0 to save a training session");
        }

        if(reps > exercise.getExerciseMaxRepsAllowedPerMinute()) {
            throw new FraudulentSessionException("Registered exercise max reps are greater than exercise max reps allowed per minute");
        }

        if(end.isBefore(start)) {
            throw new InconsistentSessionException("End time must be after start time");
        }

        if(start.equals(end)) {
            throw new InconsistentSessionException("Start time can not be equal to end time");
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
        return reps;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public Clock getClock() {
        return clock;
    }
}
