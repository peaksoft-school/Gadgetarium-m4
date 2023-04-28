package us.peaksoft.gadgetarium.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.ProfileRequest;
import us.peaksoft.gadgetarium.dto.ProfileSimpleResponse;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.entity.User;
    @Service
    public class ProfileService {
        @Autowired
        private UserRepository userRepository;
        public ProfileSimpleResponse updateProfile(Long id, ProfileRequest profileRequest) {
            User user = userRepository.findById(id).get();
            if (user == null) {

                return new ProfileSimpleResponse("Username not found");
            }
            if (!user.getPassword().equals(profileRequest.getOldPassword())) {
                return new ProfileSimpleResponse("Current Password incorrect");

            }

            user.setFirstName(profileRequest.getFirstName());
            user.setLastName(profileRequest.getLastName());
            user.setEmail(profileRequest.getEmail());
            user.setAddress(profileRequest.getAddress());
            user.setPhoneNumber(profileRequest.getPhoneNumber());
            user.setPassword(profileRequest.getNewPassword());
            userRepository.save(user);
                return new ProfileSimpleResponse("success");
        }
    }


