package org.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qa.base.TestBase;

public class CommonPage extends TestBase {

    @FindBy(xpath = "//a[@class = 'avm-bot-avatar']")
    @CacheLookup
    WebElement startChatBtn;

    @FindBy(xpath = "//a[@aria-label = 'Close McPizza']")
    @CacheLookup
    WebElement closeChatBtn;

    public CommonPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isChatOpen() {
        return closeChatBtn.isDisplayed();
    }

    public boolean isChatClosed() {
        return startChatBtn.isDisplayed();
    }

    public void startChat() {
        startChatBtn.click();
    }

    public void closeChat() {
        closeChatBtn.click();
    }
}
