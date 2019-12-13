package com.capgemini.rhf.timer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * {@link RouteBuilder} Use of Spring Dependency Injection.
 */
@Service
public class TimerRoute extends RouteBuilder {

    /**
     * {@code timer.period} definido en {@code src/main/resources/application.properties}
     */
    @Value("timer.period")
    String period = "5s";

    /**
     * Injection del Bean
     */
    @Autowired
    Counter counter;

    @Override
    public void configure() throws Exception {
        fromF("timer:capgemini_rhforum?period=%s", period)
                .setBody(() -> "Contador: " + counter.increment())
                .to("log:example?showExchangePattern=false&showBodyType=false");
    }
}
