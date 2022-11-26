import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.studyRecord.StudyRecord;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudyRecordTest {
    private StudyRecord studyRecord;
    @Mock
    private Course course;
    private String term = "00001";
    private double grade = 16.5;

    @Before
    public void setup() throws ExceptionList {
        studyRecord = new StudyRecord(term, course, grade);
    }

    @After
    public void teardown() {
        studyRecord = null;
    }

    @Test
    public void testWeightedScore() {
        when(course.getCredits()).thenReturn(3);
        Assert.assertEquals(grade * 3, studyRecord.weightedScore(), 0.01);
    }

    @Test
    public void testIsPassedCallsGraduateMinValidGrade() {
        GraduateLevel graduateLevel = mock(GraduateLevel.class);
        studyRecord.isPassed(graduateLevel);
        verify(graduateLevel).getMinValidGrade();
    }

    @Test
    public void testIsPassed() {
        GraduateLevel graduateLevel = mock(GraduateLevel.class);
        when(graduateLevel.getMinValidGrade()).thenReturn(10.0);
        when(course.getGraduateLevel()).thenReturn(graduateLevel);
        Assert.assertTrue(studyRecord.isPassed(graduateLevel));
    }
}
