package hexlet.code.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import hexlet.code.config.SpringConfigForTests;
import hexlet.code.dto.UserDto;
import hexlet.code.model.User;
import hexlet.code.repository.UserRepository;
import hexlet.code.utils.TestUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static hexlet.code.controller.UserController.ID;
import static hexlet.code.controller.UserController.USER_CONTROLLER_PATH;
import static hexlet.code.utils.TestUtils.asJson;
import static hexlet.code.utils.TestUtils.fromJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static hexlet.code.config.SpringConfigForTests.TEST_PROFILE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles(TEST_PROFILE)
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringConfigForTests.class)
public class UserControllerTests {
    private final UserDto userForTestGetAll = new UserDto(
            "ivan_email@gmail.com",
            "ivan",
            "votchinkov",
            "54321"
    );

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestUtils testUtils;

    @AfterEach
    public void clear() {
        testUtils.tearDown();
    }

    @Test
    public void testGetUserById() throws Exception {
        testUtils.regDefaultUser();
        User expectedUser = userRepository.findAll().get(0);

        MockHttpServletResponse response = testUtils.perform(
                get(USER_CONTROLLER_PATH + ID, expectedUser.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        User user = fromJson(response.getContentAsString(), new TypeReference<>() {
        });

        assertEquals(expectedUser.getId(), user.getId());
        assertEquals(expectedUser.getEmail(), user.getEmail());
        assertEquals(expectedUser.getFirstName(), user.getFirstName());
        assertEquals(expectedUser.getLastName(), user.getLastName());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        testUtils.regDefaultUser();
        testUtils.regUser(userForTestGetAll);

        MockHttpServletResponse response = testUtils.perform(
                        get(USER_CONTROLLER_PATH))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        List<User> users = fromJson(response.getContentAsString(), new TypeReference<>() {
        });

        assertThat(users).hasSize(2);
    }

    @Test
    public void testUpdateUser() throws Exception {
        testUtils.regDefaultUser();
        User crestedUser = userRepository.findAll().get(0);

        UserDto userForTestUpdate = new UserDto(
                "ivan_email@gmail.com",
                "ivan",
                "testov",
                "12345");

        MockHttpServletResponse response = testUtils.perform(
                        put(USER_CONTROLLER_PATH + ID, crestedUser.getId())
                                .content(asJson(userForTestUpdate))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        User user = fromJson(response.getContentAsString(), new TypeReference<>() {
        });

        assertEquals(userForTestUpdate.getEmail(), user.getEmail());
        assertEquals(userForTestUpdate.getFirstName(), user.getFirstName());
        assertNull(userRepository.findByEmail("test_email@gmail.com").orElse(null));
    }
}
