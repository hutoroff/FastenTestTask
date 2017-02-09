package ru.hutoroff.fasten.testtask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.hutoroff.fasten.testtask.jpa.dao.entity.UserDao;
import ru.hutoroff.fasten.testtask.service.data.request.RequestMessage;
import ru.hutoroff.fasten.testtask.service.data.response.ResponseMessage;
import ru.hutoroff.fasten.testtask.service.data.response.SuccessResponseMessage;

import java.util.Date;

/**
 * Created by hutoroff on 06.02.17.
 */
@Controller
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserDao userDao;

    @MessageMapping("/auth")
    @SendTo("/topic/auth/result")
    public ResponseMessage authUser(RequestMessage message) {
        LOG.info("AuthUserCalled!");
        return new SuccessResponseMessage(message.getSequenceId(), "OK", new Date());
    }
}
