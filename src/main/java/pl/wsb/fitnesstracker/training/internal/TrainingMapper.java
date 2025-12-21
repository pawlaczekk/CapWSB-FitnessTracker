package pl.wsb.fitnesstracker.training.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingDto;
import pl.wsb.fitnesstracker.user.internal.UserMapper;

/**
 * Mapper for converting between Training entity and TrainingDto.
 * Uses UserMapper to convert nested user information.
 */
@Component
class TrainingMapper {

    private final UserMapper userMapper;

    TrainingMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Converts Training entity to TrainingDto with full details.
     * Includes conversion of the associated user to UserDto.
     * 
     * @param training the training entity to convert
     * @return TrainingDto containing all training information including user details
     */
    TrainingDto toDto(Training training) {
        return new TrainingDto(
                training.getId(),
                userMapper.toDto(training.getUser()),
                training.getStartTime(),
                training.getEndTime(),
                training.getActivityType(),
                training.getDistance(),
                training.getAverageSpeed()
        );
    }
}
