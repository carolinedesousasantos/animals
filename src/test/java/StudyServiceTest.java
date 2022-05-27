import com.animals.Application;
import com.animals.dto.form.animalFamily.FormCreateAnimalFamily;
import com.animals.exceptions.AnimalFamilyAlreadyExistsException;
import com.animals.service.AnimalFamilyService;
import com.animals.service.StudyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudyServiceTest {

  @Autowired StudyService studyService;
  @Autowired AnimalFamilyService animalFamilyService;

  public void getLegsByFamilyId() throws AnimalFamilyAlreadyExistsException {
    int animalFamilySizeBefore = animalFamilyService.read().size();
    FormCreateAnimalFamily form = new FormCreateAnimalFamily();
    form.setName("new animal family");
    animalFamilyService.create(form);
    int animalFamilySizAfter = animalFamilyService.read().size();
    Assertions.assertEquals(animalFamilySizeBefore + 1, animalFamilySizAfter);
  }
}
