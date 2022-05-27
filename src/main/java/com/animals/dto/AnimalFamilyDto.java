package com.animals.dto;

import com.animals.modelo.AnimalFamily;

import java.util.List;
import java.util.stream.Collectors;

public class AnimalFamilyDto {

  private Long id;

  private String name;

  public AnimalFamilyDto(AnimalFamily animalFamily) {
    this.id = animalFamily.getId();
    this.name = animalFamily.getName();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static List<AnimalFamilyDto> convert(List<AnimalFamily> animals) {
    return animals.stream().map(AnimalFamilyDto::new).collect(Collectors.toList());
  }
}
