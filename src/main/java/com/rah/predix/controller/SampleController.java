package com.rah.predix.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "v1/sample")
public class SampleController {

    @RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
    public ResponseEntity healthCheck(
        @RequestHeader(value = "Authorization") String authorizationToken,
        @RequestHeader(value = "Tenant") String tenantUuid,
        @RequestHeader(value = "Origin", required = false, defaultValue = "") String originHeader) throws IOException {
        return new ResponseEntity<>("Hello : Sample application is running.", HttpStatus.OK);
    }

}
