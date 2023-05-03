package main.com.w1zer.service;

import main.com.w1zer.exception.NotFoundException;
import main.com.w1zer.exception.ProfileAlreadyExistsException;
import main.com.w1zer.model.ProfileResponse;
import main.com.w1zer.model.ProfileRequest;
import main.com.w1zer.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMappingService profileMappingService;

    public ProfileService(ProfileRepository profileRepository, ProfileMappingService profileMappingService) {
        this.profileRepository = profileRepository;
        this.profileMappingService = profileMappingService;
    }

    public List<ProfileResponse> getAll(String login) {
        if (login != null) {
            return profileMappingService.mapToProfileResponseList(profileRepository.getByLogin(login));
        }
        return profileMappingService.mapToProfileResponseList(profileRepository.getAll());
    }

    public ProfileResponse getById(Long id) {
        return profileMappingService.mapToProfileResponse(profileRepository.getById(id));
    }

    private boolean isProfileWithLoginExists(String login) {
        try {
            profileRepository.getByLogin(login);
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    public void insert(ProfileRequest profileRequest) {
        if (isProfileWithLoginExists(profileRequest.getLogin())) {
            throw new ProfileAlreadyExistsException("Profile with this login already exists");
        }
        profileRepository.insert(profileMappingService.mapToProfile(profileRequest));
    }

    public void delete(Long id) {
        profileRepository.getById(id); // Checking if profile exists
        profileRepository.delete(id);
    }

    public void update(Long id, ProfileRequest profileRequest) {
        profileRepository.getById(id); // Checking if profile exists
        profileRepository.update(profileMappingService.mapToProfile(id, profileRequest));
    }
}
