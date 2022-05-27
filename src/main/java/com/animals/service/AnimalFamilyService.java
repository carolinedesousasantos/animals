package com.animals.service;

import com.animals.dto.AnimalFamilyDto;
import com.animals.dto.form.animalFamily.FormCreateAnimalFamily;
import com.animals.dto.form.animalFamily.FormUpdateAnimalFamily;
import com.animals.exceptions.AnimalFamilyAlreadyExistsException;
import com.animals.exceptions.AnimalFamilyNotFoundException;
import com.animals.modelo.AnimalFamily;
import com.animals.repository.AnimalFamilyRepository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AnimalFamilyService {

  @Autowired private AnimalFamilyRepository animalFamilyRepository;

  public AnimalFamily create(FormCreateAnimalFamily form)
      throws AnimalFamilyAlreadyExistsException {
    Optional<AnimalFamily> animalFamilyName = animalFamilyRepository.findByName(form.getName());
    if (animalFamilyName.isPresent()) {
      throw new AnimalFamilyAlreadyExistsException();
    } else {
      AnimalFamily animalFamily = new AnimalFamily(form.getName());
      return animalFamilyRepository.save(animalFamily);
    }
  }

  public List<AnimalFamily> read() {
    return animalFamilyRepository.findAll();
  }

  public AnimalFamily getById(Long id) throws AnimalFamilyNotFoundException {
    Optional<AnimalFamily> animalFamily = animalFamilyRepository.findById(id);
    if (animalFamily.isPresent()) {
      return animalFamily.get();
    } else {
      throw new AnimalFamilyNotFoundException();
    }
  }

  public AnimalFamilyDto readByName(String name) throws AnimalFamilyNotFoundException {
    Optional<AnimalFamily> animalFamily = animalFamilyRepository.findByName(name);
    if (animalFamily.isPresent()) {
      return new AnimalFamilyDto(animalFamily.get());
    } else {
      throw new AnimalFamilyNotFoundException();
    }
  }

  public ResponseEntity<AnimalFamilyDto> update(Long id, FormUpdateAnimalFamily form)
      throws AnimalFamilyAlreadyExistsException, AnimalFamilyNotFoundException {
    Optional<AnimalFamily> optional = animalFamilyRepository.findById(id);
    if (optional.isPresent()) {
      Optional<AnimalFamily> animalFamilyName = animalFamilyRepository.findByName(form.getName());
      if (animalFamilyName.isPresent()) {
        throw new AnimalFamilyAlreadyExistsException();
      } else {
        AnimalFamily animalFamily = form.update(optional.get());
        return ResponseEntity.ok(new AnimalFamilyDto(animalFamily));
      }
    } else {
      throw new AnimalFamilyNotFoundException();
    }
  }

  public void delete(Long id) throws AnimalFamilyNotFoundException {
    Optional<AnimalFamily> animalFamily = animalFamilyRepository.findById(id);
    if (animalFamily.isPresent()) {
      animalFamilyRepository.delete(animalFamily.get());
    } else {
      throw new AnimalFamilyNotFoundException();
    }
  }
}
