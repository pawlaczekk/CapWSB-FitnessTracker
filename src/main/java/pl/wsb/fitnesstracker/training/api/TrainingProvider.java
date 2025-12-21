package pl.wsb.fitnesstracker.training.api;

import java.util.Optional;

/**
 * Service interface for providing training data.
 * Defines operations for retrieving training information.
 */
public interface TrainingProvider {

    /**
     * Retrieves a training by its ID.
     * 
     * @param trainingId the ID of the training to retrieve
     * @return Optional containing the training if found, empty otherwise
     */
    Optional<Training> getTraining(Long trainingId);

}
