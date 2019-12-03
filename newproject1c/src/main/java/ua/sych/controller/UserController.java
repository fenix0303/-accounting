package ua.sych.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.sych.dto.request.UserRequest;
import ua.sych.dto.response.DataResponse;
import ua.sych.dto.response.UserResponse;
import ua.sych.service.serviceimpl.UserServiceImpl;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequest user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UserResponse> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/image")

    public UserResponse save(@Valid UserRequest userRequest, @RequestParam MultipartFile file) throws IOException {
        return userService.saveWithImage(userRequest, file);
    }

    @GetMapping("/image")
    public DataResponse<UserResponse> get() {
        return userService.findAllWithImage();
    }

    @GetMapping("/{Id}")
    public ResponseEntity<?> getWarehouseById(@PathVariable("Id") Long id) {
        UserResponse userResponse = userService.findById(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUserById(@PathVariable("userId") Long id,
                                            @RequestBody UserRequest user) {
        userService.update(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
