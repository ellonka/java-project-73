package hexlet.code.service;

import hexlet.code.dto.UserDto;
import hexlet.code.model.User;

public interface UserService {
    User getUserById(Long id);
    User createUser(UserDto userDto);
    User updateUser(Long id, UserDto userDto);
}
