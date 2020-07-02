package org.robinsinghdevgan.stepdefinitions;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.robinsinghdevgan.setup.BaseTestSetup;
import org.robinsinghdevgan.setup.SelectWebBrowser;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MyStepdefs implements En {

    private static SelenideDriver selenideDriver;
    private String defaultWindowHandle = "";
    private WebDriver driver;
    private BaseTestSetup base;

    public MyStepdefs() {
        Given("^user's browser has no cookies of visiting Flipkart$", () -> {
            driver = base.getDriver();
            driver = SelectWebBrowser.setup(base.getPropertiesObject());
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            WebDriverRunner.setWebDriver(driver);
        });
        When("^user visits Flipkart website$", () -> open("https://flipkart.com/"));
        Then("^user see's a login form window$", () -> $(By.cssSelector("button[class='_2AkmmA _29YdH8']")).exists());
        And("^user can close this form by clicking on cross button$", () -> {
            $(By.cssSelector("button[class='_2AkmmA _29YdH8']")).click();
            $(By.cssSelector("button[class='_2AkmmA _29YdH8']")).should(disappear);
        });

        When("^user clicks on search bar$", () -> $(By.xpath("//input[@placeholder='Search for products, brands and more']")).click());
        And("^enter required \"([^\"]*)\"$", (String arg0) -> $(By.xpath("//input[@placeholder='Search for products, brands and more']")).val(arg0));
        And("^press return key$", () -> $(By.xpath("//input[@placeholder='Search for products, brands and more']")).pressEnter());
        Then("^search results matching the search keyword is returned$", this::searchResultsReadyWait);
        Given("^user is on search results screen$", this::searchResultsReadyWait);
        When("^user clicks on \"([^\"]*)\"$", this::accept);
        Then("^search results are appear in the selected sort type$", this::searchResultsReadyWait);
        When("^user scrolls down to Search Brand panel$", () -> $(By.xpath("(//div[contains(text(),'Brand')]/parent::*/parent::*)")).scrollIntoView(true));
        And("^user user clicks on More button inside Search Brand panel$", () -> $(By.xpath("(//div[contains(text(),'Brand')]/parent::*/parent::*)//*[contains(text(),'MORE')]")).click());
        Then("^brand search window opens$", () -> $(By.xpath("(//div[contains(text(),'Brand')]/parent::*/parent::*)//input[@placeholder='Search Brand']")).exists());
        And("^user enters \"([^\"]*)\" in search bar and presses return key$", (String brand) -> {
            SelenideElement brandSearchBar = $(By.xpath("(//div[contains(text(),'Brand')]/parent::*/parent::*)//input[@placeholder='Search Brand']"));
            if (brandSearchBar.exists())
                brandSearchBar.val(brand).pressEnter();
        });
        Then("^user selects \"([^\"]*)\" by clicking the first enlisted checkbox$", (String brand) -> {
            String firstCheckBox = "//div[contains(text(),'Brand')]/parent::*/following-sibling::*//div[contains(text(),'" + brand + "')]";
            $(By.xpath(firstCheckBox)).shouldBe(visible);
            $(By.xpath(firstCheckBox)).click();
        });
        And("^closes the search brand div by clicking on cross button$", () -> {
            int cc = 2715;
            char ccc = (char) Integer.parseInt(String.valueOf(cc), 16);
            final String text = String.valueOf(ccc);
            $(By.xpath("//input[@placeholder = 'Search Brand']/parent::*//div[text()='" + text + "']")).click();
        });
        And("^user sees updated search results$", this::searchResultsReadyWait);
        When("^user clicks on first search result$", () -> {
            searchResultsReadyWait();
            $(By.xpath("(//*[contains(text(),'% off')])[1]")).click();
        });
        Then("^a new tab of browser is opened$", () -> {
            Thread.sleep(500);
            assert driver.getWindowHandles().size() > 1;
        });
        Given("^user switches to product details tab$", () -> {
            switchTo().window(1);
            Thread.sleep(2000);
        });
        When("^user clicks on add to cart$", () -> {
            if ($("#pincodeInputId").exists()) {
                $("#pincodeInputId").clear();
                $("#pincodeInputId").click();
                $("#pincodeInputId").val("110085").pressEnter();
            }
            $(By.xpath("(//*[contains(text(),'% off')])[1]")).shouldBe(visible);
            $(By.xpath("(//ul[@class='row']//button)[1]")).click();
        });
        Then("^item gets added to cart$", () -> System.out.println("temp"));
        Given("^user is on any page of Flipkart$", () -> System.out.println("temp"));
        When("^user clicks on cart icon$", () -> System.out.println("temp"));
        Then("^View cart page is opened$", () -> System.out.println("temp"));

    }

    @Before
    public void before(io.cucumber.java.Scenario scenario) {
        System.out.println("------------------------------");
        System.out.println("Starting - " + scenario.getName());
        System.out.println("------------------------------");
        base = new BaseTestSetup();
        base.setProperties("flipkart.properties");
    }

    @After
    public void after(io.cucumber.java.Scenario scenario) {
        if (driver != null) {
            driver.quit();
            System.out.println("------------------------------");
            System.out.println("Closing - " + scenario.getName());
            System.out.println("------------------------------");
        }

    }

    private void searchResultsReadyWait() {
        $(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Low to')]")).should(enabled);
    }

    private void accept(String sortBy) {
        searchResultsReadyWait();
        switch (sortBy) {
            case "L2H":
                $(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Low to')]")).click();
                break;
            case "H2L":
                $(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'High to')]")).click();
                break;
            case "REL":
                $(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Relev')]")).click();
                break;
            case "POP":
                $(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'Popular')]")).click();
                break;
            case "NEW":
                $(By.xpath("(//span[contains(text(),'Sort')]/parent::*)//div[contains(text(), 'New')]")).click();
                break;
            default:
                System.out.println("err");
                break;
        }
    }
}
