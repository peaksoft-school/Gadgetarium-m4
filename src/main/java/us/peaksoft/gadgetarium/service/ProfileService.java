package us.peaksoft.gadgetarium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import us.peaksoft.gadgetarium.dto.ProfileRequest;
import us.peaksoft.gadgetarium.dto.ChangePasswordRequest;
import us.peaksoft.gadgetarium.dto.ProfileSimpleResponse;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.entity.User;
import us.peaksoft.gadgetarium.entity.Address;

@Service
    public class ProfileService {
    @Autowired
     private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean checkOldPassword(String email, String oldPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        String encodedPassword = user.getPassword();
        return passwordEncoder.matches(oldPassword, encodedPassword);
    }
    public String PassToHash (CharSequence newpassword)
    {
        return passwordEncoder.encode(newpassword);
    }
        public ProfileSimpleResponse updateProfile(String email, ProfileRequest profileRequest) {
           Address address = new Address();
            User user = userRepository.findByEmail(email);

            user.setFirstName(profileRequest.getFirstName());
            user.setLastName(profileRequest.getLastName());
            address.setStreetName(profileRequest.getAddress());
            user.setPhoneNumber(profileRequest.getPhoneNumber());
            userRepository.save(user);
                return new ProfileSimpleResponse("Profile updated!");
        }
    public ProfileSimpleResponse changePassword(String email, ChangePasswordRequest changePasswordRequest) {
        // Address address = new Address();
        User user = userRepository.findByEmail(email);

        if (user == null) {

            return new ProfileSimpleResponse("Username not found");
        }
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            return new ProfileSimpleResponse("New password and confirm password do not match");
        }
        if (!checkOldPassword(email, changePasswordRequest.getOldPassword())) {
            return new ProfileSimpleResponse("Old password do not match!!!");
        }
        user.setPassword(changePasswordRequest.getNewPassword());
        userRepository.save(user);
        return new ProfileSimpleResponse("Password updated!");
    }
    }


