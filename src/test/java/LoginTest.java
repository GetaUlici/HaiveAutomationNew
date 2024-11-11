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
        loginPage.setEmailField("ulicigeta+12@gmail.com");
        loginPage.setPassField("ParolaHaive12");
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
    public void NegativeLoginTest() {
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

    @Test(description = "Select a date on the dashboard")
    public void pickDashboardDateTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickWhenReady(loginPage.date());
        loginPage.clickPreviousMonthButton();
        loginPage.clickDay1();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The first day was selected.");
        loginPage.clickDay3();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The third day was selected.");
        Assert.assertEquals(loginPage.getValidationDay1().getText(), "Oct 01, 2024 - Oct 03, 2024", "The selected dates were not displayed on the Calendar.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The selected dates were successfully displayed on the Calendar.");
        softAssert.assertAll();
    }

    @Test(description = "Delete selected date on the dashboard")
    public void deleteDashboardDateTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickWhenReady(loginPage.date());
        loginPage.clickPreviousMonthButton();
        loginPage.clickDay1();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The first day was selected.");
        loginPage.clickDay3();
        ExtentTestNGITestListener.getTest().log(Status.INFO, "The third day was selected.");
        loginPage.clickStergeFiltruButton();
        Assert.assertEquals(loginPage.getPickADate().getText(), "Pick a date", "The dates weren't successfully deleted, the 'Pick a date' button is not displayed.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The selected dates were deleted.");
        softAssert.assertAll();
    }

    @Test(description = "Creating a new location")
    public void locationTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        DatabaseUtils.deleteVenueByOrganisationId(getDbOrganizationId2());
        wait.until(ExpectedConditions.visibilityOf(loginPage.getLocatiiIcon()));
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLocatiiIcon()));
        loginPage.clickLocatiiIcon();
        loginPage.clickWhenReady(loginPage.getCreatiLocatieIcon());
        loginPage.sendKeysWhenReady(loginPage.getNameLocatieField(), "New location");
        loginPage.setAdressLocatieField("Address");
        loginPage.setCityLocatieField("City");
        loginPage.setCodeLocatieField("123456");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(580, 2800);");
        Thread.sleep(1000);
        loginPage.clickCreatiLocatiaButton();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getConfirmationLocation()));
        Thread.sleep(3000);
        Assert.assertEquals(loginPage.getConfirmationLocation().getText(), "New location", "The location is not created and it's not displayed in the page");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "A new location was successfully created.");
