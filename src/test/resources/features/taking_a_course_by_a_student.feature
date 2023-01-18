Feature: Taking a course by a student

  Scenario: taking a course with passed prerequisite
    Given There exists a student
    And There exists a course with some prerequisite courses
    And the student has passed all prerequisites of the course
    When the student wants to take the course
    Then the student can take the course


  Scenario: taking a course with unpassed prerequisite
    Given There exists a student
    And There exists a course with some prerequisite courses
    And the student has not passed all prerequisites of the course
    When the student wants to take the course
    Then the student can not take the course
