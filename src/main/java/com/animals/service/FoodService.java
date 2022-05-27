package com.animals.service;

import com.animals.dto.FoodDto;
import com.animals.dto.form.food.FormCreateFood;
import com.animals.dto.form.food.FormUpdateFood;
import com.animals.exceptions.FoodNotFoundException;
import com.animals.modelo.Food;
import com.animals.repository.FoodRepository;
import com.animals.exceptions.FoodAlreadyExistsException;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FoodService {

  @Autowired private FoodRepository foodRepository;

  public Food createFood(FormCreateFood form) throws FoodAlreadyExistsException {
    Optional<Food> foodName = foodRepository.findByName(form.getName());
    if (foodName.isPresent()) throw new FoodAlreadyExistsException();
    Food food = new Food(form.getName());
    return foodRepository.save(food);
  }

  public List<Food> read() {
    return foodRepository.findAll();
  }

  public FoodDto readByName(String name) throws FoodNotFoundException {
    Optional<Food> food = foodRepository.findByName(name);
    if (food.isPresent()) {
      return new FoodDto(food.get());
    } else {
      throw new FoodNotFoundException();
    }
  }

  public Food update(Long id, FormUpdateFood form)
      throws FoodAlreadyExistsException, FoodNotFoundException {
    Optional<Food> foodId = foodRepository.findById(id);
    if (foodId.isPresent()) {
      Optional<Food> foodName = foodRepository.findByName(form.getName());
      if (foodName.isPresent()) {
        throw new FoodAlreadyExistsException();
      } else {
        Food food = form.update(foodId.get());
        return food;
      }
    }
    throw new FoodNotFoundException();
  }

  public void delete(Long id) throws FoodNotFoundException {
    Optional<Food> food = foodRepository.findById(id);
    if (food.isPresent()) {
      foodRepository.deleteById(id);
    } else {
      throw new FoodNotFoundException();
    }
  }

  public Food getById(Long id) throws FoodNotFoundException {
    Optional<Food> food = foodRepository.findById(id);
    if (food.isPresent()) {
      return food.get();
    } else {
      throw new FoodNotFoundException();
    }
  }
}