//        DatabaseUtils.deleteVenueByOrganisationId(getDbOrganizationId2());
        softAssert.assertAll();
    }

    @Test(description = "Creating menu")
    public void createMenuTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickMeniuButton();
        loginPage.clickWhenReady(loginPage.getCreatiMeniuButton());
        loginPage.sendKeysWhenReady(loginPage.getNumeMeniu(), "Meniul zilei");
        loginPage.sendKeysWhenReady(loginPage.getDescriereMeniu(), "Un meniu cu produse delicioase");
        wait.until(ExpectedConditions.visibilityOf(loginPage.getCreatiMeniuButton()));
        loginPage.clickWhenReady(loginPage.getCreatiMeniuFinalButtton());
        wait.until(ExpectedConditions.visibilityOf(loginPage.getMenuValidation()));
        Assert.assertEquals(loginPage.getMenuValidation().getText(), "Meniul zilei", "The menu was not created successfully.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The menu was created and displayed successfully.");
        softAssert.assertAll();
    }

    @Test(description = "Adding a category to the menu")
    public void addingCategoryToMenuTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickMeniuButton();
        loginPage.clickWhenReady(loginPage.getMeniulZilei());
        loginPage.clickWhenReady(loginPage.getAddCategorieNouaButton());
        loginPage.setNameCat("Pizza");
        loginPage.sendKeysWhenReady(loginPage.getDescriptionCat(), "Diverse feluri de pizza");
        loginPage.clickCreatiCatButton();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getCategoryConfirmation()));
        Assert.assertEquals(loginPage.getCategoryConfirmation().getText(), "Pizza (0)", "The category is not displayed on the page.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The category was added successfully.");
        softAssert.assertAll();
    }

    @Test(description = "Editing Category of the menu")
    public void editCatTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickMeniuButton();
        loginPage.clickWhenReady(loginPage.getMeniulZilei());
        loginPage.clickWhenReady(loginPage.getEditCatIcon());
        loginPage.deleteAndEditNameCat("Paste");
        loginPage.setEditDescriptionCat("Diverse feluri de paste");
        loginPage.clickWhenReady(loginPage.getSaveEditCat());
        wait.until(ExpectedConditions.visibilityOf(loginPage.getEditCatConfirmation()));
        Thread.sleep(3000);
        Assert.assertEquals(loginPage.getEditCatConfirmation().getText(), "Paste (0)", "The category was not successfully edited.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The category was successfully edited.");
        softAssert.assertAll();
    }

    @Test(description = "Removing a category from menu")
    public void removingCatFromMenu() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickMeniuButton();
        loginPage.clickWhenReady(loginPage.getMeniulZilei());
        loginPage.clickWhenReady(loginPage.getDeleteCategory());
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDeletCatNotification()));

        try {
            if (loginPage.getCategoryConfirmation().isDisplayed()) {
                Assert.fail("Element is still present, the category is not removed.");
            }
        } catch (NoSuchElementException e) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The category was successfully removed.");
            Assert.assertTrue(true, "The Category was successfully deleted.");
        }
        softAssert.assertAll();
    }

    @Test(description = "Creating products")
    public void creatingProductsTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickProduseIcon();
        loginPage.clickWhenReady(loginPage.getAdaugaProdusButton());
        wait.until(ExpectedConditions.visibilityOf(loginPage.getNameProduct()));
        loginPage.setNameProduct("Paste Carbonara");
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(521, 1042);");
        Thread.sleep(1000);
        loginPage.selectCategoryDropdown();
        loginPage.selectTipProdusDrop();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getPriceField()));
        loginPage.setPriceField("20");
        loginPage.clickWhenReady(loginPage.getCreatiProdusulButton());
        wait.until(ExpectedConditions.visibilityOf(loginPage.getConfirmationProduse()));
        Assert.assertEquals(loginPage.getConfirmationProduse().getText(), "Produse", "Produse page is not displayed.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The product has been created and it's displayed on the products page.");
        softAssert.assertAll();
    }

    @Test(description = "Creating Set modificatori test")
    public void createSetModificatoriTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickSeturiModificatoriIcon();
        loginPage.clickWhenReady(loginPage.getSeturiModificatoriIcon());
        loginPage.clickWhenReady(loginPage.getCreeazaSetNou());
        wait.until(ExpectedConditions.visibilityOf(loginPage.getNumeModificator()));
        loginPage.setNumeModificator("Sosuri");
        Thread.sleep(3000);
        loginPage.selectModificatoriDropdown();
        loginPage.clickWhenReady(loginPage.getCreatiSetNouButton());
        wait.until(ExpectedConditions.visibilityOf(loginPage.getModificatorValidation()));
        Assert.assertEquals(loginPage.getModificatorValidation().getText(), "Sosuri", "The 'Sosuri' Modificator is not displayed on the Modificatori page");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The " + loginPage.getModificatorValidation().getText() + " is displayed on the Modificatori page");
        softAssert.assertAll();
    }

    @Test(description = "Editing 'Set modificatori' test")
    public void editSetModificatoriTest() throws InterruptedException {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickSeturiModificatoriIcon();
        loginPage.clickWhenReady(loginPage.getEditSetModif());
        loginPage.clickWhenReady(loginPage.getNumeSetModif());
        loginPage.deleteAndEditNameSetModif("Parmezan");
        loginPage.clickWhenReady(loginPage.getSaveModif());
        wait.until(ExpectedConditions.visibilityOf(loginPage.getEditModifValidation()));
        Thread.sleep(2000);
        Assert.assertEquals(loginPage.getEditModifValidation().getText(), "Parmezan", "The 'Sosuri' was not successfully edited.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The 'Sosuri' modificator was edited and  the '" + loginPage.getEditModifValidation().getText() + "' is displayed instead.");
        softAssert.assertAll();
    }

    @Test(description = "Deleting 'Set de modificatori' test")
    public void deleteModificatoriTest() {
        loginPage.loginTest();
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDashboard()));

        if (loginPage.getDashboard().getText().equals("Dashboard")) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The user was successfully logged in, the Dashboard is displayed.");
        } else {
            softAssert.fail("The user has not successfully log in and the Dashboard is not displayed.");
        }

        loginPage.clickSeturiModificatoriIcon();
        loginPage.clickWhenReady(loginPage.getSeturiModificatoriIcon());
        loginPage.clickWhenReady(loginPage.getStergeModificator());
        loginPage.clickWhenReady(loginPage.getContinuatiButton());
        wait.until(ExpectedConditions.visibilityOf(loginPage.getDeleteModifValidation()));

        try {
            if (loginPage.getModificatorValidation().isDisplayed()) {
                Assert.fail("The 'Modificator set' is still displayed after the user clicks Delete button.");
            }
        } catch (NoSuchElementException e) {
            ExtentTestNGITestListener.getTest().log(Status.PASS, "The 'Modificator set' was successfully removed.");
            Assert.assertTrue(true, "The 'Modificatori set' was successfully deleted.");
        }

        Assert.assertEquals(loginPage.getDeleteModifValidation().getText(), "Nu aveți seturi de modificatori încă", "The Set de modificatori is not deleted.");
        ExtentTestNGITestListener.getTest().log(Status.PASS, "The 'Set de modificatori' is successfully deleted.");
        softAssert.assertAll();
    }
}



