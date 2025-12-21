package pl.wsb.fitnesstracker.user.internal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserSimpleDto> getAllUsers() {
        return userService.findAllUsers()
                .stream()
                .map(userMapper::toSimpleDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUser(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userMapper.toDto(userService.createUser(userMapper.toUser(userDto)));
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userMapper.toDto(userService.updateUser(id, userMapper.toUser(userDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/email")
    public List<UserEmailDto> getUsersByEmail(@RequestParam String emailFragment) {
        return userService.getUsersByEmailFragment(emailFragment)
                .stream()
                .map(userMapper::toEmailDto)
                .toList();
    }

    @GetMapping("/older/{age}")
    public List<UserDto> getUsersOlderThan(@PathVariable int age) {
        return userService.getUserOverAge(age)
                .stream()
                .map(userMapper::toDto)
                .toList();
    }
}

