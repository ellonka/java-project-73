package hexlet.code.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.dto.UserDto;
import hexlet.code.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static hexlet.code.controller.UserController.USER_CONTROLLER_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class TestUtils {
    private final UserDto userForRegistration = new UserDto(
            "test_email@gmail.com",
            "test",
            "testov",
            "12345"
    );

    public UserDto getUserForRegistration() {
        return userForRegistration;
    }
    @Autowired(required = false)
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    public void tearDown() {
        userRepository.deleteAll();
    }

    public ResultActions regDefaultUser() throws Exception {
        return regUser(userForRegistration);
    }

    public ResultActions regUser(UserDto userDto) throws Exception {
        MockHttpServletRequestBuilder request = post(USER_CONTROLLER_PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJson(userDto));
        return perform(request);
    }

    public ResultActions perform(MockHttpServletRequestBuilder request) throws Exception {
        return mockMvc.perform(request);
    }

    private static final ObjectMapper MAPPER = new ObjectMapper().findAndRegisterModules();

    public static String asJson(Object object) throws JsonProcessingException {
        return MAPPER.writeValueAsString(object);
    }

    public static <T> T fromJson(String json, TypeReference<T> to) throws JsonProcessingException {
        return MAPPER.readValue(json, to);
    }

}
