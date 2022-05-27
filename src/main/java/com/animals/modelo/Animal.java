package com.animals.modelo;

import javax.persistence.*;

@Entity
@Table(name = "animals")
public class Animal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String name;

  private Long legs;

  @OneToOne
  @JoinColumn(name = "idFood")
  private Food food;

  @OneToOne
  @JoinColumn(name = "idFamily")
  private AnimalFamily animalFamily;

  public Animal() {}

  public Animal(String name, Long legs, Food food, AnimalFamily animalFamily) {
    this.name = name;
    this.legs = legs;
    this.food = food;
    this.animalFamily = animalFamily;
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
}
