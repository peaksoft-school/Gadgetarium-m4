package us.peaksoft.gadgetarium.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import us.peaksoft.gadgetarium.dto.*;

@Service
    public interface ProfileService {
    SimpleResponse updateProfile(String header, ProfileRequest profileRequest);

    SimpleResponse changePassword(String header,ProfileChangePasswordRequest passwordRequest);

    ResponseEntity<?> getProfile(String header);
}


