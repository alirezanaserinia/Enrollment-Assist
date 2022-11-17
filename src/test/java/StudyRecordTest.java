import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.section.PresentationSchedule;
import ir.proprog.enrollassist.domain.studyRecord.StudyRecord;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(value = Parameterized.class)
public class StudyRecordTest {

    private StudyRecord studyRecord;
    private String term;
    private String courseNumber;
    private String title;
    private int credits;
    private GraduateLevel graduateLevel;
    private double grade;
    private boolean isPassedExpected;

    public StudyRecordTest(String term, String courseNumber, String title, int credits, GraduateLevel graduateLevel, double grade, boolean expectedResult){
        this.term = term;
        this.courseNumber = courseNumber;
        this.title = title;
        this.credits = credits;
        this.graduateLevel = graduateLevel;
        this.grade = grade;
        this.isPassedExpected = expectedResult;
    }

    @Before
    public void setUp() throws ExceptionList {

    }


    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {"13991", "1010101", "SoftwareTest", 3, GraduateLevel.Undergraduate, 12.5, true},
                {"13992", "1010101", "SoftwareTest", 3, GraduateLevel.Undergraduate, 9, false}
        });
    }

    @Test
    public void isPassedTest() throws ExceptionList {
        Course course = new Course(courseNumber, title, credits, graduateLevel.toString());
        studyRecord = new StudyRecord(term, course, grade);
        Assert.assertEquals(isPassedExpected, studyRecord.isPassed(course.getGraduateLevel()));
    }


    @After
    public void tearDown() {

    }
}
