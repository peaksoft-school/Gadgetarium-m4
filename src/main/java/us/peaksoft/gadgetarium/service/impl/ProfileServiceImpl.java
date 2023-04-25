package us.peaksoft.gadgetarium.service.impl;

import com.amazonaws.services.acmpca.model.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import springfox.documentation.swagger2.mappers.ModelMapper;
import us.peaksoft.gadgetarium.dto.ProfileDto;
import us.peaksoft.gadgetarium.entity.Profile;
import us.peaksoft.gadgetarium.repository.ProfileRepository;
import us.peaksoft.gadgetarium.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ModelMapper modelMapper;
    public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ProfileDto getProfile(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));
        return modelMapper.map(profile, ProfileDto.class);
    }
    @Override
    public ProfileDto updateProfile(Long id, ProfileDto profileDto) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profile", "id", id));
        // Update profile fields
        profile.setFirstName(profileDto.getFirstName());
        profile.setLastName(profileDto.getLastName());
        profile.setEmail(profileDto.getEmail());
        profile.setPhone(profileDto.getPhone());
        profile.setDeliveryAddress(profileDto.getDeliveryAddress());
        profile.setOldPassword(profileDto.getOldPassword());
        profile.setNewPassword(profileDto.getNewPassword());
        Profile updatedProfile = profileRepository.save(profile);
        return modelMapper.map(updatedProfile, ProfileDto.class);
    }
}