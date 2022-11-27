import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.course.AddCourseService;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.repository.CourseRepository;
import ir.proprog.enrollassist.repository.ProgramRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;


@RunWith(MockitoJUnitRunner.class)
public class AddCourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ProgramRepository programRepository;

    private Course course;

    private AddCourseService service;


    @BeforeEach
    public void setUp(){

        service = new AddCourseService(courseRepository, programRepository);
    }

    @Test
    public void checkLoopTest() throws ExceptionList {
        Course math1 = new Course("11", "math1", 3, "Undergraduate");
        Course math2 = new Course("11", "math2", 3, "Undergraduate").withPre(math1);
        Course engMath = new Course("11", "engMath", 3, "Undergraduate").withPre(math2);

        ExceptionList exceptionList = new ExceptionList();
        this.service.checkLoop(engMath, exceptionList);

        Assert.assertEquals(0, exceptionList.getExceptions().size());



    }

    @AfterEach
    public void tearDown(){

    }

}
