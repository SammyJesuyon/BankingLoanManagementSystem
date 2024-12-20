package org.jesuyon.blms.loanmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableAspectJAutoProxy
public class LoanManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanManagementApplication.class, args);
    }

}
