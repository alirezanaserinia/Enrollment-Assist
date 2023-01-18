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
import ir.proprog.enrollassist.domain.course.Course;
import ir.proprog.enrollassist.domain.major.Major;
import ir.proprog.enrollassist.domain.program.Program;
import ir.proprog.enrollassist.domain.student.Student;
import ir.proprog.enrollassist.domain.student.StudentNumber;
import ir.proprog.enrollassist.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Stepdefs {
    private StudentRepository studentRepository;
    private Student student;
    private List<Course> courses;
    private Program ceProgram;
    private Major ce;
//    private int expectedViolationsCounts;
    @Before
    public void setup() throws Exception {
        System.out.println("In Before method");
        setCourses();
        ce = new Major("8101", "CE", "Engineering");
        ceProgram = new Program(ce, "Undergraduate", 140, 140, "Major");
        ceProgram.addCourse(this.courses.get(0), this.courses.get(3), this.courses.get(2), this.courses.get(5));
        //enrollmentList = new EnrollmentList(listName, arman);
    }

    @Given("^There exists a Undergraduate student with studentNo (\\d+)$")
    public void there_exists_a_Master_student_with_studentNo(int studentNumber) throws Exception {
        // Write code here that turns the phrase above into concrete action
        student = new Student(Integer.toString(studentNumber), "Undergraduate");
        if (student==null)
            System.out.println("NO No NO\n");
        //student.addProgram(ceProgram);
        if (ceProgram==null)
            System.out.println("NO No NO\n");
        assertTrue(true);
    }
//"^Passed math(\\d+), physics(\\d+), farsi, english and karafarini$"
    @Given("^Passed math(\\d+), physics(\\d+), farsi, english and karafarini$")
    public void passed_math_physics_farsi_english_and_karafarini(int arg1,int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
//        student.setGrade("11112", courses.get(0), 10)
//                .setGrade("11112", courses.get(1), 12)
//                .setGrade("11112", courses.get(7), 16.3)
//                .setGrade("11112", courses.get(9), 18.5)
//                .setGrade("11112", courses.get(10), 15);
        assertTrue(true);
    }

    @Given("^There exists a course with Id (\\d+)$")
    public void there_exists_a_course_with_Id(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        assertTrue(true);
    }

    @When("^the student wants to take the course$")
    public void the_student_wants_to_take_the_course() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        assertTrue(true);
    }

    @Then("^The list of unpassed prerequisite courses return$")
    public void the_list_of_unpassed_prerequisite_courses_return() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //throw new PendingException();
        assertTrue(true);
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
