package us.peaksoft.gadgetarium.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.ProfileRequest;
import us.peaksoft.gadgetarium.dto.ProfileSimpleResponse;
import us.peaksoft.gadgetarium.repository.UserRepository;
import us.peaksoft.gadgetarium.entity.User;

@Service
@RequiredArgsConstructor
public class ProfileService {
    @Autowired
    private UserRepository userRepository;

    public ProfileSimpleResponse updateProfile(String email, ProfileRequest profileRequest) {
        User user1 = userRepository.findByEmail(email);

        if (user1 == null) {
            throw new RuntimeException("Пользователь не найден");
        }

        if (!user1.getPassword().equals(profileRequest.getOldPassword())) {
            throw new RuntimeException("Старый пароль неверный");
        }

        user1.setFirstName(profileRequest.getFirstName());
        user1.setLastName(profileRequest.getLastName());
        user1.setEmail(profileRequest.getEmail());
        user1.setAddress(profileRequest.getAddress());
        user1.setPhoneNumber(profileRequest.getPhoneNumber());
        user1.setPassword(profileRequest.getNewPassword());

        userRepository.save(user1);

        return new ProfileSimpleResponse("Профиль успешно обновлен");
    }
}