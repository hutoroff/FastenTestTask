package ru.hutoroff.fasten.testtask.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.hutoroff.fasten.testtask.jpa.model.TokenEntity;
import ru.hutoroff.fasten.testtask.service.data.request.RequestMessage;
import ru.hutoroff.fasten.testtask.service.data.response.ErrorResponseMessage;
import ru.hutoroff.fasten.testtask.service.data.response.ResponseMessage;
import ru.hutoroff.fasten.testtask.service.data.response.SuccessResponseMessage;
import ru.hutoroff.fasten.testtask.service.security.AuthResult;
import ru.hutoroff.fasten.testtask.service.security.AuthenticationResponse;
import ru.hutoroff.fasten.testtask.service.security.AuthenticationService;
import ru.hutoroff.fasten.testtask.service.security.ConstSecurity;

/**
 * Created by hutoroff on 06.02.17.
 */
@Controller
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/auth")
    //@SendTo("/topic/auth/result")
    public void authUser(SimpMessageHeaderAccessor headerAccessor, @Payload RequestMessage authMessage) {
        if(authMessage.getData() == null)
            LOG.error("No data was received in message with seq_id: {}", authMessage.getSequenceId());

        String sessionId = headerAccessor.getMessageHeaders().get(SimpMessageHeaderAccessor.SESSION_ID_HEADER).toString();

        AuthenticationResponse authenticationResponse = authService.authenticate(authMessage.getData().getEmail(), authMessage.getData().getPassword());
        ResponseMessage responseMessage = prepareResponse(authenticationResponse, authMessage);

        template.convertAndSendToUser(sessionId,"/topic/auth/result", responseMessage, this.createHeaders(sessionId));
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }

    private ResponseMessage prepareResponse(AuthenticationResponse authResult, RequestMessage requestMessage) {
        if(authResult == null || requestMessage == null)
            return null;

        switch (authResult.getResult()) {
            case ERROR_USER_NOT_FOUND:
                return failResult(requestMessage);
            case SUCCESS:
                return successResult(requestMessage, authResult.getToken());
            default:
                return null;
        }
    }

    private ResponseMessage failResult(RequestMessage requestMessage) {
        return new ErrorResponseMessage(requestMessage.getSequenceId(),
                ConstSecurity.messagesForCodes.get(AuthResult.ERROR_USER_NOT_FOUND),
                AuthResult.ERROR_USER_NOT_FOUND.toString());
    }

    private ResponseMessage successResult(RequestMessage requestMessage, TokenEntity token) {
        return new SuccessResponseMessage(requestMessage.getSequenceId(), token.getToken(), token.getExpirationDate());
    }
}
