package ru.hutoroff.fasten.testtask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.UserDao;

/**
 * Created by hutoroff on 06.02.17.
 */
@RestController
@RequestMapping("/")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserDao userDao;

    /*@RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> doAuth(@RequestBody RequestMessage, HttpServletRequest) {

    }*/
}
