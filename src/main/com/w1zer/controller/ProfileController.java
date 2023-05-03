package main.com.w1zer.controller;

import main.com.w1zer.model.ProfileResponse;
import main.com.w1zer.model.ProfileRequest;
import main.com.w1zer.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/")
    public List<ProfileResponse> getAll(@RequestParam(required = false) String login) {
        return profileService.getAll(login);
    }

    @GetMapping("/{id}/")
    public ProfileResponse getById(@PathVariable Long id) {
        return profileService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public void insert(@Valid @RequestBody ProfileRequest profileRequest) {
        profileService.insert(profileRequest);
    }

    @DeleteMapping("/{id}/")
    public void delete(@PathVariable Long id) {
        profileService.delete(id);
    }

    @PatchMapping("/{id}/")
    public void update(@PathVariable Long id, @Valid @RequestBody ProfileRequest profileRequest) {
        profileService.update(id, profileRequest);
    }
}
