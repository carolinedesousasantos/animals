package com.animals.repository;

import com.animals.modelo.AnimalFamily;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalFamilyRepository extends JpaRepository<AnimalFamily, Long> {
  Optional<AnimalFamily> findByName(String name);
}
