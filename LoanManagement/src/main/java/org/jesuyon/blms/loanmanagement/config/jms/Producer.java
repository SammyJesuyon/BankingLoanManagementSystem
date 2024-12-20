package org.jesuyon.blms.loanmanagement.config.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value(value = "${spring.producer.queue}")
    private String queueName;

    public void sendNotification(String message) {
        jmsTemplate.convertAndSend("queueName", message);
    }
}
