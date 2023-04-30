package us.peaksoft.gadgetarium.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import us.peaksoft.gadgetarium.dto.ProfileRequest;
import us.peaksoft.gadgetarium.dto.ChangePasswordRequest;
import us.peaksoft.gadgetarium.dto.ProfileSimpleResponse;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.repository.AddressRepository;
import us.peaksoft.gadgetarium.entity.User;
import us.peaksoft.gadgetarium.entity.Address;
import us.peaksoft.gadgetarium.security.JwtService;


@Service
    public class ProfileService {
    @Autowired
     private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtss;
    public boolean checkOldPassword(String email, String oldPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }
        String encodedPassword = user.getPassword();
        return passwordEncoder.matches(oldPassword, encodedPassword);
    }
    public void updateAddressStreetNameByUserId(Long userId, String streetName) {
        User user = userRepository.findById(userId).get();
         Address address = user.getAddress();
        if (address != null) {
            address.setStreetName(streetName);
            addressRepository.save(address);
        }
    }

    public String PassToHash (CharSequence newpassword)
    {
        return passwordEncoder.encode(newpassword);
    }
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey("4D6251655468566D597133743677397A24432646294A404E635266556A586E5A").parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
        public ProfileSimpleResponse updateProfile(ProfileRequest profileRequest) {

            User user = userRepository.findByEmail(jwtss.extractUsername(profileRequest.getToken()));
            Address address = user.getAddress();
            if (user == null) {

                return new ProfileSimpleResponse("Username not found");
            }

            user.setFirstName(profileRequest.getFirstName());
            user.setLastName(profileRequest.getLastName());
            user.setEmail(profileRequest.getEmail());
            //updateAddressStreetNameByUserId(user.getId(), profileRequest.getStreetName());
            user.setPhoneNumber(profileRequest.getPhoneNumber());
            userRepository.save(user);
                return new ProfileSimpleResponse("Profile updated!");
        }
    public ProfileSimpleResponse changePassword(ChangePasswordRequest changePasswordRequest) {

        User user = userRepository.findByEmail(jwtss.extractUsername(changePasswordRequest.getToken()));

        if (user == null) {

            return new ProfileSimpleResponse("Username not found");
        }
        if(!(isTokenValid(changePasswordRequest.getToken()))){

            return new ProfileSimpleResponse("Token is invalid.");
        }
        if(jwtss.isTokenExpired(changePasswordRequest.getToken()))
        {
            return new ProfileSimpleResponse("Your token is expired.");
        }
        if (!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getConfirmPassword())) {
            return new ProfileSimpleResponse("New password and confirm password do not match");
        }
        if (!checkOldPassword(user.getEmail(), changePasswordRequest.getOldPassword())) {
            return new ProfileSimpleResponse("Old password do not match!!!");
        }
        user.setPassword(PassToHash(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
        return new ProfileSimpleResponse("Password updated!");
    }
    }


