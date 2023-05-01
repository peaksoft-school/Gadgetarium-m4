package us.peaksoft.gadgetarium.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.exception.NotFoundException;
import us.peaksoft.gadgetarium.security.JwtService;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.entity.*;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.service.ProfileService;
import us.peaksoft.gadgetarium.dto.ProfileRequest;
import us.peaksoft.gadgetarium.dto.ProfileChangePasswordRequest;

@RestController
@RequestMapping("/api/public/profile")
@Tag(name = "ProfileController", description = "API endpoints for managing profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private JwtService jwts;
    @Autowired
    private UserRepository userRepository;

    @Operation(description = "Profile API | Change Profile Information")
    @PostMapping("update")
    public SimpleResponse update (@RequestHeader("Authorization") String authorizationHeader, @RequestBody ProfileRequest profileRequest) {
        SimpleResponse response = new SimpleResponse();
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setHttpStatus(HttpStatus.UNAUTHORIZED);
            response.setMessage("Invalid authorization header");
            return response;
        }
        String token = authorizationHeader.substring(7);
        if (jwts.tokenExpired(token)) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Invalid token");
            return response;
        }
        User user = userRepository.findByEmail(jwts.extractUsername(token)).get();
        if (user == null) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Invalid user from token");
            return response;
        }
        Long id = user.getId();
       return profileService.update(profileRequest, id);
    }
    @Operation(description = "Profile API | Update password")
    @PostMapping("password")
    public SimpleResponse changePassword(@RequestBody ProfileChangePasswordRequest passwordRequest, @RequestHeader("Authorization") String authorizationHeader) {
        SimpleResponse response = new SimpleResponse();
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Invalid authorization header");
            return response;
        }
        String token = authorizationHeader.substring(7);
        if (jwts.tokenExpired(token)) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Invalid token");
            return response;
        }
        User user = userRepository.findByEmail(jwts.extractUsername(token)).get();
        if (user == null) {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.setMessage("Invalid user from token");
            return response;
        }
        Long id = user.getId();
        return profileService.changePassword(passwordRequest, id);
    }
    @Operation(description = "Profile API | Get User Information")
    @GetMapping("get-info")
    public ResponseEntity<?> getById(@RequestHeader("Authorization") String authorizationHeader)
    {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid authorization header");
        }
        String token = authorizationHeader.substring(7);

        User user = userRepository.findByEmail(jwts.extractUsername(token)).get();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user from Authorization Headers");
        }
        Long id = user.getId();
        return profileService.getById(id);
    }
}

