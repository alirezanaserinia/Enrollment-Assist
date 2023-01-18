/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
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

public class StepdefsTakingACourse {
    private Student student;
    private List<Course> courses;
    private Course wantedCourse;
    private Program ceProgram;
    private Major ce;
    private List<EnrollmentRuleViolation> actualVioalationList;

    public void setup() throws Exception {
        setCourses();
        ce = new Major("8101", "CE", "Engineering");
        ceProgram = new Program(ce, "Undergraduate", 140, 140, "Major");
        ceProgram.addCourse(this.courses.get(0), this.courses.get(3), this.courses.get(2), this.courses.get(5));
    }

    @Given("^There exists a student$")
    public void there_exists_a_student() throws Throwable {
        student = new Student("810198530", "Undergraduate");
    }

    @Given("^There exists a course with some prerequisite courses$")
    public void there_exists_a_course_with_some_prerequisite_courses() throws Throwable {
        setup();
        student.addProgram(ceProgram);
    }

    @Given("^the student has passed all prerequisites of the course$")
    public void the_student_has_passed_all_prerequisites_of_the_course() throws Throwable {
        student.setGrade("11112", courses.get(0), 10)
                .setGrade("11112", courses.get(1), 12)
                .setGrade("11112", courses.get(7), 16.3)
                .setGrade("11112", courses.get(9), 18.5)
                .setGrade("11112", courses.get(10), 15);
        wantedCourse= courses.get(4);
    }
    @When("^the student wants to take the course$")
    public void the_student_wants_to_take_the_course() throws Throwable {
        assertTrue(true);
    }

    @Then("^the student can take the course$")
    public void the_student_can_take_the_course() throws Throwable {
        actualVioalationList= wantedCourse.canBeTakenBy(student);
        assertEquals(0,actualVioalationList.size());
    }

    @Given("^the student has not passed all prerequisites of the course$")
    public void the_student_has_not_passed_all_prerequisites_of_the_course() throws Throwable {
        student.setGrade("11112", courses.get(0), 10)
                .setGrade("11112", courses.get(1), 12)
                .setGrade("11112", courses.get(7), 16.3)
                .setGrade("11112", courses.get(9), 18.5)
                .setGrade("11112", courses.get(10), 15);
        wantedCourse= courses.get(5);
    }

    @Then("^the student can not take the course$")
    public void the_student_can_not_take_the_course() throws Throwable {
        actualVioalationList= wantedCourse.canBeTakenBy(student);
        assertEquals(1,actualVioalationList.size());
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
        Course te = new Course("2121212", "TE", 2, "Undergraduate");
        Course english = new Course("1010101", "EN", 2, "Undergraduate");
        Course karafarini = new Course("1313131", "KAR", 3, "Undergraduate");
        this.courses = new ArrayList<Course>(Arrays.asList(math1, phys1, prog, math2, phys2, ap, maaref, farsi,
                te, english, karafarini));
    }
}
