package com.animals.dto;

import com.animals.modelo.Animal;
import com.animals.modelo.Food;
import com.animals.modelo.AnimalFamily;

import java.util.List;
import java.util.stream.Collectors;

public class AnimalDto {

  private Long id;

  private String name;

  private Long legs;

  private Food food;

  private AnimalFamily animalFamily;

  public AnimalDto(Animal animal) {
    this.id = animal.getId();
    this.name = animal.getName();
    this.legs = animal.getLegs();
    this.food = animal.getFood();
    this.animalFamily = animal.getAnimalFamily();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getLegs() {
    return legs;
  }

  public void setLegs(Long legs) {
    this.legs = legs;
  }

  public Food getFood() {
    return food;
  }

  public void setFood(Food food) {
    this.food = food;
  }

  public AnimalFamily getAnimalFamily() {
    return animalFamily;
  }

  public void setAnimalFamily(AnimalFamily animalFamily) {
    this.animalFamily = animalFamily;
  }

  public static List<AnimalDto> convert(List<Animal> animals) {
    return animals.stream().map(AnimalDto::new).collect(Collectors.toList());
  }
}
