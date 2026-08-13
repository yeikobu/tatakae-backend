package fit.tatakae.application.usecase;

import fit.tatakae.domain.entity.Exercise;
import fit.tatakae.domain.entity.TrainingSession;
import fit.tatakae.domain.service.LeaderboardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetLeaderboardUseCaseTest {

    @Mock
    private LeaderboardService leaderboardService;

    @InjectMocks
    private GetLeaderboardUseCase useCase;

    @Test
    public void shouldDelegateGlobalRankingToLeaderboardService() {
        // Arrange
        List<TrainingSession> expected = List.of();
        when(leaderboardService.getGlobalRanking(Exercise.PULL_UP)).thenReturn(expected);

        // Act
        List<TrainingSession> ranking = useCase.executeGlobal(Exercise.PULL_UP);

        // Assert
        assertEquals(expected, ranking);
        verify(leaderboardService, times(1)).getGlobalRanking(Exercise.PULL_UP);
    }

    @Test
    public void shouldDelegateCountryRankingToLeaderboardService() {
        // Arrange
        List<TrainingSession> expected = List.of();
        when(leaderboardService.getLocalRanking(Exercise.PULL_UP, "cl")).thenReturn(expected);

        // Act
        List<TrainingSession> ranking = useCase.executeByCountry(Exercise.PULL_UP, "cl");

        // Assert
        assertEquals(expected, ranking);
        verify(leaderboardService, times(1)).getLocalRanking(Exercise.PULL_UP, "cl");
    }
}
