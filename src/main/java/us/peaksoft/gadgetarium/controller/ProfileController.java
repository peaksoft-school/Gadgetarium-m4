package us.peaksoft.gadgetarium.controller;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import us.peaksoft.gadgetarium.dto.ChangePasswordRequest;
import us.peaksoft.gadgetarium.dto.ProfileRequest;
import us.peaksoft.gadgetarium.dto.ProfileResponse;
import us.peaksoft.gadgetarium.dto.ProfileSimpleResponse;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.service.ProfileService;
import us.peaksoft.gadgetarium.entity.User;
import us.peaksoft.gadgetarium.entity.Address;

@RestController

@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserRepository userRepository;
    @Operation(description = "Обновление доступных данных профиля")
    @PutMapping()

    public ProfileSimpleResponse updateProfile(@RequestParam String email, @RequestBody ProfileRequest profileRequest) {
       return profileService.updateProfile(email, profileRequest);
    }
    @Operation(description = "Обновление пароля")
    @PostMapping("change-password")
    public ProfileSimpleResponse changePassword(@RequestParam String email, @RequestBody ChangePasswordRequest changePasswordRequest) {
        return profileService.changePassword(email, changePasswordRequest);
    }
    @Operation(description = "Получить данные профиля пользователя")
    @GetMapping
    public ResponseEntity<ProfileResponse> getProfile(@RequestParam String email) {
        User user1 = userRepository.findByEmail(email);
        Address address = new Address();
        if (user1 == null) {
            return ResponseEntity.notFound().build();
        }
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setId(user1.getId());
        profileResponse.setFirstName(user1.getFirstName());
        profileResponse.setLastName(user1.getLastName());
        profileResponse.setEmail(user1.getEmail());
        profileResponse.setAddress(address.getStreetName());
        profileResponse.setPhoneNumber(user1.getPhoneNumber());

        return ResponseEntity.ok(profileResponse);
    }

}

