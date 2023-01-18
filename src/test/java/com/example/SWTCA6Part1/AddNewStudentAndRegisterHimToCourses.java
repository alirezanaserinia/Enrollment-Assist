package com.example.SWTCA6Part1;

import java.time.Duration;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class AddNewStudentAndRegisterHimToCourses {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "/Users/mahdi/Desktop/chromedriver");
    driver = new ChromeDriver();
    baseUrl = "http://localhost:8080/swagger-ui/index.html";
    driver.get(baseUrl);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testAddNewStudentAndRegisterHimToCourses() throws Exception {
    isElementPresent(By.xpath("//div[@id='swagger-ui']/section/div[2]/div[2]/div/section/div/div/hgroup/h2"));
    isElementPresent(By.xpath("//h4[@id='operations-tag-user-controller']/a/span"));
    driver.findElement(By.xpath("//h4[@id='operations-tag-user-controller']/a/span")).click();
    driver.findElement(By.xpath("//div[@id='operations-user-controller-addOneUsingPOST_3']/div/div")).click();
    driver.findElement(By.xpath("//div[@id='operations-user-controller-addOneUsingPOST_3']/div[2]/div/div/div/div[2]/button")).click();
    driver.findElement(By.xpath("//div[@id='operations-user-controller-addOneUsingPOST_3']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/textarea")).click();

    driver.findElement(By.xpath("//div[@id='operations-user-controller-addOneUsingPOST_3']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/textarea")).clear();
    driver.findElement(By.xpath("//div[@id='operations-user-controller-addOneUsingPOST_3']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/textarea"))
            .sendKeys("{\n" +
                    "      \"id\": 0,\n" +
                    "      \"name\": \"Ali\",\n" +
                    "      \"userId\": \"123123\"\n" +
                    "    }");

    isElementPresent(By.xpath("//div[@id='operations-user-controller-addOneUsingPOST_3']/div[2]/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-user-controller-addOneUsingPOST_3']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-user-controller-addOneUsingPOST_3']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    isElementNotPresent(By.xpath("//div[@id='operations-user-controller-addOneUsingPOST_3']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre/span[24]"));
    String genUserId = (String)driver.findElement(By.xpath("//div[@id='operations-user-controller-addOneUsingPOST_3']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre/span[6]")).getText();
    driver.findElement(By.id("operations-tag-user-controller")).click();
    driver.findElement(By.id("operations-tag-student-controller")).click();
    driver.findElement(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div/div")).click();
    isElementPresent(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div/div/div[2]/button")).click();
    driver.findElement(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/div/label/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/div/label/div/select"))).selectByVisibleText("application/json");
    driver.findElement(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/textarea")).click();

    driver.findElement(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/textarea")).clear();
    driver.findElement(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/textarea"))
            .sendKeys("{\n" +
                    "      \"graduateLevel\": \"Undergraduate\",\n" +
                    "      \"studentId\": 0,\n" +
                    "      \"studentNo\": {\n" +
                    "        \"number\": \"810197590\"\n" +
                    "      },\n" +
                    "      \"userId\": \"123123\"\n" +
                    "    }");

    isElementPresent(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    isElementNotPresent(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/span"));
    String genStdId = (String)driver.findElement(By.xpath("//div[@id='operations-student-controller-addStudentUsingPOST']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre/span[6]")).getText();
    driver.findElement(By.id("operations-tag-student-controller")).click();
    driver.findElement(By.id("operations-tag-enrollment-list-controller")).click();
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div/div")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div/div/div[2]/button")).click();
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/div/label/div/select")).click();
    new Select(driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/div/label/div/select"))).selectByVisibleText("application/json");
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/textarea")).click();

    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/textarea")).clear();
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div/div[2]/div/table/tbody/tr/td[2]/div[2]/div/div/textarea"))
            .sendKeys("{\n" +
                    "      \"enrollmentListId\": 0,\n" +
                    "      \"enrollmentListName\": \"Ali List\",\n" +
                    "      \"sections\": [],\n" +
                    "      \"student\": {\n" +
                    "        \"graduateLevel\": \"Undergraduate\",\n" +
                    "        \"studentId\": 0,\n" +
                    "        \"studentNo\": {\n" +
                    "          \"number\": \"\"\n" +
                    "        },\n" +
                    "        \"userId\": \"\"\n" +
                    "      }\n" +
                    "    }");

    driver.findElement(By.xpath("//input[@value='']")).click();

    driver.findElement(By.xpath("//input[@value='']")).clear();
    driver.findElement(By.xpath("//input[@value='']")).sendKeys("810197590");

    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    isElementNotPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/span"));
    String getEnrollList = (String)driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre/span[6]")).getText();
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addOneUsingPOST']/div/div")).click();
    driver.findElement(By.id("operations-tag-enrollment-list-controller")).click();
    driver.findElement(By.id("operations-tag-section-controller")).click();
    driver.findElement(By.xpath("//div[@id='operations-section-controller-allUsingGET_4']/div")).click();
    driver.findElement(By.xpath("//div[@id='operations-section-controller-allUsingGET_4']/div[2]/div/div/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-section-controller-allUsingGET_4']/div[2]/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-section-controller-allUsingGET_4']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-section-controller-allUsingGET_4']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    String genMath1sectionId = (String)driver.findElement(By.xpath("//div[@id='operations-section-controller-allUsingGET_4']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre/span[8]")).getText();
    String genPhys1sectionId = (String)driver.findElement(By.xpath("//div[@id='operations-section-controller-allUsingGET_4']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre/span[81]")).getText();
    String genKar1sectionId = (String)driver.findElement(By.xpath("//div[@id='operations-section-controller-allUsingGET_4']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre/span[738]")).getText();
    String genFarsi1sectionId = (String)driver.findElement(By.xpath("//div[@id='operations-section-controller-allUsingGET_4']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre/span[811]")).getText();
    String genAkhlaq1sectionId = (String)driver.findElement(By.xpath("//div[@id='operations-section-controller-allUsingGET_4']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre/span[592]")).getText();
    driver.findElement(By.id("operations-tag-section-controller")).click();
    driver.findElement(By.id("operations-tag-enrollment-list-controller")).click();
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div/div/div[2]/button")).click();
    driver.findElement(By.xpath("//input[@value='']")).click();

    driver.findElement(By.xpath("//input[@value='']")).clear();
    driver.findElement(By.xpath("//input[@value='']")).sendKeys(getEnrollList);

    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[2]/input")).click();

    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[2]/input")).clear();
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div/div[2]/div/table/tbody/tr[2]/td[2]/input"))
            .sendKeys(genMath1sectionId);

    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    isElementNotPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/span"));
    driver.findElement(By.xpath("//input[@value='30']")).click();

    driver.findElement(By.xpath("//input[@value='30']")).clear();
    driver.findElement(By.xpath("//input[@value='30']")).sendKeys(genPhys1sectionId);

    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    isElementNotPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/span"));
    driver.findElement(By.xpath("//input[@value='31']")).click();

    driver.findElement(By.xpath("//input[@value='31']")).clear();
    driver.findElement(By.xpath("//input[@value='31']")).sendKeys(genKar1sectionId);

    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    isElementNotPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/span"));
    driver.findElement(By.xpath("//input[@value='40']")).click();

    driver.findElement(By.xpath("//input[@value='40']")).clear();
    driver.findElement(By.xpath("//input[@value='40']")).sendKeys(genFarsi1sectionId);

    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    isElementNotPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/span"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div")).click();
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div[2]/div/div/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div[2]/div/div/div/div[2]/button")).click();
    driver.findElement(By.xpath("//input[@value='']")).click();

    driver.findElement(By.xpath("//input[@value='']")).clear();
    driver.findElement(By.xpath("//input[@value='']")).sendKeys(getEnrollList);

    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div[2]/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div")).click();
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div")).click();
    driver.findElement(By.xpath("//input[@value='47']")).click();

    driver.findElement(By.xpath("//input[@value='47']")).clear();
    driver.findElement(By.xpath("//input[@value='47']")).sendKeys(getEnrollList);

    driver.findElement(By.xpath("//input[@value='41']")).click();

    driver.findElement(By.xpath("//input[@value='41']")).clear();
    driver.findElement(By.xpath("//input[@value='41']")).sendKeys(genAkhlaq1sectionId);

    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-addSectionUsingPUT']/div")).click();
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div")).click();
    driver.findElement(By.xpath("//input[@value='47']")).click();

    driver.findElement(By.xpath("//input[@value='47']")).clear();
    driver.findElement(By.xpath("//input[@value='47']")).sendKeys(getEnrollList);

    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div[2]/div/div[2]/button"));
    driver.findElement(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div[2]/div/div[2]/button")).click();
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div[2]/div/div[3]/div[2]/table/tbody/tr/td[2]/div/div/p"));
    isElementPresent(By.xpath("//div[@id='operations-enrollment-list-controller-checkRegulationsUsingGET']/div[2]/div/div[3]/div[2]/div/div/table/tbody/tr/td[2]/div/div/pre/span[8]"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isElementNotPresent(By by) {
    try {
      driver.findElement(by);
      return false;
    } catch (NoSuchElementException e) {
      return true;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
