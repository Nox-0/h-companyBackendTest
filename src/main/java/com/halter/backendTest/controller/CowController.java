package com.halter.backendTest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.halter.backendTest.models.Cow;
import com.halter.backendTest.models.requests.NewCowRequest;
import com.halter.backendTest.repository.CowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CowController {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private CowRepository cowRepository;

    @GetMapping("/cows")
    public ResponseEntity<String> getAllCows() throws JsonProcessingException {
        List<Cow> allCows = cowRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(allCows));
    }

    @PostMapping("/cows")
    public ResponseEntity<String> createCow(@RequestBody NewCowRequest newCowRequest) throws JsonProcessingException {
        Cow newCow = new Cow(newCowRequest);
        cowRepository.save(newCow);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.writeValueAsString(newCow));
    }

    @PutMapping("/cows/{cowId}")
    public ResponseEntity<String> updateCow(@PathVariable int cowId, @RequestBody NewCowRequest cowInfo) throws JsonProcessingException {
        Cow existingCow = cowRepository.findById(cowId);
        if (existingCow == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        Cow updatedCow = new Cow(cowInfo);
        updatedCow.setId(cowId);
        cowRepository.save(updatedCow);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(mapper.writeValueAsString(updatedCow));
    }
}
