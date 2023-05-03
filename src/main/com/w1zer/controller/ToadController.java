package main.com.w1zer.controller;

import main.com.w1zer.model.*;
import main.com.w1zer.service.ToadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ToadController {
    private final ToadService toadService;

    public ToadController(ToadService toadService) {
        this.toadService = toadService;
    }

    @GetMapping("/toads/")
    public List<ToadResponse> getAll() {
        return toadService.getAll();
    }

    @GetMapping("/toads/{id}/")
    public ToadResponse getById(@PathVariable Long id) {
        return toadService.getById(id);
    }

    @GetMapping("/profiles/{id}/toads/")
    public List<ToadResponse> getByIdProfile(@PathVariable Long id) {
        return toadService.getByIdProfile(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/toads/")
    public void insert(@Valid @RequestBody ToadRequest toadRequest) {
        toadService.insert(toadRequest);
    }

    @DeleteMapping("/toads/{id}/")
    public void delete(@PathVariable Long id) {
        toadService.delete(id);
    }

    @PatchMapping("/toads/{id}/")
    public void update(@PathVariable Long id, @Valid @RequestBody ToadRequest toadRequest) {
        toadService.update(id, toadRequest);
    }
}
