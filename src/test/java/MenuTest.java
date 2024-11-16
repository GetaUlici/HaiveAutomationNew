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
public class MenuTest extends Hooks {

    private static final Logger log = LoggerFactory.getLogger(MenuTest.class);
    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
    public LoginPage loginPage;
    public MenuPage menuPage;
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
        menuPage = new MenuPage(driver);
        dashboardPage = new DashboardPage(driver);

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }


    @Test(description = "Creating menu")
    public void createMenuTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        wait.until(ExpectedConditions.elementToBeClickable(menuPage.getMeniuButton()));
        menuPage.clickMeniuButton();
        menuPage.clickWhenReady(menuPage.getCreatiMeniuButton());
        menuPage.sendKeysWhenReady(menuPage.getNumeMeniu(), "Meniul zilei");
        menuPage.sendKeysWhenReady(menuPage.getDescriereMeniu(), "Un meniu cu produse delicioase");
        wait.until(ExpectedConditions.visibilityOf(menuPage.getCreatiMeniuButton()));
        menuPage.clickWhenReady(menuPage.getCreatiMeniuFinalButtton());
        wait.until(ExpectedConditions.visibilityOf(menuPage.getMenuValidation()));
        Assert.assertEquals(menuPage.getMenuValidation().getText(), "Meniul zilei", "The menu was not created successfully.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The menu was created and displayed successfully.");
        softAssert.assertAll();
    }

    @Test(description = "Adding a category to the menu")
    public void addingCategoryToMenuTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        wait.until(ExpectedConditions.visibilityOf(menuPage.getMeniuButton()));
        wait.until(ExpectedConditions.elementToBeClickable(menuPage.getMeniuButton()));
        menuPage.clickMeniuButton();
        menuPage.clickWhenReady(menuPage.getMeniulZilei());
        menuPage.clickWhenReady(menuPage.getAddCategorieNouaButton());
        menuPage.setNameCat("Pizza");
        menuPage.sendKeysWhenReady(menuPage.getDescriptionCat(), "Diverse feluri de pizza");
        menuPage.clickCreatiCatButton();
        wait.until(ExpectedConditions.visibilityOf(menuPage.getCategoryConfirmation()));
        Assert.assertEquals(menuPage.getCategoryConfirmation().getText(), "Pizza (0)", "The category is not displayed on the page.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The category was added successfully.");
        softAssert.assertAll();
    }

    @Test(description = "Editing Category of the menu")
    public void editCatTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        wait.until(ExpectedConditions.visibilityOf(menuPage.getMeniuButton()));
        wait.until(ExpectedConditions.elementToBeClickable(menuPage.getMeniuButton()));
        menuPage.clickMeniuButton();
        menuPage.clickWhenReady(menuPage.getMeniulZilei());
        menuPage.clickWhenReady(menuPage.getEditCatIcon());
        menuPage.deleteAndEditNameCat("Paste");
        menuPage.setEditDescriptionCat("Diverse feluri de paste");
        menuPage.clickWhenReady(menuPage.getSaveEditCat());
        wait.until(ExpectedConditions.visibilityOf(menuPage.getEditCatConfirmation()));
        Thread.sleep(3000);
        Assert.assertEquals(menuPage.getEditCatConfirmation().getText(), "Paste (0)", "The category was not successfully edited.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The category was successfully edited.");
        softAssert.assertAll();
    }

    @Test(description = "Removing a category from menu")
    public void removingCatFromMenu() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        wait.until(ExpectedConditions.visibilityOf(menuPage.getMeniuButton()));
        wait.until(ExpectedConditions.elementToBeClickable(menuPage.getMeniuButton()));
        menuPage.clickMeniuButton();
        menuPage.clickWhenReady(menuPage.getMeniulZilei());
        menuPage.clickWhenReady(menuPage.getDeleteCategory());
        wait.until(ExpectedConditions.visibilityOf(menuPage.getDeletCatNotification()));

        try {
            if (menuPage.getCategoryConfirmation().isDisplayed()) {
                Assert.fail("Element is still present, the category is not removed.");
            }
        } catch (NoSuchElementException e) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The category was successfully removed.");
            Assert.assertTrue(true, "The Category was successfully deleted.");
        }
        softAssert.assertAll();
    }
}



