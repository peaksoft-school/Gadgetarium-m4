package us.peaksoft.gadgetarium.service.impl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.entity.User;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.service.ProfileService;
import us.peaksoft.gadgetarium.dto.ProfileResponse;
import us.peaksoft.gadgetarium.dto.ProfileRequest;

@Service
public class ProfileServiceImpl extends ProfileService {
    private final UserRepository userRepository;

    public ProfileServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ProfileResponse getProfile(User user) {
        User user1 = userRepository.findById(user.getId()).get();
        if (user1 == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        ProfileResponse profileResponse = new ProfileResponse();
        profileResponse.setFirstName(user1.getFirstName());
        profileResponse.setLastName(user1.getLastName());
        profileResponse.setEmail(user1.getEmail());
        profileResponse.setDeliveryAddress(user1.getAddress().getStreetName());
        profileResponse.setPhoneNumber(user1.getPhoneNumber());

        return profileResponse;
    }
}