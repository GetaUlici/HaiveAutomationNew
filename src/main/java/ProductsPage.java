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
public class ProductsPage extends BasePage {

    // Declaring a public WebDriverWait variable named 'wait'.
    // WebDriverWait is used for implementing explicit waits during interactions with web elements.
    public WebDriverWait wait;

    public SoftAssert softAssert;

    // Constructor for the CheckoutPage class that takes a WebDriver object as an argument.
    // This constructor calls the parent class (BasePage) constructor to initialize the WebDriver instance
    // and sets up the PageFactory to initialize the web elements on this page.
    public ProductsPage(WebDriver driver) {
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


    @FindBy(linkText = "Produse")
    private WebElement produseIcon;

    public void clickProduseIcon() {
        try {
            // Wait until the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(produseIcon));
            produseIcon.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if the regular click fails
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", produseIcon);
        }
    }

    public WebElement getProduseIcon() {
        return produseIcon;
    }

    @FindBy(linkText = "Adaugă produs")
    private WebElement adaugaProdusButton;

    public void clickAdaugaProdusButton() {
        try {
            // Wait until the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(adaugaProdusButton));
            adaugaProdusButton.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if the regular click fails
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", adaugaProdusButton);
        }
    }

    public WebElement getAdaugaProdusButton() {
        return adaugaProdusButton;
    }

    @FindBy(name = "name")
    private WebElement nameProduct;

    public void setNameProduct(String name) {
        nameProduct.sendKeys(name);
    }

    public void clickNameProduct() {
        nameProduct.click();
    }

    public WebElement getNameProduct() {
        return nameProduct;
    }

    @FindBy(xpath = "//button[@role='combobox']")
    private WebElement categoryDropdown;

    public WebElement getCategoryDropdown() {
        return categoryDropdown;
    }

    @FindBy(xpath = "//span[text()='Paste']")
    private WebElement optionDropdown;

    public void clickOptionDropdown() {
        optionDropdown.click();
    }

    public WebElement getOptionDropdown() {
        return optionDropdown;
    }

    public void selectCategoryDropdown() {
        categoryDropdown.click();
        wait.until(ExpectedConditions.visibilityOf(optionDropdown));
        optionDropdown.click();
    }

    @FindBy(xpath = "(//button[@role='combobox'])[2]")
    private WebElement tipProdus;

    public WebElement getTipProdus() {
        return tipProdus;
    }

    public void selectTipProdusDrop() {
        tipProdus.click();
        wait.until(ExpectedConditions.visibilityOf(mancareTypeProduct));
        mancareTypeProduct.click();
    }

    @FindBy(xpath = "//span[text()='Mâncare']")
    private WebElement mancareTypeProduct;

    public WebElement getMancareTypeProduct() {
        return mancareTypeProduct;
    }

    @FindBy(name = "price")
    private WebElement priceField;

    public void setPriceField(String price) {
        priceField.sendKeys(price);
    }

    public WebElement getPriceField() {
        return priceField;
    }

    @FindBy(xpath = "//button[text()='Creați produsul']")
    private WebElement creatiProdusulButton;

    public WebElement getCreatiProdusulButton() {
        return creatiProdusulButton;
    }

    @FindBy(xpath = "//h1[text()='Produse']")
    private WebElement confirmationProduse;

    public WebElement getConfirmationProduse() {
        return confirmationProduse;
    }

    @FindBy(xpath = "//h3[text()='Paste Carbonara']")
    private WebElement pasteCarbonaraProduct;

    public WebElement getPasteCarbonaraProduct() {
        return pasteCarbonaraProduct;
    }

    @FindBy(xpath = "//h3[text()='Paste bologneze']")
    private WebElement pasteBolognezeProduct;

    public WebElement getPasteBolognezeProduct() {
        return pasteBolognezeProduct;
    }

    @FindBy(xpath = "//button[text()='Actualizați produsul']")
    private WebElement actualizatiProdusul;

    public WebElement getActualizatiProdusul() {
        return actualizatiProdusul;
    }

    @FindBy(xpath = "//button[text()='Ștergeți produsul']")
    private WebElement stergetiProdusul;

    public WebElement getStergetiProdusul() {
        return stergetiProdusul;
    }

    @FindBy(xpath = "//button[text()='Continuă']")
    private WebElement continuaButton;

    public WebElement getContinuaButton() {
        return continuaButton;
    }

    @FindBy(xpath = "//div[text()='Operația a fost efectuată cu succes']")
    private WebElement validationActualizare;

    public WebElement getValidationActualizare() {
        return validationActualizare;
    }
    @FindBy(xpath = "//div[text()='Operația a fost efectuată cu succes']")
    private WebElement validationDelete;

    public WebElement getValidationDelete() {
        return validationDelete;
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
    public void deleteAndEditProductName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameProduct));
        nameProduct.click();
        nameProduct.sendKeys(Keys.COMMAND + "a");
        nameProduct.sendKeys(Keys.BACK_SPACE);
        wait.until(ExpectedConditions.visibilityOf(nameProduct));
        nameProduct.sendKeys(name);
    }
}