package us.peaksoft.gadgetarium.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.ProfileDto;
import us.peaksoft.gadgetarium.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id) {
        ProfileDto profileDto = profileService.getProfile(id);
        return ResponseEntity.ok(profileDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable Long id, @RequestBody ProfileDto profileDto) {
        ProfileDto updatedProfileDto = profileService.updateProfile(id, profileDto);
        return ResponseEntity.ok(updatedProfileDto);
    }
}
