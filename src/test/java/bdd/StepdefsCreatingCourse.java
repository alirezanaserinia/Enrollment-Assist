package bdd;
import cucumber.api.java.en.Given;
        import cucumber.api.java.en.Then;
        import cucumber.api.java.en.When;
        import io.cucumber.java.Before;
        import io.cucumber.java.BeforeAll;
        import io.cucumber.java.PendingException;
        import ir.proprog.enrollassist.Exception.ExceptionList;
        import ir.proprog.enrollassist.domain.EnrollmentRules.EnrollmentRuleViolation;
        import ir.proprog.enrollassist.domain.course.Course;
        import ir.proprog.enrollassist.domain.course.CourseNumber;
        import ir.proprog.enrollassist.domain.major.Major;
        import ir.proprog.enrollassist.domain.program.Program;
        import ir.proprog.enrollassist.domain.student.Student;
        import ir.proprog.enrollassist.domain.student.StudentNumber;
        import ir.proprog.enrollassist.repository.StudentRepository;
        import org.junit.jupiter.api.BeforeEach;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.Objects;

        import static org.junit.jupiter.api.Assertions.*;

public class StepdefsCreatingCourse {
    private String courseNumber= "1234567";
    private String title= "Good Book";
    private int credits= 3;
    private String graduateLevel= "Undergraduate";
    List<Exception> exceptionList;
    Course course;

    @When("^creating a course with title and credit and graduateLevel$")
    public void creating_a_course_with_title_and_credit_and_graduateLevel() throws Throwable {
        try {
            course= new Course("",title,credits,graduateLevel);
        }
        catch (ExceptionList e){
            exceptionList= e.getExceptions();
        }
    }

    @Then("^CourseNumber exception throws$")
    public void coursenumber_exception_throws() throws Throwable {
        assertEquals("java.lang.Exception: Course number cannot be empty.",
                exceptionList.get(0).toString());
    }

    @When("^creating a course with courseNumber and title and graduateLevel$")
    public void creating_a_course_with_courseNumber_and_title_and_graduateLevel() throws Throwable {
        try {
            course= new Course(courseNumber,title,-3,graduateLevel);
        }
        catch (ExceptionList e){
            exceptionList= e.getExceptions();
        }
    }

    @Then("^Credit exception throws$")
    public void credit_exception_throws() throws Throwable {
        assertEquals("java.lang.Exception: Credit must be one of the following values: 0, 1, 2, 3, 4.",
                exceptionList.get(0).toString());
    }

    @When("^creating a course with courseNumber and title and credit$")
    public void creating_a_course_with_courseNumber_and_title_and_credit() throws Throwable {
        try {
            course= new Course(courseNumber,title,credits,"No Graduate");
        }
        catch (ExceptionList e){
            exceptionList= e.getExceptions();
        }
    }

    @Then("^GraduateLevel exception throws$")
    public void graduatelevel_exception_throws() throws Throwable {
        assertEquals("java.lang.Exception: Graduate level is not valid.",
                exceptionList.get(0).toString());
    }

    @When("^creating a course with courseNumber and credit and graduateLevel$")
    public void creating_a_course_with_courseNumber_and_credit_and_graduateLevel() throws Throwable {
        try {
            course= new Course(courseNumber,"",credits,graduateLevel);
        }
        catch (ExceptionList e){
            exceptionList= e.getExceptions();
        }
    }

    @Then("^Title exception throws$")
    public void title_exception_throws() throws Throwable {
        assertEquals("java.lang.Exception: Course must have a name.",
                exceptionList.get(0).toString());
    }
}
