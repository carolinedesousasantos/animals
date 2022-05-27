package com.animals.modelo;

import javax.persistence.*;

@Entity
@Table(name = "StudyExcluded")
public class StudyExcluded {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "idAnimal")
  private Animal animal;

  public StudyExcluded() {}

  public StudyExcluded(Animal animal) {
    this.animal = animal;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Animal getIdAnimalExcluded() {
    return animal;
  }

  public void setIdAnimalExcluded(Animal idAnimalExcluded) {
    this.animal = idAnimalExcluded;
  }
}
