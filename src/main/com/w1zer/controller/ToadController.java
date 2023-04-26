package main.com.w1zer.controller;

import main.com.w1zer.model.*;
import main.com.w1zer.service.ToadService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToadController {
    private final ToadService toadService;

    public ToadController(ToadService toadService) {
        this.toadService = toadService;
    }

    @GetMapping("/toads")
    public List<ToadGetDto> getAll(){
        return toadService.getAll();
    }

    @GetMapping("/toads/{id}")
    public ToadGetDto getById(@PathVariable Long id) {
        return toadService.getById(id);
    }

    @GetMapping("/profiles/{id}/toads")
    public List<ToadGetDto> getByIdProfile(@PathVariable Long id){
        return toadService.getByIdProfile(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/toads")
    public void insert(@RequestBody ToadPostDto toadPostDto) {
        toadService.insert(toadPostDto);
    }

    @DeleteMapping("/toads/{id}")
    public void delete(@PathVariable Long id) {
        toadService.delete(id);
    }

    @PatchMapping("/toads/{id}")
    public void update(@PathVariable Long id, @RequestBody ToadPatchDto toadPatchDto){
        toadService.update(id, toadPatchDto);
    }
}
