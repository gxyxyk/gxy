import com.entity.Animal;
import com.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans.xml"})
public class Tests {
    @Autowired
    public Animal animal;
    @Autowired
    public Student student;
    @Test
    public void test1(){
        animal.eat();
    }
}
