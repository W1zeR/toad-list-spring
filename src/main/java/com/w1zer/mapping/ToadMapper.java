package com.w1zer.mapping;

import com.w1zer.entity.Toad;
import com.w1zer.model.ToadRequest;
import com.w1zer.model.ToadResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToadMapper {
    public ToadResponse mapToToadResponse(Toad toad) {
        return ToadResponse
                .builder()
                .id(toad.getId())
                .name(toad.getName())
                .type(toad.getType())
                .weight(toad.getWeight())
                .length(toad.getLength())
                .birthday(toad.getBirthday())
                .description(toad.getDescription())
                .idProfile(toad.getIdProfile())
                .build();
    }

    public Toad mapToToad(ToadRequest toadRequest) {
        return Toad
                .builder()
                .name(toadRequest.getName())
                .type(toadRequest.getType())
                .weight(toadRequest.getWeight())
                .length(toadRequest.getLength())
                .birthday(toadRequest.getBirthday())
                .description(toadRequest.getDescription())
                .idProfile(toadRequest.getIdProfile())
                .build();
    }

    public Toad mapToToad(Long id, ToadRequest toadRequest) {
        return Toad
                .builder()
                .id(id)
                .name(toadRequest.getName())
                .type(toadRequest.getType())
                .weight(toadRequest.getWeight())
                .length(toadRequest.getLength())
                .birthday(toadRequest.getBirthday())
                .description(toadRequest.getDescription())
                .idProfile(toadRequest.getIdProfile())
                .build();
    }

    public List<ToadResponse> mapToToadResponseList(List<Toad> toads) {
        return toads
                .stream()
                .map(this::mapToToadResponse)
                .collect(Collectors.toList());
    }
}
