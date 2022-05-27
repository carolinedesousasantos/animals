package com.animals.service;

import com.animals.dto.FoodByFamilyDto;
import com.animals.dto.LegsByFamilyDto;
import com.animals.modelo.Animal;
import com.animals.repository.StudyRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudyService {

  @Autowired StudyRepository studyRepository;

  public List<LegsByFamilyDto> getLegsByFamilyId(Long familyId) {
    List<Animal> animals = studyRepository.animalsNotExcluded();
    return animals.stream()
        .filter(animal -> animal.getAnimalFamily().getId().equals(familyId))
        .map(LegsByFamilyDto::new)
        .collect(Collectors.toList());
  }

  public List<LegsByFamilyDto> getLegsByFamilyName(String familyName) {
    List<Animal> animals = studyRepository.animalsNotExcluded();
    return animals.stream()
        .filter(animal -> animal.getAnimalFamily().getName().equals(familyName))
        .map(LegsByFamilyDto::new)
        .collect(Collectors.toList());
  }

  public List<FoodByFamilyDto> getFoodByFamilyId(Long familyId) {
    List<Animal> animals = studyRepository.animalFoodNotExcluded();
    return animals.stream()
        .filter(animal -> animal.getAnimalFamily().getId().equals(familyId))
        .map(FoodByFamilyDto::new)
        .collect(Collectors.toList());
  }

  public List<FoodByFamilyDto> getFoodByFamilyName(String familyName) {
    List<Animal> animals = studyRepository.animalFoodNotExcluded();
    return animals.stream()
        .filter(animal -> animal.getAnimalFamily().getName().equals(familyName))
        .map(FoodByFamilyDto::new)
        .collect(Collectors.toList());
  }
}
