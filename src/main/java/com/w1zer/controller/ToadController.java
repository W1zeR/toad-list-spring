package com.w1zer.controller;

import com.w1zer.model.ToadRequest;
import com.w1zer.model.ToadResponse;
import com.w1zer.service.ToadService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class ToadController {
    private final ToadService toadService;

    public ToadController(ToadService toadService) {
        this.toadService = toadService;
    }

    @GetMapping("/toads")
    public List<ToadResponse> getAll() {
        return toadService.getAll();
    }

    @GetMapping("/toads/{id}")
    public ToadResponse getById(@PathVariable @Positive(message = "Id must be positive number") Long id) {
        return toadService.getById(id);
    }

    @GetMapping("/profiles/{id}/toads")
    public List<ToadResponse> getByIdProfile(@PathVariable @Positive(message = "Id must be positive number") Long id) {
        return toadService.getByIdProfile(id);
    }

    @PostMapping("/toads")
    public ToadResponse insert(@Valid @RequestBody ToadRequest toadRequest) {
        return toadService.insert(toadRequest);
    }

    @DeleteMapping("/toads/{id}")
    public void delete(@PathVariable @Positive(message = "Id must be positive number") Long id) {
        toadService.delete(id);
    }

    @PutMapping("/toads/{id}")
    public ToadResponse update(
            @PathVariable @Positive(message = "Id must be positive number") Long id,
            @Valid @RequestBody ToadRequest toadRequest) {
        return toadService.update(id, toadRequest);
    }
}
