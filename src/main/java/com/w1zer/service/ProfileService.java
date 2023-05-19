package com.w1zer.service;

import com.w1zer.entity.Profile;
import com.w1zer.exception.NotFoundException;
import com.w1zer.model.ProfileResponse;
import com.w1zer.exception.ProfileAlreadyExistsException;
import com.w1zer.model.ProfileRequest;
import com.w1zer.repository.ProfileRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMappingService profileMappingService;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(ProfileRepository profileRepository, ProfileMappingService profileMappingService, PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.profileMappingService = profileMappingService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ProfileResponse> getAll(String login) {
        if (login == null) {
            return profileMappingService.mapToProfileResponseList(profileRepository.findAll());
        }
        Profile profile = profileRepository.findProfileByLogin(login).orElseThrow(
                () -> new NotFoundException("Profile with login '%s' not found".formatted(login))
        );
        return profileMappingService.mapToProfileResponseList(profile);
    }

    public ProfileResponse getById(Long id) {
        Profile profile = profileRepository.findProfileById(id).orElseThrow(
                () -> new NotFoundException("Profile with id '%d' not found".formatted(id))
        );
        return profileMappingService.mapToProfileResponse(profile);
    }

    public ProfileResponse insert(ProfileRequest profileRequest) {
        String login = profileRequest.getLogin();
        if (profileRepository.existsByLogin(login)) {
            throw new ProfileAlreadyExistsException("Profile with login '%s' already exists".formatted(login));
        }
        Profile profile = getProfileWithEncodedPassword(profileRequest);
        Profile result = profileRepository.save(profile);
        return profileMappingService.mapToProfileResponse(result);
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
        Optional<Profile> profileWithSameLogin = profileRepository.findProfileByLogin(login);
        if (profileWithSameLogin.isPresent() && !Objects.equals(profileWithSameLogin.get().getId(), id)) {
            throw new ProfileAlreadyExistsException("Profile with login '%s' already exists".formatted(login));
        }
        Profile profile = getProfileWithEncodedPasswordAndId(id, profileRequest);
        Profile result = profileRepository.save(profile);
        return profileMappingService.mapToProfileResponse(result);
    }

    private Profile getProfileWithEncodedPassword(ProfileRequest profileRequest){
        Profile profile = profileMappingService.mapToProfile(profileRequest);
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        return profile;
    }

    private Profile getProfileWithEncodedPasswordAndId(Long id, ProfileRequest profileRequest){
        Profile profile = profileMappingService.mapToProfile(id, profileRequest);
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        return profile;
    }
}
