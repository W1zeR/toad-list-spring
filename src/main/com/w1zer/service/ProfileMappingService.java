package main.com.w1zer.service;

import main.com.w1zer.entity.Profile;
import main.com.w1zer.model.ProfileResponse;
import main.com.w1zer.model.ProfileRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileMappingService {
    public ProfileResponse mapToProfileResponse(Profile profile) {
        return ProfileResponse
                .builder()
                .id(profile.getId())
                .login(profile.getLogin())
                .build();
    }

    public Profile mapToProfile(ProfileRequest profileRequest) {
        return Profile
                .builder()
                .login(profileRequest.getLogin())
                .password(profileRequest.getPassword())
                .build();
    }

    public Profile mapToProfile(Long id, ProfileRequest profileRequest) {
        return Profile
                .builder()
                .id(id)
                .login(profileRequest.getLogin())
                .password(profileRequest.getPassword())
                .build();
    }

    public List<ProfileResponse> mapToProfileResponseList(List<Profile> profiles) {
        return profiles
                .stream()
                .map(this::mapToProfileResponse)
                .collect(Collectors.toList());
    }

    public List<ProfileResponse> mapToProfileResponseList(Profile profile) {
        return List.of(mapToProfileResponse(profile));
    }
}
