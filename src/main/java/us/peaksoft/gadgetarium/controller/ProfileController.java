package us.peaksoft.gadgetarium.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import us.peaksoft.gadgetarium.dto.ProfileRequest;
import us.peaksoft.gadgetarium.dto.ProfileResponse;
import us.peaksoft.gadgetarium.dto.ProfileSimpleResponse;
import us.peaksoft.gadgetarium.service.ProfileService;

@RestController
@RequestMapping("/public/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PutMapping("/update")
    public ProfileSimpleResponse updateProfile(@RequestParam String email, @RequestBody ProfileRequest profileRequest) {
        return profileService.updateProfile(email, profileRequest);
    }

    @GetMapping("/profile")
    ResponseEntity<ProfileResponse> getProfile() {
        return null;
    }


}

