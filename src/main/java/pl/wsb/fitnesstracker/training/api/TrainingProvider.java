package pl.wsb.fitnesstracker.training.api;

import java.util.Optional;

public interface TrainingProvider {

    Optional<Training> getTraining(Long trainingId);

}
