package org.jesuyon.blms.usermanagement.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {

    @JmsListener(destination = "${spring.listener.queue}")
    public void receiveMessage(String message) {
        System.out.println("Received message{" + message + "}");
    }
}
