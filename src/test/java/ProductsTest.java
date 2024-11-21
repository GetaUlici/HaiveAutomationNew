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
public class ProductsTest extends Hooks {

    private static final Logger log = LoggerFactory.getLogger(ProductsTest.class);
    // Declaring a public variable of type CheckoutPage named 'checkoutPage'.
    // This will be used to interact with the CheckoutPage object during the tests.
    public LoginPage loginPage;
    public ProductsPage productsPage;
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
        productsPage = new ProductsPage(driver);
        dashboardPage = new DashboardPage(driver);

        // Initializing the WebDriverWait object with the current WebDriver instance and a timeout of 30 seconds.
        // This wait will be used to pause the execution until certain conditions are met or elements are found.
        wait = new WebDriverWait(driver, 30);
        softAssert = new SoftAssert();
    }

    @Test(description = "Creating products test")
    public void creatingProductsTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        wait.until(ExpectedConditions.visibilityOf(productsPage.getProduseIcon()));
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.getProduseIcon()));
        productsPage.clickProduseIcon();
        wait.until(ExpectedConditions.visibilityOf(productsPage.getAdaugaProdusButton()));
        productsPage.clickAdaugaProdusButton();
        wait.until(ExpectedConditions.visibilityOf(productsPage.getNameProduct()));
        productsPage.setNameProduct("Paste Carbonara");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(521, 1042);");
        Thread.sleep(1000);
        productsPage.selectCategoryDropdown();
        productsPage.selectTipProdusDrop();
        wait.until(ExpectedConditions.visibilityOf(productsPage.getPriceField()));
        productsPage.setPriceField("20");
        productsPage.clickWhenReady(productsPage.getCreatiProdusulButton());
        wait.until(ExpectedConditions.visibilityOf(productsPage.getConfirmationProduse()));
        Assert.assertEquals(productsPage.getConfirmationProduse().getText(), "Produse", "Produse page is not displayed.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The product has been created and it's displayed on the products page.");
        softAssert.assertAll();
    }

    @Test(description = "Editing products test")
    public void editingProductsTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        wait.until(ExpectedConditions.visibilityOf(productsPage.getProduseIcon()));
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.getProduseIcon()));
        productsPage.clickProduseIcon();
        wait.until(ExpectedConditions.visibilityOf(productsPage.getPasteCarbonaraProduct()));
        productsPage.clickWhenReady(productsPage.getPasteCarbonaraProduct());
        wait.until(ExpectedConditions.visibilityOf(productsPage.getNameProduct()));
        productsPage.deleteAndEditProductName("Paste bologneze");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(603, 1463);");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(productsPage.getActualizatiProdusul()));
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.getActualizatiProdusul()));
        productsPage.clickWhenReady(productsPage.getActualizatiProdusul());
        wait.until(ExpectedConditions.visibilityOf(productsPage.getValidationActualizare()));

        if (productsPage.getValidationActualizare().getText().equals("Operația a fost efectuată cu succes")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The product was successfully edited.");
        } else {
            softAssert.fail("The product wasn't edited, something occurred.");
        }

        wait.until(ExpectedConditions.visibilityOf(productsPage.getPasteBolognezeProduct()));
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.getPasteBolognezeProduct()));
        Assert.assertEquals(productsPage.getPasteBolognezeProduct().getText(), "Paste bologneze", "The product wasn't updated successfully.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The product was updated successfully and " + productsPage.getPasteBolognezeProduct().getText() + " is displayed.");
        softAssert.assertAll();
    }

    @Test(description = "Deleting products test")
    public void deletingProductsTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(dashboardPage.getDashboard()));

        if (dashboardPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        wait.until(ExpectedConditions.visibilityOf(productsPage.getProduseIcon()));
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.getProduseIcon()));
        productsPage.clickProduseIcon();
        wait.until(ExpectedConditions.visibilityOf(productsPage.getPasteBolognezeProduct()));
        productsPage.clickWhenReady(productsPage.getPasteBolognezeProduct());
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(516, 1571);");
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(productsPage.getStergetiProdusul()));
        wait.until(ExpectedConditions.elementToBeClickable(productsPage.getStergetiProdusul()));
        productsPage.clickWhenReady(productsPage.getStergetiProdusul());
        wait.until(ExpectedConditions.visibilityOf(productsPage.getContinuaButton()));
        productsPage.clickWhenReady(productsPage.getContinuaButton());
        wait.until(ExpectedConditions.visibilityOf(productsPage.getValidationDelete()));

        if (productsPage.getValidationDelete().getText().equals("Operația a fost efectuată cu succes")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The product was successfully deleted.");
        } else {
            softAssert.fail("The product wasn't deleted, something occurred.");
        }

        try {
            if (productsPage.getPasteBolognezeProduct().isDisplayed()) {
                Assert.fail("The product is still displayed after the user deleted the product.");
            }
        } catch (NoSuchElementException e) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user successfully deleted the product.");
            Assert.assertTrue(true, "The product was successfully deleted.");
        }

        softAssert.assertAll();
    }
}



