package com.example.customerservice.service;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public Exception decode(String methodKey, Response response) {


        switch (response.status()){
            case 400:
                logger.error("Status code " + response.status() + ", methodKey = " + methodKey);
            case 404:
            {
                logger.error("Error took place when using Feign client to send HTTP Request. Status code " + response.status() + ", methodKey = " + methodKey);
                return new ResponseStatusException(HttpStatus.valueOf(response.status()), "<You can add error message description here>");
            }
            default:
                return new Exception(response.reason());
        }
    }
}
