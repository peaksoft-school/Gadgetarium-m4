package us.peaksoft.gadgetarium.controller;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.service.ProfileService;

@RestController
@RequestMapping("api/profile")
@Tag(name = "ProfileController", description = "API endpoints for managing profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @Operation (description = "Get all Profile Information for setting")
    @GetMapping("/my-profile")
    public  ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authorizationHeader) {
        return profileService.getProfile(authorizationHeader);
    }

    @Operation(description = "Update profile information from request")
    @PutMapping("/my-profile/update")
    public SimpleResponse updateProfile(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ProfileRequest profileRequest) {
        return profileService.updateProfile(authorizationHeader,profileRequest);
    }

    @Operation(description = "Changing Profile password from request.")
    @PutMapping("/my-profile/change-password")
    public SimpleResponse changePassword(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ProfileChangePasswordRequest passwordRequest) {
        return profileService.changePassword(authorizationHeader,passwordRequest);
    }

}
