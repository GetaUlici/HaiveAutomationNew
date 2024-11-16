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
public class MenuPage extends BasePage {

    // Declaring a public WebDriverWait variable named 'wait'.
    // WebDriverWait is used for implementing explicit waits during interactions with web elements.
    public WebDriverWait wait;

    public SoftAssert softAssert;

    // Constructor for the CheckoutPage class that takes a WebDriver object as an argument.
    // This constructor calls the parent class (BasePage) constructor to initialize the WebDriver instance
    // and sets up the PageFactory to initialize the web elements on this page.
    public MenuPage(WebDriver driver) {
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


    @FindBy(linkText = "Meniu")
    private WebElement meniuButton;

    public void clickMeniuButton() {

        try {
            // Wait until the element is clickable
            wait.until(ExpectedConditions.elementToBeClickable(meniuButton));
            meniuButton.click();
        } catch (Exception e) {
            // Fallback to JavaScript click if the regular click fails
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", meniuButton);
        }
    }

    public WebElement getMeniuButton() {
        return meniuButton;
    }

    @FindBy(xpath = "//button[text()='Creați meniu']")
    private WebElement creatiMeniuButton;

    public void clickCreatiMeniuButton() {
        creatiMeniuButton.click();
    }

    public WebElement getCreatiMeniuButton() {
        return creatiMeniuButton;
    }

    @FindBy(name = "name")
    private WebElement numeMeniu;

    public void setNumeMeniu(String name) {
        numeMeniu.sendKeys(name);
    }

    public WebElement getNumeMeniu() {
        return numeMeniu;
    }

    @FindBy(id = "description")
    private WebElement descriereMeniu;

    public void setDescriereMeniu() {
        descriereMeniu.sendKeys("Test");
    }

    public WebElement getDescriereMeniu() {
        return descriereMeniu;
    }

    @FindBy(xpath = "(//button[text()='Creați meniu'])[2]")
    private WebElement creatiMeniuFinalButtton;

    public void clickCreatiMeniuFinalButton() {
        creatiMeniuFinalButtton.click();
    }

    public WebElement getCreatiMeniuFinalButtton() {
        return creatiMeniuFinalButtton;
    }

    @FindBy(xpath = "//h1[text()='Meniul zilei']")
    private WebElement menuValidation;

    public WebElement getMenuValidation() {
        return menuValidation;
    }

    @FindBy(xpath = "//span[text()='Meniul zilei']")
    private WebElement meniulZilei;

    public void clickMeniulZilei() {
        meniulZilei.click();
    }

    public WebElement getMeniulZilei() {
        return meniulZilei;
    }

    @FindBy(xpath = "//button[text()='Categorie nouă']")
    private WebElement addCategorieNouaButton;

    public void clickAddCategorie() {
        addCategorieNouaButton.click();
    }

    public WebElement getAddCategorieNouaButton() {
        return addCategorieNouaButton;
    }

    @FindBy(name = "name")
    private WebElement nameCat;

    public void setNameCat(String name) {
        nameCat.sendKeys(name);
    }

    @FindBy(xpath = "//textarea[@placeholder='ex: Antreuri']")
    private WebElement descriptionCat;

    public void setDescriptionCat(String description) {
        descriptionCat.sendKeys(description);
    }

    public WebElement getDescriptionCat() {
        return descriptionCat;
    }

    @FindBy(xpath = "//button[text()='Creați']")
    private WebElement creatiCatButton;

    public void clickCreatiCatButton() {
        creatiCatButton.click();
    }

    @FindBy(css = ".lucide.lucide-trash.size-4.text-destructive")
    private WebElement deleteCategory;

    public void clickDeleteCat() {
        deleteCategory.click();
    }

    public WebElement getDeleteCategory() {
        return deleteCategory;
    }

    @FindBy(xpath = "//h2[text()= 'Pizza']")
    private WebElement categoryConfirmation;

    public WebElement getCategoryConfirmation() {
        return categoryConfirmation;
    }

    @FindBy(xpath = "//div[text()='Category deleted successfully']")
    private WebElement deletCatNotification;

    public WebElement getDeletCatNotification() {
        return deletCatNotification;
    }

    @FindBy(xpath = "(//button[@type='button'])[3]")
    private WebElement editCatIcon;

    public void clickEditCatIcon() {
        editCatIcon.click();
    }

    public WebElement getEditCatIcon() {
        return editCatIcon;
    }


    @FindBy(xpath = "//input[@name='name']")
    private WebElement editNameCat;

    public void deleteAndEditNameCat(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(editNameCat));
        editNameCat.click();
        editNameCat.sendKeys(Keys.COMMAND + "a");
        editNameCat.sendKeys(Keys.BACK_SPACE);
        wait.until(ExpectedConditions.visibilityOf(editCatIcon));
        editNameCat.sendKeys(name);
    }

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement editDescriptionCat;

    public void setEditDescriptionCat(String des) {
        wait.until(ExpectedConditions.elementToBeClickable(editDescriptionCat));
        editDescriptionCat.click();
        editDescriptionCat.sendKeys(Keys.COMMAND + "a");
        editDescriptionCat.sendKeys(Keys.BACK_SPACE);
        wait.until(ExpectedConditions.visibilityOf(editDescriptionCat));
        editDescriptionCat.sendKeys(des);
    }

    public WebElement getEditDescriptionCat() {
        return editDescriptionCat;
    }

    @FindBy(xpath = "//button[text()='Salvați']")
    private WebElement saveEditCat;

    public void clickSaveEditCat() {
        saveEditCat.click();
    }

    public WebElement getSaveEditCat() {
        return saveEditCat;
    }

    @FindBy(css = ".text-lg.font-semibold")
    private WebElement editCatConfirmation;

    public WebElement getEditCatConfirmation() {
        return editCatConfirmation;
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