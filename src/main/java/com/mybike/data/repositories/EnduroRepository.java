package com.mybike.data.repositories;

import com.mybike.data.entities.Enduro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnduroRepository extends JpaRepository<Enduro, Long> {

    Optional<Enduro> getByNameIgnoreCase(String name);
}
