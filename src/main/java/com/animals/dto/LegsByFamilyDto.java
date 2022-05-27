package com.animals.dto;

import com.animals.modelo.Animal;

public class LegsByFamilyDto {

  private Long idAnimal;

  private String animalName;
  private String familyName;
  private Long legs;

  public LegsByFamilyDto(Animal animal) {
    this.idAnimal = animal.getId();
    this.animalName = animal.getName();
    this.familyName = animal.getAnimalFamily().getName();
    this.legs = animal.getLegs();
  }

  public Long getIdAnimal() {
    return idAnimal;
  }

  public String getAnimalName() {
    return animalName;
  }

  public void setAnimalName(String animalName) {
    this.animalName = animalName;
  }

  public void setIdAnimal(Long idAnimal) {
    this.idAnimal = idAnimal;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public Long getLegs() {
    return legs;
  }

  public void setLegs(Long legs) {
    this.legs = legs;
  }
}
