package org.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.qa.base.TestBase;
import org.qa.util.Utility;

import java.util.List;

public class ChatWindow extends TestBase {

    private final String iFrameName = "avaamoIframe";
    @FindBy(id = "queryTextbox")
    WebElement messageInput;

    @FindBy(xpath = "//p[contains(text(),'Welcome to McPizza Booking Journey')]")
    WebElement wlcmMsg;

    @FindBy(xpath = "//div[contains(@class, 'conversation-item')]")
    List<WebElement> msgList;

    @FindBy(xpath = "//a[@title='McPizza order']")
    WebElement McPizzaOrderBtn;

    @FindBy(xpath = "//a[contains(@title, 'No, that')]")
    WebElement denyBtn;

    @FindBy(xpath = "//button[@aria-label='Send']")
    WebElement sendMsgBtn;

    @FindBy(xpath = "//a[@title='veg']")
    WebElement vegBtn;

    @FindBy(xpath = "//a[@title='non-veg']")
    WebElement nonvegBtn;

    @FindBy(xpath = "//input[@value='cheese_id']")
    WebElement cheeseToppingChkbx;

    @FindBy(xpath = "//input[@value='tomato_id']")
    WebElement tomatoToppingChkbx;

    @FindBy(xpath = "//input[@value='bacon_id']")
    WebElement baconToppingChkbx;

    @FindBy(xpath = "//input[@value='pepperoni_id']")
    WebElement pepperoniToppingChkbx;

    @FindBy(xpath = "//button[@class='btn default_card_submit']")
    WebElement submitToppingBtn;

    @FindBy(xpath = "//div[contains(text(),'Thin Crust')]//following::div[2]//a")
    WebElement thinCrustBtn;

    @FindBy(xpath = "//div[contains(text(),'Thick Crust')]//following::div[2]//a")
    WebElement thickCrustBtn;

    @FindBy(xpath = "//a[@title='Small']")
    WebElement smallSize;

    @FindBy(xpath = "//a[@title='Medium']")
    WebElement mediumSize;

    @FindBy(xpath = "//a[@title='Large']")
    WebElement largeSize;

    @FindBy(xpath = "//a[@title='Yes']")
    WebElement confirmOrderBtn;

    @FindBy(xpath = "//a[@title='No']")
    WebElement notConfirmOrderBtn;

    @FindBy(xpath = "//button[@class='thumbs-up locale-trans']")
    WebElement thumbsUpBtn;

    @FindBy(xpath = "//button[@class='thumbs-down locale-trans']")
    WebElement thumbsDownBtn;

    @FindBy(xpath = "//input[@role='combobox']")
    WebElement feedbackDD;

    @FindBy(xpath = "//li[@data-idx='Excellent']")
    WebElement feedbkExcelentOpt;

    @FindBy(xpath = "//button[@class='btn default_card_submit']")
    WebElement feedbackSubmitBtn;

    @FindBy(xpath = "//input[@aria-label='Feedback to improve']")
    WebElement feedbackTxtBox;

    public ChatWindow() {
        PageFactory.initElements(driver, this);
    }

