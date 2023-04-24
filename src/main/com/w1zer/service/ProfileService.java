package main.com.w1zer.service;

import main.com.w1zer.exception.NotFoundException;
import main.com.w1zer.exception.ProfileAlreadyExistsException;
import main.com.w1zer.model.ProfileGetDto;
import main.com.w1zer.model.ProfilePatchDto;
import main.com.w1zer.model.ProfilePostDto;
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

    public List<ProfileGetDto> getAll(){
        return profileMappingService.mapToProfileGetDtoList(profileRepository.getAll());
    }

    public ProfileGetDto getById(Long id) {
        return profileMappingService.mapToProfileGetDto(profileRepository.getById(id));
    }

    private boolean isProfileWithLoginExists(String login) {
        try {
            profileRepository.getByLogin(login);
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

    public void insert(ProfilePostDto profilePostDto) {
        if (isProfileWithLoginExists(profilePostDto.getLogin())) {
            throw new ProfileAlreadyExistsException("Profile with this login already exists");
        }
        profileRepository.insert(profileMappingService.mapToProfile(profilePostDto));
    }

    public void delete(Long id) {
        profileRepository.getById(id); // Checking if profile exists
        profileRepository.delete(id);
    }

    public void update(Long id, ProfilePatchDto profilePatchDto){
        profileRepository.getById(id); // Checking if profile exists
        profileRepository.update(profileMappingService.mapToProfile(id, profilePatchDto));
    }
}
