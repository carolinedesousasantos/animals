package com.animals.dto.form.animalFamily;

import com.animals.modelo.AnimalFamily;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class FormUpdateAnimalFamily {

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

  public AnimalFamily update(AnimalFamily animalFamily) {
    animalFamily.setName(this.getName());
    return animalFamily;
  }
}
