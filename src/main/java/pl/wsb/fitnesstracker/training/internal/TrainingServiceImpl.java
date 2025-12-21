package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;

import java.util.List;
import java.util.Optional;

@Service
class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;

    TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    List<Training> findTrainingsByUserId(Long userId) {
        return trainingRepository.findAll().stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .toList();
    }
}
