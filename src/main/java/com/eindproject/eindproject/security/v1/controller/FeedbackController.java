package com.eindproject.eindproject.security.v1.controller;

import com.eindproject.eindproject.security.v1.model.Feedback;
import com.eindproject.eindproject.security.v1.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping(value = "")
    public ResponseEntity<Object> saveFeedback(@RequestBody Feedback feedback) {
        Long newFeedback = feedbackService.saveFeedback(feedback);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newFeedback).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("")
    public ResponseEntity<Object> getAllFeedback() {
        return ResponseEntity.ok().body(feedbackService.getAllFeedback());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFeedback(@PathVariable("id") Long id, @RequestBody Feedback feedback) {
        feedbackService.updateFeedback(id, feedback);
        return ResponseEntity.ok("Feedback updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFeedback(@PathVariable("id") Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok("Feedback deleted");
    }

}
