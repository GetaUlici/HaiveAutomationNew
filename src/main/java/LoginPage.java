import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;


// Declaring the CheckoutPage class, which extends the BasePage class.
// By extending BasePage, CheckoutPage inherits the WebDriver instance and the PageFactory initialization.
public class LoginPage extends BasePage {

    // Declaring a public WebDriverWait variable named 'wait'.
    // WebDriverWait is used for implementing explicit waits during interactions with web elements.
    public WebDriverWait wait;

    public SoftAssert softAssert;

    // Constructor for the CheckoutPage class that takes a WebDriver object as an argument.
    // This constructor calls the parent class (BasePage) constructor to initialize the WebDriver instance
    // and sets up the PageFactory to initialize the web elements on this page.
    public LoginPage(WebDriver driver) {
        // Calling the parent class (BasePage) constructor using 'super' to initialize the WebDriver.
        super(driver);

        // Initializing the WebDriverWait object with a 10-second timeout.
        // This will be used to wait for certain conditions or elements during test execution.
        wait = new WebDriverWait(driver, 10);
        softAssert = new SoftAssert();
    }

    // Locating the search bar element using the @FindBy annotation.
    // @FindBy is a Selenium annotation that helps locate elements on the web page.
    // Here, the element is being located by its 'id' attribute with the value "input-search".
    // Declare the WebElement as private to enforce encapsulation
    // This ensures that 'searchBar' cannot be accessed directly from outside this class
    @FindBy(id = "input-search")
    private WebElement searchBar;

    // A public method to set a value in the search bar.
    // This method interacts with the searchBar element and sends the text "mouse" to it.
    // Public method to interact with the private 'searchBar' element
    // Provides controlled access to the encapsulated WebElement
    public void setSearchBar(String search) {
        // Typing the word "mouse" into the search bar.
        searchBar.sendKeys(search);
    }

    // Locating the search button element using the @FindBy annotation.
    // The element is being located by its CSS selector, which identifies elements based on their classes.
    // Here, the button has the classes "btn", "btn-light", and "btn-sm".


    @FindBy(linkText = "Autentificare")
    private WebElement loginIcon;

    public void clickLoginIcon() {
        loginIcon.click();
    }

    @FindBy(name = "email")
    private WebElement userNameField;

    public void setUserNameField(String name) {
        userNameField.sendKeys(name);
    }

    public WebElement getUserNameField() {
        return userNameField;
    }

    @FindBy(name = "password")
    private WebElement passwordField;

    public void setPasswordField(String password) {
        passwordField.sendKeys(password);
    }

    @FindBy(xpath = "//button[text()='Autentificare']")
    private WebElement loginButton;

    public void clickLoginButton() {
        loginButton.click();
    }

    @FindBy(xpath = "//h1[text()='Dashboard']")
    private WebElement dashboard;

    public WebElement getDashboard() {
        return dashboard;
    }

    @FindBy(xpath = "//label[text()='Cod de verificare']")
    private WebElement codVerificare;

    public WebElement getCodVerificare() {
        return codVerificare;
    }

    @FindBy(xpath = "//div[text()='Invalid email']")
    private WebElement errorEmail;

    public WebElement getErrorEmail() {
        return errorEmail;
    }

    @FindBy(xpath = "//div[text()='String must contain at least 8 character(s)']")
    private WebElement errorPassword;

    public WebElement getErrorPassword() {
        return errorPassword;
    }

    @FindBy(xpath = "//div[text()='Ceva nu a mers bine']")
    private WebElement cevaNuAMersBine;

    public WebElement getCevaNuAMersBine() {
        return cevaNuAMersBine;
    }

    @FindBy(linkText = "Nu aveți un cont? Înregistrați-vă aici")
    private WebElement authenticateIcon;

    public void clickAuthenticateIcon() {
        authenticateIcon.click();
    }

    public WebElement getAuthenticateIcon() {
        return authenticateIcon;
    }

    @FindBy(name = "firstName")
    private WebElement nameField;

    public void setNameField(String name) {
        nameField.sendKeys(name);
    }

    public WebElement getNameField() {
        return nameField;
    }

    @FindBy(name = "email")
    private WebElement emailField;

    public void setEmailField(String email) {
        emailField.sendKeys(email);
    }

    @FindBy(name = "password")
    private WebElement passField;

    public void setPassField(String pass) {
        passField.sendKeys(pass);
    }

    @FindBy(xpath = "//button[text()='Creați contul']")
    private WebElement creatiContButton;

    public void clickCreatiContButton() {
        creatiContButton.click();
    }

    @FindBy(xpath = "//h1[text()='Autentificare']")
    public WebElement autentificareText;

    public WebElement getAutentificareText() {
        return autentificareText;
    }

    public void loginTest() {
        clickLoginIcon();
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        setUserNameField("ulicigeta+1@gmail.com");
        setPasswordField("ParolaHaive1");
        clickLoginButton();
    }

    public void selectOption(WebElement element, String option) {
        Select optionSelect = new Select(element);
        optionSelect.selectByVisibleText(option);
    }

    public void NegativeLoginTest() {
        clickLoginIcon();
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        setUserNameField("dino@dino.com");
        setPasswordField("choochoochooo");
        clickLoginButton();
    }

    public void clickWhenReady(WebElement locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void sendKeysWhenReady(WebElement locator, String text) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(text);
    }
}