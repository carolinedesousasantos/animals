package com.animals.controller;

import com.animals.dto.FoodDto;
import com.animals.dto.form.food.FormCreateFood;
import com.animals.dto.form.food.FormUpdateFood;
import com.animals.exceptions.FoodAlreadyExistsException;
import com.animals.exceptions.FoodNotFoundException;
import com.animals.modelo.Food;
import com.animals.service.FoodService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/food")
public class FoodController {

  @Autowired private FoodService foodService;

  @PostMapping
  public ResponseEntity<FoodDto> create(
      @RequestBody @Valid FormCreateFood form, UriComponentsBuilder uriBuilder)
      throws FoodAlreadyExistsException {
    Food foodSaved = foodService.createFood(form);
    URI uri = uriBuilder.path("/food/{id}").buildAndExpand(foodSaved.getId()).toUri();
    return ResponseEntity.created(uri).body(new FoodDto(foodSaved));
  }

  @GetMapping
  public List<FoodDto> read() {
    List<Food> foods = foodService.read();
    return FoodDto.convert(foods);
  }

  @GetMapping(value = {"/{name}"})
  public FoodDto readName(@PathVariable String name) throws FoodNotFoundException {
    return foodService.readByName(name);
  }

  @PutMapping("/{id}")
  public ResponseEntity<FoodDto> update(
      @PathVariable Long id, @RequestBody @Valid FormUpdateFood form)
      throws FoodAlreadyExistsException, FoodNotFoundException {
    Food food = foodService.update(id, form);
    return ResponseEntity.ok(new FoodDto(food));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<FoodDto> delete(@PathVariable Long id) throws FoodNotFoundException {
    foodService.delete(id);
    return ResponseEntity.ok().build();
  }
}
