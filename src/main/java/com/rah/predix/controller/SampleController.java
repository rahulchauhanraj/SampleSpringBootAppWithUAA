package com.rah.predix.controller;

import com.rah.predix.models.DataObjectRequest;
import com.rah.predix.models.DataObjectResponse;
import com.rah.predix.service.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import javax.inject.Inject;

@RestController
@RequestMapping(value = "v1/sample")
public class SampleController {

    @Inject SampleService sampleService;

    @RequestMapping(value = "/healthCheck", method = RequestMethod.GET)
    public ResponseEntity healthCheck(
        @RequestHeader(value = "Authorization") String authorizationToken,
        @RequestHeader(value = "Tenant") String tenantUuid,
        @RequestHeader(value = "Origin", required = false, defaultValue = "") String originHeader) throws IOException {
        return new ResponseEntity<>("Hello : Sample application is running.", HttpStatus.OK);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.GET)
    public ResponseEntity<DataObjectResponse> getDataObject (
        @RequestHeader(value = "Authorization") String authorizationToken,
        @RequestHeader(value = "Tenant") String tenantUuid,
        @PathVariable String key) {
        return new ResponseEntity<>(sampleService.getDataObject(authorizationToken, tenantUuid, key), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<DataObjectResponse> addDataObject (
        @RequestHeader(value = "Authorization") String authorizationToken,
        @RequestHeader(value = "Tenant") String tenantUuid,
        @RequestBody DataObjectRequest request) {
        return new ResponseEntity<>(sampleService.createDataObject(authorizationToken, tenantUuid, request), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.PATCH)
    public ResponseEntity updateDataObject (
        @RequestHeader(value = "Authorization") String authorizationToken,
        @RequestHeader(value = "Tenant") String tenantUuid,
        @PathVariable String key,
        @RequestBody DataObjectRequest request) {
        return new ResponseEntity<>(sampleService.updateDataObject(authorizationToken, tenantUuid, key, request), HttpStatus.OK);
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.DELETE)
    public ResponseEntity<DataObjectResponse> deleteDataObject (
        @RequestHeader(value = "Authorization") String authorizationToken,
        @RequestHeader(value = "Tenant") String tenantUuid,
        @PathVariable String key) {
        sampleService.deleteDataObject(authorizationToken, tenantUuid, key);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
