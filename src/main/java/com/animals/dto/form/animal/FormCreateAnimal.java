package com.animals.dto.form.animal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class FormCreateAnimal {

  @NotNull
  @NotEmpty
  @Length(min = 3)
  private String name;

  private Long legs;

  private Long idFood;

  private Long idFamily;

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

  public Long getIdFood() {
    return idFood;
  }

  public void setIdFood(Long idFood) {
    this.idFood = idFood;
  }

  public Long getIdFamily() {
    return idFamily;
  }

  public void setIdFamily(Long idFamily) {
    this.idFamily = idFamily;
  }
}
