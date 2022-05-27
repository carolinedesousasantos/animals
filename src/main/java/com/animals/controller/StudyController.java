package com.animals.controller;

import com.animals.dto.FoodByFamilyDto;
import com.animals.dto.LegsByFamilyDto;
import com.animals.service.StudyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/study")
public class StudyController {

  @Autowired private StudyService service;

  @GetMapping(value = {"/legs/id/{familyId}"})
  public List<LegsByFamilyDto> getLegByFamilyId(@PathVariable Long familyId) {
    return service.getLegsByFamilyId(familyId);
  }

  @GetMapping(value = {"/legs/name/{familyName}"})
  public List<LegsByFamilyDto> getLegByFamilyName(@PathVariable String familyName) {
    return service.getLegsByFamilyName(familyName);
  }

  @GetMapping(value = {"/food/id/{familyId}"})
  public List<FoodByFamilyDto> getFoodByFamilyId(@PathVariable Long familyId) {
    return service.getFoodByFamilyId(familyId);
  }

  @GetMapping(value = {"/food/name/{familyName}"})
  public List<FoodByFamilyDto> getAnimalsByFoodName(@PathVariable String familyName) {
    return service.getFoodByFamilyName(familyName);
  }
}
