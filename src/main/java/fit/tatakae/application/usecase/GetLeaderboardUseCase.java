package fit.tatakae.application.usecase;

import fit.tatakae.domain.entity.Exercise;
import fit.tatakae.domain.entity.TrainingSession;
import fit.tatakae.domain.service.LeaderboardService;

import java.util.List;

public class GetLeaderboardUseCase {
    private final LeaderboardService leaderboardService;

    public GetLeaderboardUseCase(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    public List<TrainingSession> executeGlobal(Exercise exercise) {
        return leaderboardService.getGlobalRanking(exercise);
    }

    public List<TrainingSession> executeByCountry(Exercise exercise, String country) {
        return leaderboardService.getLocalRanking(exercise, country);
    }
}
