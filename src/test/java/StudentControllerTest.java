import com.google.common.collect.Iterables;
import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.controller.student.StudentController;
import ir.proprog.enrollassist.controller.student.StudentView;
import ir.proprog.enrollassist.domain.section.Section;
import ir.proprog.enrollassist.domain.student.Student;
import ir.proprog.enrollassist.domain.student.StudentNumber;
import ir.proprog.enrollassist.domain.user.User;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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


    // studentRepository:stub, arman:dummy, rohollah:dummy, arsham:dummy
    // State Verification
    // Mockisty
    @Test
    public void allTest() {
        Student arman = mock(Student.class);
        Student rohollah = mock(Student.class);
        Student arsham = mock(Student.class);

        when(studentRepository.findAll()).thenReturn(new ArrayList<Student>(Arrays.asList(arman, rohollah, arsham)));
        Assert.assertEquals(3, Iterables.size(controller.all()));
    }

    // studentRepository:stub, arman:dummy
    // State Verification
    // Mockisty
    @Test
    public void oneTest() {
        Student arman = mock(Student.class);
        when(studentRepository.findByStudentNumber(any(StudentNumber.class))).thenReturn(java.util.Optional.ofNullable(arman));
        Assert.assertEquals(new StudentView(arman).getStudentId(), controller.one("810197598").getStudentId());
    }

    // user:stub, studentRepository:stub, userRepository:stub
    // State Verification
    // Classical
    @Test
    public void addStudentTest() throws ExceptionList {
        User user = mock(User.class);
        Student student = new Student("810198533", "Undergraduate");
        StudentView studentView = new StudentView(student);
        when(user.getUserId()).thenReturn("1234");
        when(studentRepository.findByStudentNumber(any(StudentNumber.class))).thenReturn(java.util.Optional.ofNullable(null));
        when(userRepository.findByUserId(studentView.getUserId())).thenReturn(java.util.Optional.ofNullable(user));
        when(studentRepository.save(any(Student.class))).thenReturn(null);
        when(userRepository.save(any(User.class))).thenReturn(null);

        Assert.assertEquals(studentView.getStudentNo(), controller.addStudent(studentView).getStudentNo());
    }

    // studentRepository:stub, sec1:dummy, sec2:dummy, sec3:dummy
    // State Verification
    // Classical
    @Test
    public void findTakeableSectionsByMajorTest() throws ExceptionList {
        Section sec1 = mock(Section.class);
        Section sec2 = mock(Section.class);
        Section sec3 = mock(Section.class);
        Student student = new Student("810198533", "Undergraduate");
        when(studentRepository.findByStudentNumber(any(StudentNumber.class))).thenReturn(java.util.Optional.ofNullable(student));
        when(sectionRepository.findAll()).thenReturn(Arrays.asList(sec1, sec2, sec3));

        Assert.assertEquals(0, 1);
    }

}
