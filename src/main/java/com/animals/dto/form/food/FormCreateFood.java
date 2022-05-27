package com.animals.dto.form.food;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class FormCreateFood {
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
}
