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
public class ModifierSetTest extends Hooks {

    private static final Logger log = LoggerFactory.getLogger(ModifierSetTest.class);
    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
    public LoginPage loginPage;
    public ModifierSetPage modifierSetPage;
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
        modifierSetPage = new ModifierSetPage(driver);
        dashboardPage = new DashboardPage(driver);

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }


    @Test(description = "Creating Set modificatori test")
    public void createSetModificatoriTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        wait.until(ExpectedConditions.visibilityOf(modifierSetPage.getSeturiModificatoriIcon()));
        wait.until(ExpectedConditions.elementToBeClickable(modifierSetPage.getSeturiModificatoriIcon()));
        modifierSetPage.clickSeturiModificatoriIcon();
        modifierSetPage.clickWhenReady(modifierSetPage.getSeturiModificatoriIcon());
        modifierSetPage.clickWhenReady(modifierSetPage.getCreeazaSetNou());
        wait.until(ExpectedConditions.visibilityOf(modifierSetPage.getNumeModificator()));
        modifierSetPage.setNumeModificator("Sosuri");
        wait.until(ExpectedConditions.visibilityOf(modifierSetPage.getModificatoriDropdown()));
        modifierSetPage.selectModificatoriDropdown();
        modifierSetPage.clickWhenReady(modifierSetPage.getCreatiSetNouButton());
        wait.until(ExpectedConditions.visibilityOf(modifierSetPage.getModificatorValidation()));
        Assert.assertEquals(modifierSetPage.getModificatorValidation().getText(), "Sosuri", "The 'Sosuri' Modificator is not displayed on the Modificatori page");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The " + modifierSetPage.getModificatorValidation().getText() + " is displayed on the Modificatori page");
        softAssert.assertAll();
    }

    @Test(description = "Editing 'Set modificatori' test")
    public void editSetModificatoriTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        wait.until(ExpectedConditions.visibilityOf(modifierSetPage.getSeturiModificatoriIcon()));
        wait.until(ExpectedConditions.elementToBeClickable(modifierSetPage.getSeturiModificatoriIcon()));
        modifierSetPage.clickSeturiModificatoriIcon();
        modifierSetPage.clickWhenReady(modifierSetPage.getEditSetModif());
        modifierSetPage.clickWhenReady(modifierSetPage.getNumeSetModif());
        modifierSetPage.deleteAndEditNameSetModif("Parmezan");
        modifierSetPage.clickWhenReady(modifierSetPage.getSaveModif());
        wait.until(ExpectedConditions.visibilityOf(modifierSetPage.getEditModifValidation()));
        Thread.sleep(2000);
        Assert.assertEquals(modifierSetPage.getEditModifValidation().getText(), "Parmezan", "The 'Sosuri' was not successfully edited.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The 'Sosuri' modificator was edited and  the '" + modifierSetPage.getEditModifValidation().getText() + "' is displayed instead.");
        softAssert.assertAll();
    }

    @Test(description = "Deleting 'Set de modificatori' test")
    public void deleteModificatoriTest() {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        wait.until(ExpectedConditions.visibilityOf(modifierSetPage.getSeturiModificatoriIcon()));
        wait.until(ExpectedConditions.elementToBeClickable(modifierSetPage.getSeturiModificatoriIcon()));
        modifierSetPage.clickSeturiModificatoriIcon();
        modifierSetPage.clickWhenReady(modifierSetPage.getSeturiModificatoriIcon());
        modifierSetPage.clickWhenReady(modifierSetPage.getStergeModificator());
        modifierSetPage.clickWhenReady(modifierSetPage.getContinuatiButton());
        wait.until(ExpectedConditions.visibilityOf(modifierSetPage.getDeleteModifValidation()));

        try {
            if (modifierSetPage.getModificatorValidation().isDisplayed()) {
                Assert.fail("The 'Modificator set' is still displayed after the user clicks Delete button.");
            }
        } catch (NoSuchElementException e) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The 'Modificator set' was successfully removed.");
            Assert.assertTrue(true, "The 'Modificatori set' was successfully deleted.");
        }

        Assert.assertEquals(modifierSetPage.getDeleteModifValidation().getText(), "Nu aveți seturi de modificatori încă", "The 'Set de modificatori' is not deleted.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The 'Set de modificatori' is successfully deleted.");
        softAssert.assertAll();
    }
}



