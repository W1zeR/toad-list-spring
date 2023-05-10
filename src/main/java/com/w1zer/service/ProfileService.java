package com.w1zer.service;

import com.w1zer.entity.Profile;
import com.w1zer.exception.NotFoundException;
import com.w1zer.model.ProfileResponse;
import com.w1zer.exception.ProfileAlreadyExistsException;
import com.w1zer.model.ProfileRequest;
import com.w1zer.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMappingService profileMappingService;

    public ProfileService(ProfileRepository profileRepository, ProfileMappingService profileMappingService) {
        this.profileRepository = profileRepository;
        this.profileMappingService = profileMappingService;
    }

    public List<ProfileResponse> getAll(String login) {
        if (login == null) {
            return profileMappingService.mapToProfileResponseList(profileRepository.findAll());
        }
        Profile profile = profileRepository.findProfileByLogin(login);
        if (profile == null) {
            throw new NotFoundException("Profile with login '%s' not found".formatted(login));
        }
        return profileMappingService.mapToProfileResponseList(profile);
    }

    public ProfileResponse getById(Long id) {
        Profile profile = profileRepository.findProfileById(id);
        if (profile == null) {
            throw new NotFoundException("Profile with id '%d' not found".formatted(id));
        }
        return profileMappingService.mapToProfileResponse(profile);
    }

    public ProfileResponse insert(ProfileRequest profileRequest) {
        String login = profileRequest.getLogin();
        if (profileRepository.existsByLogin(login)) {
            throw new ProfileAlreadyExistsException("Profile with login '%s' already exists".formatted(login));
        }
        Profile profile = profileRepository.save(profileMappingService.mapToProfile(profileRequest));
        return profileMappingService.mapToProfileResponse(profile);
    }

    public void delete(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new NotFoundException("Profile with id '%d' not found".formatted(id));
        }
        profileRepository.deleteById(id);
    }

    public ProfileResponse update(Long id, ProfileRequest profileRequest) {
        if (!profileRepository.existsById(id)) {
            throw new NotFoundException("Profile with id '%d' not found".formatted(id));
        }
        String login = profileRequest.getLogin();
        Profile profileWithSameLogin = profileRepository.findProfileByLogin(login);
        if (profileWithSameLogin != null && !Objects.equals(profileWithSameLogin.getId(), id)) {
            throw new ProfileAlreadyExistsException("Profile with login '%s' already exists".formatted(login));
        }
        Profile profile = profileRepository.save(profileMappingService.mapToProfile(id, profileRequest));
        return profileMappingService.mapToProfileResponse(profile);
    }
}
