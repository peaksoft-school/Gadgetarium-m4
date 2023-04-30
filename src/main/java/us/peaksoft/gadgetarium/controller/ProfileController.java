package us.peaksoft.gadgetarium.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.entity.Address;
import us.peaksoft.gadgetarium.entity.User;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.security.JwtService;
import us.peaksoft.gadgetarium.service.ProfileService;
import us.peaksoft.gadgetarium.dto.ProfileRequest;


@RestController

@RequestMapping("/api/profile")
@Tag(name = "ProfileController", description = "API для профиля")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private JwtService jwts;
    @Autowired
    private UserRepository userRepository;

    @Operation(description = "Обновление доступных данных профиля")
    @PutMapping()
    public ProfileSimpleResponse updateProfile(@RequestBody ProfileRequest profileRequest) {
       return profileService.updateProfile(profileRequest);
    }
    @Operation(description = "Обновление пароля")
    @PostMapping("change-password")
    public ProfileSimpleResponse changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return profileService.changePassword(changePasswordRequest);
    }
    @Operation(description = "Получить данные профиля пользователя")
    @GetMapping("{token}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable("token") String token)
    {
        User user1 = userRepository.findByEmail(jwts.extractUsername(token));
        Address address = user1.getAddress();
        if (address == null)
        {
            setAddress = "nenull"
            return ResponseEntity.notFound().build();
        }
        if (user1 == null) {
            return ResponseEntity.notFound().build();
        }
        if(!(profileService.isTokenValid(token))){

            return ResponseEntity.notFound().build();
        }
        if(jwts.isTokenExpired(token))
        {
            return  ResponseEntity.notFound().build();
        }
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setId(user1.getId());
        profileResponse.setFirstName(user1.getFirstName());
        profileResponse.setLastName(user1.getLastName());
        profileResponse.setEmail(user1.getEmail());
        //profileResponse.setAddress(address.getStreetName());
        profileResponse.setPhoneNumber(user1.getPhoneNumber());

        return ResponseEntity.ok(profileResponse);
    }
}

