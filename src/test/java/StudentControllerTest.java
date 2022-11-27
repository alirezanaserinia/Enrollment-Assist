import com.google.common.collect.Iterables;
import ir.proprog.enrollassist.controller.student.StudentController;
import ir.proprog.enrollassist.controller.student.StudentView;
import ir.proprog.enrollassist.domain.student.Student;
import ir.proprog.enrollassist.domain.student.StudentNumber;
import ir.proprog.enrollassist.repository.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private SectionRepository sectionRepository;
    @Mock
    private EnrollmentListRepository enrollmentListRepository;
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private StudentController controller;

    @Test
    public void allTest() {
        Student arman = mock(Student.class);
        Student rohollah = mock(Student.class);
        Student arsham = mock(Student.class);

        when(studentRepository.findAll()).thenReturn(new ArrayList<Student>(Arrays.asList(arman, rohollah, arsham)));
        Assert.assertEquals(3, Iterables.size(controller.all()));
    }

    @Test
    public void oneTest() {
        Student arman = mock(Student.class);
        when(studentRepository.findByStudentNumber(any(StudentNumber.class))).thenReturn(java.util.Optional.ofNullable(arman));
        var temp = new StudentView(arman);
        var temp2 = controller.one("81019598");
//        Assert.assertEquals(new StudentView(arman), controller.one("810197598"));
        assertThat(controller.one("81019598"), samePropertyValuesAs(new StudentView(arman)));
        Assert.assertReflectionEquals(user1, user2);
    }

}
