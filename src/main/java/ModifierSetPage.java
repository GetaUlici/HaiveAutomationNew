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
public class ModifierSetPage extends BasePage {

    // Declaring a public WebDriverWait variable named 'wait'.
    // WebDriverWait is used for implementing explicit waits during interactions with web elements.
    public WebDriverWait wait;

    public SoftAssert softAssert;

    // Constructor for the CheckoutPage class that takes a WebDriver object as an argument.
    // This constructor calls the parent class (BasePage) constructor to initialize the WebDriver instance
    // and sets up the PageFactory to initialize the web elements on this page.
    public ModifierSetPage(WebDriver driver) {
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



    @FindBy(xpath = "//input[@name='name']")
    private WebElement editFieldModif;


    @FindBy(linkText = "Seturi modificatori")
    private WebElement seturiModificatoriIcon;

    public void clickSeturiModificatoriIcon() {
        try {
            // Wait until the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(seturiModificatoriIcon));
            seturiModificatoriIcon.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if the regular click fails
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", seturiModificatoriIcon);
        }
    }

    public WebElement getSeturiModificatoriIcon() {
        return seturiModificatoriIcon;
    }

    @FindBy(xpath = "//button[text()='Creează un nou set']")
    private WebElement creeazaSetNou;

    public WebElement getCreeazaSetNou() {
        return creeazaSetNou;
    }

    @FindBy(name = "name")
    private WebElement numeModificator;

    public WebElement getNumeModificator() {
        return numeModificator;
    }

    public void setNumeModificator(String modif) {
        numeModificator.sendKeys(modif);
    }

    @FindBy(xpath = "//input[@placeholder='Selectati modificatorii']")
    private WebElement modificatoriDropdown;

    public void clickModificatoriDropdown() {
        modificatoriDropdown.click();
    }

    public WebElement getModificatoriDropdown() {
        return modificatoriDropdown;
    }

    @FindBy(xpath = "//div[@data-value='Paste Carbonara']")
    private WebElement optDropdown;


    public void selectModificatoriDropdown() throws InterruptedException {
        modificatoriDropdown.click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(optDropdown));
        clickWhenReady(optDropdown);
    }

    @FindBy(xpath = "//button[text()='Creați set nou']")
    private WebElement creatiSetNouButton;

    public WebElement getCreatiSetNouButton() {
        return creatiSetNouButton;
    }

    @FindBy(xpath = "//h3[text()='Sosuri']")
    private WebElement modificatorValidation;

    public WebElement getModificatorValidation() {
        return modificatorValidation;
    }

    @FindBy(xpath = "(//button[text()='Șterge'])[1]")
    private WebElement stergeModificator;

    public WebElement getStergeModificator() {
        return stergeModificator;
    }

    @FindBy(xpath = "//button[text()='Continuați']")
    private WebElement continuatiButton;

    public WebElement getContinuatiButton() {
        return continuatiButton;
    }

    @FindBy(xpath = "//h2[text()='Nu aveți seturi de modificatori încă']")
    private WebElement deleteModifValidation;

    public WebElement getDeleteModifValidation() {
        return deleteModifValidation;
    }

    @FindBy(xpath = "//button[text()='Editează']")
    private WebElement editSetModif;

    public WebElement getEditSetModif() {
        return editSetModif;
    }

    @FindBy(name = "name")
    private WebElement numeSetModif;

    public WebElement getNumeSetModif() {
        return numeSetModif;
    }

    @FindBy(xpath = "//button[text()='Salvați']")
    private WebElement SaveModif;

    public WebElement getSaveModif() {
        return SaveModif;
    }

    @FindBy(css = ".text-xl.font-semibold.leading-none.tracking-tight.p-4")
    private WebElement editModifValidation;

    public WebElement getEditModifValidation() {
        return editModifValidation;
    }

    public void deleteAndEditNameSetModif(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(numeSetModif));
        numeSetModif.click();
        editFieldModif.sendKeys(Keys.COMMAND + "a");
        editFieldModif.sendKeys(Keys.BACK_SPACE);
        wait.until(ExpectedConditions.visibilityOf(numeSetModif));
        editFieldModif.sendKeys(name);
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