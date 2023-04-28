package us.peaksoft.gadgetarium.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import us.peaksoft.gadgetarium.dto.ProfileRequest;
import us.peaksoft.gadgetarium.dto.ProfileResponse;
import us.peaksoft.gadgetarium.dto.ProfileSimpleResponse;
import us.peaksoft.gadgetarium.service.ProfileService;
import us.peaksoft.gadgetarium.service.impl.ProfileServiceImpl;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    private ProfileServiceImpl profileServiceImpl;
    @PutMapping("update")
    public ProfileSimpleResponse updateProfile(@RequestParam Long id, @RequestBody ProfileRequest profileRequest) {

       return profileService.updateProfile(id, profileRequest);
    }

    @GetMapping("{id}")
    @Operation(description = "All users information by id")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable("id") Long id) {
        ProfileResponse profileResponse = profileServiceImpl.getProfile(id);
        return ResponseEntity.ok(profileResponse);
    }



}

