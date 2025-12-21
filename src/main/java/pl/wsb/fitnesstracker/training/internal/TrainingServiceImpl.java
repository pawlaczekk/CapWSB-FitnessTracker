package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of TrainingProvider.
 * Provides business logic for training operations including retrieval and filtering.
 */
@Service
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    /**
     * Retrieves a training by its ID.
     * 
     * @param trainingId the ID of the training to retrieve
     * @return Optional containing the training if found, empty otherwise
     */
    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    /**
     * Retrieves all trainings from the database.
     * 
     * @return list of all trainings
     */
    List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Retrieves all trainings for a specific user.
     * 
     * @param userId the ID of the user whose trainings to retrieve
     * @return list of trainings belonging to the specified user
     */
    List<Training> findTrainingsByUserId(Long userId) {
        return trainingRepository.findAll().stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .toList();
    }
}
