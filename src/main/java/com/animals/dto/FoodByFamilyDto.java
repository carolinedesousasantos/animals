package com.animals.dto;

import com.animals.modelo.Animal;

public class FoodByFamilyDto {

  private Long idAnimal;

  private String animalName;
  private String familyName;
  private String foodName;

  public FoodByFamilyDto(Animal animal) {
    this.idAnimal = animal.getId();
    this.animalName = animal.getName();
    this.familyName = animal.getAnimalFamily().getName();
    this.foodName = animal.getFood().getName();
  }

  public Long getIdAnimal() {
    return idAnimal;
  }

  public void setIdAnimal(Long idAnimal) {
    this.idAnimal = idAnimal;
  }

  public String getAnimalName() {
    return animalName;
  }

  public void setAnimalName(String animalName) {
    this.animalName = animalName;
  }

  public String getFamilyName() {
    return familyName;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public String getFoodName() {
    return foodName;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }
}
