package com.w1zer.service;

import com.w1zer.entity.Toad;
import com.w1zer.exception.NotFoundException;
import com.w1zer.model.ToadRequest;
import com.w1zer.model.ToadResponse;
import com.w1zer.repository.ToadRepository;
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
        return toadMappingService.mapToToadResponseList(toadRepository.findAll());
    }

    public ToadResponse getById(Long id) {
        Toad toad = toadRepository.findToadById(id).orElseThrow(
                () -> new NotFoundException("Toad with id '%d' not found".formatted(id))
        );
        return toadMappingService.mapToToadResponse(toad);
    }

    public List<ToadResponse> getByIdProfile(Long idProfile) {
        return toadMappingService.mapToToadResponseList(toadRepository.findToadsByIdProfile(idProfile));
    }

    public ToadResponse insert(ToadRequest toadRequest) {
        Toad toad = toadRepository.save(toadMappingService.mapToToad(toadRequest));
        return toadMappingService.mapToToadResponse(toad);
    }

    public void delete(Long id) {
        if (!toadRepository.existsById(id)) {
            throw new NotFoundException("Toad with id '%d' not found".formatted(id));
        }
        toadRepository.deleteById(id);
    }

    public ToadResponse update(Long id, ToadRequest toadRequest) {
        if (!toadRepository.existsById(id)) {
            throw new NotFoundException("Toad with id '%d' not found".formatted(id));
        }
        Toad toad = toadRepository.save(toadMappingService.mapToToad(id, toadRequest));
        return toadMappingService.mapToToadResponse(toad);
    }
}
