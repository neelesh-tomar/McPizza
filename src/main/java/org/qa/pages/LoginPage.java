package org.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qa.base.TestBase;
import org.qa.util.Utility;

public class LoginPage extends TestBase {
    private final String iFrameName = "avaamoIframe";
    @FindBy(className = "note")
    WebElement loginPageMessage;

    @FindBy(id = "first_name")
    WebElement firstNameInput;

    @FindBy(id = "email")
    WebElement emailInput;

    @FindBy(xpath = "//button[@type = 'submit']")
    WebElement nextBtn;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public String getLoginPageMsg() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        return loginPageMessage.getText();
    }

    public void enterFirstName(String fName) throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        firstNameInput.sendKeys(fName);
    }

    public void enterEmail(String email) throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        emailInput.sendKeys(email);
    }

    public String getNextBtnText() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        return nextBtn.getText();
    }

    public void clickNextBtn() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        nextBtn.click();
    }

    public boolean firstNameError() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        return firstNameInput.getAttribute("class").contains("error");
    }

    public boolean emailError() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        return emailInput.getAttribute("class").contains("error");
    }

    public void login(String fname, String email) throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        firstNameInput.sendKeys(fname);
        emailInput.sendKeys(email);
        nextBtn.click();
    }
}
