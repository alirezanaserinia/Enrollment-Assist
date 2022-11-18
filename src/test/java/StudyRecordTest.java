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

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][] {
                {"13991", "1010101", "Computer Networks", 3, GraduateLevel.Undergraduate, 12.5, true},
                {"13991", "1010101", "Computer Networks", 3, GraduateLevel.Undergraduate, 10, true},
                {"13992", "1010101", "Computer Networks", 3, GraduateLevel.Undergraduate, 9, false},
                {"13993", "1234567", "Advanced CN", 3, GraduateLevel.Masters, 11.9, false},
                {"13993", "1234567", "Advanced CN", 3, GraduateLevel.Masters, 12, true},
                {"13991", "1234567", "Advanced CN", 3, GraduateLevel.Masters, 20, true},
                {"14012", "1010101", "Stochastic Processing", 3, GraduateLevel.PHD, 9, false},
                {"14013", "1010101", "Stochastic Processing", 3, GraduateLevel.PHD, 14, true},
                {"14013", "1010101", "Stochastic Processing", 3, GraduateLevel.PHD, 14.1, true}
        });
    }

    @Test
    public void isPassedTest() throws ExceptionList {
        Course course = new Course(courseNumber, title, credits, graduateLevel.toString());
        studyRecord = new StudyRecord(term, course, grade);
        Assert.assertEquals(isPassedExpected, studyRecord.isPassed(course.getGraduateLevel()));
    }
}
