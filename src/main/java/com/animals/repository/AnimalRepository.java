package com.animals.repository;

import com.animals.modelo.Animal;
import com.animals.modelo.Food;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
  Optional<Animal> findByName(String name);

  List<Animal> findByLegs(Long legs);

  List<Animal> findByFood(Food food);
}
