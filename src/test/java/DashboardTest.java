import Utils.DatabaseUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

// This annotation is used to specify that the ExtentTestNGITestListener class should be used to listen to the test events.
// The listener will generate reports based on the test results using the ExtentReports library.
@Listeners(ExtentTestNGITestListener.class)

// Declaring the CheckoutTest class, which extends the Hooks class.
// By extending Hooks, CheckoutTest inherits the setup and teardown methods for WebDriver.
public class DashboardTest extends Hooks {

    private static final Logger log = LoggerFactory.getLogger(DashboardTest.class);
    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
    public LoginPage loginPage;
    public DashboardPage dashboardPage;

    // Declaring a public variable of type WebDriverWait named 'wait'.
    // WebDriverWait is used to explicitly wait for certain conditions or elements during test execution.
    public WebDriverWait wait;

    public SoftAssert softAssert;

    // Method annotated with @BeforeMethod, indicating that it will run before each test method.
    // This method is used to set up the page objects and other necessary components before each test.
    @BeforeMethod
    public void SetupPageObject() {

        // Initializing the checkoutPage object with the current WebDriver instance.
        // This allows the test methods to interact with elements on the checkout page.
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }


    @Test(description = "Select a date on the dashboard")
    public void pickDashboardDateTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        dashboardPage.clickWhenReady(dashboardPage.date());
        dashboardPage.clickPreviousMonthButton();
        dashboardPage.clickDay1();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The first day was selected.");
        dashboardPage.clickDay3();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The third day was selected.");
        Assert.assertEquals(dashboardPage.getValidationDay1().getText(), "Oct 01, 2024 - Oct 03, 2024", "The selected dates were not displayed on the Calendar.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The selected dates were successfully displayed on the Calendar.");
        softAssert.assertAll();
    }

    @Test(description = "Delete selected date on the dashboard")
    public void deleteDashboardDateTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        dashboardPage.clickWhenReady(dashboardPage.date());
        dashboardPage.clickPreviousMonthButton();
        dashboardPage.clickDay1();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The first day was selected.");
        dashboardPage.clickDay3();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The third day was selected.");
        dashboardPage.clickStergeFiltruButton();
        Assert.assertEquals(dashboardPage.getPickADate().getText(), "Pick a date", "The dates weren't successfully deleted, the 'Pick a date' button is not displayed.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The selected dates were deleted.");
        softAssert.assertAll();
    }
}



