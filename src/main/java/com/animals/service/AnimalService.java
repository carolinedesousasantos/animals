package com.animals.service;

import com.animals.dto.AnimalDto;
import com.animals.dto.form.animal.FormCreateAnimal;
import com.animals.dto.form.animal.FormUpdateAnimal;
import com.animals.exceptions.AnimalAlreadyExistsException;
import com.animals.exceptions.AnimalFamilyNotFoundException;
import com.animals.exceptions.AnimalNotFoundException;
import com.animals.exceptions.FoodNotFoundException;
import com.animals.modelo.Animal;
import com.animals.modelo.AnimalFamily;
import com.animals.modelo.Food;
import com.animals.repository.AnimalFamilyRepository;
import com.animals.repository.AnimalRepository;
import com.animals.repository.FoodRepository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AnimalService {

  @Autowired private AnimalRepository animalRepository;

  @Autowired private FoodRepository foodRepository;

  @Autowired private AnimalFamilyRepository animalFamilyRepository;

  public Animal create(FormCreateAnimal form)
      throws FoodNotFoundException, AnimalFamilyNotFoundException, AnimalAlreadyExistsException {
    Optional<Food> food = foodRepository.findById(form.getIdFood());
    Optional<AnimalFamily> animalFamily = animalFamilyRepository.findById(form.getIdFamily());
    if (!food.isPresent()) {
      throw new FoodNotFoundException();
    } else if (!animalFamily.isPresent()) {
      throw new AnimalFamilyNotFoundException();
    } else {
      Optional<Animal> animalOptional = animalRepository.findByName(form.getName());
      if (animalOptional.isPresent()) {
        throw new AnimalAlreadyExistsException();
      } else {
        Animal animal = new Animal(form.getName(), form.getLegs(), food.get(), animalFamily.get());
        Animal animalSaved = animalRepository.save(animal);
        return animalSaved;
      }
    }
  }

  public List<Animal> read() {
    return animalRepository.findAll();
  }

  public Animal getById(Long id) throws AnimalNotFoundException {
    Optional<Animal> animal = animalRepository.findById(id);
    if (animal.isPresent()) {
      return animal.get();
    } else {
      throw new AnimalNotFoundException();
    }
  }

  public AnimalDto readName(String name) throws AnimalNotFoundException {
    Optional<Animal> animal = animalRepository.findByName(name);
    if (animal.isPresent()) {
      return new AnimalDto(animal.get());
    } else {
      throw new AnimalNotFoundException();
    }
  }

  public List<Animal> readLegs(Long legs) throws AnimalNotFoundException {
    List<Animal> animals = animalRepository.findByLegs(legs);
    if (animals.size() > 0) {
      return animals;
    } else {
      throw new AnimalNotFoundException();
    }
  }

  public List<Animal> readByFoodName(String foodName) throws FoodNotFoundException {
    Optional<Food> optionalFood = foodRepository.findByName(foodName);
    if (optionalFood.isPresent()) {
      List<Animal> animals = animalRepository.findByFood(optionalFood.get());
      return animals;
    } else {
      throw new FoodNotFoundException();
    }
  }

  public void delete(Long id) throws AnimalNotFoundException {
    Optional<Animal> animal = animalRepository.findById(id);
    if (animal.isPresent()) {
      animalRepository.delete(animal.get());
    } else {
      throw new AnimalNotFoundException();
    }
  }

  public Animal update(Long id, FormUpdateAnimal form)
      throws AnimalNotFoundException, FoodNotFoundException, AnimalFamilyNotFoundException {
    Optional<Animal> optionalAnimal = animalRepository.findById(id);
    Optional<Food> optionalFood = foodRepository.findById(form.getIdFood());
    Optional<AnimalFamily> optionalAnimalFamily =
        animalFamilyRepository.findById(form.getIdFamily());
    if (!optionalAnimal.isPresent()) {
      throw new AnimalNotFoundException();
    } else if (!optionalFood.isPresent()) {
      throw new FoodNotFoundException();
    } else if (!optionalAnimalFamily.isPresent()) {
      throw new AnimalFamilyNotFoundException();
    } else {
      Animal animal =
          form.update(optionalAnimal.get(), optionalFood.get(), optionalAnimalFamily.get());
      return animal;
    }
  }
}
