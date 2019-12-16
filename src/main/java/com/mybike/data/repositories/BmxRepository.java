package com.mybike.data.repositories;

import com.mybike.data.entities.Bmx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BmxRepository extends JpaRepository<Bmx, Long> {
}
