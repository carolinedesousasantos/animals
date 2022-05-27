import com.animals.Application;
import com.animals.dto.AnimalDto;
import com.animals.dto.form.animal.FormCreateAnimal;
import com.animals.dto.form.animal.FormUpdateAnimal;
import com.animals.dto.form.animalFamily.FormCreateAnimalFamily;
import com.animals.dto.form.food.FormCreateFood;
import com.animals.exceptions.*;
import com.animals.modelo.Animal;
import com.animals.modelo.AnimalFamily;
import com.animals.modelo.Food;
import com.animals.service.AnimalFamilyService;
import com.animals.service.AnimalService;
import com.animals.service.FoodService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimalServiceTest {

  @Autowired AnimalService animalService;

  @Autowired FoodService foodService;
  @Autowired AnimalFamilyService animalFamilyService;

  @Test
  public void createAnimal()
      throws AnimalFamilyNotFoundException, FoodNotFoundException, FoodAlreadyExistsException,
          AnimalFamilyAlreadyExistsException, AnimalAlreadyExistsException {

    int foodSizeBefore = foodService.read().size();
    FormCreateFood formFoodCreate = Util.createFoodGeneric("new food");
    Food food = foodService.createFood(formFoodCreate);
    int foodSizeAfter = foodService.read().size();
    Assertions.assertEquals(foodSizeBefore + 1, foodSizeAfter);

    int animalFamilySizeBefore = animalFamilyService.read().size();
    FormCreateAnimalFamily formAnimalFamilyCreate =
        Util.createAnimalFamilyGeneric("new animal family");
    AnimalFamily animalFamily = animalFamilyService.create(formAnimalFamilyCreate);
    int animalFamilySizeAfter = animalFamilyService.read().size();
    Assertions.assertEquals(animalFamilySizeBefore + 1, animalFamilySizeAfter);

    int animalSizeBeforeCreate = animalService.read().size();
    FormCreateAnimal formCreateAnimal =
        Util.formCreateAnimal("new animal", 4L, animalFamily.getId(), food.getId());
    animalService.create(formCreateAnimal);
    int animalSizeAfterCreate = animalService.read().size();
    Assertions.assertEquals(animalSizeBeforeCreate + 1, animalSizeAfterCreate);
  }

  @Test
  public void read() {
    int sizeBefore = 0;
    int sizeAfter = animalService.read().size();
    Assertions.assertNotEquals(sizeBefore, sizeAfter);
  }

  @Test
  public void readByName()
      throws AnimalFamilyNotFoundException, FoodNotFoundException, AnimalNotFoundException,
          AnimalAlreadyExistsException {
    int animalSizeBefore = animalService.read().size();
    FormCreateAnimal formCreateAnimal = Util.formCreateAnimal("new animal", 4L, 1L, 1L);
    Animal animalCreated = animalService.create(formCreateAnimal);
    int animalSizeAfter = animalService.read().size();
    Assertions.assertEquals(animalSizeBefore + 1, animalSizeAfter);

    AnimalDto animalDto = animalService.readName(animalCreated.getName());
    Assertions.assertEquals(animalDto.getName(), animalCreated.getName());
  }

  @Test
  public void readByLegs()
      throws AnimalFamilyNotFoundException, FoodNotFoundException, AnimalNotFoundException,
          AnimalAlreadyExistsException {
    int animalSizeBeforeCreate = animalService.read().size();
    FormCreateAnimal formCreateAnimal = Util.formCreateAnimal("new animal", 4L, 1L, 1L);
    Animal animalCreated = animalService.create(formCreateAnimal);
    int animalSizeAfterCreate = animalService.read().size();
    Assertions.assertEquals(animalSizeBeforeCreate + 1, animalSizeAfterCreate);

    int sizeBeforeRead = 0;
    int sizeAfterRead = animalService.readLegs(animalCreated.getLegs()).size();
    Assertions.assertNotEquals(sizeBeforeRead, sizeAfterRead);
  }

  @Test
  public void readByFoodName()
      throws FoodAlreadyExistsException, FoodNotFoundException, AnimalFamilyNotFoundException,
          AnimalAlreadyExistsException {
    int foodSizeBeforeCreate = foodService.read().size();
    FormCreateFood form = Util.createFoodGeneric("new food");
    Food food = foodService.createFood(form);
    int foodSizeAfterCreate = foodService.read().size();
    Assertions.assertEquals(foodSizeBeforeCreate + 1, foodSizeAfterCreate);

    int animalSizeBeforeCreate = animalService.read().size();
    FormCreateAnimal formCreateAnimal = Util.formCreateAnimal("new animal", 4L, 1L, food.getId());
    Animal animalCreated = animalService.create(formCreateAnimal);
    int animalSizeAfterCreate = animalService.read().size();
    Assertions.assertEquals(animalSizeBeforeCreate + 1, animalSizeAfterCreate);

    int sizeBeforeReadFoodName = 0;
    int sizeAfterReadFood = animalService.readByFoodName(animalCreated.getFood().getName()).size();
    Assertions.assertNotEquals(sizeBeforeReadFoodName, sizeAfterReadFood);
  }

  @Test
  public void update()
      throws AnimalFamilyNotFoundException, FoodNotFoundException, AnimalNotFoundException,
          AnimalAlreadyExistsException {
    int animalSizeBefore = animalService.read().size();
    FormCreateAnimal formCreateAnimal = Util.formCreateAnimal("new animal", 4L, 1L, 1L);
    Animal animal = animalService.create(formCreateAnimal);
    int animalSizeAfter = animalService.read().size();
    Assertions.assertEquals(animalSizeBefore + 1, animalSizeAfter);

    FormUpdateAnimal formUpdateAnimal = Util.getFormUpdateAnimal("new animal updated", 4L, 1L, 1L);
    animalService.update(animal.getId(), formUpdateAnimal);
    Animal animalUpdated = animalService.getById(animal.getId());
    Assertions.assertEquals("new animal updated", animalUpdated.getName());
  }

  @Test
  public void animalNotFoundException() {
    Assertions.assertThrows(
        AnimalNotFoundException.class,
        () -> {
          animalService.getById(-10L);
        });
  }

  @Test
  public void animalAlreadyExistsException()
      throws AnimalFamilyNotFoundException, FoodNotFoundException, AnimalAlreadyExistsException {
    int animalSizeBefore1 = animalService.read().size();
    FormCreateAnimal formCreateAnimal1 = Util.formCreateAnimal("new animal", 4L, 1L, 1L);
    animalService.create(formCreateAnimal1);
    int animalSizeAfter1 = animalService.read().size();
    Assertions.assertEquals(animalSizeBefore1 + 1, animalSizeAfter1);

    Assertions.assertThrows(
        AnimalAlreadyExistsException.class,
        () -> {
          int animalSizeBefore2 = animalService.read().size();
          FormCreateAnimal formCreateAnimal2 = Util.formCreateAnimal("new animal", 4L, 1L, 1L);
          animalService.create(formCreateAnimal2);
          int animalSizeAfter2 = animalService.read().size();
          Assertions.assertEquals(animalSizeBefore2 + 1, animalSizeAfter2);
        });
  }

  @Test
  public void deleteAnimal()
      throws AnimalFamilyNotFoundException, AnimalNotFoundException, FoodNotFoundException,
          AnimalAlreadyExistsException {
    int animalSizeBefore = animalService.read().size();
    FormCreateAnimal formCreateAnimal = Util.formCreateAnimal("new animal", 4L, 1L, 1L);
    Animal animal = animalService.create(formCreateAnimal);
    int animalSizeAfter = animalService.read().size();
    Assertions.assertEquals(animalSizeBefore + 1, animalSizeAfter);
    animalService.delete(animal.getId());
    Assertions.assertEquals(animalSizeAfter - 1, animalSizeBefore);
  }

  @Test
  public void deleteAnimalNotExists() {
    Assertions.assertThrows(
        AnimalNotFoundException.class,
        () -> {
          animalService.delete(-10L);
        });
  }
}
