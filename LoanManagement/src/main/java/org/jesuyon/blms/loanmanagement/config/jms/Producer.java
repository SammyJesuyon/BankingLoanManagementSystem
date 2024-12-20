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
        try {
            jmsTemplate.convertAndSend(queueName, message);
        } catch (Exception e) {
            System.err.println("Error sending notification to queue " + queueName + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
