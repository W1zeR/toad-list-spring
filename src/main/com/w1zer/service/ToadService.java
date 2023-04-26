package main.com.w1zer.service;

import main.com.w1zer.model.*;
import main.com.w1zer.repository.ToadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToadService {
    private final ToadRepository toadRepository;
    private final ToadMappingService toadMappingService;

    public ToadService(ToadRepository toadRepository, ToadMappingService toadMappingService) {
        this.toadRepository = toadRepository;
        this.toadMappingService = toadMappingService;
    }

    public List<ToadGetDto> getAll(){
        return toadMappingService.mapToToadGetDtoList(toadRepository.getAll());
    }

    public ToadGetDto getById(Long id){
        return toadMappingService.mapToToadGetDto(toadRepository.getById(id));
    }

    public List<ToadGetDto> getByIdProfile(Long idProfile){
        return toadMappingService.mapToToadGetDtoList(toadRepository.getByIdProfile(idProfile));
    }

    public void insert(ToadPostDto toadPostDto) {
        toadRepository.insert(toadMappingService.mapToToad(toadPostDto));
    }

    public void delete(Long id) {
        toadRepository.getById(id); // Checking if toad exists
        toadRepository.delete(id);
    }

    public void update(Long id, ToadPatchDto toadPatchDto){
        toadRepository.getById(id); // Checking if toad exists
        toadRepository.update(toadMappingService.mapToToad(id, toadPatchDto));
    }
}
