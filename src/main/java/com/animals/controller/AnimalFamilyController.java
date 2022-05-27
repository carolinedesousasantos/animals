package com.animals.controller;

import com.animals.dto.AnimalFamilyDto;
import com.animals.dto.form.animalFamily.FormUpdateAnimalFamily;
import com.animals.modelo.AnimalFamily;
import com.animals.dto.form.animalFamily.FormCreateAnimalFamily;
import com.animals.exceptions.AnimalFamilyAlreadyExistsException;
import com.animals.exceptions.AnimalFamilyNotFoundException;
import com.animals.service.AnimalFamilyService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/animal-family")
public class AnimalFamilyController {

  @Autowired private AnimalFamilyService animalFamilyService;

  @PostMapping
  public ResponseEntity<AnimalFamilyDto> create(
      @RequestBody @Valid FormCreateAnimalFamily form, UriComponentsBuilder uriBuilder)
      throws AnimalFamilyAlreadyExistsException {
    AnimalFamily animalFamilySaved = animalFamilyService.create(form);
    URI uri =
        uriBuilder.path("/animal-family/{id}").buildAndExpand(animalFamilySaved.getId()).toUri();
    return ResponseEntity.created(uri).body(new AnimalFamilyDto(animalFamilySaved));
  }

  @GetMapping
  public List<AnimalFamilyDto> read() {
    List<AnimalFamily> animalFamily = animalFamilyService.read();
    return AnimalFamilyDto.convert(animalFamily);
  }

  @GetMapping(value = {"/{name}"})
  public AnimalFamilyDto readByName(@PathVariable String name)
      throws AnimalFamilyNotFoundException {
    return animalFamilyService.readByName(name);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AnimalFamilyDto> update(
      @PathVariable Long id, @RequestBody @Valid FormUpdateAnimalFamily form)
      throws AnimalFamilyNotFoundException, AnimalFamilyAlreadyExistsException {
    return animalFamilyService.update(id, form);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AnimalFamilyDto> delete(@PathVariable Long id)
      throws AnimalFamilyNotFoundException {
    animalFamilyService.delete(id);
    return ResponseEntity.ok().build();
  }
}
