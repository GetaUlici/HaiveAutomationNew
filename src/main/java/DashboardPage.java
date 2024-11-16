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
public class DashboardPage extends BasePage {

    // Declaring a public WebDriverWait variable named 'wait'.
    // WebDriverWait is used for implementing explicit waits during interactions with web elements.
    public WebDriverWait wait;

    public SoftAssert softAssert;

    // Constructor for the CheckoutPage class that takes a WebDriver object as an argument.
    // This constructor calls the parent class (BasePage) constructor to initialize the WebDriver instance
    // and sets up the PageFactory to initialize the web elements on this page.
    public DashboardPage(WebDriver driver) {
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


    @FindBy(xpath = "//h1[text()='Dashboard']")
    private WebElement dashboard;

    public WebElement getDashboard() {
        return dashboard;
    }

    @FindBy(xpath = "(//button[@name='day' and text()='1'])[1]")
    private WebElement day1;

    public void clickDay1() {
        day1.click();
    }

    @FindBy(xpath = "(//button[@name='day' and text()='3'])[1]")
    private WebElement day3;

    public void clickDay3() {
        day3.click();
    }

    @FindBy(id = "date")
    private WebElement datePicker;

    public void clickDate() {
        datePicker.click();
    }

    public WebElement date() {
        return datePicker;
    }

    @FindBy(xpath = "//button[text()='Oct 01, 2024']")
    private WebElement validationDay1;

    public WebElement getValidationDay1() {
        return validationDay1;
    }

    @FindBy(name = "previous-month")
    private WebElement previousMonthButton;

    public void clickPreviousMonthButton() {
        previousMonthButton.click();
    }

    @FindBy(xpath = "//button[text()='Șterge filtru']")
    private WebElement stergeFiltruButton;

    public void clickStergeFiltruButton() {
        stergeFiltruButton.click();
    }

    @FindBy(xpath = "//span[text()='Pick a date']")
    private WebElement pickADate;

    public WebElement getPickADate() {
        return pickADate;
    }

    @FindBy(css = ".relative.flex.shrink-0.overflow-hidden.rounded-full.size-8")
    private WebElement avatarIcon;

    public void clickAvatarIcon() {
        avatarIcon.click();
    }

    public WebElement getAvatarIcon() {
        return avatarIcon;
    }

    @FindBy(xpath = "//p[text()='ulicigeta+1@gmail.com']")
    private WebElement emailConfirmation;

    public WebElement getEmailConfirmation() {
        return emailConfirmation;
    }

    @FindBy(xpath = "//div[text()='Ieşi din cont']")
    private WebElement iesiDinContButton;

    public void clickIesiCont() {
        iesiDinContButton.click();
    }

    public void selectOption(WebElement element, String option) {
        Select optionSelect = new Select(element);
        optionSelect.selectByVisibleText(option);
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