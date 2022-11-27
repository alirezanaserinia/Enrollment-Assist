import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.course.AddCourseService;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.repository.CourseRepository;
import ir.proprog.enrollassist.repository.ProgramRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddCourseServiceTest {

    @Mock
    private CourseRepository courseRepository;
    @Mock
    private ProgramRepository programRepository;

    @InjectMocks
    private AddCourseService service;

    @Test
    public void checkLoopWithoutLoopSimpleTest() {
        Course math1 = mock(Course.class);
        Course math2 = mock(Course.class);

        when(math2.getPrerequisites()).thenReturn(new HashSet<>(Arrays.asList(math1)));
        ExceptionList exceptionList = new ExceptionList();
        this.service.checkLoop(math2, exceptionList);
        Assert.assertEquals(0, exceptionList.getExceptions().size());
    }

    @Test
    public void checkLoopWithoutLoopTest() {
        Course math1 = mock(Course.class);
        Course math2 = mock(Course.class);
        Course engMath = mock(Course.class);

        when(math2.getPrerequisites()).thenReturn(new HashSet<>(Arrays.asList(math1)));
        when(engMath.getPrerequisites()).thenReturn(new HashSet<>(Arrays.asList(math2)));

        ExceptionList exceptionList = new ExceptionList();
        this.service.checkLoop(engMath, exceptionList);
        Assert.assertEquals(0, exceptionList.getExceptions().size());
    }

    @Test
    public void checkLoopWithLoopTest() {
        Course math1 = mock(Course.class);
        Course math2 = mock(Course.class);
        Course engMath = mock(Course.class);

        when(math2.getPrerequisites()).thenReturn(new HashSet<>(Arrays.asList(math1)));
        when(math1.getPrerequisites()).thenReturn(new HashSet<>(Arrays.asList(math2)));
        when(engMath.getPrerequisites()).thenReturn(new HashSet<>(Arrays.asList(math2)));

        ExceptionList exceptionList = new ExceptionList();
        this.service.checkLoop(engMath, exceptionList);
        Assert.assertEquals(1, exceptionList.getExceptions().size());
    }

}
