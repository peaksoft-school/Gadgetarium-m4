package us.peaksoft.gadgetarium.service;

import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.ProfileDto;

@Service
public interface ProfileService {
    ProfileDto getProfile(Long id);
    ProfileDto updateProfile(Long id, ProfileDto profileDto);
}