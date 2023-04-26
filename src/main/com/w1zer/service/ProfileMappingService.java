package main.com.w1zer.service;

import main.com.w1zer.entity.Profile;
import main.com.w1zer.model.ProfileGetDto;
import main.com.w1zer.model.ProfilePatchDto;
import main.com.w1zer.model.ProfilePostDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileMappingService {
    public ProfileGetDto mapToProfileGetDto(Profile profile){
        return new ProfileGetDto(profile.getId(), profile.getLogin());
    }

    public Profile mapToProfile(ProfilePostDto profilePostDto){
        return new Profile(null, profilePostDto.getLogin(), profilePostDto.getPassword());
    }

    public Profile mapToProfile(Long id, ProfilePatchDto profilePatchDto){
        return new Profile(id, profilePatchDto.getLogin(), profilePatchDto.getPassword());
    }

    public List<ProfileGetDto> mapToProfileGetDtoList(List<Profile> profiles){
        List<ProfileGetDto> profileGetDtoList = new ArrayList<>();
        for (Profile profile: profiles){
            profileGetDtoList.add(mapToProfileGetDto(profile));
        }
        return profileGetDtoList;
    }
}
