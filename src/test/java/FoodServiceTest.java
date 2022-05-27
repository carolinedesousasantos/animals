import com.animals.Application;
import com.animals.dto.FoodDto;
import com.animals.dto.form.food.FormCreateFood;
import com.animals.dto.form.food.FormUpdateFood;
import com.animals.exceptions.FoodAlreadyExistsException;
import com.animals.exceptions.FoodNotFoundException;
import com.animals.modelo.Food;
import com.animals.service.FoodService;
import java.util.List;
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
public class FoodServiceTest {

  @Autowired FoodService foodService;

  @Test
  public void createFood() throws FoodAlreadyExistsException {
    int foodSizeBefore = foodService.read().size();
    FormCreateFood form = Util.createFoodGeneric("new food");
    foodService.createFood(form);
    int foodSizeAfter = foodService.read().size();
    Assertions.assertEquals(foodSizeBefore + 1, foodSizeAfter);
  }

  @Test
  public void createFoodAlreadyExists() {
    Assertions.assertThrows(
        FoodAlreadyExistsException.class,
        () -> {
          FormCreateFood form1 = Util.createFoodGeneric("new food");
          foodService.createFood(form1);
          FormCreateFood form2 = Util.createFoodGeneric("new food");
          foodService.createFood(form2);
        });
  }

  @Test
  public void read() {
    int sizeBefore = 0;
    List<Food> food = foodService.read();
    int sizeAfter = food.size();
    Assertions.assertNotEquals(sizeBefore, sizeAfter);
  }

  @Test
  public void readByName() throws FoodAlreadyExistsException, FoodNotFoundException {
    FormCreateFood form1 = new FormCreateFood();
    form1.setName("new food");
    Food foodCreated = foodService.createFood(form1);
    FoodDto foodDto = foodService.readByName(foodCreated.getName());
    Assertions.assertEquals("new food", foodDto.getName());
  }

  @Test
  public void updateFood() throws FoodAlreadyExistsException, FoodNotFoundException {
    int foodSizeBefore = foodService.read().size();
    FormCreateFood form = Util.createFoodGeneric("new food");
    Food food = foodService.createFood(form);
    int foodSizeAfter = foodService.read().size();
    Assertions.assertEquals(foodSizeBefore + 1, foodSizeAfter);
    FormUpdateFood formUpdateFood = Util.updateFoodGeneric("new food updated");
    foodService.update(food.getId(), formUpdateFood);
    Food foodUpdated = foodService.getById(food.getId());
    Assertions.assertEquals("new food updated", foodUpdated.getName());
  }

  @Test
  public void updateFoodAlreadyExistsException() throws FoodAlreadyExistsException {
    int foodSizeBefore = foodService.read().size();
    FormCreateFood form = Util.createFoodGeneric("new food");
    Food food = foodService.createFood(form);
    int foodSizeAfter = foodService.read().size();
    Assertions.assertEquals(foodSizeBefore + 1, foodSizeAfter);

    Assertions.assertThrows(
        FoodAlreadyExistsException.class,
        () -> {
          FormUpdateFood formUpdateFood = Util.updateFoodGeneric("new food");
          foodService.update(food.getId(), formUpdateFood);
        });
  }

  @Test
  public void foodNotFoundException() {
    Assertions.assertThrows(
        FoodNotFoundException.class,
        () -> {
          foodService.getById(-10L);
        });
  }

  @Test
  public void deleteFood() throws FoodAlreadyExistsException, FoodNotFoundException {
    int foodSizeBefore = foodService.read().size();
    FormCreateFood form = Util.createFoodGeneric("new food");
    Food food = foodService.createFood(form);
    int foodSizeAfter = foodService.read().size();
    Assertions.assertEquals(foodSizeBefore + 1, foodSizeAfter);
    foodService.delete(food.getId());
    Assertions.assertEquals(foodSizeAfter - 1, foodSizeBefore);
  }

  @Test
  public void deleteFoodNotExists() {
    Assertions.assertThrows(
        FoodNotFoundException.class,
        () -> {
          foodService.delete(-10L);
        });
  }
}
