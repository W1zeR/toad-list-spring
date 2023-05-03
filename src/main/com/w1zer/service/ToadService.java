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

    public List<ToadResponse> getAll() {
        return toadMappingService.mapToToadResponseList(toadRepository.getAll());
    }

    public ToadResponse getById(Long id) {
        return toadMappingService.mapToToadResponse(toadRepository.getById(id));
    }

    public List<ToadResponse> getByIdProfile(Long idProfile) {
        return toadMappingService.mapToToadResponseList(toadRepository.getByIdProfile(idProfile));
    }

    public void insert(ToadRequest toadRequest) {
        toadRepository.insert(toadMappingService.mapToToad(toadRequest));
    }

    public void delete(Long id) {
        toadRepository.getById(id); // Checking if toad exists
        toadRepository.delete(id);
    }

    public void update(Long id, ToadRequest toadRequest) {
        toadRepository.getById(id); // Checking if toad exists
        toadRepository.update(toadMappingService.mapToToad(id, toadRequest));
    }
}
