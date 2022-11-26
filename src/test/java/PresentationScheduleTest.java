import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.section.PresentationSchedule;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PresentationScheduleTest {

    PresentationSchedule mainPresentationSchedule;

    @Before
    public void setUp() throws ExceptionList {
        mainPresentationSchedule = new PresentationSchedule("Saturday", "09:00", "10:30");
    }

    @Test
    public void hasConflictWithDifferentDayOfWeekTest() throws ExceptionList {
        var testPresentationSchedule = new PresentationSchedule("Sunday", "09:00", "10:30");

        Assert.assertFalse(mainPresentationSchedule.hasConflict(testPresentationSchedule));
    }

    @Test
    public void hasConflictWithStartAfterMainFinishTest() throws ExceptionList {
        var testPresentationSchedule = new PresentationSchedule("Saturday", "11:00", "13:30");
        Assert.assertFalse(mainPresentationSchedule.hasConflict(testPresentationSchedule));
    }

    @Test
    public void hasConflictWithStartAtMainFinishTest() throws ExceptionList {
        var testPresentationSchedule = new PresentationSchedule("Saturday", "10:30", "12:00");
        Assert.assertFalse(mainPresentationSchedule.hasConflict(testPresentationSchedule));
    }

    @Test
    public void hasConflictWithFinishBeforeMainStartTest() throws ExceptionList {
        var testPresentationSchedule = new PresentationSchedule("Saturday", "07:00", "08:30");
        Assert.assertFalse(mainPresentationSchedule.hasConflict(testPresentationSchedule));
    }

    @Test
    public void hasConflictWithFinishAtMainStartTest() throws ExceptionList {
        var testPresentationSchedule = new PresentationSchedule("Saturday", "07:30", "09:00");
        Assert.assertFalse(mainPresentationSchedule.hasConflict(testPresentationSchedule));
    }

    @Test
    public void hasConflictWithFinishBetweenMainTest() throws ExceptionList {
        var testPresentationSchedule = new PresentationSchedule("Saturday", "08:00", "10:00");
        Assert.assertTrue(mainPresentationSchedule.hasConflict(testPresentationSchedule));
    }

    @Test
    public void hasConflictWithStartBetweenMainTest() throws ExceptionList {
        var testPresentationSchedule = new PresentationSchedule("Saturday", "10:00", "12:00");
        Assert.assertTrue(mainPresentationSchedule.hasConflict(testPresentationSchedule));
    }

    @Test
    public void hasConflictWithAroundMainTest() throws ExceptionList {
        var testPresentationSchedule = new PresentationSchedule("Saturday", "08:00", "12:00");
        Assert.assertTrue(mainPresentationSchedule.hasConflict(testPresentationSchedule));
    }

    @After
    public void tearDown() {
        mainPresentationSchedule = null;
    }
}
