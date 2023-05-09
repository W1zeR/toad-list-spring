package com.w1zer.repository;

import com.w1zer.entity.Toad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToadRepository extends CrudRepository<Toad, Long> {
    @Override
    @NonNull
    List<Toad> findAll();

    Toad findToadById(Long id);

    List<Toad> findToadsByIdProfile(Long idProfile);
}
