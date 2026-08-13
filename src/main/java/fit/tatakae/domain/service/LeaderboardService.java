package fit.tatakae.domain.service;

import fit.tatakae.domain.entity.Exercise;
import fit.tatakae.domain.entity.PrivacyLevel;
import fit.tatakae.domain.entity.TrainingSession;
import fit.tatakae.domain.repository.SessionRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeaderboardService {

    // Highest reps first; ties are won by the oldest session.
    private static final Comparator<TrainingSession> RANKING_ORDER =
            Comparator.comparingInt(TrainingSession::getReps)
                    .reversed()
                    .thenComparing(TrainingSession::getStart);

    private final SessionRepository sessionRepository;

    public LeaderboardService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<TrainingSession> getGlobalRanking(Exercise exercise) {
        return rank(
                sessionRepository.getAll().stream()
                        .filter(session -> session.getUser().getPrivacyLevel() == PrivacyLevel.PUBLIC)
                        .filter(session -> session.getExercise() == exercise)
        );
    }

    public List<TrainingSession> getLocalRanking(Exercise exercise, String country) {
        return rank(
                sessionRepository.getAll().stream()
                        .filter(session -> session.getUser().getPrivacyLevel() == PrivacyLevel.PUBLIC)
                        .filter(session -> session.getExercise() == exercise)
                        .filter(session -> session.getUser().getCountry().equals(country))
        );
    }

    // Keeps only the best session of each user and sorts the result.
    private List<TrainingSession> rank(Stream<TrainingSession> sessions) {
        return sessions
                .collect(Collectors.toMap(
                        session -> session.getUser().getUserId(),
                        session -> session,
                        LeaderboardService::bestSession))
                .values()
                .stream()
                .sorted(RANKING_ORDER)
                .toList();
    }

    // Resolves which of two sessions of the same user belongs in the ranking.
    private static TrainingSession bestSession(TrainingSession first, TrainingSession second) {
        if (first.getReps() != second.getReps()) {
            return first.getReps() > second.getReps() ? first : second;
        }
        return first.getStart().isAfter(second.getStart()) ? second : first;
    }
}
