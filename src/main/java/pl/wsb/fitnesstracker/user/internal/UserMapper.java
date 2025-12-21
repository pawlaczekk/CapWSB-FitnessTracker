package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

/**
 * Mapper for converting between User entity and various User DTOs.
 * Provides conversion methods for different representations of user data.
 */
@Component
public class UserMapper {

    /**
     * Converts User entity to full UserDto with all details.
     * 
     * @param user the user entity to convert
     * @return UserDto containing all user information
     */
    public UserDto toDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    /**
     * Converts User entity to UserSimpleDto with basic information only.
     * 
     * @param user the user entity to convert
     * @return UserSimpleDto containing ID, first name and last name
     */
    UserSimpleDto toSimpleDto(User user) {
        return new UserSimpleDto(user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    /**
     * Converts User entity to UserEmailDto with ID and email only.
     * 
     * @param user the user entity to convert
     * @return UserEmailDto containing ID and email
     */
    UserEmailDto toEmailDto(User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    /**
     * Converts UserDto to User entity.
     * 
     * @param userDto the DTO to convert
     * @return User entity created from DTO data
     */
    User toUser(UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }
}
