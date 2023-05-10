package com.w1zer.controller;

import com.w1zer.model.ProfileResponse;
import com.w1zer.service.ProfileService;
import com.w1zer.model.ProfileRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@Validated
@RequestMapping("/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<ProfileResponse> getAll(
            @RequestParam(required = false)
            @Size(min = 3, max = 50, message = "Login must contain from 3 to 50 characters")
            String login) {
        return profileService.getAll(login);
    }

    @GetMapping("/{id}")
    public ProfileResponse getById(@PathVariable @Positive(message = "Id must be positive number") Long id) {
        return profileService.getById(id);
    }

    @PostMapping
    public ProfileResponse insert(@Valid @RequestBody ProfileRequest profileRequest) {
        return profileService.insert(profileRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive(message = "Id must be positive number") Long id) {
        profileService.delete(id);
    }

    @PutMapping("/{id}")
    public ProfileResponse update(
            @PathVariable @Positive(message = "Id must be positive number") Long id,
            @Valid @RequestBody ProfileRequest profileRequest) {
        return profileService.update(id, profileRequest);
    }
}
