package pl.wsb.fitnesstracker.user.internal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserProvider;
import pl.wsb.fitnesstracker.user.api.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
class UserServiceImpl implements UserService, UserProvider {

    private final UserRepository userRepository;

    UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(final User user) {
        if (user.getId() != null) {
            throw new IllegalArgumentException("User has already DB ID, update is not permitted!");
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final Long userId, final User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));
        
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setBirthdate(user.getBirthdate());
        existingUser.setEmail(user.getEmail());
        
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(final Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUserOverAge(final int age) {
        LocalDate dateLimit = LocalDate.now().minusYears(age);
        return userRepository.findAll().stream()
                .filter(user -> user.getBirthdate() != null && user.getBirthdate().isBefore(dateLimit))
                .toList();
    }

    @Override
    public List<User> getUsersByEmailFragment(final String emailFragment) {
        return userRepository.findByEmailContainingIgnoreCase(emailFragment);
    }

    @Override
    public Optional<User> getUser(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}