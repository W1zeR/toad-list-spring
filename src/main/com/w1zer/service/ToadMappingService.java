package main.com.w1zer.service;

import main.com.w1zer.entity.Toad;
import main.com.w1zer.model.ToadGetDto;
import main.com.w1zer.model.ToadPatchDto;
import main.com.w1zer.model.ToadPostDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToadMappingService {
    public ToadGetDto mapToToadGetDto(Toad toad){
        return new ToadGetDto(toad.getId(), toad.getName(), toad.getType(), toad.getWeight(), toad.getLength(),
                toad.getBirthday(), toad.getDescription(), toad.getIdProfile());
    }

    public Toad mapToToad(ToadPostDto toadPostDto){
        return new Toad(null, toadPostDto.getName(), toadPostDto.getType(), toadPostDto.getWeight(),
                toadPostDto.getLength(), toadPostDto.getBirthday(), toadPostDto.getDescription(),
                toadPostDto.getIdProfile());
    }

    public Toad mapToToad(Long id, ToadPatchDto toadPatchDto) {
        return new Toad(id, toadPatchDto.getName(), toadPatchDto.getType(), toadPatchDto.getWeight(),
                toadPatchDto.getLength(), toadPatchDto.getBirthday(), toadPatchDto.getDescription(),
                toadPatchDto.getIdProfile());
    }

    public List<ToadGetDto> mapToToadGetDtoList(List<Toad> toads){
        List<ToadGetDto> toadGetDtoList = new ArrayList<>();
        for (Toad toad: toads){
            toadGetDtoList.add(mapToToadGetDto(toad));
        }
        return toadGetDtoList;
    }
}
