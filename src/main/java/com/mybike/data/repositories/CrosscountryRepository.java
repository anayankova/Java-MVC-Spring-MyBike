package com.mybike.data.repositories;

import com.mybike.data.entities.Crosscountry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrosscountryRepository extends JpaRepository<Crosscountry, Long> {
}
