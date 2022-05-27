import com.animals.dto.form.animal.FormCreateAnimal;
import com.animals.dto.form.animal.FormUpdateAnimal;
import com.animals.dto.form.animalFamily.FormCreateAnimalFamily;
import com.animals.dto.form.animalFamily.FormUpdateAnimalFamily;
import com.animals.dto.form.food.FormCreateFood;
import com.animals.dto.form.food.FormUpdateFood;
import com.animals.exceptions.FoodAlreadyExistsException;

public class Util {
  public static FormCreateFood createFoodGeneric(String name) throws FoodAlreadyExistsException {
    FormCreateFood form = new FormCreateFood();
    form.setName(name);
    return form;
  }

  public static FormUpdateFood updateFoodGeneric(String name) {
    FormUpdateFood formUpdateFood = new FormUpdateFood();
    formUpdateFood.setName(name);
    return formUpdateFood;
  }

  public static FormCreateAnimalFamily createAnimalFamilyGeneric(String name) {
    FormCreateAnimalFamily form = new FormCreateAnimalFamily();
    form.setName(name);
    return form;
  }

  public static FormUpdateAnimalFamily formUpdateAnimalFamilyGeneric(String name) {
    FormUpdateAnimalFamily form = new FormUpdateAnimalFamily();
    form.setName(name);
    return form;
  }

  public static FormCreateAnimal formCreateAnimal(
      String name, Long legs, Long idFamily, Long idFood) {
    FormCreateAnimal formCreateAnimal = new FormCreateAnimal();
    formCreateAnimal.setName(name);
    formCreateAnimal.setLegs(legs);
    formCreateAnimal.setIdFamily(idFamily);
    formCreateAnimal.setIdFood(idFood);
    return formCreateAnimal;
  }

  public static FormUpdateAnimal getFormUpdateAnimal(
      String name, Long legs, Long idFamily, Long idFood) {
    FormUpdateAnimal formUpdateAnimal = new FormUpdateAnimal();
    formUpdateAnimal.setName(name);
    formUpdateAnimal.setLegs(legs);
    formUpdateAnimal.setIdFamily(idFamily);
    formUpdateAnimal.setIdFood(idFood);
    return formUpdateAnimal;
  }
}
