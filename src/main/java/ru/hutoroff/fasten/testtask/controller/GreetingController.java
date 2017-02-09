package ru.hutoroff.fasten.testtask.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.hutoroff.fasten.testtask.service.data.Greeting;
import ru.hutoroff.fasten.testtask.service.data.HelloMessage;

/**
 * Created by hutoroff on 09.02.17.
 */
@Controller
public class GreetingController {
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
}
