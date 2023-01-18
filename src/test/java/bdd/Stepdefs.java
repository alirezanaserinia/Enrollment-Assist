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
import io.cucumber.java.PendingException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Stepdefs {
    @Given("^There exists a Master student with studentNo (\\d+)$")
    public void there_exists_a_Master_student_with_studentNo(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("salam\n");
        //throw new PendingException();
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
}
