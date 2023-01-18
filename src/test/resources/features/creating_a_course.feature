Feature: Creating a course

  Scenario: Creating a course without courseNumber
    When creating a course with title and credit and graduateLevel
    Then CourseNumber exception throws


  Scenario: Creating a course without credit
    When creating a course with courseNumber and title and graduateLevel
    Then Credit exception throws

  Scenario: Creating a course without graduateLevel
    When creating a course with courseNumber and title and credit
    Then GraduateLevel exception throws

  Scenario: Creating a course without title
    When creating a course with courseNumber and credit and graduateLevel
    Then Title exception throws
