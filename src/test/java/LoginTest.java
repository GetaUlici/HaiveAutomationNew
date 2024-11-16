import Utils.DatabaseUtils;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

// This annotation is used to specify that the ExtentTestNGITestListener class should be used to listen to the test events.
// The listener will generate reports based on the test results using the ExtentReports library.
@Listeners(ExtentTestNGITestListener.class)

// Declaring the CheckoutTest class, which extends the Hooks class.
// By extending Hooks, CheckoutTest inherits the setup and teardown methods for WebDriver.
public class LoginTest extends Hooks {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
    public LoginPage loginPage;

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

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }

    @Test(description = "Authenticating with a new user")
    public void authenticatingTest() {
        loginPage.clickLoginIcon();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getAuthenticateIcon()));
        loginPage.clickWhenReady(loginPage.getAuthenticateIcon());
        loginPage.sendKeysWhenReady(loginPage.getNameField(), "Anca");
        loginPage.setEmailField("ulicigeta+7@gmail.com");
        loginPage.setPassField("ParolaHaive7");
        loginPage.clickCreatiContButton();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getCodVerificare()));
        Assert.assertEquals(loginPage.getCodVerificare().getText(), "Cod de verificare", "'Cod de verificare' is not displayed, the user couldn't successfully reach to this step.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The user successfully created a new account.");
    }

    @Test(description = "Login with a user")
    public void loginTest() {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));
        Assert.assertEquals(loginPage.getDashboard().getText(), "Dashboard", "The user is not logged in, the dashboard page wasn't displayed.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The user is logged in, the Dashboard page is displayed");
    }

    @Test(description = "Logout with a user")
    public void logoutTest() {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickWhenReady(loginPage.getAvatarIcon());

        if (loginPage.getEmailConfirmation().getText().equals("ulicigeta+1@gmail.com")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user can be validated with the corresponding email displayed.");
        } else {
            softAssert.fail("The user's email can't be validated, the corresponding email is not successfully displayed.");
        }

        loginPage.clickIesiCont();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getAutentificareText()));
        Assert.assertEquals(loginPage.getAutentificareText().getText(), "Autentificare");
        softAssert.assertAll();
    }

    @Test(description = "Negative Login test")
    public void negativeLoginTest() {
        loginPage.NegativeLoginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getCevaNuAMersBine()));
        Assert.assertEquals(loginPage.getCevaNuAMersBine().getText(), "Ceva nu a mers bine");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The user wasn't able to log in with an invalid email or password");
    }

    @Test(description = "Entering null or invalid email input")
    public void invalidEmailTest() {
        loginPage.clickLoginIcon();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getUserNameField()));
        loginPage.setUserNameField("");
        loginPage.setPasswordField("Fasttrackit");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorEmail().getText(), "Invalid email", "The user succeeded the login, which is not the expected result.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was unable to login, the Email field was empty or invalid, error message is displayed.");
    }

    @Test(description = "Entering null or invalid password input")
    public void invalidPasswordTest() {
        loginPage.clickLoginIcon();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getUserNameField()));
        loginPage.setUserNameField("ulicicgeta+1@gmail.com");
        loginPage.setPasswordField("Fast");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorPassword().getText(), "String must contain at least 8 character(s)");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was unable to login, the Password field was empty or invalid, error message is displayed.");
    }
}



