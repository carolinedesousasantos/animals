package com.animals.repository;

import com.animals.modelo.Animal;
import com.animals.modelo.StudyExcluded;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudyRepository extends JpaRepository<StudyExcluded, Long> {
  @Query(
      value =
          "SELECT a FROM Animal a INNER JOIN AnimalFamily AS af ON a.animalFamily.id=af.id  WHERE  a.id NOT IN (SELECT s.animal.id FROM StudyExcluded AS s) ORDER By a.legs DESC")
  public List<Animal> animalsNotExcluded();

  @Query(
      value =
          "SELECT a FROM Animal a INNER JOIN Food As f ON a.food.id=f.id WHERE  a.id NOT IN (SELECT s.animal.id FROM StudyExcluded AS s) ORDER By a.name ASC")
  public List<Animal> animalFoodNotExcluded();
}
