package us.peaksoft.gadgetarium.controller;

import java.util.Optional;
import io.jsonwebtoken.ExpiredJwtException;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import us.peaksoft.gadgetarium.entity.*;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.security.JwtService;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.service.ProfileService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/profile")
@Tag(name = "ProfileController", description = "API endpoints for managing profile")
public class ProfileController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ProfileService profileService;

    @Operation (description = "Get all Profile Information for setting")
    @GetMapping("/profile")
    public  ResponseEntity<?> getProfile(@RequestHeader("Authorization") String authorizationHeader) {
        String token = getTokenFromAuthorizationHeader(authorizationHeader);
        User user = getUserFromToken(token);
        return profileService.getProfile(user.getId());
    }

    @Operation(description = "Update profile information from request")
    @PutMapping("/profile")
    public SimpleResponse updateProfile(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ProfileRequest profileRequest) {
        String token = getTokenFromAuthorizationHeader(authorizationHeader);
        User user = getUserFromToken(token);
        return profileService.updateProfile(profileRequest, user.getId());
    }

    @Operation(description = "Changing Profile password from request.")
    @PutMapping("/password")
    public SimpleResponse changePassword(@RequestHeader("Authorization") String authorizationHeader, @RequestBody ProfileChangePasswordRequest passwordRequest) {
        String token = getTokenFromAuthorizationHeader(authorizationHeader);
        User user = getUserFromToken(token);
        return profileService.changePassword(passwordRequest,user.getId());
    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<SimpleResponse> handleExpiredJwtException(ExpiredJwtException ex) {
        SimpleResponse response = new SimpleResponse();
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Invalid token");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String getTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid authorization header");
        }
        return authorizationHeader.substring(7);
    }

    private User getUserFromToken(String token) {
        String email = jwtService.extractUsername(token);
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new IllegalArgumentException("Invalid user from token");
        }
        return userOptional.get();
    }
}
