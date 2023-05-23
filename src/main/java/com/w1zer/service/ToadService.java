package com.w1zer.service;

import com.w1zer.entity.Toad;
import com.w1zer.exception.NotFoundException;
import com.w1zer.mapping.ToadMapper;
import com.w1zer.model.ToadRequest;
import com.w1zer.model.ToadResponse;
import com.w1zer.repository.ToadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToadService {
    private static final String TOAD_WITH_ID_NOT_FOUND = "Toad with id '%d' not found";

    private final ToadRepository toadRepository;
    private final ToadMapper toadMapper;

    public ToadService(ToadRepository toadRepository, ToadMapper toadMapper) {
        this.toadRepository = toadRepository;
        this.toadMapper = toadMapper;
    }

    public List<ToadResponse> getAll() {
        return toadMapper.mapToToadResponseList(toadRepository.findAll());
    }

    public ToadResponse getById(Long id) {
        Toad toad = toadRepository.findToadById(id).orElseThrow(
                () -> new NotFoundException(TOAD_WITH_ID_NOT_FOUND.formatted(id))
        );
        return toadMapper.mapToToadResponse(toad);
    }

    public List<ToadResponse> getByIdProfile(Long idProfile) {
        return toadMapper.mapToToadResponseList(toadRepository.findToadsByIdProfile(idProfile));
    }

    public ToadResponse insert(ToadRequest toadRequest) {
        Toad toad = toadRepository.save(toadMapper.mapToToad(toadRequest));
        return toadMapper.mapToToadResponse(toad);
    }

    public void delete(Long id) {
        if (!toadRepository.existsById(id)) {
            throw new NotFoundException(TOAD_WITH_ID_NOT_FOUND.formatted(id));
        }
        toadRepository.deleteById(id);
    }

    public ToadResponse update(Long id, ToadRequest toadRequest) {
        if (!toadRepository.existsById(id)) {
            throw new NotFoundException(TOAD_WITH_ID_NOT_FOUND.formatted(id));
        }
        Toad toad = toadRepository.save(toadMapper.mapToToad(id, toadRequest));
        return toadMapper.mapToToadResponse(toad);
    }
}
