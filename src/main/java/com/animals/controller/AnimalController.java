package com.animals.controller;

import com.animals.dto.AnimalDto;
import com.animals.dto.form.animal.FormCreateAnimal;
import com.animals.dto.form.animal.FormUpdateAnimal;
import com.animals.exceptions.AnimalFamilyNotFoundException;
import com.animals.exceptions.AnimalNotFoundException;
import com.animals.exceptions.FoodNotFoundException;
import com.animals.modelo.Animal;
import com.animals.repository.AnimalFamilyRepository;
import com.animals.repository.AnimalRepository;
import com.animals.repository.FoodRepository;
import com.animals.exceptions.AnimalAlreadyExistsException;
import com.animals.service.AnimalService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/animal")
public class AnimalController {

  @Autowired private AnimalService animalService;

  @Autowired private AnimalRepository animalRepository;

  @Autowired private FoodRepository foodRepository;

  @Autowired private AnimalFamilyRepository animalFamilyRepository;

  @PostMapping
  public ResponseEntity<AnimalDto> create(
          @RequestBody @Valid FormCreateAnimal form, UriComponentsBuilder uriBuilder)
      throws FoodNotFoundException, AnimalFamilyNotFoundException, AnimalAlreadyExistsException {
    Animal animalSaved = animalService.create(form);
    URI uri = uriBuilder.path("/animal/{id}").buildAndExpand(animalSaved.getId()).toUri();
    return ResponseEntity.created(uri).body(new AnimalDto(animalSaved));
  }

  @GetMapping
  public List<AnimalDto> read() {
    List<Animal> animals = animalService.read();
    return AnimalDto.convert(animals);
  }

  @GetMapping(value = {"/name/{name}"})
  public AnimalDto readName(@PathVariable String name) throws AnimalNotFoundException {
    return animalService.readName(name);
  }

  @GetMapping(value = {"/legs/{legs}"})
  public List<AnimalDto> readLegs(@PathVariable Long legs) throws AnimalNotFoundException {
    List<Animal> animals = animalService.readLegs(legs);
    return AnimalDto.convert(animals);
  }

  @GetMapping(value = {"/foodName/{foodName}"})
  public List<AnimalDto> readByFoodName(@PathVariable String foodName)
      throws FoodNotFoundException {
    List<Animal> animals = animalService.readByFoodName(foodName);
    return AnimalDto.convert(animals);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AnimalDto> update(
      @PathVariable Long id, @RequestBody @Valid FormUpdateAnimal form)
      throws AnimalNotFoundException, FoodNotFoundException, AnimalFamilyNotFoundException {
    Animal animalUpdated = animalService.update(id, form);
    return ResponseEntity.ok(new AnimalDto(animalUpdated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AnimalDto> delete(@PathVariable Long id) throws AnimalNotFoundException {
    animalService.delete(id);
    return ResponseEntity.ok().build();
  }
}
