package main.com.w1zer.controller;

import main.com.w1zer.model.ProfileGetDto;
import main.com.w1zer.model.ProfilePatchDto;
import main.com.w1zer.model.ProfilePostDto;
import main.com.w1zer.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profiles")
    public List<ProfileGetDto> getAll(){
        return profileService.getAll();
    }

    @GetMapping("/profiles/{id}")
    public ProfileGetDto getById(@PathVariable Long id) {
        return profileService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/profiles")
    public void insert(@RequestBody ProfilePostDto profilePostDto) {
        profileService.insert(profilePostDto);
    }

    @DeleteMapping("/profiles/{id}")
    public void delete(@PathVariable Long id) {
        profileService.delete(id);
    }

    @PatchMapping("/profiles/{id}")
    public void update(@PathVariable Long id, @RequestBody ProfilePatchDto profilePatchDto){
        profileService.update(id, profilePatchDto);
    }
}
