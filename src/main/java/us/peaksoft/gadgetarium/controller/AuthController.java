package us.peaksoft.gadgetarium.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import us.peaksoft.gadgetarium.dto.AuthenticationResponse;
import us.peaksoft.gadgetarium.dto.RegisterRequest;
import us.peaksoft.gadgetarium.service.AuthService;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    public AuthenticationResponse register(@RequestBody RegisterRequest request){
        return  authService.register(request);
    }
}
