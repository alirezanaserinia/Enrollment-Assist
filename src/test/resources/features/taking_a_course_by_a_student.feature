Feature: Taking a course by a student
  The company follows a policy of adding and removing passengers,
  depending on the passenger type and on the flight type

  Scenario: a course can be taken by a student
    Given There exists a Undergraduate student with studentNo 810198530
    And Passed math1, physics1, farsi, english and karafarini
    And There exists a course with Id 42
    When  the student wants to take the course
    Then The list of unpassed prerequisite courses return