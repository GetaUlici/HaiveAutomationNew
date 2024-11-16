import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;


// Declaring the CheckoutPage class, which extends the BasePage class.
// By extending BasePage, CheckoutPage inherits the WebDriver instance and the PageFactory initialization.
public class LocationPage extends BasePage {

    // Declaring a public WebDriverWait variable named 'wait'.
    // WebDriverWait is used for implementing explicit waits during interactions with web elements.
    public WebDriverWait wait;

    public SoftAssert softAssert;

    // Constructor for the CheckoutPage class that takes a WebDriver object as an argument.
    // This constructor calls the parent class (BasePage) constructor to initialize the WebDriver instance
    // and sets up the PageFactory to initialize the web elements on this page.
    public LocationPage(WebDriver driver) {
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


    @FindBy(linkText = "Locații")
    private WebElement locatiiIcon;

    public void clickLocatiiIcon() {
        try {
            // Wait until the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(locatiiIcon));
            locatiiIcon.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if the regular click fails
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", locatiiIcon);
        }
    }

    public WebElement getLocatiiIcon() {
        return locatiiIcon;
    }

    @FindBy(linkText = "Creați locație")
    private WebElement creatiLocatieIcon;

    public void clickCreatiLocatieIcon() {
        creatiLocatieIcon.click();
    }

    public WebElement getCreatiLocatieIcon() {
        return creatiLocatieIcon;
    }

    @FindBy(name = "name")
    private WebElement nameLocatieField;

    public void setNameLocatieField(String setName) {
        nameLocatieField.sendKeys(setName);
    }

    public WebElement getNameLocatieField() {
        return nameLocatieField;
    }

    public WebElement getNameLocatie() {
        return nameLocatieField;
    }

    @FindBy(name = "address")
    private WebElement adressLocatieField;

    public void setAdressLocatieField(String setAdress) {
        adressLocatieField.sendKeys(setAdress);
    }

    @FindBy(name = "city")
    private WebElement cityLocatieField;

    public void setCityLocatieField(String setCity) {
        cityLocatieField.sendKeys(setCity);
    }

    @FindBy(name = "postcode")
    private WebElement codeLocatieField;

    public void setCodeLocatieField(String post) {
        codeLocatieField.sendKeys(post);
    }

    @FindBy(xpath = "//button[contains(text(), 'Creați locația')]")
    private WebElement creatiLocatiaButton;

    public void clickCreatiLocatiaButton() {
        creatiLocatiaButton.click();
    }

    public WebElement getCreatiLocatiaButton() {
        return creatiLocatiaButton;
    }

    @FindBy(css = ".text-xl.font-semibold.leading-none.tracking-tight")
    private WebElement confirmationLocation;

    public WebElement getConfirmationLocation() {
        return confirmationLocation;
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