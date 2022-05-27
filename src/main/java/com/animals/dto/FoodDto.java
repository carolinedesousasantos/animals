package com.animals.dto;

import com.animals.modelo.Food;

import java.util.List;
import java.util.stream.Collectors;

public class FoodDto {

  private Long id;

  private String name;

  public FoodDto(Food food) {
    this.id = food.getId();
    this.name = food.getName();
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

  public static List<FoodDto> convert(List<Food> foods) {
    return foods.stream().map(FoodDto::new).collect(Collectors.toList());
  }
}
