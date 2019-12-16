package com.mybike.data.repositories;

import com.mybike.data.entities.Downhill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownhillRepository extends JpaRepository<Downhill, Long> {
}
