package fit.tatakae.domain;

import fit.tatakae.domain.ports.SessionRepository;

import java.util.Comparator;
import java.util.List;

public class LeaderboardService {
    private final SessionRepository sessionRepository;

    public LeaderboardService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<TrainingSession> getGlobalRanking(Exercise exercise) {
        List<TrainingSession> sessions = sessionRepository.getAll();
        List<TrainingSession> filteredByExerciseSessions = sessions.stream().filter(session -> session.getExercise().equals(exercise)).sorted(Comparator.comparingInt(TrainingSession::getReps)).toList().reversed();
        return filteredByExerciseSessions;
    }
}
