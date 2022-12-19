import com.google.gson.Gson;
import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.controller.course.CourseController;
import ir.proprog.enrollassist.controller.course.CourseMajorView;
import ir.proprog.enrollassist.domain.course.AddCourseService;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.repository.CourseRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CourseController.class)
@ContextConfiguration(classes=CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private AddCourseService addCourseService;

    @Test
    public void When_GettingOneCourseByValidId_Expect_ReturnValidCourseView() throws Exception {
        Course math1 = new Course("4444444", "MATH1", 3, "Undergraduate");
        when(courseRepository.findById(any(Long.class))).thenReturn(java.util.Optional.of(math1));
        mockMvc.perform(get("/courses/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.courseNumber.courseNumber").value("4444444"))
                .andExpect(jsonPath("$.courseTitle").value("MATH1"));
    }

    @Test
    public void When_GettingOneCourseByInValidId_Expect_ReturnNotFoundStatusException() throws Exception {
        when(courseRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(null));
        mockMvc.perform(get("/courses/{id}", 2L))
                .andExpect(status().isNotFound())
                .andExpect(status().reason("Course not found"));
    }

    @Test
    public void When_GettingAllCourses_Expect_ReturnValidCourseViewsList() throws Exception {
        Course math1 = new Course("4444444", "MATH1", 3, "Undergraduate");
        Course phys1 = new Course("8888888", "PHYS1", 3, "Undergraduate");
        Course prog = new Course("7777777", "PROG", 4, "Undergraduate");

        when(courseRepository.findAll()).thenReturn(new ArrayList<Course>(Arrays.asList(math1, phys1, prog)));
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].courseNumber.courseNumber").value("4444444"))
                .andExpect(jsonPath("$[0].courseTitle").value("MATH1"))
                .andExpect(jsonPath("$[1].courseNumber.courseNumber").value("8888888"))
                .andExpect(jsonPath("$[1].courseTitle").value("PHYS1"))
                .andExpect(jsonPath("$[2].courseNumber.courseNumber").value("7777777"))
                .andExpect(jsonPath("$[2].courseTitle").value("PROG"));
    }

    @Test
    public void When_AddNewValidCourse_Expect_ReturnAddedNewCourseView() throws Exception {
        Course math1 = new Course("4444444", "MATH1", 3, "Undergraduate");
        when(addCourseService.addCourse(any(CourseMajorView.class))).thenReturn(math1);

        Gson gson = new Gson();
        Set<Long> prerequisites = Collections.<Long>emptySet();
        Set<Long> programs = Collections.<Long>emptySet();
        CourseMajorView courseMajorView= new CourseMajorView(math1, prerequisites, programs);
        String courseMajorViewJson = gson.toJson(courseMajorView);

        mockMvc.perform(post("/courses")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(courseMajorViewJson)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.courseNumber.courseNumber").value("4444444"))
                .andExpect(jsonPath("$.courseTitle").value("MATH1"));
    }

    @Test
    public void When_AddNewCourseAlreadyExists_Expect_ReturnBadRequestStatusException() throws Exception {
        Course math1 = new Course("4444444", "MATH1", 3, "Undergraduate");
        ExceptionList exceptionList = new ExceptionList();
        exceptionList.addNewException(new Exception("Course number already exists."));
        when(addCourseService.addCourse(any(CourseMajorView.class))).thenThrow(exceptionList);

        Gson gson = new Gson();
        Set<Long> prerequisites = Collections.<Long>emptySet();
        Set<Long> programs = Collections.<Long>emptySet();
        CourseMajorView courseMajorView= new CourseMajorView(math1, prerequisites, programs);
        String courseMajorViewJson = gson.toJson(courseMajorView);

        mockMvc.perform(post("/courses")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(courseMajorViewJson)
        )
                .andExpect(status().isBadRequest())
                .andExpect(status().reason(exceptionList.toString()));
    }
}
