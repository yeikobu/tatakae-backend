package fit.tatakae.domain.ports;

import fit.tatakae.domain.TrainingSession;

import java.util.List;

public interface SessionRepository {
    List<TrainingSession> getAll();
}
