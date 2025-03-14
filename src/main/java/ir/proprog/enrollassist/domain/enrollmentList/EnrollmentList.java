package ir.proprog.enrollassist.domain.enrollmentList;

import ir.proprog.enrollassist.Exception.ExceptionList;
import ir.proprog.enrollassist.domain.EnrollmentRules.*;
import ir.proprog.enrollassist.domain.GraduateLevel;
import ir.proprog.enrollassist.domain.studyRecord.Grade;
import ir.proprog.enrollassist.domain.student.Student;
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.section.ExamTime;
import ir.proprog.enrollassist.domain.section.PresentationSchedule;
import ir.proprog.enrollassist.domain.section.Section;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // as required by JPA, don't use it in your code
@Getter
public class EnrollmentList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String listName;
    @ManyToOne
    Student owner;
    @ManyToMany
    List<Section> sections = new ArrayList<>();

    public String isValid()
    {
        if (listName.equals(""))
            return "Enrollment list must have a name";
        return null;
    }

    public EnrollmentList(@NonNull String listName, @NonNull Student owner) {
        this.listName = listName;
        this.owner = owner;
    }

    public void addSections(Section... new_sections) {
        //TODO: check for duplicates
        Collections.addAll(sections, new_sections);
    }

    public void addSection(Section section) {
        sections.add(section);
    }

    public void removeSection(Section section) {
        sections.remove(section);
    }

    public void clear() { sections.clear(); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentList that = (EnrollmentList) o;
        return owner.equals(that.owner) && listName.equals(that.listName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, listName);
    }

    public List<EnrollmentRuleViolation> checkEnrollmentRules() {
        List<EnrollmentRuleViolation> violations = new ArrayList<>();
        violations.addAll(checkHasPassedAllPrerequisites());
        violations.addAll(checkHasNotAlreadyPassedCourses());
        violations.addAll(checkNoCourseHasRequestedTwice());
        violations.addAll(checkValidGPALimit());
        violations.addAll(checkExamTimeConflicts());
        violations.addAll(checkSectionScheduleConflicts());
        return violations;
    }

    List<EnrollmentRuleViolation> checkNoCourseHasRequestedTwice() {
        List<EnrollmentRuleViolation> violations = new ArrayList<>();
        Set<Course> courses = new HashSet<>();
        for (Section section : sections)
            if (courses.contains(section.getCourse()))
                violations.add(new CourseRequestedTwice(section.getCourse()));
            else
                courses.add(section.getCourse());

        return violations;
    }


    List<EnrollmentRuleViolation> checkHasNotAlreadyPassedCourses() {
        List<EnrollmentRuleViolation> violations = new ArrayList<>();
        for (Section sec : sections)
            if (sec.studentHasPassedCourse(owner))
                violations.add(new RequestedCourseAlreadyPassed(sec.getCourse()));
        return violations;
    }

    List<EnrollmentRuleViolation> checkHasPassedAllPrerequisites() {
        List<EnrollmentRuleViolation> violations = new ArrayList<>();
        for (Section sec : sections)
            violations.addAll(owner.canTake(sec.getCourse()));
//            violations.addAll(sec.courseCanBeTakenBy(owner));
        return violations;
    }

    public List<EnrollmentRuleViolation> checkValidGPALimit() {
        Grade GPA = owner.calculateGPA();
        GraduateLevel ownerGraduateLevel = owner.getGraduateLevel();
        int credits = sections.stream().mapToInt(section -> section.getCourse().getCredits()).sum();
        List<EnrollmentRuleViolation> violations = new ArrayList<>(); // 1
        if(credits < ownerGraduateLevel.getMinValidTermCredit()) // (2,3) (2,4)
            violations.add(new MinCreditsRequiredNotMet(ownerGraduateLevel.getMinValidTermCredit())); // 3

        if (ownerGraduateLevel == GraduateLevel.Undergraduate) { // (4,5) (4,11)
            if(GPA.equals(Grade.ZERO) && owner.getTotalTakenCredits() == 0 && credits > 20) // (5,6) (5,7)
                violations.add(new MaxCreditsLimitExceeded(20)); // 6
            else if (credits > 14 && GPA.isLessThan(12)) // (7,8) (7,9)
                violations.add(new MaxCreditsLimitExceeded(14)); // 8
            else if (credits > 20 && GPA.isLessThan(17)) // (9,10) (9,11)
                violations.add(new MaxCreditsLimitExceeded(20)); //10
        }

        if (credits > ownerGraduateLevel.getMaxValidCredits()) // (11,12) (11,13)
            violations.add(new MaxCreditsLimitExceeded(ownerGraduateLevel.getMaxValidCredits())); // 12

        return violations; //13
    }


    public List<EnrollmentRuleViolation> checkExamTimeConflicts() {
        List<EnrollmentRuleViolation> violations = new ArrayList<>(); // 1
        for (int i = 0; i < sections.size(); i++ /*13*/) { // 2 , (3,4) , (3,14)
            Section s1 = sections.get(i); // 4
            if (s1.getExamTime() == null) // (5,6) (5,13)
                continue;
            for (int j = i + 1; j < sections.size(); j++ /*12*/) { // 6 , (7,8) , (7,13)
                Section s2 = sections.get(j); // 8
                if (s2.getExamTime() == null) // (9,10) (9,12)
                    continue;
                if (s1 != s2 && s1.getExamTime().hasTimeConflict(s2.getExamTime())) // (10,11) (10,12)
                    violations.add(new ExamTimeCollision(s1, s2)); // 11
            }
        }
        return violations; // 14
    }

    List<EnrollmentRuleViolation> checkSectionScheduleConflicts() {
        List<EnrollmentRuleViolation> violations = new ArrayList<>();
        for (int i=0; i<this.sections.size(); i++) {
            for (int j=i+1; j<this.sections.size(); j++) {
                if (this.sections.get(i).equals(this.sections.get(j)))
                    continue;
                if (this.sections.get(i).hasScheduleConflict(this.sections.get(j)))
                    violations.add(new ConflictOfClassSchedule(this.sections.get(i), this.sections.get(j)));
            }
        }
        return violations;
    }

    public boolean makeExamTimeConflict(Section section, ExamTime examTime) throws ExceptionList {
        int index = this.sections.indexOf(section);
        List<EnrollmentRuleViolation> preEnrollmentRuleViolations = this.checkExamTimeConflicts();
        ExamTime preExamTime = this.sections.get(index).getExamTime();
        this.sections.get(index).setExamTime(examTime);
        List<EnrollmentRuleViolation> enrollmentRuleViolations = this.checkExamTimeConflicts();
        this.sections.get(index).setExamTime(preExamTime);
        if ((enrollmentRuleViolations.size() == 0) || (enrollmentRuleViolations.equals(preEnrollmentRuleViolations)))
            return false;
        return enrollmentRuleViolations.size() >= preEnrollmentRuleViolations.size();
    }

    public boolean makePresentationScheduleConflict(Section section, List<PresentationSchedule> schedule) {
        int index = this.sections.indexOf(section);
        List<EnrollmentRuleViolation> preEnrollmentRuleViolations = this.checkSectionScheduleConflicts();
        Set<PresentationSchedule> prePresentationSchedule = this.sections.get(index).getPresentationSchedule();
        this.sections.get(index).setPresentationSchedule(new HashSet<>(schedule));
        List<EnrollmentRuleViolation> enrollmentRuleViolations = this.checkSectionScheduleConflicts();
        this.sections.get(index).setPresentationSchedule(prePresentationSchedule);
        if ((enrollmentRuleViolations.size() == 0) || (enrollmentRuleViolations.equals(preEnrollmentRuleViolations)))
            return false;
        return enrollmentRuleViolations.size() >= preEnrollmentRuleViolations.size();
    }
}
