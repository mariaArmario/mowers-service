package com.martorell.mowers.service.controller;

import com.martorell.mowers.service.service.MowerService;
import com.martorell.mowers.service.service.exceptions.MowerRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
public class MowerController {

    @Autowired
    private MowerService service;

    @GetMapping(value = "/mowers/location", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getMowerLocation(@RequestParam String position, @RequestParam String movement){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getMowerLocation(position, movement));
        } catch (MowerRequestException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}
