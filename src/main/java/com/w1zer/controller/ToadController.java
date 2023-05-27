package com.w1zer.controller;

import com.w1zer.model.ToadRequest;
import com.w1zer.model.ToadResponse;
import com.w1zer.service.ToadService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

import static com.w1zer.constants.ValidationConstants.ID_POSITIVE_MESSAGE;

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
    public ToadResponse getById(@PathVariable @Positive(message = ID_POSITIVE_MESSAGE) Long id) {
        return toadService.getById(id);
    }

    @GetMapping("/profiles/{id}/toads")
    public List<ToadResponse> getByIdProfile(@PathVariable @Positive(message = ID_POSITIVE_MESSAGE) Long id) {
        return toadService.getByIdProfile(id);
    }

    @PostMapping("/toads")
    public ToadResponse insert(@Valid @RequestBody ToadRequest toadRequest) {
        return toadService.insert(toadRequest);
    }

    @DeleteMapping("/toads/{id}")
    public void delete(@PathVariable @Positive(message = ID_POSITIVE_MESSAGE) Long id) {
        toadService.delete(id);
    }

    @PutMapping("/toads/{id}")
    public ToadResponse update(
            @PathVariable @Positive(message = ID_POSITIVE_MESSAGE) Long id,
            @Valid @RequestBody ToadRequest toadRequest) {
        return toadService.update(id, toadRequest);
    }
}
