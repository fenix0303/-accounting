package ua.sych.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.sych.dto.request.UserRequest;
import ua.sych.dto.response.DataResponse;
import ua.sych.dto.response.UserResponse;
import ua.sych.entity.User;
import ua.sych.exception.WrongInputDataException;
import ua.sych.repository.UserRepository;
import ua.sych.utils.ObjectMapperUtils;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.nio.file.Paths.get;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

      public void save(UserRequest userRequest) {
        User user = new User();
        user.setLogin(userRequest.getLogin());
        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
    }

    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return ObjectMapperUtils.mapAll(users, UserResponse.class);
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("User with id [" + id + "] not found"));

        return new UserResponse(user);
    }

    public void update(Long id, UserRequest userRequest) {
        User userByDB = userRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("User with " + id + " not found"));
        userByDB.setLogin(userRequest.getLogin());
        userByDB.setPassword(userRequest.getPassword());
        userRepository.save(userByDB);
    }

    public void delete(Long id) {
        User userByDB = userRepository.findById(id)
                .orElseThrow(() -> new WrongInputDataException("User with " + id + " not found"));
        userRepository.delete(userByDB);
    }

    @Transactional
    public UserResponse saveWithImage(UserRequest userRequest, MultipartFile file) throws IOException {
        User user = userRequestToUser(userRequest);
        String[] arrayForGetExpansion = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String expansion = arrayForGetExpansion[arrayForGetExpansion.length - 1];
        String nameOfFile = user.getLogin() + "." + expansion;
        String fullPath = "src/main/resources/static/" + nameOfFile;
        user.setUrlToPicture(nameOfFile);
        user = userRepository.save(user);
        Files.copy(file.getInputStream(), get(fullPath), StandardCopyOption.REPLACE_EXISTING);
        return new UserResponse(user);
    }

    private User userRequestToUser(UserRequest userRequest) {
        User user = new User();
        user.setLogin(userRequest.getLogin());
        user.setPassword(userRequest.getPassword());
        return user;
    }

    public DataResponse<UserResponse> findAllWithImage() {
        return new DataResponse<>(userRepository.findAll().stream().map(UserResponse::new).collect(Collectors.toList()));
    }
}
