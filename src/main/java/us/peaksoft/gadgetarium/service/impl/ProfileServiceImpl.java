package us.peaksoft.gadgetarium.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.service.ProfileService;
import us.peaksoft.gadgetarium.dto.*;
import us.peaksoft.gadgetarium.entity.*;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.repository.AddressRepository;
import us.peaksoft.gadgetarium.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private boolean checkOldPassword(String email, String oldPassword) {
        User user = userRepository.findByEmail(email).get();
        String encodedPassword = user.getPassword();
        return passwordEncoder.matches(oldPassword, encodedPassword);
    }

    private String PassToHash(CharSequence password) {
        return passwordEncoder.encode(password);
    }
    public String getAddressForUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        String result;
        Address address = user.getAddress();
        if (address == null) {
            result = "Not specified.";
            return result;
        }
        String country = address.getCountryName();
        String state = address.getStateName();
        String city = address.getCityName();
        String street = address.getStreetName();
        String index = address.getPostalCode();
        result = country+" "+state+" "+city+" "+" "+street+" "+index;
        return result;
    }
    public SimpleResponse update(ProfileRequest profileRequest,Long id) {
        SimpleResponse updateResponse = new SimpleResponse();
        User user = userRepository.findById(id).get();
        Address address = user.getAddress();;
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
        updatedAddress.setId(user.getId());
        updatedAddress.setUser(user);
        addressRepository.save(updatedAddress);
        user.setAddress(updatedAddress);
        user.setFirstName(profileRequest.getFirstName());
        user.setLastName(profileRequest.getLastName());
        user.setEmail(profileRequest.getEmail());
        user.setPhoneNumber(profileRequest.getPhoneNumber());
        userRepository.save(user);
        updateResponse.setHttpStatus(HttpStatus.OK);
        updateResponse.setMessage("User Details for id["+user.getId()+"] updated");
        return updateResponse;
    }

    public SimpleResponse changePassword (ProfileChangePasswordRequest passwordRequest, Long id) {

        SimpleResponse updateResponse = new SimpleResponse();
        User user = userRepository.findById(id).get();
        if (!passwordRequest.getNewPassword().equals(passwordRequest.getConfirmPassword())) {
            updateResponse.setHttpStatus(HttpStatus.NOT_MODIFIED);
            updateResponse.setMessage("New password & Confirmation password do not match.");
            return updateResponse;
        }
        if (!checkOldPassword(user.getEmail(), passwordRequest.getOldPassword())) {
                updateResponse.setHttpStatus(HttpStatus.NOT_MODIFIED);
                updateResponse.setMessage("The old password was entered incorrectly.");
                return updateResponse;
        }
        user.setPassword(PassToHash(passwordRequest.getNewPassword()));
        userRepository.save(user);
        updateResponse.setHttpStatus(HttpStatus.OK);
        updateResponse.setMessage("Password for id["+user.getId()+"] updated");
        return updateResponse;
    }
    @Override
    public ResponseEntity<?> getById(Long id) {

        User user = userRepository.findById(id).get();
        SimpleResponse response =  new SimpleResponse();
        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setFirstName(user.getFirstName());
        profileResponse.setLastName(user.getLastName());
        profileResponse.setEmail(user.getEmail());
        profileResponse.setAddress(getAddressForUser(user.getId()));
        profileResponse.setPhoneNumber(user.getPhoneNumber());
        response.setHttpStatus(HttpStatus.OK);
        return ResponseEntity
                .status(response.getHttpStatus())
                .body(profileResponse);
    }
}
