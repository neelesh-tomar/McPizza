package org.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.qa.base.TestBase;
import org.qa.pages.ChatWindow;
import org.qa.pages.CommonPage;
import org.qa.pages.LoginPage;
import org.qa.pages.WelcomePage;
import org.qa.util.Utility;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ChatPageTest extends TestBase {
    LoginPage loginPage;
    CommonPage commonPage;
    WelcomePage welcomePage;
    ChatWindow chatWindow;

    public ChatPageTest() {
        super();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        initialize();

        commonPage = new CommonPage();
        welcomePage = new WelcomePage();
        loginPage = new LoginPage();
        chatWindow = new ChatWindow();
    }

    @Test(priority = 1)
    public void verifyChatWindowTitle() throws Exception {
        commonPage.startChat();
        welcomePage.clickGetStarted();

        loginPage.login(props.getProperty("firstName"), props.getProperty("email"));

        Assert.assertEquals(chatWindow.getTitle(), "McPizza");
    }


    @Test(priority = 2)
    public void verifyChatWelcomeMessage() throws Exception {
        commonPage.startChat();
        welcomePage.clickGetStarted();

        loginPage.login(props.getProperty("firstName"), props.getProperty("email"));

        Assert.assertEquals(chatWindow.getTitle(), "McPizza");
        Assert.assertEquals(chatWindow.getWelcomeMessage(), "Welcome to McPizza Booking Journey");
    }


    @DataProvider(name = "askForThePizzaOptionsData")
    public Object[][] getAskForThePizzaOptionsData() {
        Object[][] data = Utility.getTestData("ValidCommands");
        return data;
    }

    @Test(priority = 3, dataProvider = "askForThePizzaOptionsData")
    public void askForThePizzaOptions(String message) throws Exception {
        commonPage.startChat();
        welcomePage.clickGetStarted();

        loginPage.login(props.getProperty("firstName"), props.getProperty("email"));
        Assert.assertEquals(chatWindow.getTitle(), "McPizza");

        chatWindow.sendMessage(message);
        Thread.sleep(3000);

        List<WebElement> conversation = chatWindow.getAllMessages();

        if ((conversation.getLast().getAttribute("aria-label").contains("Bot sent"))) {
            if (chatWindow.areOptionsPresent()) {
                Assert.assertTrue(true, "'McPizza Order' and 'No, that's not it' buttons are present on screen.");
            } else {
                Assert.fail("Bot did not understand the command and responded : " + conversation.getLast().findElement(By.tagName("p")).getText());
            }
        } else {
            Assert.fail("Bot did not respond.");
        }
    }

    @DataProvider(name = "askWrongCommandData")
    public Object[][] askWrongCommandData() {
        Object[][] data = Utility.getTestData("InvalidCommands");
        return data;
    }

    @Test(priority = 4, dataProvider = "askWrongCommandData")
    public void askWrongCommand(String message) throws Exception {
        commonPage.startChat();
        welcomePage.clickGetStarted();

        loginPage.login(props.getProperty("firstName"), props.getProperty("email"));
        Assert.assertEquals(chatWindow.getTitle(), "McPizza");

        chatWindow.sendMessage(message);

        Thread.sleep(5000);

        List<WebElement> conversation = chatWindow.getAllMessages();

        if ((conversation.getLast().getAttribute("aria-label").contains("Bot sent"))) {
            if (chatWindow.areOptionsPresent()) {
                chatWindow.denySuggestion();
            } else {
                Assert.fail("Bot understood the command.");
            }
        } else {
            Assert.fail("Bot did not respond.");
        }
    }

    @DataProvider(name = "orderPizzaData")
    public Object[][] orderPizzaData() {
        Object[][] data = Utility.getTestData("orderPizza");
        return data;
    }

    @Test(priority = 5, dataProvider = "orderPizzaData")
    public void orderPizza(String message, String type, String topping, String crust, String size, String feedback) throws Exception {

        commonPage.startChat();
        welcomePage.clickGetStarted();

        loginPage.login(props.getProperty("firstName"), props.getProperty("email"));
        Assert.assertEquals(chatWindow.getTitle(), "McPizza");

        chatWindow.sendMessage(message);

        List<WebElement> conversation = chatWindow.getAllMessages();

        if (chatWindow.areOptionsPresent()) {
            chatWindow.clickOrderPizzBtn();
        } else {
            Assert.fail("Bot did not understand the command and responded : " + conversation.getLast().findElement(By.tagName("p")).getText());
        }

        chatWindow.selectPizzaType(type);
        chatWindow.selectToppings(topping.split(","));
        chatWindow.chooseCrust(crust);
        chatWindow.selectSize(size);
        chatWindow.confirmOrder();
        chatWindow.giveFeedback(feedback);
    }
    
    @AfterMethod
    public void tearUp() {
        driver.quit();
    }
}
