import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.course.AddCourseService;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.repository.CourseRepository;
import ir.proprog.enrollassist.repository.ProgramRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class AddCourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ProgramRepository programRepository;

    @Rule public MockitoRule initRule = MockitoJUnit.rule();

    private Course course;

    private AddCourseService service;


    @BeforeEach
    public void setUp(){
        service = new AddCourseService(courseRepository, programRepository);
    }

    @Test
    public void checkLoopWithoutLoopTest() throws ExceptionList {
        Course math1 = new Course("1111111", "math1", 3, "Undergraduate");
        Course math2 = new Course("2222222", "math2", 3, "Undergraduate").withPre(math1);
        Course engMath = new Course("3333333", "engMath", 3, "Undergraduate").withPre(math2);

        ExceptionList exceptionList = new ExceptionList();
        this.service.checkLoop(engMath, exceptionList);

        Assert.assertEquals(0, exceptionList.getExceptions().size());
    }

    @Test
    public void checkLoopWithLoopTest() throws ExceptionList {
        Course math1 = new Course("1111111", "math1", 3, "Undergraduate");
        Course math2 = new Course("2222222", "math2", 3, "Undergraduate").withPre(math1);
        math1.withPre(math2);
        Course engMath = new Course("3333333", "engMath", 3, "Undergraduate").withPre(math2);

        ExceptionList exceptionList = new ExceptionList();
        this.service.checkLoop(engMath, exceptionList);

        Assert.assertEquals(1, exceptionList.getExceptions().size());
    }

    @AfterEach
    public void tearDown(){

    }

}
