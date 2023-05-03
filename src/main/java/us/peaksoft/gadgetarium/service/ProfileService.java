package us.peaksoft.gadgetarium.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.*;
@Service
    public interface ProfileService {
    SimpleResponse updateProfile(ProfileRequest profileRequest, Long id);
    SimpleResponse changePassword(ProfileChangePasswordRequest passwordRequest, Long id);
    ResponseEntity<?> getProfile (Long id);
}


