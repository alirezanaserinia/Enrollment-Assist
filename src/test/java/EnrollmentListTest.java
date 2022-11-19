import ir.proprog.enrollassist.domain.EnrollmentRules.EnrollmentRuleViolation;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.enrollmentList.EnrollmentList;
import ir.proprog.enrollassist.domain.major.Major;
import ir.proprog.enrollassist.domain.program.Program;
import ir.proprog.enrollassist.domain.section.ExamTime;
import ir.proprog.enrollassist.domain.section.PresentationSchedule;
import ir.proprog.enrollassist.domain.section.Section;
import ir.proprog.enrollassist.domain.student.Student;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

@RunWith(value = Parameterized.class)
public class EnrollmentListTest {

    private EnrollmentList enrollmentList;
    private String listName;
    private List<Course> courses;
    private List<ExamTime> examTimes;
    private List<PresentationSchedule> preScheds;
    private List<Section> sections;
    private int expectedViolationsCounts;
    private int[] testSections;
    private Major ce;
    private Program ceProgram;
    private Student arman;

    @Before
    public void setUp() throws Exception {
        setCourses();
        setExamTimes();
        setPreScheds();
        setSections();
        ce = new Major("8101", "CE", "Engineering");
        ceProgram = new Program(ce, "Undergraduate", 140, 140, "Major");
        ceProgram.addCourse(this.courses.get(0), this.courses.get(3), this.courses.get(2), this.courses.get(5));

        arman = new Student("810101999", "Undergraduate")
                .setGrade("11112", this.courses.get(0), 11.5);

        arman.addProgram(ceProgram);
    }

    public EnrollmentListTest(int expectedViolationsCounts, String listName, int[] testSections) throws Exception {
        this.listName = listName;
        this.testSections = testSections;
        this.expectedViolationsCounts = expectedViolationsCounts;
    }

    private void setCourses() throws Exception {
        Course math1 = new Course("4444444", "MATH1", 3, "Undergraduate");
        Course phys1 = new Course("8888888", "PHYS1", 3, "Undergraduate");
        Course prog = new Course("7777777", "PROG", 4, "Undergraduate");
        Course math2 = new Course("6666666", "MATH2", 3, "Undergraduate").withPre(math1);
        Course phys2 = new Course("9999999", "PHYS2", 3, "Undergraduate").withPre(math1, phys1);
        Course ap = new Course("2222222", "AP", 3, "Undergraduate").withPre(prog);
        Course maaref = new Course("5555555", "MAAREF", 2, "Undergraduate");
        Course farsi = new Course("1212121", "FA", 3, "Undergraduate");
        this.courses = new ArrayList<Course>(Arrays.asList(math1, phys1, prog, math2, phys2, ap, maaref, farsi));
    }

    private void setExamTimes() throws Exception {
        ExamTime exam0 = new ExamTime("2021-07-10T09:00", "2021-07-10T11:00");
        ExamTime exam1 = new ExamTime("2021-07-11T09:00", "2021-07-11T11:00");
        ExamTime exam2 = new ExamTime("2021-07-12T09:00", "2021-07-12T11:00");
        ExamTime exam3 = new ExamTime("2021-07-13T09:00", "2021-07-13T11:00");
        ExamTime exam4 = new ExamTime("2021-07-14T09:00", "2021-07-14T11:00");
        ExamTime exam5 = new ExamTime("2021-07-15T09:00", "2021-07-15T11:00");
        ExamTime exam6 = new ExamTime("2021-07-16T09:00", "2021-07-16T11:00");
        ExamTime exam7 = new ExamTime("2021-07-17T09:00", "2021-07-17T11:00");
        this.examTimes = new ArrayList<ExamTime>(Arrays.asList(exam0, exam1, exam2, exam3, exam4, exam5, exam6, exam7));
    }

