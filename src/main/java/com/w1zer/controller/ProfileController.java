package com.w1zer.controller;

import com.w1zer.model.ProfileResponse;
import com.w1zer.service.ProfileService;
import com.w1zer.model.ProfileRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

import static com.w1zer.constants.ValidationConstants.*;

@RestController
@Validated
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public List<ProfileResponse> getAll(
            @RequestParam(required = false)
            @Size(min = LOGIN_MIN_SIZE, max = LOGIN_MAX_SIZE, message = LOGIN_SIZE_MESSAGE)
            String login) {
        return profileService.getAll(login);
    }

    @GetMapping("/{id}")
    public ProfileResponse getById(@PathVariable @Positive(message = ID_POSITIVE_MESSAGE) Long id) {
        return profileService.getById(id);
    }

    @PostMapping
    public ProfileResponse insert(@Valid @RequestBody ProfileRequest profileRequest) {
        return profileService.insert(profileRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable @Positive(message = ID_POSITIVE_MESSAGE) Long id) {
        profileService.delete(id);
    }

    @PutMapping("/{id}")
    public ProfileResponse update(
            @PathVariable @Positive(message = ID_POSITIVE_MESSAGE) Long id,
            @Valid @RequestBody ProfileRequest profileRequest) {
        return profileService.update(id, profileRequest);
    }
}
