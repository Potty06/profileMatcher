package com.gameloft.profileMatcher.controller;

import com.gameloft.profileMatcher.model.PlayerProfile;
import com.gameloft.profileMatcher.service.ProfileMatcherService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ProfileMatcherController.java
@RestController
@RequestMapping("/profile-matcher")
public class ProfileMatcherController {

    private final ProfileMatcherService profileMatcherService;

    private final List<PlayerProfile> playerProfiles = new ArrayList<>();

    public ProfileMatcherController(ProfileMatcherService profileMatcherService) {
        this.profileMatcherService = profileMatcherService;
    }

    @PostMapping("/add-profile")
    public ResponseEntity<String> addProfile(@RequestBody PlayerProfile playerProfile) {
        playerProfiles.add(playerProfile);
        return new ResponseEntity<>("Player profile added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get_client_config/{playerId}")
    public ResponseEntity<PlayerProfile> getClientConfig(@PathVariable String playerId) {
        // Call the service to get the client configuration
        PlayerProfile clientConfig = profileMatcherService.getClientConfig(playerId);

        if (clientConfig != null) {
            return new ResponseEntity<>(clientConfig, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

