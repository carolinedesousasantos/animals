package com.animals.dto.form.food;

import com.animals.modelo.Food;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class FormUpdateFood {

  @NotNull
  @NotEmpty
  @Length(min = 3)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Food update(Food food) {
    food.setName(this.name);
    return food;
  }
}
