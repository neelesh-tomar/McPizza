package org.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qa.base.TestBase;

public class WelcomePage extends TestBase {
    @FindBy(className = "welcome-message")
    @CacheLookup
    WebElement welcomeMsgPH;

    @FindBy(className = "get-started-link")
    @CacheLookup
    WebElement getStartedBtn;

    public WelcomePage() {
        PageFactory.initElements(driver, this);
    }

    public String getWelcomeMessage() {
        return welcomeMsgPH.getText();
    }

    public String getStartedBtnText() {
        return getStartedBtn.getText();
    }

    public void clickGetStarted() {
        getStartedBtn.click();
    }
}
