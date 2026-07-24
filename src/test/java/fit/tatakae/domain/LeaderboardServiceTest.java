package fit.tatakae.domain;

import fit.tatakae.domain.ports.SessionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Leaderboard Service")
public class LeaderboardServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private LeaderboardService leaderboardService;

    @Test
    public void shouldReturnRankingOrderedByRepsDescending() {
        //Arrange
        User user1 = new User("1", "User 1" , "cl", PrivacyLevel.PUBLIC);
        User user2 = new User("2", "User 2" , "us", PrivacyLevel.PUBLIC);
        User user3 = new User("3", "User 3" , "br", PrivacyLevel.PUBLIC);

        Exercise exercise = Exercise.PULL_UP;
        Instant dateExecuted = Instant.parse("2026-07-22T10:00:00Z");
        Instant start = dateExecuted;
        Instant end = start.plusSeconds(60);
        Clock clock = Clock.fixed(dateExecuted, ZoneOffset.UTC);

        TrainingSession trainingSession1 = new TrainingSession(user1, exercise, 20, start, end, clock);
        TrainingSession trainingSession2 = new TrainingSession(user2, exercise, 27, start, end, clock);
        TrainingSession trainingSession3 = new TrainingSession(user3, exercise, 35, start, end, clock);

        when(sessionRepository.getAll()).thenReturn(List.of(trainingSession1, trainingSession2, trainingSession3));

        //Act
        List<TrainingSession> ranking = leaderboardService.getGlobalRanking(exercise);

        //Assert
        assertEquals(trainingSession3, ranking.get(0));
        assertEquals(trainingSession2, ranking.get(1));
        assertEquals(trainingSession1, ranking.get(2));
        Mockito.verify(sessionRepository, Mockito.times(1)).getAll();
    }
}
