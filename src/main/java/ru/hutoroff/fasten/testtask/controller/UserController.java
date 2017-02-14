package ru.hutoroff.fasten.testtask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.hutoroff.fasten.testtask.jpa.model.UserEntity;
import ru.hutoroff.fasten.testtask.service.MapperService;
import ru.hutoroff.fasten.testtask.service.UserService;
import ru.hutoroff.fasten.testtask.service.data.UserData;

/**
 * Created by hutoroff on 15.02.17.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private MapperService mapperService;

    @Autowired
    public UserController(UserService userService, MapperService mapperService) {
        this.userService = userService;
        this.mapperService = mapperService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody UserData userToAdd) {
        if(userToAdd.getEmail() == null || userToAdd.getPassword() == null) {
            LOG.warn("Bad attempt to create user with email {} and password {}", userToAdd.getEmail(), userToAdd.getPassword());
            return ResponseEntity.badRequest().build();
        }
        if(isUserExist(userToAdd.getEmail())) {
            LOG.warn("Attempt to add already existing user with email {}", userToAdd.getEmail());
            return ResponseEntity.badRequest().body("User already exists");
        }

        UserEntity userEntityToAdd = mapperService.toUserEntity(userToAdd);
        Integer newID = userService.saveUser(userEntityToAdd);
        if(newID != null)
            return ResponseEntity.ok(newID.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestBody String email) {
        if(email == null) {
            LOG.warn("Bad attempt to delete user with no email provide");
            return ResponseEntity.badRequest().build();
        }
        if(!isUserExist(email)) {
            LOG.warn("Attempt to delete not existing user with email {}", email);
            return ResponseEntity.badRequest().body("User not exists");
        }

        UserEntity deletedUser = userService.deleteUser(email);
        if(deletedUser != null)
            return ResponseEntity.ok().build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User was not deleted");
    }

    private boolean isUserExist(String email) {
        if(email == null)
            return false;

        return userService.getUserByEmail(email) != null;
    }
}
