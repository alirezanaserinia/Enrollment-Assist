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





    @After
    public void tearDown() {
        mainPresentationSchedule = null;
    }
}
