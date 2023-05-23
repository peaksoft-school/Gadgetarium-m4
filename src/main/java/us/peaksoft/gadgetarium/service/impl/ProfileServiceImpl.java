package us.peaksoft.gadgetarium.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.entity.*;
import us.peaksoft.gadgetarium.exception.NotFoundException;
import us.peaksoft.gadgetarium.repository.*;
import us.peaksoft.gadgetarium.security.JwtService;
import us.peaksoft.gadgetarium.service.ProfileService;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private boolean checkOldPassword(String email, String oldPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User is not found"));
        String encodedPassword = user.getPassword();
        return passwordEncoder.matches(oldPassword, encodedPassword);
    }

    private String PassToHash(CharSequence password) {
        return passwordEncoder.encode(password);
    }

    private String getAddressForUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        String result;
        assert user != null;
        Address address = user.getAddress();
        if (address == null) { result = "User address not specified."; return result;}
        String country = address.getCountryName();
        String state = address.getStateName();
        String city = address.getCityName();
        String street = address.getStreetName();
        String index = address.getPostalCode();
        result = country+" "+state+" "+city+" "+" "+street+" "+index;
        return result;
    }

    public SimpleResponse updateProfile(String header,ProfileRequest profileRequest) {
        SimpleResponse update = new SimpleResponse();
        String token = getTokenFromAuthorizationHeader(header);
        User user = getUserFromToken(token);
        Long user_id = user.getId();
        UserDetails userDetails = userRepository.getReferenceById(user_id);
        Address address = user.getAddress();
        String[] words = profileRequest.getAddress().split(" ");
        String country = words[0];
        String state = words[1];
        String city = words[2];
        String street = words[3];
        String index = words[4];
        Address updatedAddress = address == null ? new Address() : address;
        updatedAddress.setCountryName(country);
        updatedAddress.setStateName(state);
        updatedAddress.setCityName(city);
        updatedAddress.setStreetName(street);
        updatedAddress.setPostalCode(index);
     //   updatedAddress.setId(user.getId());
     //   updatedAddress.setUser(user);
        addressRepository.save(updatedAddress);
        user.setAddress(updatedAddress);
        user.setFirstName(profileRequest.getFirstName());
        user.setLastName(profileRequest.getLastName());
        user.setEmail(profileRequest.getEmail());
        user.setPhoneNumber(profileRequest.getPhoneNumber());
        userRepository.save(user);
        update.setHttpStatus(HttpStatus.OK);
        update.setMessage("Profile information for id["+user.getId()+"] updated");
        return update;
    }

    public SimpleResponse changePassword (String authorizationHeader,ProfileChangePasswordRequest passwordRequest) {
        SimpleResponse changed = new SimpleResponse();
        String token = getTokenFromAuthorizationHeader(authorizationHeader);
        User user = getUserFromToken(token);
        Long user_id = user.getId();
        UserDetails userDetails = userRepository.getReferenceById(user_id);
        if(!(jwtService.isTokenValid(token, userDetails)))
        {
            changed.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            changed.setMessage("Token is not valid");
            return changed;
        }
        if (!passwordRequest.getNewPassword().equals(passwordRequest.getConfirmPassword())) {
            changed.setHttpStatus(HttpStatus.BAD_REQUEST);
            changed.setMessage("New password & Confirmation password do not match.");
            return changed;
        }
        if (!checkOldPassword(user.getEmail(), passwordRequest.getOldPassword())) {
                changed.setHttpStatus(HttpStatus.BAD_REQUEST);
                changed.setMessage("The old password was entered incorrectly.");
                return changed;
        }
        user.setPassword(PassToHash(passwordRequest.getNewPassword()));
        userRepository.save(user);
        changed.setHttpStatus(HttpStatus.OK);
        changed.setMessage("Password for id["+user.getId()+"] updated");
        return changed;
    }

    @Override
    public ResponseEntity<?> getProfile(String header) {
        String token = getTokenFromAuthorizationHeader(header);
        User user = getUserFromToken(token);
        Long user_id = user.getId();
        UserDetails userDetails = userRepository.getReferenceById(user_id);
        if(!jwtService.isTokenValid(token, userDetails))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("token ");
        }
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setFirstName(user.getFirstName());
        profileResponse.setLastName(user.getLastName());
        profileResponse.setEmail(user.getEmail());
        profileResponse.setAddress(getAddressForUser(user.getId()));
        profileResponse.setPhoneNumber(user.getPhoneNumber());
        return ResponseEntity.status(HttpStatus.OK).body(profileResponse);
    }

    private User getUserFromToken(String token) {
        String email = jwtService.extractUsername(token);
        return userRepository.findByEmail(email).
                orElseThrow(() -> new NotFoundException("User is not found"));

    }

    private String getTokenFromAuthorizationHeader(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid authorization header");
        }
        return authorizationHeader.substring(7);
    }
}
