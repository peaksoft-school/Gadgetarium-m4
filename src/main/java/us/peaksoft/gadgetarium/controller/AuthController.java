package us.peaksoft.gadgetarium.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.peaksoft.gadgetarium.dto.AuthenticationRequest;
import us.peaksoft.gadgetarium.dto.AuthenticationResponse;
import us.peaksoft.gadgetarium.dto.RegisterRequest;
import us.peaksoft.gadgetarium.service.AuthService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/public")
@Tag(name = "AuthController", description = "API endpoints for managing authentication")
@RequiredArgsConstructor
public class AuthController {
List list=new ArrayList();

    private final AuthService authService;

    @PostMapping("login")
    @Operation(description = "Using username and password, user can sign in")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
        return authService.authenticate(request);
    }

    @PostMapping("register")
    @Operation(description = "All user can sign up")
    public AuthenticationResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
}
