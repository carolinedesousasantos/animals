import com.animals.Application;
import com.animals.dto.AnimalFamilyDto;
import com.animals.dto.form.animalFamily.FormCreateAnimalFamily;
import com.animals.dto.form.animalFamily.FormUpdateAnimalFamily;
import com.animals.exceptions.AnimalFamilyAlreadyExistsException;
import com.animals.exceptions.AnimalFamilyNotFoundException;
import com.animals.modelo.AnimalFamily;
import com.animals.service.AnimalFamilyService;
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
public class AnimalFamilyServiceTest {

  @Autowired AnimalFamilyService service;

  @Test
  public void create() throws AnimalFamilyAlreadyExistsException {
    int animalFamilySizeBefore = service.read().size();
    FormCreateAnimalFamily form = Util.createAnimalFamilyGeneric("new animal family");
    service.create(form);
    int animalFamilySizeAfter = service.read().size();
    Assertions.assertEquals(animalFamilySizeBefore + 1, animalFamilySizeAfter);
  }

  @Test
  public void createFamilyAlreadyExists() throws AnimalFamilyAlreadyExistsException {
    int sizeBefore = service.read().size();
    FormCreateAnimalFamily form = Util.createAnimalFamilyGeneric("new animal family");
    service.create(form);
    int sizeAfter = service.read().size();
    Assertions.assertEquals(sizeBefore + 1, sizeAfter);

    Assertions.assertThrows(
        AnimalFamilyAlreadyExistsException.class,
        () -> {
          int sizeBefore2 = service.read().size();
          FormCreateAnimalFamily form2 = Util.createAnimalFamilyGeneric("new animal family");
          service.create(form2);
          int sizeAfter2 = service.read().size();
          Assertions.assertEquals(sizeBefore2 + 1, sizeAfter2);
        });
  }

  @Test
  public void read() {
    int sizeBefore = 0;
    List<AnimalFamily> animalFamily = service.read();
    int sizeAfter = animalFamily.size();
    Assertions.assertNotEquals(sizeBefore, sizeAfter);
  }

  @Test
  public void readByName()
      throws AnimalFamilyAlreadyExistsException, AnimalFamilyNotFoundException {
    int sizeBeforeCreate = service.read().size();
    FormCreateAnimalFamily form = Util.createAnimalFamilyGeneric("new animal family");
    AnimalFamily animalFamily = service.create(form);
    int sizeAfterCreate = service.read().size();
    Assertions.assertEquals(sizeBeforeCreate + 1, sizeAfterCreate);

    AnimalFamilyDto animalFamilyDto = service.readByName(animalFamily.getName());
    Assertions.assertEquals(animalFamily.getName(), animalFamilyDto.getName());
  }

  @Test
  public void update() throws AnimalFamilyAlreadyExistsException, AnimalFamilyNotFoundException {
    int sizeBeforeCreate = service.read().size();
    FormCreateAnimalFamily formCreate = Util.createAnimalFamilyGeneric("new animal family");
    AnimalFamily animalFamily = service.create(formCreate);
    int sizeAfterCreate = service.read().size();
    Assertions.assertEquals(sizeBeforeCreate + 1, sizeAfterCreate);

    FormUpdateAnimalFamily formUpdate =
        Util.formUpdateAnimalFamilyGeneric("new animal family updated");
    service.update(animalFamily.getId(), formUpdate);
    AnimalFamily animalFamilyUpdated = service.getById(animalFamily.getId());
    Assertions.assertEquals("new animal family updated", animalFamilyUpdated.getName());
  }

  @Test
  public void updateAlreadyExists() throws AnimalFamilyAlreadyExistsException {
    int sizeBeforeCreate = service.read().size();
    FormCreateAnimalFamily formCreate = Util.createAnimalFamilyGeneric("new animal family");
    AnimalFamily animalFamily = service.create(formCreate);
    int sizeAfterCreate = service.read().size();
    Assertions.assertEquals(sizeBeforeCreate + 1, sizeAfterCreate);

    Assertions.assertThrows(
        AnimalFamilyAlreadyExistsException.class,
        () -> {
          FormUpdateAnimalFamily formUpdate =
              Util.formUpdateAnimalFamilyGeneric("new animal family");
          service.update(animalFamily.getId(), formUpdate);
          AnimalFamily animalFamilyUpdated = service.getById(animalFamily.getId());
          Assertions.assertEquals("new animal family updated", animalFamilyUpdated.getName());
        });
  }

  @Test
  public void delete() throws AnimalFamilyAlreadyExistsException, AnimalFamilyNotFoundException {
    int sizeBeforeCreate = service.read().size();
    FormCreateAnimalFamily formCreate = Util.createAnimalFamilyGeneric("new animal family");
    AnimalFamily animalFamily = service.create(formCreate);
    int sizeAfterCreate = service.read().size();
    Assertions.assertEquals(sizeBeforeCreate + 1, sizeAfterCreate);

    service.delete(animalFamily.getId());
    Assertions.assertEquals(sizeAfterCreate - 1, sizeBeforeCreate);
  }

  @Test
  public void deleteAnimalFamilyNotExists() {
    Assertions.assertThrows(
        AnimalFamilyNotFoundException.class,
        () -> {
          service.delete(-10L);
        });
  }
}