    public void sendMessage(String message) throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        messageInput.sendKeys(message);
        sendMsgBtn.click();
    }

    public String getMessage() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        return wlcmMsg.getText();
    }

    public String getTitle() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        return driver.getTitle();
    }

    public String getWelcomeMessage() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        return msgList.get(0).findElement(By.tagName("p")).getText();
    }

    public List<WebElement> getAllMessages() throws Exception {
        PageFactory.initElements(driver, this);
        Utility.switchToFrameByIdNameIndex(iFrameName);
        return msgList;
    }

    public boolean areOptionsPresent() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        try {
            return (McPizzaOrderBtn.isDisplayed() && denyBtn.isDisplayed());
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void denySuggestion() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        denyBtn.click();
    }

    public void clickOrderPizzBtn() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        McPizzaOrderBtn.click();
    }

    public void clickVegBtn() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        vegBtn.click();
    }

    public void clickNonvegBtn() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        nonvegBtn.click();
    }

    public void selectCheeseTopping() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        cheeseToppingChkbx.click();
        submitToppingBtn.click();
    }

    public void selectTomatoTopping() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        tomatoToppingChkbx.click();
        submitToppingBtn.click();
    }

    public void selectToppings(String[] toppings) throws Exception {
        if (toppings.length == 0) {
            throw new Exception("Select at-least one topping.");
        } else {
            for (int i = 0; i < toppings.length; i++) {

                if (toppings[i].equalsIgnoreCase("cheese")) {
                    Utility.switchToFrameByIdNameIndex(iFrameName);

                    if (!cheeseToppingChkbx.isSelected()) {
                        selectCheeseTopping();
                    }
                } else if (toppings[i].equalsIgnoreCase("tomato")) {
                    Utility.switchToFrameByIdNameIndex(iFrameName);

                    if (!tomatoToppingChkbx.isSelected()) {
                        selectTomatoTopping();
                    }
                } else if (toppings[i].equalsIgnoreCase("bacon")) {
                    Utility.switchToFrameByIdNameIndex(iFrameName);

                    if (!baconToppingChkbx.isSelected()) {
                        baconToppingChkbx.click();
                        submitToppingBtn.click();
                    }
                } else if (toppings[i].equalsIgnoreCase("Pepperoni")) {
                    Utility.switchToFrameByIdNameIndex(iFrameName);

                    if (!pepperoniToppingChkbx.isSelected()) {
                        pepperoniToppingChkbx.click();
                        submitToppingBtn.click();
                    }
                } else {
                    throw new Exception("This topping is not available.");
                }
            }
        }
    }

    public void chooseCrust(String crust) throws Exception {
        if (crust.equalsIgnoreCase("thin crust")) {
            Utility.switchToFrameByIdNameIndex(iFrameName);
            thinCrustBtn.click();
        } else if (crust.equalsIgnoreCase("thick crust")) {
            Utility.switchToFrameByIdNameIndex(iFrameName);
            thickCrustBtn.click();
        } else {
            throw new Exception("This crust is not available. Please cross check the crust.");
        }
    }

    public void selectSize(String size) throws Exception {
        if (size.equalsIgnoreCase("small")) {
            Utility.switchToFrameByIdNameIndex(iFrameName);
            smallSize.click();
        } else if (size.equalsIgnoreCase("medium")) {
            Utility.switchToFrameByIdNameIndex(iFrameName);
            mediumSize.click();
        } else if (size.equalsIgnoreCase("large")) {
            Utility.switchToFrameByIdNameIndex(iFrameName);
            largeSize.click();
        } else {
            throw new Exception("This size is not available. Please cross check the size.");
        }
    }

    public void confirmOrder() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        confirmOrderBtn.click();
    }

    public void doNotConfirmOrder() throws Exception {
        Utility.switchToFrameByIdNameIndex(iFrameName);
        notConfirmOrderBtn.click();
    }

    public void selectPizzaType(String type) throws Exception {

        if (type.equalsIgnoreCase("veg")) {
            clickVegBtn();
        } else if (type.equalsIgnoreCase("non-veg")) {
            clickNonvegBtn();
        } else {
            throw new Exception("Invalid pizza type, please cross-check pizza type.");
        }
    }

    public void giveFeedback(String feedback) throws Exception {
        if (feedback.equalsIgnoreCase("good")) {
            thumbsUpBtn.click();
            feedbackDD.click();
            feedbkExcelentOpt.click();
            feedbackSubmitBtn.click();
        } else if (feedback.equalsIgnoreCase("bad")) {
            thumbsDownBtn.click();
            feedbackTxtBox.sendKeys("Scope of improvement for bot responses.");
            feedbackSubmitBtn.click();
        } else {
            throw new Exception("Feedback not applicable.");
        }
    }
}