    private void setPreScheds() throws Exception {
        PresentationSchedule PreSched_Sat_1 = new PresentationSchedule("Saturday", "07:30", "09:00");
        PresentationSchedule PreSched_Sat_2 = new PresentationSchedule("Saturday", "09:00", "10:30");
        PresentationSchedule PreSched_Sat_3 = new PresentationSchedule("Saturday", "10:30", "12:00");
        PresentationSchedule PreSched_Sun_1 = new PresentationSchedule("Sunday", "07:30", "09:00");
        PresentationSchedule PreSched_Sun_2 = new PresentationSchedule("Sunday", "09:00", "10:30");
        PresentationSchedule PreSched_Sun_3 = new PresentationSchedule("Sunday", "10:30", "12:00");
        PresentationSchedule PreSched_Mon_1 = new PresentationSchedule("Monday", "07:30", "09:00");
        PresentationSchedule PreSched_Mon_2 = new PresentationSchedule("Monday", "09:00", "10:30");
        PresentationSchedule PreSched_Mon_3 = new PresentationSchedule("Monday", "10:30", "12:00");
        PresentationSchedule PreSched_Tue_1 = new PresentationSchedule("Tuesday", "07:30", "09:00");
        PresentationSchedule PreSched_Tue_2 = new PresentationSchedule("Tuesday", "09:00", "10:30");
        PresentationSchedule PreSched_Tue_3 = new PresentationSchedule("Tuesday", "10:30", "12:00");
        PresentationSchedule PreSched_Wed_1 = new PresentationSchedule("Wednesday", "08:00", "10:00");
        PresentationSchedule PreSched_Wed_2 = new PresentationSchedule("Wednesday", "10:00", "12:00");
        PresentationSchedule PreSched_Wed_3 = new PresentationSchedule("Wednesday", "12:00", "14:00");
        this.preScheds = new ArrayList<PresentationSchedule>(Arrays.asList(PreSched_Sat_1, PreSched_Sat_2,
                PreSched_Sat_3, PreSched_Sun_1, PreSched_Sun_2, PreSched_Sun_3, PreSched_Mon_1, PreSched_Mon_2,
                PreSched_Mon_3, PreSched_Tue_1, PreSched_Tue_2, PreSched_Tue_3, PreSched_Wed_1, PreSched_Wed_2,
                PreSched_Wed_3));
    }

    private void setSections() throws Exception {
        Section math1_1 = new Section(this.courses.get(0), "01", this.examTimes.get(0),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(0), this.preScheds.get(6))));
        Section phys1_1 = new Section(this.courses.get(1), "01", this.examTimes.get(1),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(1), this.preScheds.get(7))));
        Section prog_1  = new Section(this.courses.get(2), "01", this.examTimes.get(2),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(2), this.preScheds.get(8))));
        Section math2_1 = new Section(this.courses.get(3), "01", this.examTimes.get(0),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(0), this.preScheds.get(6))));
        Section math2_2 = new Section(this.courses.get(3), "02", this.examTimes.get(0),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(3), this.preScheds.get(9))));
        Section phys2_1 = new Section(this.courses.get(4), "01", this.examTimes.get(1),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(1), this.preScheds.get(7))));
        Section phys2_2 = new Section(this.courses.get(4), "02", this.examTimes.get(1),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(1), this.preScheds.get(7))));
        Section ap_1    = new Section(this.courses.get(5), "01", this.examTimes.get(2),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(4), this.preScheds.get(10))));
        Section farsi_1 = new Section(this.courses.get(7), "10", this.examTimes.get(3),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(12))));
        Section maaref_1 = new Section(this.courses.get(6), "10", this.examTimes.get(4),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(13))));
        Section maaref_2 = new Section(this.courses.get(6), "10", this.examTimes.get(5),
                new HashSet<PresentationSchedule>(Arrays.asList(this.preScheds.get(2))));
        this.sections = new ArrayList<Section>(Arrays.asList(math1_1, phys1_1, prog_1, math2_1,
                math2_2, phys2_1, phys2_2, ap_1, farsi_1, maaref_1, maaref_2));
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() throws Exception {
        return Arrays.asList(new Object[][] {
                {0, "goodList", new int[]{1, 2, 3, 8}},
                {1, "CourseHasRequestedTwice", new int[]{1, 2, 8, 8}},
                {1, "AlreadyPassedCourses", new int[]{0, 1, 2, 8}},
                {2, "Prerequisites and ExamTimeConflicts", new int[]{2, 7, 8, 9}},
                {1, "GPALimit", new int[]{1, 2, 3, 8, 9}},
                {1, "SectionScheduleConflict", new int[]{1, 2, 3, 10}}
        });
    }

    @Test
    public void checkEnrollmentRulesTest() throws Exception {
        enrollmentList = new EnrollmentList(listName, arman);
        for (int sec : testSections)
            enrollmentList.addSection(this.sections.get(sec));

        List<EnrollmentRuleViolation> violations = enrollmentList.checkEnrollmentRules();
        Assert.assertEquals(expectedViolationsCounts, violations.size());
    }

    @After
    public void tearDown(){
        courses = null;
        examTimes = null;
        preScheds = null;
        sections = null;
        ce = null;
        ceProgram = null;
        arman = null;
    }

}
