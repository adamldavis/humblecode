package com.humblecode.humblecode;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.config.WebFluxConfigurationSupport;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Configuration
public class WebFluxConfig extends WebFluxConfigurationSupport {

    @Override
    public WebExceptionHandler responseStatusExceptionHandler() {
        return (exchange, ex) -> Mono.create(callback -> {
                    exchange.getResponse().setStatusCode(HttpStatus.I_AM_A_TEAPOT);
                    System.err.println(ex.getMessage());
                    callback.success(null);
                });
    }
}
